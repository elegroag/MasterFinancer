package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.DepartamentoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Departamento;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartamentoRepository extends AbsRecordInteger<Departamento> implements DepartamentoDaoRepository {

    private static final String SQL_SELECT = "SELECT id, nombre_departamento, pais FROM departamentos WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO departamentos (nombre_departamento, pais)VALUES (?, ?);";

    private static final String SQL_UPDATE = "UPDATE departamentos SET nombre_departamento=?, pais=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM departamentos WHERE id=?;";

    public DepartamentoRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "departamentos";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombre_departamento", "pais"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Departamento recordModel(ResultSet rs) throws SQLException, DaoException {
        Departamento departamento = new Departamento();
        departamento.setNombreDepartamento(rs.getString("nombre_departamento"));
        departamento.setPais(rs.getInt("pais"));
        return departamento;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Departamento departamento) throws SQLException {
        stmt.setString(1, departamento.getNombreDepartamento());
        stmt.setInt(2, departamento.getPais());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Departamento departamento) throws SQLException {
        stmt.setString(1, departamento.getNombreDepartamento());
        stmt.setInt(2, departamento.getPais());
        stmt.setInt(3, departamento.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Departamento departamento) throws SQLException {
        stmt.setInt(1, departamento.getId());
        return stmt.executeUpdate();
    }
}
