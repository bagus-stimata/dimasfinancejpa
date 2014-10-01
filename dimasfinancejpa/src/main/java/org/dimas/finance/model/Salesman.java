package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the SALESMAN database table.
 * 
 */
@Entity
@Table(name="SALESMAN")
@NamedQuery(name="Salesman.findAll", query="SELECT s FROM Salesman s")
public class Salesman implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected SalesmanPK id;
	
	@Column(name="SALESTYPE")
	private String salestype;

	@Column(name="SPNAME")
	private String spname;

	//bi-directional many-to-one association to Arinvoice
	@OneToMany(mappedBy="salesmanBean")
	private Set<Arinvoice> arinvoices;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="division", referencedColumnName = "id")
	private Division divisionBean;

	//bi-directional many-to-one association to Arinvoice
	@OneToMany(mappedBy="salesmanBean")
	private Set<Bkbdetail> bkbdetails;

	//bi-directional many-to-one association to Arinvoice
	@OneToMany(mappedBy="salesmanBean")
	private Set<Bkkdetail> bkkdetails;
	
	//bi-directional many-to-one association to Arinvoice
	@OneToMany(mappedBy="salesmanBean")
	private Set<Bbankdetail> bbankdetails;
	
	public Salesman() {
	}


	public Set<Bkbdetail> getBkbdetails() {
		return bkbdetails;
	}


	public void setBkbdetails(Set<Bkbdetail> bkbdetails) {
		this.bkbdetails = bkbdetails;
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


	public String getSalestype() {
		return this.salestype;
	}

	public void setSalestype(String salestype) {
		this.salestype = salestype;
	}

	public String getSpname() {
		return this.spname;
	}

	public void setSpname(String spname) {
		this.spname = spname;
	}

	public Set<Arinvoice> getArinvoices() {
		return this.arinvoices;
	}

	public void setArinvoices(Set<Arinvoice> arinvoices) {
		this.arinvoices = arinvoices;
	}

	public Arinvoice addArinvoice(Arinvoice arinvoice) {
		getArinvoices().add(arinvoice);
		arinvoice.setSalesmanBean(this);

		return arinvoice;
	}

	public Arinvoice removeArinvoice(Arinvoice arinvoice) {
		getArinvoices().remove(arinvoice);
		arinvoice.setSalesmanBean(null);

		return arinvoice;
	}

//	public Set<Arretur> getArreturs() {
//		return this.arreturs;
//	}
//
//	public void setArreturs(Set<Arretur> arreturs) {
//		this.arreturs = arreturs;
//	}
//
//	public Arretur addArretur(Arretur arretur) {
//		getArreturs().add(arretur);
//		arretur.setSalesmanBean(this);
//
//		return arretur;
//	}
//
//	public Arretur removeArretur(Arretur arretur) {
//		getArreturs().remove(arretur);
//		arretur.setSalesmanBean(null);
//
//		return arretur;
//	}

	public Division getDivisionBean() {
		return this.divisionBean;
	}

	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
	}


	public SalesmanPK getId() {
		return id;
	}


	public void setId(SalesmanPK id) {
		this.id = id;
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
		Salesman other = (Salesman) obj;
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