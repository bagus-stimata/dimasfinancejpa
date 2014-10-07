package org.dimas.finance.ar.kredittunai;

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
import org.dimas.finance.model.ArinvoicePK;
import org.dimas.finance.model.Arpaymentdetail;
import org.dimas.finance.model.Arpaymentheader;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Bukutransfer;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ArPaymentCustPembayaranModel{
	
	private static final String persistenceUnit = "financePU";
	//DAO
	private ArInvoiceJpaService arInvoiceService;
	private ArPaymentHeaderJpaService arPaymentHeaderService;
	private ArPaymentDetailJpaService arPaymentDetailService;
	
	//DAO -->combobox
	private BukuGiroJpaService bukuGiroService;
	private BukuTransferJpaService bukuTransferService;
	
	//DAO PENDUKUNG	
	public Arinvoice arInvoice;
	public Arpaymentheader arPaymentHeader;
	public Arpaymentdetail arPaymentDetail;
	
	//BeanItem utama
	public BeanItem<Arinvoice> beanItemArInvoice;
	public BeanItem<ArinvoicePK> beanItemArInvoicePK;
	public BeanItem<Arpaymentheader> beanItemArPaymentHeader;
	public BeanItem<Arpaymentdetail> beanItemArPaymentDetail;
	//FOR COMBOBOX
	
	//######TIDAK DIGUNAKAN: BeanItemContainer Utama
	public BeanItemContainer<Arinvoice> beanItemContainerArInvoice;
	public BeanItemContainer<Arpaymentheader> beanItemContainerArPaymentHeader;
	public BeanItemContainer<Arpaymentdetail> beanItemContainerArPaymentDetail;
	
	//for combobox
	public BeanItemContainer<Bukugiro> beanItemContainerBukuGiro;
	public BeanItemContainer<Bukutransfer> beanItemContainerBukuTransfer;
	public BeanItemContainer<Arinvoice> beanitemContainerReturBelumLunas;
	
	//VARIABLE PENDUKUNG
	private boolean allowCloseWindow;
	private String formOperationStatus = "OPEN";
	private TransaksiHelper transaksiHelper = new TransaksiHelper();
	
	double amountForOtherDetail =0.0;
	double amountForThisDetail =0.0;
	double amountPenambahan =0.0;
	
	public ArPaymentCustPembayaranModel() {
		initVariable();
	}
	public ArPaymentCustPembayaranModel(Arinvoice arinvoice){		
		initVariable();
		this.arInvoice = arinvoice;
		initVariableData();
	}
	public ArPaymentCustPembayaranModel(Arpaymentdetail arpaymentdetail){
		initVariable();
		this.arPaymentDetail = arpaymentdetail;
		initVariableData();
	}
	public ArPaymentCustPembayaranModel(Arinvoice arinvoice, Arpaymentdetail arpaymentdetail){
		initVariable();
		this.arInvoice = arinvoice;
		this.arPaymentDetail = arpaymentdetail;
		initVariableData();
	}
	public void initVariable(){
		arInvoice = new Arinvoice();
		arPaymentHeader = new Arpaymentheader();
		arPaymentDetail = new Arpaymentdetail();
		
		//DAO UTAMA
		arInvoiceService = new ArInvoiceJpaServiceImpl();
		arPaymentHeaderService = new ArPaymentHeaderJpaServiceImpl();
		arPaymentDetailService = new ArPaymentDetailJpaServiceImpl();
		//DAO --> COMBO
		bukuGiroService = new BukuGiroJpaServiceImpl();
		bukuTransferService = new BukuTransferJpaServiceImpl();
		
		beanItemContainerArPaymentHeader = new BeanItemContainer<Arpaymentheader>(Arpaymentheader.class);
		beanItemContainerArPaymentDetail = new BeanItemContainer<Arpaymentdetail>(Arpaymentdetail.class);
		//FOR COMBOBOX
		beanItemContainerBukuGiro = new BeanItemContainer<Bukugiro>(Bukugiro.class);
		beanItemContainerBukuTransfer = new BeanItemContainer<Bukutransfer>(Bukutransfer.class);
		beanitemContainerReturBelumLunas = new BeanItemContainer<Arinvoice>(Arinvoice.class);
		
		allowCloseWindow = true;
		
	}
	
	public void initVariableData(){	
		System.out.println("Init >> ArPaymentCustPembayaranModel >> initVariableData");
		
		beanItemArInvoice = new BeanItem<Arinvoice>(arInvoice);
		beanItemArInvoicePK = new BeanItem<ArinvoicePK>(arInvoice.getId());
		beanItemArPaymentHeader = new BeanItem<Arpaymentheader>(arPaymentHeader);
		beanItemArPaymentDetail = new BeanItem<Arpaymentdetail>(arPaymentDetail);
		
		if (arPaymentDetail.getBukugiroBean() !=null) {
			
//####			beanItemContainerBukuGiro.addAll(bukuGiroService.findAllAvalilableGiro(arPaymentDetail.getBukugiroBean().getRefno()));			
		}else {
			beanItemContainerBukuGiro.addAll(bukuGiroService.findAllAvailableGiro());
		}
		if (arPaymentDetail.getBukutransferBean() !=null){
//###			beanItemContainerBukuTransfer.addAll(bukuTransferService.findAllAvailabelTransfer(arPaymentDetail.getBukutransferBean().getRefno()));			
//			beanItemContainerBukuTransfer.addAll(bukuTransferService.findAllAvailableTransfer());			
		}else {
			beanItemContainerBukuTransfer.addAll(bukuTransferService.findAllAvailableTransfer());			
		}
		if (arPaymentDetail.getReturBean() != null){
			beanitemContainerReturBelumLunas.addAll(arInvoiceService.findAllReturBelumLunas(arPaymentDetail.getReturBean()));
		} else {
			beanitemContainerReturBelumLunas.addAll(arInvoiceService.findAllReturBelumLunas());
		}
		
		
		//UNTUK MENGETAHUI BAHWA INI ADD ATAU EDIT >> 
		if (arPaymentDetail.getId().getRefno().equals("xxx")){
			formOperationStatus =  EnumFormOperationStatus.ADDING.getStrCode();
		} else {
			formOperationStatus =  EnumFormOperationStatus.EDITING.getStrCode();
		}
		
	}
	
	
	public void removeContainerFiltersTableUtama(){
		
	}	
	public void removeContainerFiltersTablePendukung(){
	}
	public ArInvoiceJpaService getArInvoiceService() {
		return arInvoiceService;
	}
	public void setArInvoiceService(ArInvoiceJpaService arInvoiceService) {
		this.arInvoiceService = arInvoiceService;
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
	public BukuGiroJpaService getBukuGiroService() {
		return bukuGiroService;
	}
	public void setBukuGiroService(BukuGiroJpaService bukuGiroService) {
		this.bukuGiroService = bukuGiroService;
	}
	public BukuTransferJpaService getBukuTransferService() {
		return bukuTransferService;
	}
	public void setBukuTransferService(BukuTransferJpaService bukuTransferService) {
		this.bukuTransferService = bukuTransferService;
	}
	public Arinvoice getArInvoice() {
		return arInvoice;
	}
	public void setArInvoice(Arinvoice arInvoice) {
		this.arInvoice = arInvoice;
	}
	public Arpaymentheader getArPaymentHeader() {
		return arPaymentHeader;
	}
	public void setArPaymentHeader(Arpaymentheader arPaymentHeader) {
		this.arPaymentHeader = arPaymentHeader;
	}
	public Arpaymentdetail getArPaymentDetail() {
		return arPaymentDetail;
	}
	public void setArPaymentDetail(Arpaymentdetail arPaymentDetail) {
		this.arPaymentDetail = arPaymentDetail;
	}
	public BeanItem<Arinvoice> getBeanItemArInvoice() {
		return beanItemArInvoice;
	}
	public void setBeanItemArInvoice(BeanItem<Arinvoice> beanItemArInvoice) {
		this.beanItemArInvoice = beanItemArInvoice;
	}
	public BeanItem<Arpaymentheader> getBeanItemArPaymentHeader() {
		return beanItemArPaymentHeader;
	}
	public void setBeanItemArPaymentHeader(
			BeanItem<Arpaymentheader> beanItemArPaymentHeader) {
		this.beanItemArPaymentHeader = beanItemArPaymentHeader;
	}
	public BeanItem<Arpaymentdetail> getBeanItemArPaymentDetail() {
		return beanItemArPaymentDetail;
	}
	public void setBeanItemArPaymentDetail(
			BeanItem<Arpaymentdetail> beanItemArPaymentDetail) {
		this.beanItemArPaymentDetail = beanItemArPaymentDetail;
	}
	public BeanItemContainer<Arinvoice> getBeanItemContainerArInvoice() {
		return beanItemContainerArInvoice;
	}
	public void setBeanItemContainerArInvoice(
			BeanItemContainer<Arinvoice> beanItemContainerArInvoice) {
		this.beanItemContainerArInvoice = beanItemContainerArInvoice;
	}
	public BeanItemContainer<Arpaymentheader> getBeanItemContainerArPaymentHeader() {
		return beanItemContainerArPaymentHeader;
	}
	public void setBeanItemContainerArPaymentHeader(
			BeanItemContainer<Arpaymentheader> beanItemContainerArPaymentHeader) {
		this.beanItemContainerArPaymentHeader = beanItemContainerArPaymentHeader;
	}
	public BeanItemContainer<Arpaymentdetail> getBeanItemContainerArPaymentDetail() {
		return beanItemContainerArPaymentDetail;
	}
	public void setBeanItemContainerArPaymentDetail(
			BeanItemContainer<Arpaymentdetail> beanItemContainerArPaymentDetail) {
		this.beanItemContainerArPaymentDetail = beanItemContainerArPaymentDetail;
	}
	public BeanItemContainer<Bukugiro> getBeanItemContainerBukuGiro() {
		return beanItemContainerBukuGiro;
	}
	public void setBeanItemContainerBukuGiro(
			BeanItemContainer<Bukugiro> beanItemContainerBukuGiro) {
		this.beanItemContainerBukuGiro = beanItemContainerBukuGiro;
	}
	public BeanItemContainer<Bukutransfer> getBeanItemContainerBukuTransfer() {
		return beanItemContainerBukuTransfer;
	}
	public void setBeanItemContainerBukuTransfer(
			BeanItemContainer<Bukutransfer> beanItemContainerBukuTransfer) {
		this.beanItemContainerBukuTransfer = beanItemContainerBukuTransfer;
	}
	public BeanItemContainer<Arinvoice> getBeanitemContainerReturBelumLunas() {
		return beanitemContainerReturBelumLunas;
	}
	public void setBeanitemContainerReturBelumLunas(
			BeanItemContainer<Arinvoice> beanitemContainerReturBelumLunas) {
		this.beanitemContainerReturBelumLunas = beanitemContainerReturBelumLunas;
	}
	public static String getPersistenceunit() {
		return persistenceUnit;
	}
	public BeanItem<ArinvoicePK> getBeanItemArInvoicePK() {
		return beanItemArInvoicePK;
	}
	public void setBeanItemArInvoicePK(BeanItem<ArinvoicePK> beanItemArInvoicePK) {
		this.beanItemArInvoicePK = beanItemArInvoicePK;
	}
	public boolean isAllowCloseWindow() {
		return allowCloseWindow;
	}
	public void setAllowCloseWindow(boolean allowCloseWindow) {
		this.allowCloseWindow = allowCloseWindow;
	}
	public double getAmountForOtherDetail() {
		return amountForOtherDetail;
	}
	public void setAmountForOtherDetail(double amountForOtherDetail) {
		this.amountForOtherDetail = amountForOtherDetail;
	}
	public double getAmountForThisDetail() {
		return amountForThisDetail;
	}
	public void setAmountForThisDetail(double amountForThisDetail) {
		this.amountForThisDetail = amountForThisDetail;
	}
	public double getAmountPenambahan() {
		return amountPenambahan;
	}
	public void setAmountPenambahan(double amountPenambahan) {
		this.amountPenambahan = amountPenambahan;
	}
	public String getFormOperationStatus() {
		return formOperationStatus;
	}
	public void setFormOperationStatus(String formOperationStatus) {
		this.formOperationStatus = formOperationStatus;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}

	
	
}
