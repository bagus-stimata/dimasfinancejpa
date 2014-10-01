package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerPK implements Serializable{

	//BAIK custno dan division DI BERIKAN NILAI OLEH divisionBean	
	@Column(name="CUSTNO")
	private String custno;
	
	@Basic(optional=false)
	@Column(name="DIVISION", updatable = false, insertable = false)
	private String division;
	
	public String getCustno() {
		return custno;
	}
	public void setCustno(String custno) {
		this.custno = custno;
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
		result = prime * result + ((custno == null) ? 0 : custno.hashCode());
		result = prime * result
				+ ((division == null) ? 0 : division.hashCode());
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
		CustomerPK other = (CustomerPK) obj;
		if (custno == null) {
			if (other.custno != null)
				return false;
		} else if (!custno.equals(other.custno))
			return false;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return custno + " - " + division;
	}

	
	
}
