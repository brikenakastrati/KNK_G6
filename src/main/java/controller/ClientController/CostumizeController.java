package controller.ClientController;

import app.Navigator;
import javafx.event.ActionEvent;

public class CostumizeController {
    public void sendMessage(ActionEvent actionEvent) {

    }
    public void handleDashboardClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }


    public void handleLogoutClick(ActionEvent ae) {
        Navigator.navigate(ae,Navigator.LOGIN_PAGE);
    }

    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.Cars_page);
    }

    public void handleCustomizeClick(ActionEvent actionEvent) {
    }
}

