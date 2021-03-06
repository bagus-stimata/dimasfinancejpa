package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import com.vaadin.annotations.AutoGenerated;

/**
 * The primary key class for the ARPAYMENTDETAIL database table.
 * 
 */
@Embeddable
public class ArpaymentdetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	
	@Column(name="REFNO")
	private String refno;
	
	@Column(name="NUMBER")
	private int number;
	
	
	@Column(name="INVOICENO", insertable=false, updatable=false)
	private String invoiceno;
	
	@Column(name="DIVISION", insertable=false, updatable=false)
	private String division;
	
	public String getRefno() {
		return refno;
	}
	public void setRefno(String refno) {
		this.refno = refno;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getInvoiceno() {
		return invoiceno;
	}
	public void setInvoiceno(String invoiceno) {
		this.invoiceno = invoiceno;
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
		result = prime * result
				+ ((invoiceno == null) ? 0 : invoiceno.hashCode());
		result = prime * result + number;
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
		ArpaymentdetailPK other = (ArpaymentdetailPK) obj;
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
		if (number != other.number)
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
		return number + "- " + refno + " - " + invoiceno + " - " + division;
	}
	
	
	

	
	
}
