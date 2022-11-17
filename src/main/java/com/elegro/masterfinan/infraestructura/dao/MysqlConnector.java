package com.elegro.masterfinan.infraestructura.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MysqlConnector {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/masterfinancer?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "8912";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static Connection getConnection(boolean transactional) throws SQLException {
        Connection dr = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        if (transactional){
            if(dr.getAutoCommit()){
                dr.setAutoCommit(false);
            }
        }
        return dr;
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(PreparedStatement stmt) throws SQLException {
        stmt.close();
    }

    public static void close(Connection con) throws SQLException {
        con.close();
    }

    public static void exep(SQLException ex) {
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
    }

}
