package org.dimas.finance.cashandbank.penerimaankas;

import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.util.HelpManager;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;

public class PenerimaanKBItemAddView extends CustomComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PenerimaanKBItemAddModel model;
	
	private VerticalLayout content = new VerticalLayout();
	
//	private TextField fieldSearchById = new TextField("MODUL/MENU");
//	private TextField fieldSearchByGroup = new TextField("GROUP");
//	private TextField fieldSearchByTitle = new TextField("TITLE");

	private ComboBox comboDivision= new ComboBox("DIVISI");
	private TextField fieldDescription = new TextField("DESCRIPTION (TERIMA DARI)");
	private ComboBox comboTranstype = new ComboBox("TIPE TRANSAKSI");
	private ComboBox comboAccount = new ComboBox("ACCOUNT");
	private ComboBox comboCustomer = new ComboBox("CUSTOMER");
	private ComboBox comboSalesman = new ComboBox("SALESMAN");
	
	private ComboBox comboDebetkredit = new ComboBox("D/K");
	private TextField fieldAmount = new TextField("AMOUNT");
	private CheckBox checkGl = new CheckBox("G/L");
	
	private Button btnNumber = new Button("0");
	private Button btnFindTranstype = new Button("F");
	private Button btnAdd = new Button("Add or Update");
	private Button btnReset = new Button("Reset");
	private Button btnClose = new Button("Close");
	
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
	
	public PenerimaanKBItemAddView(PenerimaanKBItemAddModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();	
		
	}
	public void initComponent(){
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
		
		//INIT COMPONENT BAWAH	
		
		//DEKLARASI LAYOUT
		//KOMPONEN ATAS
		//PERMAK KOMPONEN FIELD
		fieldDescription.setNullRepresentation("");
		fieldAmount.setNullRepresentation("0");
		
		HorizontalLayout layoutTop = new HorizontalLayout();		
		layoutTop.addComponent(btnNumber);
		layoutTop.addComponent(comboDivision);		
		layoutTop.addComponent(fieldDescription);
		layoutTop.addComponent(comboSalesman);
		layoutTop.addComponent(comboCustomer);		
		layoutTop.addComponent(comboTranstype);
		layoutTop.addComponent(comboAccount);
//		layoutTop.addComponent(btnFindTranstype);		
		layoutTop.addComponent(comboDebetkredit);
		layoutTop.addComponent(fieldAmount);
		layoutTop.addComponent(checkGl);

		fieldDescription.setWidth("280px");
		comboAccount.setWidth("180px");
		comboDebetkredit.setWidth("100px");
		fieldAmount.setWidth("150px");
		
		layoutTop.addComponent(btnSeparator2);
		layoutTop.addComponent(btnAdd);
		layoutTop.addComponent(btnReset);
		layoutTop.addComponent(btnClose);
		
		layoutTop.setComponentAlignment(btnNumber, Alignment.BOTTOM_CENTER);
//		layoutTop.setComponentAlignment(btnFindTranstype, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnAdd, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnReset, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnClose, Alignment.BOTTOM_CENTER);
		
		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		middleLayout.setSizeFull();

		HorizontalLayout formLayoutHorizontal = new HorizontalLayout();
//		formLayoutHorizontal.setSpacing(true);
//		formLayoutHorizontal.addComponent(btnClose);
		formLayout.addComponent(formLayoutHorizontal);
		
		//MASUKKAN KE PANEL
		panelTop.setContent(layoutTop);		
		panelTabel.setContent(middleLayout);
		panelForm.setContent(formLayout);

		//VERTICAL SPLIT PANE
		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
		verticalSplitPanel.setSizeFull();		
		verticalSplitPanel.setSplitPosition(10);
		
		verticalSplitPanel.setFirstComponent(panelTabel);		
		verticalSplitPanel.setSecondComponent(panelForm);
		
		//MASUKKAN KE ROOT
		content.addComponent(panelTop);
		content.addComponent(verticalSplitPanel);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		content.setExpandRatio(verticalSplitPanel, 1);
		
		//INIT
		formLayout.setVisible(true);
		
	}
	
	public void setDisplay(){
		
		bindAndBuildFieldGroupComponent();
		setFormButtonAndTextState();
		
	}
	public void setDisplayTableFooter(){
		
	}

	public void bindAndBuildFieldGroupComponent(){
		
		//COMBO ACCCONT
		comboDivision.setContainerDataSource(model.getBeanItemContainerDivision());
		comboDivision.setNewItemsAllowed(false);
		comboTranstype.setContainerDataSource(model.getBeanItemContainerTranstype());
		comboTranstype.setNullSelectionAllowed(false);
		comboAccount.setContainerDataSource(model.getBeanItemContainerAccount());
		comboCustomer.setContainerDataSource(model.getBeanItemContainerCustomer());
		comboSalesman.setContainerDataSource(model.getBeanItemContainerSalesman());
		
		//COMBO DEBET/KREDIT
		comboDebetkredit.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboDebetkredit.setNullSelectionAllowed(false);
		comboDebetkredit.addItem("D");
		comboDebetkredit.setItemCaption("D", "Debet");
		comboDebetkredit.addItem("K");
		comboDebetkredit.setItemCaption("K", "Kredit");		
		
		model.getBinderBkbdetail().bind(comboDivision, "divisionBean");
		model.getBinderBkbdetail().bind(fieldDescription, "description");
		model.getBinderBkbdetail().bind(comboSalesman, "salesmanBean");
		model.getBinderBkbdetail().bind(comboCustomer, "customerBean");
		model.getBinderBkbdetail().bind(comboTranstype, "bkbtranstypeBean");
		model.getBinderBkbdetail().bind(comboAccount, "accountBean");
		model.getBinderBkbdetail().bind(comboDebetkredit, "debetkredit");
		model.getBinderBkbdetail().bind(fieldAmount, "amount");
		model.getBinderBkbdetail().bind(checkGl, "gl");
		
		//NON VISIBLE ITEM :: KARENA TIDAK DIPAKAI
		comboCustomer.setVisible(false);
		comboSalesman.setVisible(false);
		comboDivision.setVisible(false);
		comboAccount.setVisible(false);
		comboDebetkredit.setVisible(false);
		
		//KEBIJAKAN DIMAS DIMATIKAN 
		checkGl.setReadOnly(true);
		
	}
	public void setFormButtonAndTextState(){
		
		if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
			btnAdd.setCaption("Add");
		} else if(model.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
			btnAdd.setCaption("Update & Close");
		}
	}
	public void focustIdOrDesc(){	
		fieldDescription.focus();
	}
	
	public PenerimaanKBItemAddModel getModel() {
		return model;
	}
	public void setModel(PenerimaanKBItemAddModel model) {
		this.model = model;
	}
	public VerticalLayout getContent() {
		return content;
	}
	public void setContent(VerticalLayout content) {
		this.content = content;
	}
	public TextField getFieldDescription() {
		return fieldDescription;
	}
	public void setFieldDescription(TextField fieldDescription) {
		this.fieldDescription = fieldDescription;
	}
	public ComboBox getComboAccount() {
		return comboAccount;
	}
	public void setComboAccount(ComboBox comboAccount) {
		this.comboAccount = comboAccount;
	}
	public ComboBox getComboDebetkredit() {
		return comboDebetkredit;
	}
	public void setComboDebetkredit(ComboBox comboDebetkredit) {
		this.comboDebetkredit = comboDebetkredit;
	}
	public TextField getFieldAmount() {
		return fieldAmount;
	}
	public void setFieldAmount(TextField fieldAmount) {
		this.fieldAmount = fieldAmount;
	}
	public CheckBox getCheckGl() {
		return checkGl;
	}
	public void setCheckGl(CheckBox checkGl) {
		this.checkGl = checkGl;
	}
	public Button getBtnAdd() {
		return btnAdd;
	}
	public void setBtnAdd(Button btnAdd) {
		this.btnAdd = btnAdd;
	}
	public Button getBtnReset() {
		return btnReset;
	}
	public void setBtnReset(Button btnReset) {
		this.btnReset = btnReset;
	}
	public Button getBtnClose() {
		return btnClose;
	}
	public void setBtnClose(Button btnClose) {
		this.btnClose = btnClose;
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
	
	public Button getBtnFindTranstype() {
		return btnFindTranstype;
	}
	public void setBtnFindTranstype(Button btnFindTranstype) {
		this.btnFindTranstype = btnFindTranstype;
	}
	public Button getBtnNumber() {
		return btnNumber;
	}
	public void setBtnNumber(Button btnNumber) {
		this.btnNumber = btnNumber;
	}
	public ComboBox getComboDivision() {
		return comboDivision;
	}
	public void setComboDivision(ComboBox comboDivision) {
		this.comboDivision = comboDivision;
	}
	public ComboBox getComboTranstype() {
		return comboTranstype;
	}
	public void setComboTranstype(ComboBox comboTranstype) {
		this.comboTranstype = comboTranstype;
	}
	public ComboBox getComboCustomer() {
		return comboCustomer;
	}
	public void setComboCustomer(ComboBox comboCustomer) {
		this.comboCustomer = comboCustomer;
	}
	public ComboBox getComboSalesman() {
		return comboSalesman;
	}
	public void setComboSalesman(ComboBox comboSalesman) {
		this.comboSalesman = comboSalesman;
	}
	

	
}
