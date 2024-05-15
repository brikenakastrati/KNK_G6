package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static String URL = "jdbc:mysql://localhost:3306/knk2024";
    private static String USER = "root";
    private static String PASSWORD = "Agnesa.010704"; //qeta duhet me ndrru krejt me bo root
    private static Connection connection = null;

    public static Connection getConnection(){
        if(connection == null){
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
