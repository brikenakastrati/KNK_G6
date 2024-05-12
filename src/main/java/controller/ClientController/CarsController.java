package controller.ClientController;
import app.Navigator;
import controller.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;



public class CarsController {

    public void handleDashboardClick(ActionEvent ae) {
    Navigator.navigate(ae, Navigator.AdminDashboard_Page);
    }

    public void handleCustomizeClick() {

    }
    public void handleLogoutClick(ActionEvent ae) {
        Navigator.navigate(ae,Navigator.LOGIN_PAGE);
    }

    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.Cars_page);
    }
}
