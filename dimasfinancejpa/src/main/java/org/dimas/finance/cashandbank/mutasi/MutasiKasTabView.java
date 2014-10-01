package org.dimas.finance.cashandbank.mutasi;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class MutasiKasTabView extends CustomComponent{
	
	public MutasiKasTabView() {
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
		MutasiKasModel penerimaanSetoranModel = new MutasiKasModel();
		MutasiKasView penerimaanSetoranView = new MutasiKasView(penerimaanSetoranModel);
		penerimaanSetoranView.setSizeFull();
		new MutasiKasPresenter(penerimaanSetoranModel, penerimaanSetoranView);
		tab1.addComponent(penerimaanSetoranView);
		tab1.setSizeFull();
		
		VerticalLayout tab2 = new VerticalLayout();
		MutasiKasModel penerimaanSetoranModel2 = new MutasiKasModel();
		MutasiKasView penerimaanSetoranView2 = new MutasiKasView(penerimaanSetoranModel);
		penerimaanSetoranView.setSizeFull();
		new MutasiKasPresenter(penerimaanSetoranModel, penerimaanSetoranView);
		tab2.addComponent(penerimaanSetoranView);
		tab2.setSizeFull();

	
		tabsheet.addTab(tab1, "Mutasi Kas-Bank",  null);
		tabsheet.addTab(tab2, "Jurnal Manual",  null);
		
		


		
	}
	

}
