package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The primary key class for the ARINVOICE database table.
 * 
 */
@Embeddable
public class ArinvoicePK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Basic(optional=false)
	@Column(name="DIVISION")
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ArinvoicePK)) {
			return false;
		}
		ArinvoicePK castOther = (ArinvoicePK)other;
		return 
			this.division.equals(castOther.division)
			&& this.invoiceno.equals(castOther.invoiceno)
			&& this.tipefaktur.equals(castOther.tipefaktur);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.division.hashCode();
		hash = hash * prime + this.invoiceno.hashCode();
		hash = hash * prime + this.tipefaktur.hashCode();
		
		return hash;
	}

	@Override
	public String toString() {
		return invoiceno + " - " + division + " - " + tipefaktur;
	}
	
	
	
}