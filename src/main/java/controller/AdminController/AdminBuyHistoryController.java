package controller.AdminController;

import Repository.PurchasesRepository;
import Repository.inventoryRepository;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Purchase;
import model.carInventory;
import service.UserSession;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminBuyHistoryController implements Initializable {
    @FXML
    public TableView<Purchase> buytable;
    @FXML
    public TableColumn<Purchase, String> buytblcarname;
    @FXML
    public TableColumn<Purchase, Double> buytablecarprice;
    @FXML
    public TableColumn<Purchase, String> buytablebuyname;
    @FXML
    public TableColumn<Purchase, String> buytablepurchasedate;
    @FXML
    public TableColumn<carInventory, Integer> buytableleftinstock;

    PurchasesRepository purchasesRepository = PurchasesRepository.getInstance();
    inventoryRepository invrepo = new inventoryRepository();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buytblcarname.setCellValueFactory(new PropertyValueFactory<>("carName"));
        buytablecarprice.setCellValueFactory(new PropertyValueFactory<>("carPrice"));
        buytablebuyname.setCellValueFactory(new PropertyValueFactory<>("buyerName"));
        buytablepurchasedate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        buytableleftinstock.setCellValueFactory(new PropertyValueFactory<>("carStock"));
        purchaseHistory();
    }

    private void purchaseHistory(){
        List<Purchase> purchases = purchasesRepository.getAllPurchases();
        buytable.getItems().setAll(purchases);
    }



    @FXML
    public void handleDashboardClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADMIN_DASHBOARD_PAGE);
    }
    @FXML
    public void handleInsertClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADMIN_INSERT_PAGE);
    }
    @FXML
    public void handleClientsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.ADMIN_CLIENTS_PAGE);
    }
    @FXML
    public void handleLogoutClick(ActionEvent ae) {
        UserSession.clearUserSession();
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }
    @FXML
    public void handleMessageClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }
}
