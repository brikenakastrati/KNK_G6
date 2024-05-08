package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
        UserDto userSignUpData = new UserDto(
                this.txtUserName.getText(),
                this.txtEmail.getText(),
                this.pwdPassword.getText(),
                this.pwdConfirmPassword.getText()
        );

        boolean response = UserService.signUp(userSignUpData);

        if(response){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Register Information");
            alert.setHeaderText("MotorEmpire");
            alert.setContentText("User Created Succesfully!");
            alert.showAndWait();
            Navigator.navigate(ae, Navigator.LOGIN_PAGE);
        }
        else {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Register Information");
            alert.setHeaderText("MotorEmpire");
            alert.setContentText("Error while creating user");
            alert.showAndWait();
        }

    }

    @FXML
    private void handleCancel(ActionEvent ae) throws IOException {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);

    }
}
