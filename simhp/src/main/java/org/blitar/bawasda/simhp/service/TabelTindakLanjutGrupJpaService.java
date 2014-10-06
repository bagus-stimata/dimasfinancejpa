package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelTindakLanjutGrup;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelTindakLanjutGrupJpaService extends GenericJpaService<TabelTindakLanjutGrup, Serializable>{
	public List<TabelTindakLanjutGrup> findAll(String strId);
	public List<TabelTindakLanjutGrup> findAllActive();

}
