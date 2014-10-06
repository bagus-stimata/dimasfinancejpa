package org.blitar.bawasda.simhp.mastertabel.temuan;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class TemuanTabView extends CustomComponent{
	
	public TemuanTabView() {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setCompositionRoot(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		VerticalLayout tab1 = new VerticalLayout();
		TabelTemuanKelompokModel tabelTemuanKelompokModel = new TabelTemuanKelompokModel();
		TabelTemuanKelompokView tabelTemuanKelompokView = new TabelTemuanKelompokView(tabelTemuanKelompokModel);
		tabelTemuanKelompokView.setSizeFull();
		new TabelTemuanKelompokPresenter(tabelTemuanKelompokModel, tabelTemuanKelompokView);
		tab1.addComponent(tabelTemuanKelompokView);
		tab1.setSizeFull();
		
		VerticalLayout tab2 = new VerticalLayout();
		TabelTemuanGrupModel tabelTemuanGrupModel = new TabelTemuanGrupModel();
		TabelTemuanGrupView tabelTemuanGrupView = new TabelTemuanGrupView(tabelTemuanGrupModel);
		tabelTemuanGrupView.setSizeFull();
		new TabelTemuanGrupPresenter(tabelTemuanGrupModel, tabelTemuanGrupView);
		tab2.addComponent(tabelTemuanGrupView);
		tab2.setSizeFull();

		VerticalLayout tab3 = new VerticalLayout();
		TabelTemuanModel tabelTemuanModel = new TabelTemuanModel();
		TabelTemuanView tabelTemuanView = new TabelTemuanView(tabelTemuanModel);
		tabelTemuanView.setSizeFull();
		new TabelTemuanPresenter(tabelTemuanModel, tabelTemuanView);
		tab3.addComponent(tabelTemuanView);
		tab3.setSizeFull();
		
		tabsheet.addTab(tab1, "Kelompok Temuan",  null);
		tabsheet.addTab(tab2, "Grup Temuan",  null);
		tabsheet.addTab(tab3, "Temuan",  null);
	
		
	}
	

}
