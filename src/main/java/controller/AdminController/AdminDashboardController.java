package controller.AdminController;

import Repository.inventoryRepository;
import app.Navigator;
import controller.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.carInventory;
import service.Interface.UserServiceInterface;
import service.UserService;


public class AdminDashboardController {
    @FXML
    public Label admUsername, carsInStock;
    @FXML
    private Label lblClientNumber;
    private UserServiceInterface userService;
    public AdminDashboardController(){
        userService = new UserService();
    }
    private inventoryRepository inventoryRepo = new inventoryRepository();
    public void initialize() {
        UsernameDisplay();
        updateClientNumber();
        updateCarsInStock();
    }

    private void updateCarsInStock(){
        try{
            int totalCarsinStock = inventoryRepo.countCarsInStock();
            carsInStock.setText(String.valueOf(totalCarsinStock));
        }catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }
    private void updateClientNumber(){
        try {
            int numberOfClients = userService.countUsers();
            lblClientNumber.setText(String.valueOf(numberOfClients));
        }catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
        private void UsernameDisplay() {

                String user = data.getUsername();
                admUsername.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
            }

    @FXML
    private void handleClientsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADMIN_CLIENTS_PAGE);
    }
    @FXML
    private void handleLogoutClick(ActionEvent ae){
        Navigator.navigate(ae,Navigator.LOGIN_PAGE);
    }
    @FXML
    private void handleInsertClick(ActionEvent ae){
        Navigator.navigate(ae,Navigator.ADMIN_INSERT_PAGE);

    }
    @FXML
   private void handleDashboardClick(){

    }
}
