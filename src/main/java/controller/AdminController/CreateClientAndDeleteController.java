package controller.AdminController;

import Repository.UserRepository;
import app.Navigator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.User;
import model.dto.UserDto;
import service.Interface.UserServiceInterface;
import service.UserService;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreateClientAndDeleteController implements Initializable{
    private UserServiceInterface userService;
    private ObservableList<User> userlist;
    private int id_to_delete;

    @FXML
    private TextField txtusername, txtemail;
    @FXML
    private PasswordField txtpassword, txtconfirmpassword;
    @FXML
    private Button deleteBtn, createclientbutton;
    @FXML
    private TableView<User> usertable;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private TableColumn<User, String> salt;

    public CreateClientAndDeleteController() {
        this.userService = new UserService();
    }
    @FXML
    private void handleCreateClient(ActionEvent ae){


        if (txtusername.getText().isEmpty() || txtemail.getText().isEmpty() || txtpassword.getText().isEmpty() ||
                txtconfirmpassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Please fill in all the fields");
            return;
        }

        if (!txtemail.getText().matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Please enter a valid email address");
            return;
        }
        if (!txtpassword.getText().equals(txtconfirmpassword.getText())) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Passwords do not match");
            return;
        }
        if (txtpassword.getText().length() < 8) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Password must be at least 8 characters long");
            return;
        }
        User user = UserRepository.getByUsername(this.txtusername.getText());
        if (user != null) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Username is already taken try another one");
            return;
        }
        User user2 = UserRepository.getByEmail(this.txtemail.getText());
        if (user2 != null) {
            showAlert(Alert.AlertType.ERROR, "Creation error", "Email address is already taken try another one");
            return;
        }


        UserDto userSignUpData = new UserDto(
                this.txtusername.getText(),
                this.txtemail.getText(),
                this.txtpassword.getText(),
                this.txtconfirmpassword.getText()
        );

        boolean response = UserService.createclientbutton(userSignUpData);

        if(response){
            showAlert(Alert.AlertType.INFORMATION, "New client created", "New client created successfully");
            Navigator.navigate(ae, Navigator.ADMIN_CLIENTS_PAGE);
        }
        else {
            showAlert(Alert.AlertType.ERROR, "Creating client error", "Error while creating new client");
        }

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
    private void handleDashboardClick(ActionEvent ae){
        Navigator.navigate(ae, Navigator.ADMIN_INSERT_PAGE);
    }
    public void handleMessageClick(ActionEvent ae){
        Navigator.navigate(ae, Navigator.MESSAGE_PAGE);
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    public void getIdPressed(MouseEvent me) {
        if (me.isPrimaryButtonDown() && me.getClickCount()==1) {
            this.usertable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelect, newSelect)->{
                if (newSelect != null) {
                    this.id_to_delete = newSelect.getId();
                }// ky mtha shto qata amo tani kur e ndrrova te tableview u hjek vet
            });
        }
        System.out.println(this.id_to_delete);
    }
    @FXML
    public void deleteUser(ActionEvent ae) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().clear();
        alert.setTitle("Confirm deletion");
        alert.setHeaderText("Are you sure you want to delete this user?");
        alert.setContentText("This action cannot be undone");

        ButtonType yes =new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.getButtonTypes().addAll(yes,no);
        Optional<ButtonType> result =alert.showAndWait();
        if (result.get() == yes) {
            this.userService.deleteUser(this.id_to_delete);
            User selectedUser = (User) this.usertable.getSelectionModel().getSelectedItem();
            this.userlist.remove(selectedUser);
            this.usertable.refresh();
        }else{
            return;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        this.username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        this.salt.setCellValueFactory(new PropertyValueFactory<User, String>("salt"));
        try {
            this.userService.fillUserTable(this.usertable, false);
            this.userlist = this.usertable.getItems();

            for (User u : userlist) {
                System.out.println(u.getUsername()+ " - " +u.getPasswordHash());
            }
        }catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}
