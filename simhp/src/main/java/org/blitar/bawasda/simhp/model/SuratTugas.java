package org.blitar.bawasda.simhp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="SURATTUGAS")
public class SuratTugas {

	
	@Id
	@Column(name="REFNO")
	private long refno;
	
	@Column(name="NOMORSURAT")
	private String nomorSurat; 	
	@Column(name="TANGGALSURAT")
	@Temporal(TemporalType.DATE)
	private Date tanggalSurat;
	@Column(name="OBRIK")
	private String obrik;
	@Column(name="TAHUNPERIODE")
	private int tahunperiode;
	
	@OneToMany(mappedBy="suratTugasBean")
	Set<SuratTugasTimAnggota> anggotaSet;
	
	
	@ManyToOne
	@JoinColumn(name="JENISPEMERIKSAAN", referencedColumnName="id")
	private TabelJenisPemeriksaan jenisPemeriksaanBean;
	
	@ManyToOne
	@JoinColumn(name="LEMBAGAKTK1", referencedColumnName="id")
	private TabelLembagaTkI lembagaTk1Bean;

	@ManyToOne
	@JoinColumn(name="LEMBAGAKTK2", referencedColumnName="id")
	private TabelLembagaTkII lembagaTk2Bean;
	
	@ManyToOne
	@JoinColumn(name="LEMBAGAKTK3", referencedColumnName="id")
	private TabelLembagaTkIII lembagaTk3Bean;
	
	@ManyToOne
	@JoinColumn(name="TANDATANGAN", referencedColumnName="id")
	private SuratTugasTandaTangan suratTugasTandaTanganBean;
	
	//sebagai 1
	@OneToOne(mappedBy="suratTugasBean")
	private HasilPemeriksaan hasilPemeriksaanBean;
	
}