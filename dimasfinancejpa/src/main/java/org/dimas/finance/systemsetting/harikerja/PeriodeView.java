package org.dimas.finance.systemsetting.harikerja;

import java.util.Calendar;
import java.util.Date;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class PeriodeView extends CustomComponent {
	private PeriodeModel model;
	private VerticalLayout content = new VerticalLayout();
	
	private DateField dateFieldAwalTanggal = new DateField("Tanggal Awal Periode: ");
	private ComboBox comboPeriode1 = new ComboBox("Periode 1: ");
	private ComboBox comboPeriode2 = new ComboBox("Periode 2: ");
	private ComboBox comboPeriode3 = new ComboBox("Periode 3: ");
	private ComboBox comboPeriode4 = new ComboBox("Periode 4: ");
	private ComboBox comboPeriode5 = new ComboBox("Periode 5: ");
	private ComboBox comboPeriode6 = new ComboBox("Periode 6: ");
	private ComboBox comboPeriode7 = new ComboBox("Periode 7: ");
	private ComboBox comboPeriode8 = new ComboBox("Periode 8: ");
	private ComboBox comboPeriode9 = new ComboBox("Periode 9: ");
	private ComboBox comboPeriode10 = new ComboBox("Periode 10: ");
	private ComboBox comboPeriode11 = new ComboBox("Periode 11: ");
	private ComboBox comboPeriode12 = new ComboBox("Periode 12: ");
	private ComboBox comboPeriode13 = new ComboBox("Periode 13: ");
	
	ComboBox comboDivision = new ComboBox();
	private Label labelNotes = new Label();
	private Button btnSetupCalender = new Button("Setup Calender Now");
	
	private HorizontalLayout layoutTitle = new HorizontalLayout();
	
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelMiddle = new Panel();
	private Panel panelButtom = new Panel();
	
	public PeriodeView(PeriodeModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();
	}
	public void initComponent(){
		//INIT COMPONENT ATAS
		//INIT COMPONENT TENGAH
		
		panelMiddle.setCaption("PROSES:");
		
		//INIT COMPONENT BAWAH	
		labelNotes.setContentMode(ContentMode.HTML);
		labelNotes.setValue("Notes: <br> Perubahan pada Calender Kerja akan mempengaruhi seluruh sistem <br>." );
	}
	public void buildView(){
		//Inisialisasi Panel 
		setSizeFull();
		content.setSizeFull();
		
		//PANEL
		panelUtama.setSizeFull();
		panelTop.setSizeUndefined();
		panelMiddle.setSizeUndefined();
		panelButtom.setSizeUndefined();
		
		
		//DEKLARASI LAYOUT
		//KOMPONEN ATAS
		VerticalLayout layoutTop = new VerticalLayout();		
		
		HorizontalLayout layoutTop0 = new HorizontalLayout();
		layoutTop0.addComponent(dateFieldAwalTanggal);
		layoutTop.addComponent(layoutTop0);
		
		HorizontalLayout layoutTop1 = new HorizontalLayout();
		layoutTop1.addComponent(comboPeriode1);
		layoutTop1.addComponent(comboPeriode2);
		layoutTop1.addComponent(comboPeriode3);
		layoutTop1.setSpacing(true);
		
		HorizontalLayout layoutTop2 = new HorizontalLayout();
		layoutTop2.addComponent(comboPeriode4);
		layoutTop2.addComponent(comboPeriode5);
		layoutTop2.addComponent(comboPeriode6);
		layoutTop2.setSpacing(true);
		
		HorizontalLayout layoutTop3 = new HorizontalLayout();
		layoutTop3.addComponent(comboPeriode7);
		layoutTop3.addComponent(comboPeriode8);
		layoutTop3.addComponent(comboPeriode9);
		layoutTop3.setSpacing(true);
		
		HorizontalLayout layoutTop4 = new HorizontalLayout();
		layoutTop4.addComponent(comboPeriode10);
		layoutTop4.addComponent(comboPeriode11);
		layoutTop4.addComponent(comboPeriode12);
		layoutTop4.setSpacing(true);
		
		HorizontalLayout layoutTop5 = new HorizontalLayout();
		layoutTop5.addComponent(comboPeriode13);
		layoutTop5.setSpacing(true);
		
		layoutTop.addComponent(layoutTop1);
		layoutTop.addComponent(layoutTop2);
		layoutTop.addComponent(layoutTop3);
		layoutTop.addComponent(layoutTop4);
		layoutTop.addComponent(layoutTop5);
		layoutTop.setSpacing(true);
		
		//ALIGNMENT COMPONENT TENGAH
//		layoutTop.setComponentAlignment(comboPeriode1, Alignment.BOTTOM_CENTER);
		
		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		middleLayout.setSizeFull();

		VerticalLayout middleLayout1 = new VerticalLayout();
		middleLayout1.addComponent(labelNotes);
		HorizontalLayout middleLayout2 = new HorizontalLayout();
		middleLayout2.addComponent(comboDivision);
		middleLayout2.addComponent(btnSetupCalender);

		middleLayout2.setSpacing(true);
		
		middleLayout.addComponent(middleLayout1);
		middleLayout.addComponent(middleLayout2);
		
		
		
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
	
	public ComboBox getComboDivision() {
		return comboDivision;
	}
	public void setComboDivision(ComboBox comboDivision) {
		this.comboDivision = comboDivision;
	}
	public Label getLabelNotes() {
		return labelNotes;
	}
	public void setLabelNotes(Label labelNotes) {
		this.labelNotes = labelNotes;
	}
	public void setDisplay(){
		bindAndBuildFieldGroupComponent();
		setDisplayTableFooter();
	}
	
	public void bindAndBuildFieldGroupComponent(){
		//TANGGAL AWAL
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.DATE, 1);
		dateFieldAwalTanggal.setValue(cal.getTime());
		
		comboDivision.setContainerDataSource(model.getBeanItemContainerDivision());
		comboDivision.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboDivision.setNullSelectionAllowed(false);
		
		comboPeriode1.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode1.addItem(String.valueOf(i));
			comboPeriode1.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode1.select("5");
		
		comboPeriode2.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode2.addItem(String.valueOf(i));
			comboPeriode1.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode2.select("4");

		comboPeriode3.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode3.addItem(String.valueOf(i));
			comboPeriode3.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode3.select("4");
		
//*************************************
		
		comboPeriode4.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode4.addItem(String.valueOf(i));
			comboPeriode4.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode4.select("5");

		comboPeriode5.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode5.addItem(String.valueOf(i));
			comboPeriode5.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode5.select("4");

		comboPeriode6.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode6.addItem(String.valueOf(i));
			comboPeriode6.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode6.select("4");

//************************************		
		comboPeriode7.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode7.addItem(String.valueOf(i));
			comboPeriode7.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode7.select("5");

		comboPeriode8.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode8.addItem(String.valueOf(i));
			comboPeriode8.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode8.select("4");
		
		comboPeriode9.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode9.addItem(String.valueOf(i));
			comboPeriode9.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode9.select("4");

		//************************************		
		comboPeriode10.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode10.addItem(String.valueOf(i));
			comboPeriode10.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode10.select("5");

		comboPeriode11.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode11.addItem(String.valueOf(i));
			comboPeriode11.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode11.select("4");

		comboPeriode12.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode12.addItem(String.valueOf(i));
			comboPeriode12.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode12.select("4");

		comboPeriode13.setNullSelectionAllowed(false);
		for (int i=0; i<=5; i++){
			comboPeriode13.addItem(String.valueOf(i));
			comboPeriode13.setItemCaption(String.valueOf(i), String.valueOf(i) + " Pekan");
		}
		comboPeriode13.select("0");

		
	}
	public void setDisplayTableFooter(){
		
	}
	public void setVisibleTableProperties(Object... tablePropertyIds) {
//		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){
	}	
	public void setFormButtonAndTextState(){
	}
	public void focustIdOrDesc(){
		
	}
	public PeriodeModel getModel() {
		return model;
	}
	public void setModel(PeriodeModel model) {
		this.model = model;
	}
	public VerticalLayout getContent() {
		return content;
	}
	public void setContent(VerticalLayout content) {
		this.content = content;
	}
	public ComboBox getComboPeriode1() {
		return comboPeriode1;
	}
	public void setComboPeriode1(ComboBox comboPeriode1) {
		this.comboPeriode1 = comboPeriode1;
	}
	public ComboBox getComboPeriode2() {
		return comboPeriode2;
	}
	public void setComboPeriode2(ComboBox comboPeriode2) {
		this.comboPeriode2 = comboPeriode2;
	}
	public ComboBox getComboPeriode3() {
		return comboPeriode3;
	}
	public void setComboPeriode3(ComboBox comboPeriode3) {
		this.comboPeriode3 = comboPeriode3;
	}
	public ComboBox getComboPeriode4() {
		return comboPeriode4;
	}
	public void setComboPeriode4(ComboBox comboPeriode4) {
		this.comboPeriode4 = comboPeriode4;
	}
	public ComboBox getComboPeriode5() {
		return comboPeriode5;
	}
	public void setComboPeriode5(ComboBox comboPeriode5) {
		this.comboPeriode5 = comboPeriode5;
	}
	public ComboBox getComboPeriode6() {
		return comboPeriode6;
	}
	public void setComboPeriode6(ComboBox comboPeriode6) {
		this.comboPeriode6 = comboPeriode6;
	}
	public ComboBox getComboPeriode7() {
		return comboPeriode7;
	}
	public void setComboPeriode7(ComboBox comboPeriode7) {
		this.comboPeriode7 = comboPeriode7;
	}
	public ComboBox getComboPeriode8() {
		return comboPeriode8;
	}
	public void setComboPeriode8(ComboBox comboPeriode8) {
		this.comboPeriode8 = comboPeriode8;
	}
	public ComboBox getComboPeriode9() {
		return comboPeriode9;
	}
	public void setComboPeriode9(ComboBox comboPeriode9) {
		this.comboPeriode9 = comboPeriode9;
	}
	public ComboBox getComboPeriode10() {
		return comboPeriode10;
	}
	public void setComboPeriode10(ComboBox comboPeriode10) {
		this.comboPeriode10 = comboPeriode10;
	}
	public ComboBox getComboPeriode11() {
		return comboPeriode11;
	}
	public void setComboPeriode11(ComboBox comboPeriode11) {
		this.comboPeriode11 = comboPeriode11;
	}
	public ComboBox getComboPeriode12() {
		return comboPeriode12;
	}
	public void setComboPeriode12(ComboBox comboPeriode12) {
		this.comboPeriode12 = comboPeriode12;
	}
	public ComboBox getComboPeriode13() {
		return comboPeriode13;
	}
	public void setComboPeriode13(ComboBox comboPeriode13) {
		this.comboPeriode13 = comboPeriode13;
	}
	public Button getBtnSetupCalender() {
		return btnSetupCalender;
	}
	public void setBtnSetupCalender(Button btnSetupCalender) {
		this.btnSetupCalender = btnSetupCalender;
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
	public Panel getPanelButtom() {
		return panelButtom;
	}
	public void setPanelButtom(Panel panelButtom) {
		this.panelButtom = panelButtom;
	}
	public DateField getDateFieldAwalTanggal() {
		return dateFieldAwalTanggal;
	}
	public void setDateFieldAwalTanggal(DateField dateFieldAwalTanggal) {
		this.dateFieldAwalTanggal = dateFieldAwalTanggal;
	}
	
	
	
}
