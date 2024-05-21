package controller.ClientController;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.MessageSaver;

public class CostumizeController {

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextArea txtMessage;
    @FXML
    private Button sendButton;

    @FXML
    private void sendMessage() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String message = txtMessage.getText();

        MessageSaver.saveMessage(firstName, lastName, message);

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
    public void handleDashboardClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    @FXML
    public void handleLogoutClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @FXML
    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CARS2_PAGE);
    }

    @FXML
    public void handleCustomizeClick(ActionEvent actionEvent) {
        // Add your customization logic here
    }

    @FXML
    public void handleHelpClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HELP_PAGE);
    }

    @FXML
    public void handleProfileClick(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.CLIENT_PROFILE_PAGE);
    }
}
