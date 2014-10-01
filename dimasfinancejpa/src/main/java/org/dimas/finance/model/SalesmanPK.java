package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SalesmanPK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//BAIK spcode dan division DI BERIKAN NILAI OLEH divisionBean
	@Column(name="SPCODE")
	private String spcode;
	
	@Basic(optional=false)
	@Column(name="DIVISION", insertable=false, updatable=false)
	private String division;

	public String getSpcode() {
		return spcode;
	}

	public void setSpcode(String spcode) {
		this.spcode = spcode;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((division == null) ? 0 : division.hashCode());
		result = prime * result + ((spcode == null) ? 0 : spcode.hashCode());
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
		SalesmanPK other = (SalesmanPK) obj;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (spcode == null) {
			if (other.spcode != null)
				return false;
		} else if (!spcode.equals(other.spcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return spcode + " - " + division;
	}
	
	
}
