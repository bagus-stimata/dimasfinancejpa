package org.blitar.bawasda.simhp.mastertabel.rekomendasi;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelRekomendasi;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelRekomendasiJpaService;
import org.blitar.bawasda.simhp.service.TabelRekomendasiJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaServiceImpl;
import org.blitar.bawasda.simhp.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class TabelRekomendasiModel {
	
	//1. DAO SERVICE
		private TabelRekomendasiJpaService rekomendasiService = new TabelRekomendasiJpaServiceImpl();
		private TabelRekomendasiGrupJpaService rekomendasiGrupservice = new TabelRekomendasiGrupJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelRekomendasi itemHeader = new TabelRekomendasi();
		protected TabelRekomendasi newItemHeader = new TabelRekomendasi();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelRekomendasi> beanItemContainerHeader = 
				new BeanItemContainer<TabelRekomendasi>(TabelRekomendasi.class);
		private BeanItemContainer<TabelRekomendasi> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelRekomendasi>(TabelRekomendasi.class);
		
		private BeanItemContainer<TabelRekomendasiGrup> beanItemContainerGrup = 
				new BeanItemContainer<TabelRekomendasiGrup>(TabelRekomendasiGrup.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelRekomendasi> binderHeader = 
				new BeanFieldGroup<TabelRekomendasi>(TabelRekomendasi.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelRekomendasiModel(){
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
		
		beanItemContainerHeader.addAll(rekomendasiService.findAll());
		//CUMA YANG AKTIF SAJA
		beanItemContainerGrup.addAll(rekomendasiGrupservice.findAllActive());
		
	}
	public TabelRekomendasiJpaService getRekomendasiService() {
		return rekomendasiService;
	}
	public void setRekomendasiService(TabelRekomendasiJpaService rekomendasiService) {
		this.rekomendasiService = rekomendasiService;
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
	public TabelRekomendasi getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelRekomendasi itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelRekomendasi getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelRekomendasi newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelRekomendasi> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelRekomendasi> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelRekomendasi> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelRekomendasi> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<TabelRekomendasiGrup> getBeanItemContainerGrup() {
		return beanItemContainerGrup;
	}
	public void setBeanItemContainerGrup(
			BeanItemContainer<TabelRekomendasiGrup> beanItemContainerGrup) {
		this.beanItemContainerGrup = beanItemContainerGrup;
	}
	public BeanFieldGroup<TabelRekomendasi> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelRekomendasi> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	

	
	
}
