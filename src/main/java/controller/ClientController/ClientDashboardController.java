package controller.ClientController;

import Repository.PurchasesRepository;
import Repository.UserRepository;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.User;
import service.UserService;
import service.UserSession;

import java.util.Map;

public class ClientDashboardController {
    @FXML
    public Label clientUsername;
    @FXML
    public PieChart salesPieChart;
    PurchasesRepository purchasesRepository = PurchasesRepository.getInstance();

    UserService userService = new UserService();

    public void initialize() {
        UsernameDisplay();
        loadPieChartData();
    }

    public void UsernameDisplay() {
        String user = UserService.getUsername();
        clientUsername.setText(user.substring(0,1).toUpperCase() + user.substring(1));
    }


    private void loadPieChartData() {
        Map<String, Double> monthlySales = purchasesRepository.getMonthlyCarSales();
        for (Map.Entry<String, Double> entry : monthlySales.entrySet()) {
            PieChart.Data slice = new PieChart.Data(entry.getKey(), entry.getValue());
            salesPieChart.getData().add(slice);
        }
    }

    public void handleLogoutClick(ActionEvent ae) {
        UserSession.clearUserSession();
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CARS2_PAGE);
    }

    public void handleClientDashboardClick(MouseEvent me) {
        Navigator.navigate(me, Navigator.HOME_PAGE);
    }

    public void handleCustomizeClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CUSTOMIZE_PAGE);
    }
    public void handleRequestCarClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.REQUEST_CAR_PAGE);
    }

    public void handleViewDetails(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.REQUEST_CAR_PAGE);
    }
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
