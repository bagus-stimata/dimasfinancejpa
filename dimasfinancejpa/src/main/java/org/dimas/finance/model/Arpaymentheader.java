package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the ARPAYMENTHEADER database table.
 * 
 */
@Entity
@Table(name="ARPAYMENTHEADER")
@NamedQuery(name="Arpaymentheader.findAll", query="SELECT a FROM Arpaymentheader a")
public class Arpaymentheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REFNO")
	private String refno;

	@Column(name="CLOSING")
	private boolean closing;

	@Temporal(TemporalType.DATE)
	@Column(name="ENTRYDATE")
	private Date entrydate;

	@Column(name="NOTES")
	private String notes;

	@Temporal(TemporalType.DATE)
	@Column(name="TRANSDATE")
	private Date transdate;

	@Column(name="USERID")
	private String userid;

	@OneToMany(mappedBy="arpaymentheaderBean")
	private Set<Arpaymentdetail> arpaymentdetails;
	
	
	public Set<Arpaymentdetail> getArpaymentdetails() {
		return arpaymentdetails;
	}

	public void setArpaymentdetails(Set<Arpaymentdetail> arpaymentdetails) {
		this.arpaymentdetails = arpaymentdetails;
	}

	public Arpaymentheader() {
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

	public Date getEntrydate() {
		return this.entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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
	public Arpaymentdetail addArpaymentdetail(Arpaymentdetail arpaymentdetail) {
		getArpaymentdetails().add(arpaymentdetail);
		arpaymentdetail.setArpaymentheaderBean(this);
		
		return arpaymentdetail;
	}

	public Arpaymentdetail removeArpaymentdetail(Arpaymentdetail arpaymentdetail) {
		getArpaymentdetails().remove(arpaymentdetail);
		arpaymentdetail.setArpaymentheaderBean(null);

		return arpaymentdetail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((refno == null) ? 0 : refno.hashCode());
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
		Arpaymentheader other = (Arpaymentheader) obj;
		if (refno == null) {
			if (other.refno != null)
				return false;
		} else if (!refno.equals(other.refno))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + refno ;
	}
	

}