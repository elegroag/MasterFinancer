package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.GastoCategoriaDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.GastoCategoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GastoCategoriaRepository extends AbsRecordLong<GastoCategoria> implements GastoCategoriaDaoRepository {

    private static final String SQL_SELECT = "SELECT id, detalle, esfijo FROM gastos_categorias WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO gastos_categorias (detalle, esfijo)VALUES (?, ?);";

    private static final String SQL_UPDATE = "UPDATE gastos_categorias SET detalle=?, esfijo=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM gastos_categorias WHERE id=?;";
    public GastoCategoriaRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "gastos_categorias";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "detalle", "esfijo"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public GastoCategoria recordModel(ResultSet rs) throws SQLException {
        GastoCategoria gastoCategoria = new GastoCategoria();
        gastoCategoria.setDetalle(rs.getString("detalle"));
        gastoCategoria.setEsfijo(rs.getInt("esfijo") == 1);
        return gastoCategoria;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, GastoCategoria gastoCategoria) throws SQLException {
       stmt.setString(1, gastoCategoria.getDetalle());
       stmt.setBoolean(2, gastoCategoria.getEsfijo());
       return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, GastoCategoria gastoCategoria) throws SQLException {
        stmt.setString(1, gastoCategoria.getDetalle());
        stmt.setBoolean(2, gastoCategoria.getEsfijo());
        stmt.setLong(3, gastoCategoria.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, GastoCategoria gastoCategoria) throws SQLException {
        stmt.setLong(1, gastoCategoria.getId());
        return stmt.executeUpdate();
    }
}
