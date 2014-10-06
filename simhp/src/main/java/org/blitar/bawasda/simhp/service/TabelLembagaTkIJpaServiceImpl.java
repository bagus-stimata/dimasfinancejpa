package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.blitar.bawasda.simhp.model.TabelLembagaTkI;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaServiceImpl;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class TabelLembagaTkIJpaServiceImpl extends GenericJpaServiceImpl<TabelLembagaTkI, Serializable> implements TabelLembagaTkIJpaService{

	@Override
	public List<TabelLembagaTkI> findAll(String strId) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From TabelLembagaTkI a "
	            		+ " WHERE "
	            		+ " a.id LIKE :theId ";
	            
	            List<TabelLembagaTkI> list = em.createQuery(query)
	            		.setParameter("theId", strId)
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
	public List<TabelLembagaTkI> findAllHasChild() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From TabelLembagaTkI a "
	            		+ " WHERE "
	            		+ " a.hasTkII = :bolValue ";
	            
	            List<TabelLembagaTkI> list = em.createQuery(query)
	            		.setParameter("bolValue", true)
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

}
