package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.MataUang;
import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface MataUangJpaService extends GenericJpaService<MataUang, Serializable>{
	public List<MataUang> findAll(String strId);
	public List<MataUang> findAllActive();

}
