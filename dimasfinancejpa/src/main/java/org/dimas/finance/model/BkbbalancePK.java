package org.dimas.finance.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class BkbbalancePK implements Serializable{
	@Temporal(TemporalType.DATE)
	@Column(name="FDATE")
	private Date fdate;
	
	@Column(name="DIVISION", insertable=false, updatable=false)
	private String division;

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
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
		result = prime * result + ((fdate == null) ? 0 : fdate.hashCode());
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
		BkbbalancePK other = (BkbbalancePK) obj;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (fdate == null) {
			if (other.fdate != null)
				return false;
		} else if (!fdate.equals(other.fdate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return fdate + "";
	}
	
	
	
}
