package controller.AdminController;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CreateClientAndDeleteController {
    @FXML
    private TextField txtusername, txtemail;
    @FXML
    private PasswordField txtpassword, txtconfirmpassword;
    @FXML
    private TableView usertable;

    @FXML
    private void handleClientsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.AdminClients_page);
    }
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
        Navigator.navigate(ae, Navigator.AdminDashboard_Page);
    }
}
