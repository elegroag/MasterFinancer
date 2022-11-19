package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.UsuarioDaoRepository;
import com.elegro.masterfinan.infraestructura.dao.MysqlConnector;
import com.elegro.masterfinan.infraestructura.entity.Usuario;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository implements UsuarioDaoRepository {

    String table = "usuarios";
    String primaryKey = "id";
    String fields;
    String[] fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion"};
    protected  Connection connectionTransactional;
    private static final String SQl_SELECT = "SELECT id, nombres, apellidos, username, password, saldo, tipo_identificacion FROM usuarios WHERE 1;";

    private static final String SQl_INSERT = "INSERT INTO usuarios (id, nombres, apellidos, username, password, saldo, tipo_identificacion)VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQl_UPDATE = "UPDATE usuarios SET nombres=? , apellidos=?, username=?, password=?, saldo=?, tipo_identificacion=? WHERE id=?";

    private static final String SQl_DELETE = "DELETE FROM usuarios WHERE id=?";

    public UsuarioRepository(){
    }

    public UsuarioRepository(Connection conn){
        this.connectionTransactional = conn;
    }

    public List<Usuario> findAll()throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList<>();

        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(SQl_SELECT);
            rs = stmt.executeQuery();
            try {
                while (rs.next()) {
                    lista.add(recordModel(rs));
                }
            } catch (SQLException ex) {
                throw new DaoException("Error de sql", ex);
            } finally {
                if (this.connectionTransactional == null) {
                    MysqlConnector.close(stmt);
                    MysqlConnector.close(conn);
                    MysqlConnector.close(rs);
                }
            }
        } catch (SQLException er) {
            MysqlConnector.exep(er);
        }
        return lista;
    }

    @Override
    public void insert(Usuario use) throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(SQl_INSERT);
            try {
                stmt.setLong(1, use.getId());
                stmt.setString(2, use.getNombres());
                stmt.setString(3, use.getApellidos());
                stmt.setString(4, use.getUsername());
                stmt.setString(5, use.getPassword());
                stmt.setDouble(6, use.getSaldo());
                stmt.setInt(7, use.getTipoIdentificacion());
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
    }

    @Override
    public void update(Usuario user)throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(SQl_UPDATE);
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
    }

    @Override
    public void delete(Usuario user)throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(SQl_DELETE);
            try {
                stmt.setFloat(1, user.getId());
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
    }

    @Override
    public void search(Usuario use) throws DaoException {
    }

    @Override
    public Usuario findById(Long id)throws DaoException {
        fields = String.join(",", fillable);
        return find("SELECT "+fields+" FROM "+table+" WHERE "+primaryKey+"=?", id);
    }

    @Override
    public Usuario findOne(Long id)throws DaoException {
        fields = String.join(",", fillable);
        return find("SELECT "+fields+" FROM "+table+" WHERE "+primaryKey+"=? LIMIT 1", id);
    }

    @Override
    public Usuario findFirst()throws DaoException {
        fields = String.join(",", fillable);
        return find("SELECT "+fields+" FROM "+table+" WHERE 1=1 ORDER BY "+primaryKey+" ASC LIMIT 1", null);
    }

    @Override
    public Usuario findLast()throws DaoException {
        fields = String.join(",", fillable);
        return find("SELECT "+fields+" FROM "+table+" WHERE 1=1 ORDER BY "+primaryKey+" DESC LIMIT 1", null);
    }

    private Usuario find(String sql, Long id)throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = null;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(sql);
            if(id != null){
                stmt.setLong(1, id);
            }
            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    user = recordModel(rs);
                }
            } catch (SQLException ex) {
                throw new DaoException("Error de sql", ex);
            } finally {
                if (this.connectionTransactional == null) {
                    MysqlConnector.close(stmt);
                    MysqlConnector.close(conn);
                    if(rs != null){
                        MysqlConnector.close(rs);
                    }
                }
            }
        } catch (SQLException er) {
            MysqlConnector.exep(er);
        }
        return user;
    }

    private Usuario recordModel(ResultSet rs) throws SQLException {
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
