package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Bukutransfer;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class BukuTransferJpaServiceImpl extends GenericJpaServiceImpl<Bukutransfer, Serializable> implements BukuTransferJpaService{

	@Override
	public List<Bukutransfer> findAllAvailableTransfer() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukutransfer a WHERE (a.amount>a.amountused  OR a.amountused IS NULL) ";
	            
	            List<Bukutransfer> list = em.createQuery(query)
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
	public List<Bukutransfer> findAllAvailabelTransfer(String exceptTransfer) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukutransfer a WHERE (a.amount>a.amountused  OR a.amountused IS NULL) "
	            		+ " OR a.refno LIKE :exceptrefno";
	            
	            List<Bukutransfer> list = em.createQuery(query)
	            		.setParameter("exceptrefno", exceptTransfer)
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
	public List<Bukutransfer> findAllById(String strRefno, String strDivision) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukutransfer a WHERE  a.id.refno LIKE :refno "
	            		+ " AND a.id.division LIKE :division";
	            
	            List<Bukutransfer> list = em.createQuery(query)
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
