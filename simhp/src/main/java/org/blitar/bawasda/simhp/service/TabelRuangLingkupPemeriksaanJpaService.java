package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelRuangLingkupPemeriksaan;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelRuangLingkupPemeriksaanJpaService extends GenericJpaService<TabelRuangLingkupPemeriksaan, Serializable>{
	public List<TabelRuangLingkupPemeriksaan> findAll(String strId);
	public List<TabelRuangLingkupPemeriksaan> findAllActive();

}
