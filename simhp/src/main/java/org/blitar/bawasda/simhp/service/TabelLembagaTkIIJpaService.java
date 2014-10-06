package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelLembagaTkII;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelLembagaTkIIJpaService extends GenericJpaService<TabelLembagaTkII, Serializable> {
	public List<TabelLembagaTkII> findAll(String strId);
	public List<TabelLembagaTkII> findAllHasChild();

}
