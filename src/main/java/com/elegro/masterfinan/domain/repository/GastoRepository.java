package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.GastoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Gasto;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class GastoRepository  extends AbsRecordLong<Gasto> implements GastoDaoRepository {

    private static final String SQL_SELECT = "SELECT id, transaccion, medio_pago, gasto_categoria FROM gastos WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO gastos (transaccion, medio_pago, gasto_categoria)VALUES (?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE gastos SET transaccion=?, medio_pago=?, gasto_categoria=?  WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM gastos WHERE id=?";

    public GastoRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "gastos";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "transaccion", "medio_pago", "gasto_categoria"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Gasto recordModel(ResultSet rs) throws SQLException {
        Gasto gasto = new Gasto();
        gasto.setTransaccion(rs.getInt("transaccion"));
        gasto.setMedioPago(rs.getString("medio_pago"));
        gasto.setGastoCategoria(rs.getInt("gasto_categoria"));
        return gasto;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Gasto gasto) throws SQLException {
        stmt.setInt(1, gasto.getTransaccion());
        stmt.setString(2, gasto.getMedioPago());
        stmt.setInt(3, gasto.getGastoCategoria());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Gasto gasto) throws SQLException {
        stmt.setInt(1, gasto.getTransaccion());
        stmt.setString(2, gasto.getMedioPago());
        stmt.setInt(3, gasto.getGastoCategoria());
        stmt.setLong(4, gasto.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Gasto gasto) throws SQLException {
        stmt.setLong(1, gasto.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Optional<Gasto> search(Gasto use) throws DaoException {
        return Optional.empty();
    }
}