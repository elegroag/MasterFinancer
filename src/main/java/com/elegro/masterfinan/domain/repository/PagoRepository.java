package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.PagoDaoRepository;
import com.elegro.masterfinan.infraestructura.dao.MysqlConnector;
import com.elegro.masterfinan.infraestructura.entity.Pago;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagoRepository extends AbsRecordLong<Pago> implements PagoDaoRepository {

    private static final String SQL_SELECT = "SELECT id, tipo_persona, identificacion, nombres, FROM pagos WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO pagos (tipo_persona, identificacion, nombres, )VALUES (?, ?, ?, ?, )";

    private static final String SQL_UPDATE = "UPDATE pagos SET tipo_persona=?, identificacion=?, ciudad=?, tipo_identificacion=?  WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM pagos WHERE id=?";

    public PagoRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "pagos";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Pago recordModel(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Pago insert(Pago use) throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            try {
                stmt.setLong(1, use.getId());
                stmt.executeUpdate();
            } catch (SQLException ex) {
                throw new DaoException("Error de sql", ex);
            } finally {
                if (this.connectionTransactional == null) {
                    MysqlConnector.close(stmt);
                    MysqlConnector.close(conn);
                }
            }
        } catch (SQLException er) {
            MysqlConnector.exep(er);
        }
        return use;
    }

    @Override
    public boolean update(Pago use) throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean estado = false;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            try {
                stmt.setLong(7, use.getId());
                stmt.executeUpdate();
            } catch (SQLException er) {
                throw new DaoException("Error sql", er);
            } finally {
                if (this.connectionTransactional == null) {
                    MysqlConnector.close(stmt);
                    MysqlConnector.close(conn);
                }
            }
        } catch (SQLException ex) {
            MysqlConnector.exep(ex);
        }
        return estado;
    }

    @Override
    public boolean delete(Pago use) throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean estado = false;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            try {
                stmt.setFloat(1, use.getId());
                stmt.executeUpdate();
            } catch (SQLException er) {
                throw new DaoException("Error SQl", er);
            } finally {
                if (this.connectionTransactional == null) {
                    MysqlConnector.close(stmt);
                    MysqlConnector.close(conn);
                }
            }
        } catch (SQLException ex) {
            MysqlConnector.exep(ex);
        }
        return estado;
    }

    @Override
    public Pago search(Pago use) throws DaoException {
        return null;
    }
}
