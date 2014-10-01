package org.dimas.finance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="SYSCALENDER")
public class SysCalender {
	
	@EmbeddedId
	private SysCalenderPK id;

	@Column(name="FPERIODE")
	private int fperiode;
	
	@Column(name="FWEEK")
	private int fweek;
	
	@Column(name="FYEAR")
	private int fyear;
	
	@Column(name="FDAY")
	private int fday;
	

	
	@Column(name = "WORKDATE")
	private boolean workdate;
	
	//bi-directional many-to-one association to Division
	@ManyToOne
	@JoinColumn(name="division", referencedColumnName = "id")
	private Division divisionBean;
	


	public SysCalenderPK getId() {
		return id;
	}



	public void setId(SysCalenderPK id) {
		this.id = id;
	}



	public int getFperiode() {
		return fperiode;
	}



	public void setFperiode(int fperiode) {
		this.fperiode = fperiode;
	}



	public int getFweek() {
		return fweek;
	}



	public void setFweek(int fweek) {
		this.fweek = fweek;
	}



	public int getFyear() {
		return fyear;
	}



	public void setFyear(int fyear) {
		this.fyear = fyear;
	}



	public int getFday() {
		return fday;
	}



	public void setFday(int fday) {
		this.fday = fday;
	}



	public boolean isWorkdate() {
		return workdate;
	}



	public void setWorkdate(boolean workdate) {
		this.workdate = workdate;
	}



	public Division getDivisionBean() {
		return divisionBean;
	}



	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SysCalender other = (SysCalender) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "" + id;
	}
	
	
	
	
}
