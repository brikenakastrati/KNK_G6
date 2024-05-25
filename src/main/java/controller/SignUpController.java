package controller;

import Repository.UserRepository;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.User;
import model.dto.UserDto;
import service.UserService;

import java.io.IOException;
import java.security.SecureRandom;
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
    @FXML
    private  TextField pwdPasswordVisible;
    @FXML
    private  TextField  pwdConfirmPasswordVisible;

    @FXML
    private Button cancel_btn;
    @FXML
    private Button signup_btn;
    @FXML
    public void initialize() {
        // Shtoni dëgjues për shtypjet e tastierës
        txtUserName.setOnKeyPressed(this::handleKeyPressed);
        txtEmail.setOnKeyPressed(this::handleKeyPressed);
        pwdPassword.setOnKeyPressed(this::handleKeyPressed);
        pwdConfirmPassword.setOnKeyPressed(this::handleKeyPressed);
        cancel_btn.setOnKeyPressed(this::handleKeyPressed);
        signup_btn.setOnKeyPressed(this::handleKeyPressed);

    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            Node node = (Node) event.getSource();
            if (node.equals(pwdConfirmPassword)) {
                signup_btn.fire();
            } else {
                KeyEvent newEvent = new KeyEvent(
                        KeyEvent.KEY_PRESSED,
                        "",
                        "",
                        KeyCode.TAB,
                        false,
                        false,
                        false,
                        false
                );
                node.fireEvent(newEvent);
            }
        }
    }
    @FXML
    private void handleHelp(ActionEvent event) {
        try {
            MenuItem menuItem = (MenuItem) event.getSource();
            Scene scene = menuItem.getParentPopup().getOwnerNode().getScene();
            Stage stage = (Stage) scene.getWindow();

            Navigator.navigate(stage, Navigator.HELP1_PAGE);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to open help page.");
        }
    }
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
            return;
        }
        User user2 = UserRepository.getByEmail(this.txtEmail.getText());
        if (user2 != null) {
            showAlert(Alert.AlertType.ERROR, "Registration error", "Email address is already taken try another one");
            return;
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
    private void handleCancel(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);

    }
    @FXML
    private void suggestPassword(ActionEvent event) {
        String suggestedPassword = generateRandomPassword(12); // Specify the length
        pwdPassword.setText(suggestedPassword);
        pwdConfirmPassword.setText(suggestedPassword);
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

    private String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }

    private String generateRandomPassword() {
        return generateRandomPassword(12);
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
@FXML
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
    }

    public void toggleConfirmPasswordVisibility(ActionEvent actionEvent) {
        if (pwdConfirmPassword.isVisible()) {
            pwdConfirmPasswordVisible.setText(pwdConfirmPassword.getText());
            pwdConfirmPasswordVisible.setVisible(true);
            pwdConfirmPassword.setVisible(false);
        } else {
            pwdConfirmPassword.setText(pwdConfirmPasswordVisible.getText());
            pwdConfirmPassword.setVisible(true);
            pwdConfirmPasswordVisible.setVisible(false);
        }
    }
}
