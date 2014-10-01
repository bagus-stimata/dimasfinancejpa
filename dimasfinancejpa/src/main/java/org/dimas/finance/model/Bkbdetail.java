package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.vaadin.ui.CheckBox;


/**
 * The persistent class for the BKBDETAIL database table.
 * 
 */
@Entity
@Table(name="BKBDETAIL")
@NamedQuery(name="Bkbdetail.findAll", query="SELECT b FROM Bkbdetail b")
public class Bkbdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BkbdetailPK id;

	@Column(name="AMOUNT")
	private double amount;

	@Column(name="CUSTNAME")
	private String custname;

	@NotNull
	@NotEmpty
	@Column(name="DESCRIPTION")
	private String description;
	
	@NotNull
	@NotEmpty
	@Column(name="DEBETKREDIT")
	private String debetkredit;

	@Column(name="GL")
	private boolean gl;

	//**************
		@Transient
		private int urut;
		
		public int getUrut() {
			return urut;
		}	
		public void setUrut(int urut) {
			this.urut = urut;
		}
	
		@Transient
		CheckBox selected = new CheckBox();

		public CheckBox getSelected() {
			return selected;
		}

		public void setSelected(CheckBox selected) {
			this.selected = selected;
		}
		
	//**************
	
	//bi-directional many-to-one association to Salesman
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="division", referencedColumnName="division", insertable=false, updatable=false),
		@JoinColumn(name="refno", referencedColumnName="refno", insertable=false, updatable=false)})	
	private Bkbheader bkbheaderBean;
	
	//bi-directional many-to-one association to Salesman
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="division", referencedColumnName="division", insertable=false, updatable=false),
		@JoinColumn(name="customerBean", referencedColumnName="custno")})	
	private Customer customerBean;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="division", referencedColumnName="division", insertable=false, updatable=false),
		@JoinColumn(name="salesmanBean", referencedColumnName="spcode")})	
	private Salesman salesmanBean;
	
	//bi-directional many-to-one association to Division
	@ManyToOne
	@JoinColumn(name="division", referencedColumnName = "id")
	private Division divisionBean;
	//bi-directional many-to-one association to Division

	@NotNull
	@ManyToOne
	@JoinColumn(name="accountBean", referencedColumnName = "id")
	private Account accountBean;


	@NotNull
	@ManyToOne
	@JoinColumn(name="transtypeBean")
	private Bkbtranstype bkbtranstypeBean;
	

	public Bkbdetail() {
	}


	public Salesman getSalesmanBean() {
		return salesmanBean;
	}
	public void setSalesmanBean(Salesman salesmanBean) {
		this.salesmanBean = salesmanBean;
	}
	public BkbdetailPK getId() {
		return this.id;
	}

	public void setId(BkbdetailPK id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCustname() {
		return this.custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}


	public Customer getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(Customer customerBean) {
		this.customerBean = customerBean;
	}

	public String getDebetkredit() {
		return this.debetkredit;
	}

	public void setDebetkredit(String debetkredit) {
		this.debetkredit = debetkredit;
	}

	
	public Bkbtranstype getBkbtranstypeBean() {
		return bkbtranstypeBean;
	}
	public void setBkbtranstypeBean(Bkbtranstype bkbtranstypeBean) {
		this.bkbtranstypeBean = bkbtranstypeBean;
	}
	public boolean isGl() {
		return gl;
	}

	public void setGl(boolean gl) {
		this.gl = gl;
	}

	public Bkbheader getBkbheaderBean() {
		return bkbheaderBean;
	}

	public void setBkbheaderBean(Bkbheader bkbheaderBean) {
		this.bkbheaderBean = bkbheaderBean;
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

	public Account getAccountBean() {
		return accountBean;
	}

	public void setAccountBean(Account accountBean) {
		this.accountBean = accountBean;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		Bkbdetail other = (Bkbdetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}