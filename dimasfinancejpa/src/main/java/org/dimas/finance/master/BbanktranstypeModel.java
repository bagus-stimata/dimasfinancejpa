package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.jpa.dao.AccountJpaServiceImpl;
import org.dimas.finance.jpa.dao.BbanktranstypeJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuGiroJpaService;
import org.dimas.finance.jpa.dao.BukuGiroJpaServiceImpl;
import org.dimas.finance.jpa.dao.AccountJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.BbanktranstypeJpaService;
import org.dimas.finance.jpa.dao.SalesmanJpaServiceImpl;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Account;
import org.dimas.finance.model.Bbanktranstype;
import org.dimas.finance.model.Bbanktranstype;
import org.dimas.finance.model.Account;
import org.dimas.finance.util.Inject;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class BbanktranstypeModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	private BbanktranstypeJpaService bbanktranstypeService;
	
	public Bbanktranstype item;
	public Bbanktranstype newItem = new Bbanktranstype();
	
	private List<Bbanktranstype> list;

	private JPAContainer<Bbanktranstype> tableJpaContainer;	
	private JPAContainer<Bbanktranstype> tableJpaContainerSearch;	
	public BeanItem<Bbanktranstype> formBeanItem;
	private BeanItemContainer<Bbanktranstype> tableBeanItemContainer;
//	private String operationStatus;
	//MIGRTION FROM FieldFactory To Binder
	private AccountJpaService accountService;
	private BeanItemContainer<Account> beanItemContainerAccount;
	private BeanFieldGroup<Bbanktranstype> binderBbanktranstype;
	
	public BbanktranstypeModel(){
		initData();
	}
	public void initData(){
		
		tableJpaContainer =  JPAContainerFactory.make(Bbanktranstype.class, persistenceUnit);		
		tableJpaContainerSearch =  JPAContainerFactory.make(Bbanktranstype.class, persistenceUnit);		
		//MIGRTION FROM FieldFactory To Binder		
		accountService = new AccountJpaServiceImpl();
		bbanktranstypeService = new BbanktranstypeJpaServiceImpl();
		
		beanItemContainerAccount = new BeanItemContainer<Account>(Account.class);		
		binderBbanktranstype = new BeanFieldGroup<Bbanktranstype>(Bbanktranstype.class);
		
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
	public BbanktranstypeJpaService getBbanktranstypeService() {
		return bbanktranstypeService;
	}
	public void setBbanktranstypeService(
			BbanktranstypeJpaService bbanktranstypeService) {
		this.bbanktranstypeService = bbanktranstypeService;
	}
	public Bbanktranstype getItem() {
		return item;
	}
	public void setItem(Bbanktranstype item) {
		this.item = item;
	}
	public Bbanktranstype getNewItem() {
		return newItem;
	}
	public void setNewItem(Bbanktranstype newItem) {
		this.newItem = newItem;
	}
	public List<Bbanktranstype> getList() {
		return list;
	}
	public void setList(List<Bbanktranstype> list) {
		this.list = list;
	}
	public JPAContainer<Bbanktranstype> getTableJpaContainer() {
		return tableJpaContainer;
	}
	public void setTableJpaContainer(JPAContainer<Bbanktranstype> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}
	public JPAContainer<Bbanktranstype> getTableJpaContainerSearch() {
		return tableJpaContainerSearch;
	}
	public void setTableJpaContainerSearch(
			JPAContainer<Bbanktranstype> tableJpaContainerSearch) {
		this.tableJpaContainerSearch = tableJpaContainerSearch;
	}
	public BeanItem<Bbanktranstype> getFormBeanItem() {
		return formBeanItem;
	}
	public void setFormBeanItem(BeanItem<Bbanktranstype> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}
	public BeanItemContainer<Bbanktranstype> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}
	public void setTableBeanItemContainer(
			BeanItemContainer<Bbanktranstype> tableBeanItemContainer) {
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
	public BeanFieldGroup<Bbanktranstype> getBinderBbanktranstype() {
		return binderBbanktranstype;
	}
	public void setBinderBbanktranstype(
			BeanFieldGroup<Bbanktranstype> binderBbanktranstype) {
		this.binderBbanktranstype = binderBbanktranstype;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getPersistenceunit() {
		return persistenceUnit;
	}
	
	

	
	
	
}
