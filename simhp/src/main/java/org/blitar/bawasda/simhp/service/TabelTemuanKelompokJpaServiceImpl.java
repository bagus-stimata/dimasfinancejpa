package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.generic.GenericJpaServiceImpl;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class TabelTemuanKelompokJpaServiceImpl extends GenericJpaServiceImpl<TabelTemuanKelompok, Serializable> implements TabelTemuanKelompokJpaService{

	@Override
	public List<TabelTemuanKelompok> findAll(String strId) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From TabelTemuanKelompok a "
	            		+ " WHERE "
	            		+ " a.id LIKE :theId ";
	            
	            List<TabelTemuanKelompok> list = em.createQuery(query)
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
	public List<TabelTemuanKelompok> findAllActive() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From TabelTemuanKelompok a "
	            		+ " WHERE "
	            		+ " a.statusActive = :activeValue ";
	            
	            List<TabelTemuanKelompok> list = em.createQuery(query)
	            		.setParameter("activeValue", true)
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
