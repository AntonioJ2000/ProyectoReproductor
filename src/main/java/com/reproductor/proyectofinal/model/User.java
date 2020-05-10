package com.reproductor.proyectofinal.model;

public class User {
    private int IDUsuario;
    private String nombre;
    private String pais;
    private String estiloFavorito;
    private String artistaFavorito;

    public User(int IDUsuario, String nombre, String pais, String estiloFavorito, String artistaFavorito) {
        this.IDUsuario = IDUsuario;
        this.nombre = nombre;
        this.pais = pais;
        this.estiloFavorito = estiloFavorito;
        this.artistaFavorito = artistaFavorito;
    }
    
    public User() {
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEstiloFavorito() {
        return estiloFavorito;
    }

    public void setEstiloFavorito(String estiloFavorito) {
        this.estiloFavorito = estiloFavorito;
    }

    public String getArtistaFavorito() {
        return artistaFavorito;
    }

    public void setArtistaFavorito(String artistaFavorito) {
        this.artistaFavorito = artistaFavorito;
    }

    @Override
    public String toString() {
        return "User{" + "IDUsuario=" + IDUsuario + ", nombre=" + nombre + ", pais=" + pais + ", estiloFavorito=" + estiloFavorito + ", artistaFavorito=" + artistaFavorito + '}';
    }
    
    
}
