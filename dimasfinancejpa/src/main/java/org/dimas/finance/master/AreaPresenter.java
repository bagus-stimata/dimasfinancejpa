package org.dimas.finance.master;

import java.util.Arrays;
import java.util.Collection;

import org.dimas.finance.model.Area;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.model.modelenum.EnumHelpOverlayTipe;
import org.dimas.finance.util.HelpManager;
import org.dimas.finance.util.HelpOverlay;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Item;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

public class AreaPresenter implements ClickListener, ValueChangeListener, Handler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AreaModel model;
	private AreaView view;
	
	Area item = new Area();
	
	public AreaPresenter(AreaModel model, AreaView view){
		this.model = model;
		this.view = view;
		initListener();		
		initDisplay();
		
	}
	
	public void initListener(){
		view.getAddButton().addClickListener(this);
		view.getBtnHelp().addClickListener(this);
		view.getBtnPrint().addClickListener(this);
		view.getBtnReload().addClickListener(this);
		view.getBtnSearch().addClickListener(this);
		view.getCommit().addClickListener(this);
		view.getDiscard().addClickListener(this);
		view.getDeleteButton().addClickListener(this);
		
		view.getTable().addValueChangeListener(this);
		
		// register action handler (enter and ctrl-n)
		view.getPanelUtama().addActionHandler(this);
		view.getPanelTop().addActionHandler(this);
		view.getPanelTabel().addActionHandler(this);
		view.getPanelForm().addActionHandler(this);
		
	}
	
	public void initDisplay(){
		//1. Table
		model.setFreshDataTable();
		view.setDisplay();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		//Antisipasi
		if (view.getOperationStatus()==null) view.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
		if (view.getOperationStatus().equals("")) view.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
		
		if (event.getButton() == view.getAddButton()) {
			if (view.getOperationStatus().equals(EnumFormOperationStatus.OPEN.getStrCode())){
				addItem();
				if (view.getForm().isVisible()) view.getForm().getField("id").focus();
				
			}else if (view.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
				discardForm(view.getOperationStatus());
			}
		} else if (event.getButton() == view.getDeleteButton()) {
			deleteForm();
		} else if (event.getButton() == view.getCommit()){
			 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Konfirmasi Simpan", "Simpan Data?", "Save", "No",
		                new ConfirmDialog.Listener() {
		                    public void onClose(ConfirmDialog dialog) {
		                        if (dialog.isConfirmed()) {
		                            // Confirmed to continue
		                    		try {			
		                    			if (view.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
		                    				if (insertAndValidateForm()==0){		                    					
		                    					//refreshContainer(); //sudah dihanle oleh method  insertAndValidateForm()
		                    					view.getTable().focus();
		                    				}else {
		                    					view.getForm().focus();
		                    				}
		                    			} else if (view.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
		                    				if (updateAndValidateForm()==0){				                    			
		                    					//refreshContainer(); //Sudah dihandle oleh method AndValidateForm()
		                    					view.getTable().focus();		                    					
		                    				}else {
		                    					view.getForm().focus();
		                    				}
		                    			}
		                    		} catch(Exception ex) {
		                    			Notification.show("Terjadi Kesalahan Simpan");
		                    		}
		                        	
		                        } else {
		                        	view.getForm().focus();
		                            // User did not confirm
		                        }
		                    }
		                });	        
			 commitDialog.setStyleName("dialog");
			 commitDialog.getOkButton().setStyleName("small");
			 commitDialog.getCancelButton().setStyleName("small");
			 //Jangan lupa
			 commitDialog.focus();
			
		} else if(event.getButton() == view.getDiscard()) {
			if (view.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
				discardForm(view.getOperationStatus());
			} else if (view.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode()) || 
					view.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
				if (view.getForm().isVisible()){
					discardForm(view.getOperationStatus());
				}				
			}else{	
				view.getTable().focus();
			}		
			
//			discardForm(operationStatus);
		} else if (event.getButton() == view.getBtnSearch()) {
			searchForm();
		} else if (event.getButton() == view.getBtnReload()){
			reloadForm();			
			if (view.getOperationStatus().equals(EnumFormOperationStatus.OPEN.getStrCode())){
				view.getTable().focus();
			}
		} else if (event.getButton() == view.getBtnPrint()){			
			printForm();
		}else if (event.getButton() == view.getBtnHelp()){
			helpForm();
		}
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
	}

	public void deleteItem(final Object itemId) {
		if (itemId != null){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		model.getTableJpaContainer().removeItem(itemId);
                    		model.setFreshDataTable();
                    		view.setDisplay();
                			Notification.show("Delete Sukses");		                        		
                    	} catch(Exception ex) {
                			Notification.show("Error Delete!!");		                        		
                    	}
                    } else {
                    // User did not confirm
                    }
					view.getTable().focus();
				}
			};
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Hapus", "Yakin akan hapus data?", 
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
		if (view.getOperationStatus().equals(EnumFormOperationStatus.OPEN.getStrCode())){
			view.getForm().setVisible(false);
			view.getTable().setSelectable(true);
			view.getAddButton().setEnabled(true);
			view.getDeleteButton().setEnabled(true);			
			view.getForm().getField("id").setReadOnly(true);			
		} else if (view.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
			view.getForm().setVisible(true);
			view.getTable().setSelectable(false);
			view.getAddButton().setEnabled(false);
			view.getDeleteButton().setEnabled(false);			
			view.getForm().getField("id").setReadOnly(false);
		}else if (view.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
			view.getForm().setVisible(true);
			view.getTable().setSelectable(true);
			view.getAddButton().setEnabled(true);
			view.getDeleteButton().setEnabled(true);	
			view.getForm().getField("id").setReadOnly(true);
			
		}		
	}
	
	protected void addItem() {
		try {
			
			//1. Deklarasikan object awal
			item = new Area();  
			//Karena tidak pisa pake null representation
			item.setId("New");
			item.setArea("");
			
			
			//2. Tampilkan form
			model.formBeanItem = new BeanItem<Area>(item);
			view.getForm().setItemDataSource(model.getFormBeanItem());
			view.getForm().setVisible(true);
			
			//3. Atur kondisi status form

			//Nicer apperance
			view.setFormProperties();
			
			view.setOperationStatus(EnumFormOperationStatus.ADDING.getStrCode());
			setFormButtonAndText();
			
			//REFRESH CONTAINER BIKIN ERROR
			//4. Refresh container jika ada perubahan dari user lain
			//Focus ke ID
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public int updateAndValidateForm(){
		try{
			//1. Commit form
			view.getForm().commit();
			//2. refresh container
    		model.setFreshDataTable();
    		view.setDisplay();
			//3.3. atur kondisi status form
			view.setOperationStatus(EnumFormOperationStatus.EDITING.getStrCode());
			setFormButtonAndText();
    		
			Notification.show("Update data Sukses!!!");
			try{
				view.getTable().focus();				
			} catch(Exception ex){}
			
		} catch(Exception ex){
			view.getForm().focus();
			return 1;
		}
		return 0;
	}
	public int insertAndValidateForm(){
		boolean kodeValid = true;
		boolean inputValid = true;
		String theId = view.getForm().getField("id").getValue().toString();
		//1. Validasi Kode
		if (theId.equalsIgnoreCase("New")) {
			kodeValid = false;
			Notification.show("Kode Tidak Boleh Memakai New");
			view.getForm().focus();
			return 1;
		}
		Equal filter = new Compare.Equal("id", theId);
		model.getTableJpaContainer().addContainerFilter(filter);
		Collection itemIds = model.getTableJpaContainer().getItemIds();
		if (itemIds.size()!= 0){
			kodeValid = false;
			Notification.show("Kode Sudah Terpakai!!.");	
			view.getForm().focus();
			return 1;
		}		
		model.getTableJpaContainer().removeContainerFilter(filter);
		
		//2. Additional Validasi input di handle oleh form Validator
		//3. Aksi Save
		if (kodeValid && inputValid) {
			try {
				//3.1 commit form
				view.getForm().commit();
				//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel
				Object itemId = model.getTableJpaContainer().addEntity(item);
				view.getTable().setValue(itemId);
				//3.3. atur kondisi status form
				view.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
				setFormButtonAndText();
				//3.4. Refresh lagi container
        		model.setFreshDataTable();
        		view.setDisplay();
				Notification.show("Penambahan Sukses!!!");
				try{
					view.getTable().focus();				
				} catch(Exception ex){}
			} catch(Exception ex) {
				Notification.show("Terjadi Kesalahan Insert data!!!");
				view.getForm().focus();
				return 1;
			}
		} else {
			return 2;
		}
		
		return 0;
	}

	
	public int discardForm(String argOperationStatus){
		 ConfirmDialog discardDialog = ConfirmDialog.show(view.getUI(),"Konfirmasi Discard", "Yakin keluar mode editing(discard)?", "Discard..", "No",
	                new ConfirmDialog.Listener() {
	                    public void onClose(ConfirmDialog dialog) {
	                        if (dialog.isConfirmed()) {
	                            // Confirmed to continue
	                    		try {			
	                    			//1. form discard
	                    			view.getForm().discard();
	                    			view.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
	                    			setFormButtonAndText();
	                    			//2. refresh container
	                        		model.setFreshDataTable();
	                        		view.setDisplay();
	                    			view.getTable().focus();
	                    			
	                    		} catch(Exception ex) {
	                    			Notification.show("Terjadi Kesalahan Discard");
	                    		}
	                        	
	                        } else {
	                        	view.getForm().getField("id").focus();
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
		deleteItem(view.getTable().getValue());
		model.setFreshDataTable();
		view.setDisplay();
		return 0;
	}
	public int searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.getTableJpaContainer().refresh();
		model.getTableJpaContainer().removeAllContainerFilters();
		
		//2. Baru Kasih Filter Lagi
		String theId = view.getFieldSearchById().getValue().toString().trim();
		String theName = view.getFieldSearchByDesc().getValue().toString().trim();
		Filter filter1 = new SimpleStringFilter("id", theId, true, false);
		Filter filter2 = new Or(new SimpleStringFilter("area", theName, true, false));
		model.getTableJpaContainer().addContainerFilter(filter1);
		model.getTableJpaContainer().addContainerFilter(filter2);
		//3. Refresh container dengan kondisi filter
//		model.setFreshDataTable();
		view.setDisplay();
		
		//Focus
		view.getTable().focus();
		
		
		return 0;
	}
	public int reloadForm(){		
		model.setFreshDataTable();
		view.setDisplay();
		
		return 0;
	}
	public int printForm(){
		Notification.show("Print belum diimplementasikan!!!");
		return 0;
	}
	
	public int helpForm(){
		//Menggunakan HelpOverlay	
		HelpManager helpManager = new HelpManager(view.getUI());
		helpManager.closeAll();
		HelpOverlay w = helpManager.addOverlay(EnumHelpOverlayTipe.SHORTCUT.getIntCode(), 
				"Account Dept :::Help Shortcut Key:::", null, null);
        w.center();
        w.setDraggable(true);
        view.getUI().addWindow(w);
        
        return 0;
	}
	
//	@Override
//	public void attach() {
//		super.attach();
////		panel.focus();
//		
//	}
    

	@Override
	public void valueChange(ValueChangeEvent event) {
		
		Object itemId = event.getProperty().getValue();
		Item item = view.getTable().getItem(itemId);
		boolean entitySelected = item != null;
		// modify visibility of form and delete button if an item is selected
		view.getForm().setVisible(entitySelected);
		view.getDeleteButton().setEnabled(entitySelected);
		if (entitySelected) {
			// set entity item to form and focus it
			view.getForm().setItemDataSource(item,
					view.getFormPropertyIds() != null ? Arrays.asList(view.getFormPropertyIds())
							: item.getItemPropertyIds());
		}
		//Nicer apperance
		view.setFormProperties();
		
		view.setOperationStatus(EnumFormOperationStatus.EDITING.getStrCode());
		setFormButtonAndText();
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
		if (sender == view.getPanelTop()) {
			return ACTIONS_SEARCH;
		} else if (sender== view.getPanelTabel()){
			return ACTIONS_TABLE;
		} else if (sender== view.getPanelForm()){
			return ACTIONS_FORM;
		}
		return ACTIONS;
	}
	@Override
	public void handleAction(Action action, Object sender, Object target) {		
		if (action==INSERT){
			view.getAddButton().click();
		}else if (action==DELETE){
			deleteForm();
		}else if (action==ENTER_SEARCH){
			view.getBtnSearch().click();
			view.getTable().focus();
		}else if (action==ENTER_TABLE){
			view.getForm().focus();
		}else if (action==ENTER_FORM){
			//MENGGANTI TAB: HARUSNYA
//			commit.click();
		}else if (action==ESCAPE_SEARCH){
			view.getTable().focus();
		}else if (action==ESCAPE_TABLE){
		}else if (action==ESCAPE_FORM){
			view.getDiscard().click();
		}else if (action==ALTS){
			view.getCommit().click();
		}else if (action==ALTC){
			if (view.getForm().isVisible()){
				discardForm(view.getOperationStatus());
			}
		}else if (action==ENTER){
			
		}else if (action==EDITMODE){
			view.getForm().focus();			
		}else if (action==FINDMODE){
			view.getFieldSearchById().focus();
		}else if (action==FIND){			
//			searchForm();
			view.getBtnSearch().click();
		}else if (action==REFRESH){
			view.getBtnReload().click();
		}else if (action==HELP){
//			helpForm();
			view.getBtnHelp().click();
		}else if (action==ADD){		
			
		}else if (action==DEL){		
//			deleteForm();
			view.getDeleteButton().click();
		}
	}
	
	
}
