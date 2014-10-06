package org.blitar.bawasda.simhp.mastertabel;

import org.blitar.bawasda.simhp.dummy.TestModel;
import org.blitar.bawasda.simhp.dummy.TestPresenter;
import org.blitar.bawasda.simhp.dummy.TestView;
import org.blitar.bawasda.simhp.mastertabel.lain.LainLainTabView;
import org.blitar.bawasda.simhp.mastertabel.lembaga.LembagaTabView;
import org.blitar.bawasda.simhp.mastertabel.pejabat.PejabatTabView;
import org.blitar.bawasda.simhp.mastertabel.pemeriksaan.PemeriksaanTabView;
import org.blitar.bawasda.simhp.mastertabel.rekomendasi.RekomendasiTabView;
import org.blitar.bawasda.simhp.mastertabel.temuan.TemuanTabView;
import org.blitar.bawasda.simhp.mastertabel.temuan.TabelTemuanGrupModel;
import org.blitar.bawasda.simhp.mastertabel.temuan.TabelTemuanGrupPresenter;
import org.blitar.bawasda.simhp.mastertabel.temuan.TabelTemuanGrupView;
import org.blitar.bawasda.simhp.mastertabel.tindaklanjut.TindakLanjutTabView;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class MasterTabelMainView extends CssLayout implements View{
	
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
		TemuanTabView  temuanTabView = new TemuanTabView();
		temuanTabView.setSizeFull();
		tab1.setSizeFull();
		tab1.addComponent(temuanTabView);

		VerticalLayout tab2 = new VerticalLayout();
		RekomendasiTabView  rekomendasiTabView = new RekomendasiTabView();
		rekomendasiTabView.setSizeFull();
		tab2.setSizeFull();
		tab2.addComponent(rekomendasiTabView);

		VerticalLayout tab3 = new VerticalLayout();
		TindakLanjutTabView tindakLanjutTabView = new TindakLanjutTabView();
		tindakLanjutTabView.setSizeFull();
		tab3.setSizeFull();
		tab3.addComponent(tindakLanjutTabView);
		
		VerticalLayout tab4 = new VerticalLayout();
		PemeriksaanTabView pemeriksaanTabView = new PemeriksaanTabView();
		pemeriksaanTabView.setSizeFull();
		tab4.setSizeFull();
		tab4.addComponent(pemeriksaanTabView);
		
		VerticalLayout tab5 = new VerticalLayout();
		LembagaTabView lembagaTabView = new LembagaTabView();
		lembagaTabView.setSizeFull();
		tab5.setSizeFull();
		tab5.addComponent(lembagaTabView);

		VerticalLayout tab6 = new VerticalLayout();
		PejabatTabView pejabatTabView = new PejabatTabView();
		pejabatTabView.setSizeFull();
		tab6.setSizeFull();
		tab6.addComponent(pejabatTabView);
		
		VerticalLayout tab7 = new VerticalLayout();
		LainLainTabView lainLainTabView = new LainLainTabView();
		lainLainTabView.setSizeFull();
		tab7.setSizeFull();
		tab7.addComponent(lainLainTabView);
		
		tabsheet.addTab(tab1, "Tabel Temuan",  null);
		tabsheet.addTab(tab2, "Tabel Rekomendasi",  null);
		tabsheet.addTab(tab3, "T. Tindak Lanjut",  null);
		tabsheet.addTab(tab4, "T. Pemeriksaan",  null);
		tabsheet.addTab(tab5, "T. Lembaga",  null);
		tabsheet.addTab(tab6, "Pegawai dan Jabatan",  null);
		tabsheet.addTab(tab7, "Tabel Lain-lain",  null);

		
	}
	

}
