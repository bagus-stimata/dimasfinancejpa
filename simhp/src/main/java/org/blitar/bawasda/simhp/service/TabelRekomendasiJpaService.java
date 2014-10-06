package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelRekomendasi;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelRekomendasiJpaService extends GenericJpaService<TabelRekomendasi, Serializable>{
	public List<TabelRekomendasi> findAll(String strId);
	public List<TabelRekomendasi> findAllActive();

}
