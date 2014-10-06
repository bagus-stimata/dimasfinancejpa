package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelTemuanKelompokJpaService extends GenericJpaService<TabelTemuanKelompok, Serializable>{
	public List<TabelTemuanKelompok> findAll(String strId);
	public List<TabelTemuanKelompok> findAllActive();

}
