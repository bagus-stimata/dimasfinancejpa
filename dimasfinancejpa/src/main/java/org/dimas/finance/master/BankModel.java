package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Region;
import org.dimas.finance.util.Inject;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class BankModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public Bank item;
	private List<Bank> list;

	private JPAContainer<Bank> tableJpaContainer;	
	private JPAContainer<Region> regionJpaContainer;	
	
	public BeanItem<Bank> formBeanItem;
	private BeanItemContainer<Bank> tableBeanItemContainer;
	private String operationStatus;
	
	public BankModel(){
		initData();
	}
	public void initData(){
		tableJpaContainer =  JPAContainerFactory.make(Bank.class, persistenceUnit);		
		regionJpaContainer = JPAContainerFactory.make(Region.class, persistenceUnit);
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
	public Bank getItem() {
		return item;
	}

	public void setItem(Bank item) {
		this.item = item;
	}

	public List<Bank> getList() {
		return list;
	}

	public void setList(List<Bank> list) {
		this.list = list;
	}

	public BeanItem<Bank> getFormBeanItem() {
		return formBeanItem;
	}

	public void setFormBeanItem(BeanItem<Bank> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public BeanItemContainer<Bank> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<Bank> tableBeanItemContainer) {
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

	public JPAContainer<Bank> getTableJpaContainer() {
		return tableJpaContainer;
	}

	public void setTableJpaContainer(JPAContainer<Bank> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}

	public JPAContainer<Region> getRegionJpaContainer() {
		return regionJpaContainer;
	}

	public void setRegionJpaContainer(JPAContainer<Region> tableJpaContainer) {
		this.regionJpaContainer = regionJpaContainer;
	
	}
	
}
