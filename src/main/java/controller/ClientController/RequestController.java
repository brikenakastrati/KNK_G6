package controller.ClientController;

import app.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.RestockRequest;
import model.User;
import service.RestockRequestService;
import service.UserService;
import service.UserSession;

import java.util.Locale;

public class RequestController {

    UserService userService = new UserService();
    RestockRequestService restockRequestService = new RestockRequestService();


    @FXML
    private TextField txtCarName;
    @FXML
    private TextField txtCarType;
    @FXML
    private TextArea txtCarDescription;


    @FXML
    private TableView<RestockRequest> stockRequestTableView;
    @FXML
    private TableColumn<RestockRequest, String> stockRequestUserColumn;
    @FXML
    private TableColumn<RestockRequest, String> stockRequestCarNameColumn;
    @FXML
    private TableColumn<RestockRequest, String> stockRequestCarTypeColumn;
    @FXML
    private TableColumn<RestockRequest, String> stockRequestDateSentColumn;
    @FXML
    private TableColumn<RestockRequest, String> stockRequestCarDescriptionColumn;
    @FXML
    public void initialize() {
        String currentUser = UserService.getUsername();
        stockRequestUserColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        stockRequestCarNameColumn.setCellValueFactory(new PropertyValueFactory<>("carName"));
        stockRequestCarTypeColumn.setCellValueFactory(new PropertyValueFactory<>("carType"));
        stockRequestDateSentColumn.setCellValueFactory(new PropertyValueFactory<>("carDescription"));
        stockRequestCarDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("requestDate"));
        stockRequestTableView.setItems(FXCollections.observableArrayList(restockRequestService.getRestockRequestsForUser(currentUser)));


    }

//    private void loadUserRequests() {
//        String currentUser = UserService.getUsername();
//        ObservableList<RestockRequest> userRequests = FXCollections.observableArrayList(
//                restockRequestService.getRestockRequestsForUser(currentUser)
//        );
//        stockRequestTableView.setItems(userRequests);
//    }
    @FXML
    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CARS2_PAGE);
    }

    @FXML
    public void handleRequestCarClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.REQUEST_CAR_PAGE);
    }

    @FXML
    public void handleCustomizeClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CUSTOMIZE_PAGE);
    }

    @FXML
    public void handleLogoutClick(ActionEvent ae) {
        UserSession.clearUserSession();
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }
    @FXML
    public void handleClientDashboardClick(MouseEvent me) {
        Navigator.navigate(me, Navigator.HOME_PAGE);
    }

    @FXML
    public void handleProfileClick(MouseEvent me) {
        try {
            User currentUser = userService.getUserByUsername(UserService.getUsername());
            UserService.setCurrentUser(currentUser);
            Navigator.navigate(me, Navigator.CLIENT_PROFILE_PAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleENClick(ActionEvent ae) {
        Locale.setDefault(new Locale("en"));
        Navigator.navigate(ae, Navigator.REQUEST_CAR_PAGE);
    }

    @FXML
    public void handleSQClick(ActionEvent ae) {
        Locale.setDefault(new Locale("sq","AL"));
        Navigator.navigate(ae, Navigator.REQUEST_CAR_PAGE);
    }

    @FXML
    public void handleHelpClick(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.HELP_PAGE);
    }

    @FXML
    public void handleSubmitRequest(ActionEvent actionEvent) {

        String carName = txtCarName.getText();
        String carType = txtCarType.getText();
        String carDescription = txtCarDescription.getText();
        String currentUser = UserService.getUsername();

        if ( carName.isEmpty() || carType.isEmpty() || carDescription.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please fill in all fields");
            return;
        }

        try {
            restockRequestService.requestRestockWithDescription(currentUser, carName, carType, carDescription);
            showAlert(Alert.AlertType.INFORMATION, "Request Submitted", "Your car suggestion has been submitted!");
            clearFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Submission Error", "An error occurred while submitting your request. Please try again.");
            e.printStackTrace();
        }
    }


    private void clearFields() {

        txtCarName.clear();
        txtCarType.clear();
        txtCarDescription.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
