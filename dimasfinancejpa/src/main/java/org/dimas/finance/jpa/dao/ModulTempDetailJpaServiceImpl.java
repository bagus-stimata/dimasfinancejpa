package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.ModulTempDetail;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.springframework.dao.DataAccessException;

public class ModulTempDetailJpaServiceImpl extends GenericJpaServiceImpl<ModulTempDetail, Serializable> implements ModulTempDetailJpaService{

	@Override
	public List<ModulTempDetail> findByHeaderId(long id) throws DataAccessException {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From ModulTempDetail a WHERE a.id.id = :headerId ";
	            
	            List<ModulTempDetail> list = em.createQuery(query)
	            		.setParameter("headerId", id)
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
	public void removeAllByHeaderId(long id) throws DataAccessException {
		// TODO Auto-generated method stub		
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "DELETE From ModulTempDetail a WHERE a.id.id = :headerId ";
	            
	            em.createQuery(query)
            		.setParameter("headerId", id)
            		.executeUpdate();
	            
	            em.getTransaction().commit();
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }    
		
	}

	
}
