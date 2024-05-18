package controller.ClientController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.dto.ChangeUserPasswordDto;

public class ChangePasswordController {

    @FXML
    private PasswordField currentPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmNewPasswordField;

    // Assuming you have a method to get the logged-in user ID and a service to handle password changes
    private int loggedInUserId = getLoggedInUserId();

    @FXML
    private void handleChangePasswordAction() {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmNewPassword = confirmNewPasswordField.getText();

        if (!newPassword.equals(confirmNewPassword)) {
            showAlert("Error", "New Password and Confirm New Password do not match.");
            return;
        }

        ChangeUserPasswordDto dto = new ChangeUserPasswordDto(loggedInUserId, currentPassword, newPassword, confirmNewPassword);
        boolean success = changeUserPassword(dto);

        if (success) {
            showAlert("Success", "Password changed successfully.");
            Stage stage = (Stage) currentPasswordField.getScene().getWindow();
            stage.close();
        } else {
            showAlert("Error", "Failed to change password. Please try again.");
        }
    }

    private int getLoggedInUserId() {
        // Implement logic to get logged-in user ID
        return 1; // Placeholder
    }

    private boolean changeUserPassword(ChangeUserPasswordDto dto) {
        // Implement the service logic to change the user's password
        return true; // Placeholder
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

