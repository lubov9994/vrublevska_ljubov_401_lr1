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
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TeacherManager implements EntityDAO<Teacher> {
    
    private EntityManager entityManager;
    
    public TeacherManager() {
        entityManager = getEntityManager();
    }
    
    public EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( NAME_UNIT );
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
        
    @Override
    public void create(Teacher entity) throws SQLException {
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
    public Teacher getById(int id) throws SQLException {
        return entityManager.find(Teacher.class, id);
    }

    @Override
    public void update( Teacher entity) throws SQLException {
        
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
    public List<Teacher> getAll() throws SQLException {
//        String query = "select  c.id, c.first_name, c.second_name, c.last_name, c.birthday, c.degree, c.kafedra"
//                + " from " + Teacher.class.getSimpleName() + " c ";
        String query = "select  c "
                + " from " + Teacher.class.getSimpleName() + " c ";
        
        return entityManager.createQuery( query ).getResultList();
    }
    
    private int generateId () throws SQLException {
        String query = "select max( c.id ) as id from " + Teacher.class.getSimpleName() + " c ";
        int max = (int) entityManager.createQuery(query).getSingleResult();   
        
        return max + 1;
    }
 
}
