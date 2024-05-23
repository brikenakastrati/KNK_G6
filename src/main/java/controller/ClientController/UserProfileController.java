package controller.ClientController;

import Repository.PurchasesRepository;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Purchase;
import model.User;
import model.filter.highPriceFilter;
import service.UserService;
import service.UserSession;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    @FXML
    private Label txtName;
    @FXML
    private PasswordField txtCurrentPassword;
    @FXML
    private PasswordField txtNewPassword;
    @FXML
    private PasswordField txtConfirmNewPassword;
    @FXML
    private Label lblStatus;
    @FXML
    private TableView<Purchase> buytable;
    @FXML
    private TableColumn<Purchase, String> carname;
    @FXML
    private TableColumn<Purchase, Double> carprice;
    @FXML
    private TableColumn<Purchase, String> buyername;
    @FXML
    private TableColumn<Purchase, String> purchasedate;
    @FXML
    private TextField txtMinPrice, txtMaxPrice;
    @FXML
    private DatePicker dpFromDate, dpToDate;
    @FXML
    private TextField txtCurrentPasswordVisible;
    @FXML
    private TextField txtNewPasswordVisible;
    @FXML
    private TextField txtConfirmNewPasswordVisible;

    private UserService userService = new UserService();
    private User currentUser;
    private PurchasesRepository purchasesRepository = PurchasesRepository.getInstance();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currentUser = UserService.getCurrentUser();
        if (currentUser != null) {
            txtName.setText(currentUser.getUsername().substring(0, 1).toUpperCase() + currentUser.getUsername().substring(1));

        }

        carname.setCellValueFactory(new PropertyValueFactory<>("carName"));
        carprice.setCellValueFactory(new PropertyValueFactory<>("carPrice"));
        buyername.setCellValueFactory(new PropertyValueFactory<>("buyerName"));
        purchasedate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));

        purchaseHistory();

        // Set event handler for key pressed
        txtCurrentPassword.setOnKeyPressed(this::handleKeyPressed);
        txtNewPassword.setOnKeyPressed(this::handleKeyPressed);
        txtConfirmNewPassword.setOnKeyPressed(this::handleKeyPressed);
    }

    @FXML
    public void saveChanges(ActionEvent event) {
        if (currentUser == null) {
            lblStatus.setText("User not set.");
            return;
        }
        String currentPassword = txtCurrentPassword.getText();
        String newPassword = txtNewPassword.getText();
        String confirmNewPassword = txtConfirmNewPassword.getText();

        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            lblStatus.setText("Please fill all fields.");
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            lblStatus.setText("New passwords do not match.");
            return;
        }

        boolean success = userService.changePassword(currentPassword, newPassword, currentUser.getId());

        if (success) {
            lblStatus.setText("Password changed successfully.");
        } else {
            lblStatus.setText("Current password is incorrect.");
        }
    }

    @FXML
    public void toggleCurrentPasswordVisibility(ActionEvent event) {
        if (txtCurrentPassword.isVisible()) {
            txtCurrentPasswordVisible.setText(txtCurrentPassword.getText());
            txtCurrentPasswordVisible.setVisible(true);
            txtCurrentPassword.setVisible(false);
        } else {
            txtCurrentPassword.setText(txtCurrentPasswordVisible.getText());
            txtCurrentPassword.setVisible(true);
            txtCurrentPasswordVisible.setVisible(false);
        }
    }

    @FXML
    public void toggleNewPasswordVisibility(ActionEvent event) {
        if (txtNewPassword.isVisible()) {
            txtNewPasswordVisible.setText(txtNewPassword.getText());
            txtNewPasswordVisible.setVisible(true);
            txtNewPassword.setVisible(false);
        } else {
            txtNewPassword.setText(txtNewPasswordVisible.getText());
            txtNewPassword.setVisible(true);
            txtNewPasswordVisible.setVisible(false);
        }
    }

    @FXML
    public void toggleConfirmPasswordVisibility(ActionEvent event) {
        if (txtConfirmNewPassword.isVisible()) {
            txtConfirmNewPasswordVisible.setText(txtConfirmNewPassword.getText());
            txtConfirmNewPasswordVisible.setVisible(true);
            txtConfirmNewPassword.setVisible(false);
        } else {
            txtConfirmNewPassword.setText(txtConfirmNewPasswordVisible.getText());
            txtConfirmNewPassword.setVisible(true);
            txtConfirmNewPasswordVisible.setVisible(false);
        }
    }

    @FXML
    public void handleLogoutClick(ActionEvent ae) {
        UserSession.clearUserSession();
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }

    @FXML
    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CARS2_PAGE);
    }

    @FXML
    public void handleDashboardClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    @FXML
    public void handleCustomizeClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CUSTOMIZE_PAGE);
    }

    @FXML
    public void handleHelpClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HELP_PAGE);
    }

    @FXML
    public void handleProfileClick(MouseEvent me) {

    }

    @FXML
    public void handleRequestCarClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.REQUEST_CAR_PAGE);
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            System.out.println("Enter key pressed");
            if (event.getSource() instanceof PasswordField) {
                PasswordField source = (PasswordField) event.getSource();
                if (source.equals(txtCurrentPassword)) {
                    txtNewPassword.requestFocus(); // Move focus to the next field (txtNewPassword)
                } else if (source.equals(txtNewPassword)) {
                    txtConfirmNewPassword.requestFocus(); // Move focus to the next field (txtConfirmNewPassword)
                } else if (source.equals(txtConfirmNewPassword)) {
                    saveChanges(null); // Trigger saveChanges action
                }
            }
        }
    }

    private void purchaseHistory() {
        List<Purchase> purchases = purchasesRepository.getPurchasesByUserId(currentUser.getId());
        buytable.getItems().setAll(purchases);
    }

    @FXML
    public void applyFilter() {
        Double minPrice = txtMinPrice.getText().isEmpty() ? null : Double.parseDouble(txtMinPrice.getText());
        Double maxPrice = txtMaxPrice.getText().isEmpty() ? null : Double.parseDouble(txtMaxPrice.getText());
        LocalDateTime from = dpFromDate.getValue() == null ? null : dpFromDate.getValue().atStartOfDay();
        LocalDateTime to = dpToDate.getValue() == null ? null : dpToDate.getValue().atStartOfDay();

        highPriceFilter filter = new highPriceFilter(minPrice, maxPrice, currentUser.getUsername(), from, to);
        List<Purchase> filteredPurchases = purchasesRepository.getPurchasesByFilter(filter, currentUser.getId());
        buytable.getItems().setAll(filteredPurchases);
    }
}
