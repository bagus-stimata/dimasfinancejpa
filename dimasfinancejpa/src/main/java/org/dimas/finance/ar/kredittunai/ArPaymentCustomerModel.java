package org.dimas.finance.ar.kredittunai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dimas.finance.jpa.dao.ArInvoiceJpaService;
import org.dimas.finance.jpa.dao.ArInvoiceJpaServiceImpl;
import org.dimas.finance.jpa.dao.ArPaymentDetailJpaService;
import org.dimas.finance.jpa.dao.ArPaymentDetailJpaServiceImpl;
import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaService;
import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuGiroJpaService;
import org.dimas.finance.jpa.dao.BukuGiroJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuTransferJpaService;
import org.dimas.finance.jpa.dao.BukuTransferJpaServiceImpl;
import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.Arpaymentdetail;
import org.dimas.finance.model.Arpaymentheader;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class ArPaymentCustomerModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public Arpaymentheader itemHeader;
	public Arpaymentdetail itemDetail;
	public Arinvoice itemInvoice;

	private ArInvoiceJpaService arInvoiceService;
	private ArPaymentHeaderJpaService arPaymentHeaderService;
	private ArPaymentDetailJpaService arPaymentDetailService;
	
	private BukuGiroJpaService bukugiroService;
	private BukuTransferJpaService bukutransferService;
	
//KITA PAKE BEAN ITEM CONTAINER - JPA
//	public JPAContainer<Arpaymentheader> tableJpaContainer;	
//	public JPAContainer<Arpaymentdetail> tableDetailJpaContainer;
	
	public BeanItem<Arinvoice> beanItemInvoice;
	public BeanItem<Arpaymentheader> beanItemHeader;
	public BeanItem<Arpaymentdetail> beanItemDetail;
	
	public BeanItemContainer<Arpaymentheader> beanItemContainerHeader;
	public BeanItemContainer<Arpaymentdetail> beanItemContainerDetail;
	
	//Bean ItemContainer Pendukung
	//TIDAK ADA
	
	private String operationStatus;
	
	private TransaksiHelper transaksiHelper = new TransaksiHelper();
	
	public ArPaymentCustomerModel(){
		initVariable();
		//TIDAK MENGIRIM VARIABLE MAKA TIDAK ADA NILAI AWAL
//		initVariableData();
	}
	public ArPaymentCustomerModel(Arinvoice arInvoice){
		initVariable();
		this.itemInvoice = arInvoice;
		initVariableData();
	}
	public BukuGiroJpaService getBukugiroService() {
		return bukugiroService;
	}
	public void setBukugiroService(BukuGiroJpaService bukugiroService) {
		this.bukugiroService = bukugiroService;
	}
	public BukuTransferJpaService getBukutransferService() {
		return bukutransferService;
	}
	public void setBukutransferService(BukuTransferJpaService bukutransferService) {
		this.bukutransferService = bukutransferService;
	}
	public void initVariable(){
		itemInvoice = new Arinvoice();
		itemHeader = new Arpaymentheader();
		itemDetail = new Arpaymentdetail();
		
		arInvoiceService = new ArInvoiceJpaServiceImpl();
		arPaymentHeaderService = new ArPaymentHeaderJpaServiceImpl();
		arPaymentDetailService = new ArPaymentDetailJpaServiceImpl();
		
		bukugiroService = new BukuGiroJpaServiceImpl();
		bukutransferService = new BukuTransferJpaServiceImpl();
		
		beanItemContainerHeader = new BeanItemContainer<Arpaymentheader>(Arpaymentheader.class);
		beanItemContainerDetail = new BeanItemContainer<Arpaymentdetail>(Arpaymentdetail.class);
		
	};

	public void initVariableData(){		
			//JANGAN SAMPAI NULL	
			beanItemInvoice = new BeanItem<Arinvoice>(itemInvoice);
			
			if (itemInvoice!=null){
				
				System.out.println("Init >> ArPaymentCustomerModel >> initVariableData");
				
				//MENGHASILKAN HEADER DARI DETAIL
				//Get List refno Transaksi yang ada INVOICE dan DIVISI 
				List<Arpaymentdetail> listDetail = new ArrayList<Arpaymentdetail>();
				String invoiceno = itemInvoice.getId().getInvoiceno();
				String div = itemInvoice.getId().getDivision();
				listDetail = arPaymentDetailService.findAllByInvoiceAndDiv(invoiceno, div);
				beanItemContainerDetail.addAll(listDetail);
				
				List<Arpaymentheader> listHeader = new ArrayList<Arpaymentheader>();				
				for (Arpaymentdetail item: listDetail){
					//JIKA SAMA >> HEADER UPDATE/TIMPA
					listHeader.add(item.getArpaymentheaderBean());
				}			
				beanItemContainerHeader = new BeanItemContainer<Arpaymentheader>(Arpaymentheader.class);
				beanItemContainerHeader.addAll(listHeader);
				
			}
		
//			//CALCULASI ULANG AMOUNTPAID
//			beanItemInvoice.getItemProperty("amountpay").setValue(getHitungAmountDetailNow());
			//COBA LANGSUNG DARI DATABASE
//			beanItemInvoice.getItemProperty("amountpay").setValue(getHitungAmountDetailAllFromDatabase());
			getItemInvoice().setAmountpay(getHitungAmountDetailAllFromDatabase());
			//UPDATE ARINVOICE
			getArInvoiceService().updateObject(getItemInvoice());
			
//			//DI NOLKAN LAGI SUPAYA DETAIL TABLE DETAIL KOSONG DAHULU
			beanItemContainerDetail.removeAllItems();

	}
	
	public double getHitungAmountDetailAllFromDatabase(){
		List<Arpaymentdetail> listDetail = new ArrayList<Arpaymentdetail>();
		String invoiceno = itemInvoice.getId().getInvoiceno();
		String div = itemInvoice.getId().getDivision();
		listDetail = arPaymentDetailService.findAllByInvoiceAndDiv(invoiceno, div);
		
		double amountInvoiceDetailAll = 0.0;		
		for (Arpaymentdetail item: listDetail){
			amountInvoiceDetailAll += item.getCashamountpay() + item.getGiroamountpay() + 
					item.getReturamountpay() + item.getTransferamountpay() + item.getPotonganamount();
		}
		
		return amountInvoiceDetailAll;
	}
	public double getHitungAmountDetailNow(){
		
		double amountInvoiceDetailAll = 0.0;
		Collection itemIds = getBeanItemContainerDetail().getItemIds();
		for (Object itemId: itemIds){
			Arpaymentdetail item = new Arpaymentdetail();
			item = getBeanItemContainerDetail().getItem(itemId).getBean();
			amountInvoiceDetailAll += item.getCashamountpay() + item.getGiroamountpay() + 
					item.getReturamountpay() + item.getTransferamountpay() + item.getPotonganamount();
		}
		
		return amountInvoiceDetailAll;		
	}
	
	public void removeContainerFiltersTableUtama(){		
		try{
		} catch(Exception ex){}
		
	}
	public void removeContainerFiltersTablePendukung(){
		try{
		} catch(Exception ex){}
	}
	public void setFreshDataForm(){
	}
	
	public Arinvoice getItemInvoice() {
		return itemInvoice;
	}
	public void setItemInvoice(Arinvoice itemInvoice) {
		this.itemInvoice = itemInvoice;
	}
	
	public Arpaymentheader getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(Arpaymentheader itemHeader) {
		this.itemHeader = itemHeader;
	}
	public static String getPersistenceunit() {
		return persistenceUnit;
	}
	public Arpaymentdetail getItemDetail() {
		return itemDetail;
	}
	public void setItemDetail(Arpaymentdetail itemDetail) {
		this.itemDetail = itemDetail;
	}


	public BeanItemContainer<Arpaymentheader> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<Arpaymentheader> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public BeanItemContainer<Arpaymentdetail> getBeanItemContainerDetail() {
		return beanItemContainerDetail;
	}
	public void setBeanItemContainerDetail(
			BeanItemContainer<Arpaymentdetail> beanItemContainerDetail) {
		this.beanItemContainerDetail = beanItemContainerDetail;
	}
	public String getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public ArPaymentHeaderJpaService getArPaymentHeaderService() {
		return arPaymentHeaderService;
	}
	public void setArPaymentHeaderService(
			ArPaymentHeaderJpaService arPaymentHeaderService) {
		this.arPaymentHeaderService = arPaymentHeaderService;
	}
	public ArPaymentDetailJpaService getArPaymentDetailService() {
		return arPaymentDetailService;
	}
	public void setArPaymentDetailService(
			ArPaymentDetailJpaService arPaymentDetailService) {
		this.arPaymentDetailService = arPaymentDetailService;
	}
	public ArInvoiceJpaService getArInvoiceService() {
		return arInvoiceService;
	}
	public void setArInvoiceService(ArInvoiceJpaService arInvoiceService) {
		this.arInvoiceService = arInvoiceService;
	}
	public BeanItem<Arinvoice> getBeanItemInvoice() {
		return beanItemInvoice;
	}
	public void setBeanItemInvoice(BeanItem<Arinvoice> beanItemInvoice) {
		this.beanItemInvoice = beanItemInvoice;
	}
	public BeanItem<Arpaymentheader> getBeanItemHeader() {
		return beanItemHeader;
	}
	public void setBeanItemHeader(BeanItem<Arpaymentheader> beanItemHeader) {
		this.beanItemHeader = beanItemHeader;
	}
	public BeanItem<Arpaymentdetail> getBeanItemDetail() {
		return beanItemDetail;
	}
	public void setBeanItemDetail(BeanItem<Arpaymentdetail> beanItemDetail) {
		this.beanItemDetail = beanItemDetail;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	
	
	
}
