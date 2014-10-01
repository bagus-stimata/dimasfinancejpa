package org.dimas.finance.cashandbank.penerimaankas;

import java.util.Date;
import java.util.Set;

import org.dimas.finance.jpa.dao.AccountJpaService;
import org.dimas.finance.jpa.dao.AccountJpaServiceImpl;
import org.dimas.finance.jpa.dao.BankJpaService;
import org.dimas.finance.jpa.dao.BankJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkbtranstypeJpaService;
import org.dimas.finance.jpa.dao.BkbtranstypeJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuTransferJpaService;
import org.dimas.finance.jpa.dao.BukuTransferJpaServiceImpl;
import org.dimas.finance.jpa.dao.CustomerJpaService;
import org.dimas.finance.jpa.dao.CustomerJpaServiceImpl;
import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.ModulJpaService;
import org.dimas.finance.jpa.dao.ModulJpaServiceImpl;
import org.dimas.finance.jpa.dao.ModulTempHeaderJpaService;
import org.dimas.finance.jpa.dao.ModulTempHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.SalesmanJpaService;
import org.dimas.finance.jpa.dao.SalesmanJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.jpa.dao.UserJpaService;
import org.dimas.finance.jpa.dao.UserJpaServiceImpl;
import org.dimas.finance.model.Account;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Bkbdetail;
import org.dimas.finance.model.Bkbheader;
import org.dimas.finance.model.BkbheaderPK;
import org.dimas.finance.model.Bkbtranstype;
import org.dimas.finance.model.Bukutransfer;
import org.dimas.finance.model.Customer;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Modul;
import org.dimas.finance.model.ModulTempHeader;
import org.dimas.finance.model.Salesman;
import org.dimas.finance.model.User;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class PenerimaanKBItemAddModel {
	
	//1. DAO SERVICE
		private DivisionJpaService divisionService;
		private BkbtranstypeJpaService transtypeService;
		private AccountJpaService accountService;
		private CustomerJpaService customerService;
		private SalesmanJpaService salesmanService;
//		private MenuAccessTempJpaService menuAccessTempService;
		private SysvarJpaService sysvarService;
		
		
	//2. ENTITY
		
		protected Bkbheader bkbheader = new Bkbheader();
		protected Bkbdetail bkbdetail = new Bkbdetail();
//		protected Bkbdetail newBkbdetail = new Bkbdetail();
//		protected MenuAccessTemp menuAccessTemp;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<Division> beanItemContainerDivision;
		private BeanItemContainer<Bkbtranstype> beanItemContainerTranstype;
		private BeanItemContainer<Account> beanItemContainerAccount;
		private BeanItemContainer<Customer> beanItemContainerCustomer;
		private BeanItemContainer<Salesman> beanItemContainerSalesman;
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<Bkbdetail> binderBkbdetail;
				
		//OTHERS
		protected String OperationStatus = "OPEN";

		public String getOperationStatus() {
			return OperationStatus;
		}

		public void setOperationStatus(String operationStatus) {
			OperationStatus = operationStatus;
		}

		public PenerimaanKBItemAddModel(){
			initVariable();
			initVariableData();
			
		}
		
		public void initVariable(){
			divisionService = new DivisionJpaServiceImpl();
			transtypeService = new BkbtranstypeJpaServiceImpl();
			accountService = new AccountJpaServiceImpl();
			customerService = new CustomerJpaServiceImpl();
			salesmanService = new SalesmanJpaServiceImpl();
			
			sysvarService = new SysvarJpaServiceImpl();
			
			//FOR COMBO
			beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
//			beanItemContainerModulSearch = new BeanItemContainer<Modul>(Modul.class);
			beanItemContainerTranstype = new BeanItemContainer<Bkbtranstype>(Bkbtranstype.class);
			beanItemContainerAccount = new BeanItemContainer<Account>(Account.class);
			beanItemContainerCustomer = new BeanItemContainer<Customer>(Customer.class);
			beanItemContainerSalesman = new BeanItemContainer<Salesman>(Salesman.class);
			
			binderBkbdetail = new BeanFieldGroup<Bkbdetail>(Bkbdetail.class);
			
		}

		public void initVariableData(){
			reload();			
		}
		
		public void reload(){
			beanItemContainerDivision.removeAllContainerFilters();
			beanItemContainerDivision.removeAllItems();
			beanItemContainerTranstype.removeAllContainerFilters();
			beanItemContainerTranstype.removeAllItems();
			beanItemContainerAccount.removeAllContainerFilters();
			beanItemContainerAccount.removeAllItems();
			beanItemContainerCustomer.removeAllContainerFilters();
			beanItemContainerCustomer.removeAllItems();
			beanItemContainerSalesman.removeAllContainerFilters();
			beanItemContainerSalesman.removeAllItems();
			
			beanItemContainerDivision.addAll(divisionService.findAll());
			
			//GRUP1=Setoran, GRUP2=Penerimaan Kas Besar, GRUP3=Pengeluaran Kas Besar, GRUP4=Mutasi Kas Besar, GRUPL= LAIN-LAIN
			beanItemContainerTranstype.addAll(transtypeService.findAllByGrup("GRUP2"));
			
			beanItemContainerAccount.addAll(accountService.findAll());
			beanItemContainerCustomer.addAll(customerService.findAll());
			beanItemContainerSalesman.addAll(salesmanService.findAll());
		}

		public DivisionJpaService getDivisionService() {
			return divisionService;
		}

		public void setDivisionService(DivisionJpaService divisionService) {
			this.divisionService = divisionService;
		}

		public BkbtranstypeJpaService getTranstypeService() {
			return transtypeService;
		}

		public void setTranstypeService(BkbtranstypeJpaService transtypeService) {
			this.transtypeService = transtypeService;
		}

		public CustomerJpaService getCustomerService() {
			return customerService;
		}

		public void setCustomerService(CustomerJpaService customerService) {
			this.customerService = customerService;
		}

		public SalesmanJpaService getSalesmanService() {
			return salesmanService;
		}

		public void setSalesmanService(SalesmanJpaService salesmanService) {
			this.salesmanService = salesmanService;
		}

		public BeanItemContainer<Division> getBeanItemContainerDivision() {
			return beanItemContainerDivision;
		}

		public void setBeanItemContainerDivision(
				BeanItemContainer<Division> beanItemContainerDivision) {
			this.beanItemContainerDivision = beanItemContainerDivision;
		}

		public BeanItemContainer<Bkbtranstype> getBeanItemContainerTranstype() {
			return beanItemContainerTranstype;
		}

		public void setBeanItemContainerTranstype(
				BeanItemContainer<Bkbtranstype> beanItemContainerTranstype) {
			this.beanItemContainerTranstype = beanItemContainerTranstype;
		}

		public BeanItemContainer<Customer> getBeanItemContainerCustomer() {
			return beanItemContainerCustomer;
		}

		public void setBeanItemContainerCustomer(
				BeanItemContainer<Customer> beanItemContainerCustomer) {
			this.beanItemContainerCustomer = beanItemContainerCustomer;
		}

		public BeanItemContainer<Salesman> getBeanItemContainerSalesman() {
			return beanItemContainerSalesman;
		}

		public void setBeanItemContainerSalesman(
				BeanItemContainer<Salesman> beanItemContainerSalesman) {
			this.beanItemContainerSalesman = beanItemContainerSalesman;
		}

		public AccountJpaService getAccountService() {
			return accountService;
		}

		public void setAccountService(AccountJpaService accountService) {
			this.accountService = accountService;
		}

		public SysvarJpaService getSysvarService() {
			return sysvarService;
		}

		public void setSysvarService(SysvarJpaService sysvarService) {
			this.sysvarService = sysvarService;
		}

		public Bkbheader getBkbheader() {
			return bkbheader;
		}

		public void setBkbheader(Bkbheader bkbheader) {
			this.bkbheader = bkbheader;
		}

		public Bkbdetail getBkbdetail() {
			return bkbdetail;
		}

		public void setBkbdetail(Bkbdetail bkbdetail) {
			this.bkbdetail = bkbdetail;
		}

		public BeanItemContainer<Account> getBeanItemContainerAccount() {
			return beanItemContainerAccount;
		}

		public void setBeanItemContainerAccount(
				BeanItemContainer<Account> beanItemContainerAccount) {
			this.beanItemContainerAccount = beanItemContainerAccount;
		}

		public BeanFieldGroup<Bkbdetail> getBinderBkbdetail() {
			return binderBkbdetail;
		}

		public void setBinderBkbdetail(BeanFieldGroup<Bkbdetail> binderBkbdetail) {
			this.binderBkbdetail = binderBkbdetail;
		}

		
	
	
}
