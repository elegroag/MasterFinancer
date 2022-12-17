package com.elegro.masterfinan.domain.repository;

import com.elegro.masterfinan.infraestructura.dao.MysqlConnector;
import com.elegro.masterfinan.infraestructura.excepetion.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbsRecordLong<T> {

    protected Connection connectionTransactional;
    protected String table;
    protected String primaryKey;
    protected Long insertId;
    protected String fields;
    protected String[] fillable;
    protected Map<String, String> query = new HashMap<>();

    public abstract T recordModel(ResultSet rs) throws SQLException;

    public abstract Integer prepareModel(PreparedStatement stmt, T use) throws SQLException ;

    public abstract Integer prepareUpdate(PreparedStatement stmt, T use) throws SQLException;

    public abstract Integer prepareDelete(PreparedStatement stmt, T use) throws  SQLException;

    public Long getInsertId() {
        return insertId;
    }
    public T findById(Long id) throws DaoException {
        fields = String.join(",", fillable);
        return find("SELECT " + fields + " FROM " + table + " WHERE " + primaryKey + "=?", id);
    }

    public T findOne(Long id) throws DaoException {
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
        List<T> lista = new ArrayList<>();
        try {
            Connection conn = this.connectionTransactional;
            PreparedStatement stmt = conn.prepareStatement(this.query.get("SQL_SELECT"));
            ResultSet rs = stmt.executeQuery();
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

    public T find(String sql, Long id) throws DaoException{
        T use = null;
        try {
            Connection conn = this.connectionTransactional;
            PreparedStatement stmt = conn.prepareStatement(sql);
            if(id != null){
                stmt.setLong(1, id);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                use = recordModel(rs);
            }
        } catch (SQLException er) {
            MysqlConnector.exep(er);
            throw new DaoException("Error SQl", er);
        }
        return use;
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

    public T insert(T use) throws DaoException {
        try {
            Connection conn = this.connectionTransactional;
            PreparedStatement stmt = conn.prepareStatement(query.get("SQL_INSERT"), Statement.RETURN_GENERATED_KEYS);
            int affectedRows = prepareModel(stmt, use);
            if (affectedRows != 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.insertId = generatedKeys.getLong(1);
                    }
                }
            }
        } catch (SQLException er) {
            MysqlConnector.exep(er);
        }
        return use;
    }

    public boolean update(T use) throws DaoException {
        int affectedRows = 0;
        try {
            Connection conn = this.connectionTransactional;
            PreparedStatement stmt = conn.prepareStatement(this.query.get("SQL_UPDATE"));
            affectedRows = prepareUpdate(stmt, use);
        } catch (SQLException ex) {
            MysqlConnector.exep(ex);
        }
        return affectedRows > 0;
    }

    public boolean delete(T use) throws DaoException {
        int affectedRows = 0;
        try {
            Connection conn = this.connectionTransactional;
            PreparedStatement stmt = conn.prepareStatement(this.query.get("SQL_DELETE"));
            affectedRows = prepareDelete(stmt, use);
        } catch (SQLException ex) {
            MysqlConnector.exep(ex);
        }
        return affectedRows > 0;
    }

}
