package org.blitar.bawasda.simhp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;


@Embeddable
public class TabelTemuanAlternatifRekomendasiPK implements Serializable{

	@Column(name="IDTEMUAN", insertable=false, updatable=false)
	private String idTemuan;
	
	@Column(name="IDREKOMENDASI", insertable=false, updatable=false)
	private String idRekomendasi;

	public String getIdTemuan() {
		return idTemuan;
	}

	public void setIdTemuan(String idTemuan) {
		this.idTemuan = idTemuan;
	}

	public String getIdRekomendasi() {
		return idRekomendasi;
	}

	public void setIdRekomendasi(String idRekomendasi) {
		this.idRekomendasi = idRekomendasi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idRekomendasi == null) ? 0 : idRekomendasi.hashCode());
		result = prime * result
				+ ((idTemuan == null) ? 0 : idTemuan.hashCode());
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
		TabelTemuanAlternatifRekomendasiPK other = (TabelTemuanAlternatifRekomendasiPK) obj;
		if (idRekomendasi == null) {
			if (other.idRekomendasi != null)
				return false;
		} else if (!idRekomendasi.equals(other.idRekomendasi))
			return false;
		if (idTemuan == null) {
			if (other.idTemuan != null)
				return false;
		} else if (!idTemuan.equals(other.idTemuan))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return idTemuan + " - " + idRekomendasi;
	}
	
	
}