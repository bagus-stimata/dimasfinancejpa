package org.dimas.finance.ar;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.util.HelpManager;
import org.dimas.finance.warehouse.WhRecapSelectModel;
import org.dimas.finance.warehouse.WhRecapSelectPresenter;
import org.dimas.finance.warehouse.WhRecapSelectView;

import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Property;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.Reindeer;

public class PelunasanCanvasView extends CustomComponent {
	
	private PelunasanCanvasModel model;
	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	private Form form;
	private FieldFactory fieldFactory;

	private Class<Arinvoice> entityClass;
	
	private Object[] formPropertyIds;
	private String operationStatus;

	//Additional Component	
	private TextField fieldSearchByRekap;
	private Button btnSelectRekapNo = new Button("F");	
	private ComboBox fieldSearchComboDivisi;
	private TextField fieldSearchByInvoice;	
	private ComboBox fieldSearchComboLunas;
	
	private ComboBox fieldSearchComboSalesman;	
	
	private DateField fieldSearchByDateInvoiceFrom;
	private DateField fieldSearchByDateInvoiceTo;

	private Button btnSearch;
	private Button btnReload;
	private Button btnLunaskan;
	private Button btnBatalLunaskan;
	
	private Button btnPrint;
	private Button btnHelp;	
	private Button btnSeparator1;
	private Button btnSeparator2;
	
	//Panel
	private Panel panelUtama;
	private Panel panelTop;
	private Panel panelTabel;
	private Panel panelForm;

	//Help Manager	
    private HelpManager helpManager;
    
    //TEKS FIELD FOR SELECTED ROW
    private TextField fieldSelectedCount = new TextField("Rec Selected: ");
    private TextField fieldTunaiCount = new TextField("Tunai: ");
    private TextField fieldKreditCount = new TextField("Kredit: ");
    private TextField fieldTunaiSum= new TextField("Nilai Tunai: ");
    private TextField fieldKreditSum = new TextField("Nilai Kredit: ");
    private TextField fieldToCount = new TextField("TO: ");
    private TextField fieldCanvasCount = new TextField("Canvas: ");
    private TextField fieldToSum= new TextField("Nilai TO: ");
    private TextField fieldCanvasSum = new TextField("Nilai Canvas: ");
    private TextField fieldAmountSum= new TextField("Nilai Faktur(TO+CVS): ");
    private TextField fieldAmountPaySum = new TextField("BAYAR: ");
	
	public PelunasanCanvasView(PelunasanCanvasModel model){
		this.model = model;		
		initComponent();
		initFieldFactory();
		buildView();
		
		//Set Awal Status form
		operationStatus = EnumFormOperationStatus.OPEN.getStrCode();
		
	}
	public void initComponent(){
//		table = new Table("Table:", model.getTableJpaContainer());		
		
		table = new Table("Table: ") {
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
		
		
		fieldSearchByRekap = new TextField("NO. REKAP");
		fieldSearchByRekap.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByRekap.setWidth("90px");
		
		fieldSearchByInvoice = new TextField("INVOICE");
		fieldSearchByInvoice.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByInvoice.setWidth("90px");

		fieldSearchByDateInvoiceFrom = new DateField("Tgl Invoice Mulai");
		fieldSearchByDateInvoiceFrom.setDateFormat("dd-MM-yyyy");
//		fieldSearchByDateInvoiceFrom.setWidth("100px");
		fieldSearchByDateInvoiceFrom.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByDateInvoiceTo = new DateField("Sampai dengan");
//		fieldSearchByDateInvoiceTo.setWidth("100px");
		fieldSearchByDateInvoiceTo.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByDateInvoiceTo.setDateFormat("dd-MM-yyyy");

		fieldSearchComboDivisi = new ComboBox("DIVISI");
		fieldSearchComboDivisi.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboDivisi.setWidth("120px");

		fieldSearchComboSalesman = new ComboBox("SALESMAN");
		fieldSearchComboSalesman.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboSalesman.setWidth("120px");
		
		fieldSearchComboLunas = new ComboBox("Lunas/Belum");
		fieldSearchComboLunas.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);				
		fieldSearchComboLunas.setNullSelectionAllowed(false);
		
		//FOOTER SELECTED
		fieldSelectedCount.setWidth("100px");
		fieldTunaiCount.setWidth("50px");
		fieldKreditCount.setWidth("50px");
		fieldTunaiSum.setWidth("110px");
		fieldKreditSum.setWidth("110px");
		fieldToCount.setWidth("50px");
		fieldCanvasCount.setWidth("50px");
		fieldToSum.setWidth("110px");
		fieldCanvasSum.setWidth("110px");
		fieldAmountSum.setWidth("120px");
		fieldAmountPaySum.setWidth("110px");
		
		btnSearch = new Button("Search");
		btnSearch.setStyleName(Reindeer.BUTTON_SMALL);
		btnReload = new Button("Reload");
		btnReload.setStyleName(Reindeer.BUTTON_SMALL);
		btnLunaskan = new Button("Lunaskan!!");
		btnLunaskan.setStyleName(Reindeer.BUTTON_SMALL);
		btnBatalLunaskan = new Button("Batalkan");
		btnBatalLunaskan.setStyleName(Reindeer.BUTTON_SMALL);
		
		
		
		btnPrint = new Button("Print");
		btnHelp = new Button("Help");
		
		btnSeparator1 = new Button("");
		btnSeparator1.setEnabled(false);
		btnSeparator2 = new Button("::");
		btnSeparator2.setEnabled(false);
		
		
	}
	public void initFieldFactory(){
		// Can't access the editable components from the table so
		// must store the information
	}
	
	public void buildView(){
		content.setSizeFull();
		
		//Inisialisasi Panel 
		setSizeFull();
		panelUtama = new Panel(getCaption());
		panelUtama.setSizeFull();

		panelTop = new Panel();
		panelTop.setSizeFull();
		panelTabel = new Panel();
		panelTabel.setSizeFull();
		panelForm = new Panel();
		panelForm.setSizeFull();

		//Init komponen tengah
		table.setSizeFull();
		table.setSelectable(true);
//		table.addValueChangeListener(this);
		table.setImmediate(true);
		table.setBuffered(false);
//		table.addActionHandler(this);		
		table.setFooterVisible(true);
		
		//Deklarasi Button dan Listener	
//		addButton = new Button("Add New");		
//		deleteButton = new Button("Delete");
//		commit = new Button("Save");		
//		discard = new Button("Cancel");
		
		//Init komponen bawah
		form = new Form();
		form.setVisible(false);
		form.setBuffered(true);
		form.setImmediate(false);
		
		//DEKLARASI LAYOUT
		//KOMPONEN ATAS
		VerticalLayout layoutTopUtama = new VerticalLayout();
		layoutTopUtama.setSizeFull();
		layoutTopUtama.addComponent(panelTop);
		
		HorizontalLayout layoutTopInner = new HorizontalLayout();		
		panelTop.setContent(layoutTopInner);
		
		layoutTopInner.addComponent(fieldSearchByRekap);
		layoutTopInner.addComponent(btnSelectRekapNo);
		layoutTopInner.addComponent(fieldSearchComboDivisi);
		layoutTopInner.addComponent(fieldSearchByInvoice);	
		layoutTopInner.addComponent(fieldSearchComboSalesman);
		layoutTopInner.addComponent(fieldSearchComboLunas);
		
		layoutTopInner.addComponent(fieldSearchByDateInvoiceFrom);
		layoutTopInner.addComponent(fieldSearchByDateInvoiceTo);

		layoutTopInner.setComponentAlignment(btnSelectRekapNo, Alignment.BOTTOM_CENTER);
		
		
		layoutTopInner.addComponent(btnSearch);
		layoutTopInner.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
//		layoutTop.addComponent(btnReload);
//		layoutTop.setComponentAlignment(btnReload, Alignment.BOTTOM_CENTER);
		layoutTopInner.addComponent(btnSeparator1);
		layoutTopInner.setComponentAlignment(btnSeparator1, Alignment.BOTTOM_CENTER);
		layoutTopInner.addComponent(btnLunaskan);
		layoutTopInner.setComponentAlignment(btnLunaskan, Alignment.BOTTOM_CENTER);
		layoutTopInner.addComponent(btnBatalLunaskan);
		layoutTopInner.setComponentAlignment(btnBatalLunaskan, Alignment.BOTTOM_CENTER);
		

		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
		verticalSplitPanel.setSizeFull();		
		verticalSplitPanel.setSplitPosition(85);
		
		
		panelTabel.setContent(table);
		
//		((HorizontalLayout) form.getFooter()).setSpacing(true);		
		
		HorizontalLayout layoutFooter = new HorizontalLayout();
		layoutFooter.addComponent(fieldSelectedCount);
		layoutFooter.addComponent(fieldTunaiCount);
		layoutFooter.addComponent(fieldKreditCount);
		layoutFooter.addComponent(fieldTunaiSum);
		layoutFooter.addComponent(fieldKreditSum);
		layoutFooter.addComponent(fieldToCount);
		layoutFooter.addComponent(fieldCanvasCount);
		layoutFooter.addComponent(fieldToSum);
		layoutFooter.addComponent(fieldCanvasSum);
		layoutFooter.addComponent(fieldAmountSum);
		layoutFooter.addComponent(fieldAmountPaySum);
		layoutFooter.setSpacing(true);
		panelForm.setContent(layoutFooter);
		
		verticalSplitPanel.setFirstComponent(panelTabel);		
		verticalSplitPanel.setSecondComponent(panelForm);

		content.addComponent(new Label("***"));
		content.addComponent(layoutTopUtama);
		content.addComponent(verticalSplitPanel);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		
		content.setExpandRatio(layoutTopUtama, 1);
		content.setExpandRatio(verticalSplitPanel, 9);
		
		btnSelectRekapNo.setVisible(false);
		
	}
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setVisibleFormProperties(Object... formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
		form.setVisibleItemProperties(formPropertyIds);
	}
	public void setTableProperties(){
		setVisibleTableProperties("selected", "recapno", "id", "tunaikredit", "salesmanBean", 
				"amount",   "amountpay", "lunas","nopo",  
				"invoicedate", "term","duedate" , "terkirim", "tertundacounter", 
				"actualduedate",  "ppn", "disc1", "disc2",
				"spname", "custname", "divisionBean", "customerBean", 
				"disc3", "lockupdate", "orderdate", "tipejual");
		
		
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("nopo", true);
			table.setColumnCollapsed("lockupdate", true);
			table.setColumnCollapsed("orderdate", true);
			table.setColumnCollapsed("disc3", true);
			table.setColumnCollapsed("tipejual", true);
			
		} catch(Exception ex){}
		
		//ALIGNMENT
		table.setColumnAlignment("selected", Align.CENTER);
		table.setColumnAlignment("tunaikredit", Align.CENTER);
		table.setColumnAlignment("term", Align.CENTER);
		table.setColumnAlignment("amount", Align.RIGHT);
		table.setColumnAlignment("disc1", Align.RIGHT);
		table.setColumnAlignment("disc2", Align.RIGHT);
		table.setColumnAlignment("amountpay", Align.RIGHT);
		table.setColumnAlignment("lunas", Align.CENTER);
		table.setColumnAlignment("terkirim", Align.CENTER);
		table.setColumnAlignment("tertundacounter", Align.CENTER);
		
		//set header
		table.setColumnHeader("selected", "<input type='checkbox'/>");
		table.setColumnHeader("recapno", "REKAP");
		table.setColumnHeader("id", "INVOICE-DIV-F/R");
		table.setColumnHeader("custname", "CUSTOMER TRANS");
		table.setColumnHeader("spname", "SALESMAN TRANS");
		table.setColumnHeader("amount", "NOMINAL+PPN");
		table.setColumnHeader("amountpay", "TERBAYAR");
		table.setColumnHeader("salesmanBean", "NAMA SALES ACTUAL");
		table.setColumnHeader("customerBean", "NAMA CUST ACTUAL");
		table.setColumnHeader("tertundacounter", "TT");
		table.setColumnHeader("terkirim", "KIRIM");
		table.setColumnHeader("tunaikredit", "T/K");
		table.setColumnHeader("lunas", "LNS");
		table.setColumnHeader("term", "TOP");
		table.setColumnHeader("tipejual", "TO/TF/C");
		
		
//		table.setColumnExpandRatio("selected", 2);
//		table.setColumnExpandRatio("recapno", 3);
//		table.setColumnExpandRatio("id", 5);
//		table.setColumnExpandRatio("tunaikredit", 1);
//		table.setColumnExpandRatio("salesmanBean", 5);
//		table.setColumnExpandRatio("nopo", 1);
//		table.setColumnExpandRatio("invoicedate", 3);
//		table.setColumnExpandRatio("term", 1);
//		table.setColumnExpandRatio("duedate", 3);
//		table.setColumnExpandRatio("terkirim", 1);
//		table.setColumnExpandRatio("tertundacounter", 1);
//		table.setColumnExpandRatio("actualduedate", 3);
//		table.setColumnExpandRatio("amount", 4);
//		table.setColumnExpandRatio("amountpay", 4);
//		table.setColumnExpandRatio("lunas", 1);
//		table.setColumnExpandRatio("ppn", 3);
//		table.setColumnExpandRatio("disc1", 3);
//		table.setColumnExpandRatio("disc2", 3);
//		table.setColumnExpandRatio("spname", 4);
//		table.setColumnExpandRatio("custname", 4);
//		table.setColumnExpandRatio("divisionBean", 4);
//		table.setColumnExpandRatio("customerBean", 4);
//		table.setColumnExpandRatio("disc3", 3);
//		table.setColumnExpandRatio("lockupdate", 1);
//		table.setColumnExpandRatio("orderdate", 3);
				
	}
	public void setFormProperties(){
//		setVisibleFormProperties("arinvoicePK.id");
	}
	public void setDisplaySearchComponent(){
		getFieldSearchComboDivisi().setContainerDataSource(model.getBeanItemContainerDivision());
		getFieldSearchComboDivisi().setNullSelectionAllowed(false);
		getFieldSearchComboDivisi().select(model.getBeanItemContainerDivision().getIdByIndex(0));

		getFieldSearchComboSalesman().setContainerDataSource(model.getBeanItemContainerSalesman());
		
		fieldSearchComboLunas.addItem("B");
		fieldSearchComboLunas.setItemCaption("B", "Belum");
		fieldSearchComboLunas.addItem("L");
		fieldSearchComboLunas.setItemCaption("L", "Lunas");
		fieldSearchComboLunas.addItem("S");
		fieldSearchComboLunas.setItemCaption("S", "Semua");
		fieldSearchComboLunas.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboLunas.setWidth("70px");
		fieldSearchComboLunas.select("B");
		
	}
	public void setDisplay(){
		//1. Refresh Table displa
//		table.setContainerDataSource(model.getTableBeanItemContainer());
		table.setContainerDataSource(model.getTableJpaContainer());
		
		//2. Jika table masih dalam kondisi di seleksi maka form masih diisi dengan hasil seleksi
		
		setTableProperties();
		
		setDisplayFooter();
	}
	
	public void setDisplayFooter(){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(0);
		
		//3. Hitung Total & Jumlah Record dll
		double sumAmount=0 ;
		double sumAmountPay=0;
		int countTerkirim=0;
		int countLunas=0;
		int countTertunda=0;
		
		int countSelected=0;
		int countTunaiSelected=0;
		int countKreditSelected=0;
		double sumTunaiSelected=0;
		double sumKreditSelected=0;
		int countToSelected=0;
		int countCanvasSelected = 0;
		double sumToSelected=0;
		double sumCanvasSelected=0;
		double sumAmountSelected=0;
		double sumAmountPaySelected=0;
		
		Collection itemIds =  model.getTableJpaContainer().getItemIds();
		for (Object itemId: itemIds){
			Arinvoice item = new Arinvoice();
			item = model.getTableJpaContainer().getItem(itemId).getEntity();
			sumAmount += item.getAmount();
			sumAmountPay += item.getAmountpay();
			if (item.isLunas()==true){
				countLunas +=1;
			}
			if (item.isTerkirim()==true){
				countTerkirim+=1;
			}
			if (item.getTertundacounter()>0){
				countTertunda+=1;
			}
			
			if (item.getSelected().getValue()==true){
				countSelected += 1;
				//TUNAI/KREDIT
				if (item.getTunaikredit().equals("K")){
					countKreditSelected +=1;
					sumKreditSelected += item.getAmount();
				} else {
					countTunaiSelected += 1;				
					sumTunaiSelected += item.getAmount();
				}
				if (item.getRecapno()==null){
					countCanvasSelected +=1;
					sumCanvasSelected += item.getAmount();
				} else {
					if (item.getRecapno().trim().equals("")){
						countCanvasSelected	+=1;
						sumCanvasSelected += item.getAmount();
					} else {
						countToSelected +=1;	
						sumToSelected += item.getAmount();
					}
				}
				sumAmountSelected += item.getAmount();
				sumAmountPaySelected += (item.getAmount()-item.getAmountpay());
				
			}
			//BUAT SELECT ITEM READONLY
			item.getSelected().setReadOnly(true);
			fieldSelectedCount.setReadOnly(false);
			fieldTunaiCount.setReadOnly(false);
			fieldKreditCount.setReadOnly(false);
			fieldTunaiSum.setReadOnly(false);
			fieldKreditSum.setReadOnly(false);
			fieldToCount.setReadOnly(false);
			fieldCanvasCount.setReadOnly(false);
			fieldToSum.setReadOnly(false);
			fieldCanvasSum.setReadOnly(false);
			fieldAmountSum.setReadOnly(false);
			fieldAmountPaySum.setReadOnly(false);
			
			
			
			fieldSelectedCount.setValue(nf.format(countSelected));
			fieldTunaiCount.setValue(nf.format(countTunaiSelected));
			fieldKreditCount.setValue(nf.format(countKreditSelected));
			fieldTunaiSum.setValue(nf.format(sumTunaiSelected));
			fieldKreditSum.setValue(nf.format(sumKreditSelected));
			fieldToCount.setValue(nf.format(countToSelected));
			fieldCanvasCount.setValue(nf.format(countCanvasSelected));
			fieldToSum.setValue(nf.format(sumToSelected));
			fieldCanvasSum.setValue(nf.format(sumCanvasSelected));
			fieldAmountSum.setValue(nf.format(sumAmountSelected));
			fieldAmountPaySum.setValue(nf.format(sumAmountPaySelected));

			fieldSelectedCount.setReadOnly(true);
			fieldTunaiCount.setReadOnly(true);
			fieldKreditCount.setReadOnly(true);
			fieldTunaiSum.setReadOnly(true);
			fieldKreditSum.setReadOnly(true);
			fieldToCount.setReadOnly(true);
			fieldCanvasCount.setReadOnly(true);
			fieldToSum.setReadOnly(true);
			fieldCanvasSum.setReadOnly(true);
			fieldAmountSum.setReadOnly(true);
			
//			//DI AKTIFKAN TERUS KARENA BOLEH LEBIH BAYAR
//			fieldAmountPaySum.setReadOnly(true);
			
		}
		
		table.setColumnFooter("amount", nf.format(sumAmount));
		table.setColumnFooter("amountpay", nf.format(sumAmountPay));
		
		table.setColumnFooter("recapno", "*Record: " + itemIds.size());
		table.setColumnFooter("terkirim",  nf.format(countTerkirim));
		table.setColumnFooter("lunas",  nf.format(countLunas));
		table.setColumnFooter("tertundacounter", nf.format(countTertunda));
		
	}

	private Window windowRecapSearch = new Window();	
	
	private ArRecapSelectModel recapSelectModel; 
	private ArRecapSelectView recapSelectView;
	
	//WINDOW HEADER SELECT
	public void buildWindowRecapSelect(){
		
		//Create window
		windowRecapSearch = new Window();
		windowRecapSearch.setModal(true);
		windowRecapSearch.center();
		windowRecapSearch.setStyleName("login-layout");
		windowRecapSearch.setWidth("800px");
		windowRecapSearch.setHeight("600px");
		
		//INITIAL DATA TO PASS
		
		recapSelectModel = new ArRecapSelectModel();
		recapSelectView = new ArRecapSelectView(recapSelectModel);
		
		ArRecapSelectPresenter recapSelectPresenter = new ArRecapSelectPresenter(
				recapSelectModel, recapSelectView);		
		recapSelectView.setSizeFull();		
		
		windowRecapSearch.setContent(recapSelectView);
		
		getUI().addWindow(windowRecapSearch);
		
		
	}
	public void destroyWindowRecapSelect(){		
		windowRecapSearch.close();
	}
	
	
	
	public PelunasanCanvasModel getModel() {
		return model;
	}
	public void setModel(PelunasanCanvasModel model) {
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
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public FieldFactory getFieldFactory() {
		return fieldFactory;
	}
	public void setFieldFactory(FieldFactory fieldFactory) {
		this.fieldFactory = fieldFactory;
	}
	public Class<Arinvoice> getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(Class<Arinvoice> entityClass) {
		this.entityClass = entityClass;
	}
	public Object[] getFormPropertyIds() {
		return formPropertyIds;
	}
	public void setFormPropertyIds(Object[] formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
	}
	public String getOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}
	public TextField getFieldSearchByRekap() {
		return fieldSearchByRekap;
	}
	public void setFieldSearchByRekap(TextField fieldSearchByRekap) {
		this.fieldSearchByRekap = fieldSearchByRekap;
	}
	public ComboBox getFieldSearchComboDivisi() {
		return fieldSearchComboDivisi;
	}
	public void setFieldSearchComboDivisi(ComboBox fieldSearchComboDivisi) {
		this.fieldSearchComboDivisi = fieldSearchComboDivisi;
	}
	public TextField getFieldSearchByInvoice() {
		return fieldSearchByInvoice;
	}
	public void setFieldSearchByInvoice(TextField fieldSearchByInvoice) {
		this.fieldSearchByInvoice = fieldSearchByInvoice;
	}
	public ComboBox getFieldSearchComboLunas() {
		return fieldSearchComboLunas;
	}
	public void setFieldSearchComboLunas(ComboBox fieldSearchComboLunas) {
		this.fieldSearchComboLunas = fieldSearchComboLunas;
	}
	public DateField getFieldSearchByDateInvoiceFrom() {
		return fieldSearchByDateInvoiceFrom;
	}
	public void setFieldSearchByDateInvoiceFrom(
			DateField fieldSearchByDateInvoiceFrom) {
		this.fieldSearchByDateInvoiceFrom = fieldSearchByDateInvoiceFrom;
	}
	public DateField getFieldSearchByDateInvoiceTo() {
		return fieldSearchByDateInvoiceTo;
	}
	public void setFieldSearchByDateInvoiceTo(DateField fieldSearchByDateInvoiceTo) {
		this.fieldSearchByDateInvoiceTo = fieldSearchByDateInvoiceTo;
	}
	public Button getBtnSearch() {
		return btnSearch;
	}
	public void setBtnSearch(Button btnSearch) {
		this.btnSearch = btnSearch;
	}
	public Button getBtnReload() {
		return btnReload;
	}
	public void setBtnReload(Button btnReload) {
		this.btnReload = btnReload;
	}
	public Button getBtnLunaskan() {
		return btnLunaskan;
	}
	public void setBtnLunaskan(Button btnLunaskan) {
		this.btnLunaskan = btnLunaskan;
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
	public ComboBox getFieldSearchComboSalesman() {
		return fieldSearchComboSalesman;
	}
	public void setFieldSearchComboSalesman(ComboBox fieldSearchComboSalesman) {
		this.fieldSearchComboSalesman = fieldSearchComboSalesman;
	}
	public Button getBtnBatalLunaskan() {
		return btnBatalLunaskan;
	}
	public void setBtnBatalLunaskan(Button btnBatalLunaskan) {
		this.btnBatalLunaskan = btnBatalLunaskan;
	}
	public TextField getFieldSelectedCount() {
		return fieldSelectedCount;
	}
	public void setFieldSelectedCount(TextField fieldSelectedCount) {
		this.fieldSelectedCount = fieldSelectedCount;
	}
	public TextField getFieldTunaiCount() {
		return fieldTunaiCount;
	}
	public void setFieldTunaiCount(TextField fieldTunaiCount) {
		this.fieldTunaiCount = fieldTunaiCount;
	}
	public TextField getFieldKreditCount() {
		return fieldKreditCount;
	}
	public void setFieldKreditCount(TextField fieldKreditCount) {
		this.fieldKreditCount = fieldKreditCount;
	}
	public TextField getFieldTunaiSum() {
		return fieldTunaiSum;
	}
	public void setFieldTunaiSum(TextField fieldTunaiSum) {
		this.fieldTunaiSum = fieldTunaiSum;
	}
	public TextField getFieldKreditSum() {
		return fieldKreditSum;
	}
	public void setFieldKreditSum(TextField fieldKreditSum) {
		this.fieldKreditSum = fieldKreditSum;
	}
	public TextField getFieldToCount() {
		return fieldToCount;
	}
	public void setFieldToCount(TextField fieldToCount) {
		this.fieldToCount = fieldToCount;
	}
	public TextField getFieldCanvasCount() {
		return fieldCanvasCount;
	}
	public void setFieldCanvasCount(TextField fieldCanvasCount) {
		this.fieldCanvasCount = fieldCanvasCount;
	}
	public TextField getFieldToSum() {
		return fieldToSum;
	}
	public void setFieldToSum(TextField fieldToSum) {
		this.fieldToSum = fieldToSum;
	}
	public TextField getFieldCanvasSum() {
		return fieldCanvasSum;
	}
	public void setFieldCanvasSum(TextField fieldCanvasSum) {
		this.fieldCanvasSum = fieldCanvasSum;
	}
	public TextField getFieldAmountSum() {
		return fieldAmountSum;
	}
	public void setFieldAmountSum(TextField fieldAmountSum) {
		this.fieldAmountSum = fieldAmountSum;
	}
	public TextField getFieldAmountPaySum() {
		return fieldAmountPaySum;
	}
	public void setFieldAmountPaySum(TextField fieldAmountPaySum) {
		this.fieldAmountPaySum = fieldAmountPaySum;
	}
	public Button getBtnSelectRekapNo() {
		return btnSelectRekapNo;
	}
	public void setBtnSelectRekapNo(Button btnSelectRekapNo) {
		this.btnSelectRekapNo = btnSelectRekapNo;
	}
	public Window getWindowRecapSearch() {
		return windowRecapSearch;
	}
	public void setWindowRecapSearch(Window windowRecapSearch) {
		this.windowRecapSearch = windowRecapSearch;
	}
	public ArRecapSelectModel getRecapSelectModel() {
		return recapSelectModel;
	}
	public void setRecapSelectModel(ArRecapSelectModel recapSelectModel) {
		this.recapSelectModel = recapSelectModel;
	}
	public ArRecapSelectView getRecapSelectView() {
		return recapSelectView;
	}
	public void setRecapSelectView(ArRecapSelectView recapSelectView) {
		this.recapSelectView = recapSelectView;
	}

	
	
}
