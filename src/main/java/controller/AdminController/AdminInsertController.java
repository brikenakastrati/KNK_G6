package controller.AdminController;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
        private TableColumn<?, ?> invColCarID;

        @FXML
        private TableColumn<?, ?> invColCarName;

        @FXML
        private TableColumn<?, ?> invColCarType;

        @FXML
        private TableColumn<?, ?> invColPrice;

        @FXML
        private TableColumn<?, ?> invColStatus;

        @FXML
        private TableColumn<?, ?> invColStock;

        @FXML
        private TableView<?> inventoryTable;

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
        void handleInsertClick(ActionEvent event) {

        }

        @FXML
        void handleLogoutClick(ActionEvent me) {
                Navigator.navigate(me,Navigator.LOGIN_PAGE);
        }

    }


