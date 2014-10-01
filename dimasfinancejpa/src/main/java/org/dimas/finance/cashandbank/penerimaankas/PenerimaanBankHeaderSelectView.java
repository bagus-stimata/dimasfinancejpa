package org.dimas.finance.cashandbank.penerimaankas;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.dimas.finance.model.Modul;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.util.HelpManager;

import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Table.Align;

public class PenerimaanBankHeaderSelectView extends CustomComponent {
	private PenerimaanBankHeaderSelectModel model;
	
	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldSearchById = new TextField("ID");
	private ComboBox comboSearchByDivision = new ComboBox("DIVISION");
	private DateField dateFieldSearchByTransdateFrom = new DateField("TGL TRANS FROM");
	private DateField dateFieldSearchByTransdateTo = new DateField("TO");
//	private TextField fieldSearchByTemplateName = new TextField("Template Name");
	private Button btnSearch = new Button("Search");
	private Button btnSelect = new Button("Select/Pilih");
	
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
	
	public PenerimaanBankHeaderSelectView(PenerimaanBankHeaderSelectModel model){
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
		    	try{
			        if (property.getType() == Date.class && property.getValue() != null) {
			            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			            return df.format((Date)property.getValue());
			        }
		    	} catch(Exception ex){}
		    	
		        try{
			        if (property.getType()==Boolean.class){
			        	if ((Boolean) property.getValue()==true) {
			        		return "CLOSE";
			        	} else {
			        		return "-";
			        	}
			        }
		        } catch(Exception ex){}
		        
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
		layoutTop.addComponent(fieldSearchById);
		layoutTop.addComponent(comboSearchByDivision);
		layoutTop.addComponent(dateFieldSearchByTransdateFrom);
		layoutTop.addComponent(dateFieldSearchByTransdateTo);
		
		layoutTop.addComponent(btnSearch);
		
		layoutTop.setComponentAlignment(fieldSearchById, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(dateFieldSearchByTransdateFrom, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(dateFieldSearchByTransdateTo, Alignment.BOTTOM_CENTER);
		
		layoutTop.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
		
		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		middleLayout.setSizeFull();
		middleLayout.addComponent(table);

		
		HorizontalLayout formLayoutHorizontal = new HorizontalLayout();
//		formLayoutHorizontal.setSpacing(true);
		formLayoutHorizontal.addComponent(btnSelect);
		formLayout.addComponent(formLayoutHorizontal);
		
		
		//MASUKKAN KE PANEL
		panelTop.setContent(layoutTop);		
		panelTabel.setContent(middleLayout);
		panelForm.setContent(formLayout);

		//VERTICAL SPLIT PANE
		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
		verticalSplitPanel.setSizeFull();		
		verticalSplitPanel.setSplitPosition(85);
		
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
		formLayout.setVisible(true);
		
		
	}
	
	public void setDisplay(){
		//TABLE
		table.setContainerDataSource(model.getBeanItemContainerItemHeader());
		
		setTableProperties();
		
		setDisplayTableFooter();
		
		bindAndBuildFieldGroupComponent();
	}
	public void setDisplayTableFooter(){
		Collection items =  model.getBeanItemContainerItemHeader().getItemIds();
		table.setColumnFooter("id", "*Jumlah Record: " + items.size());
		
	}

	public void bindAndBuildFieldGroupComponent(){
		comboSearchByDivision.setContainerDataSource(model.getBeanItemContainerDivision());
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

		setVisibleTableProperties("id", "divisionBean", 
				"transdate", "entrydate", "notes", "closing");
		
//		table.setColumnCollapsingAllowed(true);
//		try{
//			table.setColumnCollapsed("state", true);
//			table.setColumnCollapsed("birthDate", true);
//			table.setColumnCollapsed("joinDate", true);
//			
//		} catch(Exception ex){}
		
		//ALIGNMENT
		table.setColumnAlignment("id", Align.CENTER);
		table.setColumnAlignment("transdate", Align.CENTER);
		table.setColumnAlignment("closing", Align.CENTER);
		table.setColumnAlignment("entrydate", Align.CENTER);
		
		//SET HEADER
		table.setColumnHeader("id", "DIV-REFNO");
		table.setColumnHeader("divisionBean", "DIVISI");
		table.setColumnHeader("transdate", "TGL TRANS");
		table.setColumnHeader("entrydate", "TGL ENTRY");
		table.setColumnHeader("notes", "NOTES");
		table.setColumnHeader("closing", "CLOSING");
		
//		table.setColumnExpandRatio("selected", 2);
//		table.setColumnExpandRatio("recapno", 3);
				
	}
	
	
	public void focustIdOrDesc(){
		
	}
	public PenerimaanBankHeaderSelectModel getModel() {
		return model;
	}
	public void setModel(PenerimaanBankHeaderSelectModel model) {
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
	public TextField getFieldSearchById() {
		return fieldSearchById;
	}
	public void setFieldSearchById(TextField fieldSearchById) {
		this.fieldSearchById = fieldSearchById;
	}
	public ComboBox getComboSearchByDivision() {
		return comboSearchByDivision;
	}
	public void setComboSearchByDivision(ComboBox comboSearchByDivision) {
		this.comboSearchByDivision = comboSearchByDivision;
	}
	public DateField getDateFieldSearchByTransdateFrom() {
		return dateFieldSearchByTransdateFrom;
	}
	public void setDateFieldSearchByTransdateFrom(
			DateField dateFieldSearchByTransdateFrom) {
		this.dateFieldSearchByTransdateFrom = dateFieldSearchByTransdateFrom;
	}
	public DateField getDateFieldSearchByTransdateTo() {
		return dateFieldSearchByTransdateTo;
	}
	public void setDateFieldSearchByTransdateTo(
			DateField dateFieldSearchByTransdateTo) {
		this.dateFieldSearchByTransdateTo = dateFieldSearchByTransdateTo;
	}
	public Button getBtnSearch() {
		return btnSearch;
	}
	public void setBtnSearch(Button btnSearch) {
		this.btnSearch = btnSearch;
	}
	public Button getBtnSelect() {
		return btnSelect;
	}
	public void setBtnSelect(Button btnSelect) {
		this.btnSelect = btnSelect;
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
	public FormLayout getFormLayout() {
		return formLayout;
	}
	public void setFormLayout(FormLayout formLayout) {
		this.formLayout = formLayout;
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
	

	
}
