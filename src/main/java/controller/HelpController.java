package controller;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

    public class HelpController {
        @FXML
        private ScrollPane scrollPane;

        @FXML
        private AnchorPane anchorPane;

        @FXML
        private TextFlow textFlow;

        @FXML
        private Label titleLabel;

        // You can add more fields and methods as needed



        public void handleGoBackClick(ActionEvent ae) {
            Navigator.navigate(ae, Navigator.CARS2_PAGE);
        }
    }

