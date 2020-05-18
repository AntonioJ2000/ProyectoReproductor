package com.reproductor.proyectofinal.model;

import javazoom.jlgui.basicplayer.BasicPlayer;
import java.io.File;


public class Reproductor {
    private static Reproductor instance = null;
    private BasicPlayer player;
    
    private Reproductor(){
        player = new BasicPlayer();
    }
     
    public static Reproductor getInstance(){
        if(instance == null){
            instance = new Reproductor();
        }
        return instance;
    }
     
    public void Play(String ruta) throws Exception {
        abrirFichero(ruta);
        player.play();
    }
    
    private void abrirFichero(String ruta) throws Exception {
        player.open(new File(ruta));
    }
    
    public void Pausa() throws Exception {
        player.pause();
    }
    
    public void Continuar() throws Exception {
        player.resume();
    }
    
    public void Stop() throws Exception {
        player.stop();
    }
}
