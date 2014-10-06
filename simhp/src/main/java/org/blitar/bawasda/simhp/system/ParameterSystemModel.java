package org.blitar.bawasda.simhp.system;


import org.blitar.bawasda.simhp.model.Sysvar;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.util.SysvarHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class ParameterSystemModel {
//1. DAO SERVICE
	private SysvarJpaService sysvarService;
//2. ENTITY
	protected Sysvar sysvar;
	protected Sysvar newSysvar;
	
	private SysvarHelper sysvarHelper =  new SysvarHelper();
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	private BeanItemContainer<Sysvar> beanItemContainerSysvar;
	private BeanItemContainer<Sysvar> beanItemContainerSysvarSearch;
	
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
		
		sysvar = new Sysvar();
		
		beanItemContainerSysvar = new BeanItemContainer<Sysvar>(Sysvar.class);
		beanItemContainerSysvarSearch = new BeanItemContainer<Sysvar>(Sysvar.class);
		
		binderSysvar = new BeanFieldGroup<Sysvar>(Sysvar.class);
		
	}
	public void initVariableData(){
		reload();
		
	}
	public void reload(){
		beanItemContainerSysvar.removeAllContainerFilters();
		beanItemContainerSysvar.removeAllItems();		
		
		beanItemContainerSysvar.addAll(sysvarService.findAll());
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

	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}

	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
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

	
	
}
