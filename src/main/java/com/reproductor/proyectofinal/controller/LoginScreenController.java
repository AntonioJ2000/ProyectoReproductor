package com.reproductor.proyectofinal.controller;

import com.reproductor.proyectofinal.App;
import com.reproductor.proyectofinal.model.Reproductor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginScreenController {

    public void OpenRegisterWindows() {

        //Abrir el modal -> edit.fxml.
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("RegisterScreen.fxml"));
        Parent modal;
        try {
            modal = fxmlLoader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Ventana de registro");
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(App.rootstage); //ojo no existía, hay que crearlo

            Scene modalScene = new Scene(modal);
            modalStage.setScene(modalScene);

            RegisterScreenController modalController = fxmlLoader.getController();
            if (modalController != null) {
                //Para que se pueda cerrar.
                modalController.setStage(modalStage);

                // Comunicacion hijo -> padre.
                modalController.setParent(this);
                // Comunicamos padre -> hijo. 
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
            miReproductor.Play("MusicaReproductor\\1.mp3");
            
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
