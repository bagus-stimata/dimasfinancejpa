package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Arpaymentdetail;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Sysvar;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class BukuGiroJpaServiceImpl extends GenericJpaServiceImpl<Bukugiro, Serializable> implements BukuGiroJpaService{

	@Override
	public List<Bukugiro> findAllAvailableGiro(Date tgltransaksi) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukugiro a WHERE a.amount>a.amountused AND a.giroduedate>= :tgltransaksi";
	            
	            List<Bukugiro> list = em.createQuery(query)
	            		.setParameter("tgltransaksi", tgltransaksi)
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
	public List<Bukugiro> findAllAvailableGiro() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukugiro a WHERE (a.amount>a.amountused OR a.amountused IS NULL) ";
	            
	            List<Bukugiro> list = em.createQuery(query)
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
	public List<Bukugiro> findAllAvalilableGiro(Date tgltransaksi,
			String exceptGiro) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukugiro a WHERE (a.amount>a.amountused  OR a.amountused IS NULL) AND a.giroduedate>= :tgltransaksi "
	            		+ " OR a.refno LIKE :exceptrefno";
	            
	            List<Bukugiro> list = em.createQuery(query)
	            		.setParameter("tgltransaksi", tgltransaksi)
	            		.setParameter("exceptrefno", exceptGiro)
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
	public List<Bukugiro> findAllAvalilableGiro(String exceptGiro) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukugiro a WHERE (a.amount>a.amountused  OR a.amountused IS NULL) OR a.refno LIKE :exceptrefno";
	            
	            List<Bukugiro> list = em.createQuery(query)
	            		.setParameter("exceptrefno", exceptGiro)
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
	public List<Bukugiro> findAllById(String strRefno, String strDivision) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukugiro a WHERE  a.id.refno LIKE :refno "
	            		+ " AND a.id.division LIKE :division";
	            
	            List<Bukugiro> list = em.createQuery(query)
	            		.setParameter("refno", strRefno)
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
