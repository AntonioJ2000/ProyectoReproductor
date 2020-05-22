package com.reproductor.proyectofinal.model;

import javazoom.jlgui.basicplayer.BasicPlayer;
import java.io.File;


public class Reproductor implements I_Reproductor{
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
     
    @Override
    public void Play(String ruta) throws Exception {
        openFile(ruta);
        player.play();
    }
    
    private void openFile(String ruta) throws Exception {
        player.open(new File(ruta));
    }
    
    @Override
    public void Pause() throws Exception {
        player.pause();
    }
    
    @Override
    public void Resume() throws Exception {
        player.resume();
    }
    
    @Override
    public void Stop() throws Exception {
        player.stop();
    }
}
