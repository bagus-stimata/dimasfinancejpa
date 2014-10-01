package org.dimas.finance.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@Table(name="USER")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USERID")
	private String userId;
	@Column(name="ACTIVE")
	private boolean active;
	
	@Column(name="USERPASSWORD")
	private String userPassword;

	@Column(name="FULLNAME")
	private String fullName;

	@Column(name="GENDER")
	private boolean gender;

	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ADDRESS")
	private String address;
	@Column(name="CITY")
	private String city;
	@Column(name="STATE")
	private String state;
	
	@Temporal(TemporalType.DATE)
	@Column(name="BIRTHDATE")
	private Date birthDate;
	@Temporal(TemporalType.DATE)
	@Column(name="ENTRYDATE")
	private Date joinDate;
	@Temporal(TemporalType.TIME)
	@Column(name="LASTLOGIN")
	private Date lastLogin;
	
	@Column(name="CREATEBY")
	private String createBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MODULTEMPLATE")
	private ModulTempHeader modulTemplate;

	

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public ModulTempHeader getModulTemplate() {
		return modulTemplate;
	}

	public void setModulTemplate(ModulTempHeader modulTemplate) {
		this.modulTemplate = modulTemplate;
	}

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return userId + "";
	}


}