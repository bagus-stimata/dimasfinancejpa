package org.dimas.finance.systemsetting.harikerja;

import java.util.List;

import org.dimas.finance.jpa.dao.BankJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuGiroJpaServiceImpl;
import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.SalesmanJpaService;
import org.dimas.finance.jpa.dao.SalesmanJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysCalenderJpaService;
import org.dimas.finance.jpa.dao.SysCalenderJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Region;
import org.dimas.finance.model.Salesman;
import org.dimas.finance.model.SysCalender;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class PeriodeModel {

	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	private SysCalenderJpaService sysCalenderService;
	private SysvarJpaService sysvarService;
	
	public SysCalender item  = new SysCalender();
	public SysCalender newItem = new SysCalender();
	
	private JPAContainer<SysCalender> tableJpaContainer;	
	private JPAContainer<SysCalender> tableJpaContainerSearch;	
	
	private BeanItemContainer<SysCalender> tableBeanItemContainer;
	private BeanItemContainer<SysCalender> tableBeanItemContainerSearch;
//	private String operationStatus;
	//MIGRTION FROM FieldFactory To Binder
	private DivisionJpaService divisionService;
	private BeanItemContainer<Division> beanItemContainerDivision;
	private BeanFieldGroup<SysCalender> binderSysCalender;

	public PeriodeModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		//MIGRTION FROM FieldFactory To Binder		
		divisionService = new DivisionJpaServiceImpl();
		sysCalenderService = new SysCalenderJpaServiceImpl();
		sysvarService = new SysvarJpaServiceImpl();
	
		tableJpaContainer =  JPAContainerFactory.make(SysCalender.class, persistenceUnit);		
		tableJpaContainerSearch =  JPAContainerFactory.make(SysCalender.class, persistenceUnit);		

		tableBeanItemContainer = new BeanItemContainer<SysCalender>(SysCalender.class);
		tableBeanItemContainerSearch = new BeanItemContainer<SysCalender>(SysCalender.class);
		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
		
	}
	public SysvarJpaService getSysvarService() {
		return sysvarService;
	}

	public void setSysvarService(SysvarJpaService sysvarService) {
		this.sysvarService = sysvarService;
	}

	public void initVariableData(){
		reload();
		
		
	}
	public void reload(){
		tableBeanItemContainer.removeAllContainerFilters();
		beanItemContainerDivision.removeAllContainerFilters();
		tableBeanItemContainer.removeAllItems();
		beanItemContainerDivision.removeAllItems();
		
		tableBeanItemContainer.addAll(sysCalenderService.findAll());
		beanItemContainerDivision.addAll(divisionService.findAll());
		
	}

	public SysCalenderJpaService getSysCalenderService() {
		return sysCalenderService;
	}

	public void setSysCalenderService(SysCalenderJpaService sysCalenderService) {
		this.sysCalenderService = sysCalenderService;
	}

	public SysCalender getItem() {
		return item;
	}

	public void setItem(SysCalender item) {
		this.item = item;
	}

	public SysCalender getNewItem() {
		return newItem;
	}

	public void setNewItem(SysCalender newItem) {
		this.newItem = newItem;
	}

	public JPAContainer<SysCalender> getTableJpaContainer() {
		return tableJpaContainer;
	}

	public void setTableJpaContainer(JPAContainer<SysCalender> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}

	public JPAContainer<SysCalender> getTableJpaContainerSearch() {
		return tableJpaContainerSearch;
	}

	public void setTableJpaContainerSearch(
			JPAContainer<SysCalender> tableJpaContainerSearch) {
		this.tableJpaContainerSearch = tableJpaContainerSearch;
	}

	public BeanItemContainer<SysCalender> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<SysCalender> tableBeanItemContainer) {
		this.tableBeanItemContainer = tableBeanItemContainer;
	}

	public BeanItemContainer<SysCalender> getTableBeanItemContainerSearch() {
		return tableBeanItemContainerSearch;
	}

	public void setTableBeanItemContainerSearch(
			BeanItemContainer<SysCalender> tableBeanItemContainerSearch) {
		this.tableBeanItemContainerSearch = tableBeanItemContainerSearch;
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

	public BeanFieldGroup<SysCalender> getBinderSysCalender() {
		return binderSysCalender;
	}

	public void setBinderSysCalender(BeanFieldGroup<SysCalender> binderSysCalender) {
		this.binderSysCalender = binderSysCalender;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getPersistenceunit() {
		return persistenceUnit;
	}
	
	
	
	
}
