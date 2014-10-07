package org.dimas.finance.ar.kredittunai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.jpa.dao.ArInvoiceJpaService;
import org.dimas.finance.jpa.dao.ArInvoiceJpaServiceImpl;
import org.dimas.finance.jpa.dao.ArPaymentDetailJpaService;
import org.dimas.finance.jpa.dao.ArPaymentDetailJpaServiceImpl;
import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaService;
import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Region;
import org.dimas.finance.util.Inject;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Not;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;

public class CustomerCreditModel implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static final String persistenceUnit = "financePU";
	
	public Arinvoice item = new Arinvoice();

//	private JPAContainer<Arinvoice> tableJpaContainer;	
	
	public BeanItem<Arinvoice> formBeanItem;
	private BeanItemContainer<Arinvoice> tableBeanItemContainer = new BeanItemContainer<Arinvoice>(Arinvoice.class);

	private ArInvoiceJpaService arInvoiceService = new ArInvoiceJpaServiceImpl(); 
	private ArPaymentHeaderJpaService arpaymentHeaderService = new ArPaymentHeaderJpaServiceImpl();
	private ArPaymentDetailJpaService arpaymentDetailService = new ArPaymentDetailJpaServiceImpl();
	private DivisionJpaService divisionService = new DivisionJpaServiceImpl();

	
	private String operationStatus="";	
	private TransaksiHelper managerTransaksi = new TransaksiHelper();
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllInvoice=false;
	
	private BeanItemContainer<Division> beanItemContainerDivision;

	
	public CustomerCreditModel(){
		initData();
	}
	
	public void initData(){
		System.out.println("Init >> CustomerCreditModel >> initData");
		
		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
		
		arInvoiceService = new ArInvoiceJpaServiceImpl();
		arpaymentHeaderService = new ArPaymentHeaderJpaServiceImpl();
		arpaymentDetailService = new ArPaymentDetailJpaServiceImpl();
		
		
		
	};

	public void setFreshDataTable(){		
		try{
			tableBeanItemContainer.removeAllItems();
			tableBeanItemContainer.removeAllContainerFilters();
			tableBeanItemContainer.addAll(arInvoiceService.findAll());
			setFilterDefaultBeanItemContainer();

			
			//COMBOBOX DIVISION
			beanItemContainerDivision.addAll(divisionService.findAll());
			
		} catch(Exception ex){
		
		}
	}
	
	public void setFilterDefaultBeanItemContainer(){

		//TO DAN TUNAI selain CANVAS
//		Filter filter01 =  new SimpleStringFilter("tipejual","TO", true, false);
//		tableBeanItemContainer.addContainerFilter(filter01);
//				
//		Filter filter02 = new Or(new Compare.Equal("term", 1)); 
//		tableBeanItemContainer.addContainerFilter(filter02);
//		
		Filter filter01 =  new Not(new Compare.Equal("tipejual", "C"));
		tableBeanItemContainer.addContainerFilter(filter01);
		
		Filter filter03 = new Or(new Compare.Equal("terkirim", true)); 
		tableBeanItemContainer.addContainerFilter(filter03);
		
	}
	
	public void setFreshDataForm(){
		
	}
	public Arinvoice getItem() {
		return item;
	}

	public void setItem(Arinvoice item) {
		this.item = item;
	}


	public BeanItem<Arinvoice> getFormBeanItem() {
		return formBeanItem;
	}

	public void setFormBeanItem(BeanItem<Arinvoice> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public BeanItemContainer<Arinvoice> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<Arinvoice> tableBeanItemContainer) {
		this.tableBeanItemContainer = tableBeanItemContainer;
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


	public ArInvoiceJpaService getArInvoiceService() {
		return arInvoiceService;
	}

	public void setArInvoiceService(ArInvoiceJpaService arInvoiceService) {
		this.arInvoiceService = arInvoiceService;
	}

	public ArPaymentHeaderJpaService getArpaymentHeaderService() {
		return arpaymentHeaderService;
	}

	public void setArpaymentHeaderService(
			ArPaymentHeaderJpaService arpaymentHeaderService) {
		this.arpaymentHeaderService = arpaymentHeaderService;
	}

	public ArPaymentDetailJpaService getArpaymentDetailService() {
		return arpaymentDetailService;
	}

	public void setArpaymentDetailService(
			ArPaymentDetailJpaService arpaymentDetailService) {
		this.arpaymentDetailService = arpaymentDetailService;
	}

	public static String getPersistenceunit() {
		return persistenceUnit;
	}

	public TransaksiHelper getManagerTransaksi() {
		return managerTransaksi;
	}

	public void setManagerTransaksi(TransaksiHelper managerTransaksi) {
		this.managerTransaksi = managerTransaksi;
	}

	public boolean isSelectAllInvoice() {
		return selectAllInvoice;
	}

	public void setSelectAllInvoice(boolean selectAllInvoice) {
		this.selectAllInvoice = selectAllInvoice;
	}

	public DivisionJpaService getDivisionService() {
		return divisionService;
	}

	public void setDivisionService(DivisionJpaService divisionService) {
		this.divisionService = divisionService;
	}

	public BeanItemContainer<Division> getBeanItemContainerDivision() {
		return beanItemContainerDivision;
	}

	public void setBeanItemContainerDivision(
			BeanItemContainer<Division> beanItemContainerDivision) {
		this.beanItemContainerDivision = beanItemContainerDivision;
	}
	
	
}
