package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Bkbheader;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Division;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class BkbHeaderJpaServiceImpl extends GenericJpaServiceImpl<Bkbheader, Serializable> implements BkbHeaderJpaService{

	@Override
	public List<Bkbheader> findAll(String refno, String division) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Bkbheader a "
	            		+ " WHERE "
	            		+ " a.id.refno LIKE :refno AND a.id.division LIKE :division ";
	            
	            List<Bkbheader> list = em.createQuery(query)
	            		.setParameter("refno", refno)
	            		.setParameter("division", division)
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
	public List<Bkbheader> findAll(String refno, String division, Date transdateFromAndTo) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Bkbheader a "
	            		+ " WHERE "
	            		+ " a.id.refno LIKE :refno AND a.id.division LIKE :division "
	            		+ " AND a.transdate = :transdate";
	            
	            List<Bkbheader> list = em.createQuery(query)
	            		.setParameter("refno", refno)
	            		.setParameter("division", division)
	            		.setParameter("transdate", transdateFromAndTo)
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
	public List<Bkbheader> findAll(String refno, String division, Date transdateFrom,
			Date transdateTo) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Bkbheader a "
	            		+ " WHERE "
	            		+ " a.id.refno LIKE :refno AND a.id.division LIKE :division "
	            		+ " AND a.transdate >= :transdateFrom AND a.transdate <= :transdateTo";
	            
	            List<Bkbheader> list = em.createQuery(query)
	            		.setParameter("refno", refno)
	            		.setParameter("division", division)
	            		.setParameter("transdateFrom", transdateFrom)
	            		.setParameter("transdateTo", transdateTo)
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
