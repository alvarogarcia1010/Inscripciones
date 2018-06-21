
package dao;

import conexion.Conexion;
import interfaces.Metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Inscripcion;

/**
 *
 * @author Alvaro García <alvarogarcia1010 at github.com>
 */

public class InscripcionDao implements Metodos<Inscripcion> {
    
    private static final String SQL_INSERT ="INSERT INTO personas (numAfiliacion, nombres ,apellidos ,edad,profesion,estado) VALUES(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE personas SET nombres = ?, apellidos = ?, edad = ?, profesion = ?, estado = ? WHERE numAfiliacion = ?";
    private static final String SQL_DELETE = "DELETE FROM personas WHERE numAfiliacion = ?";
    private static final String SQL_READ = "SELECT * FROM personas WHERE numAfiliacion = ?";
    private static final String SQL_READALL = "SELECT * FROM personas";
    
    private static final Conexion conexion = Conexion.conectar();
    
    @Override
    public boolean create(Inscripcion g) {
        PreparedStatement ps;
        try{
            ps = this.conexion.getCnx().prepareStatement(this.SQL_INSERT);
            
            ps.setInt(1, g.getNumAFP());
            ps.setString(2, g.getNombre());
            ps.setString(3, g.getApellido());
            ps.setInt(4, g.getEdad());
            ps.setString(5, g.getProfesion());
            ps.setBoolean(6, g.isEstado());
            
            if(ps.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            this.conexion.cerrarConexion();
        }
        
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps = this.conexion.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1,key.toString());
            
            if(ps.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionDao.class.getName()).log(Level.SEVERE, null, ex);            
        }finally{
           this. conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Inscripcion c) {
        PreparedStatement ps;
        try{
            ps = this.conexion.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setInt(3, c.getEdad());
            ps.setString(4, c.getProfesion());
            ps.setBoolean(5, c.isEstado());;
            ps.setInt(6, c.getNumAFP());
            
            if(ps.executeUpdate() > 0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionDao.class.getName()).log(Level.SEVERE, null, ex);            
        }finally{
            this.conexion.cerrarConexion();
        }
        
        return false;
    }

    @Override
    public Inscripcion read(Object key) {
        Inscripcion i = null;
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = this.conexion.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs = ps.executeQuery();
            
            while(rs.next()){
                i = new Inscripcion(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getBoolean(6));
            }            
            rs.close();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionDao.class.getName()).log(Level.SEVERE, null, ex);                        
        }finally{
            this.conexion.cerrarConexion();
        }
        return i;
    }
    
    @Override
    public ArrayList<Inscripcion> readAll(){
        ArrayList<Inscripcion> all = new ArrayList<>();
        Statement s;
        ResultSet rs;
        try{
            s = this.conexion.getCnx().prepareStatement(SQL_READ);
            rs = s.executeQuery(SQL_READALL);
            while(rs.next()){
                all.add(new Inscripcion(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getBoolean(6)));
            }            
 
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(InscripcionDao.class.getName()).log(Level.SEVERE, null, ex);                                    
        }finally{
            this.conexion.cerrarConexion();
        }
        return all;
    }
    
}
