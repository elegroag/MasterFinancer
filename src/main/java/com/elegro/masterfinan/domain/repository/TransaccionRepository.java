package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.TransaccionDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class TransaccionRepository extends AbsRecordInteger<Transaccion> implements TransaccionDaoRepository {

    private static final String SQL_SELECT = "SELECT id, fecha, valor, estado, tipo_transaccion, hora, usuario  FROM transacciones WHERE 1;";

    private static final String SQL_INSERT = "INSERT INTO transacciones (fecha, valor, estado, tipo_transaccion, hora, usuario)VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE transacciones SET fecha=?, valor=?, estado=?, tipo_transaccion=?, hora=?, usuario=?  WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM transacciones WHERE id=?";

    public TransaccionRepository(Connection conn){
        this.connectionTransactional = conn;
        this.table = "transacciones";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "fecha", "valor", "estado", "tipo_transaccion", "hora", "usuario"};
        this.query.put("SQL_SELECT", SQL_SELECT);
        this.query.put("SQL_INSERT", SQL_INSERT);
        this.query.put("SQL_UPDATE", SQL_UPDATE);
        this.query.put("SQL_DELETE", SQL_DELETE);
    }
    
    @Override
    public Optional<Transaccion> search(Transaccion use) throws DaoException {
        return Optional.empty();
    }

    public Transaccion recordModel(ResultSet rs) throws SQLException, DaoException {
        Integer id = rs.getInt("id");
        LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
        Long usuario = rs.getLong("usuario");
        Double valor = rs.getDouble("valor");
        String estado = rs.getString("estado");
        LocalTime hora = LocalTime.parse(rs.getString("hora"));
        String tipoTransaccion = rs.getString("tipo_transaccion");
        Models models = new Models();
        Transaccion tra = new Transaccion();
        tra.setId(id);
        tra.setFecha(fecha);
        tra.setUsuario(usuario);
        tra.setValor(valor);
        tra.setEstado(estado);
        tra.setHora(hora);
        tra.setTipoTransaccion(tipoTransaccion);
        tra.setEntityUsuario(models.entityUsuario().findById(usuario));
        return tra;
    }

    @Override
    public Integer prepareModel(PreparedStatement stmt, Transaccion use) throws SQLException {
        stmt.setObject(1, use.getFecha());
        stmt.setDouble(2, use.getValor());
        stmt.setString(3, use.getEstado());
        stmt.setString(4, use.getTipoTransaccion());
        stmt.setObject(5, use.getHora());
        stmt.setLong(6, use.getUsuario());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareUpdate(PreparedStatement stmt, Transaccion use) throws SQLException {
        stmt.setObject(1, use.getFecha());
        stmt.setDouble(2, use.getValor());
        stmt.setString(3, use.getEstado());
        stmt.setString(4, use.getTipoTransaccion());
        stmt.setObject(5, use.getHora());
        stmt.setLong(6, use.getUsuario());
        stmt.setInt(7, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public Integer prepareDelete(PreparedStatement stmt, Transaccion use) throws SQLException {
        stmt.setInt(1, use.getId());
        return stmt.executeUpdate();
    }

    @Override
    public List<Transaccion> findByIngresoCategoria(Long idCategoria) throws DaoException {
        String sql = "SELECT tr.id, tr.fecha, tr.valor, tr.estado, tr.tipo_transaccion, tr.fecha, tr.hora, tr.usuario " +
        " FROM transacciones as tr " +
        " INNER JOIN abonos ON abonos.transaccion=tr.id " +
        " WHERE abonos.ingreso=?";
        return this.findSql(sql);
    }

    
}
