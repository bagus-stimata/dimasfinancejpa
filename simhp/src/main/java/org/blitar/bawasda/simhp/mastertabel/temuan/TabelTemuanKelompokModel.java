package org.blitar.bawasda.simhp.mastertabel.temuan;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaServiceImpl;
import org.blitar.bawasda.simhp.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class TabelTemuanKelompokModel {
	
	//1. DAO SERVICE
//		private TabelTemuanGrupJpaService temuanGrupService = new TabelTemuanGrupJpaServiceImpl();
		private TabelTemuanKelompokJpaService temuanKelompokservice = new TabelTemuanKelompokJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelTemuanKelompok itemHeader = new TabelTemuanKelompok();
		protected TabelTemuanKelompok newItemHeader = new TabelTemuanKelompok();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelTemuanKelompok> beanItemContainerHeader = 
				new BeanItemContainer<TabelTemuanKelompok>(TabelTemuanKelompok.class);
		private BeanItemContainer<TabelTemuanKelompok> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelTemuanKelompok>(TabelTemuanKelompok.class);
		
//		private BeanItemContainer<TabelTemuanKelompok> beanItemContainerKelompok = 
//				new BeanItemContainer<TabelTemuanKelompok>(TabelTemuanKelompok.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelTemuanKelompok> binderHeader = 
				new BeanFieldGroup<TabelTemuanKelompok>(TabelTemuanKelompok.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelTemuanKelompokModel(){
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
		
		beanItemContainerHeader.addAll(temuanKelompokservice.findAll());
		
	}
	public TabelTemuanKelompokJpaService getTemuanKelompokservice() {
		return temuanKelompokservice;
	}
	public void setTemuanKelompokservice(
			TabelTemuanKelompokJpaService temuanKelompokservice) {
		this.temuanKelompokservice = temuanKelompokservice;
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
	public TabelTemuanKelompok getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelTemuanKelompok itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelTemuanKelompok getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelTemuanKelompok newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelTemuanKelompok> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelTemuanKelompok> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelTemuanKelompok> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelTemuanKelompok> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<TabelTemuanKelompok> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelTemuanKelompok> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	

	
	
}
