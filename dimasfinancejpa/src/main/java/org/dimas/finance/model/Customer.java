package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the CUSTOMER database table.
 * 
 */
@Entity
@Table(name="CUSTOMER")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected CustomerPK id;
	
	@Column(name="ADDRESS1")
	private String address1;

	@Column(name="ADDRESS2")
	private String address2;

	@Column(name="CITY1")
	private String city1;

	@Column(name="CITY2")
	private String city2;

	@Column(name="CONTACTPERSON")
	private String contactperson;

	@Column(name="CREDITLIMIT")
	private double creditlimit;

	@Column(name="CRTERM")
	private int crterm;

	@Column(name="CUSTNAME")
	private String custname;

	@Column(name="HP")
	private String hp;

	@Temporal(TemporalType.DATE)
	@Column(name="JOINDATE")
	private Date joindate;

	@Temporal(TemporalType.DATE)
	@Column(name="LASTTRANS")
	private Date lasttrans;

	@Temporal(TemporalType.DATE)
	@Column(name="LASTUPDATE")
	private Date lastupdate;

	@Column(name="PHONE")
	private String phone;

	@Column(name="POSTCODE")
	private String postcode;

	@Column(name="STATE")
	private String state;

	@Column(name="TUNAIKREDIT")
	private String tunaikredit;

	@Column(name="TYPECUSTOMER")
	private String typecustomer;

	//bi-directional many-to-one association to Arinvoice
	@OneToMany(mappedBy="customerBean")
	private Set<Arinvoice> arinvoices;
	
	//bi-directional many-to-one association to Arinvoice
	@OneToMany(mappedBy="customerBean")
	private Set<Bkbdetail> bkbdetails;

	//bi-directional many-to-one association to Arinvoice
	@OneToMany(mappedBy="customerBean")
	private Set<Bkkdetail> bkkdetails;
	
	//bi-directional many-to-one association to Arinvoice
	@OneToMany(mappedBy="customerBean")
	private Set<Bbankdetail> bbankdetails;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="division", referencedColumnName = "id")
	private Division divisionBean;
	
	
//	//bi-directional many-to-one association to Arretur
//	@OneToMany(mappedBy="customerBean")
//	private Set<Arretur> arreturs;

	//bi-directional many-to-one association to Division
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="division")
//	private Division divisionBean;

	public Set<Bkbdetail> getBkbdetails() {
		return bkbdetails;
	}


	public Set<Bkkdetail> getBkkdetails() {
		return bkkdetails;
	}


	public void setBkkdetails(Set<Bkkdetail> bkkdetails) {
		this.bkkdetails = bkkdetails;
	}


	public void setBkbdetails(Set<Bkbdetail> bkbdetails) {
		this.bkbdetails = bkbdetails;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	public Customer() {
	}


	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity1() {
		return this.city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getCity2() {
		return this.city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public String getContactperson() {
		return this.contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public double getCreditlimit() {
		return this.creditlimit;
	}

	public void setCreditlimit(double creditlimit) {
		this.creditlimit = creditlimit;
	}

	public int getCrterm() {
		return this.crterm;
	}

	public void setCrterm(int crterm) {
		this.crterm = crterm;
	}

	public String getCustname() {
		return this.custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getHp() {
		return this.hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public Date getJoindate() {
		return this.joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public Date getLasttrans() {
		return this.lasttrans;
	}

	public void setLasttrans(Date lasttrans) {
		this.lasttrans = lasttrans;
	}

	public Date getLastupdate() {
		return this.lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTunaikredit() {
		return this.tunaikredit;
	}

	public void setTunaikredit(String tunaikredit) {
		this.tunaikredit = tunaikredit;
	}

	public String getTypecustomer() {
		return this.typecustomer;
	}

	public void setTypecustomer(String typecustomer) {
		this.typecustomer = typecustomer;
	}

	public Set<Arinvoice> getArinvoices() {
		return this.arinvoices;
	}

	public void setArinvoices(Set<Arinvoice> arinvoices) {
		this.arinvoices = arinvoices;
	}

	public Arinvoice addArinvoice(Arinvoice arinvoice) {
		getArinvoices().add(arinvoice);
		arinvoice.setCustomerBean(this);

		return arinvoice;
	}

	public Arinvoice removeArinvoice(Arinvoice arinvoice) {
		getArinvoices().remove(arinvoice);
		arinvoice.setCustomerBean(null);

		return arinvoice;
	}


	public Division getDivisionBean() {
		return this.divisionBean;
	}

	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
	}


	public CustomerPK getId() {
		return id;
	}


	public void setId(CustomerPK id) {
		this.id = id;
	}


	public Set<Bbankdetail> getBbankdetails() {
		return bbankdetails;
	}


	public void setBbankdetails(Set<Bbankdetail> bbankdetails) {
		this.bbankdetails = bbankdetails;
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
		Customer other = (Customer) obj;
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