package org.dimas.finance.jpa.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.dimas.finance.jpa.dao.generic.GenericJpaService;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Sysvar;

public interface BukuGiroJpaService extends GenericJpaService<Bukugiro, Serializable> {
	public List<Bukugiro> findAllAvailableGiro(Date tgltransaksi);
	public List<Bukugiro> findAllAvailableGiro();
	public List<Bukugiro> findAllAvalilableGiro(Date tgltransaksi, String exceptGiro);
	public List<Bukugiro> findAllAvalilableGiro(String exceptGiro);

	public List<Bukugiro> findAllById(String strRefno, String strDivision);
	
}
