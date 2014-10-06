package org.blitar.bawasda.simhp.mastertabel.lain;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class LainLainTabView extends CustomComponent{
	
	public LainLainTabView() {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setCompositionRoot(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();

		VerticalLayout tab1 = new VerticalLayout();
		TabelJenisKerugianModel tabelJenisKerugianModel = new TabelJenisKerugianModel();
		TabelJenisKerugianView tabelJenisKerugianView = new TabelJenisKerugianView(tabelJenisKerugianModel);
		tabelJenisKerugianView.setSizeFull();
		new TabelJenisKerugianPresenter(tabelJenisKerugianModel, tabelJenisKerugianView);
		tab1.addComponent(tabelJenisKerugianView);
		tab1.setSizeFull();

		VerticalLayout tab2 = new VerticalLayout();
		MataUangModel mataUangModel = new MataUangModel();
		MataUangView mataUangView = new MataUangView(mataUangModel);
		mataUangView.setSizeFull();
		new MataUangPresenter(mataUangModel, mataUangView);
		tab2.addComponent(mataUangView);
		tab2.setSizeFull();

		tabsheet.addTab(tab1, "Jenis Kerugian",  null);
		tabsheet.addTab(tab2, "Mata Uang",  null);
	
		
	}
	

}
