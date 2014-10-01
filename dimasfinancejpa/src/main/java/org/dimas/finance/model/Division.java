package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the DIVISION database table.
 * 
 */
@Entity
@Table(name="DIVISION")
@NamedQuery(name="Division.findAll", query="SELECT d FROM Division d")
public class Division implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private String id;

	@Column(name="DIVISI")
	private String divisi;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="divisionBean")
	private Set<Account> accounts;

	//bi-directional many-to-one association to Arinvoice
	@OneToMany(mappedBy="divisionBean")
	private Set<Arinvoice> arinvoices;

	//bi-directional many-to-one association to Arinvoice
	@OneToMany(mappedBy="divisionBean")
	private Set<Arpaymentdetail> arpaymentdetails;
	
	//bi-directional many-to-one association to Arretur
	@OneToMany(mappedBy="divisionBean")
	private Set<Arretur> arreturs;

	//bi-directional many-to-one association to Configscyllatofinance
	@OneToMany(mappedBy="divisionBean")
	private Set<Configscyllatofinance> configscyllatofinances;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="divisionBean")
	private Set<Customer> customers;

	//bi-directional many-to-one association to Area
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="area")
	private Area areaBean;

	//bi-directional many-to-one association to Departement
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="departement")
	private Departement departementBean;

	//bi-directional many-to-one association to Journaldetail
	@OneToMany(mappedBy="divisionBean")
	private Set<Journaldetail> journaldetails;

	//bi-directional many-to-one association to Salesman
	@OneToMany(mappedBy="divisionBean")
	private Set<Salesman> salesmans;

	public Division() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDivisi() {
		return this.divisi;
	}

	public void setDivisi(String divisi) {
		this.divisi = divisi;
	}

	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setDivisionBean(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setDivisionBean(null);

		return account;
	}

	public Set<Arinvoice> getArinvoices() {
		return this.arinvoices;
	}

	public void setArinvoices(Set<Arinvoice> arinvoices) {
		this.arinvoices = arinvoices;
	}

	public Arinvoice addArinvoice(Arinvoice arinvoice) {
		getArinvoices().add(arinvoice);
		arinvoice.setDivisionBean(this);

		return arinvoice;
	}

	public Arinvoice removeArinvoice(Arinvoice arinvoice) {
		getArinvoices().remove(arinvoice);
		arinvoice.setDivisionBean(null);

		return arinvoice;
	}

	public Arpaymentdetail addArpaymentdetail(Arpaymentdetail arpaymentdetail){
		getArpaymentdetails().add(arpaymentdetail);
		arpaymentdetail.setDivisionBean(this);
		
		return arpaymentdetail;
	}
	public Arpaymentdetail removeArpaymentdetail(Arpaymentdetail arpaymentdetail){
		getArpaymentdetails().remove(arpaymentdetail);
		arpaymentdetail.setDivisionBean(null);
		
		return arpaymentdetail;
	}
	
	public Set<Arpaymentdetail> getArpaymentdetails() {
		return arpaymentdetails;
	}

	public void setArpaymentdetails(Set<Arpaymentdetail> arpaymentdetails) {
		this.arpaymentdetails = arpaymentdetails;
	}

	public Set<Arretur> getArreturs() {
		return this.arreturs;
	}

	public void setArreturs(Set<Arretur> arreturs) {
		this.arreturs = arreturs;
	}

	public Arretur addArretur(Arretur arretur) {
		getArreturs().add(arretur);
		arretur.setDivisionBean(this);

		return arretur;
	}

	public Arretur removeArretur(Arretur arretur) {
		getArreturs().remove(arretur);
		arretur.setDivisionBean(null);

		return arretur;
	}

	public Set<Configscyllatofinance> getConfigscyllatofinances() {
		return this.configscyllatofinances;
	}

	public void setConfigscyllatofinances(Set<Configscyllatofinance> configscyllatofinances) {
		this.configscyllatofinances = configscyllatofinances;
	}

	public Configscyllatofinance addConfigscyllatofinance(Configscyllatofinance configscyllatofinance) {
		getConfigscyllatofinances().add(configscyllatofinance);
		configscyllatofinance.setDivisionBean(this);

		return configscyllatofinance;
	}

	public Configscyllatofinance removeConfigscyllatofinance(Configscyllatofinance configscyllatofinance) {
		getConfigscyllatofinances().remove(configscyllatofinance);
		configscyllatofinance.setDivisionBean(null);

		return configscyllatofinance;
	}

	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setDivisionBean(this);

		return customer;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setDivisionBean(null);

		return customer;
	}

	public Area getAreaBean() {
		return this.areaBean;
	}

	public void setAreaBean(Area areaBean) {
		this.areaBean = areaBean;
	}

	public Departement getDepartementBean() {
		return this.departementBean;
	}

	public void setDepartementBean(Departement departementBean) {
		this.departementBean = departementBean;
	}

	public Set<Journaldetail> getJournaldetails() {
		return this.journaldetails;
	}

	public void setJournaldetails(Set<Journaldetail> journaldetails) {
		this.journaldetails = journaldetails;
	}

	public Journaldetail addJournaldetail(Journaldetail journaldetail) {
		getJournaldetails().add(journaldetail);
		journaldetail.setDivisionBean(this);

		return journaldetail;
	}

	public Journaldetail removeJournaldetail(Journaldetail journaldetail) {
		getJournaldetails().remove(journaldetail);
		journaldetail.setDivisionBean(null);

		return journaldetail;
	}

	public Set<Salesman> getSalesmans() {
		return this.salesmans;
	}

	public void setSalesmans(Set<Salesman> salesmans) {
		this.salesmans = salesmans;
	}

	public Salesman addSalesman(Salesman salesman) {
		getSalesmans().add(salesman);
		salesman.setDivisionBean(this);

		return salesman;
	}

	public Salesman removeSalesman(Salesman salesman) {
		getSalesmans().remove(salesman);
		salesman.setDivisionBean(null);

		return salesman;
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
		Division other = (Division) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " - " + divisi;
	}

	
	
}