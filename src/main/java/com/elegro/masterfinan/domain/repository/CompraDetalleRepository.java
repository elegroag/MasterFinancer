package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.CompraDetalleDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.CompraDetalle;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CompraDetalleRepository extends AbsRecordLong<CompraDetalle> implements CompraDetalleDaoRepository {

    private static final String SQL_SELECT = "SELECT id, producto, compra, cantidad, precio_detalle FROM compra_detalles WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO compra_detalles (producto, compra, cantidad, precio_detalle)VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE compra_detalles SET producto=?, compra=?, cantidad=?, precio_detalle=? WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM compra_detalles WHERE id=?";

    public CompraDetalleRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "compra_detalles";
        this.primaryKey = "id";
        this.fillable = new String[] {"id", "producto", "compra", "cantidad", "precio_detalle"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public CompraDetalle recordModel(ResultSet rs) throws SQLException {
        CompraDetalle compraDetalle = new CompraDetalle();
        compraDetalle.setId(rs.getLong("id"));
        compraDetalle.setProducto(rs.getInt("producto"));
        compraDetalle.setCompra(rs.getInt("compra"));
        compraDetalle.setCantidad(rs.getInt("cantidad"));
        compraDetalle.setPrecioDetalle(rs.getDouble("precio_detalle"));
        return compraDetalle;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, CompraDetalle use) throws SQLException {
        stmt.setInt(1, use.getProducto());
        stmt.setInt(2, use.getCompra());
        stmt.setInt(3, use.getCantidad());
        stmt.setDouble(4, use.getPrecioDetalle());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, CompraDetalle use) throws SQLException {
        stmt.setInt(1, use.getProducto());
        stmt.setInt(2, use.getCompra());
        stmt.setInt(3, use.getCantidad());
        stmt.setDouble(4, use.getPrecioDetalle());
        stmt.setLong(5, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, CompraDetalle use) throws SQLException {
        stmt.setLong(1, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Optional<CompraDetalle> search(CompraDetalle use) throws DaoException {
        return Optional.empty();
    }
}
