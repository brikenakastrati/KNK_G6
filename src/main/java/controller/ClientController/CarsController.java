package controller.ClientController;
import app.Navigator;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.User;
import model.carInventory;
import service.CarsService;
import service.Interface.inventoryServiceInterface;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class CarsController implements Initializable {
//    @FXML private ComboBox<String> comboType1;

//    public void initialize() throws SQLException {
//
//        setupComboBoxes1();
//    }
//    private void setupComboBoxes1() {
//        comboType1.getItems().addAll("Sedan", "SUV", "Coupe", "Hatchback");
//    }

    //test
    private inventoryServiceInterface inventoryService;
    private ObservableList<carInventory> carlist;

    @FXML
    private Label carNameLabel, carTypeLabel,carPriceLabel,stockLabel,carStatusLabel;
    @FXML
    private TableView<carInventory> cartable;
    @FXML
    private TableColumn<carInventory, String> showCarsTable;
    @FXML
    private ImageView showCarPhoto;
    @FXML
    private TextArea descriptionText;
    public void handleChooseCar(ActionEvent ae) {

    }

    @Override
    public void initialize(URL url , ResourceBundle resourceBundle) {
        this.inventoryService = new CarsService();
        this.showCarsTable.setCellValueFactory(new PropertyValueFactory<carInventory, String>("carname"));
        try{
            this.inventoryService.fillCarsTable(this.cartable);
            this.carlist = this.cartable.getItems();
        }catch (SQLException se) {
            System.out.println("ERROR : " + se.getMessage());
        }
        cartable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                updateCarDetails(newSelection);
            }
        });

    }
    private List<String> carImages;

    public List<String> getCarImages() {
        return carImages;
    }

    public void setCarImages(List<String> carImages) {
        this.carImages = carImages;
    }
    @FXML
    private Pagination imagePagination;
    private void updateCarDetails(carInventory car) {
        carNameLabel.setText(car.getCarname());
        carTypeLabel.setText(car.getCartype());
        carPriceLabel.setText(String.format("%.2f", car.getCarprice()));
        stockLabel.setText(String.valueOf(car.getCarstock()));
        carStatusLabel.setText(car.getCarstatus());

//        String imagePath = car.getCarimage();
//        if (!imagePath.startsWith("http://") && !imagePath.startsWith("https://") && !imagePath.startsWith("file:///")) {
//            imagePath = "file:///" + imagePath.replace("\\", "/"); // Replace backslashes for compatibility
//        }
//        try {
//            Image image = new Image(imagePath);
//            showCarPhoto.setImage(image);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Failed to load image: " + e.getMessage());
//        }

    }


    public void handleDashboardClick(ActionEvent ae) {
    Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    public void handleCustomizeClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.CUSTOMIZE_PAGE);
    }
    public void handleLogoutClick(ActionEvent event) {
        Navigator.navigate(event,Navigator.LOGIN_PAGE);
    }

    public void handleCarsClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.CARS_PAGE);
    }

    public void handleMessageClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.MESSAGE_PAGE);
    }
    public void handleHelpClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.HELP_PAGE);
    }
}
