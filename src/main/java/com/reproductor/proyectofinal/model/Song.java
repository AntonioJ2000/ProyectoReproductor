package com.reproductor.proyectofinal.model;

public class Song {
    protected int IDCancion;
    protected String titulo;
    protected String estilo;
    protected String nombreProductor;

    public Song(int IDCancion, String Titulo, String Estilo, String nombreProductor) {
        this.IDCancion = IDCancion;
        this.titulo = Titulo;
        this.estilo = Estilo;
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
        return titulo;
    }

    public void setTitulo(String Titulo) {
        this.titulo = Titulo;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String Estilo) {
        this.estilo = Estilo;
    }

    public String getNombreProductor() {
        return nombreProductor;
    }

    public void setNombreProductor(String nombreProductor) {
        this.nombreProductor = nombreProductor;
    }

    @Override
    public String toString() {
        return "Song{" + "IDCancion=" + IDCancion + ", Titulo=" + titulo + ", Estilo=" + estilo + ", nombreProductor=" + nombreProductor + '}';
    }
    
    
    
}
