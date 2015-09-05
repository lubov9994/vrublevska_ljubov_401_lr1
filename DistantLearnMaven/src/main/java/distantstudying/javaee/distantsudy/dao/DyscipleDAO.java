/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantstudying.javaee.distantsudy.dao;

import distantstudying.javaee.distantsudy.entities.Dysciple;
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
public class DyscipleDAO implements EntityDAO<Dysciple>{
    
    private ConnectorDB connectorDB;
    private String tableName;
    
    public DyscipleDAO () {
        this.connectorDB = new ConnectorDB();
        this.tableName = "dysciple";
    }
    
    @Override
    public void create(Dysciple entity) throws SQLException {
        int newId = this.generateId();
        
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "insert into `" + this.tableName + "` " +
                        "(id, name, direction)" +
                        " values (?, ?, ?)";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, newId );
        preparedStmt.setString(2, entity.getName() );
        preparedStmt.setString(3, entity.getDirection() );

        // execute the preparedstatement
        int rowsInserted = preparedStmt.executeUpdate();
        this.revisQueryResult( rowsInserted );
        
        conn.close();
    }

    @Override
    public Dysciple getById(int id) throws SQLException {
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "select `id`, `name`, `direction`  from `" + this.tableName + "` "+
                       "where `id`=" + id;

        // create the mysql insert preparedstatement
        Statement stmt = conn.createStatement();

        // execute the preparedstatement
        ResultSet rs = stmt.executeQuery( query );
        Dysciple ds = new Dysciple();
        
        while ( rs.next() ) {
            ds.setId( rs.getInt("id") );    
            ds.setName(rs.getString("name") );
            ds.setDirection(rs.getString("direction") );
        }
        conn.close();
        
        return ds;
    }

    @Override
    public void update(Dysciple entity) throws SQLException {
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "update `" + this.tableName + "` " +
                        " set name=?, direction=?" +
                        " where id="+ entity.getId();

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString (1, entity.getName() );
        preparedStmt.setString(2, entity.getDirection() );
 
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
        
        String query = "select  `id`, `name`, `direction` from `" + this.tableName + "`";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery( query );
        
        List<Dysciple> dysciples = new ArrayList<Dysciple>();
        while ( rs.next() ) {
            Dysciple ds = new Dysciple();
            ds.setId( rs.getInt("id") );    
            ds.setName(rs.getString("name") );
            ds.setDirection(rs.getString("direction") );
            
            dysciples.add( ds );
        }
        
        conn.close();
        return dysciples;
    }
    
    private int generateId () throws SQLException {
        int max = -1;
        Connection conn = this.connectorDB.createConnection();        
        
        String query = "select max( `id` ) as `id` from `" + this.tableName + "`";

        // create the mysql insert preparedstatement
        Statement stmt = conn.createStatement();

        // execute the preparedstatement
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
