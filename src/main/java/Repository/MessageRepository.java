package Repository;

import service.DBConnector;
import model.Message;
import model.RestockRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository {

    public static void saveMessage(String firstName, String lastName, String message) {
        String insertSQL = "INSERT INTO messages (first_name, last_name, message) VALUES (?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, message);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveRestockRequest(String user, String carName, String carType) {
        String insertRequestSQL = "INSERT INTO restock_requests (user, car_name, car_type, request_date) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRequestSQL)) {

            preparedStatement.setString(1, user);
            preparedStatement.setString(2, carName);
            preparedStatement.setString(3, carType);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error saving restock request: " + e.getMessage());
        }
    }

    public void saveRestockRequestWithDescription(String user, String carName, String carType, String carDescription) {
        String insertRequestSQL = "INSERT INTO restock_requests (user, car_name, car_type, car_description, request_date) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertRequestSQL)) {

            preparedStatement.setString(1, user);
            preparedStatement.setString(2, carName);
            preparedStatement.setString(3, carType);
            preparedStatement.setString(4, carDescription);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error saving restock request: " + e.getMessage());
        }
    }

    public List<Message> getMessages(String firstNameFilter, String lastNameFilter) {
        List<Message> messages = new ArrayList<>();

        try (Connection connection = DBConnector.getConnection()) {
            StringBuilder queryBuilder = new StringBuilder("SELECT id, first_name, last_name, message FROM messages WHERE 1=1");

            if (firstNameFilter != null && !firstNameFilter.isEmpty()) {
                queryBuilder.append(" AND first_name LIKE '%").append(firstNameFilter).append("%'");
            }
            if (lastNameFilter != null && !lastNameFilter.isEmpty()) {
                queryBuilder.append(" AND last_name LIKE '%").append(lastNameFilter).append("%'");
            }

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryBuilder.toString());

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String message = resultSet.getString("message");
                messages.add(new Message(id, firstName, lastName, message));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public List<RestockRequest> getRestockRequests() {
        List<RestockRequest> requests = new ArrayList<>();

        try (Connection connection = DBConnector.getConnection()) {
            String query = "SELECT id, user, car_name, car_type, car_description, request_date FROM restock_requests";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String user = resultSet.getString("user");
                String carName = resultSet.getString("car_name");
                String carType = resultSet.getString("car_type");
                String carDescription = resultSet.getString("car_description");
                Timestamp requestDate = resultSet.getTimestamp("request_date");

                requests.add(new RestockRequest(id, user, carName, carType, carDescription, requestDate.toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requests;
    }
}
