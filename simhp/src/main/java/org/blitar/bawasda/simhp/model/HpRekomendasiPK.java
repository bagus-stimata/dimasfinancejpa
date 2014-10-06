package org.blitar.bawasda.simhp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Embeddable
public class HpRekomendasiPK implements Serializable{

	//from hpTemuanBean
	@Column(name="REFNO", insertable=false, updatable=false)
	private long refno;
	//from hpTemuanBean
	@Column(name="NOURUTTEMUAN", insertable=false, updatable=false)
	private int noUrutTemuan;
	//manually
	@Column(name="NOURUTREKOM")
	private int noUrutRekom;

	public long getRefno() {
		return refno;
	}

	public void setRefno(long refno) {
		this.refno = refno;
	}

	public int getNoUrutTemuan() {
		return noUrutTemuan;
	}

	public void setNoUrutTemuan(int noUrutTemuan) {
		this.noUrutTemuan = noUrutTemuan;
	}

	public int getNoUrutRekom() {
		return noUrutRekom;
	}

	public void setNoUrutRekom(int noUrutRekom) {
		this.noUrutRekom = noUrutRekom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noUrutRekom;
		result = prime * result + noUrutTemuan;
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
		HpRekomendasiPK other = (HpRekomendasiPK) obj;
		if (noUrutRekom != other.noUrutRekom)
			return false;
		if (noUrutTemuan != other.noUrutTemuan)
			return false;
		if (refno != other.refno)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return refno + " - " + noUrutTemuan + " - " + noUrutRekom;
	}

	
	
}