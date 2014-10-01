package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.jpa.dao.AccountJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkbtranstypeJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuGiroJpaService;
import org.dimas.finance.jpa.dao.BukuGiroJpaServiceImpl;
import org.dimas.finance.jpa.dao.AccountJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkbtranstypeJpaService;
import org.dimas.finance.jpa.dao.SalesmanJpaServiceImpl;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Account;
import org.dimas.finance.model.Bkbtranstype;
import org.dimas.finance.model.Bkbtranstype;
import org.dimas.finance.model.Account;
import org.dimas.finance.util.Inject;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class BkbtranstypeModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	private BkbtranstypeJpaService bkbtranstypeService;
	
	public Bkbtranstype item;
	public Bkbtranstype newItem = new Bkbtranstype();
	
	private List<Bkbtranstype> list;

	private JPAContainer<Bkbtranstype> tableJpaContainer;	
	private JPAContainer<Bkbtranstype> tableJpaContainerSearch;	
	public BeanItem<Bkbtranstype> formBeanItem;
	private BeanItemContainer<Bkbtranstype> tableBeanItemContainer;
//	private String operationStatus;
	//MIGRTION FROM FieldFactory To Binder
	private AccountJpaService accountService;
	private BeanItemContainer<Account> beanItemContainerAccount;
	private BeanFieldGroup<Bkbtranstype> binderBkbtranstype;
	
	public BkbtranstypeModel(){
		initData();
	}
	public void initData(){
		
		tableJpaContainer =  JPAContainerFactory.make(Bkbtranstype.class, persistenceUnit);		
		tableJpaContainerSearch =  JPAContainerFactory.make(Bkbtranstype.class, persistenceUnit);		
		//MIGRTION FROM FieldFactory To Binder		
		accountService = new AccountJpaServiceImpl();
		bkbtranstypeService = new BkbtranstypeJpaServiceImpl();
		
		beanItemContainerAccount = new BeanItemContainer<Account>(Account.class);		
		binderBkbtranstype = new BeanFieldGroup<Bkbtranstype>(Bkbtranstype.class);
		
	};

	public void setFreshDataTable(){		
		try{
			tableJpaContainer.removeAllContainerFilters();
			tableJpaContainer.refresh();
			tableJpaContainerSearch.removeAllContainerFilters();
			tableJpaContainerSearch.refresh();

			beanItemContainerAccount.removeAllContainerFilters();
			beanItemContainerAccount.removeAllItems();
			beanItemContainerAccount.addAll(accountService.findAll());
			
		} catch(Exception ex){
		
		}
	}
	
	public void setFreshDataForm(){		
	}
	public BkbtranstypeJpaService getBkbtranstypeService() {
		return bkbtranstypeService;
	}
	public void setBkbtranstypeService(BkbtranstypeJpaService bkbtranstypeService) {
		this.bkbtranstypeService = bkbtranstypeService;
	}
	public Bkbtranstype getItem() {
		return item;
	}
	public void setItem(Bkbtranstype item) {
		this.item = item;
	}
	public Bkbtranstype getNewItem() {
		return newItem;
	}
	public void setNewItem(Bkbtranstype newItem) {
		this.newItem = newItem;
	}
	public List<Bkbtranstype> getList() {
		return list;
	}
	public void setList(List<Bkbtranstype> list) {
		this.list = list;
	}
	public JPAContainer<Bkbtranstype> getTableJpaContainer() {
		return tableJpaContainer;
	}
	public void setTableJpaContainer(JPAContainer<Bkbtranstype> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}
	public JPAContainer<Bkbtranstype> getTableJpaContainerSearch() {
		return tableJpaContainerSearch;
	}
	public void setTableJpaContainerSearch(
			JPAContainer<Bkbtranstype> tableJpaContainerSearch) {
		this.tableJpaContainerSearch = tableJpaContainerSearch;
	}
	public BeanItem<Bkbtranstype> getFormBeanItem() {
		return formBeanItem;
	}
	public void setFormBeanItem(BeanItem<Bkbtranstype> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}
	public BeanItemContainer<Bkbtranstype> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}
	public void setTableBeanItemContainer(
			BeanItemContainer<Bkbtranstype> tableBeanItemContainer) {
		this.tableBeanItemContainer = tableBeanItemContainer;
	}
	public AccountJpaService getAccountService() {
		return accountService;
	}
	public void setAccountService(AccountJpaService accountService) {
		this.accountService = accountService;
	}
	public BeanItemContainer<Account> getBeanItemContainerAccount() {
		return beanItemContainerAccount;
	}
	public void setBeanItemContainerAccount(
			BeanItemContainer<Account> beanItemContainerAccount) {
		this.beanItemContainerAccount = beanItemContainerAccount;
	}
	public BeanFieldGroup<Bkbtranstype> getBinderBkbtranstype() {
		return binderBkbtranstype;
	}
	public void setBinderBkbtranstype(
			BeanFieldGroup<Bkbtranstype> binderBkbtranstype) {
		this.binderBkbtranstype = binderBkbtranstype;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getPersistenceunit() {
		return persistenceUnit;
	}
	
	

	
	
	
}
