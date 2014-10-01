package org.dimas.finance.cashandbank;

import org.dimas.finance.cashandbank.mutasi.MutasiKasTabView;
import org.dimas.finance.cashandbank.penerimaankas.PenerimaanKasTabView;
import org.dimas.finance.cashandbank.pengeluarankas.PengeluaranKasTabView;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class CashAndBankMainView extends CssLayout implements View{
	
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
		//Tab GIRO
		VerticalLayout tab1 = new VerticalLayout();
		BukuGiroModel bukuGiroModel = new BukuGiroModel();
		BukuGiroView bukuGiroView = new BukuGiroView(bukuGiroModel);
		bukuGiroView.setSizeFull();
		new BukuGiroPresenter(bukuGiroModel, bukuGiroView);
		tab1.addComponent(bukuGiroView);
		tab1.setSizeFull();
		
		//Tab GIRO
		VerticalLayout tab2 = new VerticalLayout();
		GiroBlongModel giroBlongModel = new GiroBlongModel();
		GiroBlongView giroBlongView = new GiroBlongView(giroBlongModel);
		giroBlongView.setSizeFull();
		new GiroBlongPresenter(giroBlongModel, giroBlongView);
		tab2.addComponent(giroBlongView);
		tab2.setSizeFull();
		
		//Tab GIRO
		VerticalLayout tab3 = new VerticalLayout();
		BukuTransferModel bukuTransferModel = new BukuTransferModel();
		BukuTransferView bukuTransferView = new BukuTransferView(bukuTransferModel);
		bukuTransferView.setSizeFull();
		new BukuTransferPresenter(bukuTransferModel, bukuTransferView);
		tab3.addComponent(bukuTransferView);
		tab3.setSizeFull();
		
		//PENERIMAAN KAS
		VerticalLayout tab4 = new VerticalLayout();
		PenerimaanKasTabView penerimaanKasTabView = new PenerimaanKasTabView(); 
		penerimaanKasTabView.setSizeFull();
		tab4.addComponent(penerimaanKasTabView);
		tab4.setSizeFull();
		
		//PENGELUARAN KAS
		VerticalLayout tab5 = new VerticalLayout();
		PengeluaranKasTabView pengeluaranKasTabView = new PengeluaranKasTabView(); 
		pengeluaranKasTabView.setSizeFull();
		tab5.addComponent(pengeluaranKasTabView);
		tab5.setSizeFull();

		//PENGELUARAN KAS
		VerticalLayout tab6 = new VerticalLayout();
		MutasiKasTabView mutasiKasTabView = new MutasiKasTabView(); 
		mutasiKasTabView.setSizeFull();
		tab6.addComponent(mutasiKasTabView);
		tab6.setSizeFull();

		tabsheet.addTab(tab1, "Buku Giro",  null);
		tabsheet.addTab(tab2, "Giro Blong",  null);
		tabsheet.addTab(tab3, "Buku Transfer",  null);
		
		tabsheet.addTab(tab4, "Penerimaan Kas/Bank",  null);
		tabsheet.addTab(tab5, "Pengeluaran Kas/Bank",  null);
		tabsheet.addTab(tab6, "Mutasi dan Jurnal Manual",  null);
		


		
	}
	

}
