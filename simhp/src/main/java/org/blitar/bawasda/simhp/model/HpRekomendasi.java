package org.blitar.bawasda.simhp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="HPREKOMENDASI")
public class HpRekomendasi {

	@EmbeddedId
	protected HpRekomendasiPK id;
	
	@Column(name="DESKRIPSI")
	private String desksipsi;
	@Column(name="LASTMODIFIED")
	@Temporal(TemporalType.DATE)
	private Date lastModified;
	@Column(name="LASTMODIFIEDBY")
	private String lastModifiedBy;


	@ManyToOne
	@JoinColumns({@JoinColumn(name="REFNO", referencedColumnName="refno"),
		@JoinColumn(name="NOURUTTEMUAN", referencedColumnName="noUrutTemuan")})		
	private HpTemuan hpTemuanBean;

	@ManyToOne
	@JoinColumn(name="KODEREKOMENDASI", referencedColumnName="id")
	private TabelRekomendasi rekomendasiBean;
	
	@OneToMany(mappedBy="hpRekomendasi")
	Set<HpTindakLanjutRencana> hpRencanaTindakLanjutSet;
	


	
	
}