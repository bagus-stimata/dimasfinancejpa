package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.vaadin.ui.CheckBox;


/**
 * The persistent class for the BBANKDETAIL database table.
 * 
 */
@Entity
@Table(name="BBANKDETAIL")
@NamedQuery(name="Bbankdetail.findAll", query="SELECT b FROM Bbankdetail b")
public class Bbankdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@EmbeddedId
	private BbankdetailPK id;

	@NotNull
	@NotEmpty
	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="AMOUNT")
	private double amount;

	@Column(name="AMOUNTGIRO")
	private double amountgiro;

	@Column(name="CUSTNAME")
	private String custname;

	@NotNull
	@NotEmpty
	@Column(name="DEBETKREDIT")
	private String debetkredit;

	@Column(name="GL")
	private boolean gl;

	@Column(name="TRANSTYPE")
	private String transtype;

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
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="transtypeBean")
	private Bbanktranstype bbanktranstypeBean;
		
	@NotNull
	@ManyToOne
	@JoinColumn(name="accountBean", referencedColumnName = "id")
	private Account accountBean;
	
	//bi-directional many-to-one association to Salesman
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({@JoinColumn(name="division", referencedColumnName="division", insertable=false, updatable=false),
		@JoinColumn(name="refno", referencedColumnName="refno", insertable=false, updatable=false)})	
	private Bbankheader bbankheaderBean;
	
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


	public Bbankdetail() {
	}

	public BbankdetailPK getId() {
		return this.id;
	}

	public void setId(BbankdetailPK id) {
		this.id = id;
	}



	public Salesman getSalesmanBean() {
		return salesmanBean;
	}

	public void setSalesmanBean(Salesman salesmanBean) {
		this.salesmanBean = salesmanBean;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmountgiro() {
		return this.amountgiro;
	}

	public void setAmountgiro(double amountgiro) {
		this.amountgiro = amountgiro;
	}

	public String getCustname() {
		return this.custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}


	public String getDebetkredit() {
		return this.debetkredit;
	}

	public void setDebetkredit(String debetkredit) {
		this.debetkredit = debetkredit;
	}

	

	public boolean isGl() {
		return gl;
	}

	public void setGl(boolean gl) {
		this.gl = gl;
	}

	public String getTranstype() {
		return this.transtype;
	}

	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	public Bbankheader getBbankheaderBean() {
		return bbankheaderBean;
	}

	public void setBbankheaderBean(Bbankheader bbankheaderBean) {
		this.bbankheaderBean = bbankheaderBean;
	}

	public Customer getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(Customer customerBean) {
		this.customerBean = customerBean;
	}

	public Division getDivisionBean() {
		return divisionBean;
	}

	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
	}

	public Account getAccountBean() {
		return accountBean;
	}

	public void setAccountBean(Account accountBean) {
		this.accountBean = accountBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bbanktranstype getBbanktranstypeBean() {
		return bbanktranstypeBean;
	}

	public void setBbanktranstypeBean(Bbanktranstype bbanktranstypeBean) {
		this.bbanktranstypeBean = bbanktranstypeBean;
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
		Bbankdetail other = (Bbankdetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}