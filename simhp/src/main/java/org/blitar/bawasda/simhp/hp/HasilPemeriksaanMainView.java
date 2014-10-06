package org.blitar.bawasda.simhp.hp;

import org.blitar.bawasda.simhp.dummy.TestModel;
import org.blitar.bawasda.simhp.dummy.TestPresenter;
import org.blitar.bawasda.simhp.dummy.TestView;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class HasilPemeriksaanMainView extends CssLayout implements View{
	
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
		TestModel testModel = new TestModel();
		TestView testView = new TestView(testModel);
		testView.setSizeFull();
		new TestPresenter(testModel, testView);
		tab1.addComponent(testView);
		tab1.setSizeFull();
		
		tabsheet.addTab(tab1, "Hasil Pemeriksaan",  null);
		


		
	}
	

}
