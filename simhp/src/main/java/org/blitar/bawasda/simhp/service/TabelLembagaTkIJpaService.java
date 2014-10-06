package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelLembagaTkI;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelLembagaTkIJpaService extends GenericJpaService<TabelLembagaTkI, Serializable>{
	public List<TabelLembagaTkI> findAll(String strId);
	public List<TabelLembagaTkI> findAllHasChild();

}
