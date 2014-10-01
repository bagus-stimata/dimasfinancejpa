package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.ModulTempDetail;
import org.springframework.dao.DataAccessException;

public interface ModulTempDetailJpaService extends GenericJpaService<ModulTempDetail, Serializable>{
	public List<ModulTempDetail> findByHeaderId(long id) throws DataAccessException;
	public void removeAllByHeaderId(long id) throws DataAccessException;
}
