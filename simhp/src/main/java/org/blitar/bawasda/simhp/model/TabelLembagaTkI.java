package org.blitar.bawasda.simhp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TABELLEMBAGATK1")
public class TabelLembagaTkI {

	@Id
	@Column(name="ID")
	private String id;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="HASTK2")
	private boolean hasTkII;

	@OneToMany(mappedBy="lembagaTk1Bean")
	Set<TabelLembagaTkII> lembaga2Set;

	@OneToMany(mappedBy="lembagaTk1Bean")
	Set<SuratTugas> suratTugasSet;

	

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

	public boolean isHasTkII() {
		return hasTkII;
	}

	public void setHasTkII(boolean hasTkII) {
		this.hasTkII = hasTkII;
	}

	public Set<TabelLembagaTkII> getLembaga2Set() {
		return lembaga2Set;
	}

	public void setLembaga2Set(Set<TabelLembagaTkII> lembaga2Set) {
		this.lembaga2Set = lembaga2Set;
	}

	public Set<SuratTugas> getSuratTugasSet() {
		return suratTugasSet;
	}

	public void setSuratTugasSet(Set<SuratTugas> suratTugasSet) {
		this.suratTugasSet = suratTugasSet;
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
		TabelLembagaTkI other = (TabelLembagaTkI) obj;
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