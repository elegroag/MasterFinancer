package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.IngresoCategoriaDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.GastoCategoria;
import com.elegro.masterfinan.infraestructura.entity.IngresoCategoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngresoCategoriaRepository extends AbsRecordLong<IngresoCategoria> implements IngresoCategoriaDaoRepository {

    private static final String SQL_SELECT = "SELECT id, detalle, esfijo FROM ingreso_categorias WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO ingreso_categorias (detalle, esfijo)VALUES (?, ?);";

    private static final String SQL_UPDATE = "UPDATE ingreso_categorias SET detalle=?, esfijo=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM ingreso_categorias WHERE id=?;";

    public IngresoCategoriaRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "ingreso_categorias";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "detalle", "esfijo"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public IngresoCategoria recordModel(ResultSet rs) throws SQLException {
        IngresoCategoria ingresoCategoria = new IngresoCategoria();
        ingresoCategoria.setDetalle(rs.getString("detalle"));
        ingresoCategoria.setEsfijo(rs.getInt("esfijo") == 1);
        return ingresoCategoria;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, IngresoCategoria ingresoCategoria) throws SQLException {
        stmt.setString(1, ingresoCategoria.getDetalle());
        stmt.setBoolean(2, ingresoCategoria.getEsfijo());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, IngresoCategoria ingresoCategoria) throws SQLException {
        stmt.setString(1, ingresoCategoria.getDetalle());
        stmt.setBoolean(2, ingresoCategoria.getEsfijo());
        stmt.setLong(3, ingresoCategoria.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, IngresoCategoria ingresoCategoria) throws SQLException {
        stmt.setLong(1, ingresoCategoria.getId());
        return stmt.executeUpdate();
    }
}
