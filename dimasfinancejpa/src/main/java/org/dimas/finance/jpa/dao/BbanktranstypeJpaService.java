package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Bbanktranstype;
import org.dimas.finance.model.Bkbtranstype;
import org.springframework.dao.DataAccessException;

public interface BbanktranstypeJpaService extends GenericJpaService<Bbanktranstype, Serializable>{
	public List<Bbanktranstype> findAllById(String id) throws DataAccessException;
	public List<Bbanktranstype> findAllByGrup(String grup) throws DataAccessException;
	

}
