package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.model.Account;
import org.dimas.finance.model.Account;
import org.dimas.finance.model.Account;
import org.dimas.finance.util.Inject;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class AccountModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public Account item;
	private List<Account> list;

	private JPAContainer<Account> tableJpaContainer;	
	private JPAContainer<Account> regionJpaContainer;	
	
	public BeanItem<Account> formBeanItem;
	private BeanItemContainer<Account> tableBeanItemContainer;
	private String operationStatus;
	
	public AccountModel(){
		initData();
	}
	public void initData(){
		tableJpaContainer =  JPAContainerFactory.make(Account.class, persistenceUnit);		
		regionJpaContainer = JPAContainerFactory.make(Account.class, persistenceUnit);
	};

	public void setFreshDataTable(){		
		try{
			tableJpaContainer.removeAllContainerFilters();
			tableJpaContainer.refresh();
			
		} catch(Exception ex){
		
		}
	}
	
	public void setFreshDataForm(){
		
	}
	public Account getItem() {
		return item;
	}

	public void setItem(Account item) {
		this.item = item;
	}

	public List<Account> getList() {
		return list;
	}

	public void setList(List<Account> list) {
		this.list = list;
	}

	public BeanItem<Account> getFormBeanItem() {
		return formBeanItem;
	}

	public void setFormBeanItem(BeanItem<Account> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public BeanItemContainer<Account> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<Account> tableBeanItemContainer) {
		this.tableBeanItemContainer = tableBeanItemContainer;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPAContainer<Account> getTableJpaContainer() {
		return tableJpaContainer;
	}

	public void setTableJpaContainer(JPAContainer<Account> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}

	public JPAContainer<Account> getRegionJpaContainer() {
		return regionJpaContainer;
	}

	public void setRegionJpaContainer(JPAContainer<Account> tableJpaContainer) {
		this.regionJpaContainer = regionJpaContainer;
	
	}
	
}
