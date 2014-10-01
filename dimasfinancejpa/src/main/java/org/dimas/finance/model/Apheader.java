package org.dimas.finance.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the APHEADER database table.
 * 
 */
@Entity
@Table(name="APHEADER")
@NamedQuery(name="Apheader.findAll", query="SELECT a FROM Apheader a")
public class Apheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REFNO")
	private String refno;

	@Column(name="ADDRESS1")
	private String address1;

	@Column(name="ADDRESS2")
	private String address2;

	@Column(name="CITY1")
	private String city1;

	@Column(name="CITY2")
	private String city2;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE")
	private Date date;

	@Temporal(TemporalType.DATE)
	@Column(name="DELIVERDATE")
	private Date deliverdate;

	@Temporal(TemporalType.DATE)
	@Column(name="DUEDATE")
	private Date duedate;

	@Column(name="EXPEDISI")
	private String expedisi;

	@Column(name="LOCKUPDATE")
	private byte lockupdate;

	@Temporal(TemporalType.DATE)
	@Column(name="ORDERDATE")
	private Date orderdate;

	@Column(name="STATE")
	private String state;

	//bi-directional many-to-one association to Vendor
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="vendor")
	private Vendor vendorBean;

	public Apheader() {
	}

	public String getRefno() {
		return this.refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDeliverdate() {
		return this.deliverdate;
	}

	public void setDeliverdate(Date deliverdate) {
		this.deliverdate = deliverdate;
	}

	public Date getDuedate() {
		return this.duedate;
	}

	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}

	public String getExpedisi() {
		return this.expedisi;
	}

	public void setExpedisi(String expedisi) {
		this.expedisi = expedisi;
	}

	public byte getLockupdate() {
		return this.lockupdate;
	}

	public void setLockupdate(byte lockupdate) {
		this.lockupdate = lockupdate;
	}

	public Date getOrderdate() {
		return this.orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Vendor getVendorBean() {
		return this.vendorBean;
	}

	public void setVendorBean(Vendor vendorBean) {
		this.vendorBean = vendorBean;
	}

}