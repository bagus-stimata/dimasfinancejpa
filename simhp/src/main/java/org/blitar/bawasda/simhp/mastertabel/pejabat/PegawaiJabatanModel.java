package org.blitar.bawasda.simhp.mastertabel.pejabat;

import java.util.List;

import org.blitar.bawasda.simhp.model.PegawaiJabatan;
import org.blitar.bawasda.simhp.model.TabelJenisPemeriksaanGrup;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.PegawaiJabatanJpaService;
import org.blitar.bawasda.simhp.service.PegawaiJabatanJpaServiceImpl;
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

public class PegawaiJabatanModel {
	
	//1. DAO SERVICE
		private PegawaiJabatanJpaService pegawaiJabatanService = new PegawaiJabatanJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected PegawaiJabatan itemHeader = new PegawaiJabatan();
		protected PegawaiJabatan newItemHeader = new PegawaiJabatan();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<PegawaiJabatan> beanItemContainerHeader = 
				new BeanItemContainer<PegawaiJabatan>(PegawaiJabatan.class);
		private BeanItemContainer<PegawaiJabatan> beanItemContainerHeaderSearch = 
				new BeanItemContainer<PegawaiJabatan>(PegawaiJabatan.class);
		
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<PegawaiJabatan> binderHeader = 
				new BeanFieldGroup<PegawaiJabatan>(PegawaiJabatan.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public PegawaiJabatanModel(){
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
		
		beanItemContainerHeader.addAll(pegawaiJabatanService.findAll());
		
	}
	public PegawaiJabatanJpaService getPegawaiJabatanService() {
		return pegawaiJabatanService;
	}
	public void setPegawaiJabatanService(
			PegawaiJabatanJpaService pegawaiJabatanService) {
		this.pegawaiJabatanService = pegawaiJabatanService;
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
	public PegawaiJabatan getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(PegawaiJabatan itemHeader) {
		this.itemHeader = itemHeader;
	}
	public PegawaiJabatan getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(PegawaiJabatan newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<PegawaiJabatan> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<PegawaiJabatan> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<PegawaiJabatan> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<PegawaiJabatan> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<PegawaiJabatan> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<PegawaiJabatan> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
}
