package org.blitar.bawasda.simhp.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PEGAWAI")
public class Pegawai {

	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="NAME")
	private String name;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="CITY")
	private String city;
	@Column(name="POSTCODE")
	private String postcode;
	@Column(name="PHONE")
	private String phone;
	@Column(name="NIP")
	private String nip;
	
	@Column(name="STATUSACTIVE")
	private boolean statusActive;
	
	@Column(name="BORNPLACE")
	private String bornplace;
	
	@Column(name="BORNDATE")
	@Temporal(TemporalType.DATE)
	private Date bornDate;
	
	@Column(name="GOL")
	private String gol;

	@ManyToOne
	@JoinColumn(name="PEGAWAIJABATAN")
	PegawaiJabatan pegawaiJabatanBean;
	
	@OneToMany(mappedBy="pegawaiBean")
	Set<SuratTugasTimAnggota> anggotaSet;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public boolean isStatusActive() {
		return statusActive;
	}

	public void setStatusActive(boolean statusActive) {
		this.statusActive = statusActive;
	}

	public String getBornplace() {
		return bornplace;
	}

	public void setBornplace(String bornplace) {
		this.bornplace = bornplace;
	}

	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}

	public String getGol() {
		return gol;
	}

	public void setGol(String gol) {
		this.gol = gol;
	}

	public PegawaiJabatan getPegawaiJabatanBean() {
		return pegawaiJabatanBean;
	}

	public void setPegawaiJabatanBean(PegawaiJabatan pegawaiJabatanBean) {
		this.pegawaiJabatanBean = pegawaiJabatanBean;
	}

	public Set<SuratTugasTimAnggota> getAnggotaSet() {
		return anggotaSet;
	}

	public void setAnggotaSet(Set<SuratTugasTimAnggota> anggotaSet) {
		this.anggotaSet = anggotaSet;
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
		Pegawai other = (Pegawai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " - " + name;
	}
	
	
	
}