package com.reproductor.proyectofinal.model;


public interface I_Reproductor {
    
    public void Play(String ruta) throws Exception;
        
    public void Pause()throws Exception;
    
    public void Resume()throws Exception;
    
    public void Stop()throws Exception;
    
}
