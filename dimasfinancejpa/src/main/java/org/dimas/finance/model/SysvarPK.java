package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Embeddable
public class SysvarPK implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="IDSYSVAR")
    private String idSysvar;
    
	@Column(name="DIVISION", insertable=false, updatable=false)
	private String division;

	public String getIdSysvar() {
		return idSysvar;
	}

	public void setIdSysvar(String idSysvar) {
		this.idSysvar = idSysvar;
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
		result = prime * result
				+ ((idSysvar == null) ? 0 : idSysvar.hashCode());
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
		SysvarPK other = (SysvarPK) obj;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (idSysvar == null) {
			if (other.idSysvar != null)
				return false;
		} else if (!idSysvar.equals(other.idSysvar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return division +  " - " + idSysvar ;
	}
	
	
	
	
	
}
