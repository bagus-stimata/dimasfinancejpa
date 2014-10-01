package org.dimas.finance.master;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dimas.finance.model.Division;
import org.dimas.finance.model.Salesman;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.util.HelpManager;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Item;
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

public class BkktranstypeView extends CustomComponent {
	private BkktranstypeModel model;
	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	private Form form;
	private FieldFactory fieldFactory;

	private Class<Salesman> entityClass;
	
	private Button commit;
	private Button discard;
	private Object[] formPropertyIds;
	private Button addButton;
	private Button deleteButton;
	
	private TextField fieldId = new TextField("ID");
	private TextField fieldDescription = new TextField("DESCRIPTION");
	private ComboBox comboGrup = new ComboBox("GRUP");
	private ComboBox comboAccount = new ComboBox("ACCOUNT");
	
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
	
	public BkktranstypeView(BkktranstypeModel model){
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
//		form = new Form();
//		form.setVisible(false);
//		form.setBuffered(true);
//		form.setImmediate(false);
//		form.setFormFieldFactory(customFieldFactory);
		//KOMPONEN BAWAH
		fieldId.setNullRepresentation("");
		fieldDescription.setNullRepresentation("");
		
		formLayout.addComponent(fieldId);
		formLayout.addComponent(fieldDescription);
		formLayout.addComponent(comboGrup);
		formLayout.addComponent(comboAccount);

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
		form.setVisibleItemProperties(formPropertyIds);
	}
	public void setTableProperties(){
		setVisibleTableProperties("id","description", "grup", "accountBean");
		//SET HEADER
		table.setColumnHeader("id", "ID");
		table.setColumnHeader("description", "DESCRIPTION");
		table.setColumnHeader("grup", "GRUP");
		table.setColumnHeader("accountBean", "ACCOUNT");
		
		table.setColumnExpandRatio("id", 1);
		table.setColumnExpandRatio("description", 2);		
		table.setColumnExpandRatio("grup", 1);		
		table.setColumnExpandRatio("accountBean", 1);		
	}
	public void setFormProperties(){
		setVisibleFormProperties("id", "description", "accountBean");
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
//		setFormProperties();
		bindAndBuildFieldGroupComponent();
		
		//3. Hitung Total & Jumlah Record dll
		Collection items =  model.getTableJpaContainer().getItemIds();
		table.setColumnFooter("id", "*Jumlah Record: " + items.size());
		
	}
	public void bindAndBuildFieldGroupComponent(){
		//Init isian combobox
		comboAccount.setContainerDataSource(model.getBeanItemContainerAccount());
		comboAccount.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboAccount.setNullSelectionAllowed(false);
		
		comboGrup.setNullSelectionAllowed(false);
//		comboGrup.addItem("GRUP1");
//		comboGrup.setItemCaption("GRUP1", "Setoran");
//		comboGrup.addItem("GRUP2");
		comboGrup.setItemCaption("GRUP2", "Penerimaan Kas Kecil");
		comboGrup.addItem("GRUP3");
		comboGrup.setItemCaption("GRUP3", "Pengeluaran Kas Kecil");
		comboGrup.addItem("GRUP4");
		comboGrup.setItemCaption("GRUP4", "Mutasi Kas Besar");
		comboGrup.addItem("GRUPL");
		comboGrup.setItemCaption("GRUPL", "Lain-lain");
		
		model.getBinderBkktranstype().bind(fieldId, "id");
		model.getBinderBkktranstype().bind(fieldDescription, "description");
		model.getBinderBkktranstype().bind(comboGrup, "grup");
		model.getBinderBkktranstype().bind(comboAccount, "accountBean");
		
	}
	
	
	public void setFormButtonAndText(){
		if (operationStatus.equals(EnumFormOperationStatus.OPEN.getStrCode())){
			formLayout.setVisible(false);
			table.setSelectable(true);
			addButton.setEnabled(true);
			deleteButton.setEnabled(true);						
		} else if (operationStatus.equals(EnumFormOperationStatus.ADDING.getStrCode())){
			fieldId.setReadOnly(false);
//			comboAccount.setReadOnly(false);
			formLayout.setVisible(true);
			table.setSelectable(false);
			addButton.setEnabled(false);
			deleteButton.setEnabled(false);			
		}else if (operationStatus.equals(EnumFormOperationStatus.EDITING.getStrCode())){
			fieldId.setReadOnly(true);
//			comboAccount.setReadOnly(true);
			formLayout.setVisible(true);
			table.setSelectable(true);
			addButton.setEnabled(true);
			deleteButton.setEnabled(true);			
		}		
		//TOMBOL TAMBAH TIDAK BISA
//		addButton.setEnabled(false);
	}
	
	public void focustIdOrDesc(){
		if (fieldId.isEnabled()){
			fieldId.focus();
		} else {
			fieldDescription.focus();		                    						
		}		
	}
	public BkktranstypeModel getModel() {
		return model;
	}
	public void setModel(BkktranstypeModel model) {
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
	public TextField getFieldId() {
		return fieldId;
	}
	public void setFieldId(TextField fieldId) {
		this.fieldId = fieldId;
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
	public ComboBox getComboGrup() {
		return comboGrup;
	}
	public void setComboGrup(ComboBox comboGrup) {
		this.comboGrup = comboGrup;
	}
	
	
	
	
}
