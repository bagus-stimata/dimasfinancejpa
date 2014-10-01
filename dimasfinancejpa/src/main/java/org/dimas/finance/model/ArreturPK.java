package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the ARRETUR database table.
 * 
 */
@Embeddable
public class ArreturPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DIVISION", insertable=false, updatable=false)
	private String division;
	
	@Column(name="INVOICENO")
	private String invoiceno;

	@Column(name="TIPEFAKTUR")
	private String tipefaktur;



	public String getDivision() {
		return division;
	}



	public void setDivision(String division) {
		this.division = division;
	}



	public String getInvoiceno() {
		return invoiceno;
	}



	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
	}



	public String getTipefaktur() {
		return tipefaktur;
	}



	public void setTipefaktur(String tipefaktur) {
		this.tipefaktur = tipefaktur;
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
		result = prime * result
				+ ((invoiceno == null) ? 0 : invoiceno.hashCode());
		result = prime * result
				+ ((tipefaktur == null) ? 0 : tipefaktur.hashCode());
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
		ArreturPK other = (ArreturPK) obj;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (invoiceno == null) {
			if (other.invoiceno != null)
				return false;
		} else if (!invoiceno.equals(other.invoiceno))
			return false;
		if (tipefaktur == null) {
			if (other.tipefaktur != null)
				return false;
		} else if (!tipefaktur.equals(other.tipefaktur))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return invoiceno + " - " + division + " - " + tipefaktur;
	}
}