package org.dimas.finance.cashandbank.penerimaankas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.dimas.finance.jpa.dao.BkbDetailJpaService;
import org.dimas.finance.jpa.dao.BkbDetailJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkbHeaderJpaService;
import org.dimas.finance.jpa.dao.BkbHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BkbbalanceJpaService;
import org.dimas.finance.jpa.dao.BkbbalanceJpaServiceImpl;
import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.model.Bkbdetail;
import org.dimas.finance.model.Bkbheader;
import org.dimas.finance.model.Division;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class PenerimaanKasBesarModel {
	//1. DAO SERVICE
	private BkbHeaderJpaService bkbHeaderService;
	private BkbDetailJpaService bkbDetailService;
	private BkbbalanceJpaService bkbbalanceService;
	private TransaksiHelper transaksiHelper = new TransaksiHelper();
	
	private SysvarJpaService sysvarService;
	private DivisionJpaService divisionService;
	
	
//2. ENTITY
	protected Bkbheader bkbheader;
	protected Bkbheader newBkbheader;

	protected Bkbdetail bkbdetail;
	
//	protected MenuAccessTemp menuAccessTemp;
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	private BeanItemContainer<Bkbheader> beanItemContainerBkbHeader;
	private BeanItemContainer<Bkbheader> beanItemContainerBkbHeaderSearch;

	private BeanItemContainer<Bkbdetail> beanItemContainerBkbDetail;
	
	private BeanItemContainer<Division> beanItemContainerDivision;
	
//	private BeanItemContainer<Bank> beanItemContainerBank;
//5. Binder (BeanFieldGroup)
	private BeanFieldGroup<Bkbheader> binderBkbheader;
	private BeanFieldGroup<Bkbdetail> binderBkbdetail;
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllItem;
	
	public PenerimaanKasBesarModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		bkbHeaderService = new BkbHeaderJpaServiceImpl();
		bkbDetailService = new BkbDetailJpaServiceImpl();
		bkbbalanceService = new BkbbalanceJpaServiceImpl();
		
		sysvarService = new SysvarJpaServiceImpl();
		divisionService = new DivisionJpaServiceImpl();
		
		bkbheader = new Bkbheader();
		bkbdetail = new Bkbdetail();
		
		beanItemContainerBkbHeader = new BeanItemContainer<Bkbheader>(Bkbheader.class);
		beanItemContainerBkbHeaderSearch = new BeanItemContainer<Bkbheader>(Bkbheader.class);
		beanItemContainerBkbDetail= new BeanItemContainer<Bkbdetail>(Bkbdetail.class);
		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
		
		binderBkbheader = new BeanFieldGroup<Bkbheader>(Bkbheader.class);
		binderBkbdetail = new BeanFieldGroup<Bkbdetail>(Bkbdetail.class);
	}
	public void initVariableData(){
		reload();
	}
	public void reload(){
		beanItemContainerBkbHeader.removeAllContainerFilters();
		beanItemContainerBkbHeaderSearch.removeAllContainerFilters();
		beanItemContainerBkbDetail.removeAllContainerFilters();
		beanItemContainerDivision.removeAllContainerFilters();

		beanItemContainerBkbHeader.removeAllItems();
		beanItemContainerBkbHeaderSearch.removeAllItems();
		beanItemContainerBkbDetail.removeAllItems();
		beanItemContainerDivision.removeAllItems();
		
		//DEMI ALASAN SPEED
		beanItemContainerDivision.addAll(divisionService.findAll());
		
	}

	public void fillContainerDetailWithNumber(){
		List<Bkbdetail> list = new ArrayList<Bkbdetail>();
		Collection itemIds = beanItemContainerBkbDetail.getItemIds();
		for (Object itemId: itemIds){
			Bkbdetail item = new Bkbdetail();
			item = beanItemContainerBkbDetail.getItem(itemId).getBean();
			try{
				item.setUrut(item.getId().getNourut());
			} catch(Exception ex){}
			list.add(item);
			
		}
		
		beanItemContainerBkbDetail.removeAllItems();
		beanItemContainerBkbDetail.addAll(list);
	}
	public void fillContainerDetailWithNumber(Set<Bkbdetail> bkbdetailSet){
		//KALAU SIZE =0 masih oke tapi kalau null akan error
		//Jika bkbdetailSet null atau size =0 maka kosongkan
		List<Bkbdetail> newList = new ArrayList<Bkbdetail>();
		 if (bkbdetailSet != null){
			for (Bkbdetail item: bkbdetailSet){
				item.setUrut(item.getId().getNourut());
				newList.add(item);
			}
		 }
		beanItemContainerBkbDetail.removeAllItems();
		beanItemContainerBkbDetail.addAll(newList);		
		 
	}
	
	
	public BkbbalanceJpaService getBkbbalanceService() {
		return bkbbalanceService;
	}

	public void setBkbbalanceService(BkbbalanceJpaService bkbbalanceService) {
		this.bkbbalanceService = bkbbalanceService;
	}
	
	public BkbHeaderJpaService getBkbHeaderService() {
		return bkbHeaderService;
	}

	public void setBkbHeaderService(BkbHeaderJpaService bkbHeaderService) {
		this.bkbHeaderService = bkbHeaderService;
	}

	public BkbDetailJpaService getBkbDetailService() {
		return bkbDetailService;
	}

	public void setBkbDetailService(BkbDetailJpaService bkbDetailService) {
		this.bkbDetailService = bkbDetailService;
	}

	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}

	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}

	public SysvarJpaService getSysvarService() {
		return sysvarService;
	}

	public void setSysvarService(SysvarJpaService sysvarService) {
		this.sysvarService = sysvarService;
	}

	public DivisionJpaService getDivisionService() {
		return divisionService;
	}

	public void setDivisionService(DivisionJpaService divisionService) {
		this.divisionService = divisionService;
	}

	public Bkbheader getBkbheader() {
		return bkbheader;
	}

	public void setBkbheader(Bkbheader bkbheader) {
		this.bkbheader = bkbheader;
	}


	public BeanItemContainer<Bkbheader> getBeanItemContainerBkbHeader() {
		return beanItemContainerBkbHeader;
	}

	public void setBeanItemContainerBkbHeader(
			BeanItemContainer<Bkbheader> beanItemContainerBkbHeader) {
		this.beanItemContainerBkbHeader = beanItemContainerBkbHeader;
	}

	public BeanItemContainer<Bkbheader> getBeanItemContainerBkbHeaderSearch() {
		return beanItemContainerBkbHeaderSearch;
	}

	public void setBeanItemContainerBkbHeaderSearch(
			BeanItemContainer<Bkbheader> beanItemContainerBkbHeaderSearch) {
		this.beanItemContainerBkbHeaderSearch = beanItemContainerBkbHeaderSearch;
	}

	public BeanItemContainer<Bkbdetail> getBeanItemContainerBkbDetail() {
		return beanItemContainerBkbDetail;
	}

	public void setBeanItemContainerBkbDetail(
			BeanItemContainer<Bkbdetail> beanItemContainerBkbDetail) {
		this.beanItemContainerBkbDetail = beanItemContainerBkbDetail;
	}

	public BeanItemContainer<Division> getBeanItemContainerDivision() {
		return beanItemContainerDivision;
	}

	public void setBeanItemContainerDivision(
			BeanItemContainer<Division> beanItemContainerDivision) {
		this.beanItemContainerDivision = beanItemContainerDivision;
	}

	public BeanFieldGroup<Bkbheader> getBinderBkbheader() {
		return binderBkbheader;
	}

	public void setBinderBkbheader(BeanFieldGroup<Bkbheader> binderBkbheader) {
		this.binderBkbheader = binderBkbheader;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}


	public boolean isSelectAllItem() {
		return selectAllItem;
	}

	public void setSelectAllItem(boolean selectAllItem) {
		this.selectAllItem = selectAllItem;
	}

	public Bkbdetail getBkbdetail() {
		return bkbdetail;
	}

	public void setBkbdetail(Bkbdetail bkbdetail) {
		this.bkbdetail = bkbdetail;
	}

	public BeanFieldGroup<Bkbdetail> getBinderBkbdetail() {
		return binderBkbdetail;
	}

	public void setBinderBkbdetail(BeanFieldGroup<Bkbdetail> binderBkbdetail) {
		this.binderBkbdetail = binderBkbdetail;
	}

	public Bkbheader getNewBkbheader() {
		return newBkbheader;
	}

	public void setNewBkbheader(Bkbheader newBkbheader) {
		this.newBkbheader = newBkbheader;
	}

	
}
