package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Bkbheader;
import org.dimas.finance.model.Bukutransfer;
import org.dimas.finance.model.Division;

public interface BkbHeaderJpaService extends GenericJpaService<Bkbheader, Serializable> {
	public List<Bkbheader> findAll(String refno, String division);
	public List<Bkbheader> findAll(String refno, String division, Date transdateFromAndTo);
	public List<Bkbheader> findAll(String refno, String division, Date transdateFrom, Date transdateTo);
	
}
