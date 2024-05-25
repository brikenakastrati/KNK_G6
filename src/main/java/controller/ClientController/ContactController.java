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

import service.RestockRequestService;
import service.UserService;
import service.UserSession;

import java.util.Locale;

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
    private RestockRequestService requestService=new RestockRequestService();


    @FXML
    private void sendMessage() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String message = txtMessage.getText();

        requestService.saveMessage(firstName, lastName, message);

        txtFirstName.clear();
        txtLastName.clear();
        txtMessage.clear();
    }

    @FXML
    private void handleEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (txtFirstName.isFocused()) {
                txtLastName.requestFocus();
            } else if (txtLastName.isFocused()) {
                txtMessage.requestFocus();
            } else {
                sendMessage();
                event.consume();
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

    @FXML
    public void handleClientDashboardClick(MouseEvent me) {
        Navigator.navigate(me, Navigator.HOME_PAGE);
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
    @FXML
    public void handleENClick(ActionEvent ae) {
        Locale.setDefault(new Locale("en"));
        Navigator.navigate(ae, Navigator.CUSTOMIZE_PAGE);
    }

    @FXML
    public void handleSQClick(ActionEvent ae) {
        Locale.setDefault(new Locale("sq","AL"));
        Navigator.navigate(ae, Navigator.CUSTOMIZE_PAGE);
    }
}
