package com.reproductor.proyectofinal.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class RegisterScreenController implements Initializable{

    
    //Comunicar info a mi padre
    private LoginScreenController parent;
    
    //Recibir info de mi padre
    private Object params;
    
    //poder autocerrarme
    private Stage myStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    public void setStage(Stage myStage){
        this.myStage=myStage;
    }
    
    public void setParent(LoginScreenController p){
        this.parent=p;
    }
    
    public void setParams(Object p){
        params = p;
    }
    
    
    
    @FXML
    private void cancel(){
        if(this.myStage != null){
            this.myStage.close();
        }   
    }
}
