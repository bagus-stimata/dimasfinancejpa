package org.blitar.bawasda.simhp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.TimeOfDay;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * id pada class Surat Tugas akan disamakan denga id pada HasilPemeriksaanHeader karena hubungannya OneToOne
 */
@Entity
@Table(name="HASILPEMERIKSAAN")
public class HasilPemeriksaan {

	@Id
	@Column(name="ID")
	private long refno;	

	@Column(name="PERIODEFROM")
	@Temporal(TemporalType.DATE)
	private Date periodeFrom;	
	@Column(name="PERIODETO")
	@Temporal(TemporalType.DATE)
	private Date periodeTo;
	
	@Column(name="NOMORLHP")
	private String nomorLhp;	
	@Column(name="TANGGALLHP")
	@Temporal(TemporalType.DATE)
	private Date tanggalLhp;
	
	@Column(name="TANGGALENTRY")
	@Temporal(TemporalType.DATE)
	private Date tanggalEntry;
	@Column(name="LASTMODIFIED")
	@Temporal(TemporalType.DATE)
	private Date lastModified;	
	@Column(name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	
	@Column(name="NOTES")
	private String notes;
	
	@Column(name="DISAHKANBYPIMPINAN")
	private boolean disahkanByPimpinan;

	//sebagai 0..1
	@OneToOne
	@JoinColumn(name="SURATTUGAS", referencedColumnName="refno")
	private SuratTugas suratTugasBean;
	
	@OneToMany(mappedBy="hasilPemeriksaanBean")
	private Set<HpTemuan> hpTemuanSet;
	
}