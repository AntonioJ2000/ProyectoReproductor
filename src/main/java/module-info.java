module com.mycompany.pruebafinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;
    requires java.base;
    
    opens com.mycompany.fxprueba.model to java.xml.bind;
    opens com.mycompany.fxprueba.controller to javafx.fxml;
    exports com.mycompany.pruebafinal;
    
}