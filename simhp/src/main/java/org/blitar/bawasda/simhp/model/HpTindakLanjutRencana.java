package org.blitar.bawasda.simhp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="HPTINDAKLANJUTRENCANA")
public class HpTindakLanjutRencana {

	@EmbeddedId
	protected HpTindakLanjutRencanaPK id;
	
	@Column(name="DESKRIPSI")
	private String deskripsi;
	@Column(name="LASTMODIFIED")
	@Temporal(TemporalType.DATE)
	private Date lastModified;
	@Column(name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	
	@Column(name="STATUSTL")
	private String statusTl;
	@Column(name="USEDRTL")
	private boolean usedRtl;
	@Column(name="SELESAI")
	private boolean selesai;
	

	@ManyToOne
	@JoinColumn(name="TINDAKLANJUT", referencedColumnName="id")
	private TabelTindakLanjut tindakLanjutBean;
	
	@ManyToOne
	@JoinColumns({@JoinColumn(name="REFNO", referencedColumnName="refno"),
		@JoinColumn(name="NOURUTTEMUAN", referencedColumnName="noUrutTemuan"),
		@JoinColumn(name="NOURUTREKOM", referencedColumnName="noUrutRekom")})		
	private HpRekomendasi hpRekomendasi;

}