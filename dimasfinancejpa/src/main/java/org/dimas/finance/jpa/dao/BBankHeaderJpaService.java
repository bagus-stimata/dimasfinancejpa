package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Bbankheader;

public interface BBankHeaderJpaService extends GenericJpaService<Bbankheader, Serializable>{
	public List<Bbankheader> findAll(String refno, String division);
	public List<Bbankheader> findAll(String refno, String division, Date transdateFromAndTo);
	public List<Bbankheader> findAll(String refno, String division, Date transdateFrom, Date transdateTo);

}
