package org.blitar.bawasda.simhp.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="HPTEMUANPELAKU")
public class HpTemuanPelaku {

	@EmbeddedId
	protected HpTemuanPelakuPK id;
	
	@Column(name="NIP")
	private String nip;
	@Column(name="NAMA")
	private String nama;
	@Column(name="JABATAN")
	private String jabatan;
	@Column(name="GOLONGAN")
	private String golongan;
	@Column(name="PERAN")
	private String peran;

	@ManyToOne
	@JoinColumns({@JoinColumn(name="REFNO", referencedColumnName="refno"),
		@JoinColumn(name="NOURUTTEMUAN", referencedColumnName="noUrutTemuan")})	
	private HpTemuan hpTemuanBean;

}