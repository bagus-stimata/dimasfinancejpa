package org.blitar.bawasda.simhp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="HPTEMUAN")
public class HpTemuan {

	@EmbeddedId
	protected HpTemuanPK id;
	
	@Column(name="DESKRIPSI")
	private String deskripsi;
	@Column(name="NILAITEMUAN")
	private double nilaiTemuan;
	@Column(name="LASTMODIFIED")
	@Temporal(TemporalType.DATE)
	private Date lastModified;
	@Column(name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	@Column(name="KODEPENYEBAB")
	private String kodePenyebab;

	@ManyToOne
	@JoinColumn(name="REFNO", referencedColumnName="refno")
	private HasilPemeriksaan hasilPemeriksaanBean;
	
	@ManyToOne
	@JoinColumn(name="KODETEMUAN", referencedColumnName="id")
	private TabelTemuan temuanBean;

	@ManyToOne
	@JoinColumn(name="KODEJENISKERUGIAN", referencedColumnName="id")
	private TabelJenisKerugian jenisKerugianBean;
	
	@OneToMany(mappedBy="hpTemuanBean")
	Set<HpRekomendasi> hpRekomendasiSet;
	
	
	@OneToMany(mappedBy="hpTemuanBean")
	Set<HpTemuanPelaku> hpPelakuSet;
	
	@OneToOne(mappedBy="hpTemuanBean")
	private HpTemuanNilai hpTemuanNilaiBean;

	@OneToOne(mappedBy="hpTemuanBean")
	private HpTemuanInvestigasiKhusus hpTemuanInvestigasiKhususBean;
	
}