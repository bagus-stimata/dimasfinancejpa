package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Bkbtranstype;
import org.dimas.finance.model.Bkktranstype;
import org.springframework.dao.DataAccessException;

public interface BkktranstypeJpaService extends GenericJpaService<Bkktranstype, Serializable>{
	public List<Bkktranstype> findAllById(String id) throws DataAccessException;
	public List<Bkktranstype> findAllByGrup(String grup) throws DataAccessException;

}
