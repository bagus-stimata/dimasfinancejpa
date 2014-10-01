package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.vaadin.ui.CheckBox;


/**
 * The persistent class for the BKKDETAIL database table.
 * 
 */
@Entity
@Table(name="BKKDETAIL")
@NamedQuery(name="Bkkdetail.findAll", query="SELECT b FROM Bkkdetail b")
public class Bkkdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BkkdetailPK id;

	@NotNull
	@NotEmpty
	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="AMOUNT")
	private double amount;

	@Column(name="CUSTNAME")
	private String custname;

	@Column(name="CUSTNO")
	private String custno;

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
	private Bkkheader bkkheaderBean;

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
	@JoinColumn(name="transtypeBean")
	private Bkktranstype bkktranstypeBean;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="accountBean", referencedColumnName = "id")
	private Account accountBean;

	
	public Bkkdetail() {
	}

	
	
	public Customer getCustomerBean() {
		return customerBean;
	}




	public Salesman getSalesmanBean() {
		return salesmanBean;
	}

	public void setSalesmanBean(Salesman salesmanBean) {
		this.salesmanBean = salesmanBean;
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



	public Bkkheader getBkkheaderBean() {
		return bkkheaderBean;
	}



	public void setBkkheaderBean(Bkkheader bkkheaderBean) {
		this.bkkheaderBean = bkkheaderBean;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public BkkdetailPK getId() {
		return this.id;
	}

	public void setId(BkkdetailPK id) {
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

	public String getCustno() {
		return this.custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
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



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Bkktranstype getBkktranstypeBean() {
		return bkktranstypeBean;
	}

	public void setBkktranstypeBean(Bkktranstype bkktranstypeBean) {
		this.bkktranstypeBean = bkktranstypeBean;
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
		Bkkdetail other = (Bkkdetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
}