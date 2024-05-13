package app;


import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Navigator {
    public final static String LOGIN_PAGE = "login_form.fxml";
    public final static String HOME_PAGE = "ClientDashboard.fxml";
    public final static String CREATE_ACCOUNT_PAGE = "sign_up_form.fxml";
    public final static String AdminDashboard_Page="AdminDashboard.fxml";
    public final static String AdminInsert_page="AdminInsert.fxml";
    public final static String AdminClients_page = "CreateandDeleteClient.fxml";
    public final static String Cars_page = "Cars.fxml";
    public final static String Costumize_page ="costumize.fxml";

    public static void navigate(Event event, String form){
        Node eventNode = (Node) event.getSource();
        Stage stage = (Stage) eventNode.getScene().getWindow();
        navigate(stage, form);
    }

    public static void navigate(Stage stage, String form){
        Pane formPane = loadPane(form);
        Scene newScene = new Scene(formPane);
        stage.setScene(newScene);
        stage.show();
    }
    public static void navigate(Pane pane, String form){
        Pane formPane = loadPane(form);
        pane.getChildren().clear();
        pane.getChildren().add(formPane);
    }

    private static Pane loadPane(String form){

        ResourceBundle bundle= ResourceBundle.getBundle(
                "Translations.content", Locale.getDefault()
        );
        FXMLLoader loader = new FXMLLoader(
                Navigator.class.getResource(form), bundle
        );
        try {
            return loader.load();
        }catch (IOException ioe){
            return null;
}
}
}