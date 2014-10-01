package org.dimas.finance.systemsetting.harikerja;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.ibm.icu.text.DateFormat;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class PekanView extends CustomComponent {
	private PekanModel model;
	private VerticalLayout content = new VerticalLayout();

	ComboBox comboDivision = new ComboBox();
	private Table table;
	private Button btnSimpan = new Button("Simpan");
	private Button btnSwitchKerjaLibur = new Button("Switch Kerja/Libur");
	private Button btnSeparator1 = new Button(".");
	private Button btnSeparator2 = new Button(".");
	
	private HorizontalLayout layoutTitle = new HorizontalLayout();
	
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelMiddle = new Panel();
//	private Panel panelButtom = new Panel();
	
	public PekanView(PekanModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();
	}
	public void initComponent(){
		//INIT COMPONENT ATAS
		btnSeparator1.setEnabled(false);
		btnSeparator2.setEnabled(false);
		
		//INIT COMPONENT TENGAH
		panelMiddle.setCaption("");
        final SimpleDateFormat df = new SimpleDateFormat("E, dd-MM-yyyy");

        table = new Table() {
		    @Override
		    protected String formatPropertyValue(Object rowId,
		            Object colId, Property property) {
		        // Format by property type
		        if (property.getType() == Date.class && property.getValue() != null) {
		            return df.format((Date)property.getValue());
		        }
		        
		        if (property.getType()==Boolean.class){
		        	if ((Boolean) property.getValue()==true) {
		        		return "Kerja";
		        	} else {
		        		return "--Libur--";
		        	}
		        }
		        return super.formatPropertyValue(rowId, colId, property);
		    }
		};		
		
		table.setSizeFull();
		table.setSelectable(true);
		table.setImmediate(true);
		table.setBuffered(false);
		table.setFooterVisible(true);
		
		
		
		
		//INIT COMPONENT BAWAH	
	}
	public void buildView(){
		//Inisialisasi Panel 
		setSizeFull();
		content.setSizeFull();
		
		//PANEL
		panelUtama.setSizeFull();
		panelTop.setSizeUndefined();
		panelMiddle.setSizeUndefined();
		
		//DEKLARASI LAYOUT
		//KOMPONEN ATAS
		HorizontalLayout layoutTop = new HorizontalLayout();		
		layoutTop.setWidth("700px");
		
		layoutTop.addComponent(btnSeparator1);
		layoutTop.addComponent(comboDivision);
//		layoutTop.addComponent(btnSeparator2);
		layoutTop.addComponent(btnSimpan);		
		layoutTop.addComponent(btnSwitchKerjaLibur);
		
		layoutTop.setSpacing(true);
		
		
		//ALIGNMENT COMPONENT TENGAH
//		layoutTop.setComponentAlignment(comboPeriode1, Alignment.BOTTOM_CENTER);
		
		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		middleLayout.setSizeFull();
		middleLayout.addComponent(table);
		
		//MASUKKAN KE PANEL		
		panelTop.setContent(layoutTop);
		panelMiddle.setContent(middleLayout);

		//VERTICAL SPLIT PANE
//		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
//		verticalSplitPanel.setSizeFull();		
//		verticalSplitPanel.setSplitPosition(40);
		
//		verticalSplitPanel.setFirstComponent(panelTabel);		
//		verticalSplitPanel.setSecondComponent(panelForm);
		
		//MASUKKAN KE ROOT
		content.addComponent(panelTop);
		content.addComponent(panelMiddle);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		content.setExpandRatio(panelMiddle, 1);
		
		//init
		
	}
	public void setDisplay(){
		//TABLE
		table.setContainerDataSource(model.getBeanItemContainerPekan());

		setTableProperties();
		
		bindAndBuildFieldGroupComponent();
		setDisplayTableFooter();
	}
	
	public void bindAndBuildFieldGroupComponent(){
		//TANGGAL AWAL
		
		comboDivision.setContainerDataSource(model.getBeanItemContainerDivision());
		comboDivision.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboDivision.setNullSelectionAllowed(false);

		
	}
	public void setDisplayTableFooter(){
		
	}
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

		setVisibleTableProperties("periode", "pekan", "hariAwalPekan", 
				"tglAwalPekan", "hariAkhirPekan", "tglAkhirPekan");
		
//		table.setColumnCollapsingAllowed(true);
//		try{
//			table.setColumnCollapsed("refno", true);
//			table.setColumnCollapsed("refno", true);
//			
//		} catch(Exception ex){}
		
		//ALIGNMENT
		table.setColumnAlignment("periode", Align.CENTER);
		table.setColumnAlignment("pekan", Align.CENTER);
		table.setColumnAlignment("hariAwalPekan", Align.RIGHT);
		table.setColumnAlignment("tglAwalPekan", Align.CENTER);
		table.setColumnAlignment("hariAkhirPekan", Align.CENTER);
		table.setColumnAlignment("tglAkhirPekan", Align.CENTER);
		
		//set header
		table.setColumnHeader("periode", "PERIODE");
		table.setColumnHeader("pekan", "PEKAN");
		table.setColumnHeader("hariAwalPekan", "Hari");
		table.setColumnHeader("tglAwalPekan", "Tanggal Awal Pekan");
		table.setColumnHeader("hariAkhirPekan", "Hari");
		table.setColumnHeader("tglAkhirPekan", "Hari Akhir Pekan");
		
		
////		table.setColumnExpandRatio("selected", 2);
////		table.setColumnExpandRatio("recapno", 3);
				
	}
	public void setFormButtonAndTextState(){
	}
	public void focustIdOrDesc(){
		
	}
	public PekanModel getModel() {
		return model;
	}
	public void setModel(PekanModel model) {
		this.model = model;
	}
	public VerticalLayout getContent() {
		return content;
	}
	public void setContent(VerticalLayout content) {
		this.content = content;
	}
	public ComboBox getComboDivision() {
		return comboDivision;
	}
	public void setComboDivision(ComboBox comboDivision) {
		this.comboDivision = comboDivision;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public Button getBtnSimpan() {
		return btnSimpan;
	}
	public void setBtnSimpan(Button btnSimpan) {
		this.btnSimpan = btnSimpan;
	}
	public Button getBtnSwitchKerjaLibur() {
		return btnSwitchKerjaLibur;
	}
	public void setBtnSwitchKerjaLibur(Button btnSwitchKerjaLibur) {
		this.btnSwitchKerjaLibur = btnSwitchKerjaLibur;
	}
	public Button getBtnSeparator1() {
		return btnSeparator1;
	}
	public void setBtnSeparator1(Button btnSeparator1) {
		this.btnSeparator1 = btnSeparator1;
	}
	public Button getBtnSeparator2() {
		return btnSeparator2;
	}
	public void setBtnSeparator2(Button btnSeparator2) {
		this.btnSeparator2 = btnSeparator2;
	}
	public HorizontalLayout getLayoutTitle() {
		return layoutTitle;
	}
	public void setLayoutTitle(HorizontalLayout layoutTitle) {
		this.layoutTitle = layoutTitle;
	}
	public Panel getPanelUtama() {
		return panelUtama;
	}
	public void setPanelUtama(Panel panelUtama) {
		this.panelUtama = panelUtama;
	}
	public Panel getPanelTop() {
		return panelTop;
	}
	public void setPanelTop(Panel panelTop) {
		this.panelTop = panelTop;
	}
	public Panel getPanelMiddle() {
		return panelMiddle;
	}
	public void setPanelMiddle(Panel panelMiddle) {
		this.panelMiddle = panelMiddle;
	}
	
	

	
	
	
	
}
