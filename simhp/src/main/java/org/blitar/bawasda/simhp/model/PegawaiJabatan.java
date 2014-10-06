package org.blitar.bawasda.simhp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PEGAWAIJABATAN")
public class PegawaiJabatan {

	@Id
	@Column(name="ID")
	private String id;
	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="STATUSACTIVE")
	private boolean statusActive;

	@OneToMany(mappedBy="pegawaiJabatanBean")
	Set<Pegawai> pegawaiSet;

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

	public Set<Pegawai> getPegawaiSet() {
		return pegawaiSet;
	}

	public void setPegawaiSet(Set<Pegawai> pegawaiSet) {
		this.pegawaiSet = pegawaiSet;
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
		PegawaiJabatan other = (PegawaiJabatan) obj;
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