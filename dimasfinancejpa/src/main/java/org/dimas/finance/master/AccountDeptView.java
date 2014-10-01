package org.dimas.finance.master;

import java.util.Arrays;
import java.util.Collection;

import org.dimas.finance.model.Region;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.model.modelenum.EnumHelpOverlayTipe;
import org.dimas.finance.util.HelpManager;
import org.dimas.finance.util.HelpOverlay;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;

public class AccountDeptView extends CustomComponent implements ValueChangeListener, Handler, ClickListener{
	public static final String PERSISTENCE_UNIT = "financePU";
	
	private JPAContainer<Region> container;
	private Table table;
	private Form form;
	private CustomFieldFactory customFieldFactory;
	private FieldFactory fieldFactory;
	private Class<Region> entityClass;
	private Button commit;
	private Button discard;
	private Object[] formPropertyIds;
	private Button addButton;
	private Button deleteButton;
	private Panel panel;
	private final String persistenceUnit;
	
	private Region accountDept;
	private BeanItem<Region> beanItemAccountDept;
	
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
	
	public AccountDeptView() {
		operationStatus = EnumFormOperationStatus.OPEN.getStrCode();
		
		//Account Grup
		accountDept = new Region();
		//Bean item accountDept dideklarasikan hanya jika dipakai
		
		this.entityClass = Region.class;
		this.persistenceUnit = PERSISTENCE_UNIT;
		setSizeFull();
		initContainer();
		initFieldFactory();
		buildView();
		
	}
	public void initContainer(){
		container = JPAContainerFactory.make(Region.class, persistenceUnit);
		table = new Table(null, container);		
	}
	
	public void initFieldFactory(){
		fieldFactory = new FieldFactory();
		customFieldFactory = new CustomFieldFactory();
	}
	
	public void buildView(){
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		
		//Inisialisasi Panel 
		setSizeFull();
		panelUtama = new Panel(getCaption());
		panelUtama.setSizeFull();

		panelTop = new Panel();
		panelTabel = new Panel();
		panelForm = new Panel();

		//Init Komponen atas
		fieldSearchById = new TextField();
		fieldSearchByDesc = new TextField();
		
		//Init komponen tengah
		table.setSizeFull();
		table.setSelectable(true);
		table.addValueChangeListener(this);
		table.setImmediate(true);
		table.setBuffered(false);
		table.addActionHandler(this);		
		table.setFooterVisible(true);
		
		//Deklarasi Button dan Listener	
		addButton = new Button("Add New", this);
		deleteButton = new Button("Delete", this);
		commit = new Button("Save", this);		
		discard = new Button("Cancel", this);
		btnSearch = new Button("Search", this);
		btnReload = new Button("Reload", this);
		btnPrint = new Button("Print", this);
		btnHelp = new Button("Help", this);
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
//		setSizeFull();
	
		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
		verticalSplitPanel.setSizeFull();		
		verticalSplitPanel.setSplitPosition(30);
		
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
		
		// register action handler (enter and ctrl-n)
		panelUtama.addActionHandler(this);
		panelTop.addActionHandler(this);
		panelTabel.addActionHandler(this);
		panelForm.addActionHandler(this);
		
		operationStatus = EnumFormOperationStatus.OPEN.getStrCode();
		setFormButtonAndText();
		
//Field Table dan Form yang Muncul
		setVisibleTableProperties("id");
		//Sementara dihandle oleh CustomFieldFactory
		setVisibleFormProperties("id");
		
		refreshContainer();
		
	}

	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);
	}

	public void setVisibleFormProperties(Object... formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
		form.setVisibleItemProperties(formPropertyIds);
	}
	
    protected static class CustomFieldFactory extends DefaultFieldFactory {
		public Field createField(Item item, Object propertyId, Component uiContext){
//			final JPAContainer<AccountDept> deptJPAContainer = JPAContainerFactory.make(AccountDept.class, PERSISTENCE_UNIT);
//			final JPAContainer<AccountGrup> grupJPAContainer = JPAContainerFactory.make(AccountGrup.class, PERSISTENCE_UNIT);
			
			Field field = null;
			if ("id".equals(propertyId)) {
				field = new TextField("ID: ");
				field.setRequired(true);
				field.setRequiredError("ID tidak boleh Kosong");
                field.addValidator(new StringLengthValidator("Nama antar 1 s/d 5", 1, 5, false));
				return field;
			} else if ("name".equals(propertyId)) {
				field = new TextField("Desc: ");
				field.setRequired(true);
				field.setRequiredError("Description tidak boleh kosong");
//                field.addValidator(new StringLengthValidator("Nama antar 3 s/d 30", 3, 30, false));
//Bean Validator juga tidak bisa disini				
//				field.addValidator(new BeanValidator(Person.class, "name"));	
				
				return field;				
			} else if ("combobox1".equals(propertyId)) {
//DARI JPA CONTAINER KE ARRAY LIST
//				List<AccountDept> list = new ArrayList<AccountDept>();
//				Collection itemIds = deptJPAContainer.getItemIds();
//				for (Object object : itemIds) {
//	                EntityItem entityItem = deptJPAContainer.getItem(object);
//	                Object entity = entityItem.getEntity();
//	                list.add((AccountDept) entity);
//	            }

				ComboBox c = new ComboBox("Combobox: ");					
//				ComboBox c = new ComboBox("DEPT: ", list);					
				
//				c.setNullSelectionAllowed(false);
//				c.setNewItemsAllowed(false);
//				c.setInputPrompt("Select One");
				
//				Gak tahu kok gak bisa ya?
//				c.setItemCaptionPropertyId("name");
//				c.setItemCaptionMode(ItemCaptionMode.ITEM);				
//				c.setItemCaptionMode(NativeSelect.ITEM_CAPTION_MODE_ITEM);				
//				c.setItemCaptionPropertyId("name");
				
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

	
	@Override
	public void valueChange(ValueChangeEvent event) {
		
		
		Object itemId = event.getProperty().getValue();
		Item item = table.getItem(itemId);
		boolean entitySelected = item != null;
		// modify visibility of form and delete button if an item is selected
		form.setVisible(entitySelected);
		deleteButton.setEnabled(entitySelected);
		if (entitySelected) {
			// set entity item to form and focus it
			form.setItemDataSource(item,
					formPropertyIds != null ? Arrays.asList(formPropertyIds)
							: item.getItemPropertyIds());
//			form.focus();
		}
		
		operationStatus = EnumFormOperationStatus.EDITING.getStrCode();
		setFormButtonAndText();
		
//		Notification.show("Table Di Klik!!!");
		
	}
	
	private static final ShortcutAction ENTER = new ShortcutAction("Enter",
			KeyCode.ENTER, null);

	private static final ShortcutAction ENTER_SEARCH= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ENTER_TABLE= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ENTER_FORM= new ShortcutAction("Enter",
			KeyCode.ENTER, null);

	private static final ShortcutAction ESCAPE = new ShortcutAction("Escape",
			KeyCode.ESCAPE, null);
	private static final ShortcutAction ESCAPE_SEARCH = new ShortcutAction("Escape",
			KeyCode.ESCAPE, null);
	private static final ShortcutAction ESCAPE_TABLE = new ShortcutAction("Escape",
			KeyCode.ESCAPE, null);
	private static final ShortcutAction ESCAPE_FORM = new ShortcutAction("Escape",
			KeyCode.ESCAPE, null);
	
	//Key Code Baru
	private static final ShortcutAction INSERT= new ShortcutAction("Insert",
			KeyCode.INSERT, null);
	private static final ShortcutAction DELETE = new ShortcutAction("Del",
			KeyCode.DELETE, new int[] { ShortcutAction.ModifierKey.SHIFT});
	
	private static final ShortcutAction ALTS = new ShortcutAction("Save",
			KeyCode.S, new int[] { ShortcutAction.ModifierKey.ALT });
	private static final ShortcutAction ALTC = new ShortcutAction("Cancel",
			KeyCode.C, new int[] { ShortcutAction.ModifierKey.ALT });
	
	
	
	private static final ShortcutAction REFRESH = new ShortcutAction("Refresh",
			KeyCode.F5, null);

	private static final ShortcutAction HELP = new ShortcutAction("Help",
			KeyCode.F1, null);
	private static final ShortcutAction EDITMODE = new ShortcutAction("Edit Mode",
			KeyCode.F2, null);
	private static final ShortcutAction FINDMODE = new ShortcutAction("Find Mode",
			KeyCode.F3, null);
	private static final ShortcutAction FIND = new ShortcutAction("Find ",
			KeyCode.F4, null);
	
	private static final ShortcutAction ADD = new ShortcutAction("Add",
			KeyCode.A, new int[] { ShortcutAction.ModifierKey.ALT });
	private static final ShortcutAction DEL = new ShortcutAction("Del",
			KeyCode.D, new int[] { ShortcutAction.ModifierKey.ALT });
	
	private static final Action[] ACTIONS_SEARCH = new Action[] {ENTER_SEARCH, ESCAPE_SEARCH, INSERT, DELETE,  
		ALTS, ALTC, REFRESH, HELP, EDITMODE, FINDMODE, FIND, ADD, DEL};
	private static final Action[] ACTIONS_TABLE = new Action[] {ENTER_TABLE, ESCAPE_TABLE, INSERT, DELETE,  
		ALTS, ALTC, REFRESH, HELP, EDITMODE, FINDMODE, FIND, ADD, DEL};
	private static final Action[] ACTIONS_FORM = new Action[] {ENTER_FORM, ESCAPE_FORM, INSERT, DELETE,  
		ALTS, ALTC, REFRESH, HELP, EDITMODE, FINDMODE, FIND, ADD, DEL};
	
	private static final Action[] ACTIONS = new Action[] {};
	private static final Action[] SHORTCUT_ACTIONS = new Action[] { INSERT, DELETE, ESCAPE, 
		ALTS, ALTC, ENTER, REFRESH, HELP, EDITMODE, FINDMODE, FIND, ADD, DEL};
	
	@Override
	public Action[] getActions(Object target, Object sender) {
		if (sender ==panelTop) {
			return ACTIONS_SEARCH;
		} else if (sender==panelTabel){
			return ACTIONS_TABLE;
		} else if (sender==panelForm){
			return ACTIONS_FORM;
		}
		return ACTIONS;
	}
	@Override
	public void handleAction(Action action, Object sender, Object target) {		
		if (action==INSERT){
			addButton.click();
		}else if (action==DELETE){
			deleteForm();
		}else if (action==ENTER_SEARCH){
			btnSearch.click();
			table.focus();
		}else if (action==ENTER_TABLE){
			form.focus();
		}else if (action==ENTER_FORM){
			//MENGGANTI TAB: HARUSNYA
//			commit.click();
		}else if (action==ESCAPE_SEARCH){
			table.focus();
		}else if (action==ESCAPE_TABLE){
		}else if (action==ESCAPE_FORM){
			discard.click();
		}else if (action==ALTS){
			commit.click();
		}else if (action==ALTC){
			if (form.isVisible()){
				discardForm(operationStatus);
			}
		}else if (action==ENTER){
			
		}else if (action==EDITMODE){
			form.focus();			
		}else if (action==FINDMODE){
			fieldSearchById.focus();
		}else if (action==FIND){			
			searchForm();
		}else if (action==REFRESH){
			btnReload.click();
		}else if (action==HELP){
			helpForm();
		}else if (action==ADD){		
			
		}else if (action==DEL){		
			deleteForm();
		}
	}
	@Override
	public void buttonClick(ClickEvent event) {	
		if (event.getButton() == addButton) {
			if (operationStatus.equals(EnumFormOperationStatus.OPEN.getStrCode())){
				addItem();
			}else if (operationStatus.equals(EnumFormOperationStatus.EDITING.getStrCode())){
				discardForm(operationStatus);
			}
		} else if (event.getButton() == deleteButton) {
			deleteForm();
		} else if (event.getButton() == commit){
			 ConfirmDialog commitDialog = ConfirmDialog.show(getUI(),"Konfirmasi Simpan", "Simpan Data?", "Save", "No",
		                new ConfirmDialog.Listener() {
		                    public void onClose(ConfirmDialog dialog) {
		                        if (dialog.isConfirmed()) {
		                            // Confirmed to continue
		                    		try {			
		                    			if (operationStatus.equals(EnumFormOperationStatus.ADDING.getStrCode())){
		                    				if (insertAndValidateForm()==0){		                    					
		                    					//refreshContainer(); //sudah dihanle oleh method  insertAndValidateForm()
		                    					table.focus();
		                    				}else {
		                    					form.focus();
		                    				}
		                    			} else if (operationStatus.equals(EnumFormOperationStatus.EDITING.getStrCode())){
		                    				if (updateAndValidateForm()==0){				                    			
		                    					//refreshContainer(); //Sudah dihandle oleh method AndValidateForm()
		                    					table.focus();		                    					
		                    				}else {
		                    					form.focus();
		                    				}
		                    			}
		                    		} catch(Exception ex) {
		                    			Notification.show("Terjadi Kesalahan Simpan");
		                    		}
		                        	
		                        } else {
		                        	form.focus();
		                            // User did not confirm
		                        }
		                    }
		                });	        
			 commitDialog.setStyleName("dialog");
			 commitDialog.getOkButton().setStyleName("small");
			 commitDialog.getCancelButton().setStyleName("small");
			 //Jangan lupa
			 commitDialog.focus();
			
		} else if(event.getButton() == discard) {
			if (operationStatus.equals(EnumFormOperationStatus.ADDING.getStrCode())){
				discardForm(operationStatus);
			} else if (operationStatus.equals(EnumFormOperationStatus.ADDING.getStrCode()) || 
					operationStatus.equals(EnumFormOperationStatus.EDITING.getStrCode())){
				if (form.isVisible()){
					discardForm(operationStatus);
				}				
			}else{	
				table.focus();
			}		
			
//			discardForm(operationStatus);
		} else if (event.getButton() == btnSearch) {
			searchForm();
		} else if (event.getButton() == btnReload){
			reloadForm();			
			if (operationStatus.equals(EnumFormOperationStatus.OPEN.getStrCode())){
				table.focus();
			}
		} else if (event.getButton() == btnPrint){			
			printForm();
		}else if (event.getButton() == btnHelp){
			helpForm();
		}
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
	}

	private void deleteItem(final Object itemId) {
		if (itemId != null){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		container.removeItem(itemId);
                    		refreshContainer();
                			Notification.show("Delete Sukses");		                        		
                    	} catch(Exception ex) {
                			Notification.show("Error Delete!!");		                        		
                    	}
                    } else {
                    // User did not confirm
                    }
					table.focus();
				}
			};
			
			 final ConfirmDialog d = ConfirmDialog.show(getUI(),"Konfirmasi Hapus", "Yakin akan hapus data?", 
					 "Oke Delete..", "Cancel", konfirmDialogListener);
			 
			   final ShortcutListener enter = new ShortcutListener("Oke",
		                KeyCode.ENTER, null) {
		            @Override
		            public void handleAction(Object sender, Object target) {
		            	d.close();
		            }
		        };
			
			
		 d.setStyleName("dialog");
		 d.getOkButton().setStyleName("small");
		 d.getCancelButton().setStyleName("small");
		 d.focus();
		
	}else {
		Notification.show("Tidak ada yang dipilih!!!");
	}
	
		
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
	
	protected void addItem() {
		try {
			//1. Deklarasikan object awal
			accountDept  = new Region();  
			//Karena tidak pisa pake null representation
			accountDept.setId("New");
			//2. Tampilkan form
			beanItemAccountDept = new BeanItem<Region>(accountDept);
			form.setItemDataSource(beanItemAccountDept);
			form.setVisible(true);
//			Object itemId = container.addEntity(newInstance);
//			table.setValue(itemId);
			//3. Atur kondisi status form
			operationStatus = EnumFormOperationStatus.ADDING.getStrCode();
			setFormButtonAndText();
			
//			T newInstance = newInstance();
//			Object itemId = container.addEntity(newInstance);
//			table.setValue(itemId);
			
			//REFRESH CONTAINER BIKIN ERROR
			//4. Refresh container jika ada perubahan dari user lain
			//Focus ke ID
			try{
				form.getField("id").focus();
			} catch(Exception ex){} 
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int updateAndValidateForm(){
		try{
			//1. Commit form
			form.commit();
			//2. refresh container
			refreshContainer();
			Notification.show("Update data Sukses!!!");
			try{
				table.focus();				
			} catch(Exception ex){}
			
		} catch(Exception ex){
			form.focus();
			return 1;
		}
		return 0;
	}
	public int insertAndValidateForm(){
		boolean kodeValid = true;
		boolean inputValid = true;
		String theId = form.getField("id").getValue().toString();
		//1. Validasi Kode
		if (theId.equalsIgnoreCase("New")) {
			kodeValid = false;
			Notification.show("Kode Tidak Boleh Memakai New");
			form.focus();
			return 1;
		}
		Equal filter = new Compare.Equal("id", theId);
		container.addContainerFilter(filter);
		Collection itemIds = container.getItemIds();
		if (itemIds.size()!= 0){
			kodeValid = false;
			Notification.show("Kode Sudah Terpakai!!.");	
			form.focus();
			return 1;
		}		
		container.removeContainerFilter(filter);
		
		//2. Additional Validasi input di handle oleh form Validator
		//3. Aksi Save
		if (kodeValid && inputValid) {
			try {
				//3.1 commit form
				form.commit();
				//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel
				Object itemId = container.addEntity(accountDept);
				table.setValue(itemId);
				//3.3. atur kondisi status form
				operationStatus = EnumFormOperationStatus.OPEN.getStrCode();
				setFormButtonAndText();
				//3.4. Refresh lagi container
				refreshContainer();
				Notification.show("Penambahan Sukses!!!");
				try{
					table.focus();				
				} catch(Exception ex){}
			} catch(Exception ex) {
				Notification.show("Terjadi Kesalahan Insert data!!!");
				form.focus();
				return 1;
			}
		} else {
			return 2;
		}
		
		return 0;
	}

	
	public int discardForm(String argOperationStatus){
		 ConfirmDialog discardDialog = ConfirmDialog.show(getUI(),"Konfirmasi Discard", "Yakin keluar mode editing(discard)?", "Discard..", "No",
	                new ConfirmDialog.Listener() {
	                    public void onClose(ConfirmDialog dialog) {
	                        if (dialog.isConfirmed()) {
	                            // Confirmed to continue
	                    		try {			
	                    			//1. form discard
	                    			form.discard();
	                    			operationStatus = EnumFormOperationStatus.OPEN.getStrCode();
	                    			setFormButtonAndText();
	                    			//2. refresh container
	                    			
	                    			refreshContainer();
	                    			table.focus();
	                    			

	                    		} catch(Exception ex) {
	                    			Notification.show("Terjadi Kesalahan Discard");
	                    		}
	                        	
	                        } else {
	                        	form.getField("id").focus();
	                            // User did not confirm
	                        }
	                    }
	                });	        
		 discardDialog.setStyleName("dialog");
		 discardDialog.getOkButton().setStyleName("small");
		 discardDialog.getCancelButton().setStyleName("small");
		 //Jangan lupa
		 discardDialog.focus();
		
		return 0;
	}

	public int deleteForm(){
		deleteItem(table.getValue());
		refreshContainer();
		return 0;
	}
	public int searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
		container.removeAllContainerFilters();
		
		//2. Baru Kasih Filter Lagi
		String theId = fieldSearchById.getValue().toString().trim();
		String theName = fieldSearchByDesc.getValue().toString().trim();
		Filter filter1 = new SimpleStringFilter("id", theId, true, false);
		Filter filter2 = new Or(new SimpleStringFilter("name", theName, true, false));
		container.addContainerFilter(filter1);
		container.addContainerFilter(filter2);
		//3. Refresh container dengan kondisi filter
		refreshContainer();
		//Focus
		table.focus();
		
		return 0;
	}
	public int reloadForm(){
		//Remove Filter dan Refresh container
		container.removeAllContainerFilters();
		//Refresh container tanpa filter
		refreshContainer();
		
		return 0;
	}
	public int printForm(){
		Notification.show("Print belum diimplementasikan!!!");
		return 0;
	}
	
	public int helpForm(){
		//Menggunakan HelpOverlay	
		HelpManager helpManager = new HelpManager(getUI());
		helpManager.closeAll();
		HelpOverlay w = helpManager.addOverlay(EnumHelpOverlayTipe.SHORTCUT.getIntCode(), 
				"Account Dept :::Help Shortcut Key:::", null, null);
        w.center();
        w.setDraggable(true);
        getUI().addWindow(w);
        
        return 0;
	}
	@Override
	public void attach() {
		super.attach();
//		panel.focus();
		
	}
    
	public void refreshContainer() {
		try{
			container.removeAllContainerFilters();
			container.refresh();
			if (table.getValue() != null) {
				// reset form as e.g. referenced containers may have changed
				form.setItemDataSource(table.getItem(table.getValue()));
			}
			//Hitung Total
			//Jumlah Record		
			Collection items =  container.getItemIds();
			table.setColumnFooter("id", "*Jumlah Record: " + items.size());
			
		} catch(Exception ex){
		
		}
		
	}
	

}


