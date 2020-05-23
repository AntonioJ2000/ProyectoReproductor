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

public class UserDAO extends User{

    //Es un atributo que sirve para acceder a la información del usuario principal desde cualquier clase con facilidad.
    public static UserDAO MainUser;

    public UserDAO() {
        super();
        
    }

    public UserDAO(int IDUsuario, String nombre, String password, String pais, String estiloFavorito, String artistaFav) {
        super(IDUsuario, nombre, password, pais, estiloFavorito, artistaFav);
        
    }

    public UserDAO(String nombre, String password, String pais, String estiloFavorito, String artistaFav) {
        super(-1, nombre, password, pais, estiloFavorito, artistaFav);
        
    }

    public UserDAO(User u) {
        IDUsuario = u.IDUsuario;
        Nombre = u.Nombre;
        Password = u.Password;
        Pais = u.Pais;
        estiloFavorito = u.estiloFavorito;
        artistaFav = u.artistaFav;
    }

    public UserDAO(int IDUsuario){
        super();
        
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q="SELECT * FROM Usuario WHERE IDUsuario="+IDUsuario;
            PreparedStatement ps=conn.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    this.IDUsuario=IDUsuario;
                    this.Nombre=rs.getString("Nombre");
                    this.Password=rs.getString("Password");
                    this.Pais=rs.getString("Pais");
                    this.estiloFavorito=rs.getString("estiloFav");
                    this.artistaFav=rs.getString("artistaFav");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);     
        save();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password); 
        save();
    }

    @Override
    public void setPais(String pais) {
        super.setPais(pais);
        save();
    }

    @Override
    public void setEstiloFavorito(String estiloFavorito) {
        super.setEstiloFavorito(estiloFavorito);
        save();
    }

    @Override
    public void setArtistaFav(String artistaFavorito) {
        super.setArtistaFav(artistaFavorito);
        save();
    }

    /**
     * Se encarga de hacer la función correspondiente (UPDATE | DELETE) cuando es invocada.
     * @return 0 si es que el usuario no se puede guardar o actualizar.
     */
    public int save() {
        int result = 0;
        try {
        java.sql.Connection csql = ConnectionUtil.getConnection();
        if (this.IDUsuario > 0) {
            //UPDATE
            String q = "UPDATE Usuario SET Nombre = ?, Password = ?, Pais = ?, estiloFav = ?, artistaFav = ? WHERE IDUsuario = ?";
            PreparedStatement ps = csql.prepareStatement(q);
            ps.setString(1, Nombre);
            ps.setString(2, Password);
            ps.setString(3, Pais);
            ps.setString(4, estiloFavorito);
            ps.setString(5, artistaFav);
            ps.setInt(6, MainUser.IDUsuario);
            
            result = ps.executeUpdate();
        } else {
            //INSERT
            String q = "INSERT INTO Usuario(IDUsuario, Nombre, Password, Pais, estiloFav, artistaFav) VALUES(NULL,?,?,?,?,?)";
            PreparedStatement ps = csql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Nombre);
            ps.setString(2, Password);
            ps.setString(3, Pais);
            ps.setString(4, estiloFavorito);
            ps.setString(5, artistaFav);
            result = ps.executeUpdate();
            System.out.println(result);
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
    
    /**
     * Sirve para obtener la información de la base de datos.
     * @return La lista con los usuarios existentes.
     */
    public static List<User> selectAll(){
        return selectAll("");
    }
    
    /**
     * Obtiene la información de la base de datos.
     * @param pattern El valor del patrón de busqueda.
     * @return La lista que cumple el patrón.
     */
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
                u.setPassword(rs.getString("Password"));
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
    
    /**
     * Se encarga de hacer el login mediante una query en la base de datos.
     * @param nombreUsuario El nombre del usuario introducido
     * @param passwordUsuario La contraseña introducida
     * @return true si existe en la base de datos y false si no.
     */
    public static boolean Login(String nombreUsuario, String passwordUsuario){
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "SELECT*from Usuario WHERE nombre LIKE ? and password LIKE ?";

            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, nombreUsuario);
            ps.setString(2, passwordUsuario);
                                            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                if (rs.getString("Nombre").trim().length() > 0) {
                    User mainUser = new User();
                    mainUser.setIDUsuario(rs.getInt("IDUsuario"));
                    mainUser.setNombre(rs.getString("Nombre"));
                    mainUser.setPassword(rs.getString("Password"));
                    mainUser.setPais(rs.getString("Pais"));
                    mainUser.setEstiloFavorito(rs.getString("estiloFav"));
                    mainUser.setArtistaFav(rs.getString("artistaFav"));   
                    /*
                    Creo el usuario principal y lo paso mediante el constructor personalizado
                    de User -> UserDAO junto a los valores que se atribuyen al usuario registrado.
                    */
                    UserDAO daoAux = new UserDAO(mainUser);
                    MainUser = daoAux;
                    result = true;
                }
            } 
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    
    /**
     * Se encarga de borrar el usuario de la base de datos.
     * @return true si se ha borrado, false si no.
     */
    public static boolean deleteUser(){
        boolean result = false;
        if (MainUser.getIDUsuario() > 0) {
            try {
               java.sql.Connection csql = ConnectionUtil.getConnection();
                String q = "DELETE FROM Usuario WHERE IDUsuario = " + MainUser.getIDUsuario();

                PreparedStatement ps = csql.prepareStatement(q);
                ps.executeUpdate();
                result = true;
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}