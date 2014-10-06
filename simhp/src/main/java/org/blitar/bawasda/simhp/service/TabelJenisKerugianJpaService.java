package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.TabelJenisKerugian;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface TabelJenisKerugianJpaService extends GenericJpaService<TabelJenisKerugian, Serializable>{
	public List<TabelJenisKerugian> findAll(String strId);
	public List<TabelJenisKerugian> findAllActive();

}
