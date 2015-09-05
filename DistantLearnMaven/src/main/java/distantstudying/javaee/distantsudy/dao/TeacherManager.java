/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distantstudying.javaee.distantsudy.dao;

/**
 *
 * @author Администратор
 */

import distantstudying.javaee.distantsudy.entities.Teacher;
//import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TeacherManager implements EntityDAO<Teacher> {
    
    private String tableName;
    private EntityManager entityManager;
    
    public TeacherManager() {
        this.tableName = "dysciple";
        this.entityManager = this.getEntityManager();
    }
    public EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( this.NAME_UNIT );
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
        
    @Override
    public void create(Teacher entity) throws SQLException {
        int newId = this.generateId();
        entity.setId(newId);
        
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist( entity );
            this.entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            this.entityManager.getTransaction().rollback();
            throw new SQLException( e );
        }

//        this.entityManager.close();
    }

    @Override
    public Teacher getById(int id) throws SQLException {
        return this.entityManager.find(Teacher.class, id);
    }

    @Override
    public void update( Teacher entity) throws SQLException {
        
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.merge( entity );
            this.entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            this.entityManager.getTransaction().rollback();
            throw new SQLException( e );
        }

    }

    @Override
    public boolean delete(int id) throws SQLException {
        
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove( this.getById( id ) );
            this.entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            this.entityManager.getTransaction().rollback();
            throw new SQLException( e );
        }
        
        return true;
    }

    @Override
    public List<Teacher> getAll() throws SQLException {
        String query = "select  c.id, c.name, c.direction from " +  Teacher.class.getSimpleName() + " c ";
        
        return this.entityManager.createQuery( query ).getResultList();
    }
    
    private int generateId () throws SQLException {
        String query = "select max( c.id ) as id from " + Teacher.class.getSimpleName() + " c ";
        int max = (int) this.entityManager.createQuery(query).getSingleResult();   
        
        return max + 1;
    }
 
}
