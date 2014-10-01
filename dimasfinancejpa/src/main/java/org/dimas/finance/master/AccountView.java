package org.dimas.finance.master;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dimas.finance.model.Account;
import org.dimas.finance.model.Accountgroup;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.util.HelpManager;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Item;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class AccountView extends CustomComponent {
	private AccountModel model;
	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	private Form form;
	private CustomFieldFactory customFieldFactory;
	private FieldFactory fieldFactory;

	private Class<Account> entityClass;
	
	private Button commit;
	private Button discard;
	private Object[] formPropertyIds;
	private Button addButton;
	private Button deleteButton;
	
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
	
	//Panel
	private Panel panelUtama;
	private Panel panelTop;
	private Panel panelTabel;
	private Panel panelForm;

	//Help Manager	
    private HelpManager helpManager;
	
	public AccountView(AccountModel model){
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
		form = new Form();
		form.setVisible(false);
		form.setBuffered(true);
		form.setImmediate(false);		
		form.setFormFieldFactory(customFieldFactory);

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
		
		form.getFooter().addComponent(commit);
		form.getFooter().addComponent(discard);
		((HorizontalLayout) form.getFooter()).setSpacing(true);		
		panelForm.setContent(form);
		
		verticalSplitPanel.setFirstComponent(panelTabel);		
		verticalSplitPanel.setSecondComponent(panelForm);

		content.addComponent(panelTop);
		content.addComponent(verticalSplitPanel);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		content.setExpandRatio(verticalSplitPanel, 1);
		
	}
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setVisibleFormProperties(Object... formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
		form.setVisibleItemProperties(formPropertyIds);
	}
	public void setTableProperties(){
		setVisibleTableProperties("id","name", "actualbalance",  "tipedebetkredit", 
				"accountgroup", "divisionBean", "bankBean");
		
		
		//set header
		table.setColumnHeader("id", "ID");
		table.setColumnHeader("name", "NAMA ACCOUNT");
		table.setColumnHeader("actualbalance", "ACTUAL BALANCE");
		table.setColumnHeader("tipedebetkredit", "TIPE DEBET KREDIT");
		table.setColumnHeader("accountgroup", "GROUP");
		table.setColumnHeader("divisionBean", "DIVISION");
		table.setColumnHeader("bankBean", "BANK");
		
		table.setColumnExpandRatio("id", 1);
		table.setColumnExpandRatio("name", 3);		
		table.setColumnExpandRatio("actualbalance", 2);		
		table.setColumnExpandRatio("tipedebetkredit", 1);		
		table.setColumnExpandRatio("accountgroup", 1);
		table.setColumnExpandRatio("divisionBean", 1);		
		table.setColumnExpandRatio("bankBean", 1);		
	}
	public void setFormProperties(){
		setVisibleFormProperties("id", "name", "actualbalance", "tipedebetkredit", "accountgroup", "divisionBean", "bankBean");
	}
	public void setDisplay(){
		//1. Refresh Table displa
		table.setContainerDataSource(model.getTableJpaContainer());
		
		//2. Jika table masih dalam kondisi di seleksi maka form masih diisi dengan hasil seleksi
		
		//SORI HABIS SAVE (insert atau update) di paksa untuk tidak diload lagi
//		if (table.getValue() != null) {
//		// reset form as e.g. referenced containers may have changed
//			form.setItemDataSource(table.getItem(table.getValue()));
//		}
		
		setTableProperties();
		setFormProperties();
		
		//3. Hitung Total & Jumlah Record dll
		Collection items =  model.getTableJpaContainer().getItemIds();
		table.setColumnFooter("id", "*Jumlah Record: " + items.size());
		
	}
	
	public void setFormButtonAndText(){
		if (operationStatus.equals(EnumFormOperationStatus.OPEN.getStrCode())){
			form.setVisible(false);
			table.setSelectable(true);
			addButton.setEnabled(true);
			deleteButton.setEnabled(true);						
		} else if (operationStatus.equals(EnumFormOperationStatus.ADDING.getStrCode())){
			form.setVisible(true);
			table.setSelectable(false);
			addButton.setEnabled(false);
			deleteButton.setEnabled(false);			
		}else if (operationStatus.equals(EnumFormOperationStatus.EDITING.getStrCode())){
			form.setVisible(true);
			table.setSelectable(true);
			addButton.setEnabled(true);
			deleteButton.setEnabled(true);			
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
			final JPAContainer<Accountgroup> accountGroupJPAContainer = JPAContainerFactory.make(Accountgroup.class, "financePU");
			final JPAContainer<Division> divisionJPAContainer = JPAContainerFactory.make(Division.class, "financePU");
			final JPAContainer<Bank> bankJPAContainer = JPAContainerFactory.make(Bank.class, "financePU");
			
			Field field = null;
			if ("id".equals(propertyId)) {
				field = new TextField("ID: ");
				field.setRequired(true);
				field.setRequiredError("ID tidak boleh Kosong");
                field.addValidator(new StringLengthValidator("Nama antar 1 s/d 6", 1, 6, false));
				return field;
			} else if ("name".equals(propertyId)) {
				field = new TextField("NAMA ACCOUNT: ");
				field.setRequired(true);
				field.setRequiredError("Nama account tidak boleh kosong");
				
				return field;				
			} else if ("actualbalance".equals(propertyId)) {
				field = new TextField("ACTUAL BALANCE: ");
				field.setRequired(true);
				field.setRequiredError("Actual balance tidak boleh kosong");
				
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

				
//				ComboBox c = new ComboBox("Combobox: ");					
				ComboBox c = new ComboBox("DIVISI: ", list);					
				
				c.setNullSelectionAllowed(false);
				c.setNewItemsAllowed(false);
				c.setInputPrompt("Select One");
				
				return c;
			} else if("accountgroup".equals(propertyId)){
				//DARI JPA CONTAINER KE ARRAY LIST
				List<Accountgroup> list = new ArrayList<Accountgroup>();
				Collection itemIds = accountGroupJPAContainer.getItemIds();
				for (Object object : itemIds) {
	                EntityItem entityItem = accountGroupJPAContainer.getItem(object);
	                Object entity = entityItem.getEntity();
	                list.add((Accountgroup) entity);
	            }

//				ComboBox c = new ComboBox("Combobox: ");					
				ComboBox c = new ComboBox("GROUP: ", list);					
				
				c.setNullSelectionAllowed(false);
				c.setNewItemsAllowed(false);
				c.setInputPrompt("Select One");
				
				return c;				
			} else if("bankBean".equals(propertyId)){
				//DARI JPA CONTAINER KE ARRAY LIST
				List<Bank> list = new ArrayList<Bank>();
				Collection itemIds = bankJPAContainer.getItemIds();
				for (Object object : itemIds) {
	                EntityItem entityItem = bankJPAContainer.getItem(object);
	                Object entity = entityItem.getEntity();
	                list.add((Bank) entity);
	            }

//				ComboBox c = new ComboBox("Combobox: ");					
				ComboBox c = new ComboBox("BANK: ", list);					
				
				c.setNullSelectionAllowed(true);
				c.setNewItemsAllowed(false);
				c.setInputPrompt("Select One");
				
				return c;				
			} else if("tipedebetkredit".equals(propertyId)){
				//DARI JPA CONTAINER KE ARRAY LIST
				ComboBox c = new ComboBox("Debet/Kredit: ");		
				c.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
				c.addItem("D");
				c.setItemCaption("D", "Debet");
				c.addItem("K");
				c.setItemCaption("K", "Kredit");
				
				c.setRequired(true);
				c.setRequiredError("Tipe Debet atau Kredit tidak boleh kosong");				
				c.addValidator(new BeanValidator(Account.class, "tipedebetkredit"));
				
				c.setNullSelectionAllowed(false);
				c.setNewItemsAllowed(false);
				c.setInputPrompt("Select One");
				
				return c;				
			}
			
//			else {
//				field = super.createField(item, propertyId, uiContext);				
//			}
			
            if (propertyId.equals("tanggaltanggal") || propertyId.equals("tanggaltanggalanLaen")) {
                ((DateField) field).setResolution(DateField.RESOLUTION_DAY);
            }
            if (field instanceof AbstractTextField) {
                ((AbstractTextField) field).setNullRepresentation("");
            } 			
			return field;			
		}
    	
    }

	public AccountModel getModel() {
		return model;
	}
	public void setModel(AccountModel model) {
		this.model = model;
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
	
	
}
