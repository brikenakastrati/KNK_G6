package controller.AdminController;

import app.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Message;
import model.RestockRequest;
import service.RestockRequestService;
import service.UserSession;

public class MessagesController {

    @FXML
    private TableView<Message> messageTableView;

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
    private TableView<RestockRequest> stockRequestTableView;

    @FXML
    private TableColumn<RestockRequest, Integer> stockRequestIdColumn;

    @FXML
    private TableColumn<RestockRequest, String> stockRequestUserColumn;

    @FXML
    private TableColumn<RestockRequest, String> stockRequestCarNameColumn;

    @FXML
    private TableColumn<RestockRequest, String> stockRequestDateSentColumn;

    private final RestockRequestService restockRequestService = new RestockRequestService();

    @FXML
    public void initialize() {
        // Initialize message table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        messageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
        messageTableView.setItems(FXCollections.observableArrayList(restockRequestService.getMessages(null, null)));

        // Initialize restock request table columns
        stockRequestIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        stockRequestUserColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        stockRequestCarNameColumn.setCellValueFactory(new PropertyValueFactory<>("carName"));
        stockRequestDateSentColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        stockRequestTableView.setItems(FXCollections.observableArrayList(restockRequestService.getRestockRequests()));
    }

    @FXML
    private void handleResetClick(ActionEvent ae) {
        firstNameFilterField.clear();
        lastNameFilterField.clear();
        messageTableView.setItems(FXCollections.observableArrayList(restockRequestService.getMessages(null, null)));
        stockRequestTableView.setItems(FXCollections.observableArrayList(restockRequestService.getRestockRequests()));
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
        messageTableView.setItems(FXCollections.observableArrayList(
                restockRequestService.getMessages(firstNameFilterField.getText(), lastNameFilterField.getText())
        ));
        stockRequestTableView.setItems(FXCollections.observableArrayList(restockRequestService.getRestockRequests()));
    }
}
