package org.dimas.finance.jpa.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Bkbdetail;
import org.dimas.finance.model.BkbdetailPK;
import org.springframework.dao.DataAccessException;

public class BkbDetailJpaServiceImpl extends GenericJpaServiceImpl<Bkbdetail, Serializable> implements BkbDetailJpaService{

	@Override
	public void removeAllByHeaderIdWithNoUrut(BkbdetailPK id)
			throws DataAccessException {
		// TODO Auto-generated method stub
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "DELETE From Bkbdetail a "
	            		+ "WHERE a.id.refno = :refno "
	            		+ "AND  a.id.nourut = :nourut "
	            		+ "AND  a.id.division = :division ";
	            
	            em.createQuery(query)
           		.setParameter("refno", id.getRefno())
           		.setParameter("nourut", id.getNourut())
           		.setParameter("division", id.getDivision())
           		.executeUpdate();
	            
	            em.getTransaction().commit();
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }    
		
	}

	@Override
	public void removeAllByHeaderIdWithoutNoUrut(BkbdetailPK id)
			throws DataAccessException {
		// TODO Auto-generated method stub
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "DELETE From Bkbdetail a "
	            		+ "WHERE a.id.refno = :refno "
	            		+ "AND  a.id.division = :division ";
	            
	            em.createQuery(query)
          		.setParameter("refno", id.getRefno())
          		.setParameter("division", id.getDivision())
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
