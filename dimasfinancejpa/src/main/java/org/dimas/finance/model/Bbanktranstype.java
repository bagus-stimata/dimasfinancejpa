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
import javax.validation.constraints.Null;

@Entity
@Table(name="BBANKTRANSTYPE")
public class Bbanktranstype implements Serializable{
	
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
	@OneToMany(mappedBy="bbanktranstypeBean")
	private Set<Bbankdetail> bbankdetails;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public Set<Bbankdetail> getBbankdetails() {
		return bbankdetails;
	}

	public void setBbankdetails(Set<Bbankdetail> bbankdetails) {
		this.bbankdetails = bbankdetails;
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
		Bbanktranstype other = (Bbanktranstype) obj;
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
