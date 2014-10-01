package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.model.Area;
import org.dimas.finance.model.Departement;
import org.dimas.finance.util.Inject;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class DepartementModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public Departement item;
	private List<Departement> list;

	private JPAContainer<Departement> tableJpaContainer;	
	public BeanItem<Departement> formBeanItem;
	private BeanItemContainer<Departement> tableBeanItemContainer;
	private String operationStatus;
	
	public DepartementModel(){
		initData();
	}
	public void initData(){
		tableJpaContainer =  JPAContainerFactory.make(Departement.class, persistenceUnit);		
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
	public Departement getItem() {
		return item;
	}

	public void setItem(Departement item) {
		this.item = item;
	}

	public List<Departement> getList() {
		return list;
	}

	public void setList(List<Departement> list) {
		this.list = list;
	}

	public BeanItem<Departement> getFormBeanItem() {
		return formBeanItem;
	}

	public void setFormBeanItem(BeanItem<Departement> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public BeanItemContainer<Departement> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<Departement> tableBeanItemContainer) {
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

	public JPAContainer<Departement> getTableJpaContainer() {
		return tableJpaContainer;
	}

	public void setTableJpaContainer(JPAContainer<Departement> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}

	
	
}
