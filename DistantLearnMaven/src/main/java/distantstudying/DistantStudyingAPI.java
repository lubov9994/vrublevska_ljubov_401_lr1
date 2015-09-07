/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantstudying;

/**
 *
 * @author Администратор
 */

import distantstudying.javaee.distantsudy.dao.DyscipleDAO;
import distantstudying.javaee.distantsudy.dao.DyscipleManager;
import distantstudying.javaee.distantsudy.dao.DyscipleTeacherManager;
import distantstudying.javaee.distantsudy.dao.EntityDAO;
import distantstudying.javaee.distantsudy.dao.TeacherManager;
import distantstudying.javaee.distantsudy.entities.Dysciple;
import distantstudying.javaee.distantsudy.entities.DyscipleTeacher;
import distantstudying.javaee.distantsudy.entities.Teacher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Администратор
 */

public class DistantStudyingAPI {
    
//    private ArrayList<String> menuItems;
    private ArrayList<Integer> steps;
//    private final DyscipleManager dyscipleMan;
//    private final TeacherManager teacherMan;
//    private final DyscipleTeacherManager dtMan;
    private final EntityDAO<Dysciple> dyscipleMan;
    private final EntityDAO<Teacher> teacherMan;
    private final EntityDAO<DyscipleTeacher> dtMan;
    
    public DistantStudyingAPI(){
        this.steps = new ArrayList<>();
//        this.dyscipleMan = new DyscipleManager();
//        this.teacherMan = new TeacherManager();
//        this.dtMan = new DyscipleTeacherManager();
        
        this.dyscipleMan = new DyscipleDAO();
        this.teacherMan = new TeacherManager();
        this.dtMan = new DyscipleTeacherManager();
    }
    
     public void runMenu() throws SQLException, IOException{
        int choise;
        System.out.println("<<<Дистанційне навчання>>>\n");
        this.chooseMenuItem(0); 
        choise = getFeedBack();
        
        if( choise == 1 ){// закріпити за викладачем
            
            int teacherId = this.chooseTeacher();
            while ( teacherId == -1 ) {
                teacherId = this.chooseTeacher();
            }
            
            int dyscipleId = this.chooseDysciple();
            while ( dyscipleId == -1 ) {
                dyscipleId = this.chooseDysciple();
            }
            // подтвердить
            this.chooseMenuItem(3); 
            choise = getFeedBack();
            
            if( choise == 1 ) {
                this.dtMan.create(
                            new DyscipleTeacher( 0, dyscipleId, teacherId)
                        );
             
            } else {
                this.restartMenu();
            }
            
        } else if ( choise == 2 ) { // Інaше
            chooseMenuItem(4);
            choise = getFeedBack();
            
            if( choise == 1 ){//змінити Викладачів
                
                chooseMenuItem(5);// Operations
                choise = getFeedBack();
                
                if ( choise == 1){ // add
                    Teacher teacher = generateTeacher( new Teacher() );
                    teacherMan.create(teacher);
                    
                } else if (choise == 2){ // upd
                    int teacherId = chooseTeacher();
                    while ( teacherId == -1){
                        teacherId = chooseTeacher();
                    }
                  
                    Teacher teacher = generateTeacher( 
                                        teacherMan.getById( teacherId ));
                    
                    // подтвердить
                    this.chooseMenuItem(3); 
                    choise = getFeedBack();

                    if( choise == 1 ) {
                        System.out.println("Поле оновлено");
                        teacherMan.update( teacher );
                       
                    } else {
                        this.restartMenu();
                    }
                } else if (choise == 3){ // del
                    int teacherId = chooseTeacher();
                    while ( teacherId == -1){
                        teacherId = chooseTeacher();
                    }
                    // подтвердить
                    this.chooseMenuItem(3); 
                    choise = getFeedBack();

                    if( choise == 1 ) {
                        teacherMan.delete( teacherId );
                        
                    } else {
                        this.restartMenu();
                        
                    }
                } else {
                    this.restartMenu();
                }

            } else if ( choise == 2 ) {
                //змінити Дисциплини
                chooseMenuItem(5);// Operations
                choise = getFeedBack();
                
                if ( choise == 1){ // add
                    Dysciple dysciple = generateDysciple( new Dysciple() );
                    
                    // подтвердить
                    this.chooseMenuItem(3); 
                    choise = getFeedBack();
                    
                    if( choise == 1 ) {
                        dyscipleMan.create(dysciple);
                        
                    }
                } else if (choise == 2){ // upd
                    int dyscipleId = chooseDysciple();
                    while ( dyscipleId == -1){
                        dyscipleId = chooseDysciple();
                    }
                  
                    Dysciple dysciple = generateDysciple(
                                        dyscipleMan.getById( dyscipleId ));
                    
                    // подтвердить
                    this.chooseMenuItem(3); 
                    choise = getFeedBack();

                    if( choise == 1 ) {
                        dyscipleMan.update(dysciple );
                       
                    } else {
                        this.restartMenu();
                    }
                } else if (choise == 3){ // del
                    int dyscipleId = chooseDysciple();
                    while ( dyscipleId == -1){
                        dyscipleId = chooseDysciple();
                    }
                    // подтвердить
                    this.chooseMenuItem(3); 
                    choise = getFeedBack();

                    if( choise == 1 ) {
                        dyscipleMan.delete( dyscipleId );
                        
                    } else {
                        this.restartMenu();
                    }
                } else {
                    this.restartMenu();
                }

            } else {
                this.restartMenu();
            }   
            
        } else {
            this.restartMenu();
        }      
        
        System.out.println(" Thank you, goodbay! ");  
    }
    
    private boolean chooseMenuItem( int key){
       
        switch (key) {
            case 0 : 
                this.steps.add(key);
                System.out.println("1 - Закріпити за викладачем навчальну дисципліну");
                System.out.println("2 - Інше...");
                break;
            case 1 :
                this.steps.add(key);
                System.out.println("1 - Вказати викладача");
                System.out.println("2 - Всі викладачі");
                break;
            case 2 :
                this.steps.add(key);
                System.out.println("1 - Вказати дисципліну");
                System.out.println("2 - Всі дисципліни");
                break;
            case 3 :
                this.steps.add(key);
                System.out.println("1 - Підтвердити");
                System.out.println("2 - Скасувати / any key");
                break;   
            // Інакше
            case 4 :
                this.steps.add(key);
                System.out.println("1 - Змінити таблицю Викладачів");
                System.out.println("2 - Змінити таблицю Дисциплін");
                break; 
            // операції
            case 5 :
                this.steps.add(key);
                System.out.println("1 - Додати запис");
                System.out.println("2 - Оновити запис");
                System.out.println("3 - Видалити запис");
                break;    
            // викладачі
            case 6 :
                this.steps.add(key);
                System.out.println("1 - Ім'я");
                System.out.println("2 - Пропустити");
                break;
            case 7 :
                this.steps.add(key);
                System.out.println("1 - По батькові");
                System.out.println("2 - Пропустити");
                break;
            case 8 :
                this.steps.add(key);
                System.out.println("1 - Прізвище");
                System.out.println("2 - Пропустити");
                break;
            case 9 :
                this.steps.add(key);
                System.out.println("1 - День народження (YYYY-MM-DD)");
                System.out.println("2 - Пропустити");
                break;
            case 10 :
                this.steps.add(key);
                System.out.println("1 - Cтупітнь");
                System.out.println("2 - Пропустити");
                break;
            case 11 :
                this.steps.add(key);
                System.out.println("1 - Кафедра");
                System.out.println("2 - Пропустити");
                break;
            // Дисциплыни
            case 12 :
                this.steps.add(key);
                System.out.println("1 - Назва");
                System.out.println("2 - Пропустити");
                break;
            case 13 :
                this.steps.add(key);
                System.out.println("1 - Напрям");
                System.out.println("2 - Пропустити");
                break;
            case 14 :
                this.steps.add(key);
                System.out.println("Вводьте");
                break;
            default:
                return false;
        }
        
        return true;
    }
    
    private int getFeedBack(){
        Scanner in = new Scanner(System.in);
        
        return in.nextInt();
    }
    
    private String getFeedBackStr() throws UnsupportedEncodingException, IOException{
        //Scanner in = new Scanner(System.in);
        BufferedReader in = new BufferedReader(
                    new InputStreamReader(System.in, "UTF-8"));
        return in.readLine();
    }
    
    private void restartMenu() throws SQLException, IOException{
        this.steps = new ArrayList<>();
        this.runMenu();
    }
    
    private int chooseTeacher() throws SQLException{
        this.chooseMenuItem(1);
        int choise = getFeedBack();
        
        if( choise == 1 ){
            this.chooseMenuItem(14);
            int id = getFeedBack();
            
            if ( revisTeacherId( id ) ) {
                return id;
            } 
        } else if ( choise == 2 ) {
            System.out.println( this.teacherMan.getAll().toString() ); 
        } 
        
        return -1;
    }
    
    private int chooseDysciple() throws SQLException{
        this.chooseMenuItem(2);
        int choise = getFeedBack();
          
        if( choise == 1 ){
            this.chooseMenuItem(14);
            int id = this.getFeedBack();
            
            if ( revisDyscipleId( id ) ) {
                return id;
            }

        } else if ( choise == 2 ) {
            System.out.println( dyscipleMan.getAll() );
        } 
        
        return -1;
    }
    
    private boolean revisDyscipleId ( int id ) throws SQLException{
        if ( this.dyscipleMan.getById(id) != null && this.dyscipleMan.getById(id).getId() != null ){
            return true;
        }
        
        return false;
    }
    
    private boolean revisTeacherId ( int id ) throws SQLException{
        if ( this.teacherMan.getById(id) != null && this.teacherMan.getById(id).getId() != null ){
            return true;
        }
        
        return false;
    }
     
    private Dysciple generateDysciple( Dysciple d) throws IOException {
        chooseMenuItem(12); 
        if( getFeedBack() == 1 ){
            chooseMenuItem(14);
            d.setName( getFeedBackStr() );
        }
        
        chooseMenuItem(13); 
        if( getFeedBack() == 1 ){
            chooseMenuItem(14);
            d.setDirection( getFeedBackStr() );
        }
         
        return d;
    }

    private Teacher generateTeacher( Teacher t) throws IOException {

        chooseMenuItem(6); 
        if( getFeedBack() == 1 ){
            chooseMenuItem(14);
            t.setFirstName( getFeedBackStr() );
        }
        
        chooseMenuItem(7); 
        if( getFeedBack() == 1 ){
            chooseMenuItem(14);
            t.setSecondName(getFeedBackStr() );
        }
        
        chooseMenuItem(8);  
        if( getFeedBack() == 1 ){
            chooseMenuItem(14);
            t.setLastName(getFeedBackStr() );
        }
        
        chooseMenuItem(9); 
        if( getFeedBack() == 1 ){
            chooseMenuItem(14);
            t.setBirthday( getFeedBackStr() );
        }
        
        chooseMenuItem(10); 
        if( getFeedBack() == 1 ){
            chooseMenuItem(14);
            t.setDegree(getFeedBack() );
        }
        
        chooseMenuItem(11);
        
        if( getFeedBack() == 1 ){
            chooseMenuItem(14);
            t.setDegree(getFeedBack() );
        }
         
        return t;
    }
}
