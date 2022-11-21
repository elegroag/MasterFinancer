package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.GastoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Gasto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GastoRepository  extends AbsRecordLong<Gasto> implements GastoDaoRepository {

    private static final String SQL_SELECT = "SELECT id, tipo_persona, identificacion, nombres, FROM gastos WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO gastos (tipo_persona, identificacion, nombres, )VALUES (?, ?, ?, ?, )";

    private static final String SQL_UPDATE = "UPDATE gastos SET tipo_persona=?, identificacion=?, ciudad=?, tipo_identificacion=?  WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM gastos WHERE id=?";

    public GastoRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "gastos";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Gasto recordModel(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Gasto use) throws SQLException {
        return null;
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Gasto use) throws SQLException {
        return null;
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Gasto use) throws SQLException {
        return null;
    }
}