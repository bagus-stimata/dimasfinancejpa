package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelJenisPemeriksaan;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelJenisPemeriksaanJpaService extends GenericJpaService<TabelJenisPemeriksaan, Serializable>{
	public List<TabelJenisPemeriksaan> findAll(String strId);
	public List<TabelJenisPemeriksaan> findAllActive();

}
