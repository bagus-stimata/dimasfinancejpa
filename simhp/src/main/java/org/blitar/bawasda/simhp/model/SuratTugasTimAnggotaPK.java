package org.blitar.bawasda.simhp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Embeddable
public class SuratTugasTimAnggotaPK implements Serializable{

	@Column(name="REFNO")
	private long refno;

	@Column(name="NOURUT")
	private int noUrut;

	
	public long getRefno() {
		return refno;
	}


	public void setRefno(long refno) {
		this.refno = refno;
	}


	public int getNoUrut() {
		return noUrut;
	}


	public void setNoUrut(int noUrut) {
		this.noUrut = noUrut;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noUrut;
		result = prime * result + (int) (refno ^ (refno >>> 32));
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
		SuratTugasTimAnggotaPK other = (SuratTugasTimAnggotaPK) obj;
		if (noUrut != other.noUrut)
			return false;
		if (refno != other.refno)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return noUrut + "";
	}

	
	
}