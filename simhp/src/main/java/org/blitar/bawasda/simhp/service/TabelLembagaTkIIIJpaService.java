package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelLembagaTkIII;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelLembagaTkIIIJpaService extends GenericJpaService<TabelLembagaTkIII, Serializable>{
	public List<TabelLembagaTkIII> findAll(String strId);
	public List<TabelLembagaTkIII> findAllActive();

}
