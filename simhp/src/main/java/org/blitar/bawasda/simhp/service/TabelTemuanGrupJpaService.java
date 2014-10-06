package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelTemuanGrupJpaService extends GenericJpaService<TabelTemuanGrup, Serializable>{
	public List<TabelTemuanGrup> findAll(String strId);
	public List<TabelTemuanGrup> findAllActive();
}
