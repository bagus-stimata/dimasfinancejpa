package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.jpa.dao.AccountJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkktranstypeJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuGiroJpaService;
import org.dimas.finance.jpa.dao.BukuGiroJpaServiceImpl;
import org.dimas.finance.jpa.dao.AccountJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkktranstypeJpaService;
import org.dimas.finance.jpa.dao.SalesmanJpaServiceImpl;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Account;
import org.dimas.finance.model.Bkktranstype;
import org.dimas.finance.model.Bkktranstype;
import org.dimas.finance.model.Account;
import org.dimas.finance.util.Inject;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class BkktranstypeModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	private BkktranstypeJpaService bkktranstypeService;
	
	public Bkktranstype item;
	public Bkktranstype newItem = new Bkktranstype();
	
	private List<Bkktranstype> list;

	private JPAContainer<Bkktranstype> tableJpaContainer;	
	private JPAContainer<Bkktranstype> tableJpaContainerSearch;	
	public BeanItem<Bkktranstype> formBeanItem;
	private BeanItemContainer<Bkktranstype> tableBeanItemContainer;
//	private String operationStatus;
	//MIGRTION FROM FieldFactory To Binder
	private AccountJpaService accountService;
	private BeanItemContainer<Account> beanItemContainerAccount;
	private BeanFieldGroup<Bkktranstype> binderBkktranstype;
	
	public BkktranstypeModel(){
		initData();
	}
	public void initData(){
		
		tableJpaContainer =  JPAContainerFactory.make(Bkktranstype.class, persistenceUnit);		
		tableJpaContainerSearch =  JPAContainerFactory.make(Bkktranstype.class, persistenceUnit);		
		//MIGRTION FROM FieldFactory To Binder		
		accountService = new AccountJpaServiceImpl();
		bkktranstypeService = new BkktranstypeJpaServiceImpl();
		
		beanItemContainerAccount = new BeanItemContainer<Account>(Account.class);		
		binderBkktranstype = new BeanFieldGroup<Bkktranstype>(Bkktranstype.class);
		
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
	public BkktranstypeJpaService getBkktranstypeService() {
		return bkktranstypeService;
	}
	public void setBkktranstypeService(BkktranstypeJpaService bkktranstypeService) {
		this.bkktranstypeService = bkktranstypeService;
	}
	public Bkktranstype getItem() {
		return item;
	}
	public void setItem(Bkktranstype item) {
		this.item = item;
	}
	public Bkktranstype getNewItem() {
		return newItem;
	}
	public void setNewItem(Bkktranstype newItem) {
		this.newItem = newItem;
	}
	public List<Bkktranstype> getList() {
		return list;
	}
	public void setList(List<Bkktranstype> list) {
		this.list = list;
	}
	public JPAContainer<Bkktranstype> getTableJpaContainer() {
		return tableJpaContainer;
	}
	public void setTableJpaContainer(JPAContainer<Bkktranstype> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}
	public JPAContainer<Bkktranstype> getTableJpaContainerSearch() {
		return tableJpaContainerSearch;
	}
	public void setTableJpaContainerSearch(
			JPAContainer<Bkktranstype> tableJpaContainerSearch) {
		this.tableJpaContainerSearch = tableJpaContainerSearch;
	}
	public BeanItem<Bkktranstype> getFormBeanItem() {
		return formBeanItem;
	}
	public void setFormBeanItem(BeanItem<Bkktranstype> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}
	public BeanItemContainer<Bkktranstype> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}
	public void setTableBeanItemContainer(
			BeanItemContainer<Bkktranstype> tableBeanItemContainer) {
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
	public BeanFieldGroup<Bkktranstype> getBinderBkktranstype() {
		return binderBkktranstype;
	}
	public void setBinderBkktranstype(
			BeanFieldGroup<Bkktranstype> binderBkktranstype) {
		this.binderBkktranstype = binderBkktranstype;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getPersistenceunit() {
		return persistenceUnit;
	}
	
	

	
	
	
}
