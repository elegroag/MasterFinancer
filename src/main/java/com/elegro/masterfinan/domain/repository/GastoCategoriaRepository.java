package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.GastoCategoriaDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.GastoCategoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GastoCategoriaRepository extends AbsRecordLong<GastoCategoria> implements GastoCategoriaDaoRepository {

    private static final String SQL_SELECT = "SELECT id, nombres, apellidos, username, password, saldo, tipo_identificacion FROM gastos_categorias WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO gastos_categorias (id, nombres, apellidos, username, password, saldo, tipo_identificacion)VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE = "UPDATE gastos_categorias SET nombres=? , apellidos=?, username=?, password=?, saldo=?, tipo_identificacion=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM gastos_categorias WHERE id=?;";
    public GastoCategoriaRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "gastos_categorias";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public GastoCategoria recordModel(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, GastoCategoria use) throws SQLException {
        return null;
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, GastoCategoria use) throws SQLException {
        return null;
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, GastoCategoria use) throws SQLException {
        return null;
    }
}
