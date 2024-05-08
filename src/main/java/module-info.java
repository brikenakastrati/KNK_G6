module com.example.knk_g6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.knk_g6 to javafx.fxml;

    opens model to javafx.base, javafx.fxml;
    exports com.example.knk_g6;
    exports app;
    opens controller to javafx.fxml;
    opens controller.AdminController to javafx.fxml;
    opens controller.ClientController to javafx.fxml;

}