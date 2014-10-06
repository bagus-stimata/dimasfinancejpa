package org.blitar.bawasda.simhp.mastertabel.rekomendasi;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaServiceImpl;
import org.blitar.bawasda.simhp.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class TabelRekomendasiGrupModel {
	
	//1. DAO SERVICE
//		private TabelTemuanGrupJpaService temuanGrupService = new TabelTemuanGrupJpaServiceImpl();
		private TabelRekomendasiGrupJpaService rekomendasiGrupservice = new TabelRekomendasiGrupJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelRekomendasiGrup itemHeader = new TabelRekomendasiGrup();
		protected TabelRekomendasiGrup newItemHeader = new TabelRekomendasiGrup();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelRekomendasiGrup> beanItemContainerHeader = 
				new BeanItemContainer<TabelRekomendasiGrup>(TabelRekomendasiGrup.class);
		private BeanItemContainer<TabelRekomendasiGrup> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelRekomendasiGrup>(TabelRekomendasiGrup.class);
		
//		private BeanItemContainer<TabelTemuanKelompok> beanItemContainerKelompok = 
//				new BeanItemContainer<TabelTemuanKelompok>(TabelTemuanKelompok.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelRekomendasiGrup> binderHeader = 
				new BeanFieldGroup<TabelRekomendasiGrup>(TabelRekomendasiGrup.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelRekomendasiGrupModel(){
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
		
		beanItemContainerHeader.addAll(rekomendasiGrupservice.findAll());
		
	}
	public TabelRekomendasiGrupJpaService getRekomendasiGrupservice() {
		return rekomendasiGrupservice;
	}
	public void setRekomendasiGrupservice(
			TabelRekomendasiGrupJpaService rekomendasiGrupservice) {
		this.rekomendasiGrupservice = rekomendasiGrupservice;
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
	public TabelRekomendasiGrup getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelRekomendasiGrup itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelRekomendasiGrup getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelRekomendasiGrup newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelRekomendasiGrup> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelRekomendasiGrup> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelRekomendasiGrup> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelRekomendasiGrup> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<TabelRekomendasiGrup> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelRekomendasiGrup> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	

	
	
}
