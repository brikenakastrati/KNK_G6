package controller.ClientController;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.MessageSaver;

public class CostumizeController {

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextArea txtMessage;

    @FXML
    private void sendMessage() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String message = txtMessage.getText();

        MessageSaver.saveMessage(firstName, lastName, message);

        // Optional: Clear the text fields after saving the message
        txtFirstName.clear();
        txtLastName.clear();
        txtMessage.clear();
    }

    public void handleDashboardClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    public void handleLogoutClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CARS_PAGE);
    }

    public void handleCustomizeClick(ActionEvent actionEvent) {
    }
    public void handleHelpClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HELP_PAGE);
    }
}
