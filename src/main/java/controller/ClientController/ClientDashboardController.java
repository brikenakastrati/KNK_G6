package controller.ClientController;

import app.Navigator;
import javafx.event.ActionEvent;

public class ClientDashboardController {
    public void handleCustomizeClick(ActionEvent ae) {
    }

    public void handleLogoutClick(ActionEvent ae) {
        Navigator.navigate(ae,Navigator.LOGIN_PAGE);
    }

    public void handleBuynowClick(ActionEvent ae) {
    }

    public void handleDashboardClick(ActionEvent ae) {
    }
}
