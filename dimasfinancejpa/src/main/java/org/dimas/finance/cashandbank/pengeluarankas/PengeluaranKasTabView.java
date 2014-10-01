package org.dimas.finance.cashandbank.pengeluarankas;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class PengeluaranKasTabView extends CustomComponent{
	
	public PengeluaranKasTabView() {
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
		PengeluaranBankModel pengeluaranBankModel = new PengeluaranBankModel();
		PengeluaranBankView pengeluaranBankView = new PengeluaranBankView(pengeluaranBankModel);
		pengeluaranBankView.setSizeFull();
		new PengeluaranBankPresenter(pengeluaranBankModel, pengeluaranBankView);
		tab1.addComponent(pengeluaranBankView);
		tab1.setSizeFull();
		
//		VerticalLayout tab2 = new VerticalLayout();
//		PengeluaranKasBesarModel penerimaanSetoranModel2 = new PengeluaranKasBesarModel();
//		PengeluaranKasBesarView penerimaanSetoranView2 = new PengeluaranKasBesarView(penerimaanSetoranModel);
//		penerimaanSetoranView.setSizeFull();
//		new PengeluaranKasBesarPresenter(penerimaanSetoranModel, penerimaanSetoranView);
//		tab2.addComponent(penerimaanSetoranView);
//		tab2.setSizeFull();
//
//		VerticalLayout tab3 = new VerticalLayout();
//		PengeluaranKasBesarModel penerimaanSetoranModel3 = new PengeluaranKasBesarModel();
//		PengeluaranKasBesarView penerimaanSetoranView3 = new PengeluaranKasBesarView(penerimaanSetoranModel);
//		penerimaanSetoranView.setSizeFull();
//		new PengeluaranKasBesarPresenter(penerimaanSetoranModel, penerimaanSetoranView);
//		tab3.addComponent(penerimaanSetoranView);
//		tab3.setSizeFull();
	
		tabsheet.addTab(tab1, "Pengeluaran Bank",  null);
//		tabsheet.addTab(tab2, "Pengeluaran Kas Besar",  null);
//		tabsheet.addTab(tab3, "Pengeluaran Kas Kecil",  null);
		
		


		
	}
	

}
