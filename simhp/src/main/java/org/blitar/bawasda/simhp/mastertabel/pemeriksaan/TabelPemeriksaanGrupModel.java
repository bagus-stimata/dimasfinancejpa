package org.blitar.bawasda.simhp.mastertabel.pemeriksaan;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelJenisPemeriksaanGrup;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelJenisPemeriksaanGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelJenisPemeriksaanGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelRekomendasiGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaService;
import org.blitar.bawasda.simhp.service.TabelTemuanKelompokJpaServiceImpl;
import org.blitar.bawasda.simhp.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class TabelPemeriksaanGrupModel {
	
	//1. DAO SERVICE
//		private TabelTemuanGrupJpaService temuanGrupService = new TabelTemuanGrupJpaServiceImpl();
		private TabelJenisPemeriksaanGrupJpaService jenisPemeriksaanGrupservice = new TabelJenisPemeriksaanGrupJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelJenisPemeriksaanGrup itemHeader = new TabelJenisPemeriksaanGrup();
		protected TabelJenisPemeriksaanGrup newItemHeader = new TabelJenisPemeriksaanGrup();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelJenisPemeriksaanGrup> beanItemContainerHeader = 
				new BeanItemContainer<TabelJenisPemeriksaanGrup>(TabelJenisPemeriksaanGrup.class);
		private BeanItemContainer<TabelJenisPemeriksaanGrup> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelJenisPemeriksaanGrup>(TabelJenisPemeriksaanGrup.class);
		
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelJenisPemeriksaanGrup> binderHeader = 
				new BeanFieldGroup<TabelJenisPemeriksaanGrup>(TabelJenisPemeriksaanGrup.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelPemeriksaanGrupModel(){
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
		
		beanItemContainerHeader.addAll(jenisPemeriksaanGrupservice.findAll());
		
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
	public TabelJenisPemeriksaanGrup getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelJenisPemeriksaanGrup itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelJenisPemeriksaanGrup getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelJenisPemeriksaanGrup newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelJenisPemeriksaanGrup> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelJenisPemeriksaanGrup> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelJenisPemeriksaanGrup> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelJenisPemeriksaanGrup> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<TabelJenisPemeriksaanGrup> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(
			BeanFieldGroup<TabelJenisPemeriksaanGrup> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
}
