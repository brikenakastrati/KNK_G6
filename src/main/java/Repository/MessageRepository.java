package model;

import service.DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageRepository {


    public static void saveMessage(String firstName, String lastName, String message) {
        String insertSQL = "INSERT INTO messages (first_name, last_name, message) VALUES (?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, message);

            pstmt.executeUpdate();
        }    catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
