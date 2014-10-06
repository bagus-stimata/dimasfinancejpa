package org.blitar.bawasda.simhp.service;

import java.io.Serializable;
import java.util.List;

import org.blitar.bawasda.simhp.model.SuratTugasTimAnggotaPosisi;
import org.blitar.bawasda.simhp.service.generic.GenericJpaService;

public interface SuratTugasTimAnggotaPosisiJpaService extends GenericJpaService<SuratTugasTimAnggotaPosisi, Serializable> {
	public List<SuratTugasTimAnggotaPosisi> findAll(String strId);
	public List<SuratTugasTimAnggotaPosisi> findAllActive();

}
