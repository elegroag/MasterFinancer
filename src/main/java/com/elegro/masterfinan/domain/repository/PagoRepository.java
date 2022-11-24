package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.PagoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Pago;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PagoRepository extends AbsRecordLong<Pago> implements PagoDaoRepository {

    private static final String SQL_SELECT = "SELECT id, transaccion, medio_pago, compra FROM pagos WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO pagos (transaccion, medio_pago, compra)VALUES (?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE pagos SET transaccion=?, medio_pago=?, compra=? WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM pagos WHERE id=?";

    public PagoRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "pagos";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "transaccion", "medio_pago", "compra"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Pago recordModel(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        Integer transaccion = rs.getInt("transaccion");
        String medio_pago =  rs.getString("medio_pago");
        Integer compra =  rs.getInt("compra");

        Pago pago = new Pago();
        pago.setId(id);
        pago.setTransaccion(transaccion);
        pago.setMedioPago(medio_pago);
        pago.setCompra(compra);
        return pago;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Pago use) throws SQLException {
        stmt.setInt(1, use.getTransaccion());
        stmt.setString(2, use.getMedioPago());
        stmt.setInt(3, use.getCompra());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Pago use) throws SQLException {
        stmt.setInt(1, use.getTransaccion());
        stmt.setString(2, use.getMedioPago());
        stmt.setInt(3, use.getCompra());
        stmt.setLong(4, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Pago use) throws SQLException {
        stmt.setLong(1, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Optional<Pago> search(Pago use) throws DaoException {
        return Optional.empty();
    }
}
