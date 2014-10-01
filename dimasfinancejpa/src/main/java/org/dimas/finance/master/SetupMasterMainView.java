package org.dimas.finance.master;

import org.dimas.finance.model.Region;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class SetupMasterMainView extends CssLayout implements View{
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		addComponent(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
//		// Create Account Tab
		//Tab Master Region
		VerticalLayout tab1 = new VerticalLayout();
		RegionModel regionModel = new RegionModel();
		RegionView regionView  = new RegionView(regionModel);		    
		// The presenter binds the model and view together
		new RegionPresenter(regionModel, regionView);		
		regionView.setSizeFull();
		tab1.addComponent(regionView);
		tab1.setSizeFull();
		
		VerticalLayout tab2 = new VerticalLayout();
		AreaModel areaModel = new AreaModel();
		AreaView areaView = new AreaView(areaModel);
		new AreaPresenter(areaModel, areaView);
		areaView.setSizeFull();
		tab2.addComponent(areaView);
		tab2.setSizeFull();
		
		//DIVISION
		VerticalLayout tab3 = new VerticalLayout();
		DivisionModel divisionModel = new DivisionModel();
		DivisionView divisionView = new DivisionView(divisionModel);
		new DivisionPresenter(divisionModel, divisionView);
		divisionView.setSizeFull();
		tab3.addComponent(divisionView);
		tab3.setSizeFull();
		
		//DEPARMENT
		VerticalLayout tab4 = new VerticalLayout();
		DepartementModel departementModel = new DepartementModel();
		DepartementView departementView = new DepartementView(departementModel);
		new DepartementPresenter(departementModel, departementView);
		departementView.setSizeFull();
		tab4.addComponent(departementView);
		tab4.setSizeFull();

		//ACCOUNT GROUP REPORT
		VerticalLayout tab5 = new VerticalLayout();
		AccountGroupReportModel accountGroupReportModel = new AccountGroupReportModel();
		AccountGroupReportView accountGroupReportView = new AccountGroupReportView(accountGroupReportModel);
		new AccountGroupReportPresenter(accountGroupReportModel, accountGroupReportView);
		accountGroupReportView.setSizeFull();
		tab5.addComponent(accountGroupReportView);
		tab5.setSizeFull();
		
		//ACCOUNT GROUP
		VerticalLayout tab6 = new VerticalLayout();
		AccountGroupModel accountGroupModel = new AccountGroupModel();
		AccountGroupView accountGroupView = new AccountGroupView(accountGroupModel);
		new AccountGroupPresenter(accountGroupModel, accountGroupView);
		accountGroupView.setSizeFull();
		tab6.addComponent(accountGroupView);
		tab6.setSizeFull();
		
		//ACCOUNT GROUP
		VerticalLayout tab7 = new VerticalLayout();
		AccountModel accountModel = new AccountModel();
		AccountView accountView = new AccountView(accountModel);
		new AccountPresenter(accountModel, accountView);
		accountGroupView.setSizeFull();
		tab7.addComponent(accountView);
		tab7.setSizeFull();
		
		//ACCOUNT GROUP
		VerticalLayout tab8 = new VerticalLayout();
		SalesmanModel salesmanModel = new SalesmanModel();
		SalesmanView salesmanView = new SalesmanView(salesmanModel);
		new SalesmanPresenter(salesmanModel, salesmanView);
		salesmanView.setSizeFull();
		tab8.addComponent(salesmanView);	
		tab8.setSizeFull();
		
		//CUSTOMER
		VerticalLayout tab9 = new VerticalLayout();
		CustomerModel customerModel = new CustomerModel();
		CustomerView customerView = new CustomerView(customerModel);
		new CustomerPresenter(customerModel, customerView);
		customerView.setSizeFull();
		tab9.addComponent(customerView);	
		tab9.setSizeFull();

		//BANK
		VerticalLayout tab10 = new VerticalLayout();
		BankModel bankModel = new BankModel();
		BankView bankView = new BankView(bankModel);
		new BankPresenter(bankModel, bankView);
		bankView.setSizeFull();
		tab10.addComponent(bankView);	
		tab10.setSizeFull();

		//BKB
		VerticalLayout tab11 = new VerticalLayout();
		BkbtranstypeModel bkbtranstypeModel = new BkbtranstypeModel();
		BkbtranstypeView bkbtranstypeView = new BkbtranstypeView(bkbtranstypeModel);
		new BkbtranstypePresenter(bkbtranstypeModel, bkbtranstypeView);
		bkbtranstypeView.setSizeFull();
		tab11.addComponent(bkbtranstypeView);	
		tab11.setSizeFull();
		
		//BKK
		VerticalLayout tab12 = new VerticalLayout();
		BkktranstypeModel bkktranstypeModel = new BkktranstypeModel();
		BkktranstypeView bkktranstypeView = new BkktranstypeView(bkktranstypeModel);
		new BkktranstypePresenter(bkktranstypeModel, bkktranstypeView);
		bkktranstypeView.setSizeFull();
		tab12.addComponent(bkktranstypeView);	
		tab12.setSizeFull();
		
		//BKK
		VerticalLayout tab13 = new VerticalLayout();
		BbanktranstypeModel bbanktranstypeModel = new BbanktranstypeModel();
		BbanktranstypeView bbanktranstypeView = new BbanktranstypeView(bbanktranstypeModel);
		new BbanktranstypePresenter(bbanktranstypeModel, bbanktranstypeView);
		bbanktranstypeView.setSizeFull();
		tab13.addComponent(bbanktranstypeView);	
		tab13.setSizeFull();
		
		tabsheet.addTab(tab1, "Region",  null);
		tabsheet.addTab(tab2, "Area",  null);
		tabsheet.addTab(tab3, "Division",  null);
		tabsheet.addTab(tab4, "Departement",  null);
		tabsheet.addTab(tab5, "Acc. Group Report",  null);
		tabsheet.addTab(tab6, "Acc. Group",  null);
		tabsheet.addTab(tab7, "Account",  null);
		tabsheet.addTab(tab8, "Salesman",  null);
		tabsheet.addTab(tab9, "Customer/Outlet",  null);
		tabsheet.addTab(tab10, "Bank",  null);
		tabsheet.addTab(tab11, "BKB Tipe Transaksi",  null);
		tabsheet.addTab(tab12, "BKK Tipe Transaksi",  null);
		tabsheet.addTab(tab13, "BBANK Tipe Transaksi",  null);
		
	}
	

}
