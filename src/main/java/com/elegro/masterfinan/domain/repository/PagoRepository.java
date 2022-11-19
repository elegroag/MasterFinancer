package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.PagoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Pago;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagoRepository extends AbsRecordLong<Pago> implements PagoDaoRepository {

    private static final String SQl_SELECT = "SELECT id, tipo_persona, identificacion, nombres, FROM pagos WHERE 1;";

    private static final String SQl_INSERT = "INSERT INTO pagos (tipo_persona, identificacion, nombres, )VALUES (?, ?, ?, ?, )";

    private static final String SQl_UPDATE = "UPDATE pagos SET tipo_persona=?, identificacion=?, ciudad=?, tipo_identificacion=?  WHERE id=?";

    private static final String SQl_DELETE = "DELETE FROM pagos WHERE id=?";

    public PagoRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "pagos";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
        this.query.put("SQl_SELECT", SQl_SELECT);
        this.query.put("SQl_INSERT", SQl_INSERT);
        this.query.put("SQl_UPDATE", SQl_UPDATE);
        this.query.put("SQl_DELETE", SQl_DELETE);
    }

    @Override
    public Pago recordModel(ResultSet rs) throws SQLException {
        return null;
    }

}
