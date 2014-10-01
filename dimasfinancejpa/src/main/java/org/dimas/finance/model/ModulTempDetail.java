package org.dimas.finance.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.vaadin.ui.CheckBox;

@Entity
@Table(name="MODULTEMPDETAIL")
public class ModulTempDetail {
	
	@EmbeddedId
	protected ModulTempDetailPK id;
	
	@Column(name="OTORIZE")
	private int otorize;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID", referencedColumnName="id", insertable=false, updatable=false)
	private ModulTempHeader modulTempHeader;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MODUL", referencedColumnName="id", insertable=false, updatable=false)
	private Modul modulBean;
	
	
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
	
	
	public ModulTempDetailPK getId() {
		return id;
	}

	public void setId(ModulTempDetailPK id) {
		this.id = id;
	}

	public int getOtorize() {
		return otorize;
	}

	public void setOtorize(int otorize) {
		this.otorize = otorize;
	}

	
	
	public ModulTempHeader getModulTempHeader() {
		return modulTempHeader;
	}

	public void setModulTempHeader(ModulTempHeader modulTempHeader) {
		this.modulTempHeader = modulTempHeader;
	}


	public Modul getModulBean() {
		return modulBean;
	}

	public void setModulBean(Modul modulBean) {
		this.modulBean = modulBean;
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
		ModulTempDetail other = (ModulTempDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + id ;
	}
	
	
}
