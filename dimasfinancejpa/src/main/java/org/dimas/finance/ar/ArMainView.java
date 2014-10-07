package org.dimas.finance.ar;

import org.dimas.finance.ar.canvas.PelunasanCanvasModel;
import org.dimas.finance.ar.canvas.PelunasanCanvasPresenter;
import org.dimas.finance.ar.canvas.PelunasanCanvasView;
import org.dimas.finance.ar.jatuhtempomanual.JatuhTempoManualModel;
import org.dimas.finance.ar.jatuhtempomanual.JatuhTempoManualPresenter;
import org.dimas.finance.ar.jatuhtempomanual.JatuhTempoManualView;
import org.dimas.finance.ar.kredittunai.CustomerCreditModel;
import org.dimas.finance.ar.kredittunai.CustomerCreditPresenter;
import org.dimas.finance.ar.kredittunai.CustomerCreditView;
import org.dimas.finance.ar.tunai.PelunasanTunaiModel;
import org.dimas.finance.ar.tunai.PelunasanTunaiPresenter;
import org.dimas.finance.ar.tunai.PelunasanTunaiView;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class ArMainView extends CssLayout implements View{

	private static final long serialVersionUID = 1L;

	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		addComponent(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		//Customer Credit
		VerticalLayout tab1 = new VerticalLayout();
		CustomerCreditModel customerCreditModel = new CustomerCreditModel();
		CustomerCreditView customerCreditView = new CustomerCreditView(customerCreditModel);
		new CustomerCreditPresenter(customerCreditModel, customerCreditView);
		customerCreditView.setSizeFull();
		tab1.addComponent(customerCreditView);
		tab1.setSizeFull();
		
		//PAYMENT TO TUNAI
		VerticalLayout tab2 = new VerticalLayout();
		PelunasanTunaiModel pelunasanTunaiModel = new PelunasanTunaiModel();
		PelunasanTunaiView pelunasanTunaiView = new PelunasanTunaiView(pelunasanTunaiModel);
		new PelunasanTunaiPresenter(pelunasanTunaiModel, pelunasanTunaiView);
		pelunasanTunaiView.setSizeFull();
		tab2.addComponent(pelunasanTunaiView);
		tab2.setSizeFull();

		//PAYMENT CANVAS
		VerticalLayout tab3 = new VerticalLayout();
		PelunasanCanvasModel pelunasanCanvasModel = new PelunasanCanvasModel();
		PelunasanCanvasView pelunasanCanvasView = new PelunasanCanvasView(pelunasanCanvasModel);
		new PelunasanCanvasPresenter(pelunasanCanvasModel, pelunasanCanvasView);
		pelunasanCanvasView.setSizeFull();
		tab3.addComponent(pelunasanCanvasView);
		tab3.setSizeFull();

		//PAYMENT CANVAS
		VerticalLayout tab4 = new VerticalLayout();
		JatuhTempoManualModel jatuhTempoManualModel = new JatuhTempoManualModel();
		JatuhTempoManualView jatuhTempoManualView = new JatuhTempoManualView(jatuhTempoManualModel);
		new JatuhTempoManualPresenter(jatuhTempoManualModel, jatuhTempoManualView);
		jatuhTempoManualView.setSizeFull();
		tab4.addComponent(jatuhTempoManualView);
		tab4.setSizeFull();

		
		VerticalLayout tab5 = new VerticalLayout();
		ArSummaryModel summaryArModel = new ArSummaryModel();
		ArSummaryView summaryArView = new ArSummaryView(summaryArModel);
		new ArSummaryPresenter(summaryArModel, summaryArView);
		summaryArView.setSizeFull();
		tab5.addComponent(summaryArView);
		tab5.setSizeFull();
		
		
		tabsheet.addTab(tab1, "AR Per Invoice",  null);
		tabsheet.addTab(tab2, "AR TO Tunai",  null);
		tabsheet.addTab(tab3, "Ar Canvas",  null);
		tabsheet.addTab(tab4, "Jatuh Tempo Manual",  null);
		tabsheet.addTab(tab5, "Payment Summary",  null);


		
	}
	

}
