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

public class ModulTempHeaderSelectModel {
	
	//1. DAO SERVICE
		private ModulTempHeaderJpaService modulTempHeaderService;
		private SysvarJpaService sysvarService;
		
		
	//2. ENTITY
		protected ModulTempHeader modulTempHeader;
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<ModulTempHeader> beanItemContainerModulTempHeader;
		private BeanItemContainer<ModulTempHeader> beanItemContainerModulTempHeaderSearch;
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<ModulTempHeader> binderModulTempHeader;
				
		//OTHERS
	
		public ModulTempHeaderSelectModel(){
			initVariable();
			initVariableData();
			
		}
		
		public void initVariable(){
			modulTempHeaderService = new ModulTempHeaderJpaServiceImpl();
	//		bankService = new BankJpaServiceImpl();
			sysvarService = new SysvarJpaServiceImpl();
			
			
			modulTempHeader = new ModulTempHeader();
			
			beanItemContainerModulTempHeader = new BeanItemContainer<ModulTempHeader>(ModulTempHeader.class);
			beanItemContainerModulTempHeaderSearch = new BeanItemContainer<ModulTempHeader>(ModulTempHeader.class);
			
			binderModulTempHeader = new BeanFieldGroup<ModulTempHeader>(ModulTempHeader.class);
			
		}
		public void initVariableData(){
			reload();
			
			
		}
		public void reload(){
			beanItemContainerModulTempHeader.removeAllContainerFilters();
			beanItemContainerModulTempHeader.removeAllItems();
			
			beanItemContainerModulTempHeader.addAll(modulTempHeaderService.findAll());
		}

		public ModulTempHeaderJpaService getModulTempHeaderService() {
			return modulTempHeaderService;
		}

		public void setModulTempHeaderService(
				ModulTempHeaderJpaService modulTempHeaderService) {
			this.modulTempHeaderService = modulTempHeaderService;
		}

		public SysvarJpaService getSysvarService() {
			return sysvarService;
		}

		public void setSysvarService(SysvarJpaService sysvarService) {
			this.sysvarService = sysvarService;
		}

		public ModulTempHeader getModulTempHeader() {
			return modulTempHeader;
		}

		public void setModulTempHeader(ModulTempHeader modulTempHeader) {
			this.modulTempHeader = modulTempHeader;
		}

		public BeanItemContainer<ModulTempHeader> getBeanItemContainerModulTempHeader() {
			return beanItemContainerModulTempHeader;
		}

		public void setBeanItemContainerModulTempHeader(
				BeanItemContainer<ModulTempHeader> beanItemContainerModulTempHeader) {
			this.beanItemContainerModulTempHeader = beanItemContainerModulTempHeader;
		}

		public BeanItemContainer<ModulTempHeader> getBeanItemContainerModulTempHeaderSearch() {
			return beanItemContainerModulTempHeaderSearch;
		}

		public void setBeanItemContainerModulTempHeaderSearch(
				BeanItemContainer<ModulTempHeader> beanItemContainerModulTempHeaderSearch) {
			this.beanItemContainerModulTempHeaderSearch = beanItemContainerModulTempHeaderSearch;
		}

		public BeanFieldGroup<ModulTempHeader> getBinderModulTempHeader() {
			return binderModulTempHeader;
		}

		public void setBinderModulTempHeader(
				BeanFieldGroup<ModulTempHeader> binderModulTempHeader) {
			this.binderModulTempHeader = binderModulTempHeader;
		}
	

		
	
	
}
