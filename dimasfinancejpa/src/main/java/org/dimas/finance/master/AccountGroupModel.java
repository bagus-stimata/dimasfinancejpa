package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.model.Accountgroup;
import org.dimas.finance.model.Accountgroup;
import org.dimas.finance.model.Region;
import org.dimas.finance.util.Inject;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class AccountGroupModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public Accountgroup item;
	private List<Accountgroup> list;

	private JPAContainer<Accountgroup> tableJpaContainer;	
	private JPAContainer<Region> regionJpaContainer;	
	
	public BeanItem<Accountgroup> formBeanItem;
	private BeanItemContainer<Accountgroup> tableBeanItemContainer;
	private String operationStatus;
	
	public AccountGroupModel(){
		initData();
	}
	public void initData(){
		tableJpaContainer =  JPAContainerFactory.make(Accountgroup.class, persistenceUnit);		
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
	public Accountgroup getItem() {
		return item;
	}

	public void setItem(Accountgroup item) {
		this.item = item;
	}

	public List<Accountgroup> getList() {
		return list;
	}

	public void setList(List<Accountgroup> list) {
		this.list = list;
	}

	public BeanItem<Accountgroup> getFormBeanItem() {
		return formBeanItem;
	}

	public void setFormBeanItem(BeanItem<Accountgroup> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public BeanItemContainer<Accountgroup> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<Accountgroup> tableBeanItemContainer) {
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

	public JPAContainer<Accountgroup> getTableJpaContainer() {
		return tableJpaContainer;
	}

	public void setTableJpaContainer(JPAContainer<Accountgroup> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}

	public JPAContainer<Region> getRegionJpaContainer() {
		return regionJpaContainer;
	}

	public void setRegionJpaContainer(JPAContainer<Region> tableJpaContainer) {
		this.regionJpaContainer = regionJpaContainer;
	
	}
	
}
