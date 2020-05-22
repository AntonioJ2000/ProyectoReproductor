package com.reproductor.proyectofinal.controller;

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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainScreenController implements Initializable{

    //Comunicar info a mi padre
    private LoginScreenController parent;

    //Recibir info de mi padre
    private Object params;

    //poder autocerrarme
    private Stage myStage;
    
    
    public void setStage(Stage myStage) {
        this.myStage = myStage;
    }

    public void setParent(LoginScreenController p) {
        this.parent = p;
    }

    public void setParams(Object p) {
        params = p;
    }
    
    private ObservableList<Song> data;
   
   
    @FXML
    private TableView<Song> table;
    
    @FXML
    private TableColumn<Song, String> titleColumn;
   
    @FXML
    private TableColumn<Song, String> ProducerColumn;
   
    @FXML
    private TableColumn<Song, String> StyleColumn;
    
    @FXML
    private ImageView imageview2;
    
    @FXML
    private TextField filtraCancion;
   
    @FXML
    private TextField nombre;
    
    @FXML
    private TextField password;
    
    @FXML
    private TextField pais;
    
    @FXML
    private TextField estiloFav;
    
    @FXML
    private TextField artistaFav;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Image image2 = new Image("file:utils\\oldicon.png");
        imageview2.setImage(image2);
        
    this.data = FXCollections.observableArrayList();
    
    List<Song> misCanciones = SongDAO.selectAll();
    data.addAll(misCanciones);
    
    this.titleColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getTitulo());
    });
    
    this.ProducerColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getNombreProductor());
    });

    this.StyleColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getEstilo());
    });
    
    table.setItems(data);
    
    }
    
    
    public void editUser(){
        String Nombre = this.nombre.getText();
        String Password = this.password.getText();
        String Pais = this.pais.getText();
        String EstiloFav = this.estiloFav.getText();
        String ArtistaFav = this.artistaFav.getText();
       
        if(Nombre.trim().length()>0){
        UserDAO.MainUser.setNombre(Nombre); 
        nombre.clear();
        }
        if(Password.trim().length()>0){
        UserDAO.MainUser.setPassword(Password);
        password.clear();
        }
        
        if(Pais.trim().length()>0){
        UserDAO.MainUser.setPais(Pais);
        pais.clear();
        }
        
        if(EstiloFav.trim().length()>0){
        UserDAO.MainUser.setEstiloFavorito(EstiloFav);
        estiloFav.clear();
        }
        
        if(ArtistaFav.trim().length()>0){
        UserDAO.MainUser.setArtistaFav(ArtistaFav);
        artistaFav.clear();
        }
        
    }

    public void deleteProfile(){
        if(showConfirm(UserDAO.MainUser.getNombre())){
            UserDAO.deleteUser();
            myStage.close();
            Stop();
        }else{
            showWarning("Error", "El usuario "+UserDAO.MainUser.getNombre()+" no "
                    + "ha sido borrado", "Podrá ser borrado posteriormente si lo desea.");
        }
    }
    
    public void closeApp(){
        myStage.close();
    }
    
    
    
    public void searchSong(){
        String cancion = this.filtraCancion.getText();
        if(cancion!=null){
            List<Song> misCancionesFiltradas = SongDAO.selectAll(cancion);
            data.clear();
            this.data.addAll(misCancionesFiltradas);
            
        }
        
        this.titleColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getTitulo());
         });
    
        this.ProducerColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getNombreProductor());
         });

        this.StyleColumn.setCellValueFactory(eachRowData -> {
            return new SimpleObjectProperty<>(eachRowData.getValue().getEstilo());
        });
    
        table.setItems(data);  
        if(data.isEmpty()){
            showInfo();
        }
    }

    public boolean showConfirm(String nombre) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirme la acción");
        alert.setHeaderText("¿Desea borrar el usuario " +nombre+ "?");
        alert.setContentText("Está a punto de eliminar su perfil, ¿desea hacerlo?");

           
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
    
    public void showWarning(String title, String header, String description) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);
        alert.showAndWait();
    }
    
    public void showInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡No encontrado!");
        alert.setHeaderText("La canción introducida en el buscador no existe");
        alert.setContentText("Por favor, introduzca el título de una canción que se encuentre en la base de datos.");
        Optional<ButtonType> result = alert.showAndWait();
    }
    
    
    public void Play(){
        Reproductor miReproductor = Reproductor.getInstance();
        try {
            miReproductor.Play("utils\\musicaReproductor\\1.mp3");
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void Stop(){
        Reproductor miReproductor = Reproductor.getInstance();
        try {
            miReproductor.Stop();
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Pause(){
        Reproductor miReproductor = Reproductor.getInstance();
        try {
            miReproductor.Pause();
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Resume(){
        Reproductor miReproductor = Reproductor.getInstance();
        try {
            miReproductor.Resume();
        } catch (Exception ex) {
            Logger.getLogger(MainScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}