package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.AbonoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Abono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbonoRepository extends AbsRecordLong<Abono> implements AbonoDaoRepository {

    private static final String SQL_SELECT = "SELECT id, nombres, apellidos, username, password, saldo, tipo_identificacion FROM abonos WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO abonos (id, nombres, apellidos, username, password, saldo, tipo_identificacion)VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE = "UPDATE abonos SET nombres=? , apellidos=?, username=?, password=?, saldo=?, tipo_identificacion=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM abonos WHERE id=?;";
    public AbonoRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "abonos";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Abono recordModel(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Abono use) throws SQLException {
        return null;
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Abono use) throws SQLException {
        return null;
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Abono use) throws SQLException {
        return null;
    }
}
