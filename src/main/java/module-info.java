module com.example.playerstatsproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.playerstatsproject to javafx.fxml;
    exports com.example.playerstatsproject;
    exports com.example.playerstatsproject.manager;
    opens com.example.playerstatsproject.manager to javafx.fxml;
}