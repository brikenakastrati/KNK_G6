package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageSaver {

    private static final String URL = "jdbc:mysql://localhost:3306/knk2024";
    private static final String USER = "root";
    private static final String PASSWORD = "afrimymeri12";

    public static void saveMessage(String firstName, String lastName, String message) {
        String insertSQL = "INSERT INTO messages (first_name, last_name, message) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, message);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
