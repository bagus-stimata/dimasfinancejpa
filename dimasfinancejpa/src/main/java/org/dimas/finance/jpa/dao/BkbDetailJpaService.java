package org.dimas.finance.jpa.dao;

import java.io.Serializable;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Bkbdetail;
import org.dimas.finance.model.BkbdetailPK;
import org.dimas.finance.model.BkbheaderPK;
import org.springframework.dao.DataAccessException;

public interface BkbDetailJpaService extends GenericJpaService<Bkbdetail, Serializable> {
	public void removeAllByHeaderIdWithNoUrut(BkbdetailPK id) throws DataAccessException;
	public void removeAllByHeaderIdWithoutNoUrut(BkbdetailPK id) throws DataAccessException;
}
