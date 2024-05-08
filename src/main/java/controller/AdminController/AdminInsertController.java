package controller.AdminController;

import Repository.inventoryRepository;
import app.Navigator;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.carInventory;

public class AdminInsertController {
        @FXML
        private Button btnClients;

        @FXML
        private Button btnDashboard;

        @FXML
        private Button btnInsert;

        @FXML
        private Button btnLogout;

        @FXML
        private TableColumn<carInventory, String> invColCarID;
        @FXML
        private TableColumn<carInventory, String> invColCarName;
        @FXML
        private TableColumn<carInventory, String> invColCarType;
        @FXML
        private TableColumn<carInventory, Double> invColPrice;
        @FXML
        private TableColumn<carInventory, String> invColStatus;
        @FXML
        private TableColumn<carInventory, Integer> invColStock;
        @FXML
        private TableView<carInventory> inventoryTable;

        @FXML
        private TextField txtCarID;

        @FXML
        private TextField txtCarName;

        @FXML
        void handleClientsClick(ActionEvent event) {

        }

        @FXML
        void handleDashboardClick(ActionEvent event) {

        }

       @FXML

       void handleInsertClick(ActionEvent ae) {}


        @FXML
        void handleLogoutClick(ActionEvent ae) {
                Navigator.navigate(ae,Navigator.LOGIN_PAGE);
        }



        private inventoryRepository repo = new inventoryRepository();

        public void initialize() {
                setupTableColumns();
                loadTableData();
        }

        private void setupTableColumns() {
                invColCarID.setCellValueFactory(new PropertyValueFactory<>("carid"));
                invColCarName.setCellValueFactory(new PropertyValueFactory<>("carname"));
                invColCarType.setCellValueFactory(new PropertyValueFactory<>("cartype"));
            invColStock.setCellValueFactory(new PropertyValueFactory<>("carstock"));
                invColPrice.setCellValueFactory(new PropertyValueFactory<>("carprice"));
                invColStatus.setCellValueFactory(new PropertyValueFactory<>("carstatus"));

        }

        private void loadTableData() {
                ObservableList<carInventory> inventoryData = repo.inventoryCarList();
                inventoryTable.setItems(inventoryData);
        }

        public void handleDeleteClick(ActionEvent ae) {
        }

        public void handleAddClick(ActionEvent ae) {
        }
}



