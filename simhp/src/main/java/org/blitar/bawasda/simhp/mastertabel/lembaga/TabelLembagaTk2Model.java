package org.blitar.bawasda.simhp.mastertabel.lembaga;

import java.util.List;

import org.blitar.bawasda.simhp.model.TabelLembagaTkI;
import org.blitar.bawasda.simhp.model.TabelLembagaTkII;
import org.blitar.bawasda.simhp.model.TabelRekomendasi;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.SysvarJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelLembagaTkIIJpaService;
import org.blitar.bawasda.simhp.service.TabelLembagaTkIIJpaServiceImpl;
import org.blitar.bawasda.simhp.service.TabelLembagaTkIJpaService;
import org.blitar.bawasda.simhp.service.TabelLembagaTkIJpaServiceImpl;
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

public class TabelLembagaTk2Model {
	
	//1. DAO SERVICE
		private TabelLembagaTkIIJpaService lembagaTk2Service = new TabelLembagaTkIIJpaServiceImpl();
		private TabelLembagaTkIJpaService lembagaTk1service = new TabelLembagaTkIJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected TabelLembagaTkII itemHeader = new TabelLembagaTkII();
		protected TabelLembagaTkII newItemHeader = new TabelLembagaTkII();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<TabelLembagaTkII> beanItemContainerHeader = 
				new BeanItemContainer<TabelLembagaTkII>(TabelLembagaTkII.class);
		private BeanItemContainer<TabelLembagaTkII> beanItemContainerHeaderSearch = 
				new BeanItemContainer<TabelLembagaTkII>(TabelLembagaTkII.class);
		
		private BeanItemContainer<TabelLembagaTkI> beanItemContainerGrup = 
				new BeanItemContainer<TabelLembagaTkI>(TabelLembagaTkI.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<TabelLembagaTkII> binderHeader = 
				new BeanFieldGroup<TabelLembagaTkII>(TabelLembagaTkII.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public TabelLembagaTk2Model(){
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
		
		beanItemContainerHeader.addAll(lembagaTk2Service.findAll());
		//CUMA YANG AKTIF SAJA
		beanItemContainerGrup.addAll(lembagaTk1service.findAllHasChild());
		
	}
	public TabelLembagaTkIIJpaService getLembagaTk2Service() {
		return lembagaTk2Service;
	}
	public void setLembagaTk2Service(TabelLembagaTkIIJpaService lembagaTk2Service) {
		this.lembagaTk2Service = lembagaTk2Service;
	}
	public TabelLembagaTkIJpaService getLembagaTk1service() {
		return lembagaTk1service;
	}
	public void setLembagaTk1service(TabelLembagaTkIJpaService lembagaTk1service) {
		this.lembagaTk1service = lembagaTk1service;
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
	public TabelLembagaTkII getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(TabelLembagaTkII itemHeader) {
		this.itemHeader = itemHeader;
	}
	public TabelLembagaTkII getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(TabelLembagaTkII newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<TabelLembagaTkII> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<TabelLembagaTkII> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<TabelLembagaTkII> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<TabelLembagaTkII> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<TabelLembagaTkI> getBeanItemContainerGrup() {
		return beanItemContainerGrup;
	}
	public void setBeanItemContainerGrup(
			BeanItemContainer<TabelLembagaTkI> beanItemContainerGrup) {
		this.beanItemContainerGrup = beanItemContainerGrup;
	}
	public BeanFieldGroup<TabelLembagaTkII> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<TabelLembagaTkII> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	

	
	
}
