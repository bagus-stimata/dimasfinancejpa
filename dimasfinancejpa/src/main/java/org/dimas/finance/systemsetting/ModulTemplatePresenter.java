package org.dimas.finance.systemsetting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.dimas.finance.model.Modul;
import org.dimas.finance.model.ModulTempDetail;
import org.dimas.finance.model.ModulTempDetailPK;
import org.dimas.finance.model.ModulTempHeader;
import org.dimas.finance.model.User;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.vaadin.dialogs.ConfirmDialog;

import com.google.gwt.user.client.ui.Widget;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table.HeaderClickEvent;
import com.vaadin.ui.Table.HeaderClickListener;

public class ModulTemplatePresenter implements ClickListener, ValueChangeListener{
	private ModulTemplateModel model;
	private ModulTemplateView view;
	
	public ModulTemplatePresenter(ModulTemplateModel model, ModulTemplateView view){
		this.model = model;
		this.view =	view;
		
		initVariable();
		initVariableData();		
		initListener();		
		//HARUS SETELAH DIA DI BUILD
//		initListenerWindowModulTempHeader();
//		initListenerWindowModul();
		
		
	}
	
	public void initVariable(){
		//DISERAHKAN DI MODEL
	}
	public void initVariableData(){
		//DISERAHKAN DI MODEL
	}
	public void initListener(){
		//HEADER
		view.getBtnAddForm().addClickListener(this);
		view.getBtnDeleteForm().addClickListener(this);
		view.getBtnSearchForm().addClickListener(this);
		//DETIL
		view.getBtnAdd().addClickListener(this);
		view.getBtnRem().addClickListener(this);
		//HEADER PATH
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnCancelForm().addClickListener(this);
		
		HeaderClickListener listenerHeaderTableDetail = new HeaderClickListener() {			
			@Override
			public void headerClick(HeaderClickEvent event) {
				// TODO Auto-generated method stub
				
				try{
					if (event.getPropertyId().equals("selected")){
						if (model.isSelectAllInvoice()==true) {
							
							view.getTableDetail().setColumnHeader("selected", "<input type='checkbox'  checked />");		
							model.setSelectAllInvoice(false);
							
							Collection itemIds = model.getBeanItemContainerModulTempDetail().getItemIds();
							for (Object itemId: itemIds){
								model.getBeanItemContainerModulTempDetail().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getBeanItemContainerModulTempDetail().getItem(itemId).getBean().getSelected().setValue(true);
							}
							
						} else {
							
							view.getTableDetail().setColumnHeader("selected", "<input type='checkbox' />");		
							model.setSelectAllInvoice(true);
							
							Collection itemIds = model.getBeanItemContainerModulTempDetail().getItemIds();
							for (Object itemId: itemIds){
								model.getBeanItemContainerModulTempDetail().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getBeanItemContainerModulTempDetail().getItem(itemId).getBean().getSelected().setValue(false);
							}
							
						}	
						//KASIH SELEKSI >> buat SELECTED READONLY(TRUE) LAGI				
						view.setDisplayTableFooter();

					}
				} catch(Exception ex){}
			}
				
		};
		view.getTableDetail().addHeaderClickListener(listenerHeaderTableDetail);
		
		
	}
	public void initListenerWindowModulTempHeader(){
		ClickListener listenerSelectAndClose = new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				modulTempHeaderSelectViewSelectAndClose();
			}
		};
		view.getModulTempHeaderSelectView().getBtnSelect().addClickListener(listenerSelectAndClose);		
	}
	public void initListenerWindowModul(){
		ClickListener listenerSelectAndClose = new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				modulSelectViewSelectAndClose();
			}
		};
		view.getModulSelectView().getBtnSelect().addClickListener(listenerSelectAndClose);
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getButton()==view.getBtnAddForm()){
			addHeader();
		} else if (event.getButton()==view.getBtnDeleteForm()){
			deleteHeader();
		} else if (event.getButton()==view.getBtnSearchForm()){
			searchHeader();
		} else if (event.getButton()==view.getBtnAdd()){
			addDetailModul();
		} else if (event.getButton()==view.getBtnRem()){
			remDetailModul();
		} else if (event.getButton()==view.getBtnSaveForm()){
			saveForm();
		} else if (event.getButton()==view.getBtnCancelForm()){
			cancelForm();
		} 
		
	}
	
	public void addHeader(){
		try {
			
			//1. Deklarasikan object awal
			model.newModulTempHeader = new ModulTempHeader();
			//Karena tidak pisa pake null representation
			
//			model.newModulTempHeader.setId(0);
			model.newModulTempHeader.setTemplateName("");			
			model.newModulTempHeader.setNotes("");
			
			//2. SET NEW DATA ITEM TO BINDER
//			model.getModulTempHeaderService().createObject(model.getNewModulTempHeader());
			model.getBinderModulTempHeader().setItemDataSource(model.newModulTempHeader);
			
			//3. REFRESH VIEW AND SHOW FORM LAYOUT
			view.bindAndBuildFieldGroupComponent();			
			view.getFormLayout().setVisible(true);

			
			//KOSONGKAN DETAIL
			//MENDING TIDAK USAH DIKOSONGIN SUPAYA BISA SEMACAM COPY OTORISASI
			
			//3.SET FORM STATE AND BUTTON STATE
			model.setOperationStatus(EnumFormOperationStatus.ADDING.getStrCode());
			view.setFormButtonAndTextState();
			
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	public void deleteHeader(){
		
		if (model.getModulTempHeader() != null & model.getModulTempHeader().getId()>0){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		//1. HAPUS DARI DATABASE
                    		model.getModulTempDetailService().removeAllByHeaderId(model.getModulTempHeader().getId());
                    		model.getModulTempHeaderService().removeObject(model.getModulTempHeader());
                    		//2. KOSONGKAN BINDER
                    		model.modulTempHeader = new ModulTempHeader();
                    		model.modulTempHeader.setNotes("");
                    		model.modulTempHeader.setTemplateName("");
                    		model.getBinderModulTempHeader().setItemDataSource(model.getModulTempHeader());
                    		//3. HAPUS TABLE
                    		model.getBeanItemContainerModulTempDetail().removeAllItems();
                    		view.getTableDetail().refreshRowCache();
                    		
                			Notification.show("Delete Sukses");		                        		
                    	} catch(Exception ex) {
                			Notification.show("Error Delete!!");		                        		
                    	}
                    } else {
                    // User did not confirm
                    }
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
	public void searchHeader(){
		view.buildWindowModulTempHeaderSelect();
		initListenerWindowModulTempHeader();
		view.setFormButtonAndTextState();		
	}
	public void addDetailModul(){
		view.buildWindowModulSelect();
		initListenerWindowModul();
		view.setFormButtonAndTextState();		
	}
	
	public void remDetailModul(){
		List<ModulTempDetail> listNotSelected = new ArrayList<ModulTempDetail>();
		
		Collection items = model.getBeanItemContainerModulTempDetail().getItemIds();
		for (Object itemId: items){
			ModulTempDetail modulTempDetail = new ModulTempDetail();
			modulTempDetail = model.getBeanItemContainerModulTempDetail().getItem(itemId).getBean();
			if (modulTempDetail.getSelected().getValue()==false){				
				listNotSelected.add(modulTempDetail);
			}
		}
		//HAPUS DULU BARU TAMBAH LAGI >> NDAK EFEKTIF
		model.getBeanItemContainerModulTempDetail().removeAllItems();
		model.getBeanItemContainerModulTempDetail().addAll(listNotSelected);
		
		view.setDisplayTableFooter();
		
	}
	
	public void saveForm(){
		if (model.getModulTempHeader() != null ){
			if (model.getModulTempHeader().getId()>0 || model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
				 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Konfirmasi Simpan", "Simpan Data?", "Save", "No",
			                new ConfirmDialog.Listener() {
			                    public void onClose(ConfirmDialog dialog) {
			                        if (dialog.isConfirmed()) {
			                            // Confirmed to continue
			                    		try {	
			                    			
			                    			model.getBinderModulTempHeader().commit();
			                    			long headerId = 0 ;
			                    			if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
			                    				//NO COMPONENT FOCUS NEEDED
			                    				model.getModulTempHeaderService().createObject(model.getNewModulTempHeader());
			                    				headerId = model.getNewModulTempHeader().getId();
			                    			} else {
			                    				//NO COMPONENT FOCUS NEEDED
				                    			model.getModulTempHeaderService().updateObject(model.getModulTempHeader());
			                    				headerId = model.getModulTempHeader().getId();
			                    			}
			                    			//HAPUS DETAIL YANG LAMA PADA DATABASE
			                    			model.getModulTempDetailService().removeAllByHeaderId(model.getModulTempHeader().getId());
			                    			
			                    			//SIMPAN DETAIL BARU
			                    			Collection itemIds = model.getBeanItemContainerModulTempDetail().getItemIds();
			                    			for (Object itemId: itemIds){
			                    				ModulTempDetail modulTempDetail = new ModulTempDetail();	                    				
			                    				modulTempDetail = model.getBeanItemContainerModulTempDetail().getItem(itemId).getBean();
			                    				
			                    				//HARUS DIMASUKIN ID YANG BARU >> TERUTAMA BERGUNA UNTUK ADD NEW	
			                    				ModulTempDetailPK id = new ModulTempDetailPK();
			                    				id.setId(headerId);
			                    				id.setModul(modulTempDetail.getId().getModul());
			                    				modulTempDetail.setId(id);
			                    				
			                    				model.getModulTempDetailService().updateObject(modulTempDetail);
			                    			}
		
			                    			view.bindAndBuildFieldGroupComponent();
			                    			
				                    		model.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
			                    			view.setFormButtonAndTextState();
			                    			
			                    		} catch(Exception ex) {
			                    			Notification.show("Terjadi Kesalahan Simpan");
			                    		}
			                        	
			                        } else {
			                        	//NO COMPONENT FOCUS NEEDED
			                        }
			                    }
			                });	        
				 commitDialog.setStyleName("dialog");
				 commitDialog.getOkButton().setStyleName("small");
				 commitDialog.getCancelButton().setStyleName("small");
				 //Jangan lupa
				 commitDialog.focus();
			}	 
		
		} 		
	}
	
	public void cancelForm(){
		//SAMA DENGAN DISCARD
		 ConfirmDialog discardDialog = ConfirmDialog.show(view.getUI(),"Konfirmasi Discard", 
				 "Yakin keluar mode editing(discard)?", "Discard..", "No",
	                new ConfirmDialog.Listener() {
	                    public void onClose(ConfirmDialog dialog) {
	                        if (dialog.isConfirmed()) {
	                            // Confirmed to continue
	                    		try {			
	                    			//1. form discard
	                    			if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
	                    				model.getBinderModulTempHeader().setItemDataSource(model.getModulTempHeader());
	                    			} else {
	                    				model.getBinderModulTempHeader().discard();
	                    			}
	                    			//DETAIL DI LOAD ULANG LAH
	                    			model.getBeanItemContainerModulTempDetail().removeAllItems();
	                    			List<ModulTempDetail> listDetail = new ArrayList<ModulTempDetail>(model.getModulTempHeader().getModulTempDetails());
	                    			model.getBeanItemContainerModulTempDetail().addAll(listDetail);
	                    			view.getTableDetail().refreshRowCache();
	                    		
	                    			model.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
	                    			view.setFormButtonAndTextState();
	                    			//2. focust ke table (TIDAK ADA TABLE YANG HARUS DI FOCUS)
	                    			
	                    		} catch(Exception ex) {
	                    			Notification.show("Terjadi Kesalahan Discard");
	                    		}
	                        	
	                        } else {
	                        //TIDAK ADA YANG PERLU DIFOCUS
	                         
	                        }
	                    }
	                });	        
		 discardDialog.setStyleName("dialog");
		 discardDialog.getOkButton().setStyleName("small");
		 discardDialog.getCancelButton().setStyleName("small");
		 //JANGAN LUPA
		 discardDialog.focus();
		
	}
	
	public void modulTempHeaderSelectViewSelectAndClose(){
		view.destroyWindowModulTempHeaderSelect();
		model.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
		//SET ITEM HEADER BINDER
		if (view.getModulTempHeaderSelectModel().getModulTempHeader() != null){
			//HEADER
			model.modulTempHeader = new ModulTempHeader();
			model.modulTempHeader = view.getModulTempHeaderSelectModel().getModulTempHeader();
			model.getBinderModulTempHeader().setItemDataSource(model.modulTempHeader);
			view.bindAndBuildFieldGroupComponent();			
			//DETAIL
			//TAMPILIN DETAIL AFTER SELECT
			model.getBeanItemContainerModulTempDetail().removeAllItems();
			List<ModulTempDetail> listDetail = new ArrayList<ModulTempDetail>(model.getModulTempHeader().getModulTempDetails());
			model.getBeanItemContainerModulTempDetail().addAll(listDetail);
			view.getTableDetail().refreshRowCache();
			
		}
		view.setDisplayTableFooter();
		view.setFormButtonAndTextState();
	}
	
	public void modulSelectViewSelectAndClose(){
		view.destroyWindowModulSelect();
		
		Collection items = view.getModulSelectModel().getBeanItemContainerModul().getItemIds();
		for (Object itemId: items){
			Modul modul = new Modul();
			modul = view.getModulSelectModel().getBeanItemContainerModul().getItem(itemId).getBean();
			if (modul.getSelected().getValue()==true){
				ModulTempDetail modulTempDetail = new ModulTempDetail();
				
				ModulTempDetailPK id = new ModulTempDetailPK();
				id.setId(model.getModulTempHeader().getId());
				id.setModul(modul.getId());
				modulTempDetail.setId(id);
				
				modulTempDetail.setModulBean(modul);
				modulTempDetail.setOtorize(7);
				model.getBeanItemContainerModulTempDetail().addBean(modulTempDetail);
			}
		}
		
		view.setDisplayTableFooter();
		
	}
	
	
}
