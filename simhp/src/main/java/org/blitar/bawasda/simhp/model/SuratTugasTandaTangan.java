package org.blitar.bawasda.simhp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SURATTUGASPENANDATANGAN")
public class SuratTugasTandaTangan {

	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="NAMALENGKAP")
	private String namaLengkap;
	@Column(name="NAMAJABATAN")
	private String namaJabatan;
	@Column(name="NAMANIP")
	private String namaNip;
	
	@Column(name="DIKELUARKANTANGGAL")
	@Temporal(TemporalType.DATE)
	private Date dikeluarkanTanggal;
	@Column(name="DIKELUARKANTEMPAT")
	private String dikeluarkanTempat;
	@Column(name="ATASNAMA1")
	private String atasNama1;
	@Column(name="ATASNAMA2")
	private String atasNama2;
	@Column(name="ATASNAMA3")
	private String atasNama3;
	@Column(name="ATASNAMA4")
	private String atasNama4;
	
	
	@OneToMany(mappedBy="suratTugasTandaTanganBean")
	Set<SuratTugas> suratTugasSet;
	
	
}