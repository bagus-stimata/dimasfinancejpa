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
@Table(name="TABELTINDAKLANJUT")
public class TabelTindakLanjut {

	@Id
	@Column(name="ID")
	private String id;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="NOTES")
	private String notes;
	@Column(name="STATUSACTIVE")
	private boolean statusActive;
	@Column(name="LASTMODIFIED")
	@Temporal(TemporalType.DATE)
	private Date lastModified;
	@Column(name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	
	@ManyToOne
	@JoinColumn(name="TINDAKLANJUTGRUP", referencedColumnName="id")
	TabelTindakLanjutGrup tindakLanjutGrupBean;

	@OneToMany(mappedBy="tindakLanjutBean")
	Set<HpTindakLanjutRencana> hpTindakLanjutSet;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public TabelTindakLanjutGrup getTindakLanjutGrupBean() {
		return tindakLanjutGrupBean;
	}

	public void setTindakLanjutGrupBean(TabelTindakLanjutGrup tindakLanjutGrupBean) {
		this.tindakLanjutGrupBean = tindakLanjutGrupBean;
	}

	public Set<HpTindakLanjutRencana> getHpTindakLanjutSet() {
		return hpTindakLanjutSet;
	}

	public void setHpTindakLanjutSet(Set<HpTindakLanjutRencana> hpTindakLanjutSet) {
		this.hpTindakLanjutSet = hpTindakLanjutSet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelTindakLanjut other = (TabelTindakLanjut) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " - " + description;
	}
	
	
}