package org.blitar.bawasda.simhp.mastertabel.pejabat;

import java.util.List;

import org.blitar.bawasda.simhp.model.SuratTugasTimAnggotaPosisi;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelRuangLingkupPemeriksaan;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SuratTugasTimAnggotaPosisiJpaService;
import org.blitar.bawasda.simhp.service.SuratTugasTimAnggotaPosisiJpaServiceImpl;
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

public class SuratTugasTimAnggotaPosisiModel {
	
	//1. DAO SERVICE
		private SuratTugasTimAnggotaPosisiJpaService suratTugasTimAnggotaPosisiService = new SuratTugasTimAnggotaPosisiJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected SuratTugasTimAnggotaPosisi itemHeader = new SuratTugasTimAnggotaPosisi();
		protected SuratTugasTimAnggotaPosisi newItemHeader = new SuratTugasTimAnggotaPosisi();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<SuratTugasTimAnggotaPosisi> beanItemContainerHeader = 
				new BeanItemContainer<SuratTugasTimAnggotaPosisi>(SuratTugasTimAnggotaPosisi.class);
		private BeanItemContainer<SuratTugasTimAnggotaPosisi> beanItemContainerHeaderSearch = 
				new BeanItemContainer<SuratTugasTimAnggotaPosisi>(SuratTugasTimAnggotaPosisi.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<SuratTugasTimAnggotaPosisi> binderHeader = 
				new BeanFieldGroup<SuratTugasTimAnggotaPosisi>(SuratTugasTimAnggotaPosisi.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public SuratTugasTimAnggotaPosisiModel(){
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
		
		beanItemContainerHeader.addAll(suratTugasTimAnggotaPosisiService.findAll());
		
	}
	public SuratTugasTimAnggotaPosisiJpaService getSuratTugasTimAnggotaPosisiService() {
		return suratTugasTimAnggotaPosisiService;
	}
	public void setSuratTugasTimAnggotaPosisiService(
			SuratTugasTimAnggotaPosisiJpaService suratTugasTimAnggotaPosisiService) {
		this.suratTugasTimAnggotaPosisiService = suratTugasTimAnggotaPosisiService;
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
	public SuratTugasTimAnggotaPosisi getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(SuratTugasTimAnggotaPosisi itemHeader) {
		this.itemHeader = itemHeader;
	}
	public SuratTugasTimAnggotaPosisi getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(SuratTugasTimAnggotaPosisi newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<SuratTugasTimAnggotaPosisi> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<SuratTugasTimAnggotaPosisi> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<SuratTugasTimAnggotaPosisi> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<SuratTugasTimAnggotaPosisi> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<SuratTugasTimAnggotaPosisi> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(
			BeanFieldGroup<SuratTugasTimAnggotaPosisi> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
}
