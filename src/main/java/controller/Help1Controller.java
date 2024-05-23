package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

public class Help1Controller {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextFlow textFlow;

    @FXML
    private Label titleLabel;

    @FXML
    private Button gobackBtn;

    @FXML
    public void handleGoBackClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CREATE_ACCOUNT_PAGE);
    }
}