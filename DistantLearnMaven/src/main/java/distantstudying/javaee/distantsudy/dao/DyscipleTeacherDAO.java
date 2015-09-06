/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantstudying.javaee.distantsudy.dao;

import distantstudying.javaee.distantsudy.entities.DyscipleTeacher;
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
public class DyscipleTeacherDAO implements EntityDAO<DyscipleTeacher>{
    
    private ConnectorDB connectorDB;
    private String tableName;
    
    public DyscipleTeacherDAO () {
        this.connectorDB = new ConnectorDB();
        this.tableName = "dysciple_teacher";
    }
    
    @Override
    public void create(DyscipleTeacher entity) throws SQLException {
        int newId = this.generateId();
        
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "insert into `" + this.tableName + "` " +
                        "(id, id_teacher, id_dysciple)" +
                        " values (?, ?, ?)";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, newId );
        preparedStmt.setInt(2, entity.getIdTeacher());
        preparedStmt.setInt(3, entity.getIdDysciple());
        
        int rowsInserted = preparedStmt.executeUpdate();
        this.revisQueryResult( rowsInserted );
        
        conn.close();
    }

    @Override
    public DyscipleTeacher getById(int id) throws SQLException {
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "select id, id_teacher, id_dysciple from `" + this.tableName + "` "+
                       "where `id`=" + id;
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery( query );
        DyscipleTeacher ds = new DyscipleTeacher();
        
        while ( rs.next() ) {
            ds.setId( rs.getInt("id") );    
            ds.setIdTeacher(rs.getInt("id_teacher") );
            ds.setIdDysciple(rs.getInt("id_dysciple") );
        }
        conn.close();
        
        return ds;
    }

    @Override
    public void update(DyscipleTeacher entity) throws SQLException {
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "update `" + this.tableName + "` " +
                        " set id_teacher=?, id_dysciple=? " +
                        " where id="+ entity.getId();

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        
        preparedStmt.setInt(1, entity.getIdTeacher());
        preparedStmt.setInt(2, entity.getIdDysciple());
 
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
        
        String query = "select  `id`, `id_teacher`, `id_dysciple` from `" + this.tableName + "`";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery( query );
        
        List<DyscipleTeacher> dysciples = new ArrayList<DyscipleTeacher>();
        while ( rs.next() ) {
            DyscipleTeacher ds = new DyscipleTeacher();
            ds.setId( rs.getInt("id") );    
            ds.setIdTeacher(rs.getInt("id_teacher") );
            ds.setIdDysciple(rs.getInt("id_dysciple") ); 
                       
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
