package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.dimas.finance.jpa.dao.generic.GenericJpaServiceImpl;
import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.ArinvoicePK;
import org.dimas.finance.model.Bbankheader;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class ArInvoiceJpaServiceImpl extends GenericJpaServiceImpl<Arinvoice, Serializable> implements ArInvoiceJpaService{

	@Override
	public List<Arinvoice> findAllReturBelumLunas() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM Arinvoice a WHERE a.id.tipefaktur LIKE 'R' AND (a.amount > a.amountpay OR a.amountpay IS NULL) ";
	            
	            List<Arinvoice> list = em.createQuery(query)
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
	public List<Arinvoice> findAllReturBelumLunas(Arinvoice exceptRetur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM Arinvoice a WHERE (a.id.tipefaktur LIKE 'R' AND (a.amount > a.amountpay  OR a.amountpay IS NULL)) "
	            		+ " OR (a.id.invoiceno LIKE :exReturNo AND a.id.division LIKE :exReturDiv)";
	            
	            List<Arinvoice> list = em.createQuery(query)
	            		.setParameter("exReturNo", exceptRetur.getId().getInvoiceno())
	            		.setParameter("exReturDiv", exceptRetur.getId().getDivision())
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
	public Arinvoice findByPk(ArinvoicePK pk) {
	       EntityManager em = getFactory().createEntityManager();
           Arinvoice arinvoice = new Arinvoice();
	       
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Arinvoice a WHERE a.id.division LIKE :strDivision "
	            		+ " AND a.id.invoiceno LIKE :strInvoiceno "
	            		+ " AND a.id.tipefaktur LIKE :strTipe";
	            Object item = null;
	            
	            try {
		            item = em.createQuery(query)
		            		.setParameter("strDivision", pk.getDivision())
		            		.setParameter("strInvoiceno", pk.getInvoiceno())
		            		.setParameter("strTipe", pk.getTipefaktur())
		            		.setHint(QueryHints.REFRESH, HintValues.TRUE)
		            		.getSingleResult();
		            em.getTransaction().commit();
	            } catch(NoResultException nre){}
	            
	            if (item != null){
	            	arinvoice = (Arinvoice) item;	            	
	            }
	            return arinvoice;
	            
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }   
	    
	}

	@Override
	public List<Arinvoice> findAllForRecapSelectWh(String recapno, String division) {
		// TODO Auto-generated method stub
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Arinvoice a "
	            		+ " WHERE "
	            		+ " a.recapno LIKE :recapno AND a.id.division LIKE :division AND a.terkirim = false "
	            		+ " GROUP BY a.recapno";
	            
	            List<Arinvoice> list = em.createQuery(query)
	            		.setParameter("recapno", recapno)
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
	public List<Arinvoice> findAllForRecapSelectWh(String recapno,
			String division, Date invoiceFromAndTo) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Arinvoice a "
	            		+ " WHERE "
	            		+ " a.recapno LIKE :recapno AND a.id.division LIKE :division "
	            		+ " AND a.invoicedate = :invoicedateFormTo AND a.terkirim = false"
	            		+ " GROUP BY a.recapno";
	            
	            List<Arinvoice> list = em.createQuery(query)
	            		.setParameter("recapno", recapno)
	            		.setParameter("division", division)
	            		.setParameter("invoicedateFormTo", invoiceFromAndTo)
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
	public List<Arinvoice> findAllForRecapSelectWh(String recapno,
			String division, Date invoiceDateFrom, Date invoiceDateTo) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Arinvoice a "
	            		+ " WHERE "
	            		+ " a.recapno LIKE :recapno AND a.id.division LIKE :division "
	            		+ " AND a.invoicedate >= :invoicedateFrom AND a.invoicedate <= :invoicedateTo  AND a.terkirim = false"
	            		+ " GROUP BY a.recapno";
	            
	            List<Arinvoice> list = em.createQuery(query)
	            		.setParameter("recapno", recapno)
	            		.setParameter("division", division)
	            		.setParameter("invoicedateFrom", invoiceDateFrom)
	            		.setParameter("invoicedateTo", invoiceDateTo)
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
	public List<Arinvoice> findAllForRecapSelectArTOTunai(String recapno, String division) {
		// TODO Auto-generated method stub
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Arinvoice a "
	            		+ " WHERE "
	            		+ " a.recapno LIKE :recapno AND a.id.division LIKE :division"
	            		+ " AND a.lunas = false AND a.term = 1 AND a.tipejual LIKE 'TO'  "
	            		+ " GROUP BY a.recapno";
	            
	            List<Arinvoice> list = em.createQuery(query)
	            		.setParameter("recapno", recapno)
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
	public List<Arinvoice> findAllForRecapSelectArTOTunai(String recapno,
			String division, Date invoiceFromAndTo) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Arinvoice a "
	            		+ " WHERE "
	            		+ " a.recapno LIKE :recapno AND a.id.division LIKE :division "
	            		+ " AND a.invoicedate = :invoicedateFormTo AND a.lunas = false"
	            		+ " AND  a.term = 1 AND a.tipejual LIKE 'TO'  "
	            		+ " GROUP BY a.recapno";
	            
	            List<Arinvoice> list = em.createQuery(query)
	            		.setParameter("recapno", recapno)
	            		.setParameter("division", division)
	            		.setParameter("invoicedateFormTo", invoiceFromAndTo)
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
	public List<Arinvoice> findAllForRecapSelectArTOTunai(String recapno,
			String division, Date invoiceDateFrom, Date invoiceDateTo) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Arinvoice a "
	            		+ " WHERE "
	            		+ " a.recapno LIKE :recapno AND a.id.division LIKE :division "
	            		+ " AND a.invoicedate >= :invoicedateFrom AND a.invoicedate <= :invoicedateTo "
	            		+ " AND a.lunas = false AND a.term = 1 AND a.tipejual LIKE 'TO' "
	            		+ " GROUP BY a.recapno";
	            
	            List<Arinvoice> list = em.createQuery(query)
	            		.setParameter("recapno", recapno)
	            		.setParameter("division", division)
	            		.setParameter("invoicedateFrom", invoiceDateFrom)
	            		.setParameter("invoicedateTo", invoiceDateTo)
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
	public List<Arinvoice> findAllByIdPk(ArinvoicePK id) {
		// TODO Auto-generated method stub
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Arinvoice a "
	            		+ " WHERE "
	            		+ " a.id.invoiceno LIKE :invoiceno AND a.id.division LIKE :division"
	            		+ " AND a.id.tipefaktur LIKE :tipefaktur ";
	            
	            List<Arinvoice> list = em.createQuery(query)
	            		.setParameter("invoiceno", id.getInvoiceno())
	            		.setParameter("division", id.getDivision())
	            		.setParameter("tipefaktur", id.getTipefaktur())
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
