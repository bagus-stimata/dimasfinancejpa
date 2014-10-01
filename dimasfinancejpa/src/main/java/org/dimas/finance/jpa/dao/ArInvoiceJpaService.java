package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.ArinvoicePK;

public interface ArInvoiceJpaService extends GenericJpaService<Arinvoice, Serializable>{
	public List<Arinvoice> findAllReturBelumLunas();
	public List<Arinvoice> findAllReturBelumLunas(Arinvoice exceptRetur);
	public Arinvoice findByPk(ArinvoicePK pk);
	
}
