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

	@EmbeddedId
	protected ArpaymentheaderPK id;
	
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
	
	//bi-directional many-to-one association to Division
	@ManyToOne
	@JoinColumn(name="division", referencedColumnName = "id")
	private Division divisionBean;

	public ArpaymentheaderPK getId() {
		return id;
	}

	public void setId(ArpaymentheaderPK id) {
		this.id = id;
	}

	public boolean isClosing() {
		return closing;
	}

	public void setClosing(boolean closing) {
		this.closing = closing;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getTransdate() {
		return transdate;
	}

	public void setTransdate(Date transdate) {
		this.transdate = transdate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Set<Arpaymentdetail> getArpaymentdetails() {
		return arpaymentdetails;
	}

	public void setArpaymentdetails(Set<Arpaymentdetail> arpaymentdetails) {
		this.arpaymentdetails = arpaymentdetails;
	}

	public Division getDivisionBean() {
		return divisionBean;
	}

	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
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
		Arpaymentheader other = (Arpaymentheader) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}