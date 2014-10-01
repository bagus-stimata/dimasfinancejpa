package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Region;
import org.dimas.finance.util.Inject;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class DivisionModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public Division item;
	private List<Division> list;

	private JPAContainer<Division> tableJpaContainer;	
	private JPAContainer<Region> regionJpaContainer;	
	
	public BeanItem<Division> formBeanItem;
	private BeanItemContainer<Division> tableBeanItemContainer;
	private String operationStatus;
	
	public DivisionModel(){
		initData();
	}
	public void initData(){
		tableJpaContainer =  JPAContainerFactory.make(Division.class, persistenceUnit);		
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
	public Division getItem() {
		return item;
	}

	public void setItem(Division item) {
		this.item = item;
	}

	public List<Division> getList() {
		return list;
	}

	public void setList(List<Division> list) {
		this.list = list;
	}

	public BeanItem<Division> getFormBeanItem() {
		return formBeanItem;
	}

	public void setFormBeanItem(BeanItem<Division> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public BeanItemContainer<Division> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<Division> tableBeanItemContainer) {
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

	public JPAContainer<Division> getTableJpaContainer() {
		return tableJpaContainer;
	}

	public void setTableJpaContainer(JPAContainer<Division> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}

	public JPAContainer<Region> getRegionJpaContainer() {
		return regionJpaContainer;
	}

	public void setRegionJpaContainer(JPAContainer<Region> tableJpaContainer) {
		this.regionJpaContainer = regionJpaContainer;
	
	}
	
}
