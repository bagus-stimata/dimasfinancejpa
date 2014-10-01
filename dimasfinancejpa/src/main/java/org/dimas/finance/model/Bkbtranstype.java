package org.dimas.finance.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="BKBTRANSTYPE")
public class Bkbtranstype implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Id
	@Column(name="ID")
	private String id;

	@NotNull
	@Column(name="DESCRIPTION")
	private String description;
	
	@NotNull
	@Column(name="GRUP")
	private String grup;
	
	@ManyToOne
	@JoinColumn(name="accountBean", referencedColumnName = "id")
	private Account accountBean;

	//bi-directional many-to-one association to Accountbalance
	@OneToMany(mappedBy="bkbtranstypeBean")
	private Set<Bkbdetail> bkbdetails;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public Set<Bkbdetail> getBkbdetails() {
		return bkbdetails;
	}

	public void setBkbdetails(Set<Bkbdetail> bkbdetails) {
		this.bkbdetails = bkbdetails;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getAccountBean() {
		return accountBean;
	}

	public void setAccountBean(Account accountBean) {
		this.accountBean = accountBean;
	}

	public String getGrup() {
		return grup;
	}

	public void setGrup(String grup) {
		this.grup = grup;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Bkbtranstype other = (Bkbtranstype) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id + " - " + description;
	}
	

	
	
}
