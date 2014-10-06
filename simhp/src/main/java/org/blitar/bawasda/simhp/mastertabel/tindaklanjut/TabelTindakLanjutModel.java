package org.blitar.bawasda.simhp.mastertabel.tindaklanjut;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelRekomendasi;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.model.TabelTindakLanjut;
import org.blitar.bawasda.simhp.model.TabelTindakLanjutGrup;
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
import org.blitar.bawasda.simhp.service.TabelTindakLanjutGrupJpaService;
import org.blitar.bawasda.simhp.service.TabelTindakLanjutGrupJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelTindakLanjutJpaService;
import org.blitar.bawasda.simhp.service.TabelTindakLanjutJpaServiceImpl;
import org.blitar.bawasda.simhp.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class TabelTindakLanjutModel {
	
	//1. DAO SERVICE
		private TabelTindakLanjutJpaService tindakLanjutService = new TabelTindakLanjutJpaServiceImpl();
		private TabelTindakLanjutGrupJpaService tindakLanjutGrupservice = new TabelTindakLanjutGrupJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelTindakLanjut itemHeader = new TabelTindakLanjut();
		protected TabelTindakLanjut newItemHeader = new TabelTindakLanjut();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelTindakLanjut> beanItemContainerHeader = 
				new BeanItemContainer<TabelTindakLanjut>(TabelTindakLanjut.class);
		private BeanItemContainer<TabelTindakLanjut> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelTindakLanjut>(TabelTindakLanjut.class);
		
		private BeanItemContainer<TabelTindakLanjutGrup> beanItemContainerGrup = 
				new BeanItemContainer<TabelTindakLanjutGrup>(TabelTindakLanjutGrup.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelTindakLanjut> binderHeader = 
				new BeanFieldGroup<TabelTindakLanjut>(TabelTindakLanjut.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelTindakLanjutModel(){
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
		
		beanItemContainerHeader.addAll(tindakLanjutService.findAll());
		//CUMA YANG AKTIF SAJA
		beanItemContainerGrup.addAll(tindakLanjutGrupservice.findAllActive());
		
	}
	public TabelTindakLanjutJpaService getTindakLanjutService() {
		return tindakLanjutService;
	}
	public void setTindakLanjutService(
			TabelTindakLanjutJpaService tindakLanjutService) {
		this.tindakLanjutService = tindakLanjutService;
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
	public TabelTindakLanjut getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelTindakLanjut itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelTindakLanjut getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelTindakLanjut newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelTindakLanjut> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelTindakLanjut> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelTindakLanjut> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelTindakLanjut> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<TabelTindakLanjutGrup> getBeanItemContainerGrup() {
		return beanItemContainerGrup;
	}
	public void setBeanItemContainerGrup(
			BeanItemContainer<TabelTindakLanjutGrup> beanItemContainerGrup) {
		this.beanItemContainerGrup = beanItemContainerGrup;
	}
	public BeanFieldGroup<TabelTindakLanjut> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelTindakLanjut> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
}
