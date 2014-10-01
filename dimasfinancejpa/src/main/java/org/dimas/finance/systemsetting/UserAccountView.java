package org.dimas.finance.systemsetting;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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

public class UserAccountView extends CustomComponent {
	private UserAccountModel model;
	
	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldUserId = new TextField("USER ID");
	private CheckBox checkUserActive = new CheckBox("Active");
	private ComboBox comboModulTemplate = new ComboBox("MODUL TEMPLATE");
	private PasswordField fieldUserPassword = new PasswordField("PASSWORD");
	private TextField fieldUserPasswordShow = new TextField("PASSWORD");
	private CheckBox  checkShowPassword = new CheckBox("Show Password", false);
	
	private TextField fieldFullName = new TextField("NAMA LENGKAP");
	private ComboBox comboGender = new ComboBox("GENDER");
	
	private TextField fieldEmail = new TextField("EMAIL");
	private TextField fieldAddress = new TextField("ADDRESS");
	private TextField fieldCity = new TextField("CITY");
	private TextField fieldState = new TextField("STATE");
	
	private DateField dateFieldBirthDate = new DateField("BIRTH DATE");
	private DateField dateFieldJoinDate = new DateField("JOIN DATE");
	private DateField dateFieldLastLogin = new DateField("LAST LOGIN");
	
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	private Button btnAddForm= new Button("Add New");
	private Button btnDeleteForm= new Button("Delete");

	//Additional Component
	private TextField fieldSearchByUserId = new TextField("USER ID");
	private TextField fieldSearchByUserName = new TextField("NAME");
//	private TextField fieldSearchByNasabah = new TextField("NASABAH");
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
	
	public UserAccountView(UserAccountModel model){
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
		layoutTop.addComponent(fieldSearchByUserId);
		layoutTop.addComponent(fieldSearchByUserName);
//		layoutTop.addComponent(fieldSearchByNasabah);
		
		layoutTop.addComponent(btnSearch);
		layoutTop.addComponent(btnSeparator1);
		layoutTop.addComponent(btnAddForm);
		layoutTop.addComponent(btnDeleteForm);
		layoutTop.addComponent(btnSeparator2);
		layoutTop.addComponent(btnPrint);
		layoutTop.addComponent(btnHelp);		
		
		layoutTop.setComponentAlignment(fieldSearchByUserId, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(fieldSearchByUserName, Alignment.BOTTOM_CENTER);
		
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
		HorizontalLayout buttomLayout1 = new HorizontalLayout();		
		buttomLayout1.addComponent(fieldUserId);
		buttomLayout1.addComponent(checkUserActive);	
		buttomLayout1.addComponent(comboModulTemplate);
		formLayout.addComponent(buttomLayout1);		
		buttomLayout1.setSpacing(true);

		buttomLayout1.setComponentAlignment(checkUserActive, Alignment.MIDDLE_CENTER);
		
		HorizontalLayout buttomLayout2 = new HorizontalLayout();				
		buttomLayout2.addComponent(fieldUserPassword);
		buttomLayout2.addComponent(fieldUserPasswordShow);
		buttomLayout2.addComponent(checkShowPassword);
		formLayout.addComponent(buttomLayout2);
		
		buttomLayout2.setComponentAlignment(checkShowPassword, Alignment.MIDDLE_CENTER);
		
		HorizontalLayout buttomLayout3 = new HorizontalLayout();
		buttomLayout3.addComponent(fieldFullName);
		buttomLayout3.addComponent(comboGender);
		buttomLayout3.addComponent(fieldEmail);
		formLayout.addComponent(buttomLayout3);
		
		HorizontalLayout buttomLayout4 = new HorizontalLayout();
		buttomLayout4.addComponent(fieldAddress);
		buttomLayout4.addComponent(fieldCity);
		buttomLayout4.addComponent(fieldState);
		formLayout.addComponent(buttomLayout4);
		
		
		HorizontalLayout buttomLayout5 = new HorizontalLayout();
		buttomLayout5.addComponent(dateFieldBirthDate);
		buttomLayout5.addComponent(dateFieldJoinDate);
		buttomLayout5.addComponent(dateFieldLastLogin);
		formLayout.addComponent(buttomLayout5);
		
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
		table.setContainerDataSource(model.getBeanItemContainerUser());
		
		setTableProperties();
		
		setDisplayTableFooter();
		
		bindAndBuildFieldGroupComponent();
		
	}
	public void setDisplayTableFooter(){
		Collection items =  model.getBeanItemContainerUser().getItemIds();
		table.setColumnFooter("refno", "*Jumlah Record: " + items.size());
		
	}

	public void bindAndBuildFieldGroupComponent(){

		comboModulTemplate.setContainerDataSource(model.getBeanItemContainerModulTemplate());
		comboModulTemplate.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboModulTemplate.setNullSelectionAllowed(true);
		
		//Init isian combobox
//		comboGender.setContainerDataSource();
		comboGender.addItem(true);
		comboGender.setItemCaption(true, "LAKI-LAKI");
		comboGender.addItem(false);
		comboGender.setItemCaption(false, "PEREMPUAN");
		
		comboGender.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);			
		comboGender.setNullSelectionAllowed(false);
		
		model.getBinderUser().bind(fieldUserId, "userId");
		model.getBinderUser().bind(checkUserActive, "active");
		model.getBinderUser().bind(comboModulTemplate, "modulTemplate");
		//DEFAULT PASSWORD NOT SHOW
		model.getBinderUser().bind(fieldUserPassword, "userPassword");
		fieldUserPasswordShow.setVisible(false);
		checkShowPassword.setValue(false);
		
		model.getBinderUser().bind(comboGender, "gender");
		model.getBinderUser().bind(fieldFullName, "fullName");
		model.getBinderUser().bind(fieldEmail, "email");
		model.getBinderUser().bind(fieldAddress, "address");
		model.getBinderUser().bind(fieldCity, "city");
		model.getBinderUser().bind(fieldState, "state");
		model.getBinderUser().bind(dateFieldBirthDate, "birthDate");
		model.getBinderUser().bind(dateFieldJoinDate, "joinDate");
		model.getBinderUser().bind(dateFieldLastLogin, "lastLogin");
		
		
	}
	
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

		setVisibleTableProperties("userId", "fullName", 
				"gender", "address", "city", "state", "birthDate",
				"joinDate", "lastLogin");
		
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("state", true);
			table.setColumnCollapsed("birthDate", true);
			table.setColumnCollapsed("joinDate", true);
			
		} catch(Exception ex){}
		
		//ALIGNMENT
		table.setColumnAlignment("gender", Align.CENTER);
		table.setColumnAlignment("birthDate", Align.CENTER);
		table.setColumnAlignment("joinDate", Align.CENTER);
		table.setColumnAlignment("lastLogin", Align.CENTER);
		
		//set header
		table.setColumnHeader("userId", "USER ID");
		table.setColumnHeader("userPassword", "PASSWORD");
		table.setColumnHeader("fullName", "NAMA LENGKAP");
		table.setColumnHeader("gender", "GENDER");
		table.setColumnHeader("address", "ALAMAT");
		table.setColumnHeader("city", "KOTA");
		table.setColumnHeader("state", "PROPINSI");
		table.setColumnHeader("birthDate", "TGL. LHR");
		table.setColumnHeader("joinDate", "TGL. DAFTAR");
		table.setColumnHeader("lastLogin", "TERAKHIR LOGIN");
		
		
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
			fieldUserId.setReadOnly(true);
		} else if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
			formLayout.setVisible(true);
			table.setSelectable(false);
			btnAddForm.setEnabled(false);
			btnDeleteForm.setEnabled(false);
			btnSearch.setEnabled(false);
			fieldUserId.setReadOnly(false);
		}else if (model.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
			formLayout.setVisible(true);
			table.setSelectable(true);
			btnAddForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);			
			btnSearch.setEnabled(true);
			fieldUserId.setReadOnly(false);
		}		
		
	}
	
	public void focustIdOrDesc(){
		if (fieldUserId.isEnabled()){
			fieldUserId.focus();
		} else {
			fieldFullName.focus();		                    						
		}
	}
	
	public void showPassword(boolean bol){
		if (bol==true){
			model.getBinderUser().unbind(fieldUserPassword);				
			fieldUserPasswordShow.setVisible(true);
			model.getBinderUser().bind(fieldUserPasswordShow, "userPassword");		
			fieldUserPassword.setVisible(false);
		} else {
			model.getBinderUser().unbind(fieldUserPasswordShow);				
			fieldUserPassword.setVisible(true);
			model.getBinderUser().bind(fieldUserPassword, "userPassword");		
			fieldUserPasswordShow.setVisible(false);
		}
	
	}
	
	public UserAccountModel getModel() {
		return model;
	}
	public void setModel(UserAccountModel model) {
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
	public TextField getFieldUserId() {
		return fieldUserId;
	}
	public void setFieldUserId(TextField fieldUserId) {
		this.fieldUserId = fieldUserId;
	}
	
	
	
	public PasswordField getFieldUserPassword() {
		return fieldUserPassword;
	}
	public void setFieldUserPassword(PasswordField fieldUserPassword) {
		this.fieldUserPassword = fieldUserPassword;
	}
	public TextField getFieldUserPasswordShow() {
		return fieldUserPasswordShow;
	}
	public void setFieldUserPasswordShow(TextField fieldUserPasswordShow) {
		this.fieldUserPasswordShow = fieldUserPasswordShow;
	}
	public TextField getFieldFullName() {
		return fieldFullName;
	}
	public void setFieldFullName(TextField fieldFullName) {
		this.fieldFullName = fieldFullName;
	}
	public ComboBox getComboGender() {
		return comboGender;
	}
	public void setComboGender(ComboBox comboGender) {
		this.comboGender = comboGender;
	}
	public TextField getFieldEmail() {
		return fieldEmail;
	}
	public void setFieldEmail(TextField fieldEmail) {
		this.fieldEmail = fieldEmail;
	}
	public TextField getFieldAddress() {
		return fieldAddress;
	}
	public void setFieldAddress(TextField fieldAddress) {
		this.fieldAddress = fieldAddress;
	}
	public TextField getFieldCity() {
		return fieldCity;
	}
	public void setFieldCity(TextField fieldCity) {
		this.fieldCity = fieldCity;
	}
	public TextField getFieldState() {
		return fieldState;
	}
	public void setFieldState(TextField fieldState) {
		this.fieldState = fieldState;
	}
	public DateField getDateFieldBirthDate() {
		return dateFieldBirthDate;
	}
	public void setDateFieldBirthDate(DateField dateFieldBirthDate) {
		this.dateFieldBirthDate = dateFieldBirthDate;
	}
	public DateField getDateFieldJoinDate() {
		return dateFieldJoinDate;
	}
	public void setDateFieldJoinDate(DateField dateFieldJoinDate) {
		this.dateFieldJoinDate = dateFieldJoinDate;
	}
	public DateField getDateFieldLastLogin() {
		return dateFieldLastLogin;
	}
	public void setDateFieldLastLogin(DateField dateFieldLastLogin) {
		this.dateFieldLastLogin = dateFieldLastLogin;
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
	public TextField getFieldSearchByUserId() {
		return fieldSearchByUserId;
	}
	public void setFieldSearchByUserId(TextField fieldSearchByUserId) {
		this.fieldSearchByUserId = fieldSearchByUserId;
	}
	public TextField getFieldSearchByUserName() {
		return fieldSearchByUserName;
	}
	public void setFieldSearchByUserName(TextField fieldSearchByUserName) {
		this.fieldSearchByUserName = fieldSearchByUserName;
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
	public CheckBox getCheckUserActive() {
		return checkUserActive;
	}
	public void setCheckUserActive(CheckBox checkUserActive) {
		this.checkUserActive = checkUserActive;
	}
	public CheckBox getCheckShowPassword() {
		return checkShowPassword;
	}
	public void setCheckShowPassword(CheckBox checkShowPassword) {
		this.checkShowPassword = checkShowPassword;
	}
	public ComboBox getComboModulTemplate() {
		return comboModulTemplate;
	}
	public void setComboModulTemplate(ComboBox comboModulTemplate) {
		this.comboModulTemplate = comboModulTemplate;
	}
	
	
	
}
