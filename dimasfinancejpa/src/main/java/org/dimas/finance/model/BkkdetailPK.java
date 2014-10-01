package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the BKKDETAIL database table.
 * 
 */
@Embeddable
public class BkkdetailPK implements Serializable {

	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="REFNO")
	private String refno;

	@Column(name="NOURUT")
	private int nourut;
	
	@Column(name="DIVISION", insertable = false, updatable = false)
	private String division;

	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public int getNourut() {
		return nourut;
	}

	public void setNourut(int nourut) {
		this.nourut = nourut;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((division == null) ? 0 : division.hashCode());
		result = prime * result + nourut;
		result = prime * result + ((refno == null) ? 0 : refno.hashCode());
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
		BkkdetailPK other = (BkkdetailPK) obj;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (nourut != other.nourut)
			return false;
		if (refno == null) {
			if (other.refno != null)
				return false;
		} else if (!refno.equals(other.refno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nourut + " - " + division + " - " + refno;
	}
}