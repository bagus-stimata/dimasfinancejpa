package org.dimas.finance.model;

import java.util.Date;

import javax.persistence.Id;

public class SysCalenderPekan {
	@Id
	private int pekan;
	private int periode;
	private String hariAwalPekan;
	private Date tglAwalPekan;
	private String hariAkhirPekan;
	private Date tglAkhirPekan;
	public int getPeriode() {
		return periode;
	}
	public void setPeriode(int periode) {
		this.periode = periode;
	}
	public int getPekan() {
		return pekan;
	}
	public void setPekan(int pekan) {
		this.pekan = pekan;
	}
	public String getHariAwalPekan() {
		return hariAwalPekan;
	}
	public void setHariAwalPekan(String hariAwalPekan) {
		this.hariAwalPekan = hariAwalPekan;
	}
	public Date getTglAwalPekan() {
		return tglAwalPekan;
	}
	public void setTglAwalPekan(Date tglAwalPekan) {
		this.tglAwalPekan = tglAwalPekan;
	}
	public String getHariAkhirPekan() {
		return hariAkhirPekan;
	}
	public void setHariAkhirPekan(String hariAkhirPekan) {
		this.hariAkhirPekan = hariAkhirPekan;
	}
	public Date getTglAkhirPekan() {
		return tglAkhirPekan;
	}
	public void setTglAkhirPekan(Date tglAkhirPekan) {
		this.tglAkhirPekan = tglAkhirPekan;
	}
	
	
}
