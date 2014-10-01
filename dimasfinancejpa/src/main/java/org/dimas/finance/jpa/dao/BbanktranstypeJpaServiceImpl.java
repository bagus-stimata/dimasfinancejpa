package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Bbanktranstype;
import org.dimas.finance.model.Bkbtranstype;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.springframework.dao.DataAccessException;

public class BbanktranstypeJpaServiceImpl extends GenericJpaServiceImpl<Bbanktranstype, Serializable> implements BbanktranstypeJpaService{

	@Override
	public List<Bbanktranstype> findAllById(String id)
			throws DataAccessException {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bbanktranstype a WHERE  a.id LIKE :id ";
	            
	            List<Bbanktranstype> list = em.createQuery(query)
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
	public List<Bbanktranstype> findAllByGrup(String grup)
			throws DataAccessException {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bbanktranstype a WHERE  a.grup LIKE :grup ";
	            
	            List<Bbanktranstype> list = em.createQuery(query)
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
