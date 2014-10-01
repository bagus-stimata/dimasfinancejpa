package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Salesman;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class SalesmanJpaServiceImpl extends GenericJpaServiceImpl<Salesman, Serializable> implements SalesmanJpaService{

	@Override
	public List<Salesman> findAllById(String strSpcode, String strDivision) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Salesman a WHERE  a.id.spcode LIKE :spcode "
	            		+ " AND a.id.division LIKE :division";
	            
	            List<Salesman> list = em.createQuery(query)
	            		.setParameter("spcode", strSpcode)
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
