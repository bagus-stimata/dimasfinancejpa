package org.blitar.bawasda.simhp.mastertabel.rekomendasi;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class RekomendasiTabView extends CustomComponent{
	
	public RekomendasiTabView() {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setCompositionRoot(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		VerticalLayout tab1 = new VerticalLayout();
		TabelRekomendasiGrupModel tabelTemuanKelompokModel = new TabelRekomendasiGrupModel();
		TabelRekomendasiGrupView tabelTemuanKelompokView = new TabelRekomendasiGrupView(tabelTemuanKelompokModel);
		tabelTemuanKelompokView.setSizeFull();
		new TabelRekomendasiGrupPresenter(tabelTemuanKelompokModel, tabelTemuanKelompokView);
		tab1.addComponent(tabelTemuanKelompokView);
		tab1.setSizeFull();
		
		VerticalLayout tab2 = new VerticalLayout();
		TabelRekomendasiModel tabelTemuanGrupModel = new TabelRekomendasiModel();
		TabelRekomendasiView tabelTemuanGrupView = new TabelRekomendasiView(tabelTemuanGrupModel);
		tabelTemuanGrupView.setSizeFull();
		new TabelRekomendasiPresenter(tabelTemuanGrupModel, tabelTemuanGrupView);
		tab2.addComponent(tabelTemuanGrupView);
		tab2.setSizeFull();

		tabsheet.addTab(tab1, "Grup Rekomendasi",  null);
		tabsheet.addTab(tab2, "Rekomendasi",  null);
	
		
	}
	

}
