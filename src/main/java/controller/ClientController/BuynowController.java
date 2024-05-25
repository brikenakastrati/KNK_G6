package controller.ClientController;

import Repository.PurchasesRepository;
import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;
import model.carInventory;
import service.BuyNowService;
import service.PurchasesService;
import service.UserService;
import service.UserSession;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class BuynowController implements Initializable {
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label stockLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField fullnametxt;
    @FXML
    private TextField cardnumbertxt;
    @FXML
    private TextField cvvtxt;
    @FXML
    private TextField dateExpirationtxt;

    private carInventory car;

    private UserService userService = new UserService();

    private PurchasesService purchasesService = new PurchasesService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        car = BuyNowService.getInstance().getSelectedCar();
        nameLabel.setText(car.getCarname());
        priceLabel.setText(String.format("%.2f", car.getCarprice()));
        stockLabel.setText(String.valueOf(car.getCarstock()));
        String user = UserService.getUsername();
        usernameLabel.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }

    @FXML
    public void handleBuyCarButton(ActionEvent ae) {
        String carId = car.getCarid();

        String account = fullnametxt.getText();
        String bank_nr = cardnumbertxt.getText();
        String cvv = cvvtxt.getText();
        String dateExpire = dateExpirationtxt.getText();

        boolean paymentSuccessful = accountCredentialsValidation(account, bank_nr, cvv, dateExpire);
        if (paymentSuccessful) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().clear();
            alert.setTitle("Confirm the buying process");
            alert.setHeaderText("Are you sure you want to buy this car?");
            alert.setContentText("This action cannot be undone");

            ButtonType yes = new ButtonType("Yes");
            ButtonType no = new ButtonType("No");

            alert.getButtonTypes().addAll(yes, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == yes) {
                BuyNowService.getInstance().decreaseStock(carId);
                purchasesService.storePurchaseDetails(car, account, bank_nr, cvv, dateExpire);
                Navigator.navigate(ae, Navigator.CARS2_PAGE);
            }else{
                Navigator.navigate(ae, Navigator.CARS2_PAGE);
            }
        }else{
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error processing payment");
            alert1.setContentText("Your credentials are invalid");
            alert1.showAndWait();
        }
    }

    private boolean accountCredentialsValidation(String account, String bank_nr, String cvv, String dateExpire) {
        String regexFullName = "[A-Za-z]+ [A-Za-z]+";
        String cvvRegex = "\\d{3}";
        String cardNumberRegex = "\\d{16}";
        String dateExpRegex = "^(0[1-9]|1[0-2])\\/([0-9]{2})$";

        if (account.matches(regexFullName) && bank_nr.matches(cardNumberRegex) && cvv.matches(cvvRegex) && dateExpire.matches(dateExpRegex)) {

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
                YearMonth expYearMonth = YearMonth.parse(dateExpire, formatter);
                LocalDate expDate = expYearMonth.atEndOfMonth();

                LocalDate today = LocalDate.now();
                LocalDate endOfMonthToday = today.withDayOfMonth(today.getMonth().length(today.isLeapYear()));

                if (expDate.isBefore(endOfMonthToday)) {
                    return false;
                }

                return true;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format: " + e.getMessage());
                return false;
            }
        } else if (account.isEmpty() || bank_nr.isEmpty() || cvv.isEmpty() || dateExpire.isEmpty()) {
            return false;
        } else {
            return false;
        }
    }



    public void handleCarsClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CARS2_PAGE);
    }
    public void handleHelpClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HELP_PAGE);
    }
    public void handleLogoutClick(ActionEvent ae) {
        UserSession.clearUserSession();
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }
    public void handleCustomizeClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.CUSTOMIZE_PAGE);
    }

    public void handleProfileClick(MouseEvent me) {
        try {
            User currentUser = userService.getUserByUsername(UserService.getUsername());
            UserService.setCurrentUser(currentUser);
            Navigator.navigate(me, Navigator.CLIENT_PROFILE_PAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void handleRequestCarClick(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.REQUEST_CAR_PAGE);
    }
    public void handleClientDashboardClick(MouseEvent me) {
        Navigator.navigate(me, Navigator.HOME_PAGE);
    }
    @FXML
    public void handleENClick(ActionEvent ae) {
        Locale.setDefault(new Locale("en"));
        Navigator.navigate(ae, Navigator.BUY_NOW_PAGE);
    }

    @FXML
    public void handleSQClick(ActionEvent ae) {
        Locale.setDefault(new Locale("sq","AL"));
        Navigator.navigate(ae, Navigator.BUY_NOW_PAGE);
    }
}
