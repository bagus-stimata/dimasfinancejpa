package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.jpa.dao.CustomerJpaService;
import org.dimas.finance.jpa.dao.CustomerJpaServiceImpl;
import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.SalesmanJpaService;
import org.dimas.finance.jpa.dao.SalesmanJpaServiceImpl;
import org.dimas.finance.model.Customer;
import org.dimas.finance.model.Customer;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Region;
import org.dimas.finance.model.Salesman;
import org.dimas.finance.util.Inject;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class CustomerModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";

	private CustomerJpaService customerService;
	
	public Customer item;
	public Customer newItem = new Customer();
	private List<Customer> list;

	private JPAContainer<Customer> tableJpaContainer;	
	private JPAContainer<Division> divisionJPAContainer;
	
	public BeanItem<Customer> formBeanItem;
	private BeanItemContainer<Customer> tableBeanItemContainer;
	private String operationStatus;
//	private String operationStatus;
	//MIGRTION FROM FieldFactory To Binder
	private DivisionJpaService divisionService;
	private BeanItemContainer<Division> beanItemContainerDivision;
	private BeanFieldGroup<Customer> binderCustomer;
	
	
	public CustomerModel(){
		initData();
	}
	public void initData(){
		tableJpaContainer =  JPAContainerFactory.make(Customer.class, persistenceUnit);	
		//MIGRTION FROM FieldFactory To Binder		
		divisionService = new DivisionJpaServiceImpl();
		customerService = new CustomerJpaServiceImpl();
		
		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);		
		binderCustomer = new BeanFieldGroup<Customer>(Customer.class);
		
	}

	public void setFreshDataTable(){		
		try{
			tableJpaContainer.removeAllContainerFilters();
			tableJpaContainer.refresh();
			
			beanItemContainerDivision.removeAllContainerFilters();
			beanItemContainerDivision.removeAllItems();
			beanItemContainerDivision.addAll(divisionService.findAll());
			
		} catch(Exception ex){
		
		}
	}
	
	public void setFreshDataForm(){
		
	}
	public Customer getItem() {
		return item;
	}

	public void setItem(Customer item) {
		this.item = item;
	}

	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}

	public BeanItem<Customer> getFormBeanItem() {
		return formBeanItem;
	}

	public void setFormBeanItem(BeanItem<Customer> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public BeanItemContainer<Customer> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<Customer> tableBeanItemContainer) {
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

	public JPAContainer<Customer> getTableJpaContainer() {
		return tableJpaContainer;
	}

	public void setTableJpaContainer(JPAContainer<Customer> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}
	public CustomerJpaService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerJpaService customerService) {
		this.customerService = customerService;
	}
	public Customer getNewItem() {
		return newItem;
	}
	public void setNewItem(Customer newItem) {
		this.newItem = newItem;
	}
	public JPAContainer<Division> getDivisionJPAContainer() {
		return divisionJPAContainer;
	}
	public void setDivisionJPAContainer(JPAContainer<Division> divisionJPAContainer) {
		this.divisionJPAContainer = divisionJPAContainer;
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
	public BeanFieldGroup<Customer> getBinderCustomer() {
		return binderCustomer;
	}
	public void setBinderCustomer(BeanFieldGroup<Customer> binderCustomer) {
		this.binderCustomer = binderCustomer;
	}
	public static String getPersistenceunit() {
		return persistenceUnit;
	}

	
}
