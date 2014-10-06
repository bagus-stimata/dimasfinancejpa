package org.blitar.bawasda.simhp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="SURATTUGASRUANGLINGKUP")
public class SuratTugasRuangLingkup {

	@EmbeddedId
	protected SuratTugasRuangLingkupPK id;	
	
	@Column(name="LASTMODIFIED")
	@Temporal(TemporalType.DATE)
	private Date lastModified;
	@Column(name="LASTMODIFIEDBY")	
	private String lastModifiedBy;

	@ManyToOne
	@JoinColumn(name="SURATTUGAS", referencedColumnName="refno")
	private SuratTugas suratTugasBean;
	
	@ManyToOne
	@JoinColumn(name="RUANGLINGKUPPEMERIKSAAN", referencedColumnName="id")
	private TabelRuangLingkupPemeriksaan ruangLingkupPemeriksaanBean;
	
}