package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Bkbtranstype;
import org.springframework.dao.DataAccessException;

public interface BkbtranstypeJpaService extends GenericJpaService<Bkbtranstype, Serializable> {
	public List<Bkbtranstype> findAllById(String id) throws DataAccessException;
	public List<Bkbtranstype> findAllByGrup(String grup) throws DataAccessException;
	
}
