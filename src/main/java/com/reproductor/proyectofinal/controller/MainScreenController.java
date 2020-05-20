package com.reproductor.proyectofinal.controller;

import com.reproductor.proyectofinal.model.Song;
import com.reproductor.proyectofinal.model.SongDAO;
import com.reproductor.proyectofinal.model.User;
import com.reproductor.proyectofinal.model.UserDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TextField nombre;
    @FXML
    private TextField password;
    @FXML
    private TextField pais;
    @FXML
    private TextField estiloFav;
    @FXML
    private TextField artistaFav;
    
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        
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
}
