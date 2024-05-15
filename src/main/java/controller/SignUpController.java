package controller;

import Repository.UserRepository;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;
import model.dto.UserDto;
import service.UserService;

import java.io.IOException;
import java.util.Objects;

public class SignUpController {
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pwdPassword;
    @FXML
    private PasswordField pwdConfirmPassword;
    private Stage stage;
    private Scene scene;
    @FXML
    private void handleSignUp(ActionEvent ae){

        if (txtUserName.getText().isEmpty() || txtEmail.getText().isEmpty() || pwdPassword.getText().isEmpty() ||
                pwdConfirmPassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Registration error", "Please fill in all the fields");
            return;
        }

        if (!txtEmail.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            showAlert(Alert.AlertType.ERROR, "Registration error", "Please enter a valid email address");
            return;
        }
        if (!pwdPassword.getText().equals(pwdConfirmPassword.getText())) {
            showAlert(Alert.AlertType.ERROR, "Registration error", "Passwords do not match");
            return;
        }
        if (pwdPassword.getText().length() < 8) {
            showAlert(Alert.AlertType.ERROR, "Registration error", "Password must be at least 8 characters long");
            return;
        }
        User user = UserRepository.getByUsername(this.txtUserName.getText());
        if (user != null) {
            showAlert(Alert.AlertType.ERROR, "Registration error", "Username is already taken try another one");
        }
        User user2 = UserRepository.getByEmail(this.txtEmail.getText());
        if (user2 != null) {
            showAlert(Alert.AlertType.ERROR, "Registration error", "Email address is already taken try another one");
        }

        UserDto userSignUpData = new UserDto(
                this.txtUserName.getText(),
                this.txtEmail.getText(),
                this.pwdPassword.getText(),
                this.pwdConfirmPassword.getText()
        );

        boolean response = UserService.signUp(userSignUpData);

        if(response){
            showAlert(Alert.AlertType.INFORMATION, "Registered Successfully", "User was created successfully");
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
        }
        else {
            showAlert(Alert.AlertType.ERROR, "Register Error", "Error while creating user");
        }

    }
    @FXML
    private void handleCancel(ActionEvent ae) throws IOException {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);

    }
    @FXML
    private void handleLoginAccountClick(MouseEvent me) {
        Navigator.navigate(me, Navigator.LOGIN_PAGE);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void handleMessageClick(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.MESSAGE_PAGE);
    }
}
