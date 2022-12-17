package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.PaisDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Pais;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaisRepository extends AbsRecordInteger<Pais> implements PaisDaoRepository {

    private static final String SQL_SELECT = "SELECT id, nombre_pais FROM paises WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO paises (nombre_pais)VALUES (?);";

    private static final String SQL_UPDATE = "UPDATE paises SET nombre_pais=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM paises WHERE id=?;";

    public PaisRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "paises";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "nombre_pais"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Pais recordModel(ResultSet rs) throws SQLException, DaoException {
        Pais pais = new Pais();
        pais.setId(rs.getInt("id"));
        pais.setNombrePais(rs.getString("nombre_pais"));
        return pais;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Pais pais) throws SQLException {
        stmt.setString(1, pais.getNombrePais());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Pais pais) throws SQLException {
        stmt.setString(1, pais.getNombrePais());
        stmt.setInt(2, pais.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Pais pais) throws SQLException {
        stmt.setInt(1, pais.getId());
        return stmt.executeUpdate();
    }
}
