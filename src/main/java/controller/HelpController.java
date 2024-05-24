package controller;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

    public class HelpController {
        @FXML
        private Pane pane;

        @FXML
        private AnchorPane anchorPane;

        @FXML
        private TextFlow textFlow;

        @FXML
        private Label titleLabel;



        public void handleGoBackClick(ActionEvent ae) {
            Navigator.navigate(ae, Navigator.CARS2_PAGE);
        }
    }

