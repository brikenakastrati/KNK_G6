package controller.AdminController;

import Repository.inventoryRepository;
import app.Navigator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import service.CarsService;
import service.Interface.UserServiceInterface;
import service.Interface.inventoryServiceInterface;
import service.UserService;
import service.UserSession;

import java.sql.SQLException;
import java.util.Map;

public class AdminDashboardController {
    @FXML
    private Label admUsername, carsInStock, lblClientNumber;
    @FXML
    private LineChart<String, Number> incomeGraph,customersGraph;
    @FXML
    private CategoryAxis xAxis,xAxisCustomers;
    @FXML
    private NumberAxis yAxis,yAxisCustomers;

    private UserServiceInterface userService;
    private inventoryServiceInterface carsService;

    public AdminDashboardController() {
        userService = new UserService();
        carsService = new CarsService();
    }

    private inventoryRepository inventoryRepo = new inventoryRepository();

    public void initialize() {
        UsernameDisplay();
        updateClientNumber();
        updateCarsInStock();
        try {
            setupIncomeGraph();
            setupCustomersGraph();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupIncomeGraph() throws SQLException {
        xAxis.setLabel("Month");
        yAxis.setLabel("Income");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Monthly Income");

        Map<String, Double> monthlyIncome = carsService.getMonthlyIncome();
        for (Map.Entry<String, Double> entry : monthlyIncome.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        incomeGraph.getData().add(series);
    }
    private void setupCustomersGraph() throws SQLException {
        xAxisCustomers.setLabel("Month");
        yAxisCustomers.setLabel("Customers");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Customers Registered");

        Map<String, Integer> monthlyRegistrations = userService.getMonthlyRegistrations();
        for (Map.Entry<String, Integer> entry : monthlyRegistrations.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        customersGraph.getData().add(series);
    }
    private void updateCarsInStock() {
        try {
            int totalCarsInStock = inventoryRepo.countCarsInStock();
            carsInStock.setText(String.valueOf(totalCarsInStock));
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    private void updateClientNumber() {
        try {
            int numberOfClients = userService.countUsers();
            lblClientNumber.setText(String.valueOf(numberOfClients));
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private void UsernameDisplay() {
        String user = UserService.getUsername();
        admUsername.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }

    @FXML
    private void handleClientsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADMIN_CLIENTS_PAGE);
    }

    @FXML
    private void handleLogoutClick(ActionEvent ae) {
        UserSession.clearUserSession();
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @FXML
    private void handleInsertClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADMIN_INSERT_PAGE);
    }

    @FXML
    private void handleDashboardClick(ActionEvent ae) {
        // This method seems redundant if it just navigates to the same page
    }

    @FXML
    private void handleMessageClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.MESSAGE_PAGE);
    }
}
