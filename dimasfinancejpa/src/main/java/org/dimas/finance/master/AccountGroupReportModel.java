package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.model.Area;
import org.dimas.finance.model.Accountgroupreport;
import org.dimas.finance.util.Inject;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class AccountGroupReportModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public Accountgroupreport item;
	private List<Accountgroupreport> list;

	private JPAContainer<Accountgroupreport> tableJpaContainer;	
	public BeanItem<Accountgroupreport> formBeanItem;
	private BeanItemContainer<Accountgroupreport> tableBeanItemContainer;
	private String operationStatus;
	
	public AccountGroupReportModel(){
		initData();
	}
	public void initData(){
		tableJpaContainer =  JPAContainerFactory.make(Accountgroupreport.class, persistenceUnit);		
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
	public Accountgroupreport getItem() {
		return item;
	}

	public void setItem(Accountgroupreport item) {
		this.item = item;
	}

	public List<Accountgroupreport> getList() {
		return list;
	}

	public void setList(List<Accountgroupreport> list) {
		this.list = list;
	}

	public BeanItem<Accountgroupreport> getFormBeanItem() {
		return formBeanItem;
	}

	public void setFormBeanItem(BeanItem<Accountgroupreport> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public BeanItemContainer<Accountgroupreport> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<Accountgroupreport> tableBeanItemContainer) {
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

	public JPAContainer<Accountgroupreport> getTableJpaContainer() {
		return tableJpaContainer;
	}

	public void setTableJpaContainer(JPAContainer<Accountgroupreport> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}

	
	
}
