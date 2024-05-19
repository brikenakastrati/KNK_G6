package controller.ClientController;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.carInventory;
import service.BuyNowService;

import java.net.URL;
import java.util.ResourceBundle;

public class BuynowController implements Initializable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label stockLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carInventory car = BuyNowService.getInstance().getSelectedCar();
        nameLabel.setText(car.getCarname());
        priceLabel.setText(String.format("%.2f", car.getCarprice()));
        stockLabel.setText(String.valueOf(car.getCarstock()));
    }




    public void handleDashboardClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }
    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CARS2_PAGE);
    }
    public void handleHelpClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HELP_PAGE);
    }
    public void handleLogoutClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }
    public void handleCustomizeClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.MESSAGE_PAGE);
    }
}
