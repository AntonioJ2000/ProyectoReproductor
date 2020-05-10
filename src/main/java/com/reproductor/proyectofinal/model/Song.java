package com.reproductor.proyectofinal.model;

public class Song {
    private int IDCancion;
    private String Titulo;
    private String Estilo;
    private String nombreProductor;

    public Song(int IDCancion, String Titulo, String Estilo, String nombreProductor) {
        this.IDCancion = IDCancion;
        this.Titulo = Titulo;
        this.Estilo = Estilo;
        this.nombreProductor = nombreProductor;
    }

    public Song() {
        
    }

    public int getIDCancion() {
        return IDCancion;
    }

    public void setIDCancion(int IDCancion) {
        this.IDCancion = IDCancion;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getEstilo() {
        return Estilo;
    }

    public void setEstilo(String Estilo) {
        this.Estilo = Estilo;
    }

    public String getNombreProductor() {
        return nombreProductor;
    }

    public void setNombreProductor(String nombreProductor) {
        this.nombreProductor = nombreProductor;
    }

    @Override
    public String toString() {
        return "Song{" + "IDCancion=" + IDCancion + ", Titulo=" + Titulo + ", Estilo=" + Estilo + ", nombreProductor=" + nombreProductor + '}';
    }
    
    
    
}
