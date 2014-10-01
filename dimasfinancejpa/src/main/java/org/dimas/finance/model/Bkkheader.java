package org.dimas.finance.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the BKKHEADER database table.
 * 
 */
@Entity
@Table(name="BKKHEADER")
@NamedQuery(name="Bkkheader.findAll", query="SELECT b FROM Bkkheader b")
public class Bkkheader implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected BkkheaderPK id;

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

	//bi-directional many-to-one association to Bkkdetail
	@OneToMany(mappedBy="bkkheaderBean")
	private Set<Bkkdetail> bkkdetails;

	//bi-directional many-to-one association to Division
	@ManyToOne
	@JoinColumn(name="division", referencedColumnName = "id")
	private Division divisionBean;
	
	public Bkkheader() {
	}

	
	
	public Division getDivisionBean() {
		return divisionBean;
	}



	public void setDivisionBean(Division divisionBean) {
		this.divisionBean = divisionBean;
	}



	public BkkheaderPK getId() {
		return id;
	}

	public void setId(BkkheaderPK id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Set<Bkkdetail> getBkkdetails() {
		return this.bkkdetails;
	}

	public void setBkkdetails(Set<Bkkdetail> bkkdetails) {
		this.bkkdetails = bkkdetails;
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
		Bkkheader other = (Bkkheader) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}