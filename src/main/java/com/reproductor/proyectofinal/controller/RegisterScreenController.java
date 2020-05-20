package com.reproductor.proyectofinal.controller;

import com.reproductor.proyectofinal.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterScreenController implements Initializable {

    @FXML
    private TextField Nombre;

    @FXML
    private TextField Contrasena;

    @FXML
    private TextField Pais;

    @FXML
    private TextField estiloFavorito;

    @FXML
    private TextField artistaFavorito;

    //Comunicar info a mi padre
    private LoginScreenController parent;

    //Recibir info de mi padre
    private Object params;

    //poder autocerrarme
    private Stage myStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }

    public void setParent(LoginScreenController p) {
        this.parent = p;
    }

    public void setParams(Object p) {
        params = p;
    }

    @FXML
    private void add() {
        String nombre = this.Nombre.getText();
        String password = this.Contrasena.getText();
        String pais = this.Pais.getText();
        String estiloFav = this.estiloFavorito.getText();
        String artistaFav = this.artistaFavorito.getText();

        if (nombre.trim().length() > 0 && password.trim().length() > 0 && pais.trim().length() > 0) {
            User newUser = new User(0, nombre, password, pais, estiloFav, artistaFav);
            if (parent != null) {
                parent.doOnModalClosed(newUser);
            }
            if (this.myStage != null) {
                this.myStage.close();
                parent.showConfirm(nombre);
            }
        } else {
            if (parent != null) {
                parent.showWarning("Error de validación", "Corrija el siguiente error:", "Tanto el campo 'Nombre', 'Contraseña' como 'País' no deben quedar vacíos");
            }
        }
    }

    @FXML
    private void cancel() {
        if (this.parent != null) {
            parent.doOnModalClosed(null);
        }

        if (this.myStage != null) {
            this.myStage.close();
        }
    }
}
