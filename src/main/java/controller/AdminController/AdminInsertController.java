package controller.AdminController;

import Repository.inventoryRepository;
import app.Navigator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Photo;
import model.carInventory;
import service.CarsService;
import service.UserSession;

import java.io.File;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminInsertController {
    @FXML
    private Button btnImportPhotos;
    @FXML
    private TableColumn<Photo, String> columnPhotoPath;
    @FXML
    private TableView<Photo> tableViewPhotos;
    @FXML
    private TableColumn<carInventory, String> invColCarID, invColCarName, invColCarType, invColStatus;
    @FXML
    private TableColumn<carInventory, Double> invColPrice;
    @FXML
    private TableColumn<carInventory, Integer> invColStock;
    @FXML
    private TableView<carInventory> inventoryTable;
    @FXML
    private TableColumn<carInventory, Timestamp> invColDateAdded;
    @FXML
    private Label lblimagepath, lblStatus;
    @FXML
    private TextField txtCarID, txtCarName, txtCarStock, txtCarPrice;
    @FXML
    private ComboBox<String> comboType, comboStatus;

    private inventoryRepository repo = new inventoryRepository();
    private CarsService carsService;
    private ObservableList<Photo> photos;

    public AdminInsertController() {
        carsService = new CarsService();
        photos = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() throws SQLException {
        setupTableColumns();
        loadTableData();
        setupComboBoxes();
        columnPhotoPath.setCellValueFactory(new PropertyValueFactory<>("path"));
        tableViewPhotos.setItems(photos);
    }

    private void setupTableColumns() {
        invColCarID.setCellValueFactory(new PropertyValueFactory<>("carid"));
        invColCarName.setCellValueFactory(new PropertyValueFactory<>("carname"));
        invColCarType.setCellValueFactory(new PropertyValueFactory<>("cartype"));
        invColStock.setCellValueFactory(new PropertyValueFactory<>("carstock"));
        invColPrice.setCellValueFactory(new PropertyValueFactory<>("carprice"));
        invColStatus.setCellValueFactory(new PropertyValueFactory<>("carstatus"));
        invColDateAdded.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
    }

    private void loadTableData() throws SQLException {
        ObservableList<carInventory> inventoryData = repo.inventoryCarList();
        inventoryTable.setItems(inventoryData);
    }

    private void setupComboBoxes() {
        comboType.getItems().addAll("Sedan", "SUV", "Coupe", "Hatchback");
        comboStatus.getItems().addAll("Available");
    }

    @FXML
    private void handleImportPhotos(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photos");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        Stage stage = (Stage) btnImportPhotos.getScene().getWindow();
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage);

        if (selectedFiles != null) {
            for (File file : selectedFiles) {
                photos.add(new Photo(file.getAbsolutePath()));
            }
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

            if (carID.isEmpty() || carName.isEmpty() || carType == null || carStatus == null) {
                lblStatus.setText("Please fill all fields and select an image.");
                return;
            }

            Timestamp dateAdded = new Timestamp(System.currentTimeMillis());
            carInventory newCar = new carInventory(carID, carName, carType, carStock, carPrice, carStatus, dateAdded, new ArrayList<>());
            repo.addCar(newCar);



            // Save photos after adding the car
            carsService.savePhotos(photos, carID);

            loadTableData();
            clearFields();
            lblStatus.setText("New car and photos added successfully!");
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
        photos.clear();
    }

    @FXML
    void handleDeleteClick(ActionEvent ae) {
        carInventory selectedCar = inventoryTable.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            try {
                carsService.deleteCarAndPhotos(selectedCar.getCarid());
                loadTableData();
                lblStatus.setText("Car and associated photos deleted successfully!");
            } catch (SQLException e) {
                lblStatus.setText("Error deleting car: " + e.getMessage());
            }
        } else {
            lblStatus.setText("Please select a car to delete.");
        }
    }

    @FXML
    void handleLogoutClick(ActionEvent ae) {
        UserSession.clearUserSession();
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @FXML
    void handleClientsClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.ADMIN_CLIENTS_PAGE);
    }

    @FXML
    void handleDashboardClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.ADMIN_DASHBOARD_PAGE);
    }

    @FXML
    public void handleInsertClick(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.ADMIN_INSERT_PAGE);
    }

    @FXML
    public void handleMessageClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.MESSAGE_PAGE);
    }

    @FXML
    public void handleENClick(ActionEvent ae) {
        Locale.setDefault(new Locale("en"));
        Navigator.navigate(ae, Navigator.ADMIN_INSERT_PAGE);
    }
    @FXML
    public void handleSQClick(ActionEvent ae) {
        Locale.setDefault(new Locale("sq","AL"));
        Navigator.navigate(ae, Navigator.ADMIN_INSERT_PAGE);
    }

}
