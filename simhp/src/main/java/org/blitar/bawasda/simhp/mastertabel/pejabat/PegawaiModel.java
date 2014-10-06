package org.blitar.bawasda.simhp.mastertabel.pejabat;

import java.util.List;

import org.blitar.bawasda.simhp.model.Pegawai;
import org.blitar.bawasda.simhp.model.PegawaiJabatan;
import org.blitar.bawasda.simhp.model.TabelJenisPemeriksaan;
import org.blitar.bawasda.simhp.model.TabelJenisPemeriksaanGrup;
import org.blitar.bawasda.simhp.model.TabelRekomendasi;
import org.blitar.bawasda.simhp.model.TabelRekomendasiGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanGrup;
import org.blitar.bawasda.simhp.model.TabelTemuanKelompok;
import org.blitar.bawasda.simhp.service.PegawaiJabatanJpaService;
import org.blitar.bawasda.simhp.service.PegawaiJabatanJpaServiceImpl;
import org.blitar.bawasda.simhp.service.PegawaiJpaService;
import org.blitar.bawasda.simhp.service.PegawaiJpaServiceImpl;
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

public class PegawaiModel {
	
	//1. DAO SERVICE
		private PegawaiJpaService pegawaiService = new PegawaiJpaServiceImpl();
		private PegawaiJabatanJpaService pegawaiJabatanService = new PegawaiJabatanJpaServiceImpl();
		
		private SysvarJpaService sysvarService = new SysvarJpaServiceImpl();
		private TransaksiHelper transaksiHelper = new TransaksiHelper();
		
	//2. ENTITY
		protected Pegawai itemHeader = new Pegawai();
		protected Pegawai newItemHeader = new Pegawai();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<Pegawai> beanItemContainerHeader = 
				new BeanItemContainer<Pegawai>(Pegawai.class);
		private BeanItemContainer<Pegawai> beanItemContainerHeaderSearch = 
				new BeanItemContainer<Pegawai>(Pegawai.class);
		
		private BeanItemContainer<PegawaiJabatan> beanItemContainerGrup = 
				new BeanItemContainer<PegawaiJabatan>(PegawaiJabatan.class);
	
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<Pegawai> binderHeader = 
				new BeanFieldGroup<Pegawai>(Pegawai.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public PegawaiModel(){
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
		
		beanItemContainerHeader.addAll(pegawaiService.findAll());
		//CUMA YANG AKTIF SAJA
		beanItemContainerGrup.addAll(pegawaiJabatanService.findAllActive());
		
	}
	public PegawaiJpaService getPegawaiService() {
		return pegawaiService;
	}
	public void setPegawaiService(PegawaiJpaService pegawaiService) {
		this.pegawaiService = pegawaiService;
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
	public Pegawai getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(Pegawai itemHeader) {
		this.itemHeader = itemHeader;
	}
	public Pegawai getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(Pegawai newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public BeanItemContainer<Pegawai> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<Pegawai> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<Pegawai> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<Pegawai> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<PegawaiJabatan> getBeanItemContainerGrup() {
		return beanItemContainerGrup;
	}
	public void setBeanItemContainerGrup(
			BeanItemContainer<PegawaiJabatan> beanItemContainerGrup) {
		this.beanItemContainerGrup = beanItemContainerGrup;
	}
	public BeanFieldGroup<Pegawai> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<Pegawai> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
}
