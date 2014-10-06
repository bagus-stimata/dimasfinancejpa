package org.blitar.bawasda.simhp.mastertabel.lembaga;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelLembagaTkI;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelLembagaTkIJpaService;
import org.blitar.bawasda.simhp.service.TabelLembagaTkIJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaServiceImpl;
import org.blitar.bawasda.simhp.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class TabelLembagaTk1Model {
	
	//1. DAO SERVICE
//		private TabelTemuanGrupJpaService temuanGrupService = new TabelTemuanGrupJpaServiceImpl();
		private TabelLembagaTkIJpaService lembagaTk1service = new TabelLembagaTkIJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelLembagaTkI itemHeader = new TabelLembagaTkI();
		protected TabelLembagaTkI newItemHeader = new TabelLembagaTkI();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelLembagaTkI> beanItemContainerHeader = 
				new BeanItemContainer<TabelLembagaTkI>(TabelLembagaTkI.class);
		private BeanItemContainer<TabelLembagaTkI> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelLembagaTkI>(TabelLembagaTkI.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelLembagaTkI> binderHeader = 
				new BeanFieldGroup<TabelLembagaTkI>(TabelLembagaTkI.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelLembagaTk1Model(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(lembagaTk1service.findAll());
		
	}
	public TabelLembagaTkIJpaService getLembagaTk1service() {
		return lembagaTk1service;
	}
	public void setLembagaTk1service(TabelLembagaTkIJpaService lembagaTk1service) {
		this.lembagaTk1service = lembagaTk1service;
	}
	public SysvarJpaService getSysvarService() {
		return sysvarService;
	}
	public void setSysvarService(SysvarJpaService sysvarService) {
		this.sysvarService = sysvarService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public TabelLembagaTkI getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelLembagaTkI itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelLembagaTkI getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelLembagaTkI newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelLembagaTkI> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelLembagaTkI> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelLembagaTkI> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelLembagaTkI> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<TabelLembagaTkI> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelLembagaTkI> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	

	
	
}
