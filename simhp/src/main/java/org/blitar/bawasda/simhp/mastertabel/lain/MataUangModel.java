package org.blitar.bawasda.simhp.mastertabel.lain;

import java.util.List;

import org.blitar.bawasda.simhp.model.MataUang;
import org.blitar.bawasda.simhp.model.SuratTugasTimAnggotaPosisi;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelRuangLingkupPemeriksaan;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.MataUangJpaService;
import org.blitar.bawasda.simhp.service.MataUangJpaServiceImpl;
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

public class MataUangModel {
	
	//1. DAO SERVICE
		private MataUangJpaService mataUangService = new MataUangJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected MataUang itemHeader = new MataUang();
		protected MataUang newItemHeader = new MataUang();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<MataUang> beanItemContainerHeader = 
				new BeanItemContainer<MataUang>(MataUang.class);
		private BeanItemContainer<MataUang> beanItemContainerHeaderSearch = 
				new BeanItemContainer<MataUang>(MataUang.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<MataUang> binderHeader = 
				new BeanFieldGroup<MataUang>(MataUang.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public MataUangModel(){
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
		
		beanItemContainerHeader.addAll(mataUangService.findAll());
		
	}
	public MataUangJpaService getMataUangService() {
		return mataUangService;
	}
	public void setMataUangService(MataUangJpaService mataUangService) {
		this.mataUangService = mataUangService;
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
	public MataUang getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(MataUang itemHeader) {
		this.itemHeader = itemHeader;
	}
	public MataUang getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(MataUang newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<MataUang> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<MataUang> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<MataUang> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<MataUang> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<MataUang> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<MataUang> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
}
