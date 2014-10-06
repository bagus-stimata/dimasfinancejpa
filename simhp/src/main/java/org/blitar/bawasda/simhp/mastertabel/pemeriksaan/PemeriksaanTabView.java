package org.blitar.bawasda.simhp.mastertabel.pemeriksaan;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class PemeriksaanTabView extends CustomComponent{
	
	public PemeriksaanTabView() {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setCompositionRoot(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		VerticalLayout tab1 = new VerticalLayout();
		TabelPemeriksaanGrupModel tabelTemuanKelompokModel = new TabelPemeriksaanGrupModel();
		TabelPemeriksaanGrupView tabelTemuanKelompokView = new TabelPemeriksaanGrupView(tabelTemuanKelompokModel);
		tabelTemuanKelompokView.setSizeFull();
		new TabelPemeriksaanGrupPresenter(tabelTemuanKelompokModel, tabelTemuanKelompokView);
		tab1.addComponent(tabelTemuanKelompokView);
		tab1.setSizeFull();
		
		VerticalLayout tab2 = new VerticalLayout();
		TabelPemeriksaanModel tabelTemuanGrupModel = new TabelPemeriksaanModel();
		TabelPemeriksaanView tabelTemuanGrupView = new TabelPemeriksaanView(tabelTemuanGrupModel);
		tabelTemuanGrupView.setSizeFull();
		new TabelPemeriksaanPresenter(tabelTemuanGrupModel, tabelTemuanGrupView);
		tab2.addComponent(tabelTemuanGrupView);
		tab2.setSizeFull();

		VerticalLayout tab3 = new VerticalLayout();
		TabelRuangLingkupPemeriksaanModel tabelRuangLingkupPemeriksaanModel = new TabelRuangLingkupPemeriksaanModel();
		TabelRuangLingkupPemeriksaanView tabelRuangLingkupPemeriksaanView = new TabelRuangLingkupPemeriksaanView(tabelRuangLingkupPemeriksaanModel);
		tabelRuangLingkupPemeriksaanView.setSizeFull();
		new TabelRuangLingkupPemeriksaanPresenter(tabelRuangLingkupPemeriksaanModel, tabelRuangLingkupPemeriksaanView);
		tab3.addComponent(tabelRuangLingkupPemeriksaanView);
		tab3.setSizeFull();

		tabsheet.addTab(tab1, "Grup Jenis Pemeriksaan",  null);
		tabsheet.addTab(tab2, "Jenis Pemeriksaan",  null);
		tabsheet.addTab(tab3, "Ruang Lingkup Pemeriksaan",  null);
	
		
	}
	

}
