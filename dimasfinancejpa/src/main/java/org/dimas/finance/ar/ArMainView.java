package org.dimas.finance.ar;

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

		VerticalLayout tab4 = new VerticalLayout();
		ArSummaryModel summaryArModel = new ArSummaryModel();
		ArSummaryView summaryArView = new ArSummaryView(summaryArModel);
		new ArSummaryPresenter(summaryArModel, summaryArView);
		summaryArView.setSizeFull();
		tab4.addComponent(summaryArView);
		tab4.setSizeFull();
		
		
		tabsheet.addTab(tab1, "AR Per Invoice",  null);
		tabsheet.addTab(tab2, "AR TO Tunai",  null);
		tabsheet.addTab(tab3, "Ar Canvas",  null);
		tabsheet.addTab(tab4, "Payment Summary",  null);


		
	}
	

}
