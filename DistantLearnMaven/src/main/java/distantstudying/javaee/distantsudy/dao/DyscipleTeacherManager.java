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

import distantstudying.javaee.distantsudy.entities.DyscipleTeacher;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DyscipleTeacherManager implements EntityDAO<DyscipleTeacher> {
    
    private EntityManager entityManager;
    
    public DyscipleTeacherManager() {
        entityManager = getEntityManager();
    }
    public EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( NAME_UNIT );
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
        
    @Override
    public void create(DyscipleTeacher entity) throws SQLException {
        int newId = generateId();
        entity.setId(newId);
        
        try {
            entityManager.getTransaction().begin();
            entityManager.persist( entity );
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new SQLException( e );
        }
        
    }

    @Override
    public DyscipleTeacher getById(int id) throws SQLException {
        return entityManager.find(DyscipleTeacher.class, id);
    }

    @Override
    public void update( DyscipleTeacher entity) throws SQLException {

        try {
            entityManager.getTransaction().begin();
            entityManager.merge( entity );
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new SQLException( e );
        }

    }

    @Override
    public boolean delete(int id) throws SQLException {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove( getById( id ) );
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            throw new SQLException( e );
        }
        
        return true;
    }

    @Override
    public List<DyscipleTeacher> getAll() throws SQLException {
        String query = "select c from " +  DyscipleTeacher.class.getSimpleName() + " c ";
        
        return entityManager.createQuery( query ).getResultList();
    }
    
    private int generateId () throws SQLException {
        String query = "select max( c.id ) as id from " + DyscipleTeacher.class.getSimpleName() + " c ";
        int max = (int) entityManager.createQuery(query).getSingleResult();   
        
        return max + 1;
    }
 
}
