package org.dimas.finance.ar;

import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.Arpaymentdetail;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ArPaymentCustPembayaranView extends CustomComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArPaymentCustPembayaranModel model;
	
	//COMPONENT WINDOW
	private TextField fieldInvoice = new TextField("INVOICE:");
	private TextField fieldInvoiceAmount = new TextField("NILAI INVOICE:");
	private TextField fieldInvoiceAmountPaid = new TextField("INVOICE TERBAYAR:");
	private TextField fieldSubTotalAmountPaid = new TextField("SUB TOTAL:");
	
	private TextField fieldCashPay = new TextField("CASH:");
	private Button btnEqualCash = new Button("=");

	private TextField fieldReturPay = new TextField("RETUR:");
	private Button btnEqualRetur = new Button("=");
	private ComboBox comboRetur = new ComboBox("INV RETUR");
	private Button btnReturBrowse = new Button("...");
	
	private TextField fieldGiroPay = new TextField("GIRO:");
	private Button btnEqualGiro = new Button("=");
	private ComboBox comboGiro = new ComboBox("NO GIRO");
	private Button btnGiroBrowse = new Button("...");

	private TextField fieldTransferPay = new TextField("TRANSFER:");
	private Button btnEqualTransfer = new Button("=");
	private ComboBox comboTransfer = new ComboBox("NO TRANSFER");
	private Button btnTransferBrowse = new Button("...");
//	private TextField fieldTransfer = new TextField("NO TRANSFER/KET");
	
	
	private TextField fieldPotLainPay = new TextField("POT LAIN/DISC KHUSUS:");
	private Button btnEqualPotLain = new Button("=");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel & close");

	private FormLayout layoutRoot = new FormLayout();
	
	private BeanFieldGroup<Arinvoice> binderArinvoice = new BeanFieldGroup<Arinvoice>(Arinvoice.class);
	private BeanFieldGroup<Arpaymentdetail> binderArpaymentDetail = new BeanFieldGroup<Arpaymentdetail>(Arpaymentdetail.class);
	
	public ArPaymentCustPembayaranView(){
		initFieldFactory();		
		buildView();
		//USING FIELD GROUP
//		bindAndBuildFieldGroupComponent();
	} 
	public ArPaymentCustPembayaranView(ArPaymentCustPembayaranModel model){
		this.model = model;
		initFieldFactory();		
		buildView();
		//USING FIELD GROUP
		bindAndBuildFieldGroupComponent();
			
	}
	public void initFieldFactory(){
		
	}
	public void buildView(){
		comboRetur.setWidth("250px");
		comboTransfer.setWidth("250px");
		comboGiro.setWidth("250px");
			
		//Create content
		layoutRoot.setImmediate(false);
		
//		binderArinvoice.setBuffered(true);
		binderArpaymentDetail.setBuffered(true);
		
//		comboGiro.setImmediate(true);
//		comboTransfer.setImmediate(true);
//		comboRetur.setImmediate(true);
		
		VerticalLayout layoutGutter = new VerticalLayout();
		VerticalLayout layoutContent = new VerticalLayout();

		HorizontalLayout layoutTop = new HorizontalLayout();
		FormLayout layoutMidlle = new FormLayout();
		HorizontalLayout layoutButtom = new HorizontalLayout();
		
		//Layout Top Buat Judul
		Label labelJudul = new Label("::FORM PEMBAYARAN PER INVOICE::");
		layoutTop.addComponent(labelJudul);
		
		layoutContent.addComponent(layoutTop);
		layoutContent.addComponent(layoutMidlle);
		layoutContent.addComponent(layoutButtom);
		
		//Buat Isian Form
		HorizontalLayout layoutInvoice = new HorizontalLayout();		
		HorizontalLayout layoutCash = new HorizontalLayout();				
		HorizontalLayout layoutGiro = new HorizontalLayout();		
		HorizontalLayout layoutTransfer = new HorizontalLayout();		
		HorizontalLayout layoutRetur = new HorizontalLayout();
		HorizontalLayout layoutPotLain = new HorizontalLayout();
		
		layoutInvoice.addComponent(fieldInvoice);
		layoutInvoice.addComponent(fieldInvoiceAmount);
		layoutInvoice.addComponent(fieldInvoiceAmountPaid);
		layoutInvoice.addComponent(fieldSubTotalAmountPaid);
		layoutMidlle.addComponent(layoutInvoice);
		
		layoutCash.addComponent(fieldCashPay);
		layoutCash.addComponent(btnEqualCash);
		layoutCash.setComponentAlignment(btnEqualCash, Alignment.BOTTOM_CENTER);
		layoutMidlle.addComponent(layoutCash);

		layoutRetur.addComponent(fieldReturPay);
		layoutRetur.addComponent(btnEqualRetur);
		layoutRetur.addComponent(comboRetur);
		layoutRetur.addComponent(btnReturBrowse);
		layoutRetur.setComponentAlignment(btnReturBrowse, Alignment.BOTTOM_CENTER);
		layoutRetur.setComponentAlignment(btnEqualRetur, Alignment.BOTTOM_CENTER);
		layoutMidlle.addComponent(layoutRetur);
		
		layoutGiro.addComponent(fieldGiroPay);
		layoutGiro.addComponent(btnEqualGiro);
		layoutGiro.addComponent(comboGiro);
		layoutGiro.addComponent(btnGiroBrowse);
		layoutGiro.setComponentAlignment(btnGiroBrowse, Alignment.BOTTOM_CENTER);
		layoutGiro.setComponentAlignment(btnEqualGiro, Alignment.BOTTOM_CENTER);
		layoutMidlle.addComponent(layoutGiro);
		
		layoutTransfer.addComponent(fieldTransferPay);
		layoutTransfer.addComponent(btnEqualTransfer);
		layoutTransfer.addComponent(comboTransfer);
		layoutTransfer.addComponent(btnTransferBrowse);
		layoutTransfer.setComponentAlignment(btnEqualTransfer, Alignment.BOTTOM_CENTER);
		layoutTransfer.setComponentAlignment(btnTransferBrowse, Alignment.BOTTOM_CENTER);
		layoutMidlle.addComponent(layoutTransfer);
		
		layoutPotLain.addComponent(fieldPotLainPay);
		layoutPotLain.addComponent(btnEqualPotLain);
		layoutPotLain.setComponentAlignment(btnEqualPotLain, Alignment.BOTTOM_CENTER);
		layoutMidlle.addComponent(layoutPotLain);
		
		//Buat Isian Bawah		
		layoutButtom.addComponent(btnSaveForm);
		layoutButtom.addComponent(btnCancelForm);
		layoutButtom.setSpacing(true);
		
		layoutGutter.addComponent(new Label("***"));
		layoutRoot.addComponent(layoutGutter);
		layoutRoot.addComponent(layoutContent);		
		
		setCompositionRoot(layoutRoot);

		//Alignment and Size
		//Gak tahu gak mau ketengah
		layoutTop.setExpandRatio(labelJudul, 1);
		layoutContent.setComponentAlignment(layoutTop, Alignment.MIDDLE_CENTER);		
		layoutContent.setExpandRatio(layoutMidlle, 1);
		
	}
	
	public void bindAndBuildFieldGroupComponent(){
		
		//Init isian combobox
		comboGiro.setContainerDataSource(model.getBeanItemContainerBukuGiro());
		comboGiro.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);			
//		comboGiro.setNullSelectionAllowed(false);
	
		comboTransfer.setContainerDataSource(model.getBeanItemContainerBukuTransfer());
		comboTransfer.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);			
//		comboTransfer.setNullSelectionAllowed(false);
		
		comboRetur.setContainerDataSource(model.getBeanitemContainerReturBelumLunas());
		comboRetur.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);			
//		comboTransfer.setNullSelectionAllowed(false);
		

		
		binderArinvoice.bind(fieldInvoice, "id.invoiceno");
		binderArinvoice.bind(fieldInvoiceAmount, "amount");
		binderArinvoice.bind(fieldInvoiceAmountPaid, "amountpay");
		
		binderArpaymentDetail.bind(fieldSubTotalAmountPaid, "subtotalpay");
		
		binderArpaymentDetail.bind(fieldCashPay, "cashamountpay");
		
		
		binderArpaymentDetail.bind(fieldReturPay, "returamountpay");
		binderArpaymentDetail.bind(comboRetur, "returBean");
		
		binderArpaymentDetail.bind(fieldGiroPay, "giroamountpay");
		binderArpaymentDetail.bind(comboGiro, "bukugiroBean");
		
		binderArpaymentDetail.bind(fieldTransferPay, "transferamountpay");		
		binderArpaymentDetail.bind(comboTransfer, "bukutransferBean");
		
		binderArpaymentDetail.bind(fieldPotLainPay, "potonganamount");
		
	}
	public void refreshData(){
		binderArinvoice.setItemDataSource(model.getArInvoice());
		binderArpaymentDetail.setItemDataSource(model.getArPaymentDetail());

		
		//SEMUA FIELD SETELAH DIREFRESH DATA AKAN READONLY(FALSE) SEHINGGA
		//HARUS DIBALIKKAN READ ONLY LAGI SETELAH REFRESH DATA
		fieldInvoice.setReadOnly(true);		
		fieldInvoiceAmount.setReadOnly(true);		
		fieldInvoiceAmountPaid.setReadOnly(true);
		
		//JIKA RETUR MAKA TIDAK BISA CASH, GIRO, RETUR
		if (model.getArInvoice().getId().getTipefaktur().equals("R")){
			fieldGiroPay.setReadOnly(true);
			fieldTransferPay.setReadOnly(true);
			fieldReturPay.setReadOnly(true);
			comboGiro.setReadOnly(true);
			comboTransfer.setReadOnly(true);
			comboRetur.setReadOnly(true);
		}
		
	}
	
	public ArPaymentCustPembayaranModel getModel() {
		return model;
	}
	public void setModel(ArPaymentCustPembayaranModel model) {
		this.model = model;
	}
	public TextField getFieldInvoice() {
		return fieldInvoice;
	}
	public void setFieldInvoice(TextField fieldInvoice) {
		this.fieldInvoice = fieldInvoice;
	}
	public TextField getFieldInvoiceAmount() {
		return fieldInvoiceAmount;
	}
	public void setFieldInvoiceAmount(TextField fieldInvoiceAmount) {
		this.fieldInvoiceAmount = fieldInvoiceAmount;
	}
	public TextField getFieldInvoiceAmountPaid() {
		return fieldInvoiceAmountPaid;
	}
	public void setFieldInvoiceAmountPaid(TextField fieldInvoiceAmountPaid) {
		this.fieldInvoiceAmountPaid = fieldInvoiceAmountPaid;
	}
	public TextField getFieldCashPay() {
		return fieldCashPay;
	}
	public void setFieldCashPay(TextField fieldCashPay) {
		this.fieldCashPay = fieldCashPay;
	}
	public Button getBtnEqualCash() {
		return btnEqualCash;
	}
	public void setBtnEqualCash(Button btnEqualCash) {
		this.btnEqualCash = btnEqualCash;
	}
	public TextField getFieldGiroPay() {
		return fieldGiroPay;
	}
	public void setFieldGiroPay(TextField fieldGiroPay) {
		this.fieldGiroPay = fieldGiroPay;
	}
	public Button getBtnEqualGiro() {
		return btnEqualGiro;
	}
	public void setBtnEqualGiro(Button btnEqualGiro) {
		this.btnEqualGiro = btnEqualGiro;
	}
	public ComboBox getComboGiro() {
		return comboGiro;
	}
	public void setComboGiro(ComboBox comboGiro) {
		this.comboGiro = comboGiro;
	}
	public Button getBtnGiroBrowse() {
		return btnGiroBrowse;
	}
	public void setBtnGiroBrowse(Button btnGiroBrowse) {
		this.btnGiroBrowse = btnGiroBrowse;
	}
	public TextField getFieldTransferPay() {
		return fieldTransferPay;
	}
	public void setFieldTransferPay(TextField fieldTransferPay) {
		this.fieldTransferPay = fieldTransferPay;
	}
	public Button getBtnEqualTransfer() {
		return btnEqualTransfer;
	}
	public void setBtnEqualTransfer(Button btnEqualTransfer) {
		this.btnEqualTransfer = btnEqualTransfer;
	}
	public ComboBox getComboTransfer() {
		return comboTransfer;
	}
	public void setComboTransfer(ComboBox comboTransfer) {
		this.comboTransfer = comboTransfer;
	}
	public TextField getFieldReturPay() {
		return fieldReturPay;
	}
	public void setFieldReturPay(TextField fieldReturPay) {
		this.fieldReturPay = fieldReturPay;
	}
	public Button getBtnEqualRetur() {
		return btnEqualRetur;
	}
	public void setBtnEqualRetur(Button btnEqualRetur) {
		this.btnEqualRetur = btnEqualRetur;
	}
	public ComboBox getComboRetur() {
		return comboRetur;
	}
	public void setComboRetur(ComboBox comboRetur) {
		this.comboRetur = comboRetur;
	}
	public Button getBtnReturBrowse() {
		return btnReturBrowse;
	}
	public void setBtnReturBrowse(Button btnReturBrowse) {
		this.btnReturBrowse = btnReturBrowse;
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
	public TextField getFieldSubTotalAmountPaid() {
		return fieldSubTotalAmountPaid;
	}
	public void setFieldSubTotalAmountPaid(TextField fieldSubTotalAmountPaid) {
		this.fieldSubTotalAmountPaid = fieldSubTotalAmountPaid;
	}
	public Button getBtnTransferBrowse() {
		return btnTransferBrowse;
	}
	public void setBtnTransferBrowse(Button btnTransferBrowse) {
		this.btnTransferBrowse = btnTransferBrowse;
	}
	public TextField getFieldPotLainPay() {
		return fieldPotLainPay;
	}
	public void setFieldPotLainPay(TextField fieldPotLainPay) {
		this.fieldPotLainPay = fieldPotLainPay;
	}
	public Button getBtnEqualPotLain() {
		return btnEqualPotLain;
	}
	public void setBtnEqualPotLain(Button btnEqualPotLain) {
		this.btnEqualPotLain = btnEqualPotLain;
	}
	public FormLayout getLayoutRoot() {
		return layoutRoot;
	}
	public void setLayoutRoot(FormLayout layoutRoot) {
		this.layoutRoot = layoutRoot;
	}
	public BeanFieldGroup<Arinvoice> getBinderArinvoice() {
		return binderArinvoice;
	}
	public void setBinderArinvoice(BeanFieldGroup<Arinvoice> binderArinvoice) {
		this.binderArinvoice = binderArinvoice;
	}
	public BeanFieldGroup<Arpaymentdetail> getBinderArpaymentDetail() {
		return binderArpaymentDetail;
	}
	public void setBinderArpaymentDetail(
			BeanFieldGroup<Arpaymentdetail> binderArpaymentDetail) {
		this.binderArpaymentDetail = binderArpaymentDetail;
	}
	
	
	
}
