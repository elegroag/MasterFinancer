package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.PersonaDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Persona;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class PersonaRepository extends AbsRecordLong<Persona> implements PersonaDaoRepository {

    private static final String SQl_SELECT = "SELECT id, tipo_persona, identificacion, nombres, apellidos, celular, email, telefono_comercial, direccion_comercial, pais, departamento, ciudad, tipo_identificacion FROM personas WHERE 1;";

    private static final String SQl_INSERT = "INSERT INTO personas (tipo_persona, identificacion, nombres, apellidos, celular, email, telefono_comercial, direccion_comercial, pais, departamento, ciudad, tipo_identificacion)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQl_UPDATE = "UPDATE personas SET tipo_persona=?, identificacion=?, nombres=?, apellidos=?, celular=?, email=?, telefono_comercial=?, direccion_comercial=?, pais=?, departamento=?, ciudad=?, tipo_identificacion=?  WHERE id=?";

    private static final String SQl_DELETE = "DELETE FROM personas WHERE id=?";

    public PersonaRepository(Connection conn){
        this.connectionTransactional = conn;
        this.table = "personas";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "tipo_persona", "identificacion", "nombres", "apellidos", "celular", "email", "telefono_comercial", "direccion_comercial", "pais", "departamento", "ciudad", "tipo_identificacion"};
        this.query.put("SQl_SELECT", SQl_SELECT);
        this.query.put("SQl_INSERT", SQl_INSERT);
        this.query.put("SQl_UPDATE", SQl_UPDATE);
        this.query.put("SQl_DELETE", SQl_DELETE);
    }

    @Override
    public Persona recordModel(ResultSet rs) throws SQLException {
        return null;
    }
}
