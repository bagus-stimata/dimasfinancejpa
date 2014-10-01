package org.dimas.finance.accounting;

import org.dimas.finance.cashandbank.BukuGiroModel;
import org.dimas.finance.cashandbank.BukuGiroPresenter;
import org.dimas.finance.cashandbank.BukuGiroView;
import org.dimas.finance.cashandbank.GiroBlongModel;
import org.dimas.finance.cashandbank.GiroBlongPresenter;
import org.dimas.finance.cashandbank.GiroBlongView;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class AccountingMainView extends CssLayout implements View{
	
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
//		//Tab GIRO
//		VerticalLayout tab1 = new VerticalLayout();
//		BukuGiroModel bukuGiroModel = new BukuGiroModel();
//		BukuGiroView bukuGiroView = new BukuGiroView(bukuGiroModel);
//		bukuGiroView.setSizeFull();
//		new BukuGiroPresenter(bukuGiroModel, bukuGiroView);
//		tab1.addComponent(bukuGiroView);
//		tab1.setSizeFull();
//		
//		//Tab GIRO
//		VerticalLayout tab2 = new VerticalLayout();
//		GiroBlongModel giroBlongModel = new GiroBlongModel();
//		GiroBlongView giroBlongView = new GiroBlongView(giroBlongModel);
//		giroBlongView.setSizeFull();
//		new GiroBlongPresenter(giroBlongModel, giroBlongView);
//		tab2.addComponent(giroBlongView);
//		tab2.setSizeFull();
//		
//		tabsheet.addTab(tab1, "Buku Giro",  null);
//		tabsheet.addTab(tab2, "Giro Blong",  null);
//
		
	}
	

}
