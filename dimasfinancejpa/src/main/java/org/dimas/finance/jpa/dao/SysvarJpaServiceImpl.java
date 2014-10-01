package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Sysvar;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.exceptions.JPQLException;

public class SysvarJpaServiceImpl extends GenericJpaServiceImpl<Sysvar, Serializable> implements SysvarJpaService{

	@Override
	public Sysvar findById(String strIdSysvar, String strDivision){
	       EntityManager em = getFactory().createEntityManager();
           Sysvar sysvar = new Sysvar();
	       
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Sysvar a WHERE a.id.idSysvar LIKE :idSysvar AND a.id.division LIKE :division";
	            Object item = null;
	            
	            try {
		            item = em.createQuery(query)
		            		.setParameter("idSysvar", strIdSysvar)
		            		.setParameter("division", strDivision)
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
	public List<Sysvar> findAllByGroup(String strGroup) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Sysvar a WHERE a.groupSysvar LIKE :strGroupSysvar";
	            
	            List<Sysvar> list = em.createQuery(query)
	            		.setParameter("strGroupSysvar", strGroup)
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
	public List<Sysvar> findAllById(String strIdSysvar, String strDivision) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Sysvar a WHERE  a.id.idSysvar LIKE :idSysvar "
	            		+ " AND a.id.division LIKE :division";
	            
	            List<Sysvar> list = em.createQuery(query)
	            		.setParameter("idSysvar", strIdSysvar)
	            		.setParameter("division", strDivision)
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
