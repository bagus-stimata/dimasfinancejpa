package org.blitar.bawasda.simhp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="HPTEMUANINVESTIGASIKHUSUS")
public class HpTemuanInvestigasiKhusus {

	@EmbeddedId
	protected HpTemuanInvestigasiKhususPK id;
	
	@Column(name="DESKRIPSI")
	private String deskripsi;
	@Column(name="LASTMODIFIED")
	@Temporal(TemporalType.DATE)
	private Date lastModified;
	@Column(name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	
	@JoinColumns({@JoinColumn(name="REFNO", referencedColumnName="refno"),
		@JoinColumn(name="NOURUTTEMUAN", referencedColumnName="noUrutTemuan")})		
	private HpTemuan hpTemuanBean;

}