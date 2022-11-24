package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.CompraDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Compra;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class CompraRepository extends AbsRecordInteger<Compra> implements CompraDaoRepository {

    private static final String SQL_SELECT = "SELECT id, estado_credito, fecha_inicial, fecha_final, saldo_pendiente, persona, tipo_pago, valor_compra, usuario FROM compras WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO compras (estado_credito, fecha_inicial, fecha_final, saldo_pendiente, persona, tipo_pago, valor_compra, usuario)VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE = "UPDATE compras SET estado_credito=?, fecha_inicial=?, fecha_final=?, saldo_pendiente=?, persona=?, tipo_pago=?, valor_compra=?, usuario=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM compras WHERE id=?;";

    public CompraRepository(Connection conn){
        this.connectionTransactional = conn;
        this.table = "compras";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "estado_credito", "fecha_inicial", "fecha_final", "saldo_pendiente", "persona", "tipo_pago", "valor_compra", "usuario"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Compra recordModel(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String estado_credito = rs.getString("estado_credito");
        LocalDate fecha_inicial = LocalDate.parse(rs.getString("fecha_inicial"));
        LocalDate fecha_final = LocalDate.parse(rs.getString("fecha_final"));
        Double saldo_pendiente = rs.getDouble("saldo_pendiente");
        Long perdona = rs.getLong("persona");
        String tipo_pago = rs.getString("tipo_pago");
        Double valor_compra = rs.getDouble("valor_compra");
        Long usuario = rs.getLong("usuario");

        Compra compra = new Compra();
        compra.setId(id);
        compra.setEstadoCredito(estado_credito);
        compra.setFechaFinal(fecha_final);
        compra.setFechaInicial(fecha_inicial);
        compra.setSaldoPendiente(saldo_pendiente);
        compra.setTipoPago(tipo_pago);
        compra.setValorCompra(valor_compra);
        compra.setPersona(perdona);
        compra.setUsuario(usuario);
        return compra;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Compra use) throws SQLException {
        stmt.setString(1, use.getEstadoCredito());
        stmt.setObject(2, use.getFechaInicial());
        stmt.setObject(3, use.getFechaFinal());
        stmt.setDouble(4, use.getSaldoPendiente());
        stmt.setLong(5, use.getPersona());
        stmt.setString(6, use.getTipoPago());
        stmt.setDouble(7, use.getValorCompra());
        stmt.setLong(8, use.getUsuario());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Compra use) throws SQLException {
        stmt.setString(1, use.getEstadoCredito());
        stmt.setObject(2, use.getFechaInicial());
        stmt.setObject(3, use.getFechaFinal());
        stmt.setDouble(4, use.getSaldoPendiente());
        stmt.setLong(5, use.getPersona());
        stmt.setString(6, use.getTipoPago());
        stmt.setDouble(7, use.getValorCompra());
        stmt.setLong(8, use.getUsuario());
        stmt.setInt(9, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Compra use) throws SQLException {
        stmt.setInt(1, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Optional<Compra> search(Compra use) throws DaoException {
        return null;
    }
}
