package org.blitar.bawasda.simhp.mastertabel.pemeriksaan;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelJenisPemeriksaan;
import org.blitar.bawasda.simhp.model.TabelJenisPemeriksaanGrup;
import org.blitar.bawasda.simhp.model.TabelRekomendasi;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelJenisPemeriksaanGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelJenisPemeriksaanGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelJenisPemeriksaanJpaService;
import org.blitar.bawasda.simhp.service.TabelJenisPemeriksaanJpaServiceImpl;
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

public class TabelPemeriksaanModel {
	
	//1. DAO SERVICE
		private TabelJenisPemeriksaanJpaService jenisPemeriksaanService = new TabelJenisPemeriksaanJpaServiceImpl();
		private TabelJenisPemeriksaanGrupJpaService jenisPemeriksaanGrupservice = new TabelJenisPemeriksaanGrupJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelJenisPemeriksaan itemHeader = new TabelJenisPemeriksaan();
		protected TabelJenisPemeriksaan newItemHeader = new TabelJenisPemeriksaan();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelJenisPemeriksaan> beanItemContainerHeader = 
				new BeanItemContainer<TabelJenisPemeriksaan>(TabelJenisPemeriksaan.class);
		private BeanItemContainer<TabelJenisPemeriksaan> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelJenisPemeriksaan>(TabelJenisPemeriksaan.class);
		
		private BeanItemContainer<TabelJenisPemeriksaanGrup> beanItemContainerGrup = 
				new BeanItemContainer<TabelJenisPemeriksaanGrup>(TabelJenisPemeriksaanGrup.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelJenisPemeriksaan> binderHeader = 
				new BeanFieldGroup<TabelJenisPemeriksaan>(TabelJenisPemeriksaan.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelPemeriksaanModel(){
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
		
		beanItemContainerHeader.addAll(jenisPemeriksaanService.findAll());
		//CUMA YANG AKTIF SAJA
		beanItemContainerGrup.addAll(jenisPemeriksaanGrupservice.findAllActive());
		
	}
	public TabelJenisPemeriksaanJpaService getJenisPemeriksaanService() {
		return jenisPemeriksaanService;
	}
	public void setJenisPemeriksaanService(
			TabelJenisPemeriksaanJpaService jenisPemeriksaanService) {
		this.jenisPemeriksaanService = jenisPemeriksaanService;
	}
	public TabelJenisPemeriksaanGrupJpaService getJenisPemeriksaanGrupservice() {
		return jenisPemeriksaanGrupservice;
	}
	public void setJenisPemeriksaanGrupservice(
			TabelJenisPemeriksaanGrupJpaService jenisPemeriksaanGrupservice) {
		this.jenisPemeriksaanGrupservice = jenisPemeriksaanGrupservice;
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
	public TabelJenisPemeriksaan getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelJenisPemeriksaan itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelJenisPemeriksaan getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelJenisPemeriksaan newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelJenisPemeriksaan> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelJenisPemeriksaan> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelJenisPemeriksaan> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelJenisPemeriksaan> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<TabelJenisPemeriksaanGrup> getBeanItemContainerGrup() {
		return beanItemContainerGrup;
	}
	public void setBeanItemContainerGrup(
			BeanItemContainer<TabelJenisPemeriksaanGrup> beanItemContainerGrup) {
		this.beanItemContainerGrup = beanItemContainerGrup;
	}
	public BeanFieldGroup<TabelJenisPemeriksaan> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelJenisPemeriksaan> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	
	
}
