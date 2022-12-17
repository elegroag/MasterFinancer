package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.CiudadDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Ciudad;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CiudadRepository extends AbsRecordInteger<Ciudad> implements CiudadDaoRepository {

    private static final String SQL_SELECT = "SELECT id, nombre_ciudad, departamento FROM ciudades WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO ciudades (nombre_ciudad, departamento)VALUES (?, ?);";

    private static final String SQL_UPDATE = "UPDATE ciudades SET nombre_ciudad=?, departamento=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM ciudades WHERE id=?;";

    public CiudadRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "ciudades";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombre_ciudad", "departamento"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Ciudad recordModel(ResultSet rs) throws SQLException, DaoException {
        Ciudad ciudad = new Ciudad();
        ciudad.setId(rs.getInt("id"));
        ciudad.setNombreCiudad(rs.getString("nombre_ciudad"));
        ciudad.setDepartamento(rs.getInt("departamento"));
        return ciudad;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Ciudad ciudad) throws SQLException {
        stmt.setString(1, ciudad.getNombreCiudad());
        stmt.setInt(2, ciudad.getDepartamento());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Ciudad ciudad) throws SQLException {
        stmt.setString(1, ciudad.getNombreCiudad());
        stmt.setInt(2, ciudad.getDepartamento());
        stmt.setInt(3, ciudad.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Ciudad ciudad) throws SQLException {
        stmt.setInt(1, ciudad.getId());
        return stmt.executeUpdate();
    }
}
