package org.dimas.finance.systemsetting;

import org.dimas.finance.jpa.dao.BankJpaService;
import org.dimas.finance.jpa.dao.BankJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuGiroJpaService;
import org.dimas.finance.jpa.dao.BukuGiroJpaServiceImpl;
import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Sysvar;
import org.dimas.finance.model.SysvarPK;
import org.dimas.finance.util.SysvarHelper;
import org.dimas.finance.util.TransaksiHelper;
import org.thymeleaf.standard.expression.DivisionExpression;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class ParameterSystemModel {
//1. DAO SERVICE
	private SysvarJpaService sysvarService;
	private DivisionJpaService divisionService;
//2. ENTITY
	protected Sysvar sysvar;
	protected Sysvar newSysvar;
	
	private SysvarHelper sysvarHelper =  new SysvarHelper();
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	private BeanItemContainer<Sysvar> beanItemContainerSysvar;
	private BeanItemContainer<Sysvar> beanItemContainerSysvarSearch;
	
	private BeanItemContainer<Division> beanItemContainerDivision;
//5. Binder (BeanFieldGroup)
	private BeanFieldGroup<Sysvar> binderSysvar;
			
//OTHERS
	protected String OperationStatus = "OPEN";

	public ParameterSystemModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		sysvarService = new SysvarJpaServiceImpl();
		divisionService = new DivisionJpaServiceImpl();
		
		sysvar = new Sysvar();
		
		beanItemContainerSysvar = new BeanItemContainer<Sysvar>(Sysvar.class);
		beanItemContainerSysvarSearch = new BeanItemContainer<Sysvar>(Sysvar.class);
		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
		
		binderSysvar = new BeanFieldGroup<Sysvar>(Sysvar.class);
		
	}
	public void initVariableData(){
		reload();
		
	}
	public void reload(){
		beanItemContainerSysvar.removeAllContainerFilters();
		beanItemContainerSysvar.removeAllItems();		
		beanItemContainerDivision.removeAllContainerFilters();
		beanItemContainerDivision.removeAllItems();
		
		beanItemContainerSysvar.addAll(sysvarService.findAll());
		beanItemContainerDivision.addAll(divisionService.findAll());
	}

	public SysvarJpaService getSysvarService() {
		return sysvarService;
	}

	public void setSysvarService(SysvarJpaService sysvarService) {
		this.sysvarService = sysvarService;
	}

	public Sysvar getSysvar() {
		return sysvar;
	}

	public void setSysvar(Sysvar sysvar) {
		this.sysvar = sysvar;
	}

	public Sysvar getNewSysvar() {
		return newSysvar;
	}

	public void setNewSysvar(Sysvar newSysvar) {
		this.newSysvar = newSysvar;
	}

	public BeanItemContainer<Sysvar> getBeanItemContainerSysvar() {
		return beanItemContainerSysvar;
	}

	public void setBeanItemContainerSysvar(
			BeanItemContainer<Sysvar> beanItemContainerSysvar) {
		this.beanItemContainerSysvar = beanItemContainerSysvar;
	}

	public BeanItemContainer<Sysvar> getBeanItemContainerSysvarSearch() {
		return beanItemContainerSysvarSearch;
	}

	public void setBeanItemContainerSysvarSearch(
			BeanItemContainer<Sysvar> beanItemContainerSysvarSearch) {
		this.beanItemContainerSysvarSearch = beanItemContainerSysvarSearch;
	}

	public BeanFieldGroup<Sysvar> getBinderSysvar() {
		return binderSysvar;
	}

	public void setBinderSysvar(BeanFieldGroup<Sysvar> binderSysvar) {
		this.binderSysvar = binderSysvar;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
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

	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}

	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
	}
	

	
	
}
