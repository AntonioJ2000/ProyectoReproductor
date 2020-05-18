package com.reproductor.proyectofinal.controller;

import com.reproductor.proyectofinal.App;
import com.reproductor.proyectofinal.model.Reproductor;
import com.reproductor.proyectofinal.model.Song;
import com.reproductor.proyectofinal.model.SongDAO;
import com.reproductor.proyectofinal.model.User;
import com.reproductor.proyectofinal.model.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginScreenController implements Initializable{

    @FXML
    private ImageView imageview;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("file:images\\icon.png");
        imageview.setImage(image);
        
        /* 
        List<Song> songs = SongDAO.selectAll();
        System.out.println(songs);
        */
        
        List<User> users = UserDAO.selectAll();
        System.out.println(users);
        
    }

    public void OpenRegisterWindows() {
        //Abrimos la ventana de registro.
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("RegisterScreen.fxml"));
        Parent modal;
        try {
            modal = fxmlLoader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Registro previo");
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(App.rootstage);

            Scene modalScene = new Scene(modal);
            modalStage.setScene(modalScene);

            RegisterScreenController modalController = fxmlLoader.getController();
            if (modalController != null) {
                modalController.setStage(modalStage);
                modalController.setParent(this);
                modalController.setParams(null);
            }

            modalStage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void play() {
        try {
            Reproductor miReproductor = Reproductor.getInstance();
            miReproductor.Play("MusicaReproductor\\"
                    + 1     //(Código de la cancion)
                    + ".mp3");
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }

    }
    
    public void pause(){
        try {
            Reproductor miReproductor = Reproductor.getInstance();
            miReproductor.Pausa();
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }     
    }
    
    public void resume(){
        try {
            Reproductor miReproductor = Reproductor.getInstance();
            miReproductor.Continuar();
            
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        } 
        
    }


}
