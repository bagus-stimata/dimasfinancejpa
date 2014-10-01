package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Bkbtranstype;
import org.dimas.finance.model.Bukugiro;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.springframework.dao.DataAccessException;

public class BkbtranstypeJpaServiceImpl extends GenericJpaServiceImpl<Bkbtranstype, Serializable> implements BkbtranstypeJpaService{

	@Override
	public List<Bkbtranstype> findAllById(String id) throws DataAccessException {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bkbtranstype a WHERE  a.id LIKE :id ";
	            
	            List<Bkbtranstype> list = em.createQuery(query)
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
	public List<Bkbtranstype> findAllByGrup(String grup)
			throws DataAccessException {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bkbtranstype a WHERE  a.grup LIKE :grup ";
	            
	            List<Bkbtranstype> list = em.createQuery(query)
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
