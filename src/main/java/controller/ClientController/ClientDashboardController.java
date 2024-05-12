package controller.ClientController;

import app.Navigator;
import controller.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ClientDashboardController {
    @FXML
    public Label clientUsername;

    public void initialize() {
        UsernameDisplay();
    }

    private void UsernameDisplay() {

        String user = data.getUsername();
        clientUsername.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }


    public void handleLogoutClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.Cars_page);
    }

    public void handleDashboardClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    public void handleCustomizeClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.Costumize_page);
    }
}
