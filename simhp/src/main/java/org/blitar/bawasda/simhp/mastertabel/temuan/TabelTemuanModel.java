package org.blitar.bawasda.simhp.mastertabel.temuan;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelTemuan;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaServiceImpl;
import org.blitar.bawasda.simhp.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class TabelTemuanModel {
	
	//1. DAO SERVICE
		private TabelTemuanJpaService temuanService = new TabelTemuanJpaServiceImpl();
		private TabelTemuanGrupJpaService temuanGrupservice = new TabelTemuanGrupJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelTemuan itemHeader = new TabelTemuan();
		protected TabelTemuan newItemHeader = new TabelTemuan();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelTemuan> beanItemContainerHeader = 
				new BeanItemContainer<TabelTemuan>(TabelTemuan.class);
		private BeanItemContainer<TabelTemuan> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelTemuan>(TabelTemuan.class);
		
		private BeanItemContainer<TabelTemuanGrup> beanItemContainerGrup = 
				new BeanItemContainer<TabelTemuanGrup>(TabelTemuanGrup.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelTemuan> binderHeader = 
				new BeanFieldGroup<TabelTemuan>(TabelTemuan.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelTemuanModel(){
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
		beanItemContainerGrup.removeAllContainerFilters();
		beanItemContainerGrup.removeAllItems();
		
		beanItemContainerHeader.addAll(temuanService.findAll());
		//CUMA YANG AKTIF SAJA
		beanItemContainerGrup.addAll(temuanGrupservice.findAllActive());
		
	}
	public TabelTemuanJpaService getTemuanService() {
		return temuanService;
	}
	public void setTemuanService(TabelTemuanJpaService temuanService) {
		this.temuanService = temuanService;
	}
	public TabelTemuanGrupJpaService getTemuanGrupservice() {
		return temuanGrupservice;
	}
	public void setTemuanGrupservice(TabelTemuanGrupJpaService temuanGrupservice) {
		this.temuanGrupservice = temuanGrupservice;
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
	public TabelTemuan getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelTemuan itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelTemuan getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelTemuan newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelTemuan> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelTemuan> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelTemuan> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelTemuan> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<TabelTemuanGrup> getBeanItemContainerGrup() {
		return beanItemContainerGrup;
	}
	public void setBeanItemContainerGrup(
			BeanItemContainer<TabelTemuanGrup> beanItemContainerGrup) {
		this.beanItemContainerGrup = beanItemContainerGrup;
	}
	public BeanFieldGroup<TabelTemuan> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelTemuan> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
}
