/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.congresosoft.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pe.edu.pucp.congresosoft.config.DBManager;
import pe.edu.pucp.congresosoft.dao.CongresoDAO;
import pe.edu.pucp.congresosoft.model.Congreso;

/**
 *
 * @author David
 */
public class CongresoMySQL implements CongresoDAO{
    
    @Override
    public int insertar(Congreso congreso) {
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
           con = DriverManager.getConnection(DBManager.urlMySQL, 
                    DBManager.user, DBManager.password);
            
            String nomb,pais;
            int act,id;
            id = congreso.getId_congreso();
            nomb = congreso.getNombre();
            pais = congreso.getPais();
            act = congreso.getActivo();           
                    
            
            String sentencia = "INSERT INTO CONGRESO (ID_CONGRESO,NOMBRE,PAIS,ACTIVO,FECHA_INICIO,FECHA_FIN)"
                    + "VALUES(?,?,?,?,?,?,?)";
           
            PreparedStatement ps = con.prepareStatement(sentencia);
            ps.setInt(1, id);
            ps.setString(2, nomb);
            ps.setString(3, pais);
            ps.setInt(4, act);
            ps.setDate(5,new java.sql.Date(congreso.getFecha_ini().getTime()));
            ps.setDate(6,new java.sql.Date(congreso.getFecha_fin().getTime()));
            
           
            System.out.println("Se ingreso nuevo dato");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        
        
        return 0;
    }
    
    
    public int listar(ArrayList<Congreso> congresos) {           
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.
                    getConnection("jdbc:mysql://" +
                    "lp2mysql.cmqt7vdk6ppz.us-east-1.rds.amazonaws.com"
                            + ":3306/inf282","admin","abcd1234");
           
            String sentencia = "SELECT * FROM CONGRESO";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentencia);
            while(rs.next()){
                Congreso congreso = new Congreso();
                congreso.setId_congreso(rs.getInt("ID_CONGRESO"));
                congreso.setNombre(rs.getString("NOMBRE"));
                congreso.setFecha_ini(rs.getDate("FECHA_INICIO"));
                congreso.setFecha_fin(rs.getDate("FECHA_FIN"));
                congreso.setPais(rs.getString("PAIS"));
                congreso.setActivo(rs.getInt("ACTIVO"));
                congresos.add(congreso);               
                
            }
            System.out.println("Se obtuvo datos con exito");
            
            con.close();
            
        }catch(Exception ex){
            
            System.out.println(ex.getMessage());
            
        }
        return 0;
    }
        
    
 
    
}
