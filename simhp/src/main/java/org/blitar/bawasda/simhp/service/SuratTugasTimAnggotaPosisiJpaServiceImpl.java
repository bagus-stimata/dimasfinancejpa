package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.blitar.bawasda.simhp.model.SuratTugasTimAnggotaPosisi;
import org.blitar.bawasda.simhp.model.TabelJenisKerugian;
import org.blitar.bawasda.simhp.service.generic.GenericJpaServiceImpl;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class SuratTugasTimAnggotaPosisiJpaServiceImpl extends GenericJpaServiceImpl<SuratTugasTimAnggotaPosisi, Serializable> implements SuratTugasTimAnggotaPosisiJpaService{

	@Override
	public List<SuratTugasTimAnggotaPosisi> findAll(String strId) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From SuratTugasTimAnggotaPosisi a "
	            		+ " WHERE "
	            		+ " a.id LIKE :theId ";
	            
	            List<SuratTugasTimAnggotaPosisi> list = em.createQuery(query)
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
	public List<SuratTugasTimAnggotaPosisi> findAllActive() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From SuratTugasTimAnggotaPosisi a "
	            		+ " WHERE "
	            		+ " a.statusActive = :activeValue ";
	            
	            List<SuratTugasTimAnggotaPosisi> list = em.createQuery(query)
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
