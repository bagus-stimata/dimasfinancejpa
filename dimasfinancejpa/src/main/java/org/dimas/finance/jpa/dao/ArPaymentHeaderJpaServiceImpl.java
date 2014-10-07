package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Arpaymentheader;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Sysvar;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class ArPaymentHeaderJpaServiceImpl extends GenericJpaServiceImpl<Arpaymentheader, Serializable> implements ArPaymentHeaderJpaService{

	@Override
	public Arpaymentheader findById(String strId) {
	       EntityManager em = getFactory().createEntityManager();
	       Arpaymentheader arpaymentHeader = new Arpaymentheader();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Arpaymentheader a WHERE a.refno LIKE :theRefno";

	            Object item = null;
	        	try{	            
		            item = em.createQuery(query)
		            		.setParameter("theRefno", strId)
		            		.setHint(QueryHints.REFRESH, HintValues.TRUE)
		            		.getSingleResult();
		            em.getTransaction().commit();
	        	} catch(NoResultException nre) {}
	        	
	        	if (item !=null ){
	        		arpaymentHeader = (Arpaymentheader) item;
	        	}
	            return arpaymentHeader;
	            
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }    
	}

	@Override
	public List<Arpaymentheader> findAllById(String strRefno, String strDivision) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Arpaymentheader a WHERE  a.id.refno LIKE :refno "
	            		+ " AND a.id.division LIKE :division";
	            
	            List<Arpaymentheader> list = em.createQuery(query)
	            		.setParameter("refno",  "%" + strRefno.trim() + "%")
	            		.setParameter("division", "%" + strDivision.trim() + "%")
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
