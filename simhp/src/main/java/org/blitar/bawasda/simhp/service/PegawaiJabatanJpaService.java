package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.PegawaiJabatan;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface PegawaiJabatanJpaService extends GenericJpaService<PegawaiJabatan, Serializable>{
	public List<PegawaiJabatan> findAll(String strId);
	public List<PegawaiJabatan> findAllActive();

}
