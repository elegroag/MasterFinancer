package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.UsuarioDaoRepository;
import com.elegro.masterfinan.infraestructura.dao.MysqlConnector;
import com.elegro.masterfinan.infraestructura.entity.Usuario;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository extends AbsRecordLong<Usuario> implements UsuarioDaoRepository {


    private static final String SQL_SELECT = "SELECT id, nombres, apellidos, username, password, saldo, tipo_identificacion FROM usuarios WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO usuarios (id, nombres, apellidos, username, password, saldo, tipo_identificacion)VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE = "UPDATE usuarios SET nombres=? , apellidos=?, username=?, password=?, saldo=?, tipo_identificacion=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id=?;";

    public UsuarioRepository(Connection conn)
    {
        this.connectionTransactional = conn;
        this.table = "usuarios";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }
    @Override
    public Usuario insert(Usuario user) throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            try {
                stmt.setLong(1, user.getId());
                stmt.setString(2, user.getNombres());
                stmt.setString(3, user.getApellidos());
                stmt.setString(4, user.getUsername());
                stmt.setString(5, user.getPassword());
                stmt.setDouble(6, user.getSaldo());
                stmt.setInt(7, user.getTipoIdentificacion());
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
        return user;
    }

    @Override
    public boolean update(Usuario user)throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean estado = false;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            try {
                stmt.setString(1, user.getNombres());
                stmt.setString(2, user.getApellidos());
                stmt.setString(3, user.getUsername());
                stmt.setString(4, user.getPassword());
                stmt.setDouble(5, user.getSaldo());
                stmt.setInt(6, user.getTipoIdentificacion());
                stmt.setLong(7, user.getId());
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
    public boolean delete(Usuario use)throws DaoException {
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
    public Usuario search(Usuario use) throws DaoException {
        return  use;
    }

    public Usuario recordModel(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String nombres = rs.getString("nombres");
        String apellidos = rs.getString("apellidos");
        String username = rs.getString("username");
        String password = rs.getString("password");
        double saldo = rs.getDouble("saldo");
        int tipo_identificacion = rs.getInt("tipo_identificacion");

        Usuario user = new Usuario();
        user.setId(id);
        user.setNombres(nombres);
        user.setApellidos(apellidos);
        user.setUsername(username);
        user.setPassword(password);
        user.setSaldo(saldo);
        user.setTipoIdentificacion(tipo_identificacion);
        return user;
    }
}
