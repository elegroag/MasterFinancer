package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.AbonoDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Abono;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AbonoRepository extends AbsRecordLong<Abono> implements AbonoDaoRepository {

    private static final String SQL_SELECT = "SELECT id, transaccion, medio_pago, categoria_ingreso, persona FROM abonos WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO abonos (transaccion, medio_pago, categoria_ingreso, persona)VALUES (?, ?, ?, ?);";

    private static final String SQL_UPDATE = "UPDATE abonos SET transaccion=?, medio_pago=?, categoria_ingreso=?, persona=? WHERE id=?;";

    private static final String SQL_DELETE = "DELETE FROM abonos WHERE id=?;";
    public AbonoRepository(Connection conn) {
        this.connectionTransactional = conn;
        this.table = "abonos";
        this.primaryKey = "id";
        this.fillable = new String[] {"id", "transaccion", "medio_pago", "categoria_ingreso", "persona"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }

    @Override
    public Abono recordModel(ResultSet rs) throws SQLException {
        Abono abono = new Abono();
        abono.setId(rs.getLong("id"));
        abono.setTransaccion(rs.getInt("transaccion"));
        abono.setMedioPago(rs.getString("medio_pago"));
        abono.setCategoriaIngreso(rs.getLong("categoria_ingreso"));
        abono.setPersona(rs.getLong("persona"));
        return abono;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Abono abono) throws SQLException {
        stmt.setInt(1, abono.getTransaccion());
        stmt.setString(2, abono.getMedioPago());
        stmt.setLong(3, abono.getCategoriaIngreso());
        stmt.setLong(4, abono.getPersona());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Abono abono) throws SQLException {
        stmt.setInt(1, abono.getTransaccion());
        stmt.setString(2, abono.getMedioPago());
        stmt.setLong(3, abono.getCategoriaIngreso());
        stmt.setLong(4, abono.getPersona());
        stmt.setLong(5, abono.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Abono abono) throws SQLException {
        stmt.setLong(1, abono.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Optional<Abono> search(Abono use) throws DaoException {
        return Optional.empty();
    }
}
