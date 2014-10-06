package org.blitar.bawasda.simhp.system;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class SystemMainView extends CssLayout implements View{
	
	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		addComponent(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		VerticalLayout tab1 = new VerticalLayout();
		ParameterSystemModel parameterSystemModel = new ParameterSystemModel();
		ParameterSystemView parameterSystemView = new ParameterSystemView(parameterSystemModel);
		parameterSystemView.setSizeFull();
		new ParameterSystemPresenter(parameterSystemModel, parameterSystemView);
		tab1.addComponent(parameterSystemView);
		tab1.setSizeFull();

		
		tabsheet.addTab(tab1, "PEMELIHARAAN PARAMETER SISTEM",  null);

		
	}
	

}
