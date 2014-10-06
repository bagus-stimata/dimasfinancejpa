package org.blitar.bawasda.simhp.mastertabel.tindaklanjut;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class TindakLanjutTabView extends CustomComponent{
	
	public TindakLanjutTabView() {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setCompositionRoot(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		VerticalLayout tab1 = new VerticalLayout();
		TabelTindakLanjutGrupModel tabelTemuanKelompokModel = new TabelTindakLanjutGrupModel();
		TabelTindakLanjutGrupView tabelTemuanKelompokView = new TabelTindakLanjutGrupView(tabelTemuanKelompokModel);
		tabelTemuanKelompokView.setSizeFull();
		new TabelTindakLanjutGrupPresenter(tabelTemuanKelompokModel, tabelTemuanKelompokView);
		tab1.addComponent(tabelTemuanKelompokView);
		tab1.setSizeFull();
		
		VerticalLayout tab2 = new VerticalLayout();
		TabelTindakLanjutModel tabelTemuanGrupModel = new TabelTindakLanjutModel();
		TabelTindakLanjutView tabelTemuanGrupView = new TabelTindakLanjutView(tabelTemuanGrupModel);
		tabelTemuanGrupView.setSizeFull();
		new TabelTindakLanjutPresenter(tabelTemuanGrupModel, tabelTemuanGrupView);
		tab2.addComponent(tabelTemuanGrupView);
		tab2.setSizeFull();

		tabsheet.addTab(tab1, "Grup Tindak Lanjut",  null);
		tabsheet.addTab(tab2, "Tindak Lanjut",  null);
	
		
	}
	

}
