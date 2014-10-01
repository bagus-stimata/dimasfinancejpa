package org.dimas.finance.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the JOURNALHEADER database table.
 * 
 */
@Entity
@Table(name="JOURNALHEADER")
@NamedQuery(name="Journalheader.findAll", query="SELECT j FROM Journalheader j")
public class Journalheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REFNO")
	private String refno;

	@Column(name="CLOSING")
	private boolean closing;

	@Column(name="DEBET")
	private double debet;

	@Temporal(TemporalType.DATE)
	@Column(name="ENTRYDATE")
	private Date entrydate;

	@Column(name="KREDIT")
	private double kredit;

	@Column(name="PERIODE")
	private int periode;

	@Temporal(TemporalType.DATE)
	@Column(name="TRANSDATE")
	private Date transdate;

	@Column(name="USERID")
	private String userid;

	//bi-directional many-to-one association to Journaldetail
	@OneToMany(mappedBy="journalheader")
	private Set<Journaldetail> journaldetails;

	public Journalheader() {
	}

	public String getRefno() {
		return this.refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public boolean isClosing() {
		return closing;
	}

	public void setClosing(boolean closing) {
		this.closing = closing;
	}

	public double getDebet() {
		return this.debet;
	}

	public void setDebet(double debet) {
		this.debet = debet;
	}

	public Date getEntrydate() {
		return this.entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public double getKredit() {
		return this.kredit;
	}

	public void setKredit(double kredit) {
		this.kredit = kredit;
	}

	public int getPeriode() {
		return this.periode;
	}

	public void setPeriode(int periode) {
		this.periode = periode;
	}

	public Date getTransdate() {
		return this.transdate;
	}

	public void setTransdate(Date transdate) {
		this.transdate = transdate;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Set<Journaldetail> getJournaldetails() {
		return this.journaldetails;
	}

	public void setJournaldetails(Set<Journaldetail> journaldetails) {
		this.journaldetails = journaldetails;
	}

	public Journaldetail addJournaldetail(Journaldetail journaldetail) {
		getJournaldetails().add(journaldetail);
		journaldetail.setJournalheader(this);

		return journaldetail;
	}

	public Journaldetail removeJournaldetail(Journaldetail journaldetail) {
		getJournaldetails().remove(journaldetail);
		journaldetail.setJournalheader(null);

		return journaldetail;
	}

}