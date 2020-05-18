package com.reproductor.proyectofinal.model;

import com.reproductor.proyectofinal.utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends User implements I_UserDAO {

    private boolean persist;

    public UserDAO() {
        super();
        persist = false;
    }

    public UserDAO(int IDUsuario, String nombre, String password, String pais, String estiloFavorito, String artistaFav) {
        super(IDUsuario, nombre, password, pais, estiloFavorito, artistaFav);
        persist = false;
    }

    public UserDAO(String nombre, String password, String pais, String estiloFavorito, String artistaFav) {
        super(-1, nombre, password, pais, estiloFavorito, artistaFav);
        persist = false;
    }

    public UserDAO(User u) {
        IDUsuario = u.IDUsuario;
        Nombre = u.Nombre;
        Password = u.Password;
        Pais = u.Pais;
        estiloFavorito = u.estiloFavorito;
        artistaFav = u.artistaFav;
    }

    public void persist() {
        persist = true;
    }

    public void detatch() {
        persist = false;
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
        if (persist) {
            save();
        }
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        if (persist) {
            save();
        }

    }

    @Override
    public void setPais(String pais) {
        super.setPais(pais);
        if (persist) {
            save();
        }
    }

    @Override
    public void setEstiloFavorito(String estiloFavorito) {
        super.setEstiloFavorito(estiloFavorito);
        if (persist) {
            save();
        }
    }

    public void setArtistaFavorito(String artistaFavorito) {
        super.setArtistaFav(artistaFavorito);
        if (persist) {
            save();
        }
    }


    public int save() {
        int result = -1;
        try {
        java.sql.Connection csql = ConnectionUtil.getConnection();
        if (this.IDUsuario > 0) {

            //UPDATE
            String q = "UPDATE usuario SET Nombre = ?, Password = ?, Pais = ?, estiloFav = ?, artistaFav = ? WHERE IDUsuario = ?";
            PreparedStatement ps = csql.prepareStatement(q);
            ps.setString(1, Nombre);
            ps.setString(2, Password);
            ps.setString(3, Pais);
            ps.setString(4, estiloFavorito);
            ps.setString(5, artistaFav);

        } else {

            //INSERT
            String q = "INSERT INTO usuario (IDUsuario, Nombre, Password, Pais, estiloFav, artistaFav) VALUES (NULL, ?,?,?,?,?)";
            PreparedStatement ps = csql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Nombre);
            ps.setString(2, Password);
            ps.setString(3, Pais);
            ps.setString(4, estiloFavorito);
            ps.setString(5, artistaFav);
            result = ps.executeUpdate();
            try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                if(generatedKeys.next()){
                    result = generatedKeys.getInt(1);
                }
            }
            this.IDUsuario = result;
        }
        
    } catch (SQLException ex){
        Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
    }

        return result;
    }
    
    public static List<User> selectAll(){
        return selectAll("");
    }
    
    public static List<User> selectAll(String pattern){
        List<User> result = new ArrayList<>();
        
        try{
        java.sql.Connection conn = ConnectionUtil.getConnection();
        String q = "SELECT * FROM Usuario";
        
        if(pattern.length()>0){
            q+=" WHERE Nombre LIKE ?";
        }
        PreparedStatement ps = conn.prepareStatement(q);
        if(pattern.length()>0){
            ps.setString(1, pattern+"%");
        }
        
        ResultSet rs = ps.executeQuery();
        if(rs!=null){
            while (rs.next()) {
                User u = new User();
                u.setIDUsuario(rs.getInt("IDUsuario"));
                u.setNombre(rs.getString("Nombre"));
                u.setPais(rs.getString("Pais"));
                u.setEstiloFavorito(rs.getString("estiloFav"));
                u.setArtistaFav(rs.getString("artistaFav"));
                result.add(u);
            }
        }
        }catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        
    } 
        return result;
    }
}