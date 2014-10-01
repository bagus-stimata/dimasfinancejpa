package org.dimas.finance.systemsetting;

import java.util.Collection;
import java.util.Date;

import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Modul;
import org.dimas.finance.model.User;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.model.modelenum.EnumHelpOverlayTipe;
import org.dimas.finance.util.HelpManager;
import org.dimas.finance.util.HelpOverlay;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Item;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ModulPresenter implements ClickListener, ValueChangeListener{
	private ModulModel model;
	private ModulView view;
	
	public ModulPresenter(ModulModel model, ModulView view){
		this.model = model;
		this.view = view;
		
		initListener();
	}
	
	public void initListener(){
		view.getBtnAddForm().addClickListener(this);
		view.getBtnCancelForm().addClickListener(this);
		view.getBtnDeleteForm().addClickListener(this);
		view.getBtnHelp().addClickListener(this);
		view.getBtnPrint().addClickListener(this);
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnSearch().addClickListener(this);

		view.getTable().addValueChangeListener(this);
		
	}
	
	public void initDisplay(){
		//SUDAH DIHANDLE OLEH VIEW
	}


	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		
		Object itemId = event.getProperty().getValue();
		Item item = view.getTable().getItem(itemId);
		boolean entitySelected = item != null;
		// modify visibility of form and delete button if an item is selected
		view.getFormLayout().setVisible(entitySelected);
		view.getBtnDeleteForm().setEnabled(entitySelected);
		
		if (entitySelected) {
			
			model.modul = new Modul();
			model.modul = model.getBeanItemContainerModul().getItem(itemId).getBean();
			model.getBinderModul().setItemDataSource(model.modul);
			
		}
		
		view.bindAndBuildFieldGroupComponent();		
		model.setOperationStatus(EnumFormOperationStatus.EDITING.getStrCode());		
		view.setFormButtonAndTextState();
		
		
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		//Antisipasi
		if (model.getOperationStatus()==null) model.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
		if (model.getOperationStatus().equals("")) model.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
		
		if (event.getButton() == view.getBtnAddForm()) {
			
			if (model.getOperationStatus().equals(EnumFormOperationStatus.OPEN.getStrCode())){
				
				addItem();				
			}else if (model.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
				discardForm();
			}
		} else if (event.getButton() == view.getBtnDeleteForm()) {
			deleteForm();
		} else if (event.getButton() == view.getBtnSaveForm()){
			 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Konfirmasi Simpan", "Simpan Data?", "Save", "No",
		                new ConfirmDialog.Listener() {
		                    public void onClose(ConfirmDialog dialog) {
		                        if (dialog.isConfirmed()) {
		                            // Confirmed to continue
		                    		try {			
		                    			if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
		                    				if (insertAndValidateForm()==0){		                    					
		                    					view.getTable().focus();
		                    				}else {
		                    					view.focustIdOrDesc();
		                    				}
		                    			} else if (model.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
		                    				if (updateAndValidateForm()==0){				                    			
		                    					//refreshContainer(); //Sudah dihandle oleh method AndValidateForm()
		                    					view.getTable().focus();		                    					
		                    				}else {
		                    					view.focustIdOrDesc();
		                    				}
		                    			}
		                    		} catch(Exception ex) {
		                    			Notification.show("Terjadi Kesalahan Simpan");
		                    		}
		                        	
		                        } else {
		                        	view.focustIdOrDesc();
		                        }
		                    }
		                });	        
			 commitDialog.setStyleName("dialog");
			 commitDialog.getOkButton().setStyleName("small");
			 commitDialog.getCancelButton().setStyleName("small");
			 //Jangan lupa
			 commitDialog.focus();
			
		} else if(event.getButton() == view.getBtnCancelForm()) {
			if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
				discardForm();
			} else if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode()) || 
					model.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
				if (view.getFormLayout().isVisible()){
					discardForm();
				}				
			}else{	
				view.getTable().focus();
			}		
			
//			discardForm(operationStatus);
		} else if (event.getButton() == view.getBtnSearch()) {
			searchForm();
		} else if (event.getButton() == view.getBtnPrint()){			
			printForm();
		}else if (event.getButton() == view.getBtnHelp()){
			helpForm();
		}
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
		
	}
	
	public void addItem(){
		try {
			
			//1. Deklarasikan object awal
			model.newModul = new Modul();
			//Karena tidak pisa pake null representation
			
			model.newModul.setId("New");
			model.newModul.setModulGroup("");
			
			model.newModul.setTitle("");
			model.newModul.setNotes("");
				//2. SET NEW DATA ITEM TO BINDER
			model.getBinderModul().setItemDataSource(model.newModul);
			
			//3. REFRESH VIEW AND SHOW FORM LAYOUT
			view.bindAndBuildFieldGroupComponent();			
			view.getFormLayout().setVisible(true);
			
			//3.SET FORM STATE AND BUTTON STATE
			model.setOperationStatus(EnumFormOperationStatus.ADDING.getStrCode());
			view.setFormButtonAndTextState();
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	public int discardForm(){
		 ConfirmDialog discardDialog = ConfirmDialog.show(view.getUI(),"Konfirmasi Discard", 
				 "Yakin keluar mode editing(discard)?", "Discard..", "No",
	                new ConfirmDialog.Listener() {
	                    public void onClose(ConfirmDialog dialog) {
	                        if (dialog.isConfirmed()) {
	                            // Confirmed to continue
	                    		try {			
	                    			//1. form discard
	                    			model.getBinderModul().discard();
	                    			model.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
	                    			view.setFormButtonAndTextState();
	                    			//2. focust ke table
	                    			view.getTable().focus();
	                    			
	                    		} catch(Exception ex) {
	                    			Notification.show("Terjadi Kesalahan Discard");
	                    		}
	                        	
	                        } else {
	                        	view.focustIdOrDesc();
	                         
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
	public void deleteForm(){
		final Object itemId = view.getTable().getValue();
		
		if (itemId != null){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		model.getBeanItemContainerModul().removeItem(itemId);
                    		view.getTable().refreshRowCache();
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

	public int insertAndValidateForm(){
		boolean kodeValid = true;
		boolean inputValid = true;
		String theId = (String) view.getFieldId().getConvertedValue();
		//1. Validasi Kode
		if (theId.equalsIgnoreCase("New")) {
			kodeValid = false;
			Notification.show("Kode Tidak Boleh Memakai New");
			view.focustIdOrDesc();
			return 1;
		}
		Equal filter = new Compare.Equal("refno", theId);
		model.getBeanItemContainerModulSearch().addContainerFilter(filter);
		Collection itemIds = model.getBeanItemContainerModulSearch().getItemIds();
		if (itemIds.size()!= 0){
			kodeValid = false;
			Notification.show("Kode Sudah Terpakai!!.");	
			view.focustIdOrDesc();
			return 1;
		}		
		model.getBeanItemContainerModulSearch().removeContainerFilter(filter);
		
		//VALIDASI KODE TIDAK DIPERLUKAN LAGI 
		
		//2. Additional Validasi input di handle oleh form Validator
		//3. Aksi Save
		if (kodeValid && inputValid) {
			try {
				
				//3.1 commit form >> MASUKKAN KE DATABASE
				model.getBinderModul().commit(); //COMMIT TETAP PAKE NEW TAPI CLIENT SIDE				
//				model.getNewUser().setUserId(model.getTransaksiHelper().getNewNomorUrutBukuGiro());
				model.getModulService().createObject(model.getNewModul());
				
				//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel
				model.getBeanItemContainerModul().addItem(model.getNewModul());
				view.getTable().refreshRowCache();
				
				//3.3. atur kondisi status form
				model.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
				view.setFormButtonAndTextState();
				//3.4. Refresh lagi container >> SUDAH TIDAK DIPERLUKAN LAGI OLEH KARENA OPERSI //3.2
				
				Notification.show("Penambahan Sukses!!!");
				try{
					view.getTable().focus();				
				} catch(Exception ex){}
			} catch(Exception ex) {
				Notification.show("Terjadi Kesalahan Insert data!!!");
				view.focustIdOrDesc();
				return 1;
			}
		} else {
			return 2;
		}
		
		return 0;
	}
	
	public int updateAndValidateForm(){
		try{
			//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel >> dan database
			model.getBinderModul().commit();
			model.getModulService().updateObject(model.getModul());
			
			view.getTable().refreshRowCache();
			
			//3.3. atur kondisi status form
			model.setOperationStatus(EnumFormOperationStatus.EDITING.getStrCode());
			view.setFormButtonAndTextState();
			//3.4. Refresh lagi container >> SUDAH TIDAK DIPERLUKAN LAGI OLEH KARENA OPERSI //3.2
			
			Notification.show("Penambahan Sukses!!!");
			try{
				view.getTable().focus();				
			} catch(Exception ex){}
			
		} catch(Exception ex){
			view.focustIdOrDesc();
			return 1;
		}
		return 0;
	}
	
	public int searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.getBeanItemContainerModul().removeAllContainerFilters();
		
		//2. Baru Kasih Filter Lagi
		String theId = view.getFieldSearchById().getValue().toString().trim();
		String theGroup = view.getFieldSearchByGroup().getValue().toString().trim();
		String theTitle = view.getFieldSearchByTitle().getValue().toString().trim();
		Filter filter1 = new SimpleStringFilter("id", theId, true, false);
		Filter filter2 = new Or(new SimpleStringFilter("modulGroup", theGroup, true, false));
		Filter filter3 = new Or(new SimpleStringFilter("title", theTitle, true, false));
		model.getBeanItemContainerModul().addContainerFilter(filter1);
		model.getBeanItemContainerModul().addContainerFilter(filter2);
		model.getBeanItemContainerModul().addContainerFilter(filter3);
		
		//3. Refresh TABLE
		view.getTable().refreshRowCache();
		//4. Focus KE TABLE
		view.getTable().focus();
		
		return 0;
		
	}
	
	public void printForm(){
		
	}
	
	public void helpForm(){
		//Menggunakan HelpOverlay	
		HelpManager helpManager = new HelpManager(view.getUI());
		helpManager.closeAll();
		HelpOverlay w = helpManager.addOverlay(EnumHelpOverlayTipe.SHORTCUT.getIntCode(), 
				"Account Dept :::Help Shortcut Key:::", null, null);
        w.center();
        w.setDraggable(true);
        view.getUI().addWindow(w);
		
	}
	
	

}
