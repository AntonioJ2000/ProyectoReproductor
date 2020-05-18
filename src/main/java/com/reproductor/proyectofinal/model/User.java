package com.reproductor.proyectofinal.model;

import java.util.List;

public class User {
   private int IDUsuario;
   private String Nombre;
   private String Password;
   private String Pais;
   private String estiloFavorito;
   private String artistaFav;
   private List<User> ListaDeAmigos;

    public User(int IDUsuario, String Nombre, String Password, String Pais, String estiloFavorito, String artistaFav, List<User> ListaDeAmigos) {
        this.IDUsuario = IDUsuario;
        this.Nombre = Nombre;
        this.Password = Password;
        this.Pais = Pais;
        this.estiloFavorito = estiloFavorito;
        this.artistaFav = artistaFav;
        this.ListaDeAmigos = ListaDeAmigos;
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getEstiloFavorito() {
        return estiloFavorito;
    }

    public void setEstiloFavorito(String estiloFavorito) {
        this.estiloFavorito = estiloFavorito;
    }

    public String getArtistaFav() {
        return artistaFav;
    }

    public void setArtistaFav(String artistaFav) {
        this.artistaFav = artistaFav;
    }

    public List<User> getListaDeAmigos() {
        return ListaDeAmigos;
    }

    public void setListaDeAmigos(List<User> ListaDeAmigos) {
        this.ListaDeAmigos = ListaDeAmigos;
    }

    @Override
    public String toString() {
        return "User{" + "IDUsuario=" + IDUsuario + ", Nombre=" + Nombre + ", Password=" + Password + ", Pais=" + Pais + ", estiloFavorito=" + estiloFavorito + ", artistaFav=" + artistaFav + ", ListaDeAmigos=" + ListaDeAmigos + '}';
    }
   
}
