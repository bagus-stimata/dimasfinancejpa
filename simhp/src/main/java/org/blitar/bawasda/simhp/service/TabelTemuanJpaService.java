package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelTemuanJpaService extends GenericJpaService<TabelTemuan, Serializable>{
	public List<TabelTemuan> findAll(String strId);
	public List<TabelTemuan> findAllActive();

}
