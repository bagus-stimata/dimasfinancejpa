package org.dimas.finance.jpa.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Bbankdetail;
import org.dimas.finance.model.BbankdetailPK;
import org.springframework.dao.DataAccessException;

public class BBankDetailJpaServiceImpl extends GenericJpaServiceImpl<Bbankdetail, Serializable> implements BBankDetailJpaService{

	@Override
	public void removeAllByHeaderIdWithNoUrut(BbankdetailPK id)
			throws DataAccessException {
		// TODO Auto-generated method stub
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "DELETE From Bbankdetail a "
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
	public void removeAllByHeaderIdWithoutNoUrut(
			BbankdetailPK id) throws DataAccessException {
		// TODO Auto-generated method stub
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "DELETE From Bbankdetail a "
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
