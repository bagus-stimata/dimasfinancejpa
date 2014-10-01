package org.dimas.finance.systemsetting;

import org.dimas.finance.systemsetting.harikerja.PemeliharaanHariKerjaTabView;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class SetupSystemMainView extends CssLayout implements View{
	
	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		addComponent(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		// Create Account Tab
		//PARAMETER SYSTEM
		VerticalLayout tab1 = new VerticalLayout();
		ParameterSystemModel parameterSystemModel = new ParameterSystemModel();
		ParameterSystemView parameterSystemView = new ParameterSystemView(parameterSystemModel);
		parameterSystemView.setSizeFull();
		new ParameterSystemPresenter(parameterSystemModel, parameterSystemView);
		tab1.addComponent(parameterSystemView);
		tab1.setSizeFull();
		
		//MANAGEMENT USER ACCOUNT
		VerticalLayout tab2 = new VerticalLayout();
		UserAccountModel userAccountModel = new UserAccountModel();
		UserAccountView userAccountView = new UserAccountView(userAccountModel);
		userAccountView.setSizeFull();
		new UserAccountPresenter(userAccountModel, userAccountView);
		tab2.addComponent(userAccountView);
		tab2.setSizeFull();
		
		//SETUP CALENDER YANG DAKSANAKAN SETIAP AWAL PERIODE
		VerticalLayout tab3 = new VerticalLayout();
		PemeliharaanHariKerjaTabView pemeliharaanHariKerjaTabView = new PemeliharaanHariKerjaTabView();
		pemeliharaanHariKerjaTabView.setSizeFull();
		tab3.addComponent(pemeliharaanHariKerjaTabView);
		tab3.setSizeFull();

		//MODUL ATAU MENU
		VerticalLayout tab4 = new VerticalLayout();
		ModulModel modulModel = new ModulModel();
		ModulView modulView = new ModulView(modulModel);
		modulView.setSizeFull();
		new ModulPresenter(modulModel, modulView);
		tab4.addComponent(modulView);
		tab4.setSizeFull();
		

		//MODUL TEMPLATE (OTORISASI)
		VerticalLayout tab5 = new VerticalLayout();
		ModulTemplateModel modulTemplateModel = new ModulTemplateModel();
		ModulTemplateView modulTemplateView = new ModulTemplateView(modulTemplateModel);
		modulTemplateView.setSizeFull();
		new ModulTemplatePresenter(modulTemplateModel, modulTemplateView);
		tab5.addComponent(modulTemplateView);
		tab5.setSizeFull();

		
//		//MODUL SELECT (TEST)
//		VerticalLayout tab6 = new VerticalLayout();
//		ModulSelectModel modulSelectModel = new ModulSelectModel();
//		ModulSelectView modulSelectView = new ModulSelectView(modulSelectModel);
//		modulSelectView.setSizeFull();
//		new ModulSelectPresenter(modulSelectModel, modulSelectView);
//		tab6.addComponent(modulSelectView);
//		tab6.setSizeFull();
//		
//		//MODUL SELECT (TEST)
//		VerticalLayout tab7 = new VerticalLayout();
//		ModulTempHeaderSelectModel modulTempHeaderSelectModel = new ModulTempHeaderSelectModel();
//		ModulTempHeaderSelectView modulTempHeaderSelectView = new ModulTempHeaderSelectView(modulTempHeaderSelectModel);
//		modulTempHeaderSelectView.setSizeFull();
//		new ModulTempHeaderSelectPresenter(modulTempHeaderSelectModel, modulTempHeaderSelectView);
//		tab7.addComponent(modulTempHeaderSelectView);
//		tab7.setSizeFull();
		
		
		tabsheet.addTab(tab1, "Parameter System",  null);
		tabsheet.addTab(tab2, "User Account",  null);
		tabsheet.addTab(tab3, "Setup Calender(Tahunan)",  null);
		tabsheet.addTab(tab4, "Modul/Menu System",  null);
		tabsheet.addTab(tab5, "Modul/Menu Template",  null);

//		tabsheet.addTab(tab6, "Select Modul",  null);
//		tabsheet.addTab(tab7, "Select Header Template",  null);
		
	}
	

}
