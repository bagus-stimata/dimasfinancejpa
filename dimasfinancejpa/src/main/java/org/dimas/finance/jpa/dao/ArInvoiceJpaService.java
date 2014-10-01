package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.ArinvoicePK;
import org.dimas.finance.model.Bbankheader;

public interface ArInvoiceJpaService extends GenericJpaService<Arinvoice, Serializable>{
	public List<Arinvoice> findAllReturBelumLunas();
	public List<Arinvoice> findAllReturBelumLunas(Arinvoice exceptRetur);
	public Arinvoice findByPk(ArinvoicePK pk);
	public List<Arinvoice> findAllForRecapSelect(String recapno, String division);
	public List<Arinvoice> findAllForRecapSelect(String recapno, String division, Date invoiceDateFromAndTo);
	public List<Arinvoice> findAllForRecapSelect(String recapno, String division, Date invoiceDateFrom, Date invoiceDateTo);
	
	
}
