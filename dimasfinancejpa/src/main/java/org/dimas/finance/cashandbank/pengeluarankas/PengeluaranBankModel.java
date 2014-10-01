package org.dimas.finance.cashandbank.pengeluarankas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.dimas.finance.jpa.dao.BBankDetailJpaService;
import org.dimas.finance.jpa.dao.BBankDetailJpaServiceImpl;
import org.dimas.finance.jpa.dao.BBankHeaderJpaService;
import org.dimas.finance.jpa.dao.BBankHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BbankbalanceJpaService;
import org.dimas.finance.jpa.dao.BbankbalanceJpaServiceImpl;
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
import org.dimas.finance.model.Bbankdetail;
import org.dimas.finance.model.Bbankheader;
import org.dimas.finance.model.Bkbdetail;
import org.dimas.finance.model.Bkbheader;
import org.dimas.finance.model.Division;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class PengeluaranBankModel {
	//1. DAO SERVICE
	private BBankHeaderJpaService bbankHeaderService;
	private BBankDetailJpaService bbankDetailService;
	private BbankbalanceJpaService bbankbalanceService;
	private TransaksiHelper transaksiHelper = new TransaksiHelper();
	
	private SysvarJpaService sysvarService;
	private DivisionJpaService divisionService;
	
	
//2. ENTITY
	protected Bbankheader itemHeader;
	protected Bbankheader newItemHeader;

	protected Bbankdetail itemDetail;
	
//	protected MenuAccessTemp menuAccessTemp;
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	private BeanItemContainer<Bbankheader> beanItemContainerHeader;
	private BeanItemContainer<Bbankheader> beanItemContainerHeaderSearch;

	private BeanItemContainer<Bbankdetail> beanItemContainerDetail;
	
	private BeanItemContainer<Division> beanItemContainerDivision;
	
//	private BeanItemContainer<Bank> beanItemContainerBank;
//5. Binder (BeanFieldGroup)
	private BeanFieldGroup<Bbankheader> binderHeader;
	private BeanFieldGroup<Bbankdetail> binderDetail;
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllItem;
	
	public PengeluaranBankModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		bbankHeaderService = new BBankHeaderJpaServiceImpl();
		bbankDetailService = new BBankDetailJpaServiceImpl();
		bbankbalanceService = new BbankbalanceJpaServiceImpl();
		
		sysvarService = new SysvarJpaServiceImpl();
		divisionService = new DivisionJpaServiceImpl();
		
		itemHeader = new Bbankheader();
		itemDetail = new Bbankdetail();
		
		beanItemContainerHeader = new BeanItemContainer<Bbankheader>(Bbankheader.class);
		beanItemContainerHeaderSearch = new BeanItemContainer<Bbankheader>(Bbankheader.class);
		beanItemContainerDetail= new BeanItemContainer<Bbankdetail>(Bbankdetail.class);
		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
		
		binderHeader = new BeanFieldGroup<Bbankheader>(Bbankheader.class);
		binderDetail = new BeanFieldGroup<Bbankdetail>(Bbankdetail.class);
	}
	public void initVariableData(){
		reload();
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeaderSearch.removeAllContainerFilters();
		beanItemContainerDetail.removeAllContainerFilters();
		beanItemContainerDivision.removeAllContainerFilters();

		beanItemContainerHeader.removeAllItems();
		beanItemContainerHeaderSearch.removeAllItems();
		beanItemContainerDetail.removeAllItems();
		beanItemContainerDivision.removeAllItems();
		
		//DEMI ALASAN SPEED
		beanItemContainerDivision.addAll(divisionService.findAll());
		
	}

	public void fillContainerDetailWithNumber(){
		List<Bbankdetail> list = new ArrayList<Bbankdetail>();
		Collection itemIds = beanItemContainerDetail.getItemIds();
		for (Object itemId: itemIds){
			Bbankdetail item = new Bbankdetail();
			item = beanItemContainerDetail.getItem(itemId).getBean();
			try{
				item.setUrut(item.getId().getNourut());
			} catch(Exception ex){}
			list.add(item);
			
		}
		
		beanItemContainerDetail.removeAllItems();
		beanItemContainerDetail.addAll(list);
	}
	public void fillContainerDetailWithNumber(Set<Bbankdetail> detailSet){
		//KALAU SIZE =0 masih oke tapi kalau null akan error
		//Jika bkbdetailSet null atau size =0 maka kosongkan
		List<Bbankdetail> newList = new ArrayList<Bbankdetail>();
		 if (detailSet != null){
			for (Bbankdetail item: detailSet){
				item.setUrut(item.getId().getNourut());
				newList.add(item);
			}
		 }
		beanItemContainerDetail.removeAllItems();
		beanItemContainerDetail.addAll(newList);		
		 
	}

	public BBankHeaderJpaService getBbankHeaderService() {
		return bbankHeaderService;
	}

	public void setBbankHeaderService(BBankHeaderJpaService bbankHeaderService) {
		this.bbankHeaderService = bbankHeaderService;
	}

	public BBankDetailJpaService getBbankDetailService() {
		return bbankDetailService;
	}

	public void setBbankDetailService(BBankDetailJpaService bbankDetailService) {
		this.bbankDetailService = bbankDetailService;
	}

	public BbankbalanceJpaService getBbankbalanceService() {
		return bbankbalanceService;
	}

	public void setBbankbalanceService(BbankbalanceJpaService bbankbalanceService) {
		this.bbankbalanceService = bbankbalanceService;
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

	public Bbankheader getItemHeader() {
		return itemHeader;
	}

	public void setItemHeader(Bbankheader itemHeader) {
		this.itemHeader = itemHeader;
	}

	public Bbankheader getNewItemHeader() {
		return newItemHeader;
	}

	public void setNewItemHeader(Bbankheader newItemHeader) {
		this.newItemHeader = newItemHeader;
	}

	public Bbankdetail getItemDetail() {
		return itemDetail;
	}

	public void setItemDetail(Bbankdetail itemDetail) {
		this.itemDetail = itemDetail;
	}

	public BeanItemContainer<Bbankheader> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}

	public void setBeanItemContainerHeader(
			BeanItemContainer<Bbankheader> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}

	public BeanItemContainer<Bbankheader> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}

	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<Bbankheader> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}

	public BeanItemContainer<Bbankdetail> getBeanItemContainerDetail() {
		return beanItemContainerDetail;
	}

	public void setBeanItemContainerDetail(
			BeanItemContainer<Bbankdetail> beanItemContainerDetail) {
		this.beanItemContainerDetail = beanItemContainerDetail;
	}

	public BeanItemContainer<Division> getBeanItemContainerDivision() {
		return beanItemContainerDivision;
	}

	public void setBeanItemContainerDivision(
			BeanItemContainer<Division> beanItemContainerDivision) {
		this.beanItemContainerDivision = beanItemContainerDivision;
	}

	public BeanFieldGroup<Bbankheader> getBinderHeader() {
		return binderHeader;
	}

	public void setBinderHeader(BeanFieldGroup<Bbankheader> binderHeader) {
		this.binderHeader = binderHeader;
	}

	public BeanFieldGroup<Bbankdetail> getBinderDetail() {
		return binderDetail;
	}

	public void setBinderDetail(BeanFieldGroup<Bbankdetail> binderDetail) {
		this.binderDetail = binderDetail;
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

	

	
}
