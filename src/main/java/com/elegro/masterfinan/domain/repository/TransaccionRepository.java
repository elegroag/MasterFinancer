package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.cruds.TransaccionDaoRepository;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TransaccionRepository extends AbsRecordInteger<Transaccion> implements TransaccionDaoRepository {

    private static final String SQl_SELECT = "SELECT id, fecha, valor, estado, tipo_transaccion, fecha, hora, usuario  FROM transacciones WHERE 1;";

    private static final String SQl_INSERT = "INSERT INTO transacciones (id, fecha, valor, estado, tipo_transaccion, fecha, hora, usuario)VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQl_UPDATE = "UPDATE transacciones SET fecha=?, valor=?, estado=?, tipo_transaccion=?, fecha=?, hora=?, usuario=?  WHERE id=?";

    private static final String SQl_DELETE = "DELETE FROM transacciones WHERE id=?";

    public TransaccionRepository(Connection conn){
        this.connectionTransactional = conn;
        this.table = "transacciones";
        this.primaryKey = "id";
        this.fillable = new String[] { "id", "fecha", "valor", "estado", "tipo_transaccion", "fecha", "hora", "usuario"};
        this.query.put("SQl_SELECT", SQl_SELECT);
        this.query.put("SQl_INSERT", SQl_INSERT);
        this.query.put("SQl_UPDATE", SQl_UPDATE);
        this.query.put("SQl_DELETE", SQl_DELETE);
    }

    @Override
    public Transaccion insert(Transaccion use) throws DaoException {
        return use;
    }

    @Override
    public boolean update(Transaccion use) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Transaccion use) throws DaoException {
        return false;
    }

    @Override
    public Transaccion search(Transaccion use) throws DaoException {
        return use;
    }

    public Transaccion recordModel(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
        Long usuario = rs.getLong("usuario");
        Double valor = rs.getDouble("valor");
        String estado = rs.getString("estado");
        LocalTime hora = LocalTime.parse(rs.getString("hora"));
        String tipoTransaccion = rs.getString("tipo_transaccion");

        Transaccion tra = new Transaccion();
        tra.setId(id);
        tra.setFecha(fecha);
        tra.setUsuario(usuario);
        tra.setValor(valor);
        tra.setEstado(estado);
        tra.setHora(hora);
        tra.setTipoTransaccion(tipoTransaccion);
        tra.setEntityUsuario(null);
        tra.setEntityUsuario(null);
        return tra;
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
