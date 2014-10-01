package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Arpaymentheader;
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

}
