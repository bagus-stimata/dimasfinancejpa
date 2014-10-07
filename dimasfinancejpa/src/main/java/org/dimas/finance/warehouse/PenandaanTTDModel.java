package org.dimas.finance.warehouse;

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
import org.dimas.finance.jpa.dao.SalesmanJpaService;
import org.dimas.finance.jpa.dao.SalesmanJpaServiceImpl;
import org.dimas.finance.model.ArInvoiceSelected;
import org.dimas.finance.model.ArInvoiceSelected;
import org.dimas.finance.model.ArInvoiceSelected;
import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.Arpaymentdetail;
import org.dimas.finance.model.Arpaymentheader;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Region;
import org.dimas.finance.model.Salesman;
import org.dimas.finance.util.Inject;
import org.dimas.finance.util.TransaksiHelper;
import org.eclipse.persistence.jpa.jpql.utility.filter.NullFilter;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.IsNull;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.Not;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.util.filter.Compare.Equal;

public class PenandaanTTDModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public Arinvoice item;
	private List<Arinvoice> list;

	
	private ArInvoiceJpaService arInvoiceService; 
	private ArPaymentHeaderJpaService arpaymentHeaderService;
	private ArPaymentDetailJpaService arpaymentDetailService;
	
	private DivisionJpaService divisionService;
	private SalesmanJpaService salesmanService;
	
//	private JPAContainer<Arinvoice> tableJpaContainer;	
	
	public BeanItem<Arinvoice> formBeanItem;	
	private List<Arinvoice> tableList;
	private BeanItemContainer<Arinvoice> tableBeanItemContainer;
	
	private BeanItemContainer<Division> beanItemContainerDivision;
	private BeanItemContainer<Salesman> beanItemContainerSalesman;
	
	private String operationStatus;
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllInvoice;
	private TransaksiHelper managerTransaksi = new TransaksiHelper();
	
	public PenandaanTTDModel(){
		initVariable();		
		initVariableData();
	}
	public void initVariable(){
		tableBeanItemContainer = new BeanItemContainer<Arinvoice>(Arinvoice.class);
		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
		beanItemContainerSalesman = new BeanItemContainer<Salesman>(Salesman.class);
		
		arInvoiceService = new ArInvoiceJpaServiceImpl();
		arpaymentHeaderService = new ArPaymentHeaderJpaServiceImpl();
		arpaymentDetailService = new ArPaymentDetailJpaServiceImpl();
		
		divisionService = new DivisionJpaServiceImpl();
		salesmanService = new SalesmanJpaServiceImpl();
		
	}
	
	public void initVariableData(){
		System.out.println("Init >> PenandaanKirimModel >> initData");		
		//TABLE
		tableList = new ArrayList<Arinvoice>();

//		tableJpaContainer =  JPAContainerFactory.make(Arinvoice.class, persistenceUnit);
		tableBeanItemContainer.addAll(arInvoiceService.findAll());
		setFilterDefaultBeanItemContainer();
		
		//COMBOBOX DIVISION
		beanItemContainerDivision.addAll(divisionService.findAll());
		//COMBO SALESMAN
		beanItemContainerSalesman.addAll(salesmanService.findAll());
		
	};

	public void setCurrentContainerUncheck(){

		Collection itemIds = tableBeanItemContainer.getItemIds();
		for (Object itemId: itemIds){
			tableBeanItemContainer.getItem(itemId).getBean().getSelected().setValue(false);
		}
	}
	
	public ArInvoiceSelected convertArInvoiceToSelected(Arinvoice arInvoice){
		ArInvoiceSelected item = new ArInvoiceSelected();
		
		item.setId(arInvoice.getId());
		item.setCustname(arInvoice.getCustname());
		item.setSpname(arInvoice.getSpname());
		item.setAmount(arInvoice.getAmount());
		item.setAmountpay(arInvoice.getAmountpay());
		item.setDisc1(arInvoice.getDisc1());
		item.setDisc2(arInvoice.getDisc2());
		item.setDisc3(arInvoice.getDisc3());
		item.setNopo(arInvoice.getNopo());
		item.setRecapno(arInvoice.getRecapno());
		item.setLockupdate(arInvoice.isLockupdate());
		item.setPpn(arInvoice.getPpn());
		item.setTerkirim(arInvoice.isTerkirim());
		item.setTerm(arInvoice.getTerm());
		item.setTertundacounter(arInvoice.getTertundacounter());
		item.setTunaikredit(item.getTunaikredit());
		item.setLunas(arInvoice.isLunas());
		item.setOrderdate(arInvoice.getOrderdate());
		item.setInvoicedate(arInvoice.getInvoicedate());
		item.setDuedate(arInvoice.getDuedate());
		item.setActualduedate(arInvoice.getActualduedate());
		item.setSalesmanBean(arInvoice.getSalesmanBean());
		item.setDivisionBean(arInvoice.getDivisionBean());
		item.setCustomerBean(arInvoice.getCustomerBean());
		
		item.setArpaymentDetailInvoices(arInvoice.getArpaymentDetailInvoices());
		item.setArpaymentDetailReturs(arInvoice.getArpaymentDetailReturs());
		
		//SELECTED >> DEFAULT FALSE (UNCHECK)
		item.getSelected().setValue(false);
		
		return item;
	}
	public void setFreshDataBeanItemContainer(){
		tableBeanItemContainer.removeAllItems();
		tableBeanItemContainer.removeAllContainerFilters();
		tableBeanItemContainer.addAll(arInvoiceService.findAll());

	}
	public void setFilterDefaultBeanItemContainer(){
		//FILTER KRITERIA:
		//1. Semua Invoice yang belum dilunasi
		//2. Bukan Canvas
		//3. Belum Terkirim
		Filter filter = new Not(new Compare.Equal("lunas", true));
		tableBeanItemContainer.addContainerFilter(filter);
		
		Filter filterNotCanvas = new Not(new SimpleStringFilter("tipejual", "C", true, false));
		tableBeanItemContainer.addContainerFilter(filterNotCanvas);
		
		Filter filterBelumTerkirim = new Compare.Equal("terkirim", false);
		tableBeanItemContainer.addContainerFilter(filterBelumTerkirim);
	}

	public Arinvoice getItem() {
		return item;
	}
	public void setItem(Arinvoice item) {
		this.item = item;
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
	public static String getPersistenceunit() {
		return persistenceUnit;
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
	public List<Arinvoice> getTableList() {
		return tableList;
	}
	public void setTableList(List<Arinvoice> tableList) {
		this.tableList = tableList;
	}
	
	public String getOperationStatus() {
		return operationStatus;
	}
	
	public BeanItemContainer<Arinvoice> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}
	public void setTableBeanItemContainer(
			BeanItemContainer<Arinvoice> tableBeanItemContainer) {
		this.tableBeanItemContainer = tableBeanItemContainer;
	}
	public SalesmanJpaService getSalesmanService() {
		return salesmanService;
	}
	public void setSalesmanService(SalesmanJpaService salesmanService) {
		this.salesmanService = salesmanService;
	}
	public BeanItemContainer<Salesman> getBeanItemContainerSalesman() {
		return beanItemContainerSalesman;
	}
	public void setBeanItemContainerSalesman(
			BeanItemContainer<Salesman> beanItemContainerSalesman) {
		this.beanItemContainerSalesman = beanItemContainerSalesman;
	}
	public boolean isSelectAllInvoice() {
		return selectAllInvoice;
	}
	public void setSelectAllInvoice(boolean selectAllInvoice) {
		this.selectAllInvoice = selectAllInvoice;
	}
	public TransaksiHelper getManagerTransaksi() {
		return managerTransaksi;
	}
	public void setManagerTransaksi(TransaksiHelper managerTransaksi) {
		this.managerTransaksi = managerTransaksi;
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

	
}
