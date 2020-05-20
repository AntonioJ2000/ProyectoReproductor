package com.reproductor.proyectofinal;

import com.reproductor.proyectofinal.model.Reproductor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

public class App extends Application {

    private static Scene scene;
    public static Stage rootstage;

    @Override
    public void start(Stage stage) throws IOException {
        rootstage=stage;
        scene = new Scene(loadFXML("LoginScreen"), 500, 600);
        stage.setScene(scene);
        rootstage.setTitle("Music 24/7");
        stage.getIcons().add(new Image("file:images\\icon.png"));
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    
    public static void main(String[] args) {
        launch();
    }

}