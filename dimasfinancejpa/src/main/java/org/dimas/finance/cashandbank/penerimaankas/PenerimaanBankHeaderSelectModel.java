package org.dimas.finance.cashandbank.penerimaankas;

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

public class PenerimaanBankHeaderSelectModel {
	//1. DAO SERVICE
		private BBankHeaderJpaService bbankHeaderService;
		private SysvarJpaService sysvarService;
		private DivisionJpaService divisionService;
		
	//2. ENTITY
		protected Bbankheader itemHeader;
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<Bbankheader> beanItemContainerItemHeader;
		private BeanItemContainer<Bbankheader> beanItemContainerItemHeaderSearch;
		
		private BeanItemContainer<Division> beanItemContainerDivision;
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<Bbankheader> binderItemHeader;
				
		//OTHERS
	
		public PenerimaanBankHeaderSelectModel(){
			initVariable();
			initVariableData();
			
		}
		
		public void initVariable(){
			bbankHeaderService = new BBankHeaderJpaServiceImpl();
	//		bankService = new BankJpaServiceImpl();
			sysvarService = new SysvarJpaServiceImpl();
			divisionService = new DivisionJpaServiceImpl();
			
			itemHeader = new Bbankheader();
			
			beanItemContainerItemHeader = new BeanItemContainer<Bbankheader>(Bbankheader.class);
			beanItemContainerItemHeaderSearch = new BeanItemContainer<Bbankheader>(Bbankheader.class);
			beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
			
			binderItemHeader = new BeanFieldGroup<Bbankheader>(Bbankheader.class);
			
		}
		public void initVariableData(){
			reload();
			
		}
		public void reload(){
			
			beanItemContainerItemHeader.removeAllContainerFilters();
			beanItemContainerItemHeader.removeAllItems();
			
			beanItemContainerItemHeader.addAll(bbankHeaderService.findAll());
			beanItemContainerDivision.addAll(divisionService.findAll());
		}

		public BBankHeaderJpaService getBbankHeaderService() {
			return bbankHeaderService;
		}

		public void setBbankHeaderService(BBankHeaderJpaService bbankHeaderService) {
			this.bbankHeaderService = bbankHeaderService;
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


		public Bbankheader getItemHeader() {
			return itemHeader;
		}

		public void setItemHeader(Bbankheader itemHeader) {
			this.itemHeader = itemHeader;
		}

		public BeanItemContainer<Bbankheader> getBeanItemContainerItemHeader() {
			return beanItemContainerItemHeader;
		}

		public void setBeanItemContainerItemHeader(
				BeanItemContainer<Bbankheader> beanItemContainerItemHeader) {
			this.beanItemContainerItemHeader = beanItemContainerItemHeader;
		}

		public BeanItemContainer<Bbankheader> getBeanItemContainerItemHeaderSearch() {
			return beanItemContainerItemHeaderSearch;
		}

		public void setBeanItemContainerItemHeaderSearch(
				BeanItemContainer<Bbankheader> beanItemContainerItemHeaderSearch) {
			this.beanItemContainerItemHeaderSearch = beanItemContainerItemHeaderSearch;
		}

		public BeanItemContainer<Division> getBeanItemContainerDivision() {
			return beanItemContainerDivision;
		}

		public void setBeanItemContainerDivision(
				BeanItemContainer<Division> beanItemContainerDivision) {
			this.beanItemContainerDivision = beanItemContainerDivision;
		}

		public BeanFieldGroup<Bbankheader> getBinderItemHeader() {
			return binderItemHeader;
		}

		public void setBinderItemHeader(BeanFieldGroup<Bbankheader> binderItemHeader) {
			this.binderItemHeader = binderItemHeader;
		}


		
	
	
}
