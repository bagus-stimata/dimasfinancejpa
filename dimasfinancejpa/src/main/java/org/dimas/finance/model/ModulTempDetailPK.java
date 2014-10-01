package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ModulTempDetailPK implements Serializable{
	
	@Column(name="ID")
	private long id;
	
	@Column(name="MODUL")
	private String modul;
	
	
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getModul() {
		return modul;
	}



	public void setModul(String modul) {
		this.modul = modul;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((modul == null) ? 0 : modul.hashCode());
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
		ModulTempDetailPK other = (ModulTempDetailPK) obj;
		if (id != other.id)
			return false;
		if (modul == null) {
			if (other.modul != null)
				return false;
		} else if (!modul.equals(other.modul))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return id + " - " + modul;
	}
	
	
	
}
