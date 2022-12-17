package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.TipoIdentificacionDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.TipoIdentificacion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoIdentificacionRepository extends AbsRecordInteger<TipoIdentificacion> implements TipoIdentificacionDaoRepository {

    private static final String SQL_SELECT = "SELECT id, abreviatura, detalle FROM tipo_identificaciones WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO tipo_identificaciones (abreviatura, detalle)VALUES (?, ?);";

    private static final String SQL_UPDATE = "UPDATE tipo_identificaciones SET abreviatura=?, detalle=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM tipo_identificaciones WHERE id=?;";

    public TipoIdentificacionRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "tipo_identificaciones";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "abreviatura", "detalle"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public TipoIdentificacion recordModel(ResultSet rs) throws SQLException, DaoException {
        TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();
        tipoIdentificacion.setId(rs.getInt("id"));
        tipoIdentificacion.setAbreviatura(rs.getString("abreviatura"));
        tipoIdentificacion.setDetalle(rs.getString("detalle"));
        return tipoIdentificacion;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, TipoIdentificacion use) throws SQLException {
        stmt.setString(1, use.getAbreviatura());
        stmt.setString(2, use.getDetalle());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, TipoIdentificacion use) throws SQLException {
        stmt.setString(1, use.getAbreviatura());
        stmt.setString(2, use.getDetalle());
        stmt.setLong(3, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, TipoIdentificacion use) throws SQLException {
        stmt.setLong(1, use.getId());
        return stmt.executeUpdate();
    }
}
