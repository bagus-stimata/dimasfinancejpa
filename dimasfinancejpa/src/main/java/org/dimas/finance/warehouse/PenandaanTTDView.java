package org.dimas.finance.warehouse;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.util.HelpManager;

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

public class PenandaanTTDView extends CustomComponent {
	
	private PenandaanTTDModel model;
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
	private ComboBox fieldSearchComboTertunda;
	
	private ComboBox fieldSearchComboSalesman;	
	
	private DateField fieldSearchByDateInvoiceFrom;
	private DateField fieldSearchByDateInvoiceTo;

	private Button btnSearch;
	private Button btnReload;
	private Button btnSetTertunda;
	private Button btnBatalTertunda;
	
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
	
	public PenandaanTTDView(PenandaanTTDModel model){
		this.model = model;		
		initContainer();
		initFieldFactory();
		buildView();
		
		//Set Awal Status form
		operationStatus = EnumFormOperationStatus.OPEN.getStrCode();
		
	}
	
	public void initContainer(){
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
		panelTabel = new Panel();
		panelTabel.setSizeFull();
		panelForm = new Panel();

		//Init Komponen atas
		
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
		
		fieldSearchComboTertunda = new ComboBox("TERTUNDA/Tidak");
		fieldSearchComboTertunda.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);				
		fieldSearchComboTertunda.setNullSelectionAllowed(false);
		
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
		
		btnSearch = new Button("Search");
		btnSearch.setStyleName(Reindeer.BUTTON_SMALL);
		btnReload = new Button("Reload");
		btnReload.setStyleName(Reindeer.BUTTON_SMALL);
		btnSetTertunda = new Button("Tandai TERTUNDA");
		btnSetTertunda.setStyleName(Reindeer.BUTTON_SMALL);
		btnBatalTertunda = new Button("BATAL!!");
		btnBatalTertunda.setStyleName(Reindeer.BUTTON_SMALL);
		
		
		
		btnPrint = new Button("Print");
		btnHelp = new Button("Help");
		
		btnSeparator1 = new Button("");
		btnSeparator1.setEnabled(false);
		btnSeparator2 = new Button("::");
		btnSeparator2.setEnabled(false);
		
		//Init komponen bawah
		form = new Form();
		form.setVisible(false);
		form.setBuffered(true);
		form.setImmediate(false);
		
		//DEKLARASI LAYOUT
		//KOMPONEN ATAS
		HorizontalLayout layoutTop = new HorizontalLayout();		
	
		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
		verticalSplitPanel.setSizeFull();		
		verticalSplitPanel.setSplitPosition(85);
		
		layoutTop.addComponent(fieldSearchByRekap);
		layoutTop.addComponent(btnSelectRekapNo);
		layoutTop.addComponent(fieldSearchComboDivisi);
		layoutTop.addComponent(fieldSearchByInvoice);	
		layoutTop.addComponent(fieldSearchComboSalesman);
		layoutTop.addComponent(fieldSearchComboTertunda);
		
		layoutTop.addComponent(fieldSearchByDateInvoiceFrom);
		layoutTop.addComponent(fieldSearchByDateInvoiceTo);
		
		layoutTop.setComponentAlignment(btnSelectRekapNo, Alignment.BOTTOM_CENTER);
		
		HorizontalLayout horBut = new HorizontalLayout();
		
		
		layoutTop.addComponent(btnSearch);
		layoutTop.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
//		layoutTop.addComponent(btnReload);
//		layoutTop.setComponentAlignment(btnReload, Alignment.BOTTOM_CENTER);
		layoutTop.addComponent(btnSeparator1);
		layoutTop.setComponentAlignment(btnSeparator1, Alignment.BOTTOM_CENTER);
		layoutTop.addComponent(btnSetTertunda);
		layoutTop.setComponentAlignment(btnSetTertunda, Alignment.BOTTOM_CENTER);
		layoutTop.addComponent(btnBatalTertunda);
		layoutTop.setComponentAlignment(btnBatalTertunda, Alignment.BOTTOM_CENTER);
		
		
		panelTop.setContent(layoutTop);
		
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
		content.addComponent(panelTop);
		content.addComponent(verticalSplitPanel);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		
		content.setExpandRatio(verticalSplitPanel, 1);
		
		//VISIBLE COMPONEN
		fieldAmountPaySum.setVisible(false);
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	
	public void setVisibleFormProperties(Object... formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
		form.setVisibleItemProperties(formPropertyIds);
	}
	
	public void setTableProperties(){
		setVisibleTableProperties("selected", "recapno", "id", "tipejual", "tunaikredit", "salesmanBean", 
				"amount",   "amountpay", "terkirim", "tertundacounter",  "lunas","nopo",  
				"invoicedate", "term","duedate" ,
				"actualduedate",  "ppn", "disc1", "disc2",
				"spname", "customerBean", "custname", "divisionBean", 
				"disc3", "lockupdate", "orderdate");
		
		
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("nopo", true);
			table.setColumnCollapsed("lockupdate", true);
			table.setColumnCollapsed("orderdate", true);
			table.setColumnCollapsed("disc3", true);
			table.setColumnCollapsed("lunas", true);
			table.setColumnCollapsed("amountpay", true);
			
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
		table.setColumnAlignment("tipejual", Align.CENTER);
		
		//SET HEADER
		table.setColumnHeader("selected", "<input type='checkbox'/>");
		table.setColumnHeader("recapno", "REKAP");
		table.setColumnHeader("id", "INVOICE-DIV-F/R");
		table.setColumnHeader("custname", "CUSTOMER TRANS");
		table.setColumnHeader("spname", "SALESMAN TRANS");
		table.setColumnHeader("amount", "NOMINAL+PPN");
		table.setColumnHeader("amountpay", "TERBAYAR");
		table.setColumnHeader("salesmanBean", "SALES ACTUAL");
		table.setColumnHeader("customerBean", "CUST ACTUAL");
		table.setColumnHeader("tertundacounter", "TTD");
		table.setColumnHeader("terkirim", "KIRIM");
		table.setColumnHeader("tunaikredit", "T/K");
		table.setColumnHeader("lunas", "LNS");
		table.setColumnHeader("term", "TOP");
		table.setColumnHeader("tipejual", "TO/C/TF");
		
		
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
		getFieldSearchComboSalesman().setContainerDataSource(model.getBeanItemContainerSalesman());
		
		fieldSearchComboTertunda.addItem("TDK");
		fieldSearchComboTertunda.setItemCaption("TDK", "Tidak");
		fieldSearchComboTertunda.addItem("TTD");
		fieldSearchComboTertunda.setItemCaption("TTD", "TERTUNDA");
		fieldSearchComboTertunda.addItem("S");
		fieldSearchComboTertunda.setItemCaption("S", "Semua");
		fieldSearchComboTertunda.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboTertunda.setWidth("70px");
		//DEFAULT VIEW
		fieldSearchComboTertunda.select("TDK");
		
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
	
	private WhRecapSelectModel recapSelectModel; 
	private WhRecapSelectView recapSelectView;
	
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
		
		recapSelectModel = new WhRecapSelectModel();
		recapSelectView = new WhRecapSelectView(recapSelectModel);
		
		WhRecapSelectPresenter recapSelectPresenter = new WhRecapSelectPresenter(
				recapSelectModel, recapSelectView);		
		recapSelectView.setSizeFull();		
		
		windowRecapSearch.setContent(recapSelectView);
		
		getUI().addWindow(windowRecapSearch);
		
		
	}
	public void destroyWindowRecapSelect(){		
		windowRecapSearch.close();
	}
	
	

	public PenandaanTTDModel getModel() {
		return model;
	}

	public void setModel(PenandaanTTDModel model) {
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


	public ComboBox getFieldSearchComboSalesman() {
		return fieldSearchComboSalesman;
	}

	public void setFieldSearchComboSalesman(ComboBox fieldSearchComboSalesman) {
		this.fieldSearchComboSalesman = fieldSearchComboSalesman;
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


	public ComboBox getFieldSearchComboTertunda() {
		return fieldSearchComboTertunda;
	}

	public void setFieldSearchComboTertunda(ComboBox fieldSearchComboTertunda) {
		this.fieldSearchComboTertunda = fieldSearchComboTertunda;
	}

	public Button getBtnSetTertunda() {
		return btnSetTertunda;
	}

	public void setBtnSetTertunda(Button btnSetTertunda) {
		this.btnSetTertunda = btnSetTertunda;
	}


	public Button getBtnBatalTertunda() {
		return btnBatalTertunda;
	}

	public void setBtnBatalTertunda(Button btnBatalTertunda) {
		this.btnBatalTertunda = btnBatalTertunda;
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

	public WhRecapSelectModel getRecapSelectModel() {
		return recapSelectModel;
	}

	public void setRecapSelectModel(WhRecapSelectModel recapSelectModel) {
		this.recapSelectModel = recapSelectModel;
	}

	public WhRecapSelectView getRecapSelectView() {
		return recapSelectView;
	}

	public void setRecapSelectView(WhRecapSelectView recapSelectView) {
		this.recapSelectView = recapSelectView;
	}
	
	
	
}
