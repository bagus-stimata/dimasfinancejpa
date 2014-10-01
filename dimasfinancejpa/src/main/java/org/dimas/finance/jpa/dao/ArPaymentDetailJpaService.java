package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Arpaymentdetail;
import org.dimas.finance.model.Arpaymentheader;

public interface ArPaymentDetailJpaService extends GenericJpaService<Arpaymentdetail, Serializable>{
	public List<Arpaymentdetail> findAllByInvoiceAndDiv(String invoiceno, String div);
	public List<Arpaymentdetail> findAllByRefnoAndInvAndDiv(String refno, String inv, String div);
}
