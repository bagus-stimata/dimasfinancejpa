package org.blitar.bawasda.simhp.mastertabel.pemeriksaan;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelRuangLingkupPemeriksaan;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelRuangLingkupPemeriksaanJpaService;
import org.blitar.bawasda.simhp.service.TabelRuangLingkupPemeriksaanJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaServiceImpl;
import org.blitar.bawasda.simhp.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class TabelRuangLingkupPemeriksaanModel {
	
	//1. DAO SERVICE
		private TabelRuangLingkupPemeriksaanJpaService ruangLingkupPemeriksaanservice = new TabelRuangLingkupPemeriksaanJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelRuangLingkupPemeriksaan itemHeader = new TabelRuangLingkupPemeriksaan();
		protected TabelRuangLingkupPemeriksaan newItemHeader = new TabelRuangLingkupPemeriksaan();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelRuangLingkupPemeriksaan> beanItemContainerHeader = 
				new BeanItemContainer<TabelRuangLingkupPemeriksaan>(TabelRuangLingkupPemeriksaan.class);
		private BeanItemContainer<TabelRuangLingkupPemeriksaan> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelRuangLingkupPemeriksaan>(TabelRuangLingkupPemeriksaan.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelRuangLingkupPemeriksaan> binderHeader = 
				new BeanFieldGroup<TabelRuangLingkupPemeriksaan>(TabelRuangLingkupPemeriksaan.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelRuangLingkupPemeriksaanModel(){
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
		
		beanItemContainerHeader.addAll(ruangLingkupPemeriksaanservice.findAll());
		
	}
	public TabelRuangLingkupPemeriksaanJpaService getRuangLingkupPemeriksaanservice() {
		return ruangLingkupPemeriksaanservice;
	}
	public void setRuangLingkupPemeriksaanservice(
			TabelRuangLingkupPemeriksaanJpaService ruangLingkupPemeriksaanservice) {
		this.ruangLingkupPemeriksaanservice = ruangLingkupPemeriksaanservice;
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
	public TabelRuangLingkupPemeriksaan getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelRuangLingkupPemeriksaan itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelRuangLingkupPemeriksaan getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelRuangLingkupPemeriksaan newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelRuangLingkupPemeriksaan> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelRuangLingkupPemeriksaan> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelRuangLingkupPemeriksaan> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelRuangLingkupPemeriksaan> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<TabelRuangLingkupPemeriksaan> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(
			BeanFieldGroup<TabelRuangLingkupPemeriksaan> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	

	
	
}
