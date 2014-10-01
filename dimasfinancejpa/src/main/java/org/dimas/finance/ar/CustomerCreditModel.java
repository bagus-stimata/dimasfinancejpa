package org.dimas.finance.ar;

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
import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.Region;
import org.dimas.finance.util.Inject;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

public class CustomerCreditModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public Arinvoice item;
	private List<Arinvoice> list;

	private JPAContainer<Arinvoice> tableJpaContainer;	
	private JPAContainer<Region> regionJpaContainer;	
	
	public BeanItem<Arinvoice> formBeanItem;
	private BeanItemContainer<Arinvoice> tableBeanItemContainer;

	private ArInvoiceJpaService arInvoiceService; 
	private ArPaymentHeaderJpaService arpaymentHeaderService;
	private ArPaymentDetailJpaService arpaymentDetailService;
	
	
	private String operationStatus;	
	private TransaksiHelper managerTransaksi = new TransaksiHelper();
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllInvoice;
	

	
	public CustomerCreditModel(){
		initData();
	}
	
	public void initData(){
		System.out.println("Init >> CustomerCreditModel >> initData");
		
		arInvoiceService = new ArInvoiceJpaServiceImpl();
		arpaymentHeaderService = new ArPaymentHeaderJpaServiceImpl();
		arpaymentDetailService = new ArPaymentDetailJpaServiceImpl();
		
		tableJpaContainer =  JPAContainerFactory.make(Arinvoice.class, persistenceUnit);		
		regionJpaContainer = JPAContainerFactory.make(Region.class, persistenceUnit);
		
	};

	public void setFreshDataTable(){		
		try{
			tableJpaContainer.removeAllContainerFilters();
			tableJpaContainer.refresh();
			
		} catch(Exception ex){
		
		}
	}
	
	public void setFreshDataForm(){
		
	}
	public Arinvoice getItem() {
		return item;
	}

	public void setItem(Arinvoice item) {
		this.item = item;
	}

	public List<Arinvoice> getList() {
		return list;
	}

	public void setList(List<Arinvoice> list) {
		this.list = list;
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

	public JPAContainer<Arinvoice> getTableJpaContainer() {
		return tableJpaContainer;
	}

	public void setTableJpaContainer(JPAContainer<Arinvoice> tableJpaContainer) {
		this.tableJpaContainer = tableJpaContainer;
	}

	public JPAContainer<Region> getRegionJpaContainer() {
		return regionJpaContainer;
	}

	public void setRegionJpaContainer(JPAContainer<Region> tableJpaContainer) {
		this.regionJpaContainer = regionJpaContainer;
	
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
	
	
}
