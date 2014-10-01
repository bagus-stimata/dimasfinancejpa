package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Arpaymentheader;

public interface ArPaymentHeaderJpaService extends GenericJpaService<Arpaymentheader, Serializable>{

	public Arpaymentheader findById(String strId);
	
}
