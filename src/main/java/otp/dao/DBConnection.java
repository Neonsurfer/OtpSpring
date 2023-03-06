package otp.dao;

import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class DBConnection {
    private static Connection connection;

    private static final String DATABASE_URL =  "jdbc:sqlserver://localhost\\SQLEXPRESS;Database=otp;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "secret";

    public DBConnection() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if(connection == null) {
            try {
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            }catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return connection;
    }
}
