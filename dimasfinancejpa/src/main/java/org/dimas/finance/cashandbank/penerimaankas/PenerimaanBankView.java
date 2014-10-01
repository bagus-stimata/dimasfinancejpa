package org.dimas.finance.cashandbank.penerimaankas;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.dimas.finance.model.Bbankdetail;
import org.dimas.finance.model.Bkbdetail;
import org.dimas.finance.model.BkbdetailPK;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.util.HelpManager;

import com.vaadin.client.ui.Field;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PenerimaanBankView extends CustomComponent{

	private PenerimaanBankModel model;
	
	private VerticalLayout content = new VerticalLayout();
	
	//KOMPONEN HEADER atau UTAMA
	private TextField fieldRefno = new TextField("NO. DOK");
	private ComboBox comboDivision = new ComboBox("DIVISION");	
	private DateField dateFieldEntryDate = new DateField("TGL ENTRY");
	private DateField dateFieldTransDate = new DateField("TGL TRANSAKSI");
	private TextField fieldNotes = new TextField("NOTES");
	private CheckBox checkClosing = new CheckBox("CLOSING");
	
	private Button btnAddForm= new Button("Add New");
	private Button btnDeleteForm= new Button("Delete");
	private Button btnSearchForm = new Button("Search");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	
	//KOMPONEN DETAIL
	private Button btnAdd = new Button("Add");
	private Button btnEdit = new Button("Edit");
	private Button btnRem = new Button("Remove");
	private Table tableDetail;
	
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
	private Panel panelDetailAddTop = new Panel();
	private Panel panelDetailAddTabel = new Panel();
	private Panel panelDetailAddButtom = new Panel();
	
	//Help Manager	
    private HelpManager helpManager;
	
	public PenerimaanBankView(PenerimaanBankModel model){
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
			        		return "GL";
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
		panelDetailAddTop.setSizeFull();
		panelDetailAddTabel.setSizeFull();
		panelDetailAddButtom.setSizeFull();
		
		//INIT COMPONENT ATAS
		btnSeparator1.setEnabled(false);
		btnSeparator2.setEnabled(false);
		
		//COMPONEN ATAS or HEADER		
//		btnAddForm.setWidth("170px");
//		btnDeleteForm.setWidth("120px");
//		btnSearchForm.setWidth("120px");
		
		HorizontalLayout layoutHeader = new HorizontalLayout();
		layoutHeader.setSizeFull();
		
		HorizontalLayout layoutHeaderLeft = new HorizontalLayout();
		HorizontalLayout layoutHeaderRight = new HorizontalLayout();
		
		//PERMAK FIELD
		fieldRefno.setNullRepresentation("");
		fieldNotes.setNullRepresentation("");
		
		layoutHeaderLeft.addComponent(fieldRefno);
		layoutHeaderLeft.addComponent(comboDivision);
		layoutHeaderLeft.addComponent(dateFieldEntryDate);
		layoutHeaderLeft.addComponent(dateFieldTransDate);
		layoutHeaderLeft.addComponent(fieldNotes);
		layoutHeaderLeft.addComponent(checkClosing);
		

//		layoutHeaderLeft.addComponent(btnAddForm);
//		layoutHeaderLeft.addComponent(btnDeleteForm);
//		layoutHeaderLeft.addComponent(btnSearchForm);
		layoutHeaderRight.addComponent(btnAddForm);
		layoutHeaderRight.addComponent(btnDeleteForm);
		layoutHeaderRight.addComponent(btnSearchForm);
		layoutHeaderRight.setSpacing(true);
		layoutHeader.addComponent(layoutHeaderLeft);
		layoutHeader.addComponent(layoutHeaderRight);
		
		layoutHeaderLeft.setComponentAlignment(checkClosing, Alignment.BOTTOM_CENTER);
		layoutHeader.setComponentAlignment(layoutHeaderLeft, Alignment.BOTTOM_CENTER);
		layoutHeader.setComponentAlignment(layoutHeaderRight, Alignment.BOTTOM_RIGHT);
		layoutHeader.setExpandRatio(layoutHeaderLeft, 5);
		layoutHeader.setExpandRatio(layoutHeaderRight, 2);
		
		//KOMPONEN BAWAH or DETAIL
		VerticalLayout layoutDetail = new VerticalLayout();
//		layoutDetail.setSizeFull();
		
		HorizontalLayout layoutDetailTop = new HorizontalLayout();
		HorizontalLayout layoutDetailTable = new HorizontalLayout();
		layoutDetailTable.setSizeFull();
		HorizontalLayout layoutDetailButtom = new HorizontalLayout();
		
		layoutDetailTop.addComponent(btnAdd);
		layoutDetailTop.addComponent(btnEdit);
		layoutDetailTop.addComponent(btnRem);
		layoutDetailTop.setSpacing(true);
		
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
//		model.getBeanItemContainerBkbDetail().sort(new Object[] { "debetkredit"}, new boolean[] { true });
		tableDetail.setContainerDataSource(model.getBeanItemContainerDetail());
		
		setTableDetailProperties();
		
		setDisplayTableFooter();
		
		bindAndBuildFieldGroupComponent();
		
		setFormButtonAndTextState();
		
		
	}
	public void setDisplayTableFooter(){
		Collection itemIds =  model.getBeanItemContainerDetail().getItemIds();
		double sumAmount = 0.0;
		double sumDebet = 0.0;
		double sumKredit = 0.0;
		
		for (Object itemId: itemIds){
			Bbankdetail itemDetail = new Bbankdetail();
			itemDetail = model.getBeanItemContainerDetail().getItem(itemId).getBean();
			sumAmount += itemDetail.getAmount();
			if (itemDetail.getDebetkredit().equals("D")){
				sumDebet += itemDetail.getAmount();
			} else if (itemDetail.getDebetkredit().equals("K")){
				sumKredit += itemDetail.getAmount();
			}
		}
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(0);
		nf.setMinimumFractionDigits(0);
		
		tableDetail.setColumnFooter("id", "*Jumlah Record: " + itemIds.size());
		tableDetail.setColumnFooter("amount", nf.format(sumAmount));
		
	}
	
	public void setVisibleTableDetailProperties(Object... tablePropertyIds) {
		tableDetail.setVisibleColumns(tablePropertyIds);		
	}
	
	public void setTableDetailProperties(){

		setVisibleTableDetailProperties("selected", "urut", "id","description", "accountBean", 
				"bbanktranstypeBean",  "debetkredit",
				"amount", "custname", "gl", "bbankheaderBean", "divisionBean", "customerBean");
		
		tableDetail.setColumnCollapsingAllowed(true);
		try{
			tableDetail.setColumnCollapsed("accountBean", true);
			tableDetail.setColumnCollapsed("custname", true);
//			tableDetail.setColumnCollapsed("bkbtranstypeBean", true);
			tableDetail.setColumnCollapsed("bbankheaderBean", true);
			
		} catch(Exception ex){}
		
		//ALIGNMENT
		tableDetail.setColumnAlignment("selected", Align.CENTER);
		tableDetail.setColumnAlignment("debetkredit", Align.CENTER);
		tableDetail.setColumnAlignment("amount", Align.RIGHT);
		
		//SET HEADER
		tableDetail.setColumnHeader("selected", "<input type='checkbox'/>");		
		tableDetail.setColumnHeader("urut", "No");
		tableDetail.setColumnHeader("id", "DIV-REFNO");
		tableDetail.setColumnHeader("description", "DESCRIPTION");
		tableDetail.setColumnHeader("bbanktranstypeBean", "TIPE TRANSAKSI");
		tableDetail.setColumnHeader("accountBean", "ACCOUNT");
		tableDetail.setColumnHeader("debetkredit", "D/K");
		tableDetail.setColumnHeader("amount", "AMOUNT/JML NOMINAL");
		tableDetail.setColumnHeader("divisionBean", "DIVISI");
		tableDetail.setColumnHeader("customerBean", "CUSTOMER");
		tableDetail.setColumnHeader("gl", "G/L");
		
//		table.setColumnExpandRatio("selected", 2);
//		table.setColumnExpandRatio("recapno", 3);
		
		tableDetail.sort(new Object[] { "urut"}, new boolean[] { true });		
				
	}
	
	public void bindAndBuildFieldGroupComponent(){
		comboDivision.setContainerDataSource(model.getBeanItemContainerDivision());
		comboDivision.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboDivision.setNullSelectionAllowed(false);
			
		model.getBinderHeader().bind(fieldRefno, "id.refno");
		model.getBinderHeader().bind(comboDivision, "divisionBean");
		model.getBinderHeader().bind(dateFieldEntryDate, "entrydate");
		model.getBinderHeader().bind(dateFieldTransDate, "transdate");
		model.getBinderHeader().bind(fieldNotes, "notes");
		model.getBinderHeader().bind(checkClosing, "closing");
	
	}
	public void bindAndBuildFieldGroupComponentItem(){
	}	
	
	private Window windowHeaderSearch = new Window();	
	
	private PenerimaanBankHeaderSelectModel headerSelectModel; 
	private PenerimaanBankHeaderSelectView headerSelectView;
	
	//WINDOW HEADER SELECT
	public void buildWindowHeaderSelect(){
		
		//Create window
		windowHeaderSearch = new Window();
		windowHeaderSearch.setModal(true);
		windowHeaderSearch.center();
		windowHeaderSearch.setStyleName("login-layout");
		windowHeaderSearch.setWidth("800px");
		windowHeaderSearch.setHeight("600px");
		
		//INITIAL DATA TO PASS
		
		headerSelectModel = new PenerimaanBankHeaderSelectModel();
		headerSelectView = new PenerimaanBankHeaderSelectView(headerSelectModel);
		
		PenerimaanBankHeaderSelectPresenter modulTempHeaderSelectPresenter = new PenerimaanBankHeaderSelectPresenter(
				headerSelectModel, headerSelectView);		
		headerSelectView.setSizeFull();		
		
		windowHeaderSearch.setContent(headerSelectView);
		
		getUI().addWindow(windowHeaderSearch);
		
		
	}
	public void destroyWindowHeaderSelect(){		
		windowHeaderSearch.close();
	}
	
	private Window windowItemAdd = new Window();	
	
	private PenerimaanBankItemAddModel itemAddModel; 
	private PenerimaanBankItemAddView itemAddView;
	private PenerimaanBankItemAddPresenter itemAddPresenter;
	
	//WINDOW HEADER SELECT
	public void buildWindowItemAdd(String operationStatus){
		//Create window
		windowItemAdd = new Window();
		windowItemAdd.setModal(true);
		windowItemAdd.center();
		windowItemAdd.setStyleName("login-layout");
		windowItemAdd.setWidth("1200px");
		windowItemAdd.setHeight("150px");
		
		//INITIAL DATA TO PASS		
		itemAddModel = new PenerimaanBankItemAddModel();
		itemAddModel.itemHeader = model.getItemHeader();
		itemAddModel.itemDetail = model.getItemDetail();	
		itemAddModel.setOperationStatus(operationStatus);
		if (operationStatus.equals(EnumFormOperationStatus.ADDING.getStrCode())){
			Bbankdetail newDetail = new Bbankdetail();
			itemAddModel.itemDetail = newDetail;
		}
		itemAddModel.getBinderItemDetail().setItemDataSource(itemAddModel.getItemDetail());
		itemAddView = new PenerimaanBankItemAddView(itemAddModel);
		
		itemAddPresenter = new PenerimaanBankItemAddPresenter(
				itemAddModel, itemAddView);
		
		itemAddView.setSizeFull();		
		
		windowItemAdd.setContent(itemAddView);		
		getUI().addWindow(windowItemAdd);
		
	}
	public void destroyWindowItemAdd(){
		windowItemAdd.close();
	}

	public void setFormButtonAndTextState(){
		//KODE REFNO SELALU READ ONLY KARENA OTOMATIS
//		fieldUserId.setReadOnly(true);
		
		if (model.getOperationStatus().equals(EnumFormOperationStatus.OPEN.getStrCode())){
			btnAddForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);
			btnSearchForm.setEnabled(true);			
			comboDivision.setReadOnly(true);
		} else if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
			btnAddForm.setEnabled(false);
			btnDeleteForm.setEnabled(false);
			btnSearchForm.setEnabled(false);
			comboDivision.setReadOnly(false);
		}else if (model.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
			//EDITING SAMA DENGAN OPEN
			btnAddForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);
			btnSearchForm.setEnabled(true);
			comboDivision.setReadOnly(true);
		}		
		
		fieldRefno.setReadOnly(true);
		checkClosing.setReadOnly(true);

		//JIKA DIVISION KOSONG MAKA TIDAK BISA MEN SAVE DAN MENAMBAH DETAIL
		Division div = new Division();
		String strDiv = null;
		try{
			if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
				div = model.getNewItemHeader().getDivisionBean();
			} else {
				div = model.getItemHeader().getDivisionBean();
			}
			strDiv = div.getId();
		} catch(Exception ex){}
		
		String refno = null;	
		try{
			if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
				refno = model.getNewItemHeader().getId().getRefno().toString().trim();
			} else {
				refno = model.getItemHeader().getId().getRefno().toString().trim();				
				strDiv = div.getId();
			}
		} catch(Exception ex){}
		
//		System.out.println( model.getOperationStatus() + "\t" + comboDivision.getValue() + "\t" + div + "\t" + strDiv + "\t" + refno);
		
		if (strDiv==null || refno==null){
			btnAdd.setEnabled(false);
			btnEdit.setEnabled(false);
			btnRem.setEnabled(false);
			btnSaveForm.setEnabled(false);
			btnCancelForm.setEnabled(false);
		} else {
			btnAdd.setEnabled(true);
			btnEdit.setEnabled(true);
			btnRem.setEnabled(true);
			btnSaveForm.setEnabled(true);
			btnCancelForm.setEnabled(true);			
		}
		
	}

	public PenerimaanBankModel getModel() {
		return model;
	}

	public void setModel(PenerimaanBankModel model) {
		this.model = model;
	}

	public VerticalLayout getContent() {
		return content;
	}

	public void setContent(VerticalLayout content) {
		this.content = content;
	}

	public TextField getFieldRefno() {
		return fieldRefno;
	}

	public void setFieldRefno(TextField fieldRefno) {
		this.fieldRefno = fieldRefno;
	}

	public ComboBox getComboDivision() {
		return comboDivision;
	}

	public void setComboDivision(ComboBox comboDivision) {
		this.comboDivision = comboDivision;
	}

	public DateField getDateFieldEntryDate() {
		return dateFieldEntryDate;
	}

	public void setDateFieldEntryDate(DateField dateFieldEntryDate) {
		this.dateFieldEntryDate = dateFieldEntryDate;
	}

	public DateField getDateFieldTransDate() {
		return dateFieldTransDate;
	}

	public void setDateFieldTransDate(DateField dateFieldTransDate) {
		this.dateFieldTransDate = dateFieldTransDate;
	}

	public TextField getFieldNotes() {
		return fieldNotes;
	}

	public void setFieldNotes(TextField fieldNotes) {
		this.fieldNotes = fieldNotes;
	}

	public CheckBox getCheckClosing() {
		return checkClosing;
	}

	public void setCheckClosing(CheckBox checkClosing) {
		this.checkClosing = checkClosing;
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

	public Button getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(Button btnEdit) {
		this.btnEdit = btnEdit;
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

	public Panel getPanelDetailAddTop() {
		return panelDetailAddTop;
	}

	public void setPanelDetailAddTop(Panel panelDetailAddTop) {
		this.panelDetailAddTop = panelDetailAddTop;
	}

	public Panel getPanelDetailAddTabel() {
		return panelDetailAddTabel;
	}

	public void setPanelDetailAddTabel(Panel panelDetailAddTabel) {
		this.panelDetailAddTabel = panelDetailAddTabel;
	}

	public Panel getPanelDetailAddButtom() {
		return panelDetailAddButtom;
	}

	public void setPanelDetailAddButtom(Panel panelDetailAddButtom) {
		this.panelDetailAddButtom = panelDetailAddButtom;
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

	public PenerimaanBankHeaderSelectModel getHeaderSelectModel() {
		return headerSelectModel;
	}

	public void setHeaderSelectModel(
			PenerimaanBankHeaderSelectModel headerSelectModel) {
		this.headerSelectModel = headerSelectModel;
	}

	public PenerimaanBankHeaderSelectView getHeaderSelectView() {
		return headerSelectView;
	}

	public void setHeaderSelectView(PenerimaanBankHeaderSelectView headerSelectView) {
		this.headerSelectView = headerSelectView;
	}

	public Window getWindowItemAdd() {
		return windowItemAdd;
	}

	public void setWindowItemAdd(Window windowItemAdd) {
		this.windowItemAdd = windowItemAdd;
	}

	public PenerimaanBankItemAddModel getItemAddModel() {
		return itemAddModel;
	}

	public void setItemAddModel(PenerimaanBankItemAddModel itemAddModel) {
		this.itemAddModel = itemAddModel;
	}

	public PenerimaanBankItemAddView getItemAddView() {
		return itemAddView;
	}

	public void setItemAddView(PenerimaanBankItemAddView itemAddView) {
		this.itemAddView = itemAddView;
	}

	public PenerimaanBankItemAddPresenter getItemAddPresenter() {
		return itemAddPresenter;
	}

	public void setItemAddPresenter(PenerimaanBankItemAddPresenter itemAddPresenter) {
		this.itemAddPresenter = itemAddPresenter;
	}

	

	
}
