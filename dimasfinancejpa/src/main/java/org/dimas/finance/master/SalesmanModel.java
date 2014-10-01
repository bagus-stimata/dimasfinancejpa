package org.dimas.finance.master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.jpa.dao.BukuGiroJpaService;
import org.dimas.finance.jpa.dao.BukuGiroJpaServiceImpl;
import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.SalesmanJpaService;
import org.dimas.finance.jpa.dao.SalesmanJpaServiceImpl;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Salesman;
import org.dimas.finance.model.Salesman;
import org.dimas.finance.model.Region;
import org.dimas.finance.util.Inject;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class SalesmanModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	private SalesmanJpaService salesmanService;
	
	public Salesman item;
	public Salesman newItem = new Salesman();
	
	private List<Salesman> list;

	private JPAContainer<Salesman> tableJpaContainer;	
	private JPAContainer<Salesman> tableJpaContainerSearch;	
	private JPAContainer<Region> regionJpaContainer;	
	public BeanItem<Salesman> formBeanItem;
	private BeanItemContainer<Salesman> tableBeanItemContainer;
//	private String operationStatus;
	//MIGRTION FROM FieldFactory To Binder
	private DivisionJpaService divisionService;
	private BeanItemContainer<Division> beanItemContainerDivision;
	private BeanFieldGroup<Salesman> binderSalesman;
	
	public SalesmanModel(){
		initData();
	}
	public void initData(){
		
		tableJpaContainer =  JPAContainerFactory.make(Salesman.class, persistenceUnit);		
		tableJpaContainerSearch =  JPAContainerFactory.make(Salesman.class, persistenceUnit);		
		regionJpaContainer = JPAContainerFactory.make(Region.class, persistenceUnit);
		//MIGRTION FROM FieldFactory To Binder		
		divisionService = new DivisionJpaServiceImpl();
		salesmanService = new SalesmanJpaServiceImpl();
		
		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);		
		binderSalesman = new BeanFieldGroup<Salesman>(Salesman.class);
		
	};

	public void setFreshDataTable(){		
		try{
			tableJpaContainer.removeAllContainerFilters();
			tableJpaContainer.refresh();
			tableJpaContainerSearch.removeAllContainerFilters();
			tableJpaContainerSearch.refresh();

			beanItemContainerDivision.removeAllContainerFilters();
			beanItemContainerDivision.removeAllItems();
			beanItemContainerDivision.addAll(divisionService.findAll());
			
		} catch(Exception ex){
		
		}
	}
	
	public void setFreshDataForm(){
		
	}
	public Salesman getItem() {
		return item;
	}

	public void setItem(Salesman item) {
		this.item = item;
	}

	public List<Salesman> getList() {
		return list;
	}

	public void setList(List<Salesman> list) {
		this.list = list;
	}

	public BeanItem<Salesman> getFormBeanItem() {
		return formBeanItem;
	}

	public void setFormBeanItem(BeanItem<Salesman> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public BeanItemContainer<Salesman> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<Salesman> tableBeanItemContainer) {
		this.tableBeanItemContainer = tableBeanItemContainer;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JPAContainer<Salesman> getTableJpaContainer() {
		return tableJpaContainer;
	}

	public void setTableJpaContainer(JPAContainer<Salesman> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}

	public JPAContainer<Region> getRegionJpaContainer() {
		return regionJpaContainer;
	}

	public void setRegionJpaContainer(JPAContainer<Region> tableJpaContainer) {
		this.regionJpaContainer = regionJpaContainer;
	
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
	public BeanFieldGroup<Salesman> getBinderSalesman() {
		return binderSalesman;
	}
	public void setBinderSalesman(BeanFieldGroup<Salesman> binderSalesman) {
		this.binderSalesman = binderSalesman;
	}
	public static String getPersistenceunit() {
		return persistenceUnit;
	}
	public Salesman getNewItem() {
		return newItem;
	}
	public void setNewItem(Salesman newItem) {
		this.newItem = newItem;
	}
	public JPAContainer<Salesman> getTableJpaContainerSearch() {
		return tableJpaContainerSearch;
	}
	public void setTableJpaContainerSearch(
			JPAContainer<Salesman> tableJpaContainerSearch) {
		this.tableJpaContainerSearch = tableJpaContainerSearch;
	}
	public SalesmanJpaService getSalesmanService() {
		return salesmanService;
	}
	public void setSalesmanService(SalesmanJpaService salesmanService) {
		this.salesmanService = salesmanService;
	}
	
	
	
}
