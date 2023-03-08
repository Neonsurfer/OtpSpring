package Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {
    private static final String DATABASE_URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;Database=otp;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "secret";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
    }

    public static List<Map<String, Object>> getRecordSetFromDb(String SQL) {
        List<Map<String, Object>> recordSet = new ArrayList<>();
        try {
            Connection connection = getConnection();
            ResultSet rs = connection.createStatement().executeQuery(SQL);

            while (rs.next()) {
                Map<String, Object> record = new LinkedHashMap<>();
                ResultSetMetaData metaData = rs.getMetaData();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    record.put(metaData.getColumnLabel(i), rs.getObject(i));
                }
                recordSet.add(record);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recordSet;
    }
}
