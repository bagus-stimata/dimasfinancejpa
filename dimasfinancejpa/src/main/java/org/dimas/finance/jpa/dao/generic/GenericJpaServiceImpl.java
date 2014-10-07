/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dimas.finance.jpa.dao.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author bagus
 */
public class GenericJpaServiceImpl<T, ID extends Serializable> implements GenericJpaService<T, ID> {
	private static final String PERSISTENCE_UNIT = "financePU";
	private static EntityManagerFactory factory;
	
	private final Class<T> persistentClass;



    public EntityManagerFactory getFactory() {
        return factory;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }
       
    @SuppressWarnings("unchecked")
    public GenericJpaServiceImpl() {
            this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                            .getGenericSuperclass()).getActualTypeArguments()[0];
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    }

    @Override
    public List<T> findAll() throws DataAccessException {    	
       EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            String query = "SELECT a From " + persistentClass.getSimpleName() + " a";
            List<T> list = em.createQuery(query)
            		.setHint(QueryHints.REFRESH, HintValues.TRUE)
            		.getResultList();
            em.getTransaction().commit();
            return list;
        } catch (PersistenceException exception) {
            em.getTransaction().rollback();
            throw exception;
        } finally {
            em.close();
        }    
        
    }

    @Override
    public T findById(Serializable ID) throws DataAccessException {
            EntityManager em = factory.createEntityManager();            
            try {
                em.getTransaction().begin();
                final T result = (T) em.find(persistentClass, ID);
                em.getTransaction().commit();
                return result;
            } catch (PersistenceException exception) {
                em.getTransaction().rollback();
                throw exception;
            } finally {
                em.close();
            }    
        
    }

    @Override
    public void createObject(T domain) throws DataAccessException {
            EntityManager em = factory.createEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(domain);
                em.getTransaction().commit();               
            } catch (PersistenceException exception) {
                em.getTransaction().rollback();
                throw exception;
            } finally {
                em.close();
            }    
    }

    @Override
    public void updateObject(T domain) throws DataAccessException {
            EntityManager em = factory.createEntityManager();
            try {
                em.getTransaction().begin();
                em.merge(domain);
                em.getTransaction().commit();
            } catch (PersistenceException exception) {
                em.getTransaction().rollback();
                throw exception;
            } finally {
                em.close();
            }    
    }

    @Override
    public void removeObject(T domain) throws DataAccessException {
            EntityManager em = factory.createEntityManager();
            try {
                em.getTransaction().begin();
                //em.remove(em.contains(domain) ? domain : em.merge(domain));         
                em.remove(em.merge(domain));
                em.getTransaction().commit();
            } catch (PersistenceException exception) {
                em.getTransaction().rollback();
                throw exception;
            } finally {
                em.close();
            }    
    }
    
    @Override
    public List<T> findAllById(Serializable ID) throws DataAccessException {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            final List<T> list = (List<T>) em.find(persistentClass, ID);
            em.getTransaction().commit();
            return list;
        } catch (PersistenceException pex){
            em.getTransaction().rollback();
            throw pex;
        } finally{
            em.close();
        }
    }

	@Override
	public long count() throws DataAccessException {
	       EntityManager em = factory.createEntityManager();
	        try {
	            em.getTransaction().begin();
	            
	            String query = "SELECT a From " + persistentClass.getSimpleName() + " a";
	            long count = em.createQuery(query)
	            		.setHint(QueryHints.REFRESH, HintValues.TRUE)
	            		.getResultList().size();
	            em.getTransaction().commit();
	            return count;
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }    
	}

    
}
