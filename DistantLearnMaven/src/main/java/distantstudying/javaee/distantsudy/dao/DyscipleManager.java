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

import distantstudying.javaee.distantsudy.entities.Dysciple;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DyscipleManager implements EntityDAO<Dysciple> {
    
    private EntityManager entityManager;
    
    public DyscipleManager() {
        entityManager = getEntityManager();
    }
    public EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( NAME_UNIT );
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
        
    @Override
    public void create(Dysciple entity) throws SQLException {
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

//        entityManager.close();
    }

    @Override
    public Dysciple getById(int id) throws SQLException {
        return entityManager.find(Dysciple.class, id);
    }

    @Override
    public void update( Dysciple entity) throws SQLException {
        
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
    public List<Dysciple> getAll() throws SQLException {
        String query = "select c from " +  Dysciple.class.getSimpleName() + " c ";
        
        return entityManager.createQuery( query ).getResultList();
    }
    
    private int generateId () throws SQLException {
        String query = "select max( c.id ) as id from " + Dysciple.class.getSimpleName() + " c ";
        int max = (int) entityManager.createQuery(query).getSingleResult();   
        
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
