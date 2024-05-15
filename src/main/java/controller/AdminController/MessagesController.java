package controller.AdminController;

import app.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Message;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MessagesController {

    @FXML
    private TableView<Message> tableView;

    @FXML
    private TableColumn<Message, Integer> idColumn;

    @FXML
    private TableColumn<Message, String> firstNameColumn;

    @FXML
    private TableColumn<Message, String> lastNameColumn;

    @FXML
    private TableColumn<Message, String> messageColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        tableView.setItems(getMessages());
    }

    private ObservableList<Message> getMessages() {
        ObservableList<Message> messages = FXCollections.observableArrayList();

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/knk2024";
        String user = "root";
        String password = "2302";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, first_name, last_name, message FROM messages";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String message = resultSet.getString("message");
                messages.add(new Message(id, first_name, last_name, message));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }

    public void handleDashboardClick(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent,Navigator.ADMIN_DASHBOARD_PAGE);
    }

    public void handleInsertClick(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent,Navigator.ADMIN_INSERT_PAGE);
    }

    public void handleClientsClick(ActionEvent actionEvent) {

    }

    public void handleMessageClick(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent,Navigator.MESSAGE_PAGE);
    }

    public void handleLogoutClick(ActionEvent actionEvent) {
    }
}