package org.dimas.finance.cashandbank.penerimaankas;

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
import org.dimas.finance.model.Bank;
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

public class PenerimaanKBHeaderSelectModel {
	//1. DAO SERVICE
		private BkbHeaderJpaService bkbHeaderService;
		private SysvarJpaService sysvarService;
		private DivisionJpaService divisionService;
		
	//2. ENTITY
		protected Bkbheader bkbHeader;
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<Bkbheader> beanItemContainerBkbHeader;
		private BeanItemContainer<Bkbheader> beanItemContainerBkbHeaderSearch;
		
		private BeanItemContainer<Division> beanItemContainerDivision;
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<Bkbheader> binderBkbHeader;
				
		//OTHERS
	
		public PenerimaanKBHeaderSelectModel(){
			initVariable();
			initVariableData();
			
		}
		
		public void initVariable(){
			bkbHeaderService = new BkbHeaderJpaServiceImpl();
	//		bankService = new BankJpaServiceImpl();
			sysvarService = new SysvarJpaServiceImpl();
			divisionService = new DivisionJpaServiceImpl();
			
			bkbHeader = new Bkbheader();
			
			beanItemContainerBkbHeader = new BeanItemContainer<Bkbheader>(Bkbheader.class);
			beanItemContainerBkbHeaderSearch = new BeanItemContainer<Bkbheader>(Bkbheader.class);
			beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
			
			binderBkbHeader = new BeanFieldGroup<Bkbheader>(Bkbheader.class);
			
		}
		public void initVariableData(){
			reload();
			
		}
		public void reload(){
			
			beanItemContainerBkbHeader.removeAllContainerFilters();
			beanItemContainerBkbHeader.removeAllItems();
			
			beanItemContainerBkbHeader.addAll(bkbHeaderService.findAll());
			beanItemContainerDivision.addAll(divisionService.findAll());
		}

		public BkbHeaderJpaService getBkbHeaderService() {
			return bkbHeaderService;
		}

		public void setBkbHeaderService(BkbHeaderJpaService bkbHeaderService) {
			this.bkbHeaderService = bkbHeaderService;
		}

		public SysvarJpaService getSysvarService() {
			return sysvarService;
		}

		public void setSysvarService(SysvarJpaService sysvarService) {
			this.sysvarService = sysvarService;
		}

		public Bkbheader getBkbHeader() {
			return bkbHeader;
		}

		public void setBkbHeader(Bkbheader bkbHeader) {
			this.bkbHeader = bkbHeader;
		}

		public BeanItemContainer<Bkbheader> getBeanItemContainerBkbHeader() {
			return beanItemContainerBkbHeader;
		}

		public void setBeanItemContainerBkbHeader(
				BeanItemContainer<Bkbheader> beanItemContainerBkbHeader) {
			this.beanItemContainerBkbHeader = beanItemContainerBkbHeader;
		}

		public BeanItemContainer<Bkbheader> getBeanItemContainerBkbHeaderSearch() {
			return beanItemContainerBkbHeaderSearch;
		}

		public void setBeanItemContainerBkbHeaderSearch(
				BeanItemContainer<Bkbheader> beanItemContainerBkbHeaderSearch) {
			this.beanItemContainerBkbHeaderSearch = beanItemContainerBkbHeaderSearch;
		}

		public BeanFieldGroup<Bkbheader> getBinderBkbHeader() {
			return binderBkbHeader;
		}

		public void setBinderBkbHeader(BeanFieldGroup<Bkbheader> binderBkbHeader) {
			this.binderBkbHeader = binderBkbHeader;
		}

		public DivisionJpaService getDivisionService() {
			return divisionService;
		}

		public void setDivisionService(DivisionJpaService divisionService) {
			this.divisionService = divisionService;
		}

		public BeanItemContainer<Division> getBeanItemContainerDivision() {
			return beanItemContainerDivision;
		}

		public void setBeanItemContainerDivision(
				BeanItemContainer<Division> beanItemContainerDivision) {
			this.beanItemContainerDivision = beanItemContainerDivision;
		}

	

		
	
	
}
