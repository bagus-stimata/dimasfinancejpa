package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelJenisPemeriksaanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelJenisPemeriksaanGrupJpaService extends GenericJpaService<TabelJenisPemeriksaanGrup, Serializable>{
	public List<TabelJenisPemeriksaanGrup> findAll(String strId);
	public List<TabelJenisPemeriksaanGrup> findAllActive();

}
