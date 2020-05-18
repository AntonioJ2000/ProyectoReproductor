package com.reproductor.proyectofinal.model;

import java.util.List;

public class UserDAO extends User implements I_UserDAO{
    
    public UserDAO(int IDUsuario, String Nombre, String Password, String Pais, String estiloFavorito, String artistaFav, List<User> ListaDeAmigos) {
        super(IDUsuario, Nombre, Password, Pais, estiloFavorito, artistaFav, ListaDeAmigos);
    }
    
}
