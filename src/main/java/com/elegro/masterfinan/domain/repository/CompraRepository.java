package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.CompraDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Compra;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompraRepository extends AbsRecordInteger<Compra> implements CompraDaoRepository {

    private static final String SQl_SELECT = "SELECT id, nombres, apellidos, username, password, saldo, tipo_identificacion FROM compras WHERE 1;";

    private static final String SQl_INSERT = "INSERT INTO compras (id, nombres, apellidos, username, password, saldo, tipo_identificacion)VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQl_UPDATE = "UPDATE compras SET nombres=? , apellidos=?, username=?, password=?, saldo=?, tipo_identificacion=? WHERE id=?;";

    private static final String SQl_DELETE = "DELETE FROM compras WHERE id=?;";

    public CompraRepository(Connection conn){
        this.connectionTransactional = conn;
        this.table = "compras";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
        this.query.put("SQl_SELECT", SQl_SELECT);
        this.query.put("SQl_INSERT", SQl_INSERT);
        this.query.put("SQl_UPDATE", SQl_UPDATE);
        this.query.put("SQl_DELETE", SQl_DELETE);
    }

    @Override
    public Compra recordModel(ResultSet rs) throws SQLException {
        return null;
    }
}
