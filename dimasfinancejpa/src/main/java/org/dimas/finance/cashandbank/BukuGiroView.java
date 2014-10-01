package org.dimas.finance.cashandbank;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.dimas.finance.master.SalesmanBinderModel;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Salesman;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.util.HelpManager;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Table.Align;

public class BukuGiroView extends CustomComponent{
	private BukuGiroModel model;

	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldRefno = new TextField("NO. DOKUMEN");
	private ComboBox comboDivision = new ComboBox("DIVISI");
	private TextField fieldGiroNumber = new TextField("NOMOR GIRO");
	private ComboBox comboBank = new ComboBox("BANK");
	private TextField fieldNasabah= new TextField("NASABAH");
	private TextField fieldAmount = new TextField("NILAI GIRO");
	private TextField fieldAmountUsed = new TextField("TERPAKAI");
	private DateField dateFieldGiroDate = new DateField("TANGGAL GIRO");
	private DateField dateFieldDueDate = new DateField("JATUH TEMPO");
	
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	private Button btnAddForm= new Button("Add New");
	private Button btnDeleteForm= new Button("Delete");

	//Additional Component
	private ComboBox comboSearchByDivision = new ComboBox("DIVISI");
	private TextField fieldSearchByRefno = new TextField("NO. DOK");
	private TextField fieldSearchByNogiro = new TextField("GIRO");
	private TextField fieldSearchByNasabah = new TextField("NASABAH");
	private Button btnSearch = new Button("Search");
	private Button btnPrint = new Button("Print");
	private Button btnHelp = new Button("Help");
	private Button btnSeparator1 = new Button(".");
	private Button btnSeparator2 = new Button(".");

	//LAYOUT
	private FormLayout formLayout = new FormLayout();
	
	//Panel
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelTabel = new Panel();
	private Panel panelForm = new Panel();

	//Help Manager	
    private HelpManager helpManager;
	
	
	public BukuGiroView(BukuGiroModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();
		
	}
	
	public void initComponent(){
		table = new Table() {
		    @Override
		    protected String formatPropertyValue(Object rowId,
		            Object colId, Property property) {
		        // Format by property type
		        if (property.getType() == Date.class && property.getValue() != null) {
		            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		            return df.format((Date)property.getValue());
		        }
		        
		        if (property.getType()==Boolean.class){
		        	if ((Boolean) property.getValue()==true) {
		        		return "OK";
		        	} else {
		        		return "-";
		        	}
		        }
		        return super.formatPropertyValue(rowId, colId, property);
		    }
		};		
		
	}
	
	public void buildView(){
		
		//Inisialisasi Panel 
		setSizeFull();
		content.setSizeFull();
		
		//PANEL
		panelUtama.setSizeFull();
		panelTabel.setSizeFull();
		
		//INIT COMPONENT ATAS
		btnSeparator1.setEnabled(false);
		btnSeparator2.setEnabled(false);
		
		//INIT COMPONENT TENGAH
		table.setSizeFull();
		table.setSelectable(true);
		table.setImmediate(true);
		table.setBuffered(false);
		table.setFooterVisible(true);
		
		//INIT COMPONENT BAWAH	
		
		//DEKLARASI LAYOUT
		//KOMPONEN ATAS
		HorizontalLayout layoutTop = new HorizontalLayout();		
		layoutTop.addComponent(comboSearchByDivision);
//		layoutTop.addComponent(fieldSearchByRefno);
		layoutTop.addComponent(fieldSearchByNogiro);
		layoutTop.addComponent(fieldSearchByNasabah);
		
		layoutTop.addComponent(btnSearch);
		layoutTop.addComponent(btnSeparator1);
		layoutTop.addComponent(btnAddForm);
		layoutTop.addComponent(btnDeleteForm);
		layoutTop.addComponent(btnSeparator2);
		layoutTop.addComponent(btnPrint);
		layoutTop.addComponent(btnHelp);		
		
		layoutTop.setComponentAlignment(comboSearchByDivision, Alignment.BOTTOM_CENTER);
//		layoutTop.setComponentAlignment(fieldSearchByRefno, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(fieldSearchByNogiro, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(fieldSearchByNasabah, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnSeparator1, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnAddForm, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnDeleteForm, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnSeparator2, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnPrint, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnHelp, Alignment.BOTTOM_CENTER);
		
		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		middleLayout.setSizeFull();
		middleLayout.addComponent(table);

		//KOMPONEN BAWAH
		formLayout.addComponent(fieldRefno);
		formLayout.addComponent(comboDivision);
		formLayout.addComponent(comboBank);
		formLayout.addComponent(fieldGiroNumber);
		formLayout.addComponent(fieldNasabah);
		formLayout.addComponent(fieldAmount);
		formLayout.addComponent(fieldAmountUsed);
		formLayout.addComponent(dateFieldGiroDate);
		formLayout.addComponent(dateFieldDueDate);
		
		HorizontalLayout formLayoutHorizontal = new HorizontalLayout();
		formLayoutHorizontal.setSpacing(true);
		formLayoutHorizontal.addComponent(btnSaveForm);
		formLayoutHorizontal.addComponent(btnCancelForm);
		formLayout.addComponent(formLayoutHorizontal);
		
		//MASUKKAN KE PANEL
		panelTop.setContent(layoutTop);		
		panelTabel.setContent(middleLayout);
		panelForm.setContent(formLayout);

		//VERTICAL SPLIT PANE
		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
		verticalSplitPanel.setSizeFull();		
		verticalSplitPanel.setSplitPosition(40);
		
		verticalSplitPanel.setFirstComponent(panelTabel);		
		verticalSplitPanel.setSecondComponent(panelForm);
		
		//MASUKKAN KE ROOT
		content.addComponent(panelTop);
		content.addComponent(verticalSplitPanel);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		content.setExpandRatio(verticalSplitPanel, 1);
		
		//init
		formLayout.setVisible(false);
		
	}
	
	public void setDisplay(){
		//TABLE
		table.setContainerDataSource(model.getBeanItemContainerBukugiro());
		
		setTableProperties();
		
		setDisplayTableFooter();
		
		bindAndBuildFieldGroupComponent();
		
	}
	public void setDisplayTableFooter(){
		Collection items =  model.getBeanItemContainerBukugiro().getItemIds();
		table.setColumnFooter("id", "*Jumlah Record: " + items.size());
		
	}

	public void bindAndBuildFieldGroupComponent(){
		comboSearchByDivision.setContainerDataSource(model.getBeanItemContainerDivision());
		comboSearchByDivision.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboSearchByDivision.setNullSelectionAllowed(true);
		
		//Init isian combobox
		comboDivision.setContainerDataSource(model.getBeanItemContainerDivision());
		comboDivision.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboDivision.setNullSelectionAllowed(false);
		
		comboBank.setContainerDataSource(model.getBeanItemContainerBank());
		comboBank.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);			
		comboBank.setNullSelectionAllowed(false);
		
		model.getBinderBukugiro().bind(fieldRefno, "id.refno");
		model.getBinderBukugiro().bind(comboDivision, "divisionBean");
		model.getBinderBukugiro().bind(comboBank, "bankBean");
		model.getBinderBukugiro().bind(fieldGiroNumber, "gironumber");
		model.getBinderBukugiro().bind(fieldNasabah, "nasabah");
		model.getBinderBukugiro().bind(fieldAmount, "amount");
		model.getBinderBukugiro().bind(fieldAmountUsed, "amountused");
		model.getBinderBukugiro().bind(dateFieldGiroDate, "girodate");
		model.getBinderBukugiro().bind(dateFieldDueDate, "giroduedate");
		
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

		setVisibleTableProperties("id", "bankBean", "gironumber", 
				"nasabah", "amount", "amountused", "girodate", "giroduedate");
		
//		table.setColumnCollapsingAllowed(true);
//		try{
//			table.setColumnCollapsed("refno", true);
//			table.setColumnCollapsed("refno", true);
//			
//		} catch(Exception ex){}
		
		//ALIGNMENT
		table.setColumnAlignment("amount", Align.RIGHT);
		table.setColumnAlignment("amountused", Align.RIGHT);
		
		//set header
		table.setColumnHeader("id", "DIV-YEAR-DAY");
		table.setColumnHeader("bankBean", "BANK");
		table.setColumnHeader("gironumber", "GIRO NUMBER");
		table.setColumnHeader("nasabah", "NASABAH");
		table.setColumnHeader("amount", "NILAI GIRO");
		table.setColumnHeader("amountused", "TERPAKAI");
		table.setColumnHeader("girodate", "TANGGAL GIRO");
		table.setColumnHeader("giroduedate", "JATUH TEMPO");
		
		
//		table.setColumnExpandRatio("selected", 2);
//		table.setColumnExpandRatio("recapno", 3);
				
	}
	
	public void setFormButtonAndTextState(){
		//KODE REFNO SELALU READ ONLY KARENA OTOMATIS
		fieldRefno.setReadOnly(true);
		
		if (model.getOperationStatus().equals(EnumFormOperationStatus.OPEN.getStrCode())){
			formLayout.setVisible(false);
			table.setSelectable(true);
			btnAddForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);			
			btnSearch.setEnabled(true);
		} else if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
			formLayout.setVisible(true);
			table.setSelectable(false);
			btnAddForm.setEnabled(false);
			btnDeleteForm.setEnabled(false);
			btnSearch.setEnabled(false);
		}else if (model.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
			formLayout.setVisible(true);
			table.setSelectable(true);
			btnAddForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);			
			btnSearch.setEnabled(true);
		}		
		
	}
	
	public void focustIdOrDesc(){
		if (fieldRefno.isEnabled()){
			fieldRefno.focus();
		} else {
			fieldGiroNumber.focus();		                    						
		}		
	}
	
	public BukuGiroModel getModel() {
		return model;
	}

	public void setModel(BukuGiroModel model) {
		this.model = model;
	}

	public VerticalLayout getContent() {
		return content;
	}

	public void setContent(VerticalLayout content) {
		this.content = content;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public TextField getFieldRefno() {
		return fieldRefno;
	}

	public void setFieldRefno(TextField fieldRefno) {
		this.fieldRefno = fieldRefno;
	}

	public TextField getFieldGiroNumber() {
		return fieldGiroNumber;
	}

	public void setFieldGiroNumber(TextField fieldGiroNumber) {
		this.fieldGiroNumber = fieldGiroNumber;
	}

	public ComboBox getComboBank() {
		return comboBank;
	}

	public void setComboBank(ComboBox comboBank) {
		this.comboBank = comboBank;
	}

	public TextField getFieldNasabah() {
		return fieldNasabah;
	}

	public void setFieldNasabah(TextField fieldNasabah) {
		this.fieldNasabah = fieldNasabah;
	}

	public TextField getFieldAmount() {
		return fieldAmount;
	}

	public void setFieldAmount(TextField fieldAmount) {
		this.fieldAmount = fieldAmount;
	}

	public TextField getFieldAmountUsed() {
		return fieldAmountUsed;
	}

	public void setFieldAmountUsed(TextField fieldAmountUsed) {
		this.fieldAmountUsed = fieldAmountUsed;
	}

	public DateField getDateFieldGiroDate() {
		return dateFieldGiroDate;
	}

	public void setDateFieldGiroDate(DateField dateFieldGiroDate) {
		this.dateFieldGiroDate = dateFieldGiroDate;
	}

	public DateField getDateFieldDueDate() {
		return dateFieldDueDate;
	}

	public void setDateFieldDueDate(DateField dateFieldDueDate) {
		this.dateFieldDueDate = dateFieldDueDate;
	}

	public Button getBtnSaveForm() {
		return btnSaveForm;
	}

	public void setBtnSaveForm(Button btnSaveForm) {
		this.btnSaveForm = btnSaveForm;
	}

	public Button getBtnCancelForm() {
		return btnCancelForm;
	}

	public void setBtnCancelForm(Button btnCancelForm) {
		this.btnCancelForm = btnCancelForm;
	}

	public Button getBtnAddForm() {
		return btnAddForm;
	}

	public void setBtnAddForm(Button btnAddForm) {
		this.btnAddForm = btnAddForm;
	}

	public Button getBtnDeleteForm() {
		return btnDeleteForm;
	}

	public void setBtnDeleteForm(Button btnDeleteForm) {
		this.btnDeleteForm = btnDeleteForm;
	}

	public TextField getFieldSearchByRefno() {
		return fieldSearchByRefno;
	}

	public void setFieldSearchByRefno(TextField fieldSearchByRefno) {
		this.fieldSearchByRefno = fieldSearchByRefno;
	}

	public TextField getFieldSearchByNogiro() {
		return fieldSearchByNogiro;
	}

	public void setFieldSearchByNogiro(TextField fieldSearchByNogiro) {
		this.fieldSearchByNogiro = fieldSearchByNogiro;
	}

	public TextField getFieldSearchByNasabah() {
		return fieldSearchByNasabah;
	}

	public void setFieldSearchByNasabah(TextField fieldSearchByNasabah) {
		this.fieldSearchByNasabah = fieldSearchByNasabah;
	}

	public Button getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(Button btnSearch) {
		this.btnSearch = btnSearch;
	}

	public Button getBtnPrint() {
		return btnPrint;
	}

	public void setBtnPrint(Button btnPrint) {
		this.btnPrint = btnPrint;
	}

	public Button getBtnHelp() {
		return btnHelp;
	}

	public void setBtnHelp(Button btnHelp) {
		this.btnHelp = btnHelp;
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

	public Panel getPanelTabel() {
		return panelTabel;
	}

	public void setPanelTabel(Panel panelTabel) {
		this.panelTabel = panelTabel;
	}

	public Panel getPanelForm() {
		return panelForm;
	}

	public void setPanelForm(Panel panelForm) {
		this.panelForm = panelForm;
	}

	public HelpManager getHelpManager() {
		return helpManager;
	}

	public void setHelpManager(HelpManager helpManager) {
		this.helpManager = helpManager;
	}

	public FormLayout getFormLayout() {
		return formLayout;
	}

	public void setFormLayout(FormLayout formLayout) {
		this.formLayout = formLayout;
	}

	public ComboBox getComboDivision() {
		return comboDivision;
	}

	public void setComboDivision(ComboBox comboDivision) {
		this.comboDivision = comboDivision;
	}

	public ComboBox getComboSearchByDivision() {
		return comboSearchByDivision;
	}

	public void setComboSearchByDivision(ComboBox comboSearchByDivision) {
		this.comboSearchByDivision = comboSearchByDivision;
	}
	
	

}
