package org.blitar.bawasda.simhp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Embeddable
public class HpTemuanInvestigasiKhususPK implements Serializable{

	//from hpTemuanBean
	@Column(name="REFNO", insertable=false, updatable=false)
	private long refno;

	//from hpTemuan
	@Column(name="NOURUTTEMUAN", insertable=false, updatable=false)
	private int noUrutTemuan;


	

	
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




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		HpTemuanInvestigasiKhususPK other = (HpTemuanInvestigasiKhususPK) obj;
		if (noUrutTemuan != other.noUrutTemuan)
			return false;
		if (refno != other.refno)
			return false;
		return true;
	}




	@Override
	public String toString() {
		return noUrutTemuan + "";
	}



	
	
}