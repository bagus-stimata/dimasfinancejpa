package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Bkbtranstype;
import org.dimas.finance.model.Bkktranstype;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.springframework.dao.DataAccessException;

public class BkktranstypeJpaServiceImpl extends GenericJpaServiceImpl<Bkktranstype, Serializable> implements BkktranstypeJpaService{

	@Override
	public List<Bkktranstype> findAllById(String id) throws DataAccessException {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bkktranstype a WHERE  a.id LIKE :id ";
	            
	            List<Bkktranstype> list = em.createQuery(query)
	            		.setParameter("id", id)
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
	public List<Bkktranstype> findAllByGrup(String grup)
			throws DataAccessException {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bkktranstype a WHERE  a.grup LIKE :grup ";
	            
	            List<Bkktranstype> list = em.createQuery(query)
	            		.setParameter("grup", grup)
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
