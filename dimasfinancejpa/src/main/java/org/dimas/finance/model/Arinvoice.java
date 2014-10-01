package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import com.vaadin.ui.CheckBox;

import java.util.Date;
import java.util.Set;

/**
 * The persistent class for the ARINVOICE database table.
 * 
 */

@Entity
@Table(name="ARINVOICE")
@NamedQuery(name="Arinvoice.findAll", query="SELECT a FROM Arinvoice a")
public class Arinvoice implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected ArinvoicePK id;
	
	@Column(name="CUSTNAME")
	private String custname;
	@Column(name="SPNAME")
	private String spname;

	@Column(name="AMOUNT")
	private double amount;

	@Column(name="AMOUNTPAY")
	private double amountpay;

	@Column(name="DISC1")
	private double disc1;

	@Column(name="DISC2")
	private double disc2;

	@Column(name="DISC3")
	private double disc3;
	
	@Column(name="NOPO")
	private String nopo;
	
	@Column(name="RECAPNO")
	private String recapno;

	@Column(name="LOCKUPDATE")
	private boolean lockupdate;

	@Column(name="PPN")
	private double ppn;

	@Column(name="TERKIRIM")
	private boolean terkirim;

	@Column(name="TERM")
	private int term;

	@Column(name="TERTUNDACOUNTER")
	private int tertundacounter;

	@Column(name="TUNAIKREDIT")
	private String tunaikredit;
	
	@Column(name="TIPEJUAL")
	private String tipejual;
	
	@Column(name="LUNAS")
	private boolean lunas;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ORDERDATE")
	private Date orderdate;
	@Temporal(TemporalType.DATE)
	@Column(name="INVOICEDATE")
	private Date invoicedate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DUEDATE")
	private Date duedate;	
	@Temporal(TemporalType.DATE)
	@Column(name="ACTUALDUEDATE")
	private Date actualduedate;
	
	@Column(name="RETURCODE")
	private String returcode;
	
	
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
		@JoinColumns({@JoinColumn(name="salesmanDivision", referencedColumnName="division", insertable=false, updatable=false),
			@JoinColumn(name="salesman", referencedColumnName="spcode", insertable=false, updatable=false)})	
		private Salesman salesmanBean;
        
        @Column(name = "SALESMAN")
        private String salesman;
        @Column(name = "SALESMANDIVISION")
        private String salesmanDivision;
        
		@ManyToOne
		@JoinColumns({@JoinColumn(name="customerDivision", referencedColumnName="division", insertable=false, updatable=false),
			@JoinColumn(name="customer", referencedColumnName="custno", insertable=false, updatable=false)})	
		private Customer customerBean;
	
        @Column(name = "CUSTOMER")
        private String customer;
        
        @Column(name = "CUSTOMERDIVISION")
        private String customerDivision;
        	
	//AKU CUMA  BISA MEMAUKKAN DIVISION DISINI>> BUKAN DI PRIMARY KEY ATAUPUN DI salesmanBean atau customerBean
	//bi-directional many-to-one association to Division
	@ManyToOne
	@JoinColumn(name="division", referencedColumnName = "id", insertable = false, updatable = false)
	private Division divisionBean;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="invoiceBean")
	private Set<Arpaymentdetail> arpaymentDetailInvoices;
	
	@OneToMany(mappedBy="returBean")
	private Set<Arpaymentdetail> arpaymentDetailReturs;
	
	
	public Arinvoice() {
	}

	public ArinvoicePK getId() {
		return id;
	}

	public void setId(ArinvoicePK id) {
		this.id = id;
	}


	public String getCustname() {
		return custname;
	}


	public void setCustname(String custname) {
		this.custname = custname;
	}


	public String getSpname() {
		return spname;
	}


	public void setSpname(String spname) {
		this.spname = spname;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public double getAmountpay() {
		return amountpay;
	}


	public void setAmountpay(double amountpay) {
		this.amountpay = amountpay;
	}


	public double getDisc1() {
		return disc1;
	}


	public void setDisc1(double disc1) {
		this.disc1 = disc1;
	}


	public double getDisc2() {
		return disc2;
	}


	public void setDisc2(double disc2) {
		this.disc2 = disc2;
	}


	public double getDisc3() {
		return disc3;
	}


	public void setDisc3(double disc3) {
		this.disc3 = disc3;
	}


	public String getNopo() {
		return nopo;
	}


	public void setNopo(String nopo) {
		this.nopo = nopo;
	}


	public String getRecapno() {
		return recapno;
	}


	public void setRecapno(String recapno) {
		this.recapno = recapno;
	}


	public boolean isLockupdate() {
		return lockupdate;
	}


	public void setLockupdate(boolean lockupdate) {
		this.lockupdate = lockupdate;
	}


	public double getPpn() {
		return ppn;
	}


	public void setPpn(double ppn) {
		this.ppn = ppn;
	}


	public boolean isTerkirim() {
		return terkirim;
	}


	public void setTerkirim(boolean terkirim) {
		this.terkirim = terkirim;
	}


	public int getTerm() {
		return term;
	}


	public void setTerm(int term) {
		this.term = term;
	}


	public int getTertundacounter() {
		return tertundacounter;
	}


	public void setTertundacounter(int tertundacounter) {
		this.tertundacounter = tertundacounter;
	}


	public String getTunaikredit() {
		return tunaikredit;
	}


	public void setTunaikredit(String tunaikredit) {
		this.tunaikredit = tunaikredit;
	}


	public boolean isLunas() {
		return lunas;
	}


	public void setLunas(boolean lunas) {
		this.lunas = lunas;
	}


	public Date getOrderdate() {
		return orderdate;
	}


	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}


	public Date getInvoicedate() {
		return invoicedate;
	}


	public void setInvoicedate(Date invoicedate) {
		this.invoicedate = invoicedate;
	}


	public Date getDuedate() {
		return duedate;
	}


	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}


	public Date getActualduedate() {
		return actualduedate;
	}


	public void setActualduedate(Date actualduedate) {
		this.actualduedate = actualduedate;
	}


	public Salesman getSalesmanBean() {
		return salesmanBean;
	}


	public void setSalesmanBean(Salesman salesmanBean) {
		this.salesmanBean = salesmanBean;
	}


	public Division getDivisionBean() {
		return divisionBean;
	}


	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
	}


	public Customer getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(Customer customerBean) {
		this.customerBean = customerBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Arpaymentdetail> getArpaymentDetailInvoices() {
		return arpaymentDetailInvoices;
	}

	public void setArpaymentDetailInvoices(
			Set<Arpaymentdetail> arpaymentDetailInvoices) {
		this.arpaymentDetailInvoices = arpaymentDetailInvoices;
	}

	public Set<Arpaymentdetail> getArpaymentDetailReturs() {
		return arpaymentDetailReturs;
	}

	public void setArpaymentDetailReturs(Set<Arpaymentdetail> arpaymentDetailReturs) {
		this.arpaymentDetailReturs = arpaymentDetailReturs;
	}


	public String getReturcode() {
		return returcode;
	}

	public void setReturcode(String returcode) {
		this.returcode = returcode;
	}

	public String getTipejual() {
		return tipejual;
	}

	public void setTipejual(String tipejual) {
		this.tipejual = tipejual;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getSalesmanDivision() {
		return salesmanDivision;
	}

	public void setSalesmanDivision(String salesmanDivision) {
		this.salesmanDivision = salesmanDivision;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCustomerDivision() {
		return customerDivision;
	}

	public void setCustomerDivision(String customerDivision) {
		this.customerDivision = customerDivision;
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
		Arinvoice other = (Arinvoice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + id ;
	}
	

}