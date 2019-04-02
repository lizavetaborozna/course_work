package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public static final String URL = "jdbc:mysql://localhost:3306/bookshop" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";

    public static final String USERNAME = "root";
    public static final String PASSWORD = "1111";
    private static ConnectionManager instance;

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(
                    ConnectionManager.URL,
                    ConnectionManager.USERNAME,
                    ConnectionManager.PASSWORD
            );
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return null;
    }
}