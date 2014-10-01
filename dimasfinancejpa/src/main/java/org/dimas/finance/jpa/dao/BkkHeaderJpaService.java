package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Bkbheader;
import org.dimas.finance.model.Bkkheader;

public interface BkkHeaderJpaService extends GenericJpaService<Bkkheader, Serializable>{
	public List<Bkkheader> findAll(String refno, String division);
	public List<Bkkheader> findAll(String refno, String division, Date transdateFromAndTo);
	public List<Bkkheader> findAll(String refno, String division, Date transdateFrom, Date transdateTo);

}
