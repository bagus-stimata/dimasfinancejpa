package org.dimas.finance.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="BKBBALANCE")
public class Bkbbalance implements Serializable{
	@EmbeddedId
	private BkbbalancePK id;	
	
	@Column(name="FYEAR")
	private int fyear;
	@Column(name="FPERIODE")
	private int fperiode;	
	@Column(name="FWEEK")
	private int fweek;
	@Column(name="FDAY")
	private int fday;
	
	@Column(name="AMOUNT")
	private double amount;
	@Column(name="AMOUNTDEBET")
	private double amountDebet;
	@Column(name="AMOUNTKREDIT")	
	private double amountKredit;
	
	@Column(name="LASTREVISIONCOUNTER")
	private int lastRevisionCounter;
	@Temporal(TemporalType.DATE)
	@Column(name="LASTREVISIONDATE")
	private Date lastRevisionDate;
	@Column(name="LASTREVISIONUSERID")
	private String lastRevisionUserId;
	
	
	//bi-directional many-to-one association to Division
	@ManyToOne
	@JoinColumn(name="division", referencedColumnName = "id")
	private Division divisionBean;
	
	
	public BkbbalancePK getId() {
		return id;
	}
	public void setId(BkbbalancePK id) {
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
	public int getLastRevisionCounter() {
		return lastRevisionCounter;
	}
	public void setLastRevisionCounter(int lastRevisionCounter) {
		this.lastRevisionCounter = lastRevisionCounter;
	}
	public Date getLastRevisionDate() {
		return lastRevisionDate;
	}
	public void setLastRevisionDate(Date lastRevisionDate) {
		this.lastRevisionDate = lastRevisionDate;
	}
	public String getLastRevisionUserId() {
		return lastRevisionUserId;
	}
	public void setLastRevisionUserId(String lastRevisionUserId) {
		this.lastRevisionUserId = lastRevisionUserId;
	}
	
	public Division getDivisionBean() {
		return divisionBean;
	}
	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmountDebet() {
		return amountDebet;
	}
	public void setAmountDebet(double amountDebet) {
		this.amountDebet = amountDebet;
	}
	public double getAmountKredit() {
		return amountKredit;
	}
	public void setAmountKredit(double amountKredit) {
		this.amountKredit = amountKredit;
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
		Bkbbalance other = (Bkbbalance) obj;
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
