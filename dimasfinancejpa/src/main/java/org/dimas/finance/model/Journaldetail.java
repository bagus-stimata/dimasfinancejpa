package org.dimas.finance.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the JOURNALDETAIL database table.
 * 
 */
@Entity
@Table(name="JOURNALDETAIL")
@NamedQuery(name="Journaldetail.findAll", query="SELECT j FROM Journaldetail j")
public class Journaldetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REFNO")
	private String refno;

	@Column(name="ACCOUNTID")
	private String accountid;

	@Column(name="AMOUNT")
	private double amount;

	@Column(name="DEBETKREDIT")
	private String debetkredit;

	@Column(name="NOTES")
	private String notes;

	@Column(name="NOURUT")
	private int nourut;

	//bi-directional many-to-one association to Division
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="division")
	private Division divisionBean;

	//bi-directional many-to-one association to Journalheader
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="journalHeader")
	private Journalheader journalheader;

	public Journaldetail() {
	}

	public String getRefno() {
		return this.refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public String getAccountid() {
		return this.accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDebetkredit() {
		return this.debetkredit;
	}

	public void setDebetkredit(String debetkredit) {
		this.debetkredit = debetkredit;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getNourut() {
		return this.nourut;
	}

	public void setNourut(int nourut) {
		this.nourut = nourut;
	}

	public Division getDivisionBean() {
		return this.divisionBean;
	}

	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
	}

	public Journalheader getJournalheader() {
		return this.journalheader;
	}

	public void setJournalheader(Journalheader journalheader) {
		this.journalheader = journalheader;
	}

}