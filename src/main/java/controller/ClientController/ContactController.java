package controller.ClientController;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import Repository.MessageRepository;
import javafx.scene.input.MouseEvent;
import model.User;

import service.UserService;
import service.UserSession;

public class ContactController {

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextArea txtMessage;
    @FXML
    private Button sendButton;

    private UserService userService = new UserService();


    @FXML
    private void sendMessage() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String message = txtMessage.getText();

        MessageRepository.saveMessage(firstName, lastName, message);

        // Clear the text fields after saving the message
        txtFirstName.clear();
        txtLastName.clear();
        txtMessage.clear();
    }

    @FXML
    private void handleEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (txtFirstName.isFocused()) {  // Nese jeni te fokusuar tek emri
                txtLastName.requestFocus();  // Vendos fokusin tek fusha e mbiemrit
            } else if (txtLastName.isFocused()) {  // Nese jeni te fokusuar tek mbiemri
                txtMessage.requestFocus();  // Vendos fokusin tek fusha e mesazhit
            } else {  // Nese jeni te fokusuar tek mesazhi
                sendMessage();  // Dergo mesazhin
                event.consume();  // Konsumo eventin (taste te shtypur)
            }
        }
    }




    @FXML
    public void handleCustomizeClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CUSTOMIZE_PAGE);
    }

    @FXML
    public void handleLogoutClick(ActionEvent ae) {
        UserSession.clearUserSession();
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @FXML
    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CARS2_PAGE);
    }

    @FXML
    public void handleRequestCarClick(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.REQUEST_CAR_PAGE);
    }

    @FXML
    public void handleHelpClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HELP_PAGE);
    }


    public void handleProfileClick(MouseEvent me) {
        try {
            User currentUser = userService.getUserByUsername(UserService.getUsername());
            UserService.setCurrentUser(currentUser);
            Navigator.navigate(me, Navigator.CLIENT_PROFILE_PAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
