package controller.ClientController;
import app.Navigator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.sql.SQLException;


public class CarsController {
//    @FXML private ComboBox<String> comboType1;

//    public void initialize() throws SQLException {
//
//        setupComboBoxes1();
//    }
//    private void setupComboBoxes1() {
//        comboType1.getItems().addAll("Sedan", "SUV", "Coupe", "Hatchback");
//    }

    public void handleDashboardClick(ActionEvent ae) {
    Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    public void handleCustomizeClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CUSTOMIZE_PAGE);
    }
    public void handleLogoutClick(ActionEvent ae) {
        Navigator.navigate(ae,Navigator.LOGIN_PAGE);
    }

    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CARS_PAGE);
    }

    public void handleMessageClick(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.MESSAGE_PAGE);
    }
    public void handleHelpClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HELP_PAGE);
    }
}
