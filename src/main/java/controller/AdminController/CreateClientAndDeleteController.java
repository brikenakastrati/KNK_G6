package controller.AdminController;

import Repository.UserRepository;
import app.Navigator;
import controller.SignUpController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.User;
import model.dto.UserDto;
import service.UserService;

public class CreateClientAndDeleteController {
    @FXML
    private TextField txtusername, txtemail;
    @FXML
    private PasswordField txtpassword, txtconfirmpassword;
    @FXML
    private Button deleteBtn, createclientbutton;
    @FXML
    private TableView usertable;

    @FXML
    private void handleCreateClient(ActionEvent ae){


        if (txtusername.getText().isEmpty() || txtemail.getText().isEmpty() || txtpassword.getText().isEmpty() ||
                txtconfirmpassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Please fill in all the fields");
            return;
        }

        if (!txtemail.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Please enter a valid email address");
            return;
        }
        if (!txtpassword.getText().equals(txtconfirmpassword.getText())) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Passwords do not match");
            return;
        }
        if (txtpassword.getText().length() < 8) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Password must be at least 8 characters long");
            return;
        }
        User user = UserRepository.getByUsername(this.txtusername.getText());
        if (user != null) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Username is already taken try another one");
        }
        User user2 = UserRepository.getByEmail(this.txtemail.getText());
        if (user2 != null) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Email address is already taken try another one");
        }


        UserDto userSignUpData = new UserDto(
                this.txtusername.getText(),
                this.txtemail.getText(),
                this.txtpassword.getText(),
                this.txtconfirmpassword.getText()
        );

        boolean response = UserService.createclientbutton(userSignUpData);

        if(response){
            showAlert(Alert.AlertType.INFORMATION, "New client created", "New client created successfully");
            Navigator.navigate(ae, Navigator.ADMIN_CLIENTS_PAGE);
        }
        else {
            showAlert(Alert.AlertType.ERROR, "Creating client error", "Error while creating new client");
        }

    }


    @FXML
    private void handleClientsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADMIN_CLIENTS_PAGE);
    }
    @FXML
    private void handleLogoutClick(ActionEvent ae){
        Navigator.navigate(ae,Navigator.LOGIN_PAGE);
    }
    @FXML
    private void handleInsertClick(ActionEvent ae){
        Navigator.navigate(ae,Navigator.ADMIN_INSERT_PAGE);
    }
    @FXML
    private void handleDashboardClick(ActionEvent ae){
        Navigator.navigate(ae, Navigator.ADMIN_INSERT_PAGE);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
