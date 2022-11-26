package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.UsuarioDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Usuario;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsuarioRepository extends AbsRecordLong<Usuario> implements UsuarioDaoRepository {


    private static final String SQL_SELECT = "SELECT id, nombres, apellidos, username, password, saldo, tipo_identificacion, email, terminos_condiciones FROM usuarios WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO usuarios (id, nombres, apellidos, username, password, saldo, tipo_identificacion, email, terminos_condiciones )VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE = "UPDATE usuarios SET nombres=? , apellidos=?, username=?, password=?, saldo=?, tipo_identificacion=?, email=?, terminos_condiciones=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id=?;";

    public UsuarioRepository(Connection conn)
    {
        this.connectionTransactional = conn;
        this.table = "usuarios";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombres", "apellidos", "username", "password", "saldo", "tipo_identificacion", "email", "terminos_condiciones"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Optional<Usuario> search(Usuario use) throws DaoException {
        return  Optional.empty();
    }

    public Usuario recordModel(ResultSet rs) throws SQLException {
        Usuario user = new Usuario();
        user.setId(rs.getLong("id"));
        user.setNombres(rs.getString("nombres"));
        user.setApellidos( rs.getString("apellidos"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setSaldo(rs.getDouble("saldo"));
        user.setTipoIdentificacion(rs.getInt("tipo_identificacion"));
        user.setEmail(rs.getString("email"));
        user.setTerminos_condiciones(rs.getBoolean("terminos_condiciones"));
        return user;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Usuario user) throws SQLException {
        stmt.setLong(1, user.getId());
        stmt.setString(2, user.getNombres());
        stmt.setString(3, user.getApellidos());
        stmt.setString(4, user.getUsername());
        stmt.setString(5, user.getPassword());
        stmt.setDouble(6, user.getSaldo());
        stmt.setInt(7, user.getTipoIdentificacion());
        stmt.setString(8, user.getEmail());
        stmt.setBoolean(9, user.isTerminos_condiciones());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Usuario user) throws SQLException {
        stmt.setString(1, user.getNombres());
        stmt.setString(2, user.getApellidos());
        stmt.setString(3, user.getUsername());
        stmt.setString(4, user.getPassword());
        stmt.setDouble(5, user.getSaldo());
        stmt.setInt(6, user.getTipoIdentificacion());
        stmt.setString(7, user.getEmail());
        stmt.setBoolean(8, user.isTerminos_condiciones());
        stmt.setLong(9, user.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Usuario user) throws SQLException {
        stmt.setLong(1, user.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Optional<Usuario> findByUsername(String username){
        try {
            String fields = String.join(",", fillable);
            Usuario user = find("SELECT "+fields+" FROM "+table+" WHERE username='"+username+"'", null);
            return Optional.of(user);
        }catch (DaoException err){
            return Optional.empty();
        }
    }
}
