package org.dimas.finance.systemsetting.harikerja;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class PemeliharaanHariKerjaTabView extends CustomComponent{
	
	public PemeliharaanHariKerjaTabView() {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setCompositionRoot(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		// Create Account Tab
		//PERIODE
		VerticalLayout tab1 = new VerticalLayout();
		PeriodeModel periodeModel = new PeriodeModel();
		PeriodeView periodeView = new PeriodeView(periodeModel);
		periodeView.setSizeFull();
		new PeriodePresenter(periodeModel, periodeView);
		tab1.addComponent(periodeView);
		tab1.setSizeFull();

		//PERIODE
		VerticalLayout tab2 = new VerticalLayout();
		PekanModel pekanModel = new PekanModel();
		PekanView pekanView = new PekanView(pekanModel);
		pekanView.setSizeFull();
		new PekanPresenter(pekanModel, pekanView);
		tab2.addComponent(pekanView);
		tab2.setSizeFull();
		
		//PERIODE
		VerticalLayout tab3 = new VerticalLayout();
		HariKerjaModel hariKerjaModel = new HariKerjaModel();
		HariKerjaView hariKerjaView = new HariKerjaView(hariKerjaModel);
		hariKerjaView.setSizeFull();
		new HariKerjaPresenter(hariKerjaModel, hariKerjaView);
		tab3.addComponent(hariKerjaView);
		tab3.setSizeFull();
		
		tabsheet.addTab(tab1, "PERIODE",  null);
		tabsheet.addTab(tab2, "PEKAN",  null);
		tabsheet.addTab(tab3, "Hari Kerja",  null);
//		tabsheet.addTab(tab3, "Penerimaan Bank",  null);

		
	}
	

}
