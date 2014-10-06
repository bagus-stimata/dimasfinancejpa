package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.Sysvar;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface SysvarJpaService extends GenericJpaService<Sysvar, Serializable>{
	public Sysvar findById(String stringId);
	public List<Sysvar> findAll(String stringId);
	public List<Sysvar> findAllByGroup(String stringGrup);

}
