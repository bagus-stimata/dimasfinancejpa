package org.dimas.finance.jpa.dao;

import java.io.Serializable;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Bbankdetail;
import org.dimas.finance.model.BbankdetailPK;
import org.springframework.dao.DataAccessException;

public interface BBankDetailJpaService extends GenericJpaService<Bbankdetail, Serializable> {
	public void removeAllByHeaderIdWithNoUrut(BbankdetailPK id) throws DataAccessException;
	public void removeAllByHeaderIdWithoutNoUrut(BbankdetailPK id) throws DataAccessException;

}
