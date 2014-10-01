package org.dimas.finance.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the ACCOUNTBALANCE database table.
 * 
 */
@Embeddable
public class AccountbalancePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNTID")
	private String accountid;

	@Temporal(TemporalType.DATE)
	@Column(name="FDATE")
	private Date fdate;
	
	@Column(name="DIVISION", insertable=false, updatable=false)
	private String division;

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountid == null) ? 0 : accountid.hashCode());
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
		AccountbalancePK other = (AccountbalancePK) obj;
		if (accountid == null) {
			if (other.accountid != null)
				return false;
		} else if (!accountid.equals(other.accountid))
			return false;
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
		return  division + " - " + fdate + " - " +  accountid;
	}

	
}