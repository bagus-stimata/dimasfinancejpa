package org.dimas.finance.master;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dimas.finance.model.Customer;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Region;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.util.HelpManager;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Item;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
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

public class CustomerView extends CustomComponent {
	private CustomerModel model;
	private VerticalLayout content = new VerticalLayout();

//	private JPAContainer<Customer> container;
	
	private Table table;
	private CustomFieldFactory customFieldFactory;
	private FieldFactory fieldFactory;

	private Class<Customer> entityClass;
	
	private Button commit;
	private Button discard;
	private Object[] formPropertyIds;
	private Button addButton;
	private Button deleteButton;
	
	
	private TextField fieldCustno = new TextField("KODE CUST");
	private ComboBox comboDivision = new ComboBox("DIVISI");
	private TextField fieldCustname = new TextField("NAMA");
	private TextField fieldAddress1 = new TextField("ALAMAT");
	private TextField fieldCity1 = new TextField("KOTA");
	private TextField fieldState = new TextField("PROPINSI");
	private TextField fieldPostcode = new TextField("POSTCODE");
	private TextField fieldContactperson = new TextField("CONTACT");
	private TextField fieldHp = new TextField("HP");
	private TextField fieldPhone = new TextField("PHONE");
	private TextField fieldCreditlimit = new TextField("CREDIT LIMIT");
	private TextField fieldCrterm = new TextField("TOP");
	private ComboBox comboTunaikredit = new ComboBox("TUNAI/KREDIT");
	private TextField fieldTypecustomer = new TextField("TOP");
//	private ComboBox comboTypecustomer = new ComboBox("TIPE CUSTOMER");
	private DateField dateFieldJoindate = new DateField("TANGGAL MASUK");
	private DateField dateFieldLasttrans = new DateField("TRANSAKSI TERAKHIR");
	private DateField dateFieldLastupdate = new DateField("UPDATE TERAHIR");
	
	
//	private final String persistenceUnit;
	
//	private AccountGrup accountGrup;
//	private BeanItem<AccountGrup> beanItemAccountGrup;
	
	private String operationStatus;

	//Additional Component
	private TextField fieldSearchById;
	private TextField fieldSearchByDesc;
	private Button btnSearch;
	private Button btnReload;
	private Button btnPrint;
	private Button btnHelp;	
	private Button btnSeparator1;
	private Button btnSeparator2;

	//LAYOUT
	private FormLayout formLayout = new FormLayout();
	
	//Panel
	private Panel panelUtama;
	private Panel panelTop;
	private Panel panelTabel;
	private Panel panelForm;

	//Help Manager	
    private HelpManager helpManager;
	
	public CustomerView(CustomerModel model){
		this.model = model;		
		initContainer();
		initFieldFactory();
		buildView();
		
		//Set Awal Status form
		operationStatus = EnumFormOperationStatus.OPEN.getStrCode();
		
	}
	public void initContainer(){
		table = new Table("Table:", model.getTableJpaContainer());		
		
	}
	public void initFieldFactory(){
		fieldFactory = new FieldFactory();
		customFieldFactory = new CustomFieldFactory();		
	}
	public void buildView(){
        addStyleName("schedule");
		
		content.setSizeFull();
		
		//Inisialisasi Panel 
		setSizeFull();
		panelUtama = new Panel(getCaption());
		panelUtama.setSizeFull();

		panelTop = new Panel();
		panelTabel = new Panel();
		panelTabel.setSizeFull();
		panelForm = new Panel();
		panelForm.setHeight("300px");
		
		//Init Komponen atas
		fieldSearchById = new TextField();
		fieldSearchByDesc = new TextField();
		
		//Init komponen tengah
		table.setSizeFull();
		table.setSelectable(true);
//		table.addValueChangeListener(this);
		table.setImmediate(true);
		table.setBuffered(false);
//		table.addActionHandler(this);		
		table.setFooterVisible(true);
		
		//Deklarasi Button dan Listener	
		addButton = new Button("Add New");		
		deleteButton = new Button("Delete");
		commit = new Button("Save");		
		discard = new Button("Cancel");
		btnSearch = new Button("Search");
		btnReload = new Button("Reload");
		btnPrint = new Button("Print");
		btnHelp = new Button("Help");
		btnSeparator1 = new Button(":::::::");
		btnSeparator1.setEnabled(false);
		btnSeparator2 = new Button(":::::::");
		btnSeparator2.setEnabled(false);
		
		//Init komponen bawah
//		form = new Form();
//		form.setVisible(false);
//		form.setBuffered(true);
//		form.setImmediate(false);		
//		form.setFormFieldFactory(customFieldFactory);
		//KOMPONEN BAWAH
		formLayout.addComponent(fieldCustno);
		formLayout.addComponent(comboDivision);
		formLayout.addComponent(fieldCustname);
		formLayout.addComponent(fieldAddress1);
		formLayout.addComponent(fieldCity1);
		formLayout.addComponent(fieldState);
		formLayout.addComponent(fieldPostcode);
		formLayout.addComponent(fieldContactperson);
		formLayout.addComponent(fieldHp);
		formLayout.addComponent(fieldPhone);
		formLayout.addComponent(fieldCreditlimit);
		formLayout.addComponent(fieldCrterm);
		formLayout.addComponent(dateFieldJoindate);
		formLayout.addComponent(dateFieldLasttrans);
		formLayout.addComponent(dateFieldLastupdate);
		formLayout.addComponent(comboTunaikredit);
		formLayout.addComponent(fieldTypecustomer);

		//DEKLARASI LAYOUT
		//KOMPONEN ATAS
		HorizontalLayout layoutTop = new HorizontalLayout();		
	
		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
		verticalSplitPanel.setSizeFull();		
		verticalSplitPanel.setSplitPosition(40);
		
		layoutTop.addComponent(fieldSearchById);
		layoutTop.addComponent(fieldSearchByDesc);
		layoutTop.addComponent(btnSearch);
		layoutTop.addComponent(btnReload);
		layoutTop.addComponent(btnSeparator1);
		layoutTop.addComponent(addButton);
		layoutTop.addComponent(deleteButton);
		layoutTop.addComponent(btnSeparator2);
		layoutTop.addComponent(btnPrint);
		layoutTop.addComponent(btnHelp);		
		panelTop.setContent(layoutTop);
		
		panelTabel.setContent(table);
		
//		form.getFooter().addComponent(commit);
//		form.getFooter().addComponent(discard);
//		((HorizontalLayout) form.getFooter()).setSpacing(true);		
//		panelForm.setContent(form);

		HorizontalLayout formLayoutHorizontal = new HorizontalLayout();
		formLayoutHorizontal.setSpacing(true);
		formLayoutHorizontal.addComponent(commit);
		formLayoutHorizontal.addComponent(discard);
		formLayout.addComponent(formLayoutHorizontal);
		
		panelForm.setContent(formLayout);
		
		verticalSplitPanel.setFirstComponent(panelTabel);		
		verticalSplitPanel.setSecondComponent(panelForm);
		

		content.addComponent(panelTop);
		content.addComponent(verticalSplitPanel);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		content.setExpandRatio(verticalSplitPanel, 1);
		
		//init
		formLayout.setVisible(false);		
		
	}
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setVisibleFormProperties(Object... formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
//		form.setVisibleItemProperties(formPropertyIds);
	}
	public void setTableProperties(){
		setVisibleTableProperties("id","custname", "divisionBean", "address1", "city1", "state", "postcode", 
				"contactperson", "hp", "phone", "creditlimit", "crterm", "joindate", "lasttrans", 
				"lastupdate", "tunaikredit", "typecustomer");
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("joindate", true);
			table.setColumnCollapsed("lasttrans", true);
			table.setColumnCollapsed("lastupdate", true);
			table.setColumnCollapsed("contactperson", true);
			table.setColumnCollapsed("hp", true);
			table.setColumnCollapsed("phone", true);
			table.setColumnCollapsed("postcode", true);
			
		} catch(Exception ex){}
		
		
		//set header
		table.setColumnHeader("id", "DIV-CUSTNO");
//		table.setColumnHeader("custname", "NAMA");
		
//		table.setColumnExpandRatio("id", 1);
//		table.setColumnExpandRatio("custname", 2);		
	}
	public void setFormProperties(){
		setVisibleFormProperties("divisionBean", "custname", "address1", "city1", "state", "postcode", 
				"contactperson", "hp", "phone", "creditlimit", "crterm", "joindate", "lasttrans", 
				"lastupdate", "tunaikredit", "typecustomer");
	}
	public void setDisplay(){
		//1. Refresh Table displa
		table.setContainerDataSource(model.getTableJpaContainer());
		
		//2. Jika table masih dalam kondisi di seleksi maka form masih diisi dengan hasil seleksi
		
		
		setTableProperties();
//		setFormProperties();
		
		//3. Hitung Total & Jumlah Record dll
		Collection items =  model.getTableJpaContainer().getItemIds();
		table.setColumnFooter("id", "*Jumlah Record: " + items.size());
		
	}
	
	public void bindAndBuildFieldGroupComponent(){
		//Init isian combobox
		comboDivision.setContainerDataSource(model.getBeanItemContainerDivision());
		comboDivision.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboDivision.setNullSelectionAllowed(false);
		
		comboTunaikredit.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);				
		comboTunaikredit.addItem("T");
		comboTunaikredit.setItemCaption("T", "Tunai");
		comboTunaikredit.addItem("K");
		comboTunaikredit.setItemCaption("K", "Kredit");
		
		comboTunaikredit.setNullSelectionAllowed(false);
		comboTunaikredit.setNewItemsAllowed(false);
		comboTunaikredit.setInputPrompt("Select One");
		
		model.getBinderCustomer().bind(fieldCustno, "id.custno");
		model.getBinderCustomer().bind(comboDivision, "divisionBean");
		model.getBinderCustomer().bind(fieldCustname, "custname");
		model.getBinderCustomer().bind(fieldAddress1, "address1");
		model.getBinderCustomer().bind(fieldCity1, "city1");
		model.getBinderCustomer().bind(fieldState, "state");
		model.getBinderCustomer().bind(fieldPostcode, "postcode");
		model.getBinderCustomer().bind(fieldContactperson, "contactperson");
		model.getBinderCustomer().bind(fieldHp, "hp");
		model.getBinderCustomer().bind(fieldPhone, "phone");
		model.getBinderCustomer().bind(fieldCreditlimit, "creditlimit");
		model.getBinderCustomer().bind(fieldCrterm, "crterm");
		model.getBinderCustomer().bind(dateFieldJoindate, "joindate");
		model.getBinderCustomer().bind(dateFieldLasttrans, "lasttrans");
		model.getBinderCustomer().bind(dateFieldLastupdate, "lastupdate");
		model.getBinderCustomer().bind(comboTunaikredit, "tunaikredit");
		model.getBinderCustomer().bind(fieldTypecustomer, "typecustomer");
		
	}
	
	
	public void setFormButtonAndText(){
		if (operationStatus.equals(EnumFormOperationStatus.OPEN.getStrCode())){
			formLayout.setVisible(false);
			table.setSelectable(true);
			addButton.setEnabled(true);
			deleteButton.setEnabled(true);						
		} else if (operationStatus.equals(EnumFormOperationStatus.ADDING.getStrCode())){
			formLayout.setVisible(true);
			table.setSelectable(false);
			addButton.setEnabled(false);
			deleteButton.setEnabled(false);			
		}else if (operationStatus.equals(EnumFormOperationStatus.EDITING.getStrCode())){
			formLayout.setVisible(true);
			table.setSelectable(true);
			addButton.setEnabled(true);
			deleteButton.setEnabled(true);			
		}		
		//add button selalu tidak bisa
		addButton.setEnabled(false);
	}
	
	public void focustIdOrDesc(){
		if (fieldCustno.isEnabled()){
			fieldCustno.focus();
		} else {
			fieldCustname.focus();		                    						
		}		
	}
	
	
	public VerticalLayout getContent() {
		return content;
	}
	public void setContent(VerticalLayout content) {
		this.content = content;
	}
	
    protected static class CustomFieldFactory extends DefaultFieldFactory {
		public Field createField(Item item, Object propertyId, Component uiContext){
			final JPAContainer<Region> regionJPAContainer = JPAContainerFactory.make(Region.class, "financePU");
			final JPAContainer<Division> divisionJPAContainer = JPAContainerFactory.make(Division.class, "financePU");
			
			Field field = null;
			if ("idxxx".equals(propertyId)) {
				field = new TextField("ID CUSTOMER: ");
				field.setRequired(true);
				field.setRequiredError("ID tidak boleh Kosong");
                field.addValidator(new StringLengthValidator("Nama antar 1 s/d 6", 1, 6, false));
				return field;
			} else if ("custname".equals(propertyId)) {
				field = new TextField("NAME: ");
				field.setRequired(true);
				field.setRequiredError("Nama customer tidak boleh kosong");
				
				return field;				
			} else if ("address1".equals(propertyId)) {
				field = new TextField("ALAMAT: ");
				
				return field;				
			} else if ("city".equals(propertyId)) {
				field = new TextField("KOTA: ");
				
				return field;				
			} else if ("state".equals(propertyId)) {
				field = new TextField("PROPINSI: ");
				
				return field;				
			} else if ("postcode".equals(propertyId)) {
				field = new TextField("KODE POS: ");
				
				return field;				
			} else if ("contactperson".equals(propertyId)) {
				field = new TextField("CONTACT: ");
				
				return field;				
			} else if ("hp".equals(propertyId)) {
				field = new TextField("HP: ");
				
				return field;				
			} else if ("phone".equals(propertyId)) {
				field = new TextField("PHONE: ");
				
				return field;				
			} else if ("creditlimit".equals(propertyId)) {
				field = new TextField("CREDIT LIMIT: ");
				
				return field;				
			} else if ("crterm".equals(propertyId)) {
				field = new TextField("CREDIT TERM: ");
				
				return field;				
			} else if ("tunaikredit".equals(propertyId)) {
				field = new TextField("TUNAI/KRDIT: ");

				
				return field;				
			} else if ("typecustomer".equals(propertyId)) {
				field = new TextField("TIPE CUST: ");
				
				return field;				
			} else if ("regionBean".equals(propertyId)) {
//DARI JPA CONTAINER KE ARRAY LIST
				List<Region> list = new ArrayList<Region>();
				Collection itemIds = regionJPAContainer.getItemIds();
				for (Object object : itemIds) {
	                EntityItem entityItem = regionJPAContainer.getItem(object);
	                Object entity = entityItem.getEntity();
	                list.add((Region) entity);
	            }

//				ComboBox c = new ComboBox("Combobox: ");					
				ComboBox c = new ComboBox("REGION: ", list);					
				
				c.setNullSelectionAllowed(false);
				c.setNewItemsAllowed(false);
				c.setInputPrompt("Select One");
				
				
				return c;
			}else if ("joindate".equals(propertyId)) {
				field = new DateField("Tanggal Masuk: ");				
				return field;				
			}else if ("lasttrans".equals(propertyId)) {
				field = new DateField("Terakhir Trans: ");				
				return field;				
			}else if ("lastupdate".equals(propertyId)) {
				field = new DateField("Terakhir Update: ");				
				return field;				
			} else if ("divisionBean".equals(propertyId)) {
				//DARI JPA CONTAINER KE ARRAY LIST
				List<Division> list = new ArrayList<Division>();
				Collection itemIds = divisionJPAContainer.getItemIds();
				for (Object object : itemIds) {
	                EntityItem entityItem = divisionJPAContainer.getItem(object);
	                Object entity = entityItem.getEntity();
	                list.add((Division) entity);
	            }
	
				ComboBox c = new ComboBox("DIVISI: ", list);					
				
				c.setNullSelectionAllowed(false);
				c.setNewItemsAllowed(false);
				c.setInputPrompt("Select One");
				
				return c;
			}
			
//			//sori buat tanggal
//			else {
//				field = super.createField(item, propertyId, uiContext);				
//			}
			
//            if (propertyId.equals("joindate") || propertyId.equals("lasttrans")) {
//                ((DateField) field).setResolution(DateField.RESOLUTION_DAY);
//            }
			
            if (field instanceof AbstractTextField) {
                ((AbstractTextField) field).setNullRepresentation("");
            } 			
			return field;			
		}
    	
    }

	public CustomerModel getModel() {
		return model;
	}
	public void setModel(CustomerModel model) {
		this.model = model;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public CustomFieldFactory getCustomFieldFactory() {
		return customFieldFactory;
	}
	public void setCustomFieldFactory(CustomFieldFactory customFieldFactory) {
		this.customFieldFactory = customFieldFactory;
	}
	public FieldFactory getFieldFactory() {
		return fieldFactory;
	}
	public void setFieldFactory(FieldFactory fieldFactory) {
		this.fieldFactory = fieldFactory;
	}
	public Button getCommit() {
		return commit;
	}
	public void setCommit(Button commit) {
		this.commit = commit;
	}
	public Button getDiscard() {
		return discard;
	}
	public void setDiscard(Button discard) {
		this.discard = discard;
	}
	public Object[] getFormPropertyIds() {
		return formPropertyIds;
	}
	public void setFormPropertyIds(Object[] formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
	}
	public Button getAddButton() {
		return addButton;
	}
	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}
	public Button getDeleteButton() {
		return deleteButton;
	}
	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}
	public String getOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}
	public TextField getFieldSearchById() {
		return fieldSearchById;
	}
	public void setFieldSearchById(TextField fieldSearchById) {
		this.fieldSearchById = fieldSearchById;
	}
	public TextField getFieldSearchByDesc() {
		return fieldSearchByDesc;
	}
	public void setFieldSearchByDesc(TextField fieldSearchByDesc) {
		this.fieldSearchByDesc = fieldSearchByDesc;
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
	public Class<Customer> getEntityClass() {
		return entityClass;
	}
	public void setEntityClass(Class<Customer> entityClass) {
		this.entityClass = entityClass;
	}
	public TextField getFieldCustno() {
		return fieldCustno;
	}
	public void setFieldCustno(TextField fieldCustno) {
		this.fieldCustno = fieldCustno;
	}
	public ComboBox getComboDivision() {
		return comboDivision;
	}
	public void setComboDivision(ComboBox comboDivision) {
		this.comboDivision = comboDivision;
	}
	public TextField getFieldCustname() {
		return fieldCustname;
	}
	public void setFieldCustname(TextField fieldCustname) {
		this.fieldCustname = fieldCustname;
	}
	public TextField getFieldAddress1() {
		return fieldAddress1;
	}
	public void setFieldAddress1(TextField fieldAddress1) {
		this.fieldAddress1 = fieldAddress1;
	}
	public TextField getFieldCity1() {
		return fieldCity1;
	}
	public void setFieldCity1(TextField fieldCity1) {
		this.fieldCity1 = fieldCity1;
	}
	public TextField getFieldState() {
		return fieldState;
	}
	public void setFieldState(TextField fieldState) {
		this.fieldState = fieldState;
	}
	public TextField getFieldPostcode() {
		return fieldPostcode;
	}
	public void setFieldPostcode(TextField fieldPostcode) {
		this.fieldPostcode = fieldPostcode;
	}
	public TextField getFieldContactperson() {
		return fieldContactperson;
	}
	public void setFieldContactperson(TextField fieldContactperson) {
		this.fieldContactperson = fieldContactperson;
	}
	public TextField getFieldHp() {
		return fieldHp;
	}
	public void setFieldHp(TextField fieldHp) {
		this.fieldHp = fieldHp;
	}
	public TextField getFieldPhone() {
		return fieldPhone;
	}
	public void setFieldPhone(TextField fieldPhone) {
		this.fieldPhone = fieldPhone;
	}
	public TextField getFieldCreditlimit() {
		return fieldCreditlimit;
	}
	public void setFieldCreditlimit(TextField fieldCreditlimit) {
		this.fieldCreditlimit = fieldCreditlimit;
	}
	public TextField getFieldCrterm() {
		return fieldCrterm;
	}
	public void setFieldCrterm(TextField fieldCrterm) {
		this.fieldCrterm = fieldCrterm;
	}
	public ComboBox getComboTunaikredit() {
		return comboTunaikredit;
	}
	public void setComboTunaikredit(ComboBox comboTunaikredit) {
		this.comboTunaikredit = comboTunaikredit;
	}
	public TextField getFieldTypecustomer() {
		return fieldTypecustomer;
	}
	public void setFieldTypecustomer(TextField fieldTypecustomer) {
		this.fieldTypecustomer = fieldTypecustomer;
	}
	public DateField getDateFieldJoindate() {
		return dateFieldJoindate;
	}
	public void setDateFieldJoindate(DateField dateFieldJoindate) {
		this.dateFieldJoindate = dateFieldJoindate;
	}
	public DateField getDateFieldLasttrans() {
		return dateFieldLasttrans;
	}
	public void setDateFieldLasttrans(DateField dateFieldLasttrans) {
		this.dateFieldLasttrans = dateFieldLasttrans;
	}
	public DateField getDateFieldLastupdate() {
		return dateFieldLastupdate;
	}
	public void setDateFieldLastupdate(DateField dateFieldLastupdate) {
		this.dateFieldLastupdate = dateFieldLastupdate;
	}
	public FormLayout getFormLayout() {
		return formLayout;
	}
	public void setFormLayout(FormLayout formLayout) {
		this.formLayout = formLayout;
	}
	
	
}
