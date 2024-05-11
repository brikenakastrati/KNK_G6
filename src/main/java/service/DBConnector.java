package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static String URL = "jdbc:mysql://localhost:3306/knk2024";
    private static String USER = "root";
    //Qetu duhet me e bo n mysql krejt passwordin root puna qe mos me na ra me ndrru saher t punon najkush
    private static String PASSWORD = "Agnesa.010704";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        URL, USER, PASSWORD
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}