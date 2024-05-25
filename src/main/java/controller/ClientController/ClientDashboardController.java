package controller.ClientController;

import Repository.MessageRepository;
import Repository.PurchasesRepository;
import Repository.UserRepository;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.User;
import service.*;

import java.util.Locale;
import java.util.Map;

public class ClientDashboardController {
    @FXML
    public Label clientUsername;
    @FXML
    private PieChart salesPieChart;
    @FXML
    private Label allcarsSold;
    @FXML
    private Label carsRequested;
    UserService userService = new UserService();
    private CarsService carsService = new CarsService();
    private PurchasesService purchasesService = new PurchasesService();
    private RestockRequestService restockRequestService = new RestockRequestService();

    public void initialize() {
        UsernameDisplay();
        loadPieChartData();
        loadTotalCarsSold();
        loadTotalRequestsPerUser();
    }

    public void UsernameDisplay() {
        String user = UserService.getUsername();
        clientUsername.setText(user.substring(0,1).toUpperCase() + user.substring(1));
    }


    private void loadPieChartData() {
        Map<String, Double> monthlySales = purchasesService.getMonthlyCarSales(); // Fetch data from CarsService
        if (monthlySales.isEmpty()) {
            System.out.println("No sales data available.");
            return;
        }
        for (Map.Entry<String, Double> entry : monthlySales.entrySet()) {
            PieChart.Data slice = new PieChart.Data(entry.getKey(), entry.getValue());
            salesPieChart.getData().add(slice);
        }
    }
    private void loadTotalCarsSold(){
        int blerjetTotale = purchasesService.getTotalNumberOfPurchases();
        allcarsSold.setText(String.valueOf(blerjetTotale));
    }
    private void loadTotalRequestsPerUser(){
        String user = UserService.getUsername();
        int kerkesatTotale = restockRequestService.getNumberOfCarRequestsFromUser(user);
        carsRequested.setText(String.valueOf(kerkesatTotale));
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
    @FXML
    public void handleENClick(ActionEvent ae) {
        Locale.setDefault(new Locale("en"));
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    @FXML
    public void handleSQClick(ActionEvent ae) {
        Locale.setDefault(new Locale("sq","AL"));
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }
}
