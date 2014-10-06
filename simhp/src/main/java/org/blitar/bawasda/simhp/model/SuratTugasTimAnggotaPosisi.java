package org.blitar.bawasda.simhp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="SURATTUGASTIMANGGOTAPOSISI")
public class SuratTugasTimAnggotaPosisi {

	@Id
	@Column(name="ID")
	private String id;	
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="STATUSACTIVE")
	private boolean statusActive;
	
	@OneToMany(mappedBy="timPositionBean")
	Set<SuratTugasTimAnggota> suratTugasTimAnggotaSet;

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

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public Set<SuratTugasTimAnggota> getSuratTugasTimAnggotaSet() {
		return suratTugasTimAnggotaSet;
	}

	public void setSuratTugasTimAnggotaSet(
			Set<SuratTugasTimAnggota> suratTugasTimAnggotaSet) {
		this.suratTugasTimAnggotaSet = suratTugasTimAnggotaSet;
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
		SuratTugasTimAnggotaPosisi other = (SuratTugasTimAnggotaPosisi) obj;
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