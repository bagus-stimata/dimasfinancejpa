package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Sysvar;
import org.eclipse.persistence.exceptions.JPQLException;

public interface SysvarJpaService extends GenericJpaService<Sysvar, Serializable> {
	public Sysvar findById(String strIdSysvar, String strDivision);
	public List<Sysvar> findAllById(String strIdSysvar, String strDivision);
	public List<Sysvar> findAllByGroup(String strGroup);
}
