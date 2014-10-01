package org.dimas.finance.cashandbank;

import java.util.List;

import org.dimas.finance.jpa.dao.BankJpaService;
import org.dimas.finance.jpa.dao.BankJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuGiroJpaService;
import org.dimas.finance.jpa.dao.BukuGiroJpaServiceImpl;
import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Division;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class BukuGiroModel {
	
	//1. DAO SERVICE
		private BukuGiroJpaService bukuGiroService;
		private BankJpaService bankService;
		private SysvarJpaService sysvarService;
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		private DivisionJpaService divisionService;
		
	//2. ENTITY
		protected Bukugiro bukugiro;
		protected Bukugiro newBukuGiro;
		protected Bank bank;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<Bukugiro> beanItemContainerBukugiro;
		private BeanItemContainer<Bukugiro> beanItemContainerBukugiroSearch;
		
		private BeanItemContainer<Bank> beanItemContainerBank;
		private BeanItemContainer<Division> beanItemContainerDivision;
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<Bukugiro> binderBukugiro;
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public BukuGiroModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		bukuGiroService = new BukuGiroJpaServiceImpl();
		bankService = new BankJpaServiceImpl();
		sysvarService = new SysvarJpaServiceImpl();
		divisionService = new DivisionJpaServiceImpl();
		
		bukugiro = new Bukugiro();
		bank = new Bank();
		
		beanItemContainerBukugiro = new BeanItemContainer<Bukugiro>(Bukugiro.class);
		beanItemContainerBukugiroSearch = new BeanItemContainer<Bukugiro>(Bukugiro.class);
		beanItemContainerBank = new BeanItemContainer<Bank>(Bank.class);
		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
		
		binderBukugiro = new BeanFieldGroup<Bukugiro>(Bukugiro.class);
		
	}
	public void initVariableData(){
		reload();
		
		
	}
	public void reload(){
		beanItemContainerBukugiro.removeAllContainerFilters();
		beanItemContainerBank.removeAllContainerFilters();
		beanItemContainerBukugiro.removeAllItems();
		beanItemContainerBank.removeAllItems();
		beanItemContainerDivision.removeAllContainerFilters();
		beanItemContainerDivision.removeAllItems();
		
		beanItemContainerBukugiro.addAll(bukuGiroService.findAll());
		beanItemContainerBank.addAll(bankService.findAll());
		beanItemContainerDivision.addAll(divisionService.findAll());
		
	}
	public BukuGiroJpaService getBukuGiroService() {
		return bukuGiroService;
	}
	public void setBukuGiroService(BukuGiroJpaService bukuGiroService) {
		this.bukuGiroService = bukuGiroService;
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
	public Bukugiro getBukugiro() {
		return bukugiro;
	}
	public void setBukugiro(Bukugiro bukugiro) {
		this.bukugiro = bukugiro;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public BeanItemContainer<Bukugiro> getBeanItemContainerBukugiro() {
		return beanItemContainerBukugiro;
	}
	public void setBeanItemContainerBukugiro(
			BeanItemContainer<Bukugiro> beanItemContainerBukugiro) {
		this.beanItemContainerBukugiro = beanItemContainerBukugiro;
	}
	public BeanFieldGroup<Bukugiro> getBinderBukugiro() {
		return binderBukugiro;
	}
	public void setBinderBukugiro(BeanFieldGroup<Bukugiro> binderBukugiro) {
		this.binderBukugiro = binderBukugiro;
	}
	public BeanItemContainer<Bank> getBeanItemContainerBank() {
		return beanItemContainerBank;
	}
	public void setBeanItemContainerBank(
			BeanItemContainer<Bank> beanItemContainerBank) {
		this.beanItemContainerBank = beanItemContainerBank;
	}
	public Bukugiro getNewBukuGiro() {
		return newBukuGiro;
	}
	public void setNewBukuGiro(Bukugiro newBukuGiro) {
		this.newBukuGiro = newBukuGiro;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	public BeanItemContainer<Bukugiro> getBeanItemContainerBukugiroSearch() {
		return beanItemContainerBukugiroSearch;
	}
	public void setBeanItemContainerBukugiroSearch(
			BeanItemContainer<Bukugiro> beanItemContainerBukugiroSearch) {
		this.beanItemContainerBukugiroSearch = beanItemContainerBukugiroSearch;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
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
