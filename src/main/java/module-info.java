module com.mycompany.proyecto2_paradigmas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

opens controller to javafx.fxml; 
            exports controller;
    opens app to javafx.fxml;
    exports app;
}
