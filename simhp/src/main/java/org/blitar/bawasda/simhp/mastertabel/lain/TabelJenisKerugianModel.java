package org.blitar.bawasda.simhp.mastertabel.lain;

import java.util.List;

import org.blitar.bawasda.simhp.model.SuratTugasTimAnggotaPosisi;
import org.blitar.bawasda.simhp.model.TabelJenisKerugian;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelRuangLingkupPemeriksaan;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SuratTugasTimAnggotaPosisiJpaService;
import org.blitar.bawasda.simhp.service.SuratTugasTimAnggotaPosisiJpaServiceImpl;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelJenisKerugianJpaService;
import org.blitar.bawasda.simhp.service.TabelJenisKerugianJpaServiceImpl;
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

public class TabelJenisKerugianModel {
	
	//1. DAO SERVICE
		private TabelJenisKerugianJpaService jenisKerugianService = new TabelJenisKerugianJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelJenisKerugian itemHeader = new TabelJenisKerugian();
		protected TabelJenisKerugian newItemHeader = new TabelJenisKerugian();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelJenisKerugian> beanItemContainerHeader = 
				new BeanItemContainer<TabelJenisKerugian>(TabelJenisKerugian.class);
		private BeanItemContainer<TabelJenisKerugian> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelJenisKerugian>(TabelJenisKerugian.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelJenisKerugian> binderHeader = 
				new BeanFieldGroup<TabelJenisKerugian>(TabelJenisKerugian.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelJenisKerugianModel(){
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
		
		beanItemContainerHeader.addAll(jenisKerugianService.findAll());
		
	}
	public TabelJenisKerugianJpaService getJenisKerugianService() {
		return jenisKerugianService;
	}
	public void setJenisKerugianService(
			TabelJenisKerugianJpaService jenisKerugianService) {
		this.jenisKerugianService = jenisKerugianService;
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
	public TabelJenisKerugian getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelJenisKerugian itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelJenisKerugian getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelJenisKerugian newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelJenisKerugian> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelJenisKerugian> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelJenisKerugian> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelJenisKerugian> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<TabelJenisKerugian> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelJenisKerugian> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
}
