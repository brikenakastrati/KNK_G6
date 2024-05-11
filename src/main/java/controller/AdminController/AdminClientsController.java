package controller.AdminController;
import app.Navigator;
import controller.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
public class AdminClientsController {
    @FXML
    private ComboBox comboType1;
    @FXML
    public Label admUsername;
    public void initialize() {
        UsernameDisplay();
    }

    private void UsernameDisplay() {

        String user = data.getUsername();
        admUsername.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }


//    private void setupComboBoxes1() {
//        comboType1.getItems().addAll("Sedan", "SUV", "Coupe", "Hatchback");
//    }

    @FXML
    private void handleLogoutClick(ActionEvent ae){
        Navigator.navigate(ae,Navigator.LOGIN_PAGE);
    }
    @FXML
    private void handleInsertClick(ActionEvent ae){
        Navigator.navigate(ae,Navigator.AdminInsert_page);
    }
    @FXML
    private void handleDashboardClick(ActionEvent ae){
        Navigator.navigate(ae,Navigator.AdminDashboard_Page);
    }


}
