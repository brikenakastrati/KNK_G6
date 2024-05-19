package app;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.scene.control.ScrollPane;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Navigator {
    public final static String LOGIN_PAGE = "login_form.fxml";
    public final static String HOME_PAGE = "ClientDashboard.fxml";
    public final static String CREATE_ACCOUNT_PAGE = "sign_up_form.fxml";
    public final static String ADMIN_DASHBOARD_PAGE = "AdminDashboard.fxml";
    public final static String ADMIN_INSERT_PAGE = "AdminInsert.fxml";
    public final static String ADMIN_CLIENTS_PAGE = "CreateandDeleteClient.fxml";
    public final static String CARS_PAGE = "Cars.fxml";
    public final static String CUSTOMIZE_PAGE = "costumize.fxml";
    public final static String MESSAGE_PAGE = "messages.fxml";
    public final static String HELP_PAGE = "help.fxml";
    public final static String CARS2_PAGE = "cars2.fxml";
    public final static String BUY_NOW_PAGE = "BuyNow.fxml";
public final static String CLIENT_PROFILE_PAGE = "UserProfile.fxml";
    public static void navigate(Event event, String form) {
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        navigate(stage, form);
    }

    public static void navigate(Stage stage, String form) {
        Pane formPane = loadPane(form);
        Scene newScene = new Scene(formPane);
        stage.setScene(newScene);
        stage.show();
    }

    public static void navigate(Pane pane, String form) {
        Pane formPane = loadPane(form);
        pane.getChildren().clear();
        pane.getChildren().add(formPane);
    }

    private static Pane loadPane(String form) {
        ResourceBundle bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
        FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(form), bundle);
        try {
            Parent root = loader.load();
            if (root instanceof ScrollPane) {
                ScrollPane scrollPane = (ScrollPane) root;
                AnchorPane anchorPane = new AnchorPane(scrollPane.getContent());
                return anchorPane;
            } else {
                Pane pane = new Pane();
                pane.getChildren().add(root);
                return pane;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}
