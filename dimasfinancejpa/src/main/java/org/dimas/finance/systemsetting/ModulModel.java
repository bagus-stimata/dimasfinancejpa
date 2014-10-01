package org.dimas.finance.systemsetting;

import org.dimas.finance.jpa.dao.BankJpaService;
import org.dimas.finance.jpa.dao.BankJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuTransferJpaService;
import org.dimas.finance.jpa.dao.BukuTransferJpaServiceImpl;
import org.dimas.finance.jpa.dao.ModulJpaService;
import org.dimas.finance.jpa.dao.ModulJpaServiceImpl;
import org.dimas.finance.jpa.dao.ModulTempHeaderJpaService;
import org.dimas.finance.jpa.dao.ModulTempHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.jpa.dao.UserJpaService;
import org.dimas.finance.jpa.dao.UserJpaServiceImpl;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Bukutransfer;
import org.dimas.finance.model.Modul;
import org.dimas.finance.model.ModulTempHeader;
import org.dimas.finance.model.User;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class ModulModel {
	
	//1. DAO SERVICE
		private ModulJpaService modulService;
//		private MenuAccessTempJpaService menuAccessTempService;
		private SysvarJpaService sysvarService;
		
		
	//2. ENTITY
		protected Modul modul;
		protected Modul newModul;
//		protected MenuAccessTemp menuAccessTemp;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<Modul> beanItemContainerModul;
		private BeanItemContainer<Modul> beanItemContainerModulSearch;
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<Modul> binderModul;
				
	//OTHERS
	protected String OperationStatus = "OPEN";

	public ModulModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		modulService = new ModulJpaServiceImpl();
//		bankService = new BankJpaServiceImpl();
		sysvarService = new SysvarJpaServiceImpl();
		
		
		modul = new Modul();
		
		beanItemContainerModul = new BeanItemContainer<Modul>(Modul.class);
		beanItemContainerModulSearch = new BeanItemContainer<Modul>(Modul.class);
		
		binderModul = new BeanFieldGroup<Modul>(Modul.class);
		
	}
	public void initVariableData(){
		reload();
		
		
	}
	public void reload(){
		beanItemContainerModul.removeAllContainerFilters();
		beanItemContainerModul.removeAllItems();
		
		beanItemContainerModul.addAll(modulService.findAll());
	}

	public ModulJpaService getModulService() {
		return modulService;
	}

	public void setModulService(ModulJpaService modulService) {
		this.modulService = modulService;
	}

	public SysvarJpaService getSysvarService() {
		return sysvarService;
	}

	public void setSysvarService(SysvarJpaService sysvarService) {
		this.sysvarService = sysvarService;
	}

	public Modul getModul() {
		return modul;
	}

	public void setModul(Modul modul) {
		this.modul = modul;
	}

	public Modul getNewModul() {
		return newModul;
	}

	public void setNewModul(Modul newModul) {
		this.newModul = newModul;
	}

	public BeanItemContainer<Modul> getBeanItemContainerModul() {
		return beanItemContainerModul;
	}

	public void setBeanItemContainerModul(
			BeanItemContainer<Modul> beanItemContainerModul) {
		this.beanItemContainerModul = beanItemContainerModul;
	}

	public BeanItemContainer<Modul> getBeanItemContainerModulSearch() {
		return beanItemContainerModulSearch;
	}

	public void setBeanItemContainerModulSearch(
			BeanItemContainer<Modul> beanItemContainerModulSearch) {
		this.beanItemContainerModulSearch = beanItemContainerModulSearch;
	}

	public BeanFieldGroup<Modul> getBinderModul() {
		return binderModul;
	}

	public void setBinderModul(BeanFieldGroup<Modul> binderModul) {
		this.binderModul = binderModul;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}


	
	
	
	
}
