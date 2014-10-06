package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelRekomendasi;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelRekomendasiGrupJpaService extends GenericJpaService<TabelRekomendasiGrup, Serializable>{
	public List<TabelRekomendasiGrup> findAll(String strId);
	public List<TabelRekomendasiGrup> findAllActive();

}
