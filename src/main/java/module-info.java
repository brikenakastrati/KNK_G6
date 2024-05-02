module com.example.knk_g6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.knk_g6 to javafx.fxml;
    exports com.example.knk_g6;
}