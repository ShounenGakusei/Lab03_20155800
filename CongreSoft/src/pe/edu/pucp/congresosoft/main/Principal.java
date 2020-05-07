/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.congresosoft.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pe.edu.pucp.congresosoft.dao.CongresoDAO;
import pe.edu.pucp.congresosoft.mysql.CongresoMySQL;
import pe.edu.pucp.congresosoft.model.Congreso;


/**
 *
 * @author David
 */
public class Principal {
    
    public static void main(String[] args) throws ParseException{
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Congreso congresoADD = new Congreso("INTERACT_2021",
                sdf.parse("01-01-2021"),sdf.parse("05-01-2021"),"BOLIVIA",0);
        
        congresoADD.setId_congreso(6);
        
        ArrayList<Congreso> congresos = new ArrayList<>();
        
        CongresoMySQL congreso = new CongresoMySQL();
        int a = congreso.listar(congresos);
        for(Congreso c : congresos)
            c.imprimir();
        
        congreso.insertar(congresoADD);
        
    }
    
    
}
