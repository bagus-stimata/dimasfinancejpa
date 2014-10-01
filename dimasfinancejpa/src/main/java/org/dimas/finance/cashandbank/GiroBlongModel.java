package org.dimas.finance.cashandbank;

import org.dimas.finance.jpa.dao.ArPaymentDetailJpaService;
import org.dimas.finance.jpa.dao.ArPaymentDetailJpaServiceImpl;
import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaService;
import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BankJpaService;
import org.dimas.finance.jpa.dao.BankJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuGiroJpaService;
import org.dimas.finance.jpa.dao.BukuGiroJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.model.Arpaymentheader;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class GiroBlongModel {
	//1. DAO SERVICE
	private BukuGiroJpaService bukuGiroService;
	private BankJpaService bankService;
	private SysvarJpaService sysvarService;
	private TransaksiHelper transaksiHelper = new TransaksiHelper();
	
	//KARENA MEREFISI
	private ArPaymentHeaderJpaService arpaymentHeaderService;
	private ArPaymentDetailJpaService arpaymentDetailService;
	
//2. ENTITY
	protected Bukugiro bukugiro;
	protected Bukugiro newBukuGiro;
	protected Bank bank;
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	private BeanItemContainer<Bukugiro> beanItemContainerBukugiro;
	private BeanItemContainer<Bukugiro> beanItemContainerBukugiroSearch;
	
	private BeanItemContainer<Bank> beanItemContainerBank;
//5. Binder (BeanFieldGroup)
	private BeanFieldGroup<Bukugiro> binderBukugiro;
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public GiroBlongModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		bukuGiroService = new BukuGiroJpaServiceImpl();
		bankService = new BankJpaServiceImpl();
		sysvarService = new SysvarJpaServiceImpl();
		arpaymentHeaderService = new ArPaymentHeaderJpaServiceImpl();
		arpaymentDetailService = new ArPaymentDetailJpaServiceImpl();
		
		bukugiro = new Bukugiro();
		bank = new Bank();
		
		beanItemContainerBukugiro = new BeanItemContainer<Bukugiro>(Bukugiro.class);
		beanItemContainerBukugiroSearch = new BeanItemContainer<Bukugiro>(Bukugiro.class);
		beanItemContainerBank = new BeanItemContainer<Bank>(Bank.class);
		
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
		
		beanItemContainerBukugiro.addAll(bukuGiroService.findAll());
		beanItemContainerBank.addAll(bankService.findAll());
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
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public ArPaymentHeaderJpaService getArpaymentHeaderService() {
		return arpaymentHeaderService;
	}
	public void setArpaymentHeaderService(
			ArPaymentHeaderJpaService arpaymentHeaderService) {
		this.arpaymentHeaderService = arpaymentHeaderService;
	}
	public ArPaymentDetailJpaService getArpaymentDetailService() {
		return arpaymentDetailService;
	}
	public void setArpaymentDetailService(
			ArPaymentDetailJpaService arpaymentDetailService) {
		this.arpaymentDetailService = arpaymentDetailService;
	}
	public Bukugiro getBukugiro() {
		return bukugiro;
	}
	public void setBukugiro(Bukugiro bukugiro) {
		this.bukugiro = bukugiro;
	}
	public Bukugiro getNewBukuGiro() {
		return newBukuGiro;
	}
	public void setNewBukuGiro(Bukugiro newBukuGiro) {
		this.newBukuGiro = newBukuGiro;
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
	public BeanItemContainer<Bukugiro> getBeanItemContainerBukugiroSearch() {
		return beanItemContainerBukugiroSearch;
	}
	public void setBeanItemContainerBukugiroSearch(
			BeanItemContainer<Bukugiro> beanItemContainerBukugiroSearch) {
		this.beanItemContainerBukugiroSearch = beanItemContainerBukugiroSearch;
	}
	public BeanItemContainer<Bank> getBeanItemContainerBank() {
		return beanItemContainerBank;
	}
	public void setBeanItemContainerBank(
			BeanItemContainer<Bank> beanItemContainerBank) {
		this.beanItemContainerBank = beanItemContainerBank;
	}
	public BeanFieldGroup<Bukugiro> getBinderBukugiro() {
		return binderBukugiro;
	}
	public void setBinderBukugiro(BeanFieldGroup<Bukugiro> binderBukugiro) {
		this.binderBukugiro = binderBukugiro;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}



}
