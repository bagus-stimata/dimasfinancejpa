package org.blitar.bawasda.simhp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="HPTEMUANNILAI")
public class HpTemuanNilai {


	@EmbeddedId
	protected HpTemuanNilaiPK id;
	
	@Column(name="DESKRIPSI")
	private String deskripsi;
	@Column(name="NOMINAL")
	private double nominal;
	@Column(name="KURS")
	private double kurs;
	@Column(name="TANGGALKURS")
	@Temporal(TemporalType.DATE)
	private Date tanggalKurs;

	@ManyToOne
	@JoinColumn(name="MATAUANG", referencedColumnName="id")
	private MataUang mataUangBean;
	
	@OneToOne
	@JoinColumns({@JoinColumn(name="REFNO", referencedColumnName="refno"),
		@JoinColumn(name="NOURUTTEMUAN", referencedColumnName="noUrutTemuan")})		
	private HpTemuan hpTemuanBean;
	
}