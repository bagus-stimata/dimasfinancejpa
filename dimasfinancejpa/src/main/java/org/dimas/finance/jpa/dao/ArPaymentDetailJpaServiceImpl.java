package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Arpaymentdetail;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class ArPaymentDetailJpaServiceImpl extends GenericJpaServiceImpl<Arpaymentdetail, Serializable> implements ArPaymentDetailJpaService{

	@Override
	public List<Arpaymentdetail> findAllByInvoiceAndDiv(String invoiceno, String div) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM Arpaymentdetail a WHERE a.id.invoiceno LIKE :invoice "
	            		+ " AND a.id.division LIKE :division";
	            
	            List<Arpaymentdetail> list = em.createQuery(query)
	            		 .setParameter("invoice", invoiceno)
	            		 .setParameter("division", div)
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
	public List<Arpaymentdetail> findAllByRefnoAndInvAndDiv(String refno,
			String inv, String div) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM Arpaymentdetail a WHERE "
	            		+ "	a.id.refno LIKE :refno "
	            		+ " AND a.id.invoiceno LIKE :invoice "
	            		+ " AND a.id.division LIKE :division";
	            
	            List<Arpaymentdetail> list = em.createQuery(query)
	            		.setParameter("refno", refno)
	            		.setParameter("invoice", inv)
	            		.setParameter("division", div)
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
