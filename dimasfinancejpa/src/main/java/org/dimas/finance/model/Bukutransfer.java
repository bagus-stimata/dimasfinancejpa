package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the GIROTRANSFER database table.
 * 
 */
@Entity
@Table(name="BUKUTRANSFER")
@NamedQuery(name="Bukutransfer.findAll", query="SELECT g FROM Bukutransfer g")
public class Bukutransfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected BukutransferPK id;
	
	@Column(name="TRANSFERNUMBER")
	private String transfernumber;

	@Column(name="NASABAH")
	private String nasabah;
	
	@Column(name="AMOUNT")
	private double amount;

	@Column(name="AMOUNTUSED")
	private double amountused;

	@Temporal(TemporalType.DATE)
	@Column(name="transferdate")
	private Date transferdate;

	@OneToMany(mappedBy="bukutransferBean")
	private Set<Arpaymentdetail> arpaymentdetails;


	//bi-directional many-to-one association to Bank
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bank")
	private Bank bankBean;

	//bi-directional many-to-one association to Division
	@ManyToOne
	@JoinColumn(name="division", referencedColumnName = "id")
	private Division divisionBean;
	
	
//CONSTRUCTOR NEEDED	
	public Bukutransfer() {
	}

	public Arpaymentdetail addArpaymentdetail(Arpaymentdetail arpaymentdetail) {
		getArpaymentdetails().add(arpaymentdetail);
		arpaymentdetail.setBukutransferBean(this);
		return arpaymentdetail;
	}

	public Arpaymentdetail removeArpaymentdetail(Arpaymentdetail arpaymentdetail) {
		getArpaymentdetails().remove(arpaymentdetail);
		arpaymentdetail.setBukutransferBean(null);
		return arpaymentdetail;
	}
	

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Date getTransferdate() {
		return transferdate;
	}


	public void setTransferdate(Date transferdate) {
		this.transferdate = transferdate;
	}


	public String getNasabah() {
		return nasabah;
	}


	public void setNasabah(String nasabah) {
		this.nasabah = nasabah;
	}


	public Bank getBankBean() {
		return this.bankBean;
	}

	public void setBankBean(Bank bankBean) {
		this.bankBean = bankBean;
	}


	public Set<Arpaymentdetail> getArpaymentdetails() {
		return arpaymentdetails;
	}


	public void setArpaymentdetails(Set<Arpaymentdetail> arpaymentdetails) {
		this.arpaymentdetails = arpaymentdetails;
	}


	public double getAmountused() {
		return amountused;
	}


	public void setAmountused(double amountused) {
		this.amountused = amountused;
	}


	public String getTransfernumber() {
		return transfernumber;
	}

	public void setTransfernumber(String transfernumber) {
		this.transfernumber = transfernumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BukutransferPK getId() {
		return id;
	}

	public void setId(BukutransferPK id) {
		this.id = id;
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
		Bukutransfer other = (Bukutransfer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}