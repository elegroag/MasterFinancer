package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.PersonaDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Persona;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PersonaRepository extends AbsRecordLong<Persona> implements PersonaDaoRepository {

    private static final String SQL_SELECT = "SELECT id, tipo_persona, identificacion, nombres, apellidos, celular, email, telefono_comercial, direccion_comercial, pais, departamento, ciudad, tipo_identificacion FROM personas WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO personas (tipo_persona, identificacion, nombres, apellidos, celular, email, telefono_comercial, direccion_comercial, pais, departamento, ciudad, tipo_identificacion)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE personas SET tipo_persona=?, identificacion=?, nombres=?, apellidos=?, celular=?, email=?, telefono_comercial=?, direccion_comercial=?, pais=?, departamento=?, ciudad=?, tipo_identificacion=?  WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM personas WHERE id=?";

    public PersonaRepository(Connection conn){
        this.connectionTransactional = conn;
        this.table = "personas";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "tipo_persona", "identificacion", "nombres", "apellidos", "celular", "email", "telefono_comercial", "direccion_comercial", "pais", "departamento", "ciudad", "tipo_identificacion"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Persona recordModel(ResultSet rs) throws SQLException {
        Persona persona = new Persona();
        persona.setId(rs.getLong("id"));
        persona.setTipoPersona(rs.getString("tipo_persona"));
        persona.setIdentificacion(rs.getInt("identificacion"));
        persona.setNombres(rs.getString("nombres"));
        persona.setApellidos(rs.getString("apellidos"));
        persona.setCelular(rs.getString("celular"));
        persona.setEmail(rs.getString("email"));
        persona.setTelefonoComercial(rs.getString("telefono_comercial"));
        persona.setDireccionComercial(rs.getString("direccion_comercial"));
        persona.setPais(rs.getInt("pais"));
        persona.setDepartamento(rs.getInt("departamento"));
        persona.setCiudad(rs.getInt("ciudad"));
        persona.setTipoIdentificacion(rs.getInt("tipo_identificacion"));
        return persona;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Persona use) throws SQLException {
        stmt.setString(1, use.getTipoPersona());
        stmt.setInt(2, use.getTipoIdentificacion());
        stmt.setString(3, use.getNombres());
        stmt.setString(4, use.getApellidos());
        stmt.setString(5, use.getCelular());
        stmt.setString(6, use.getEmail());
        stmt.setString(7, use.getTelefonoComercial());
        stmt.setString(8, use.getDireccionComercial());
        stmt.setInt(9, use.getPais());
        stmt.setInt(10, use.getDepartamento());
        stmt.setInt(11, use.getCiudad());
        stmt.setInt(12, use.getTipoIdentificacion());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Persona use) throws SQLException {
        stmt.setString(1, use.getTipoPersona());
        stmt.setInt(2, use.getTipoIdentificacion());
        stmt.setString(3, use.getNombres());
        stmt.setString(4, use.getApellidos());
        stmt.setString(5, use.getCelular());
        stmt.setString(6, use.getEmail());
        stmt.setString(7, use.getTelefonoComercial());
        stmt.setString(8, use.getDireccionComercial());
        stmt.setInt(9, use.getPais());
        stmt.setInt(10, use.getDepartamento());
        stmt.setInt(11, use.getCiudad());
        stmt.setInt(12, use.getTipoIdentificacion());
        stmt.setLong(13, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Persona use) throws SQLException {
        stmt.setLong(1, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Optional<Persona> search(Persona use) throws DaoException {
        return Optional.empty();
    }
}
