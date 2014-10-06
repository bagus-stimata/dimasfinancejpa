package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelTindakLanjut;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelTindakLanjutJpaService extends GenericJpaService<TabelTindakLanjut, Serializable>{
	public List<TabelTindakLanjut> findAll(String strId);
	public List<TabelTindakLanjut> findAllActive();
}
