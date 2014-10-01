package org.dimas.finance.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the VENDOR database table.
 * 
 */
@Entity
@Table(name="VENDOR")
@NamedQuery(name="Vendor.findAll", query="SELECT v FROM Vendor v")
public class Vendor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDVENDOR")
	private String idvendor;

	@Column(name="ADDRESS1")
	private String address1;

	@Column(name="ADDRESS2")
	private String address2;

	@Column(name="CITY1")
	private String city1;

	@Column(name="CITY2")
	private String city2;

	@Column(name="COUNTRY")
	private String country;

	@Column(name="PHONE")
	private String phone;

	@Column(name="POSTCODE")
	private String postcode;

	@Column(name="STATE")
	private String state;

	@Column(name="VENDOR")
	private String vendor;

	//bi-directional many-to-one association to Apheader
	@OneToMany(mappedBy="vendorBean")
	private Set<Apheader> apheaders;

	public Vendor() {
	}

	public String getIdvendor() {
		return this.idvendor;
	}

	public void setIdvendor(String idvendor) {
		this.idvendor = idvendor;
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

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getVendor() {
		return this.vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Set<Apheader> getApheaders() {
		return this.apheaders;
	}

	public void setApheaders(Set<Apheader> apheaders) {
		this.apheaders = apheaders;
	}

	public Apheader addApheader(Apheader apheader) {
		getApheaders().add(apheader);
		apheader.setVendorBean(this);

		return apheader;
	}

	public Apheader removeApheader(Apheader apheader) {
		getApheaders().remove(apheader);
		apheader.setVendorBean(null);

		return apheader;
	}

}