package org.dimas.finance.cashandbank;

import org.dimas.finance.jpa.dao.BankJpaService;
import org.dimas.finance.jpa.dao.BankJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuTransferJpaService;
import org.dimas.finance.jpa.dao.BukuTransferJpaServiceImpl;
import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Bukutransfer;
import org.dimas.finance.model.Division;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class BukuTransferModel {
	
	//1. DAO SERVICE
		private BukuTransferJpaService bukuTranferService;
		private BankJpaService bankService;
		private SysvarJpaService sysvarService;
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		private DivisionJpaService divisionService;
		
	//2. ENTITY
		protected Bukutransfer bukutransfer;
		protected Bukutransfer newBukuTransfer;
		protected Bank bank;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<Bukutransfer> beanItemContainerBukutransfer;
		private BeanItemContainer<Bukutransfer> beanItemContainerBukutransferSearch;
		
		private BeanItemContainer<Bank> beanItemContainerBank;
		private BeanItemContainer<Division> beanItemContainerDivision;
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<Bukutransfer> binderBukutransfer;
				
		//OTHERS
		protected String OperationStatus = "OPEN";
	
	public BukuTransferModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		bukuTranferService = new BukuTransferJpaServiceImpl();
		bankService = new BankJpaServiceImpl();
		sysvarService = new SysvarJpaServiceImpl();
		divisionService = new DivisionJpaServiceImpl();
		
		bukutransfer = new Bukutransfer();
		bank = new Bank();
		
		beanItemContainerBukutransfer = new BeanItemContainer<Bukutransfer>(Bukutransfer.class);
		beanItemContainerBukutransferSearch = new BeanItemContainer<Bukutransfer>(Bukutransfer.class);
		beanItemContainerBank = new BeanItemContainer<Bank>(Bank.class);
		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
		
		binderBukutransfer = new BeanFieldGroup<Bukutransfer>(Bukutransfer.class);
		
	}
	public void initVariableData(){
		reload();
		
		
	}
	public void reload(){
		beanItemContainerBukutransfer.removeAllContainerFilters();
		beanItemContainerBank.removeAllContainerFilters();
		beanItemContainerBukutransfer.removeAllItems();
		beanItemContainerBank.removeAllItems();
		beanItemContainerDivision.removeAllContainerFilters();
		beanItemContainerDivision.removeAllItems();
		
		beanItemContainerBukutransfer.addAll(bukuTranferService.findAll());
		beanItemContainerBank.addAll(bankService.findAll());
		beanItemContainerDivision.addAll(divisionService.findAll());
	}
	
	public BukuTransferJpaService getBukuTranferService() {
		return bukuTranferService;
	}
	public void setBukuTranferService(BukuTransferJpaService bukuTranferService) {
		this.bukuTranferService = bukuTranferService;
	}
	public BankJpaService getBankService() {
		return bankService;
	}
	public void setBankService(BankJpaService bankService) {
		this.bankService = bankService;
	}
	public SysvarJpaService getSysvarService() {
		return sysvarService;
	}
	public void setSysvarService(SysvarJpaService sysvarService) {
		this.sysvarService = sysvarService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	
	
	public Bukutransfer getBukutransfer() {
		return bukutransfer;
	}
	public void setBukutransfer(Bukutransfer bukutransfer) {
		this.bukutransfer = bukutransfer;
	}
	public Bukutransfer getNewBukuTransfer() {
		return newBukuTransfer;
	}
	public void setNewBukuTransfer(Bukutransfer newBukuTransfer) {
		this.newBukuTransfer = newBukuTransfer;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public BeanItemContainer<Bukutransfer> getBeanItemContainerBukutransfer() {
		return beanItemContainerBukutransfer;
	}
	public void setBeanItemContainerBukutransfer(
			BeanItemContainer<Bukutransfer> beanItemContainerBukutransfer) {
		this.beanItemContainerBukutransfer = beanItemContainerBukutransfer;
	}
	public BeanItemContainer<Bukutransfer> getBeanItemContainerBukutransferSearch() {
		return beanItemContainerBukutransferSearch;
	}
	public void setBeanItemContainerBukutransferSearch(
			BeanItemContainer<Bukutransfer> beanItemContainerBukutransferSearch) {
		this.beanItemContainerBukutransferSearch = beanItemContainerBukutransferSearch;
	}
	public BeanItemContainer<Bank> getBeanItemContainerBank() {
		return beanItemContainerBank;
	}
	public void setBeanItemContainerBank(
			BeanItemContainer<Bank> beanItemContainerBank) {
		this.beanItemContainerBank = beanItemContainerBank;
	}
	public BeanFieldGroup<Bukutransfer> getBinderBukutransfer() {
		return binderBukutransfer;
	}
	public void setBinderBukutransfer(
			BeanFieldGroup<Bukutransfer> binderBukutransfer) {
		this.binderBukutransfer = binderBukutransfer;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
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
