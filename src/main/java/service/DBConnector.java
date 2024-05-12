package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/knk2024";
    private static final String USER = "root";
    private static final String PASSWORD = "2302";
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            // Check if connection is null or closed, and (re)establish it if necessary
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            // Consider logging this exception more gracefully or rethrowing a custom exception
            throw new RuntimeException("Failed to connect to the database", e);
        }
        return connection;
    }
}
