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
@Table(name="TABELLEMBAGATK3")
public class TabelLembagaTkIII {

	@Id
	@Column(name="ID")
	private String id;
	@Column(name="DESCRIPTION")
	private String description;

	@ManyToOne
	@JoinColumn(name="LEMBAGATK2")
	private TabelLembagaTkII lembagaTk2Bean;
	
	@OneToMany(mappedBy="lembagaTk3Bean")
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

	public TabelLembagaTkII getLembagaTk2Bean() {
		return lembagaTk2Bean;
	}

	public void setLembagaTk2Bean(TabelLembagaTkII lembagaTk2Bean) {
		this.lembagaTk2Bean = lembagaTk2Bean;
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
		TabelLembagaTkIII other = (TabelLembagaTkIII) obj;
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