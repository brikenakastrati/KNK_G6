package controller.AdminController;

import app.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import model.Message;
import service.DBConnector;
import service.UserSession;

import java.sql.Connection;
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
    private TextField firstNameFilterField;

    @FXML
    private TextField lastNameFilterField;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        tableView.setItems(getMessages(null, null));
    }

    private ObservableList<Message> getMessages(String firstNameFilter, String lastNameFilter) {
        ObservableList<Message> messages = FXCollections.observableArrayList();

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
    @FXML
    private void handleResetClick(ActionEvent ae) {
        firstNameFilterField.clear();
        lastNameFilterField.clear();
        tableView.setItems(getMessages(null, null));
    }

    @FXML
    private void handleClientsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADMIN_CLIENTS_PAGE);
    }

    @FXML
    private void handleLogoutClick(ActionEvent ae) {
        UserSession.clearUserSession();
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @FXML
    private void handleInsertClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADMIN_INSERT_PAGE);
    }

    @FXML
    private void handleDashboardClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADMIN_DASHBOARD_PAGE);
    }

    @FXML
    private void handleMessageClick(ActionEvent ae) {
        tableView.setItems(getMessages(firstNameFilterField.getText(), lastNameFilterField.getText()));
    }
}
