package org.blitar.bawasda.simhp.mastertabel.lembaga;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class LembagaTabView extends CustomComponent{
	
	public LembagaTabView() {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setCompositionRoot(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		VerticalLayout tab1 = new VerticalLayout();
		TabelLembagaTk1Model tabelLembagaTk1Model = new TabelLembagaTk1Model();
		TabelLembagaTk1View tabelLembagaTk1View = new TabelLembagaTk1View(tabelLembagaTk1Model);
		tabelLembagaTk1View.setSizeFull();
		new TabelLembagaTk1Presenter(tabelLembagaTk1Model, tabelLembagaTk1View);
		tab1.addComponent(tabelLembagaTk1View);
		tab1.setSizeFull();
		
		VerticalLayout tab2 = new VerticalLayout();
		TabelLembagaTk2Model tabelLembagaTk2Model = new TabelLembagaTk2Model();
		TabelLembagaTk2View tabelLembagaTk2View = new TabelLembagaTk2View(tabelLembagaTk2Model);
		tabelLembagaTk2View.setSizeFull();
		new TabelLembagaTk2Presenter(tabelLembagaTk2Model, tabelLembagaTk2View);
		tab2.addComponent(tabelLembagaTk2View);
		tab2.setSizeFull();

		VerticalLayout tab3 = new VerticalLayout();
		TabelLembagaTk3Model tabelLembagaTk3Model = new TabelLembagaTk3Model();
		TabelLembagaTk3View tabelLembagaTk3View = new TabelLembagaTk3View(tabelLembagaTk3Model);
		tabelLembagaTk3View.setSizeFull();
		new TabelLembagaTk3Presenter(tabelLembagaTk3Model, tabelLembagaTk3View);
		tab3.addComponent(tabelLembagaTk3View);
		tab3.setSizeFull();
		
		tabsheet.addTab(tab1, "Lembaga Tk I",  null);
		tabsheet.addTab(tab2, "Lembaga Tk II",  null);
		tabsheet.addTab(tab3, "Lembaga Tk III",  null);
	
		
	}
	

}
