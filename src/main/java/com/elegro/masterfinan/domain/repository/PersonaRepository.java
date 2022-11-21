package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.PersonaDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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
        return null;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Persona use) throws SQLException {
        return null;
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Persona use) throws SQLException {
        return null;
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Persona use) throws SQLException {
        return null;
    }
}
