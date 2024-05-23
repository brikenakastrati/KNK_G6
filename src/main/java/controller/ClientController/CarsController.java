package controller.ClientController;
import app.Navigator;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.User;
import model.carInventory;
import service.CarsService;
import service.Interface.inventoryServiceInterface;
import service.BuyNowService;
import service.UserService;
import service.UserSession;
import service.RestockRequestService;
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

    private UserService userService = new UserService();
    private inventoryServiceInterface inventoryService;
    private ObservableList<carInventory> carlist;
    private RestockRequestService restockRequestService = new RestockRequestService();


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
    @FXML
    private Button buynowbutton;
    @FXML
    private VBox imageContainer;
    @FXML
    private Button reqforcarbtn;
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

    @FXML
    private Pagination imagePagination;


    private Node createImageView(String imageUrl) {
        if (!imageUrl.startsWith("http://") && !imageUrl.startsWith("https://") && !imageUrl.startsWith("file:/")) {
            imageUrl = "file:///" + imageUrl.replace("\\", "/");
        }

        ImageView imageView = new ImageView();
        try {
            imageView.setImage(new Image(imageUrl));
        } catch (Exception e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
        imageView.setFitHeight(showCarPhoto.getFitHeight()); // me ndrru
        imageView.setFitWidth(showCarPhoto.getFitWidth()); // me ndrru edhe kto
        //imageView.setPreserveRatio(true);
        imageView.setLayoutX(showCarPhoto.getLayoutX());
        imageView.setLayoutY(showCarPhoto.getLayoutY());
        imageView.setX(showCarPhoto.getX());
        imageView.setY(showCarPhoto.getY());
        imageView.setPreserveRatio(showCarPhoto.isPreserveRatio());
        return imageView;
    }

    private void updateCarDetails(carInventory car) {
        carNameLabel.setText(car.getCarname());
        carTypeLabel.setText(car.getCartype());
        carPriceLabel.setText(String.format("%.2f", car.getCarprice()));
        stockLabel.setText(String.valueOf(car.getCarstock()));
        carStatusLabel.setText(car.getCarstatus());

        BuyNowService.getInstance().setSelectedCar(car);

        List<String> imageUrls = car.getCarImages();
        if (imageUrls != null && !imageUrls.isEmpty()) {
            imagePagination.setPageCount(imageUrls.size());
            imagePagination.setPageFactory(pageIndex -> createImageView(imageUrls.get(pageIndex)));
        } else {
            imagePagination.setPageCount(1);
            imagePagination.setPageFactory(pageIndex -> new ImageView());
        }

        // Enable or disable the request for car button based on stock status
        if (car.getCarstock() == 0) {
            reqforcarbtn.setDisable(false);
        } else {
            reqforcarbtn.setDisable(true);
        }
    }
    public void handleBuynow(ActionEvent ae) {
        carInventory selectedCar = BuyNowService.getInstance().getSelectedCar();
        if (selectedCar == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Missing");
            alert.setHeaderText(null);
            alert.setContentText("Please select a car first.");
            alert.showAndWait();
            return;
        }

        String status = selectedCar.getCarstatus();
        if ("Sold".equals(status) || "Out of Stock".equals(status)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Unavailable Car");
            alert.setHeaderText(null);
            alert.setContentText("This car is " + status + " and cannot be purchased.");
            alert.showAndWait();
            return;
        }

        Navigator.navigate(ae, Navigator.BUY_NOW_PAGE);
    }


    public void handleRequestForCar(ActionEvent ae) {
        carInventory selectedCar = BuyNowService.getInstance().getSelectedCar();
        if (selectedCar == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Missing");
            alert.setHeaderText(null);
            alert.setContentText("Please select a car first.");
            alert.showAndWait();
            return;
        }
        String user = UserService.getUsername();
        String carName = selectedCar.getCarname();

        restockRequestService.requestRestock(user, carName);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Request Sent");
        alert.setHeaderText(null);
        alert.setContentText("A request has been sent to the admin to restock the car: " + selectedCar.getCarname());
        alert.showAndWait();
    }

    public void handleDashboardClick(ActionEvent ae) {
    Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    public void handleCustomizeClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.CUSTOMIZE_PAGE);
    }
    public void handleLogoutClick(ActionEvent event) {
        UserSession.clearUserSession();
        Navigator.navigate(event,Navigator.LOGIN_PAGE);
    }

    public void handleCarsClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.CARS2_PAGE);
    }

    public void handleMessageClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.MESSAGE_PAGE);
    }
    public void handleHelpClick(ActionEvent event) {
        Navigator.navigate(event, Navigator.HELP_PAGE);
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

    public void handleRequestCarClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.REQUEST_CAR_PAGE);
    }

}
