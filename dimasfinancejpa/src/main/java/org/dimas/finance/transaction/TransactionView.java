package org.dimas.finance.transaction;

import org.eclipse.jdt.internal.core.SetContainerOperation;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class TransactionView extends CssLayout implements View{

	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		addComponent(layout);
		
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
//		// Create the first tab
//		VerticalLayout tab1 = new VerticalLayout();
//		DummyCustomComponent dummy = new DummyCustomComponent();
//		tab1.addComponent(dummy);
//		tab1.setSizeFull();
//		tabsheet.addTab(tab1, "Account (Perkiraan)",  null);
//		
//		VerticalLayout tab2 = new VerticalLayout();
//		CollectionContainerSimple dummy2 = new CollectionContainerSimple();
//		tab2.addComponent(dummy2);
//		tab2.setSizeFull();
//		tabsheet.addTab(tab2, "Grup Account",  null);
//		
////		tab1.addComponent(new Embedded(null,
////		        new ThemeResource("img/planets/Mercury.jpg")));
////		tabsheet.addTab(tab1, "Mercury",
////		        new ThemeResource("img/planets/Mercury_symbol.png"));
//
//		// This tab gets its caption from the component caption
////		VerticalLayout tab2 = new VerticalLayout();
////		tab2.addComponent(new Embedded(null,
////		        new ThemeResource("img/planets/Venus.jpg")));
////		tab2.setCaption("Venus");
////		tabsheet.addTab(tab2).setIcon(
////		        new ThemeResource("img/planets/Venus_symbol.png"));		
		
		
	}

}
