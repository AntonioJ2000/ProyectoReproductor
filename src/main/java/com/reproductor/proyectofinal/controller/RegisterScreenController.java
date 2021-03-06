package com.reproductor.proyectofinal.controller;

import com.reproductor.proyectofinal.model.User;
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

    private LoginScreenController parent;

    private Object params;

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

    /**
     * Añade el usuario registrado a la base de datos.
     */
    @FXML
    private void add() {
        String nombre = this.Nombre.getText();
        Nombre.clear();
        String password = this.Contrasena.getText();
        Contrasena.clear();
        String pais = this.Pais.getText();
        Pais.clear();
        String estiloFav = this.estiloFavorito.getText();
        estiloFavorito.clear();
        String artistaFav = this.artistaFavorito.getText();
        artistaFavorito.clear();
        
        if (nombre.trim().length() > 0 && password.trim().length() > 0 && pais.trim().length() > 0) {
            User newUser = new User(0, nombre, password, pais, estiloFav, artistaFav);
            if (parent != null) {
                parent.doOnModalClosed(newUser);
            }
            if (this.myStage != null) {
                this.myStage.close();
                parent.showInfo(nombre);
            }
        } else {
            if (parent != null) {
                parent.showWarning("Error de validación", "Corrija el siguiente error:", "Tanto el campo 'Nombre', 'Contraseña' como 'País' no deben quedar vacíos");
            }
        }
    }

    /**
     * Cancela el proceso de registro.
     */
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
