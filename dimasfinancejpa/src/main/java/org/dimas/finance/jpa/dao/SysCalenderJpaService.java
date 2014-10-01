package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.SysCalender;
import org.dimas.finance.model.Sysvar;
import org.springframework.dao.DataAccessException;

public interface SysCalenderJpaService extends GenericJpaService<SysCalender, Serializable>{
	public List<SysCalender> findAllByDivision(Division divisionBean) throws DataAccessException;

}
