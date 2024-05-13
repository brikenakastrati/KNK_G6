package controller.AdminController;

import app.Navigator;
import controller.data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class AdminDashboardController {
    @FXML
    public Label admUsername;
    public void initialize() {
       UsernameDisplay();
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
