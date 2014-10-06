package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.Pegawai;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface PegawaiJpaService extends GenericJpaService<Pegawai, Serializable> {
	public List<Pegawai> findAll(String strId);
	public List<Pegawai> findAllActive();

}
