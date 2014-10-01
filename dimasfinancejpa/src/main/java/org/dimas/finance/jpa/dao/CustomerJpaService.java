package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Customer;

public interface CustomerJpaService extends GenericJpaService<Customer, Serializable> {
	public List<Customer> findAllById(String strCustno, String strDivision);

}
