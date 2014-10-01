package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Set;


/**
 * The persistent class for the ACCOUNT database table.
 * 
 */
@Entity
@Table(name="ACCOUNT")
@NamedQuery(name="Account.findAll", query="SELECT a FROM Account a")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Id
	@Column(name="ID")
	private String id;

	@Column(name="ACTUALBALANCE")
	private double actualbalance;

	@Size(min=2, max=50)
	@Column(name="NAME")
	private String name;

	@NotNull(message="Tidak boleh null atau kosong")
	@NotEmpty(message="Tidak boleh null atau kosong")
	@Column(name="TIPEDEBETKREDIT")
	private String tipedebetkredit;

	//bi-directional many-to-one association to Accountgroup
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="accountgroup")
	private Accountgroup accountgroup;

	//bi-directional many-to-one association to Bank
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bank")
	private Bank bankBean;

	//bi-directional many-to-one association to Division
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="division")
	private Division divisionBean;

	//bi-directional many-to-one association to Accountbalance
	@OneToMany(mappedBy="accountBean")
	private Set<Accountbalance> accountbalances;

	//bi-directional many-to-one association to Accountbalance
	@OneToMany(mappedBy="accountBean")
	private Set<Bkbdetail> bkbdetails;

	//bi-directional many-to-one association to Accountbalance
	@OneToMany(mappedBy="accountBean")
	private Set<Bkkdetail> bkkdetails;
	

	//bi-directional many-to-one association to Accountbalance
	@OneToMany(mappedBy="accountBean")
	private Set<Bbankdetail> bbankdetails;

	//bi-directional many-to-one association to Accountbalance
	@OneToMany(mappedBy="accountBean")
	private Set<Bkbtranstype> bkbtranstypes;
	
	@OneToMany(mappedBy="accountBean")
	private Set<Bkktranstype> bkktranstypes;
	
	@OneToMany(mappedBy="accountBean")
	private Set<Bbanktranstype> bbanktranstypes;
	
	public Account() {
	}

	
	
	public Set<Bkbtranstype> getBkbtranstypes() {
		return bkbtranstypes;
	}



	public void setBkbtranstypes(Set<Bkbtranstype> bkbtranstypes) {
		this.bkbtranstypes = bkbtranstypes;
	}



	public Set<Bkkdetail> getBkkdetails() {
		return bkkdetails;
	}



	public void setBkkdetails(Set<Bkkdetail> bkkdetails) {
		this.bkkdetails = bkkdetails;
	}



	public Set<Bbankdetail> getBbankdetails() {
		return bbankdetails;
	}



	public void setBbankdetails(Set<Bbankdetail> bbankdetails) {
		this.bbankdetails = bbankdetails;
	}



	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getActualbalance() {
		return this.actualbalance;
	}

	public void setActualbalance(double actualbalance) {
		this.actualbalance = actualbalance;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTipedebetkredit() {
		return this.tipedebetkredit;
	}

	public void setTipedebetkredit(String tipedebetkredit) {
		this.tipedebetkredit = tipedebetkredit;
	}

	public Accountgroup getAccountgroup() {
		return this.accountgroup;
	}

	public void setAccountgroup(Accountgroup accountgroup) {
		this.accountgroup = accountgroup;
	}

	public Bank getBankBean() {
		return this.bankBean;
	}

	public void setBankBean(Bank bankBean) {
		this.bankBean = bankBean;
	}

	public Division getDivisionBean() {
		return this.divisionBean;
	}

	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
	}

	public Set<Accountbalance> getAccountbalances() {
		return this.accountbalances;
	}

	public void setAccountbalances(Set<Accountbalance> accountbalances) {
		this.accountbalances = accountbalances;
	}

	public Accountbalance addAccountbalance(Accountbalance accountbalance) {
		getAccountbalances().add(accountbalance);
		accountbalance.setAccountBean(this);

		return accountbalance;
	}

	public Accountbalance removeAccountbalance(Accountbalance accountbalance) {
		getAccountbalances().remove(accountbalance);
		accountbalance.setAccountBean(null);

		return accountbalance;
	}

	
	
	public Set<Bkbdetail> getBkbdetails() {
		return bkbdetails;
	}

	public void setBkbdetails(Set<Bkbdetail> bkbdetails) {
		this.bkbdetails = bkbdetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Bkktranstype> getBkktranstypes() {
		return bkktranstypes;
	}



	public void setBkktranstypes(Set<Bkktranstype> bkktranstypes) {
		this.bkktranstypes = bkktranstypes;
	}



	public Set<Bbanktranstype> getBbanktranstypes() {
		return bbanktranstypes;
	}



	public void setBbanktranstypes(Set<Bbanktranstype> bbanktranstypes) {
		this.bbanktranstypes = bbanktranstypes;
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
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " - " + name;
	}
	
	

}