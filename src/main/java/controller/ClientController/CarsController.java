package controller.ClientController;
import app.Navigator;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.carInventory;
import service.CarsService;
import service.Interface.inventoryServiceInterface;

import java.net.URL;
import java.sql.SQLException;
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
    }


    public void handleDashboardClick(ActionEvent ae) {
    Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    public void handleCustomizeClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CUSTOMIZE_PAGE);
    }
    public void handleLogoutClick(ActionEvent ae) {
        Navigator.navigate(ae,Navigator.LOGIN_PAGE);
    }

    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CARS_PAGE);
    }

    public void handleMessageClick(ActionEvent actionEvent) {
        Navigator.navigate(actionEvent, Navigator.MESSAGE_PAGE);
    }
    public void handleHelpClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HELP_PAGE);
    }
}
