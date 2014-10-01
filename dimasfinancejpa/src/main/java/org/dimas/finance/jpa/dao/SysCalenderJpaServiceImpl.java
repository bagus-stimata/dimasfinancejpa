package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.SysCalender;
import org.dimas.finance.model.Sysvar;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.springframework.dao.DataAccessException;

public class SysCalenderJpaServiceImpl extends GenericJpaServiceImpl<SysCalender, Serializable> implements SysCalenderJpaService{

	@Override
	public List<SysCalender> findAllByDivision(Division divisionBean)
			throws DataAccessException {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From SysCalender a WHERE a.id.division LIKE :division";
	            
	            List<SysCalender> list = em.createQuery(query)
	            		.setParameter("division", divisionBean.getId())
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
