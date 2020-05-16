package com.reproductor.proyectofinal.model;

import javazoom.jlgui.basicplayer.BasicPlayer;
import java.io.File;


public class Reproductor {
     private final BasicPlayer player;    

    
    public Reproductor() {
        player = new BasicPlayer();
    }
     
    public void Play() throws Exception {
        player.play();
    }
    
    public void abrirFichero(String ruta) throws Exception {
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
