package controller.ClientController;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.dto.ChangeUserPasswordDto;
import service.UserService;

public class UserProfileController {
    @FXML
    private TextField txtName;
    @FXML
    private PasswordField txtCurrentPassword;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private PasswordField txtConfirmNewPassword;
    @FXML
    private Label lblStatus;


    @FXML
    public void saveChanges(ActionEvent event) {
    }
}

