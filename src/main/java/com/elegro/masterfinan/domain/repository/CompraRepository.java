package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.CompraDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Compra;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompraRepository extends AbsRecordInteger<Compra> implements CompraDaoRepository {

    private static final String SQL_SELECT = "SELECT id, nombres, apellidos, username, password, saldo, tipo_identificacion FROM compras WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO compras (id, nombres, apellidos, username, password, saldo, tipo_identificacion)VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE = "UPDATE compras SET nombres=? , apellidos=?, username=?, password=?, saldo=?, tipo_identificacion=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM compras WHERE id=?;";

    public CompraRepository(Connection conn){
        this.connectionTransactional = conn;
        this.table = "compras";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Compra recordModel(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Compra insert(Compra use) throws DaoException {
        return null;
    }

    @Override
    public boolean update(Compra use) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Compra use) throws DaoException {
        return false;
    }

    @Override
    public Compra search(Compra use) throws DaoException {
        return null;
    }
}
