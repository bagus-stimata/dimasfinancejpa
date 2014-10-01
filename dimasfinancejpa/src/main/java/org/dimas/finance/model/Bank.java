package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;

/**
 * The persistent class for the BANK database table.
 * 
 */

@Entity
@Table(name="BANK")
@NamedQuery(name="Bank.findAll", query="SELECT b FROM Bank b")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDBANK")
	private String idbank;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="REKENING")
	private String rekening;
	
	@Column(name="GL")
	private boolean gl;
	
	
	//bi-directional many-to-one association to Account
	@OneToMany(mappedBy="bankBean")
	private Set<Account> accounts;

	//bi-directional many-to-one association to Bukugiro
	@OneToMany(mappedBy="bankBean")
	private Set<Bukugiro> bukugiros;

	//bi-directional many-to-one association to Girotransfer
	@OneToMany(mappedBy="bankBean")
	private Set<Bukutransfer> girotransfers;

	public Bank() {
	}

	public String getIdbank() {
		return this.idbank;
	}

	public void setIdbank(String idbank) {
		this.idbank = idbank;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getRekening() {
		return rekening;
	}

	public void setRekening(String rekening) {
		this.rekening = rekening;
	}

	public boolean isGl() {
		return gl;
	}

	public void setGl(boolean gl) {
		this.gl = gl;
	}

	public Set<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setBankBean(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setBankBean(null);

		return account;
	}

	public Set<Bukugiro> getBukugiros() {
		return this.bukugiros;
	}

	public void setBukugiros(Set<Bukugiro> bukugiros) {
		this.bukugiros = bukugiros;
	}

	public Bukugiro addBukugiro(Bukugiro bukugiro) {
		getBukugiros().add(bukugiro);
		bukugiro.setBankBean(this);

		return bukugiro;
	}

	public Bukugiro removeBukugiro(Bukugiro bukugiro) {
		getBukugiros().remove(bukugiro);
		bukugiro.setBankBean(null);

		return bukugiro;
	}

	public Set<Bukutransfer> getGirotransfers() {
		return this.girotransfers;
	}

	public void setGirotransfers(Set<Bukutransfer> girotransfers) {
		this.girotransfers = girotransfers;
	}

	public Bukutransfer addGirotransfer(Bukutransfer girotransfer) {
		getGirotransfers().add(girotransfer);
		girotransfer.setBankBean(this);

		return girotransfer;
	}

	public Bukutransfer removeGirotransfer(Bukutransfer girotransfer) {
		getGirotransfers().remove(girotransfer);
		girotransfer.setBankBean(null);

		return girotransfer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idbank == null) ? 0 : idbank.hashCode());
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
		Bank other = (Bank) obj;
		if (idbank == null) {
			if (other.idbank != null)
				return false;
		} else if (!idbank.equals(other.idbank))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return idbank + " - " + description;
	}

	
}