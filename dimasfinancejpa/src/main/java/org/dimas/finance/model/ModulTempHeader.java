package org.dimas.finance.model;

import java.util.Date;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="MODULTEMPHEADER")
public class ModulTempHeader {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="TEMPLATENAME")
	private String templateName;
	@Column(name="NOTES")
	private String notes;
	
	@OneToMany(mappedBy="modulTemplate")
	private Set<User> users;
	
	
	@OneToMany(mappedBy="modulTempHeader")	
	private Set<ModulTempDetail> modulTempDetails;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CREATETIME")
	private Date createTime;

	
	
	
	public Set<ModulTempDetail> getModulTempDetails() {
		return modulTempDetails;
	}

	public void setModulTempDetails(Set<ModulTempDetail> modulTempDetails) {
		this.modulTempDetails = modulTempDetails;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		ModulTempHeader other = (ModulTempHeader) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
