package org.dimas.finance.systemsetting;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.dimas.finance.ar.kredittunai.ArPaymentCustPembayaranModel;
import org.dimas.finance.ar.kredittunai.ArPaymentCustPembayaranPresenter;
import org.dimas.finance.ar.kredittunai.ArPaymentCustPembayaranView;
import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.Arpaymentdetail;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.util.HelpManager;

import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Table.Align;

public class ModulTemplateView extends CustomComponent{

	private ModulTemplateModel model;
	
	private VerticalLayout content = new VerticalLayout();
	
	//KOMPONEN HEADER atau UTAMA
	private TextField fieldId = new TextField("TEMPLATE ID");
	private TextField fieldName = new TextField("NAME/TITLE");
	private TextField fieldNotes = new TextField("NOTES");

	private Button btnAddForm= new Button("Add New or Copy");
	private Button btnDeleteForm= new Button("Delete");
	private Button btnSearchForm = new Button("Search");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	
//	//KOMPONEN WINDOW LIST HEADER
//	private TextField fieldSearchByHeaderId = new TextField("TEMPLATE ID");
//	private TextField fieldSearchByHeaderName = new TextField("NAME/TITLE");
//	private Button btnSearchHeader = new Button("Search/Filter");
//	
//	private Table tableHeader;	
//	private Button btnSelectHeader = new Button("Select and Close");
	
	//KOMPONEN DETAIL
	private Button btnAdd = new Button("Add Modul");
	private Button btnRem = new Button("Remove");
	private Table tableDetail;
	
//	//KOMPONEN WINDOW ADD DETAIL
//	private TextField fieldSeachByModulId = new TextField("MODUL/MENU ID");
//	private TextField fieldSearchByModulGroup = new TextField("GROUP");
//	private TextField fieldSearchByModulName = new TextField("NAME");
//
//	private Table tableModul;
//	
//	private Button btnSearchModul = new Button("Search/Filter");
//	private Button btnSelectModul = new Button("Select and Close");

	private Button btnPrint = new Button("Print");
	private Button btnHelp = new Button("Help");
	private Button btnSeparator1 = new Button(".");
	private Button btnSeparator2 = new Button(".");

	//LAYOUT
	private FormLayout formLayout = new FormLayout();
	
	//Panel
	private Panel panelUtama = new Panel();
	
	private Panel panelHeader = new Panel();
	private Panel panelHeaderSearchTop = new Panel();
	private Panel panelHeaderSearchTable = new Panel();
	private Panel panelHeaderSearchButtom = new Panel();
	
	private Panel panelDetail = new Panel("***");
	private Panel panelDetailTop = new Panel();
	private Panel panelDetailTable = new Panel();
	private Panel panelDetailButtom = new Panel();
	private Panel panelDetailAddModulTop = new Panel();
	private Panel panelDetailAddModulTabel = new Panel();
	private Panel panelDetailAddModulButtom = new Panel();
	
	
	
	//Help Manager	
    private HelpManager helpManager;
	
	public ModulTemplateView(ModulTemplateModel model){
		this.model = model;
		initComponent();
		buildView();
	
		setDisplay();
	}
	
	public void initComponent(){
		
		tableDetail = new Table() {
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
			        		return "OK";
			        	} else {
			        		return "-";
			        	}
			        }
		        } catch(Exception ex){}
		        
		        return super.formatPropertyValue(rowId, colId, property);
		    }
		};		
		
		
		tableDetail.setSizeFull();
		tableDetail.setSelectable(true);
		tableDetail.setImmediate(true);
		tableDetail.setBuffered(false);
		tableDetail.setFooterVisible(true);
		
	}
	public void buildView(){
		//Inisialisasi Panel 
		setSizeFull();
		content.setSizeFull();
		
		//PANEL
		panelUtama.setSizeFull();
//		panelHeader.setSizeFull();
		panelHeaderSearchTop.setSizeFull();
		panelHeaderSearchTable.setSizeFull();
		panelHeaderSearchButtom.setSizeFull();
		
		panelDetail.setSizeFull();
		panelDetailTop.setSizeFull();
		panelDetailTable.setSizeFull();
		panelDetailButtom.setSizeFull();
		panelDetailAddModulTop.setSizeFull();
		panelDetailAddModulTabel.setSizeFull();
		panelDetailAddModulButtom.setSizeFull();
		
		//INIT COMPONENT ATAS
		btnSeparator1.setEnabled(false);
		btnSeparator2.setEnabled(false);
		
		//COMPONEN ATAS or HEADER		
		btnAddForm.setWidth("170px");
		btnDeleteForm.setWidth("120px");
		btnSearchForm.setWidth("120px");
		
		HorizontalLayout layoutHeader = new HorizontalLayout();
		layoutHeader.setSizeFull();
		
		HorizontalLayout layoutHeaderLeft = new HorizontalLayout();
		HorizontalLayout layoutHeaderRight = new HorizontalLayout();
		
		layoutHeaderLeft.addComponent(fieldId);
		layoutHeaderLeft.addComponent(fieldName);
		layoutHeaderLeft.addComponent(fieldNotes);
		
		layoutHeaderRight.addComponent(btnAddForm);
		layoutHeaderRight.addComponent(btnDeleteForm);
		layoutHeaderRight.addComponent(btnSearchForm);
		layoutHeaderRight.setSpacing(true);
		
		layoutHeader.addComponent(layoutHeaderLeft);
		layoutHeader.addComponent(layoutHeaderRight);
		
		layoutHeader.setComponentAlignment(layoutHeaderRight, Alignment.BOTTOM_LEFT);
//		layoutHeader.setExpandRatio(layoutHeaderLeft, 3);
//		layoutHeader.setExpandRatio(layoutHeaderRight, 2);
		
		//KOMPONEN BAWAH or DETAIL
		VerticalLayout layoutDetail = new VerticalLayout();
//		layoutDetail.setSizeFull();
		
		HorizontalLayout layoutDetailTop = new HorizontalLayout();
		HorizontalLayout layoutDetailTable = new HorizontalLayout();
		layoutDetailTable.setSizeFull();
		HorizontalLayout layoutDetailButtom = new HorizontalLayout();
		
		layoutDetailTop.addComponent(btnAdd);
		layoutDetailTop.addComponent(btnRem);
		
		layoutDetailTable.addComponent(tableDetail);
		
		layoutDetailButtom.addComponent(btnSaveForm);
		layoutDetailButtom.addComponent(btnCancelForm);
		
		layoutDetail.addComponent(layoutDetailTop);
		layoutDetail.addComponent(layoutDetailTable);
		layoutDetail.addComponent(layoutDetailButtom);
		
		
		//MASUKKAN KE PANEL
		panelHeader.setContent(layoutHeader);
		content.addComponent(panelHeader);
		
		panelDetail.setContent(layoutDetail);
		content.addComponent(panelDetail);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		
		content.setExpandRatio(panelDetail, 1);
		
		
	}
	
	public void setDisplay(){
		//TABLE HEADER
		
		//TABLE DETAIL
		tableDetail.setContainerDataSource(model.getBeanItemContainerModulTempDetail());
		
		setTableDetailProperties();
		
		setDisplayTableFooter();
		
		bindAndBuildFieldGroupComponent();
		
		setFormButtonAndTextState();
		
	}
	public void setDisplayTableFooter(){
		Collection items =  model.getBeanItemContainerModulTempDetail().getItemIds();
		tableDetail.setColumnFooter("modulBean", "*Jumlah Record: " + items.size());
		
	}
	
	public void setVisibleTableDetailProperties(Object... tablePropertyIds) {
		tableDetail.setVisibleColumns(tablePropertyIds);		
	}
	
	public void setTableDetailProperties(){

		setVisibleTableDetailProperties("selected", "modulBean",  "otorize");
		
//		tableDetail.setColumnCollapsingAllowed(true);
//		try{
//			table.setColumnCollapsed("state", true);
//			
//		} catch(Exception ex){}
//		
//		//ALIGNMENT
//		table.setColumnAlignment("gender", Align.CENTER);
		tableDetail.setColumnAlignment("selected", Align.CENTER);
		
		//SET HEADER
		tableDetail.setColumnHeader("selected", "<input type='checkbox'/>");		
		tableDetail.setColumnHeader("modulBean", "MODUL/MENU");
		tableDetail.setColumnHeader("otorize", "OTORIZE (RWD/ReadWriteDelete)");
		
//		table.setColumnExpandRatio("selected", 2);
//		table.setColumnExpandRatio("recapno", 3);
				
	}
	
	public void bindAndBuildFieldGroupComponent(){
		
		model.getBinderModulTempHeader().bind(fieldId, "id");
		model.getBinderModulTempHeader().bind(fieldName, "templateName");
		model.getBinderModulTempHeader().bind(fieldNotes, "notes");
		
	}
	
	
	private Window windowHeaderSearch = new Window();	
	
	private ModulTempHeaderSelectModel modulTempHeaderSelectModel; 
	private ModulTempHeaderSelectView modulTempHeaderSelectView;
	
	//WINDOW HEADER SELECT
	public void buildWindowModulTempHeaderSelect(){
		
		//Create window
		windowHeaderSearch = new Window();
		windowHeaderSearch.setModal(true);
		windowHeaderSearch.center();
		windowHeaderSearch.setStyleName("login-layout");
		windowHeaderSearch.setWidth("700px");
		windowHeaderSearch.setHeight("600px");
		
		//INITIAL DATA TO PASS
		
		modulTempHeaderSelectModel = new ModulTempHeaderSelectModel();
		modulTempHeaderSelectView = new ModulTempHeaderSelectView(modulTempHeaderSelectModel);
		
		ModulTempHeaderSelectPresenter modulTempHeaderSelectPresenter = new ModulTempHeaderSelectPresenter(
				modulTempHeaderSelectModel, modulTempHeaderSelectView);		
		modulTempHeaderSelectView.setSizeFull();		
		
		windowHeaderSearch.setContent(modulTempHeaderSelectView);
		
		getUI().addWindow(windowHeaderSearch);
		
		
	}
	public void destroyWindowModulTempHeaderSelect(){		
		windowHeaderSearch.close();
	}
	
	private Window windowModulSearch = new Window();	
	
	private ModulSelectModel modulSelectModel; 
	private ModulSelectView modulSelectView;
	
	//WINDOW HEADER SELECT
	public void buildWindowModulSelect(){
		//Create window
		windowModulSearch = new Window();
		windowModulSearch.setModal(true);
		windowModulSearch.center();
		windowModulSearch.setStyleName("login-layout");
		windowModulSearch.setWidth("700px");
		windowModulSearch.setHeight("600px");
		
		//INITIAL DATA TO PASS
		
		modulSelectModel = new ModulSelectModel();
		modulSelectView = new ModulSelectView(modulSelectModel);
		
		ModulSelectPresenter modulSelectPresenter = new ModulSelectPresenter(
				modulSelectModel, modulSelectView);		
		modulSelectView.setSizeFull();		
		
		windowModulSearch.setContent(modulSelectView);		
		getUI().addWindow(windowModulSearch);
		
		
	}
	public void destroyWindowModulSelect(){
		windowModulSearch.close();
	}

	public void setFormButtonAndTextState(){
		//KODE REFNO SELALU READ ONLY KARENA OTOMATIS
//		fieldUserId.setReadOnly(true);
		
		if (model.getOperationStatus().equals(EnumFormOperationStatus.OPEN.getStrCode())){
			btnAddForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);
			btnSearchForm.setEnabled(true);			
		} else if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
			btnAddForm.setEnabled(false);
			btnDeleteForm.setEnabled(false);
			btnSearchForm.setEnabled(false);
		}else if (model.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
			//EDITING SAMA DENGAN OPEN
			btnAddForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);
			btnSearchForm.setEnabled(true);
		}		
		fieldId.setReadOnly(true);
		
	}

	public ModulTemplateModel getModel() {
		return model;
	}

	public void setModel(ModulTemplateModel model) {
		this.model = model;
	}

	public VerticalLayout getContent() {
		return content;
	}

	public void setContent(VerticalLayout content) {
		this.content = content;
	}

	public TextField getFieldId() {
		return fieldId;
	}

	public void setFieldId(TextField fieldId) {
		this.fieldId = fieldId;
	}

	public TextField getFieldName() {
		return fieldName;
	}

	public void setFieldName(TextField fieldName) {
		this.fieldName = fieldName;
	}

	public TextField getFieldNotes() {
		return fieldNotes;
	}

	public void setFieldNotes(TextField fieldNotes) {
		this.fieldNotes = fieldNotes;
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

	public Button getBtnSearchForm() {
		return btnSearchForm;
	}

	public void setBtnSearchForm(Button btnSearchForm) {
		this.btnSearchForm = btnSearchForm;
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

	public Button getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(Button btnAdd) {
		this.btnAdd = btnAdd;
	}

	public Button getBtnRem() {
		return btnRem;
	}

	public void setBtnRem(Button btnRem) {
		this.btnRem = btnRem;
	}

	public Table getTableDetail() {
		return tableDetail;
	}

	public void setTableDetail(Table tableDetail) {
		this.tableDetail = tableDetail;
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

	public Panel getPanelHeader() {
		return panelHeader;
	}

	public void setPanelHeader(Panel panelHeader) {
		this.panelHeader = panelHeader;
	}

	public Panel getPanelHeaderSearchTop() {
		return panelHeaderSearchTop;
	}

	public void setPanelHeaderSearchTop(Panel panelHeaderSearchTop) {
		this.panelHeaderSearchTop = panelHeaderSearchTop;
	}

	public Panel getPanelHeaderSearchTable() {
		return panelHeaderSearchTable;
	}

	public void setPanelHeaderSearchTable(Panel panelHeaderSearchTable) {
		this.panelHeaderSearchTable = panelHeaderSearchTable;
	}

	public Panel getPanelHeaderSearchButtom() {
		return panelHeaderSearchButtom;
	}

	public void setPanelHeaderSearchButtom(Panel panelHeaderSearchButtom) {
		this.panelHeaderSearchButtom = panelHeaderSearchButtom;
	}

	public Panel getPanelDetail() {
		return panelDetail;
	}

	public void setPanelDetail(Panel panelDetail) {
		this.panelDetail = panelDetail;
	}

	public Panel getPanelDetailTop() {
		return panelDetailTop;
	}

	public void setPanelDetailTop(Panel panelDetailTop) {
		this.panelDetailTop = panelDetailTop;
	}

	public Panel getPanelDetailTable() {
		return panelDetailTable;
	}

	public void setPanelDetailTable(Panel panelDetailTable) {
		this.panelDetailTable = panelDetailTable;
	}

	public Panel getPanelDetailButtom() {
		return panelDetailButtom;
	}

	public void setPanelDetailButtom(Panel panelDetailButtom) {
		this.panelDetailButtom = panelDetailButtom;
	}

	public Panel getPanelDetailAddModulTop() {
		return panelDetailAddModulTop;
	}

	public void setPanelDetailAddModulTop(Panel panelDetailAddModulTop) {
		this.panelDetailAddModulTop = panelDetailAddModulTop;
	}

	public Panel getPanelDetailAddModulTabel() {
		return panelDetailAddModulTabel;
	}

	public void setPanelDetailAddModulTabel(Panel panelDetailAddModulTabel) {
		this.panelDetailAddModulTabel = panelDetailAddModulTabel;
	}

	public Panel getPanelDetailAddModulButtom() {
		return panelDetailAddModulButtom;
	}

	public void setPanelDetailAddModulButtom(Panel panelDetailAddModulButtom) {
		this.panelDetailAddModulButtom = panelDetailAddModulButtom;
	}

	public HelpManager getHelpManager() {
		return helpManager;
	}

	public void setHelpManager(HelpManager helpManager) {
		this.helpManager = helpManager;
	}

	public Window getWindowHeaderSearch() {
		return windowHeaderSearch;
	}

	public void setWindowHeaderSearch(Window windowHeaderSearch) {
		this.windowHeaderSearch = windowHeaderSearch;
	}

	public ModulTempHeaderSelectModel getModulTempHeaderSelectModel() {
		return modulTempHeaderSelectModel;
	}

	public void setModulTempHeaderSelectModel(
			ModulTempHeaderSelectModel modulTempHeaderSelectModel) {
		this.modulTempHeaderSelectModel = modulTempHeaderSelectModel;
	}

	public ModulTempHeaderSelectView getModulTempHeaderSelectView() {
		return modulTempHeaderSelectView;
	}

	public void setModulTempHeaderSelectView(
			ModulTempHeaderSelectView modulTempHeaderSelectView) {
		this.modulTempHeaderSelectView = modulTempHeaderSelectView;
	}

	public Window getWindowModulSearch() {
		return windowModulSearch;
	}

	public void setWindowModulSearch(Window windowModulSearch) {
		this.windowModulSearch = windowModulSearch;
	}

	public ModulSelectModel getModulSelectModel() {
		return modulSelectModel;
	}

	public void setModulSelectModel(ModulSelectModel modulSelectModel) {
		this.modulSelectModel = modulSelectModel;
	}

	public ModulSelectView getModulSelectView() {
		return modulSelectView;
	}

	public void setModulSelectView(ModulSelectView modulSelectView) {
		this.modulSelectView = modulSelectView;
	}
	
	
	
	
}
