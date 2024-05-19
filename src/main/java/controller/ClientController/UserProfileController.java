package controller.ClientController;



import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.UserService;

public class UserProfileController {
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField txtCurrentPassword;
    @FXML
    private PasswordField txtNewPassword;

    @FXML
    public void saveChanges() {
        String name = txtName.getText();
        String currentPassword = txtCurrentPassword.getText();
        String newPassword = txtNewPassword.getText();

        // Implementoni kodin për të ruajtur ndryshimet
        // Për shembull, mund të thirrni një metodë në një shërbim të ndërfaqes së përdoruesit për të kryer veprimet e nevojshme.
        UserService userService = new UserService(); // Përveçojini në shërbimin tuaj të ndërfaqes së përdoruesit

    }
}

