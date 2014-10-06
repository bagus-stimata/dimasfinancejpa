package org.blitar.bawasda.simhp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TABELLEMBAGATK2")
public class TabelLembagaTkII {

	@Id
	@Column(name="ID")
	private String id;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="HASTK3")
	private boolean hasTkIII;

	@ManyToOne
	@JoinColumn(name="LEMBAGATK1")
	private TabelLembagaTkI lembagaTk1Bean;
	
	@OneToMany(mappedBy="lembagaTk2Bean")
	Set<TabelLembagaTkIII> lembaga3Set;
	
	@OneToMany(mappedBy="lembagaTk2Bean")
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


	public boolean isHasTkIII() {
		return hasTkIII;
	}

	public void setHasTkIII(boolean hasTkIII) {
		this.hasTkIII = hasTkIII;
	}

	public TabelLembagaTkI getLembagaTk1Bean() {
		return lembagaTk1Bean;
	}

	public void setLembagaTk1Bean(TabelLembagaTkI lembagaTk1Bean) {
		this.lembagaTk1Bean = lembagaTk1Bean;
	}

	public Set<TabelLembagaTkIII> getLembaga3Set() {
		return lembaga3Set;
	}

	public void setLembaga3Set(Set<TabelLembagaTkIII> lembaga3Set) {
		this.lembaga3Set = lembaga3Set;
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
		TabelLembagaTkII other = (TabelLembagaTkII) obj;
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