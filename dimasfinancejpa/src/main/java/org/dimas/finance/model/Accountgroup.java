package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the ACCOUNTGROUP database table.
 * 
 */
@Entity
@Table(name="ACCOUNTGROUP")
@NamedQuery(name="Accountgroup.findAll", query="SELECT a FROM Accountgroup a")
public class Accountgroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	private String id;

	@Column(name="NAME")
	private String name;

	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="accountgroup")
	private Set<Account> accounts;

	//bi-directional many-to-one association to Accountgroupreport
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACCOUNTGROUPREPORT_ID")
	private Accountgroupreport accountgroupreport;

	public Accountgroup() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setAccountgroup(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setAccountgroup(null);

		return account;
	}

	public Accountgroupreport getAccountgroupreport() {
		return this.accountgroupreport;
	}

	public void setAccountgroupreport(Accountgroupreport accountgroupreport) {
		this.accountgroupreport = accountgroupreport;
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
		Accountgroup other = (Accountgroup) obj;
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