package org.dimas.finance.systemsetting;

import org.dimas.finance.jpa.dao.ModulJpaService;
import org.dimas.finance.jpa.dao.ModulJpaServiceImpl;
import org.dimas.finance.jpa.dao.ModulTempDetailJpaService;
import org.dimas.finance.jpa.dao.ModulTempDetailJpaServiceImpl;
import org.dimas.finance.jpa.dao.ModulTempHeaderJpaService;
import org.dimas.finance.jpa.dao.ModulTempHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.jpa.dao.UserJpaService;
import org.dimas.finance.jpa.dao.UserJpaServiceImpl;
import org.dimas.finance.model.Modul;
import org.dimas.finance.model.ModulTempDetail;
import org.dimas.finance.model.ModulTempHeader;
import org.dimas.finance.model.User;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class ModulTemplateModel {
	//1. DAO SERVICE
	private ModulTempHeaderJpaService modulTempHeaderService;
	private ModulTempDetailJpaService modulTempDetailService;

	private SysvarJpaService sysvarService;
	
	private ModulJpaService modulService;
	
//2. ENTITY
	protected ModulTempHeader modulTempHeader;
	protected ModulTempHeader newModulTempHeader;
	
//	protected MenuAccessTemp menuAccessTemp;
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	private BeanItemContainer<ModulTempHeader> beanItemContainerModulTempHeader;
	private BeanItemContainer<ModulTempHeader> beanItemContainerModulTempHeaderSearch;

	private BeanItemContainer<ModulTempDetail> beanItemContainerModulTempDetail;
	
	private BeanItemContainer<Modul> beanItemContainerModul;
	
//	private BeanItemContainer<Bank> beanItemContainerBank;
//5. Binder (BeanFieldGroup)
	private BeanFieldGroup<ModulTempHeader> binderModulTempHeader;
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllInvoice;
	
	public ModulTemplateModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		modulTempHeaderService = new ModulTempHeaderJpaServiceImpl();
		modulTempDetailService = new ModulTempDetailJpaServiceImpl();
		
		sysvarService = new SysvarJpaServiceImpl();
		
		modulService = new ModulJpaServiceImpl();
		
		modulTempHeader = new ModulTempHeader();
		
		beanItemContainerModulTempHeader = new BeanItemContainer<ModulTempHeader>(ModulTempHeader.class);
		beanItemContainerModulTempHeaderSearch = new BeanItemContainer<ModulTempHeader>(ModulTempHeader.class);
		beanItemContainerModulTempDetail= new BeanItemContainer<ModulTempDetail>(ModulTempDetail.class);
		beanItemContainerModul = new BeanItemContainer<Modul>(Modul.class);
		
		binderModulTempHeader = new BeanFieldGroup<ModulTempHeader>(ModulTempHeader.class);
		
	}
	public void initVariableData(){
		reload();
		
		
	}
	public void reload(){
		beanItemContainerModulTempHeader.removeAllContainerFilters();
		beanItemContainerModulTempDetail.removeAllContainerFilters();
		beanItemContainerModul.removeAllContainerFilters();
		beanItemContainerModulTempHeader.removeAllItems();
		beanItemContainerModulTempDetail.removeAllItems();
		beanItemContainerModul.removeAllItems();
		
		//DEMI ALASAN SPEED
		beanItemContainerModulTempHeader.addAll(modulTempHeaderService.findAll());
		beanItemContainerModul.addAll(modulService.findAll());
		
	}

	public ModulTempHeaderJpaService getModulTempHeaderService() {
		return modulTempHeaderService;
	}

	public void setModulTempHeaderService(
			ModulTempHeaderJpaService modulTempHeaderService) {
		this.modulTempHeaderService = modulTempHeaderService;
	}

	public ModulTempDetailJpaService getModulTempDetailService() {
		return modulTempDetailService;
	}

	public void setModulTempDetailService(
			ModulTempDetailJpaService modulTempDetailService) {
		this.modulTempDetailService = modulTempDetailService;
	}

	public SysvarJpaService getSysvarService() {
		return sysvarService;
	}

	public void setSysvarService(SysvarJpaService sysvarService) {
		this.sysvarService = sysvarService;
	}

	public ModulJpaService getModulService() {
		return modulService;
	}

	public void setModulService(ModulJpaService modulService) {
		this.modulService = modulService;
	}

	public ModulTempHeader getModulTempHeader() {
		return modulTempHeader;
	}

	public void setModulTempHeader(ModulTempHeader modulTempHeader) {
		this.modulTempHeader = modulTempHeader;
	}

	public ModulTempHeader getNewModulTempHeader() {
		return newModulTempHeader;
	}

	public void setNewModulTempHeader(ModulTempHeader newModulTempHeader) {
		this.newModulTempHeader = newModulTempHeader;
	}

	public BeanItemContainer<ModulTempHeader> getBeanItemContainerModulTempHeader() {
		return beanItemContainerModulTempHeader;
	}

	public void setBeanItemContainerModulTempHeader(
			BeanItemContainer<ModulTempHeader> beanItemContainerModulTempHeader) {
		this.beanItemContainerModulTempHeader = beanItemContainerModulTempHeader;
	}

	public BeanItemContainer<ModulTempHeader> getBeanItemContainerModulTempHeaderSearch() {
		return beanItemContainerModulTempHeaderSearch;
	}

	public void setBeanItemContainerModulTempHeaderSearch(
			BeanItemContainer<ModulTempHeader> beanItemContainerModulTempHeaderSearch) {
		this.beanItemContainerModulTempHeaderSearch = beanItemContainerModulTempHeaderSearch;
	}

	public BeanItemContainer<ModulTempDetail> getBeanItemContainerModulTempDetail() {
		return beanItemContainerModulTempDetail;
	}

	public void setBeanItemContainerModulTempDetail(
			BeanItemContainer<ModulTempDetail> beanItemContainerModulTempDetail) {
		this.beanItemContainerModulTempDetail = beanItemContainerModulTempDetail;
	}

	public BeanItemContainer<Modul> getBeanItemContainerModul() {
		return beanItemContainerModul;
	}

	public void setBeanItemContainerModul(
			BeanItemContainer<Modul> beanItemContainerModul) {
		this.beanItemContainerModul = beanItemContainerModul;
	}

	public BeanFieldGroup<ModulTempHeader> getBinderModulTempHeader() {
		return binderModulTempHeader;
	}

	public void setBinderModulTempHeader(
			BeanFieldGroup<ModulTempHeader> binderModulTempHeader) {
		this.binderModulTempHeader = binderModulTempHeader;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	public boolean isSelectAllInvoice() {
		return selectAllInvoice;
	}

	public void setSelectAllInvoice(boolean selectAllInvoice) {
		this.selectAllInvoice = selectAllInvoice;
	}


}
