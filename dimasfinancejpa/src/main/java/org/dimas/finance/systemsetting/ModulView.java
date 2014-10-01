package org.dimas.finance.systemsetting;

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

public class ModulView extends CustomComponent {
	private ModulModel model;
	
	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldId = new TextField("MODUL/MENU ID");
	private TextField fieldModulGroup = new TextField("MODUL GROUP");	
	private TextField fieldTitle = new TextField("TITLE");	
	private TextField fieldNotes = new TextField("NOTES");
	
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	private Button btnAddForm= new Button("Add New");
	private Button btnDeleteForm= new Button("Delete");

	//Additional Component
	private TextField fieldSearchById = new TextField("MODUL/MENU ID");
	private TextField fieldSearchByGroup = new TextField("GROUP");
	private TextField fieldSearchByTitle = new TextField("TITLE");
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
	
	public ModulView(ModulModel model){
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
			        		return "LAKI-LAKI";
			        	} else {
			        		return "PEREMPUAN";
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
		layoutTop.addComponent(fieldSearchByGroup);
		layoutTop.addComponent(fieldSearchByTitle);
		
		layoutTop.addComponent(btnSearch);
		layoutTop.addComponent(btnSeparator1);
		layoutTop.addComponent(btnAddForm);
		layoutTop.addComponent(btnDeleteForm);
		layoutTop.addComponent(btnSeparator2);
		layoutTop.addComponent(btnPrint);
		layoutTop.addComponent(btnHelp);		
		
		layoutTop.setComponentAlignment(fieldSearchById, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(fieldSearchByGroup, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(fieldSearchByTitle, Alignment.BOTTOM_CENTER);
		
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
		formLayout.addComponent(fieldId);
		formLayout.addComponent(fieldModulGroup);
		formLayout.addComponent(fieldTitle);
		formLayout.addComponent(fieldNotes);	
		
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
		table.setContainerDataSource(model.getBeanItemContainerModul());
		
		setTableProperties();
		
		setDisplayTableFooter();
		
		bindAndBuildFieldGroupComponent();
		
	}
	public void setDisplayTableFooter(){
		Collection items =  model.getBeanItemContainerModul().getItemIds();
		table.setColumnFooter("id", "*Jumlah Record: " + items.size());
		
	}

	public void bindAndBuildFieldGroupComponent(){

		
		model.getBinderModul().bind(fieldId, "id");
		model.getBinderModul().bind(fieldModulGroup, "modulGroup");
		model.getBinderModul().bind(fieldTitle, "title");
		model.getBinderModul().bind(fieldNotes, "notes");
		
		
	}
	
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

		setVisibleTableProperties("id", "modulGroup", 
				"title", "notes");
		
//		table.setColumnCollapsingAllowed(true);
//		try{
//			table.setColumnCollapsed("state", true);
//			table.setColumnCollapsed("birthDate", true);
//			table.setColumnCollapsed("joinDate", true);
//			
//		} catch(Exception ex){}
		
//		//ALIGNMENT
//		table.setColumnAlignment("id", Align.CENTER);
//		table.setColumnAlignment("modulGroup", Align.CENTER);
//		table.setColumnAlignment("title", Align.CENTER);
//		table.setColumnAlignment("notes", Align.CENTER);
		
		//set header
		table.setColumnHeader("id", "MODUL/MENU ID");
		table.setColumnHeader("modulGroup", "MODUL/MENU GROUP");
		table.setColumnHeader("title", "TITLE");
		table.setColumnHeader("notes", "NOTES");
		
		
//		table.setColumnExpandRatio("selected", 2);
//		table.setColumnExpandRatio("recapno", 3);
				
	}
	
	public void setFormButtonAndTextState(){
		//KODE REFNO SELALU READ ONLY KARENA OTOMATIS
//		fieldUserId.setReadOnly(true);
		
		if (model.getOperationStatus().equals(EnumFormOperationStatus.OPEN.getStrCode())){
			formLayout.setVisible(false);
			table.setSelectable(true);
			btnAddForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);			
			btnSearch.setEnabled(true);
			fieldId.setReadOnly(true);
		} else if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
			formLayout.setVisible(true);
			table.setSelectable(false);
			btnAddForm.setEnabled(false);
			btnDeleteForm.setEnabled(false);
			btnSearch.setEnabled(false);
			fieldId.setReadOnly(false);
		}else if (model.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
			formLayout.setVisible(true);
			table.setSelectable(true);
			btnAddForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);			
			btnSearch.setEnabled(true);
			fieldId.setReadOnly(false);
		}		
		
	}
	
	public void focustIdOrDesc(){
		if (fieldId.isEnabled()){
			fieldId.focus();
		} else {
			fieldModulGroup.focus();		                    						
		}
	}
	
	
	
	public ModulModel getModel() {
		return model;
	}
	public void setModel(ModulModel model) {
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
	public TextField getFieldId() {
		return fieldId;
	}
	public void setFieldId(TextField fieldId) {
		this.fieldId = fieldId;
	}
	public TextField getFieldModulGroup() {
		return fieldModulGroup;
	}
	public void setFieldModulGroup(TextField fieldModulGroup) {
		this.fieldModulGroup = fieldModulGroup;
	}
	public TextField getFieldTitle() {
		return fieldTitle;
	}
	public void setFieldTitle(TextField fieldTitle) {
		this.fieldTitle = fieldTitle;
	}
	public TextField getFieldNotes() {
		return fieldNotes;
	}
	public void setFieldNotes(TextField fieldNotes) {
		this.fieldNotes = fieldNotes;
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
	
	
	public TextField getFieldSearchById() {
		return fieldSearchById;
	}
	public void setFieldSearchById(TextField fieldSearchById) {
		this.fieldSearchById = fieldSearchById;
	}
	public TextField getFieldSearchByGroup() {
		return fieldSearchByGroup;
	}
	public void setFieldSearchByGroup(TextField fieldSearchByGroup) {
		this.fieldSearchByGroup = fieldSearchByGroup;
	}
	public TextField getFieldSearchByTitle() {
		return fieldSearchByTitle;
	}
	public void setFieldSearchByTitle(TextField fieldSearchByTitle) {
		this.fieldSearchByTitle = fieldSearchByTitle;
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
