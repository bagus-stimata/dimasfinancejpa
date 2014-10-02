package org.dimas.finance.ar;

import org.dimas.finance.jpa.dao.ArInvoiceJpaService;
import org.dimas.finance.jpa.dao.ArInvoiceJpaServiceImpl;
import org.dimas.finance.jpa.dao.BBankHeaderJpaService;
import org.dimas.finance.jpa.dao.BBankHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BankJpaService;
import org.dimas.finance.jpa.dao.BankJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkbHeaderJpaService;
import org.dimas.finance.jpa.dao.BkbHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuTransferJpaService;
import org.dimas.finance.jpa.dao.BukuTransferJpaServiceImpl;
import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.ModulJpaService;
import org.dimas.finance.jpa.dao.ModulJpaServiceImpl;
import org.dimas.finance.jpa.dao.ModulTempHeaderJpaService;
import org.dimas.finance.jpa.dao.ModulTempHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.jpa.dao.UserJpaService;
import org.dimas.finance.jpa.dao.UserJpaServiceImpl;
import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Bbankheader;
import org.dimas.finance.model.Bkbheader;
import org.dimas.finance.model.Bukutransfer;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Modul;
import org.dimas.finance.model.ModulTempHeader;
import org.dimas.finance.model.User;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class ArRecapSelectModel {
	//1. DAO SERVICE
		private ArInvoiceJpaService arInvoiceService;
		private SysvarJpaService sysvarService;
		private DivisionJpaService divisionService;
		
	//2. ENTITY
		protected Arinvoice itemHeader;
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<Arinvoice> beanItemContainerItemHeader;
		private BeanItemContainer<Arinvoice> beanItemContainerItemHeaderSearch;
		
		private BeanItemContainer<Division> beanItemContainerDivision;
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<Arinvoice> binderItemHeader;
				
		//OTHERS
		//CHECK BOX BUAT TABLE PADA HEADER
		private boolean selectAllInvoice;
	
		public ArRecapSelectModel(){
			initVariable();
			initVariableData();
			
		}
		
		public void initVariable(){
			arInvoiceService = new ArInvoiceJpaServiceImpl();
	//		bankService = new BankJpaServiceImpl();
			sysvarService = new SysvarJpaServiceImpl();
			divisionService = new DivisionJpaServiceImpl();
			
			itemHeader = new Arinvoice();
			
			beanItemContainerItemHeader = new BeanItemContainer<Arinvoice>(Arinvoice.class);
			beanItemContainerItemHeaderSearch = new BeanItemContainer<Arinvoice>(Arinvoice.class);
			beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
			
			binderItemHeader = new BeanFieldGroup<Arinvoice>(Arinvoice.class);
			
		}
		public void initVariableData(){
			reload();
			
		}
		public void reload(){
			
			beanItemContainerItemHeader.removeAllContainerFilters();
			beanItemContainerItemHeader.removeAllItems();
			
			beanItemContainerItemHeader.addAll(arInvoiceService.findAllForRecapSelectArTOTunai("%", "%"));
			beanItemContainerDivision.addAll(divisionService.findAll());
		}

		public ArInvoiceJpaService getArInvoiceService() {
			return arInvoiceService;
		}

		public void setArInvoiceService(ArInvoiceJpaService arInvoiceService) {
			this.arInvoiceService = arInvoiceService;
		}

		public SysvarJpaService getSysvarService() {
			return sysvarService;
		}

		public void setSysvarService(SysvarJpaService sysvarService) {
			this.sysvarService = sysvarService;
		}

		public DivisionJpaService getDivisionService() {
			return divisionService;
		}

		public void setDivisionService(DivisionJpaService divisionService) {
			this.divisionService = divisionService;
		}

		public Arinvoice getItemHeader() {
			return itemHeader;
		}

		public void setItemHeader(Arinvoice itemHeader) {
			this.itemHeader = itemHeader;
		}

		public BeanItemContainer<Arinvoice> getBeanItemContainerItemHeader() {
			return beanItemContainerItemHeader;
		}

		public void setBeanItemContainerItemHeader(
				BeanItemContainer<Arinvoice> beanItemContainerItemHeader) {
			this.beanItemContainerItemHeader = beanItemContainerItemHeader;
		}

		public BeanItemContainer<Arinvoice> getBeanItemContainerItemHeaderSearch() {
			return beanItemContainerItemHeaderSearch;
		}

		public void setBeanItemContainerItemHeaderSearch(
				BeanItemContainer<Arinvoice> beanItemContainerItemHeaderSearch) {
			this.beanItemContainerItemHeaderSearch = beanItemContainerItemHeaderSearch;
		}

		public BeanItemContainer<Division> getBeanItemContainerDivision() {
			return beanItemContainerDivision;
		}

		public void setBeanItemContainerDivision(
				BeanItemContainer<Division> beanItemContainerDivision) {
			this.beanItemContainerDivision = beanItemContainerDivision;
		}

		public BeanFieldGroup<Arinvoice> getBinderItemHeader() {
			return binderItemHeader;
		}

		public void setBinderItemHeader(BeanFieldGroup<Arinvoice> binderItemHeader) {
			this.binderItemHeader = binderItemHeader;
		}

		public boolean isSelectAllInvoice() {
			return selectAllInvoice;
		}

		public void setSelectAllInvoice(boolean selectAllInvoice) {
			this.selectAllInvoice = selectAllInvoice;
		}

	
	
}
