package org.blitar.bawasda.simhp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="TABELTEMUANALTERNATIFREKOMENDASI")
public class TabelTemuanAlternatifRekomendasi implements Serializable {

	@EmbeddedId
	protected TabelTemuanAlternatifRekomendasiPK id;
	
	@Column(name="NOTES")
	private String notes;
	@Column(name="STATUSACTIVE")
	private boolean statusActive;

	
	@ManyToOne
	@JoinColumn(name="IDTEMUAN", referencedColumnName="id")
	private TabelTemuan temuanBean;
	
	@ManyToOne
	@JoinColumn(name="IDREKOMENDASI", referencedColumnName="id")
	private TabelRekomendasi rekomendasiBean;
	
}