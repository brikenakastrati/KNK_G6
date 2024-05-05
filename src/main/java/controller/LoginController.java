package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.dto.LoginUserDto;
import service.LoginResult;
import service.UserService;

public class LoginController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;

    @FXML
    private void handleLoginClick(ActionEvent ae) {
        LoginUserDto loginUserData = new LoginUserDto(
                this.txtEmail.getText(),
                this.pwdPassword.getText()
        );

        // Attempt to log in
        LoginResult result = UserService.login(loginUserData);

        // Check login success
        if (!result.isSuccess()) {
            // Login failed; show an error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect email or password.");
            alert.showAndWait();
        } else {
                // Navigate to the home page for regular users
                Navigator.navigate(ae, Navigator.HOME_PAGE);
            }
        }



    @FXML
    private void handleLoginAsAdmin(ActionEvent me) {
        LoginUserDto loginUserData = new LoginUserDto(
                this.txtEmail.getText(),
                this.pwdPassword.getText()
        );

        // Attempt to log in
        LoginResult result = UserService.login(loginUserData);

        // Check login success and admin status
        if (result.isSuccess() && result.isAdmin()) {
            Navigator.navigate(me, Navigator.AdminDashboard_Page);
        } else {
            // Display error or access denied message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            if (!result.isSuccess()) {
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect email or password.");
            } else if (!result.isAdmin()) {
                alert.setTitle("Access Denied");
                alert.setHeaderText(null);
                alert.setContentText("You do not have administrator access.");
            }
            alert.showAndWait();
        }
    }


    @FXML
    private void handleCreateAccountClick(MouseEvent me) {
        Navigator.navigate(me, Navigator.CREATE_ACCOUNT_PAGE);
    }
}




