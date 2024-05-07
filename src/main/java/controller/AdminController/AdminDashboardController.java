package controller.AdminController;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AdminDashboardController {
    @FXML
    private void handleClientsClick(){

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
   private void handleDashboardClick(){

    }
}
