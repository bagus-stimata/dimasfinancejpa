package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.blitar.bawasda.simhp.model.Sysvar;
import org.blitar.bawasda.simhp.service.generic.GenericJpaServiceImpl;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class SysvarJpaServiceImpl extends GenericJpaServiceImpl<Sysvar, Serializable> implements SysvarJpaService{

	@Override
	public Sysvar findById(String stringId) {
	       EntityManager em = getFactory().createEntityManager();
           Sysvar sysvar = new Sysvar();
	       
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Sysvar a WHERE a.idSysvar LIKE :idSysvar";
	            Object item = null;
	            
	            try {
		            item = em.createQuery(query)
		            		.setParameter("idSysvar", stringId)
		            		.setHint(QueryHints.REFRESH, HintValues.TRUE)
		            		.getSingleResult();
		            em.getTransaction().commit();
	            } catch(NoResultException nre){}
	            
	            if (item != null){
	            	sysvar = (Sysvar) item;	            	
	            }
	            return sysvar;
	            
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }   
	}

	@Override
	public List<Sysvar> findAll(String stringId) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Sysvar a WHERE a.idSysvar LIKE :idSysvar";
	            
	            List<Sysvar> list = em.createQuery(query)
	            		.setParameter("idSysvar", stringId)
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
	public List<Sysvar> findAllByGroup(String stringGrup) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Sysvar a WHERE  a.groupSysvar LIKE :groupSysvar";
	            
	            List<Sysvar> list = em.createQuery(query)
	            		.setParameter("groupSysvar", stringGrup)
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
