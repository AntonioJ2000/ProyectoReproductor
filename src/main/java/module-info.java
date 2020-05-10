module com.mycompany.pruebafinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.mycompany.pruebafinal to javafx.fxml;
    exports com.mycompany.pruebafinal;
}