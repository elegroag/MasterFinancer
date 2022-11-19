package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.AbonoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Abono;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbonoRepository extends AbsRecordLong<Abono> implements AbonoDaoRepository {

    private static final String SQl_SELECT = "SELECT id, nombres, apellidos, username, password, saldo, tipo_identificacion FROM abonos WHERE 1;";

    private static final String SQl_INSERT = "INSERT INTO abonos (id, nombres, apellidos, username, password, saldo, tipo_identificacion)VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQl_UPDATE = "UPDATE abonos SET nombres=? , apellidos=?, username=?, password=?, saldo=?, tipo_identificacion=? WHERE id=?;";

    private static final String SQl_DELETE = "DELETE FROM abonos WHERE id=?;";
    public AbonoRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "abonos";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
        this.query.put("SQl_SELECT", SQl_SELECT);
        this.query.put("SQl_INSERT", SQl_INSERT);
        this.query.put("SQl_UPDATE", SQl_UPDATE);
        this.query.put("SQl_DELETE", SQl_DELETE);
    }

    @Override
    public Abono recordModel(ResultSet rs) throws SQLException {
        return null;
    }
}
