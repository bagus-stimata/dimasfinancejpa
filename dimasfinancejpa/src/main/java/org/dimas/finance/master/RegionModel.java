package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.model.Area;
import org.dimas.finance.model.Region;
import org.dimas.finance.util.Inject;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class RegionModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public Region item;
	private List<Region> list;

	private JPAContainer<Region> tableJpaContainer;	
	public BeanItem<Region> formBeanItem;
	private BeanItemContainer<Region> tableBeanItemContainer;
	private String operationStatus;
	
	public RegionModel(){
		initData();
	}
	public void initData(){
		tableJpaContainer =  JPAContainerFactory.make(Region.class, persistenceUnit);		
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
	public Region getItem() {
		return item;
	}

	public void setItem(Region item) {
		this.item = item;
	}

	public List<Region> getList() {
		return list;
	}

	public void setList(List<Region> list) {
		this.list = list;
	}

	public BeanItem<Region> getFormBeanItem() {
		return formBeanItem;
	}

	public void setFormBeanItem(BeanItem<Region> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public BeanItemContainer<Region> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<Region> tableBeanItemContainer) {
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

	public JPAContainer<Region> getTableJpaContainer() {
		return tableJpaContainer;
	}

	public void setTableJpaContainer(JPAContainer<Region> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}

	
	
}
