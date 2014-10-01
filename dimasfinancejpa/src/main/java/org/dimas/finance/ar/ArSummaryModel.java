package org.dimas.finance.ar;

import org.dimas.finance.jpa.dao.ArInvoiceJpaService;
import org.dimas.finance.jpa.dao.ArInvoiceJpaServiceImpl;
import org.dimas.finance.jpa.dao.ArPaymentDetailJpaService;
import org.dimas.finance.jpa.dao.ArPaymentDetailJpaServiceImpl;
import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaService;
import org.dimas.finance.jpa.dao.ArPaymentHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.AreaJpaService;
import org.dimas.finance.jpa.dao.AreaJpaServiceImpl;
import org.dimas.finance.jpa.dao.CustomerJpaService;
import org.dimas.finance.jpa.dao.CustomerJpaServiceImpl;
import org.dimas.finance.jpa.dao.DivisionJpaService;
import org.dimas.finance.jpa.dao.DivisionJpaServiceImpl;
import org.dimas.finance.jpa.dao.SalesmanJpaService;
import org.dimas.finance.jpa.dao.SalesmanJpaServiceImpl;
import org.dimas.finance.model.SummaryModel;
import com.vaadin.data.util.BeanItemContainer;

public class ArSummaryModel {

	private ArInvoiceJpaService arInvoiceService; 
	private ArPaymentHeaderJpaService arpaymentHeaderService;
	private ArPaymentDetailJpaService arpaymentDetailService;
	
	private DivisionJpaService divisionService;
	private AreaJpaService areaService;
	private SalesmanJpaService salesmanService;
	private CustomerJpaService customerService;
	
	
	private BeanItemContainer<SummaryModel> beanItemContainerSummaryModel;
	
	public ArSummaryModel(){
		initVariable();		
		initVariableData();
		
	}
	public void initVariable(){
		beanItemContainerSummaryModel = new BeanItemContainer<SummaryModel>(SummaryModel.class);
		
		arInvoiceService = new ArInvoiceJpaServiceImpl();
		arpaymentHeaderService = new ArPaymentHeaderJpaServiceImpl();
		arpaymentDetailService = new ArPaymentDetailJpaServiceImpl();
		
		divisionService = new DivisionJpaServiceImpl();
		areaService = new AreaJpaServiceImpl();
		salesmanService = new SalesmanJpaServiceImpl();
		customerService = new CustomerJpaServiceImpl();
		
	}
	public void initVariableData(){
		
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
	public DivisionJpaService getDivisionService() {
		return divisionService;
	}
	public void setDivisionService(DivisionJpaService divisionService) {
		this.divisionService = divisionService;
	}
	public AreaJpaService getAreaService() {
		return areaService;
	}
	public void setAreaService(AreaJpaService areaService) {
		this.areaService = areaService;
	}
	public SalesmanJpaService getSalesmanService() {
		return salesmanService;
	}
	public void setSalesmanService(SalesmanJpaService salesmanService) {
		this.salesmanService = salesmanService;
	}
	public CustomerJpaService getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerJpaService customerService) {
		this.customerService = customerService;
	}
	public BeanItemContainer<SummaryModel> getBeanItemContainerSummaryModel() {
		return beanItemContainerSummaryModel;
	}
	public void setBeanItemContainerSummaryModel(
			BeanItemContainer<SummaryModel> beanItemContainerSummaryModel) {
		this.beanItemContainerSummaryModel = beanItemContainerSummaryModel;
	}
	
	
	
	
	
}
