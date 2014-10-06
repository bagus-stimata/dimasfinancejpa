package org.blitar.bawasda.simhp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MATAUANG")
public class MataUang {

	@Id
	@Column(name="ID")
	private String id;
	@Column(name="SHORTNAME")
	private String shortName;
	@Column(name="FULLNAME")
	private String fullName;
	@Column(name="NEGARAASAL")
	private String negaraAsal;
	
	@Column(name="STATUSACTIVE")
	private boolean statusActive;
	
	@OneToMany(mappedBy="mataUangBean")
	Set<HpTemuanNilai> nilaiTemuanSet;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNegaraAsal() {
		return negaraAsal;
	}

	public void setNegaraAsal(String negaraAsal) {
		this.negaraAsal = negaraAsal;
	}

	public Set<HpTemuanNilai> getNilaiTemuanSet() {
		return nilaiTemuanSet;
	}

	public void setNilaiTemuanSet(Set<HpTemuanNilai> nilaiTemuanSet) {
		this.nilaiTemuanSet = nilaiTemuanSet;
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
		MataUang other = (MataUang) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " - " + shortName;
	}
	
	
	
}