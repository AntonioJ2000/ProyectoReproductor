module com.reproductor.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.bind;
    requires java.base;
    requires basicplayer;
    
    opens com.reproductor.proyectofinal.model to java.xml.bind;
    opens com.reproductor.proyectofinal.controller to javafx.fxml;
    exports com.reproductor.proyectofinal;
    
}