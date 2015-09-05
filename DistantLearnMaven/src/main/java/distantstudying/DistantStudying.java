/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantstudying;

import distantstudying.javaee.distantsudy.dao.DyscipleDAO;
import distantstudying.javaee.distantsudy.dao.DyscipleManager;
import distantstudying.javaee.distantsudy.entities.Dysciple;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Администратор
 */
public class DistantStudying {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello");
        Dysciple dysciple = new Dysciple( 5, "ТСУ", "описание");
        Dysciple dysciple2 = new Dysciple( 5, "English", "Study it now");
        System.out.println( dysciple.toString() );
        
        DyscipleDAO discDAO = new DyscipleDAO();
        DyscipleManager discMan = new DyscipleManager();
        try {
            
//            discDAO.create( dysciple );           
//            dysciple.setName("Math");
//            System.out.println("1 d="+dysciple.toString());
//            
//            dysciple.setId(4);
//            discDAO.update( dysciple );
//            
//            Dysciple d = discDAO.getById( 5 );
//            System.out.println("2 d="+d.toString());
//            System.out.println(" List "+ discDAO.getAll().toString());
            
//            discDAO.delete(4);
            
            System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");
            
//            discMan.create(dysciple2);
//            dysciple.setId(4);
//            dysciple.setDirection("Multic");
//            discMan.update(dysciple);
//            Dysciple d = discMan.getById( 5 );
//            System.out.println("2 d="+d.toString());
//            System.out.println(" List "+ discMan.getAll().toString());
            System.out.println(discMan.delete(4));
            System.out.println(" ");System.out.println(" ");System.out.println(" ");System.out.println(" ");
            
        } catch (SQLException ex) {
            System.err.println(" Can not create dyscipline " + ex);
        }
        
    }
    
}
