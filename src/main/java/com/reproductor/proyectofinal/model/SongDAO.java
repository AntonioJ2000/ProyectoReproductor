package com.reproductor.proyectofinal.model;

import com.reproductor.proyectofinal.utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SongDAO extends Song implements I_SongDAO{
    
    private boolean persist;
    
    public SongDAO(){
        super();
        persist=false;
    }
    
    public SongDAO(int IDCancion, String titulo, String estilo, String nombreProductor){
        super(IDCancion, titulo, estilo, nombreProductor);
        persist = false;
    }
    
    public SongDAO(Song s){
        IDCancion = s.IDCancion;
        titulo = s.titulo;
        estilo = s.estilo;
        nombreProductor = s.nombreProductor;
    }
    
    public static List<Song> selectAll(){
        return selectAll("");
        
    }
    
    public static List<Song> selectAll(String pattern){
        List<Song> result = new ArrayList<>();
        
        try {
            
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "SELECT * FROM CANCION";
            
            if(pattern.length()>0){
                q+=" WHERE Titulo LIKE ?";
            }
            
            PreparedStatement ps = conn.prepareStatement(q);
            
            if(pattern.length()>0){
                ps.setString(1, pattern+"%");
            }
            
            ResultSet rs = ps.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    Song s = new Song();
                    s.setIDCancion(rs.getInt("IDCancion"));
                    s.setTitulo(rs.getString("Titulo"));
                    s.setEstilo(rs.getString("Estilo"));
                    s.setNombreProductor(rs.getString("nombreProductor"));
                    result.add(s);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
