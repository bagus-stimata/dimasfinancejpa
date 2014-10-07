package org.dimas.finance.warehouse;

import org.dimas.finance.ar.kredittunai.CustomerCreditModel;
import org.dimas.finance.ar.kredittunai.CustomerCreditPresenter;
import org.dimas.finance.ar.kredittunai.CustomerCreditView;
import org.dimas.finance.warehouse.tunaiorkredit.PenandaanTunaiOrKreditModel;
import org.dimas.finance.warehouse.tunaiorkredit.PenandaanTunaiOrKreditPresenter;
import org.dimas.finance.warehouse.tunaiorkredit.PenandaanTunaiOrKreditView;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class WarehouseMainView extends CssLayout implements View{
	
	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		addComponent(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		//PENANDAAN TERKIRIM
		VerticalLayout tab1 = new VerticalLayout();
		PenandaanKirimModel penandaanKirimModel = new PenandaanKirimModel();
		PenandaanKirimView penandaanKirimView = new PenandaanKirimView(penandaanKirimModel);
		new PenandaanKirimPresenter(penandaanKirimModel, penandaanKirimView);
		penandaanKirimView.setSizeFull();
		tab1.addComponent(penandaanKirimView);
		tab1.setSizeFull();
		
		//PENANDAAN TERKIRIM
		VerticalLayout tab2 = new VerticalLayout();
		PenandaanTTDModel penandaanTTDModel = new PenandaanTTDModel();
		PenandaanTTDView penandaanTTDView = new PenandaanTTDView(penandaanTTDModel);
		new PenandaanTTDPresenter(penandaanTTDModel, penandaanTTDView);
		penandaanTTDView.setSizeFull();
		tab2.addComponent(penandaanTTDView);
		tab2.setSizeFull();
		
		//PENANDAAN TUNAI OR KREDIT
		VerticalLayout tab3 = new VerticalLayout();
		PenandaanTunaiOrKreditModel penandaanTuaniOrKreditModel = new PenandaanTunaiOrKreditModel();
		PenandaanTunaiOrKreditView penandaanTunaiOrKreditView = new PenandaanTunaiOrKreditView(penandaanTuaniOrKreditModel);
		new PenandaanTunaiOrKreditPresenter(penandaanTuaniOrKreditModel, penandaanTunaiOrKreditView);
		penandaanTunaiOrKreditView.setSizeFull();
		tab3.addComponent(penandaanTunaiOrKreditView);
		tab3.setSizeFull();

		//SUMMARY PENGIRIMAN
		VerticalLayout tab4 = new VerticalLayout();
		SummaryPengirimanModel summaryPengirimanModel = new SummaryPengirimanModel();
		SummaryPengirimanView summaryPengirimanView = new SummaryPengirimanView(summaryPengirimanModel);
		new SummaryPengirimanPresenter(summaryPengirimanModel, summaryPengirimanView);
		summaryPengirimanView.setSizeFull();
		tab4.addComponent(summaryPengirimanView);
		tab4.setSizeFull();
		
		tabsheet.addTab(tab1, "Penandaan TERKIRIM",  null);
		tabsheet.addTab(tab2, "TERTUNDA",  null);
		tabsheet.addTab(tab3, "Penandaan TUNAI-KREDIT",  null);
		tabsheet.addTab(tab4, "Summary Pengiriman",  null);
		

		
	}
	

}
