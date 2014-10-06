package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.blitar.bawasda.simhp.model.PegawaiJabatan;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaServiceImpl;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class PegawaiJabatanJpaServiceImpl extends GenericJpaServiceImpl<PegawaiJabatan, Serializable> implements PegawaiJabatanJpaService{

	@Override
	public List<PegawaiJabatan> findAll(String strId) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From PegawaiJabatan a "
	            		+ " WHERE "
	            		+ " a.id LIKE :theId ";
	            
	            List<PegawaiJabatan> list = em.createQuery(query)
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
	public List<PegawaiJabatan> findAllActive() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From PegawaiJabatan a "
	            		+ " WHERE "
	            		+ " a.statusActive = :activeValue ";
	            
	            List<PegawaiJabatan> list = em.createQuery(query)
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
