/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantstudying;

import distantstudying.javaee.distantsudy.dao.DyscipleDAO;
import distantstudying.javaee.distantsudy.dao.DyscipleManager;
import distantstudying.javaee.distantsudy.dao.DyscipleTeacherDAO;
import distantstudying.javaee.distantsudy.dao.DyscipleTeacherManager;
import distantstudying.javaee.distantsudy.dao.TeacherDAO;
import distantstudying.javaee.distantsudy.dao.TeacherManager;
import distantstudying.javaee.distantsudy.entities.Dysciple;
import distantstudying.javaee.distantsudy.entities.DyscipleTeacher;
import distantstudying.javaee.distantsudy.entities.Teacher;
import java.io.IOException;
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
    public static void main(String[] args) throws IOException {
        DistantStudyingAPI dAPI = new DistantStudyingAPI();
        try {
            dAPI.runMenu();
        } catch (SQLException ex) {
            System.out.println("ERROR" + ex.getMessage());
        }
        
    }
    
}
