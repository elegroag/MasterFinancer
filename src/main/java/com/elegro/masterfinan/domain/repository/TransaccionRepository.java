package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.entity.cruds.TransaccionDaoRepository;
import com.elegro.masterfinan.infraestructura.dao.MysqlConnector;
import com.elegro.masterfinan.infraestructura.entity.Transaccion;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaccionRepository implements TransaccionDaoRepository {

    private static final String SQl_SELECT = "SELECT id, fecha, valor, estado, tipo_transaccion, fecha, hora, usuario  FROM transacciones WHERE 1;";

    private static final String SQl_INSERT = "INSERT INTO transacciones (id, fecha, valor, estado, tipo_transaccion, fecha, hora, usuario)VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQl_UPDATE = "UPDATE transacciones SET fecha=?, valor=?, estado=?, tipo_transaccion=?, fecha=?, hora=?, usuario=?  WHERE id=?";

    private static final String SQl_DELETE = "DELETE FROM transacciones WHERE id=?";
    protected Connection connectionTransactional;

    public TransaccionRepository(){
    }

    public TransaccionRepository(Connection conn){
        this.connectionTransactional = conn;
    }

    @Override
    public List<Transaccion> findAll() throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Transaccion> lista = new ArrayList<>();

        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(SQl_SELECT);
            rs = stmt.executeQuery();
            try {
                while (rs.next()) {
                    lista.add(recordModel(rs));
                }
            } catch (SQLException ex) {
                throw new DaoException("Error de sql", ex);
            } finally {
                if (this.connectionTransactional == null) {
                    MysqlConnector.close(stmt);
                    MysqlConnector.close(conn);
                    MysqlConnector.close(rs);
                }
            }
        } catch (SQLException er) {
            MysqlConnector.exep(er);
        }
        return lista;
    }

    @Override
    public Transaccion findById(Long id) throws DaoException {
        return null;
    }

    @Override
    public Transaccion findOne(Long id) throws DaoException {
        return null;
    }


    @Override
    public Transaccion findFirst() throws DaoException {
        return null;
    }

    @Override
    public Transaccion findLast() throws DaoException {
        return null;
    }

    @Override
    public void insert(Transaccion use) throws DaoException {

    }

    @Override
    public void update(Transaccion use) throws DaoException {

    }

    @Override
    public void delete(Transaccion use) throws DaoException {
    }

    @Override
    public void search(Transaccion use) throws DaoException {

    }

    private Transaccion recordModel(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        //LocalDate fecha =  rs.getDate("fecha");
        Long usuario = rs.getLong("usuario");
        double valor = rs.getDouble("valor");
        String estado = rs.getString("estado");
        //LocalTime hora = rs.getString("hora");
        String tipoTransaccion = rs.getString("tipo_transaccion");

        Transaccion tra = new Transaccion();
        tra.setId(id);
        //tra.setFecha(fecha);
        tra.setUsuario(usuario);
        tra.setValor(valor);
        tra.setEstado(estado);
        //tra.setHora();
        tra.setTipoTransaccion(tipoTransaccion);
        tra.setEntityUsuario(null);
        tra.setEntityUsuario(null);
        return tra;
    }
}
