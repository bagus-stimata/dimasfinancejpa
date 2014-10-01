package org.dimas.finance.cashandbank.penerimaankas;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class PenerimaanKasTabView extends CustomComponent{
	
	public PenerimaanKasTabView() {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setCompositionRoot(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		// Create Account Tab
		//Tab GIRO
		VerticalLayout tab1 = new VerticalLayout();
		PenerimaanSetoranModel penerimaanSetoranModel = new PenerimaanSetoranModel();
		PenerimaanSetoranView penerimaanSetoranView = new PenerimaanSetoranView(penerimaanSetoranModel);
		penerimaanSetoranView.setSizeFull();
		new PenerimaanSetoranPresenter(penerimaanSetoranModel, penerimaanSetoranView);
		tab1.addComponent(penerimaanSetoranView);
		tab1.setSizeFull();
		
		VerticalLayout tab2 = new VerticalLayout();
		PenerimaanKasBesarModel penerimaanKasBesarModel = new PenerimaanKasBesarModel();
		PenerimaanKasBesarView penerimaanKasBesarView = new PenerimaanKasBesarView(penerimaanKasBesarModel);
		penerimaanKasBesarView.setSizeFull();
		new PenerimaanKasBesarPresenter(penerimaanKasBesarModel, penerimaanKasBesarView);
		tab2.addComponent(penerimaanKasBesarView);
		tab2.setSizeFull();

		VerticalLayout tab3 = new VerticalLayout();
		PenerimaanBankModel penerimaanBankModel = new PenerimaanBankModel();
		PenerimaanBankView penerimaanBankView = new PenerimaanBankView(penerimaanBankModel);
		penerimaanBankView.setSizeFull();
		new PenerimaanBankPresenter(penerimaanBankModel, penerimaanBankView);
		tab3.addComponent(penerimaanBankView);
		tab3.setSizeFull();
	
//		tabsheet.addTab(tab1, "Penerimaan Setoran Salesman",  null);
		tabsheet.addTab(tab2, "Penerimaan Kas Besar",  null);
		tabsheet.addTab(tab3, "Penerimaan Bank",  null);

//		BeanFieldGroupValidation test = new BeanFieldGroupValidation();
//		VerticalLayout tab4 = new VerticalLayout();
//		tab4.addComponent(test);
//		tabsheet.addTab(tab4, "Test Validation",  null);

		
	}
	

}
