package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the BUKUGIRO database table.
 * 
 */
@Entity
@Table(name="BUKUGIRO")
@NamedQuery(name="Bukugiro.findAll", query="SELECT b FROM Bukugiro b")
public class Bukugiro implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected BukugiroPK id;
	
	@Column(name="NASABAH")
	private String nasabah;
	
	@Column(name="AMOUNT")
	private double amount;

	@Temporal(TemporalType.DATE)
	@Column(name="GIRODATE")
	private Date girodate;

	@Temporal(TemporalType.DATE)
	@Column(name="GIRODUEDATE")
	private Date giroduedate;

	@Column(name="GIRONAME")
	private String gironame;

	@Column(name="GIRONUMBER")
	private String gironumber;

	@Column(name="AMOUNTUSED")
	private double amountused;

	@OneToMany(mappedBy="bukugiroBean")
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
	public Bukugiro() {
	}

	public Arpaymentdetail addArpaymentdetail(Arpaymentdetail arpaymentdetail) {
		getArpaymentdetails().add(arpaymentdetail);
		arpaymentdetail.setBukugiroBean(this);

		return arpaymentdetail;
	}

	public Arpaymentdetail removeArpaymentdetail(Arpaymentdetail arpaymentdetail) {
		getArpaymentdetails().remove(arpaymentdetail);
		arpaymentdetail.setBukugiroBean(null);

		return arpaymentdetail;
	}
	
	

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getGirodate() {
		return this.girodate;
	}

	public void setGirodate(Date girodate) {
		this.girodate = girodate;
	}

	public Date getGiroduedate() {
		return this.giroduedate;
	}

	public void setGiroduedate(Date giroduedate) {
		this.giroduedate = giroduedate;
	}

	public String getGironame() {
		return this.gironame;
	}

	public void setGironame(String gironame) {
		this.gironame = gironame;
	}

	public String getGironumber() {
		return this.gironumber;
	}

	public void setGironumber(String gironumber) {
		this.gironumber = gironumber;
	}


	public double getAmountused() {
		return amountused;
	}

	public void setAmountused(double amountused) {
		this.amountused = amountused;
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

	public BukugiroPK getId() {
		return id;
	}

	public void setId(BukugiroPK id) {
		this.id = id;
	}

	public String getNasabah() {
		return nasabah;
	}

	public void setNasabah(String nasabah) {
		this.nasabah = nasabah;
	}

	public Division getDivisionBean() {
		return divisionBean;
	}

	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Bukugiro other = (Bukugiro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	
}