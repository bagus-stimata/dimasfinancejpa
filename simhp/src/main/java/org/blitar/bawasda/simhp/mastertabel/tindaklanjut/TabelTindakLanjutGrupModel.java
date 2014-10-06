package org.blitar.bawasda.simhp.mastertabel.tindaklanjut;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.model.TabelTindakLanjutGrup;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTindakLanjutGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelTindakLanjutGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTindakLanjutJpaService;
import org.blitar.bawasda.simhp.service.TabelTindakLanjutJpaServiceImpl;
import org.blitar.bawasda.simhp.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class TabelTindakLanjutGrupModel {
	
	//1. DAO SERVICE
		private TabelTindakLanjutGrupJpaService tindakLanjutGrupservice = new TabelTindakLanjutGrupJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelTindakLanjutGrup itemHeader = new TabelTindakLanjutGrup();
		protected TabelTindakLanjutGrup newItemHeader = new TabelTindakLanjutGrup();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelTindakLanjutGrup> beanItemContainerHeader = 
				new BeanItemContainer<TabelTindakLanjutGrup>(TabelTindakLanjutGrup.class);
		private BeanItemContainer<TabelTindakLanjutGrup> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelTindakLanjutGrup>(TabelTindakLanjutGrup.class);
		
//		private BeanItemContainer<TabelTemuanKelompok> beanItemContainerKelompok = 
//				new BeanItemContainer<TabelTemuanKelompok>(TabelTemuanKelompok.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelTindakLanjutGrup> binderHeader = 
				new BeanFieldGroup<TabelTindakLanjutGrup>(TabelTindakLanjutGrup.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelTindakLanjutGrupModel(){
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
		
		beanItemContainerHeader.addAll(tindakLanjutGrupservice.findAll());
		
	}
	public TabelTindakLanjutGrupJpaService getTindakLanjutGrupservice() {
		return tindakLanjutGrupservice;
	}
	public void setTindakLanjutGrupservice(
			TabelTindakLanjutGrupJpaService tindakLanjutGrupservice) {
		this.tindakLanjutGrupservice = tindakLanjutGrupservice;
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
	public TabelTindakLanjutGrup getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelTindakLanjutGrup itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelTindakLanjutGrup getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelTindakLanjutGrup newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelTindakLanjutGrup> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelTindakLanjutGrup> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelTindakLanjutGrup> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelTindakLanjutGrup> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<TabelTindakLanjutGrup> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelTindakLanjutGrup> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
}
