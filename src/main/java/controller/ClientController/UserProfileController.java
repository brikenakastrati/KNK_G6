package controller.ClientController;



import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;
import model.dto.ChangeUserPasswordDto;
import service.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    @FXML
    private Label txtName;
    @FXML
    private PasswordField txtCurrentPassword;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private PasswordField txtConfirmNewPassword;
    @FXML
    private Label lblStatus;

    private UserService userService = new UserService();
    private User currentUser;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currentUser = UserService.getCurrentUser();
        if (currentUser != null) {
            txtName.setText(currentUser.getUsername());
        }
    }

    @FXML
    public void saveChanges(ActionEvent event) {
        if (currentUser == null) {
            lblStatus.setText("User not set.");
            return;
        }
        String currentPassword = txtCurrentPassword.getText();
        String newPassword = txtNewPassword.getText();
        String confirmNewPassword = txtConfirmNewPassword.getText();

        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            lblStatus.setText("Please fill all fields.");
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            lblStatus.setText("New passwords do not match.");
            return;
        }

        boolean success = userService.changePassword(currentPassword, newPassword, currentUser.getId());

        if (success) {
            lblStatus.setText("Password changed successfully.");
        } else {
            lblStatus.setText("Current password is incorrect.");
        }
    }

    public void handleLogoutClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CARS2_PAGE);
    }

    public void handleDashboardClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    public void handleCustomizeClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CUSTOMIZE_PAGE);
    }

    public void handleHelpClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HELP_PAGE);
    }

    public void handleProfileClick(MouseEvent me) {

    }

}

