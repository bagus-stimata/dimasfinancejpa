package org.dimas.finance.master;

import java.util.List;

import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Region;
import org.dimas.finance.model.Salesman;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class SalesmanBinderModel {
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public Salesman item;
	private List<Salesman> list;

	private JPAContainer<Salesman> tableJpaContainer;	
	private JPAContainer<Region> regionJpaContainer;	
	private JPAContainer<Division> divisionJPAContainer;
	
	private DivisionJpaService divisionJpaService;
	private BeanItemContainer<Division> beanItemContainerDivision;
	
	public BeanItem<Salesman> formBeanItem;
	private BeanItemContainer<Salesman> tableBeanItemContainer;
	private String operationStatus;
	
	public SalesmanBinderModel(){
		tableJpaContainer =  JPAContainerFactory.make(Salesman.class, persistenceUnit);		
		regionJpaContainer = JPAContainerFactory.make(Region.class, persistenceUnit);
		divisionJPAContainer = JPAContainerFactory.make(Division.class, persistenceUnit);		
		
		divisionJpaService = new DivisionJpaServiceImpl();
		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
		
		initData();		
		
	}
	
	public void initData(){
		
		beanItemContainerDivision.addAll(divisionJpaService.findAll());
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

	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
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

	public JPAContainer<Division> getDivisionJPAContainer() {
		return divisionJPAContainer;
	}

	public void setDivisionJPAContainer(JPAContainer<Division> divisionJPAContainer) {
		this.divisionJPAContainer = divisionJPAContainer;
	}

	public DivisionJpaService getDivisionJpaService() {
		return divisionJpaService;
	}

	public void setDivisionJpaService(DivisionJpaService divisionJpaService) {
		this.divisionJpaService = divisionJpaService;
	}

	public BeanItemContainer<Division> getBeanItemContainerDivision() {
		return beanItemContainerDivision;
	}

	public void setBeanItemContainerDivision(
			BeanItemContainer<Division> beanItemContainerDivision) {
		this.beanItemContainerDivision = beanItemContainerDivision;
	}

	public static String getPersistenceunit() {
		return persistenceUnit;
	}
	
	
	
	
}
