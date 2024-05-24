package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;
import java.util.ResourceBundle;

import model.User;
import model.dto.LoginUserDto;
import javafx.scene.Node;
import service.LoginResult;
import service.UserService;

import java.util.Locale;

public class LoginController {
    @FXML
    public Label admUsername; //Prej fxml t admindashboard vyn per me shfaq username
    @FXML
    public Label clientUsername;
    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private TextField pwdPasswordVisible;


    @FXML
    public void initialize() {
        // Add a key event handler to switch focus on Tab key press
        txtUserName.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.TAB) {
                pwdPassword.requestFocus();
                event.consume(); // Prevent the default Tab behavior
            }
        });

        pwdPassword.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleLoginClick(new ActionEvent(txtUserName, null)); // Provide correct source
                event.consume(); // Prevent the default Enter behavior
            }
        });
    }



    @FXML
    private void handleLoginClick(ActionEvent ae) {
        LoginUserDto loginUserData = new LoginUserDto(
                this.txtUserName.getText(),
                this.pwdPassword.getText()
        );

        // Attempt to log in
        LoginResult result = UserService.login(loginUserData);

        // Check login success
        if (!result.isSuccess()) {
            //To show an error when there is one
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect username or password.");
            alert.showAndWait();
        } else {
            // Check if the user is an admin
            if (result.isAdmin()) {
                // Navigate to the admin dashboard
                UserService.setUsername(this.txtUserName.getText());
                Navigator.navigate(ae, Navigator.ADMIN_DASHBOARD_PAGE);
            } else {
                // Navigate to the home page for regular users
                UserService.setUsername(this.txtUserName.getText());
                Navigator.navigate(ae, Navigator.CARS2_PAGE);
            }

        }
    }


//    @FXML
//    private void handleLoginAsAdmin(ActionEvent me) {
//        LoginUserDto loginUserData = new LoginUserDto(
//                this.txtUserName.getText(),
//                this.pwdPassword.getText()
//        );
//
//        // Attempt to log in
//        LoginResult result = UserService.login(loginUserData);
//
//
//        // Check login success and admin status
//        if (result.isSuccess() && result.isAdmin()) {
//
//            UserService.setUsername(this.txtUserName.getText());
//            Navigator.navigate(me, Navigator.ADMIN_DASHBOARD_PAGE);
//
//
//
//        } else {
//
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            if (!result.isSuccess()) {
//                alert.setTitle("Login Error");
//                alert.setHeaderText(null);
//                alert.setContentText("Incorrect email or password.");
//            } else if (!result.isAdmin()) {
//                alert.setTitle("Access Denied");
//                alert.setHeaderText(null);
//                alert.setContentText("You do not have administrator access.");
//            }
//            alert.showAndWait();
//        }
//
//    }


    @FXML
    private void handleCreateAccountClick(MouseEvent me) {
        Navigator.navigate(me, Navigator.CREATE_ACCOUNT_PAGE);
    }
    @FXML
    private void handleENClick(ActionEvent ae) {
        Locale.setDefault(new Locale("en"));
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @FXML
    private void handleSQClick(ActionEvent ae) {
        Locale.setDefault(new Locale("sq", "AL"));
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }


    public void togglePasswordVisibility(ActionEvent actionEvent) {
        if (pwdPassword.isVisible()) {
            pwdPasswordVisible.setText(pwdPassword.getText());
            pwdPasswordVisible.setVisible(true);
            pwdPassword.setVisible(false);
        } else {
            pwdPassword.setText(pwdPasswordVisible.getText());
            pwdPassword.setVisible(true);
            pwdPasswordVisible.setVisible(false);
        }
    }}




