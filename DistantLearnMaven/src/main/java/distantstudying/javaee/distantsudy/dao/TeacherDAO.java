/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantstudying.javaee.distantsudy.dao;

import distantstudying.javaee.distantsudy.entities.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Администратор
 */
public class TeacherDAO implements EntityDAO<Teacher>{
    
    private ConnectorDB connectorDB;
    private String tableName;
    
    public TeacherDAO () {
        this.connectorDB = new ConnectorDB();
        this.tableName = "teacher";
    }
    
    @Override
    public void create(Teacher entity) throws SQLException {
        int newId = this.generateId();
        
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "insert into `" + this.tableName + "` " +
                        "(id, first_name, second_name, last_name, birthday, degree, kafedra)" +
                        " values (?, ?, ?, ?, ?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, newId );
        preparedStmt.setString(2, entity.getFirstName());
        preparedStmt.setString(3, entity.getSecondName());
        preparedStmt.setString(4, entity.getLastName());
        preparedStmt.setString(5, entity.getBirthday());
        preparedStmt.setInt(6, entity.getDegree());
        preparedStmt.setInt(7, entity.getKafedra());
        // execute the preparedstatement
        int rowsInserted = preparedStmt.executeUpdate();
        this.revisQueryResult( rowsInserted );
        
        conn.close();
    }

    @Override
    public Teacher getById(int id) throws SQLException {
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "select id, first_name, second_name, last_name, birthday, degree, kafedra  from `" + this.tableName + "` "+
                       "where `id`=" + id;

        // create the mysql insert preparedstatement
        Statement stmt = conn.createStatement();
        // execute the preparedstatement
        ResultSet rs = stmt.executeQuery( query );
        Teacher ds = new Teacher();
        
        while ( rs.next() ) {
            ds.setId( rs.getInt("id") );    
            ds.setFirstName(rs.getString("first_name") );
            ds.setSecondName(rs.getString("second_name") );
            ds.setLastName(rs.getString("last_name") );            
            ds.setBirthday(rs.getString("birthday") );
            ds.setDegree(rs.getInt("degree") );
            ds.setKafedra(rs.getInt("kafedra") );
        }
        conn.close();
        
        return ds;
    }

    @Override
    public void update(Teacher entity) throws SQLException {
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "update `" + this.tableName + "` " +
                        " set first_name=?, second_name=?, last_name=?, birthday=?, degree=?, kafedra=? " +
                        " where id="+ entity.getId();

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        
        preparedStmt.setString(1, entity.getFirstName());
        preparedStmt.setString(2, entity.getSecondName());
        preparedStmt.setString(3, entity.getLastName());
        preparedStmt.setString(4, entity.getBirthday());
        preparedStmt.setInt(5, entity.getDegree());
        preparedStmt.setInt(6, entity.getKafedra());
 
        int rowsInserted = preparedStmt.executeUpdate();
        this.revisQueryResult( rowsInserted );
        conn.close();
    }

    @Override
    public boolean delete(int id) throws SQLException {
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "delete from `" + this.tableName + "` " +
                       "where id="+ id;

        Statement stmt = conn.createStatement();
        int rowsInserted = stmt.executeUpdate(query);
        conn.close();
        
        return true;
    }

    @Override
    public List getAll() throws SQLException {
        
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "select  `id`, `first_name`, `second_name`, `last_name`, `birthday`, `degree`, `kafedra` from `" + this.tableName + "`";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery( query );
        
        List<Teacher> dysciples = new ArrayList<Teacher>();
        while ( rs.next() ) {
            Teacher ds = new Teacher();
             ds.setId( rs.getInt("id") );    
            ds.setFirstName(rs.getString("first_name") );
            ds.setSecondName(rs.getString("second_name") );
            ds.setLastName(rs.getString("last_name") );            
            ds.setBirthday(rs.getString("birthday") );
            ds.setDegree(rs.getInt("degree") );
            ds.setKafedra(rs.getInt("kafedra") );
            
            dysciples.add( ds );
        }
        
        conn.close();
        return dysciples;
    }
    
    private int generateId () throws SQLException {
        int max = -1;
        Connection conn = this.connectorDB.createConnection();        
        String query = "select max( `id` ) as `id` from `" + this.tableName + "`";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery( query );
     
        while ( rs.next() ) {
            max = rs.getInt("id");           
        }
        
        conn.close();
        
        return max + 1;
    }
    
    private void revisQueryResult( int rowsInserted ){
        if (rowsInserted > 0) {
            System.out.println("The query execution was successfull!");
        } else {
            System.err.println("The query execution was not successfull!");
        }
    }
    
}
