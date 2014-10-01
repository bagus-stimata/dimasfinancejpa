package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Bukutransfer;

public interface BukuTransferJpaService extends GenericJpaService<Bukutransfer, Serializable>{
	public List<Bukutransfer> findAllAvailableTransfer();
	public List<Bukutransfer> findAllAvailabelTransfer(String exceptTransfer);

	public List<Bukutransfer> findAllById(String strRefno, String strDivision);
	
}
