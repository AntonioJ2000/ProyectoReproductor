package com.reproductor.proyectofinal.controller;

import com.reproductor.proyectofinal.App;
import com.reproductor.proyectofinal.model.Reproductor;
import com.reproductor.proyectofinal.model.Song;
import com.reproductor.proyectofinal.model.SongDAO;
import com.reproductor.proyectofinal.model.User;
import com.reproductor.proyectofinal.model.UserDAO;
import com.reproductor.proyectofinal.utils.ConnectionUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginScreenController implements Initializable {

    @FXML
    private ImageView imageview;

    @FXML
    private TextField nombreUsuario;

    @FXML
    private PasswordField passwordUsuario;

    private ObservableList<User> data;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("file:images\\icon.png");
        imageview.setImage(image);

        this.data = FXCollections.observableArrayList();
    }

    public void Login() {
        String nombreUsuario = this.nombreUsuario.getText();
        this.nombreUsuario.clear();
        String passwordUsuario = this.passwordUsuario.getText();
        this.passwordUsuario.clear();
        if(UserDAO.Login(nombreUsuario, passwordUsuario)){
            OpenMainWindow();
        }else{
            showWarning("Error", "Usuario o contraseña erróneos", "Por favor, introduzca un usuario o contraseña válidos, si no dispone de una cuenta, creela.");
        }
    }

    public void OpenRegisterWindow() {
        //Abrimos la ventana de registro.
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("RegisterScreen.fxml"));
        Parent modal;
        try {
            modal = fxmlLoader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Registro previo");
            modalStage.getIcons().add(new Image("file:images\\icon.png"));
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

    public void OpenMainWindow() {
        //Abrimos la mainscreen.
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainScreen.fxml"));
        Parent modal;
        
        try {
            modal = fxmlLoader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Music 24/7");
            modalStage.getIcons().add(new Image("file:images\\icon.png"));
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.initOwner(App.rootstage);

            Scene modalScene = new Scene(modal);
            modalStage.setScene(modalScene);

            MainScreenController modalController = fxmlLoader.getController();
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

    public void doOnModalClosed(Object response) {
        if (response != null) {
            User newUser = (User) response;

            data.add(newUser);

            UserDAO dao = new UserDAO(newUser);
            int newID = dao.save();

            newUser.setIDUsuario(newID);
        }
    }

    public static void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }

    public void showInfo(String nombre) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acción realizada correctamente");
        alert.setHeaderText("¡El usuario " + nombre + " ha sido creado correctamente!");
        alert.setContentText("Recuerde que puede cambiar los datos dentro de la aplicación, disfrute de la buena música.");
        Optional<ButtonType> result = alert.showAndWait();
    }
}
