package org.blitar.bawasda.simhp.mastertabel.pejabat;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class PejabatTabView extends CustomComponent{
	
	public PejabatTabView() {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setCompositionRoot(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		VerticalLayout tab1 = new VerticalLayout();
		PegawaiJabatanModel tabelTemuanKelompokModel = new PegawaiJabatanModel();
		PegawaiJabatanView tabelTemuanKelompokView = new PegawaiJabatanView(tabelTemuanKelompokModel);
		tabelTemuanKelompokView.setSizeFull();
		new PegawaiJabatanPresenter(tabelTemuanKelompokModel, tabelTemuanKelompokView);
		tab1.addComponent(tabelTemuanKelompokView);
		tab1.setSizeFull();
		
		VerticalLayout tab2 = new VerticalLayout();
		PegawaiModel tabelTemuanGrupModel = new PegawaiModel();
		PegawaiView tabelTemuanGrupView = new PegawaiView(tabelTemuanGrupModel);
		tabelTemuanGrupView.setSizeFull();
		new PegawaiPresenter(tabelTemuanGrupModel, tabelTemuanGrupView);
		tab2.addComponent(tabelTemuanGrupView);
		tab2.setSizeFull();

		VerticalLayout tab3 = new VerticalLayout();
		SuratTugasTimAnggotaPosisiModel tabelRuangLingkupPemeriksaanModel = new SuratTugasTimAnggotaPosisiModel();
		SuratTugasTimAnggotaPosisiView tabelRuangLingkupPemeriksaanView = new SuratTugasTimAnggotaPosisiView(tabelRuangLingkupPemeriksaanModel);
		tabelRuangLingkupPemeriksaanView.setSizeFull();
		new SuratTugasTimAnggotaPosisiPresenter(tabelRuangLingkupPemeriksaanModel, tabelRuangLingkupPemeriksaanView);
		tab3.addComponent(tabelRuangLingkupPemeriksaanView);
		tab3.setSizeFull();

		tabsheet.addTab(tab1, "Tabel Jabatan",  null);
		tabsheet.addTab(tab2, "Pegawai",  null);
		tabsheet.addTab(tab3, "Jabatan Anggota pada Surat Tugas Pemeriksaan",  null);
	
		
	}
	

}
