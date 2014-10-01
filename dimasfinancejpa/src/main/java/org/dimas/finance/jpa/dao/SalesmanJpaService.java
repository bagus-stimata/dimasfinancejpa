package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Salesman;

public interface SalesmanJpaService extends GenericJpaService<Salesman, Serializable> {
	public List<Salesman> findAllById(String strSpcode, String strDivision);

}
