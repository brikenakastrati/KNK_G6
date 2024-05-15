package controller.AdminController;

import Repository.inventoryRepository;
import app.Navigator;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.carInventory;

import java.io.File;
import java.sql.SQLException;

public class AdminInsertController {
    @FXML private Button btnClients, btnDashboard, btnInsert, btnLogout;
    @FXML private TableColumn<carInventory, String> invColCarID, invColCarName, invColCarType, invColStatus;
    @FXML private TableColumn<carInventory, Double> invColPrice;
    @FXML private TableColumn<carInventory, Integer> invColStock;
    @FXML private TableView<carInventory> inventoryTable;
    @FXML private Label lblimagepath, lblStatus;
    @FXML private TextField txtCarID, txtCarName, txtCarStock, txtCarPrice;
    @FXML private ComboBox<String> comboType, comboStatus;
    @FXML private ImageView imageview;

    private inventoryRepository repo = new inventoryRepository();

    public void initialize() throws SQLException {
        setupTableColumns();
        loadTableData();
        setupComboBoxes();
    }

    private void setupTableColumns() {
        invColCarID.setCellValueFactory(new PropertyValueFactory<>("carid"));
        invColCarName.setCellValueFactory(new PropertyValueFactory<>("carname"));
        invColCarType.setCellValueFactory(new PropertyValueFactory<>("cartype"));
        invColStock.setCellValueFactory(new PropertyValueFactory<>("carstock"));
        invColPrice.setCellValueFactory(new PropertyValueFactory<>("carprice"));
        invColStatus.setCellValueFactory(new PropertyValueFactory<>("carstatus"));
    }

    private void loadTableData() throws SQLException {
        ObservableList<carInventory> inventoryData = repo.inventoryCarList();
        inventoryTable.setItems(inventoryData);
    }

    private void setupComboBoxes() {
        comboType.getItems().addAll("Sedan", "SUV", "Coupe", "Hatchback");
        comboStatus.getItems().addAll("Available", "Sold", "Out of Stock");
    }

    @FXML
    private void handleImportImageClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageview.setImage(image);
            lblimagepath.setText(file.getAbsolutePath());
        } else {
            lblimagepath.setText("No image selected");
        }
    }

    @FXML
    public void handleAddClick(ActionEvent ae) {
        try {
            String carID = txtCarID.getText();
            String carName = txtCarName.getText();
            String carType = comboType.getValue();
            int carStock = Integer.parseInt(txtCarStock.getText().trim());
            double carPrice = Double.parseDouble(txtCarPrice.getText().trim());
            String carStatus = comboStatus.getValue();
            String carImage = lblimagepath.getText();

            if (carID.isEmpty() || carName.isEmpty() || carType == null || carStatus == null || carImage.isEmpty()) {
                lblStatus.setText("Please fill all fields and select an image.");
                return;
            }
            imageview.setImage(null);
            carInventory newCar = new carInventory(carID, carName, carType, carStock, carPrice, carStatus, carImage);
            repo.addCar(newCar);
            loadTableData();
            clearFields();
            lblStatus.setText("New car added successfully!");
        } catch (NumberFormatException e) {
            lblStatus.setText("Invalid number format in stock or price.");
        } catch (Exception e) {
            lblStatus.setText("Error adding car: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtCarID.clear();
        txtCarName.clear();
        comboType.getSelectionModel().clearSelection();
        txtCarStock.clear();
        txtCarPrice.clear();
        comboStatus.getSelectionModel().clearSelection();
        lblimagepath.setText("No image selected");
        lblStatus.setText("");
    }

    @FXML
    void handleLogoutClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @FXML
    void handleDeleteClick(ActionEvent ae) {
        // Delete logic to be implemented
    }

    @FXML
    void handleClientsClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.ADMIN_CLIENTS_PAGE);
    }

    @FXML
    void handleDashboardClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.ADMIN_DASHBOARD_PAGE);

    }

    public void handleInsertClick(ActionEvent actionEvent) {
        // Handle insert click if needed
    }
}
