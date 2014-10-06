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

public class TabelTemuanGrupModel {
	
	//1. DAO SERVICE
		private TabelTemuanGrupJpaService temuanGrupService = new TabelTemuanGrupJpaServiceImpl();
		private TabelTemuanKelompokJpaService temuanKelompokservice = new TabelTemuanKelompokJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelTemuanGrup itemHeader = new TabelTemuanGrup();
		protected TabelTemuanGrup newItemHeader = new TabelTemuanGrup();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelTemuanGrup> beanItemContainerHeader = 
				new BeanItemContainer<TabelTemuanGrup>(TabelTemuanGrup.class);
		private BeanItemContainer<TabelTemuanGrup> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelTemuanGrup>(TabelTemuanGrup.class);
		
		private BeanItemContainer<TabelTemuanKelompok> beanItemContainerKelompok = 
				new BeanItemContainer<TabelTemuanKelompok>(TabelTemuanKelompok.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelTemuanGrup> binderHeader = 
				new BeanFieldGroup<TabelTemuanGrup>(TabelTemuanGrup.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelTemuanGrupModel(){
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
		beanItemContainerKelompok.removeAllContainerFilters();
		beanItemContainerKelompok.removeAllItems();
		
		beanItemContainerHeader.addAll(temuanGrupService.findAll());
		//CUMA YANG AKTIF SAJA
		beanItemContainerKelompok.addAll(temuanKelompokservice.findAllActive());
		
	}
	
	
	public TabelTemuanGrupJpaService getTemuanGrupService() {
		return temuanGrupService;
	}
	public void setTemuanGrupService(TabelTemuanGrupJpaService temuanGrupService) {
		this.temuanGrupService = temuanGrupService;
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
	public TabelTemuanGrup getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelTemuanGrup itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelTemuanGrup getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelTemuanGrup newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelTemuanGrup> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelTemuanGrup> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelTemuanGrup> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelTemuanGrup> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<TabelTemuanKelompok> getBeanItemContainerKelompok() {
		return beanItemContainerKelompok;
	}
	public void setBeanItemContainerKelompok(
			BeanItemContainer<TabelTemuanKelompok> beanItemContainerKelompok) {
		this.beanItemContainerKelompok = beanItemContainerKelompok;
	}
	public BeanFieldGroup<TabelTemuanGrup> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelTemuanGrup> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	

	
	
}
