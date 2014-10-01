package org.dimas.finance.master;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dimas.finance.model.Division;
import org.dimas.finance.model.Salesman;
import org.dimas.finance.util.HelpManager;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
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
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class SalesmanBinderView extends CustomComponent{
	private SalesmanBinderModel model;
	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	private Form form;
	private FormLayout formLayout = new FormLayout();
	private BeanFieldGroup<Salesman> binder = new BeanFieldGroup<Salesman>(Salesman.class);
	
	private CustomFieldFactory customFieldFactory;
	private FieldFactory fieldFactory;

	private Class<Salesman> entityClass;
	
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
    
    public SalesmanBinderView(SalesmanBinderModel model){
    	this.model = model;

    	table = new Table("Table:", model.getTableJpaContainer());		
		
    	fieldFactory = new FieldFactory();
		customFieldFactory = new CustomFieldFactory();		

    	buildView();
    	
    	setDisplay();
    	
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
		table.setImmediate(true);
		table.setBuffered(false);
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
		formLayout = new FormLayout();
//		binder.setFieldFactory(new DefaultFieldGroupFieldFactory());
		
		//INITIAL DUMMY DATA
		Salesman salesman = new Salesman();
		binder.setItemDataSource(salesman);
		binder.setFieldFactory(new CustomFieldGroupFieldFactory());
		
		formLayout.addComponent(binder.buildAndBind("SPCODE", "id.spcode"));
//		formLayout.addComponent(binder.buildAndBind("Divisi", "divisionBean"));
		TextField coba = new TextField("Coba");
		binder.bind(coba, "spname");
		formLayout.addComponent(coba);
		
		ComboBox comboBox = new ComboBox("DIVISI", model.getBeanItemContainerDivision());
//	comboBox.setItemCaptionPropertyId("id");
		comboBox.setItemCaptionPropertyId("divisi");
		comboBox.setItemCaptionMode(ItemCaptionMode.PROPERTY);
//	comboBox.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		binder.bind(comboBox, "divisionBean");
		
		
//		ComboBox comboBox = new ComboBox("DIVISI", model.getBeanItemContainerDivision());
////		comboBox.setItemCaptionPropertyId("id");
//		comboBox.setItemCaptionPropertyId("divisi");
//		comboBox.setItemCaptionMode(ItemCaptionMode.PROPERTY);
////		comboBox.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//		binder.bind(comboBox, "divisionBean");
		
		formLayout.addComponent(comboBox);
		
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
		
		verticalSplitPanel.setFirstComponent(panelTabel);		
		verticalSplitPanel.setSecondComponent(formLayout);
//		verticalSplitPanel.setSecondComponent(panelForm);
		

		content.addComponent(panelTop);
		content.addComponent(verticalSplitPanel);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		content.setExpandRatio(verticalSplitPanel, 1);
		
		
			
    	
    }
    
    public void setDisplay(){
    	if (model.getItem() == null){
    		Salesman salesman = new Salesman();
    		model.setItem(salesman);
    	}	
		binder.setItemDataSource(model.getItem());
//    	binder.setFieldFactory(new CustomFieldGroupFieldFactory());
//    	Notification.show("hehehe");
		
    }
    protected static class CustomFieldGroupFieldFactory extends DefaultFieldGroupFieldFactory{
		public Field createField(Item item, Object propertyId, Component uiContext){
			final JPAContainer<Division> divisionJPAContainer = JPAContainerFactory.make(Division.class, "financePU");
			
			Field field = null;
			if ("id.spcode".equals(propertyId)) {
				field = new TextField("ID SALESMAN: ");
				field.setRequired(true);
				field.setRequiredError("ID tidak boleh Kosong");
                field.addValidator(new StringLengthValidator("Nama antar 1 s/d 6", 1, 6, false));
				return field;
			} else if ("spname".equals(propertyId)) {
				field = new TextField("NAMA: ");
				field.setRequired(true);
				field.setRequiredError("Nama salesman tidak boleh kosong");
//                field.addValidator(new StringLengthValidator("Nama antar 3 s/d 30", 3, 30, false));
//Bean Validator juga tidak bisa disini				
//				field.addValidator(new BeanValidator(Person.class, "name"));	
				
				return field;				
			} else if ("salestype2".equals(propertyId)) {
				field = new TextField("TIPE SALES: ");
				field.setRequired(true);
				field.setRequiredError("Tipe sales tidak boleh kosong");
				
				return field;				
			} else if ("salestype".equals(propertyId)) {
				
//				List<String> list = new ArrayList<String>();
//				list.add("TO");
//				list.add("C");
//				list.add("SHOP");
				
//				ComboBox c = new ComboBox("TIPE SALES: ", list);
				ComboBox c = new ComboBox("Combobox: ");					
				c.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);				
				c.addItem("T");
				c.setItemCaption("T", "Task Force");
				c.addItem("C");
				c.setItemCaption("C", "Canvas");
				c.addItem("S");
				c.setItemCaption("S", "Shop Sales");
		
				
				c.setNullSelectionAllowed(false);
				c.setNewItemsAllowed(false);
				c.setInputPrompt("Select One");
				
//				Gak tahu kok gak bisa ya?
//				c.setItemCaptionPropertyId("name");
//				c.setItemCaptionMode(ItemCaptionMode.ITEM);				
//				c.setItemCaptionMode(NativeSelect.ITEM_CAPTION_MODE_ITEM);				
//				c.setItemCaptionPropertyId("name");
				
				return c;
			
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
    
    protected static class CustomFieldFactory extends DefaultFieldFactory {
		public Field createField(Item item, Object propertyId, Component uiContext){
			final JPAContainer<Division> divisionJPAContainer = JPAContainerFactory.make(Division.class, "financePU");
			
			Field field = null;
			if ("spcode".equals(propertyId)) {
				field = new TextField("ID SALESMAN: ");
				field.setRequired(true);
				field.setRequiredError("ID tidak boleh Kosong");
                field.addValidator(new StringLengthValidator("Nama antar 1 s/d 6", 1, 6, false));
				return field;
			} else if ("spname".equals(propertyId)) {
				field = new TextField("NAMA: ");
				field.setRequired(true);
				field.setRequiredError("Nama salesman tidak boleh kosong");
//                field.addValidator(new StringLengthValidator("Nama antar 3 s/d 30", 3, 30, false));
//Bean Validator juga tidak bisa disini				
//				field.addValidator(new BeanValidator(Person.class, "name"));	
				
				return field;				
			} else if ("salestype2".equals(propertyId)) {
				field = new TextField("TIPE SALES: ");
				field.setRequired(true);
				field.setRequiredError("Tipe sales tidak boleh kosong");
				
				return field;				
			} else if ("salestype".equals(propertyId)) {
				
//				List<String> list = new ArrayList<String>();
//				list.add("TO");
//				list.add("C");
//				list.add("SHOP");
				
//				ComboBox c = new ComboBox("TIPE SALES: ", list);
				ComboBox c = new ComboBox("Combobox: ");					
				c.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);				
				c.addItem("T");
				c.setItemCaption("T", "Task Force");
				c.addItem("C");
				c.setItemCaption("C", "Canvas");
				c.addItem("S");
				c.setItemCaption("S", "Shop Sales");
		
				
				c.setNullSelectionAllowed(false);
				c.setNewItemsAllowed(false);
				c.setInputPrompt("Select One");
				
//				Gak tahu kok gak bisa ya?
//				c.setItemCaptionPropertyId("name");
//				c.setItemCaptionMode(ItemCaptionMode.ITEM);				
//				c.setItemCaptionMode(NativeSelect.ITEM_CAPTION_MODE_ITEM);				
//				c.setItemCaptionPropertyId("name");
				
				return c;
			
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

	public SalesmanBinderModel getModel() {
		return model;
	}

	public void setModel(SalesmanBinderModel model) {
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

	public Class<Salesman> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<Salesman> entityClass) {
		this.entityClass = entityClass;
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
