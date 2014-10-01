package org.dimas.finance.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.vaadin.ui.CheckBox;

			
@Entity
@Table(name="MODUL")
public class Modul {
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="MODULGROUP")
	private String modulGroup;
	@Column(name="TITLE")
	private String title;
	@Column(name="NOTES")
	private String notes;
	
	@OneToMany(mappedBy="modulBean")
	private Set<ModulTempDetail> modulTempDetails;
		
//**************
	@Transient
	CheckBox selected = new CheckBox();

	public CheckBox getSelected() {
		return selected;
	}

	public void setSelected(CheckBox selected) {
		this.selected = selected;
	}
//**************
	
	public Set<ModulTempDetail> getModulTempDetails() {
		return modulTempDetails;
	}
	public void setModulTempDetails(Set<ModulTempDetail> modulTempDetails) {
		this.modulTempDetails = modulTempDetails;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModulGroup() {
		return modulGroup;
	}
	public void setModulGroup(String modulGroup) {
		this.modulGroup = modulGroup;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
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
		Modul other = (Modul) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return id + " - " + title;
	}
	
	
	
	
	
}
