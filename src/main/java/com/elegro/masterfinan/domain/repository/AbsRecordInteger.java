package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.dao.MysqlConnector;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbsRecordInteger<T> {

    protected Connection connectionTransactional;
    protected String table;
    protected String primaryKey;
    protected String fields;
    protected String[] fillable;
    protected Map<String, String> query = new HashMap<>();

    public T findById(Integer id) throws DaoException {
        fields = String.join(",", fillable);
        return find("SELECT " + fields + " FROM " + table + " WHERE " + primaryKey + "=?", id);
    }

    public T findOne(Integer id) throws DaoException {
        fields = String.join(",", fillable);
        return find("SELECT " + fields + " FROM " + table + " WHERE " + primaryKey + "=? LIMIT 1", id);
    }

    public T findFirst() throws DaoException {
        fields = String.join(",", fillable);
        return find("SELECT " + fields + " FROM " + table + " WHERE 1=1 ORDER BY " + primaryKey + " ASC LIMIT 1", null);
    }

    public T findLast() throws DaoException {
        fields = String.join(",", fillable);
        return find("SELECT " + fields + " FROM " + table + " WHERE 1=1 ORDER BY " + primaryKey + " DESC LIMIT 1", null);
    }

    public List<T> findAll() throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<T> lista = new ArrayList<>();
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(this.query.get("SQl_SELECT"));
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


    public List<T> findSql(String sql) throws DaoException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<T> lista = new ArrayList<>();
        try {
            conn = this.connectionTransactional;
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(recordModel(rs));
            }
        } catch (SQLException ex) {
            throw new DaoException("Error de sql", ex);
        }
        return lista;
    }

    public T find(String sql, Integer id) throws DaoException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        T use = null;
        try {
            conn = this.connectionTransactional != null ? this.connectionTransactional : MysqlConnector.getConnection();
            stmt = conn.prepareStatement(sql);
            if(id != null){
                stmt.setInt(1, id);
            }
            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    use = recordModel(rs);
                }
            } catch (SQLException ex) {
                throw new DaoException("Error de sql", ex);
            } finally {
                if (this.connectionTransactional == null) {
                    MysqlConnector.close(stmt);
                    MysqlConnector.close(conn);
                    if(rs != null){
                        MysqlConnector.close(rs);
                    }
                }
            }
        } catch (SQLException er) {
            MysqlConnector.exep(er);
        }
        return use;
    }

    public abstract T recordModel(ResultSet rs) throws SQLException;
}
