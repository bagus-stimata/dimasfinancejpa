package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.blitar.bawasda.simhp.model.Pegawai;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaServiceImpl;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class PegawaiJpaServiceImpl extends GenericJpaServiceImpl<Pegawai, Serializable> implements PegawaiJpaService{

	@Override
	public List<Pegawai> findAll(String strId) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Pegawai a "
	            		+ " WHERE "
	            		+ " a.id LIKE :theId ";
	            
	            List<Pegawai> list = em.createQuery(query)
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
	public List<Pegawai> findAllActive() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Pegawai a "
	            		+ " WHERE "
	            		+ " a.statusActive = :activeValue ";
	            
	            List<Pegawai> list = em.createQuery(query)
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