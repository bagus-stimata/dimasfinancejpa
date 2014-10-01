package org.dimas.finance.cashandbank.pengeluarankas;

import java.util.Date;
import java.util.Set;

import org.dimas.finance.jpa.dao.AccountJpaService;
import org.dimas.finance.jpa.dao.AccountJpaServiceImpl;
import org.dimas.finance.jpa.dao.BankJpaService;
import org.dimas.finance.jpa.dao.BankJpaServiceImpl;
import org.dimas.finance.jpa.dao.BbanktranstypeJpaService;
import org.dimas.finance.jpa.dao.BbanktranstypeJpaServiceImpl;
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
import org.dimas.finance.model.Bbankdetail;
import org.dimas.finance.model.Bbankheader;
import org.dimas.finance.model.Bbanktranstype;
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

public class PengeluaranBankItemAddModel {
	
	//1. DAO SERVICE
		private DivisionJpaService divisionService;
		private BbanktranstypeJpaService transtypeService;
		private AccountJpaService accountService;
		private CustomerJpaService customerService;
		private SalesmanJpaService salesmanService;
//		private MenuAccessTempJpaService menuAccessTempService;
		private SysvarJpaService sysvarService;
		
		
	//2. ENTITY
		
		protected Bbankheader itemHeader = new Bbankheader();
		protected Bbankdetail itemDetail = new Bbankdetail();
//		protected Bkbdetail newBkbdetail = new Bkbdetail();
//		protected MenuAccessTemp menuAccessTemp;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<Division> beanItemContainerDivision;
		private BeanItemContainer<Bbanktranstype> beanItemContainerTranstype;
		private BeanItemContainer<Account> beanItemContainerAccount;
		private BeanItemContainer<Customer> beanItemContainerCustomer;
		private BeanItemContainer<Salesman> beanItemContainerSalesman;
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<Bbankdetail> binderItemDetail;
				
		//OTHERS
		protected String OperationStatus = "OPEN";

		public String getOperationStatus() {
			return OperationStatus;
		}

		public void setOperationStatus(String operationStatus) {
			OperationStatus = operationStatus;
		}

		public PengeluaranBankItemAddModel(){
			initVariable();
			initVariableData();
			
		}
		
		public void initVariable(){
			divisionService = new DivisionJpaServiceImpl();
			transtypeService = new BbanktranstypeJpaServiceImpl();
			accountService = new AccountJpaServiceImpl();
			customerService = new CustomerJpaServiceImpl();
			salesmanService = new SalesmanJpaServiceImpl();
			
			sysvarService = new SysvarJpaServiceImpl();
			
			//FOR COMBO
			beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
//			beanItemContainerModulSearch = new BeanItemContainer<Modul>(Modul.class);
			beanItemContainerTranstype = new BeanItemContainer<Bbanktranstype>(Bbanktranstype.class);
			beanItemContainerAccount = new BeanItemContainer<Account>(Account.class);
			beanItemContainerCustomer = new BeanItemContainer<Customer>(Customer.class);
			beanItemContainerSalesman = new BeanItemContainer<Salesman>(Salesman.class);
			
			binderItemDetail = new BeanFieldGroup<Bbankdetail>(Bbankdetail.class);
			
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
			beanItemContainerTranstype.addAll(transtypeService.findAllByGrup("GRUP3"));
			
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

		public BbanktranstypeJpaService getTranstypeService() {
			return transtypeService;
		}

		public void setTranstypeService(BbanktranstypeJpaService transtypeService) {
			this.transtypeService = transtypeService;
		}

		public AccountJpaService getAccountService() {
			return accountService;
		}

		public void setAccountService(AccountJpaService accountService) {
			this.accountService = accountService;
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

		public SysvarJpaService getSysvarService() {
			return sysvarService;
		}

		public void setSysvarService(SysvarJpaService sysvarService) {
			this.sysvarService = sysvarService;
		}

		public Bbankheader getItemHeader() {
			return itemHeader;
		}

		public void setItemHeader(Bbankheader itemHeader) {
			this.itemHeader = itemHeader;
		}

		public Bbankdetail getItemDetail() {
			return itemDetail;
		}

		public void setItemDetail(Bbankdetail itemDetail) {
			this.itemDetail = itemDetail;
		}

		public BeanItemContainer<Division> getBeanItemContainerDivision() {
			return beanItemContainerDivision;
		}

		public void setBeanItemContainerDivision(
				BeanItemContainer<Division> beanItemContainerDivision) {
			this.beanItemContainerDivision = beanItemContainerDivision;
		}

		public BeanItemContainer<Bbanktranstype> getBeanItemContainerTranstype() {
			return beanItemContainerTranstype;
		}

		public void setBeanItemContainerTranstype(
				BeanItemContainer<Bbanktranstype> beanItemContainerTranstype) {
			this.beanItemContainerTranstype = beanItemContainerTranstype;
		}

		public BeanItemContainer<Account> getBeanItemContainerAccount() {
			return beanItemContainerAccount;
		}

		public void setBeanItemContainerAccount(
				BeanItemContainer<Account> beanItemContainerAccount) {
			this.beanItemContainerAccount = beanItemContainerAccount;
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

		public BeanFieldGroup<Bbankdetail> getBinderItemDetail() {
			return binderItemDetail;
		}

		public void setBinderItemDetail(BeanFieldGroup<Bbankdetail> binderItemDetail) {
			this.binderItemDetail = binderItemDetail;
		}



		
	
	
}
