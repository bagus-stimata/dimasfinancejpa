package org.blitar.bawasda.simhp.mastertabel.lembaga;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelLembagaTkII;
import org.blitar.bawasda.simhp.model.TabelLembagaTkIII;
import org.blitar.bawasda.simhp.model.TabelRekomendasi;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelLembagaTkIIIJpaService;
import org.blitar.bawasda.simhp.service.TabelLembagaTkIIIJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelLembagaTkIIJpaService;
import org.blitar.bawasda.simhp.service.TabelLembagaTkIIJpaServiceImpl;
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

public class TabelLembagaTk3Model {
	
	//1. DAO SERVICE
		private TabelLembagaTkIIIJpaService lembagaTk3Service = new TabelLembagaTkIIIJpaServiceImpl();
		private TabelLembagaTkIIJpaService lembagaTk2service = new TabelLembagaTkIIJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelLembagaTkIII itemHeader = new TabelLembagaTkIII();
		protected TabelLembagaTkIII newItemHeader = new TabelLembagaTkIII();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelLembagaTkIII> beanItemContainerHeader = 
				new BeanItemContainer<TabelLembagaTkIII>(TabelLembagaTkIII.class);
		private BeanItemContainer<TabelLembagaTkIII> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelLembagaTkIII>(TabelLembagaTkIII.class);
		
		private BeanItemContainer<TabelLembagaTkII> beanItemContainerGrup = 
				new BeanItemContainer<TabelLembagaTkII>(TabelLembagaTkII.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelLembagaTkIII> binderHeader = 
				new BeanFieldGroup<TabelLembagaTkIII>(TabelLembagaTkIII.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelLembagaTk3Model(){
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
		
		beanItemContainerHeader.addAll(lembagaTk3Service.findAll());
		//CUMA YANG AKTIF SAJA
		beanItemContainerGrup.addAll(lembagaTk2service.findAllHasChild());
		
	}
	public TabelLembagaTkIIIJpaService getLembagaTk3Service() {
		return lembagaTk3Service;
	}
	public void setLembagaTk3Service(TabelLembagaTkIIIJpaService lembagaTk3Service) {
		this.lembagaTk3Service = lembagaTk3Service;
	}
	public TabelLembagaTkIIJpaService getLembagaTk2service() {
		return lembagaTk2service;
	}
	public void setLembagaTk2service(TabelLembagaTkIIJpaService lembagaTk2service) {
		this.lembagaTk2service = lembagaTk2service;
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
	public TabelLembagaTkIII getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelLembagaTkIII itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelLembagaTkIII getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelLembagaTkIII newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelLembagaTkIII> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelLembagaTkIII> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelLembagaTkIII> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelLembagaTkIII> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<TabelLembagaTkII> getBeanItemContainerGrup() {
		return beanItemContainerGrup;
	}
	public void setBeanItemContainerGrup(
			BeanItemContainer<TabelLembagaTkII> beanItemContainerGrup) {
		this.beanItemContainerGrup = beanItemContainerGrup;
	}
	public BeanFieldGroup<TabelLembagaTkIII> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelLembagaTkIII> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}


	
	
}
