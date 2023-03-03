package otp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    Connection connection;

    private static final String DATABASE_URL =  "jdbc:sqlserver://localhost\\SQLEXPRESS;Database=otp;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "secret";

    public DBConnection() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
