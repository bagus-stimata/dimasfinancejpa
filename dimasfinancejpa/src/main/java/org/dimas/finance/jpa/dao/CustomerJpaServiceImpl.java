package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Customer;
import org.dimas.finance.model.Salesman;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class CustomerJpaServiceImpl extends GenericJpaServiceImpl<Customer, Serializable> implements CustomerJpaService {

	@Override
	public List<Customer> findAllById(String strCustno, String strDivision) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Customer a WHERE  a.id.custno LIKE :custno "
	            		+ " AND a.id.division LIKE :division";
	            
	            List<Customer> list = em.createQuery(query)
	            		.setParameter("custno", strCustno)
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
