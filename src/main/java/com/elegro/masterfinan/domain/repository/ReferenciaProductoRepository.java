package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.ReferenciaProductoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.ReferenciaProducto;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReferenciaProductoRepository extends AbsRecordLong<ReferenciaProducto> implements ReferenciaProductoDaoRepository {

    private static final String SQL_SELECT = "SELECT id, detalle, persona FROM referecia_productos WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO referecia_productos (detalle, persona)VALUES (?, ?)";

    private static final String SQL_UPDATE = "UPDATE referecia_productos SET detalle=?, persona=?  WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM referecia_productos WHERE id=?";

    public ReferenciaProductoRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "referecia_productos";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "detalle", "persona"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public ReferenciaProducto recordModel(ResultSet rs) throws SQLException {
        ReferenciaProducto referenciaProducto = new ReferenciaProducto();
        referenciaProducto.setId(rs.getLong("id"));
        referenciaProducto.setDetalle(rs.getString("detalle"));
        referenciaProducto.setPersona(rs.getLong("persona"));
        return referenciaProducto;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, ReferenciaProducto use) throws SQLException {
        stmt.setString(1, use.getDetalle());
        stmt.setLong(2, use.getPersona());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, ReferenciaProducto use) throws SQLException {
        stmt.setString(1, use.getDetalle());
        stmt.setLong(2, use.getPersona());
        stmt.setLong(3, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, ReferenciaProducto use) throws SQLException {
        stmt.setLong(1, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Optional<ReferenciaProducto> search(ReferenciaProducto use) throws DaoException {
        return Optional.empty();
    }
}
