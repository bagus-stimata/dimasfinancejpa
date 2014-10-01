package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the ARRETUR database table.
 * 
 */

@Entity
@Table(name="ARRETUR")
@NamedQuery(name="Arretur.findAll", query="SELECT a FROM Arretur a")
public class Arretur implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ArreturPK id;

	@Column(name="AMOUNT")
	private double amount;

	@Column(name="AMOUNTPAY")
	private double amountpay;

	@Column(name="DISC1")
	private double disc1;

	@Column(name="DISC2")
	private double disc2;

	@Column(name="DIVISI")
	private String divisi;

	@Column(name="NOPO")
	private String nopo;

	@Column(name="LOCKUPDATE")
	private byte lockupdate;

	@Column(name="NAMA")
	private String nama;

	@Column(name="PERCENTDISC1")
	private double percentdisc1;

	@Column(name="PERCENTDISC2")
	private double percentdisc2;

	@Column(name="PPN")
	private double ppn;

	@Temporal(TemporalType.DATE)
	@Column(name="RETURNDATE")
	private Date returndate;

	@Column(name="RETURNO")
	private String returno;

//	//bi-directional many-to-one association to Customer
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="customer")
//	private Customer customerBean;

	//bi-directional many-to-one association to Division
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="division")
	private Division divisionBean;

	//bi-directional many-to-one association to Salesman
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="salesman")
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumns({@JoinColumn(name="division", referencedColumnName="division"),
//		@JoinColumn(name="salesmanBean", referencedColumnName="spcode")})		
//	private Salesman salesmanBean;

	public Arretur() {
	}

	public ArreturPK getId() {
		return this.id;
	}

	public void setId(ArreturPK id) {
		this.id = id;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmountpay() {
		return this.amountpay;
	}

	public void setAmountpay(double amountpay) {
		this.amountpay = amountpay;
	}

	public double getDisc1() {
		return this.disc1;
	}

	public void setDisc1(double disc1) {
		this.disc1 = disc1;
	}

	public double getDisc2() {
		return this.disc2;
	}

	public void setDisc2(double disc2) {
		this.disc2 = disc2;
	}

	public String getDivisi() {
		return this.divisi;
	}

	public void setDivisi(String divisi) {
		this.divisi = divisi;
	}


	public String getNopo() {
		return nopo;
	}

	public void setNopo(String nopo) {
		this.nopo = nopo;
	}

	public byte getLockupdate() {
		return this.lockupdate;
	}

	public void setLockupdate(byte lockupdate) {
		this.lockupdate = lockupdate;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public double getPercentdisc1() {
		return this.percentdisc1;
	}

	public void setPercentdisc1(double percentdisc1) {
		this.percentdisc1 = percentdisc1;
	}

	public double getPercentdisc2() {
		return this.percentdisc2;
	}

	public void setPercentdisc2(double percentdisc2) {
		this.percentdisc2 = percentdisc2;
	}

	public double getPpn() {
		return this.ppn;
	}

	public void setPpn(double ppn) {
		this.ppn = ppn;
	}

	public Date getReturndate() {
		return this.returndate;
	}

	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}

	public String getReturno() {
		return this.returno;
	}

	public void setReturno(String returno) {
		this.returno = returno;
	}

//	public Customer getCustomerBean() {
//		return this.customerBean;
//	}
//
//	public void setCustomerBean(Customer customerBean) {
//		this.customerBean = customerBean;
//	}

	public Division getDivisionBean() {
		return this.divisionBean;
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
	public String toString() {
		return "" + id;
	}

	
}