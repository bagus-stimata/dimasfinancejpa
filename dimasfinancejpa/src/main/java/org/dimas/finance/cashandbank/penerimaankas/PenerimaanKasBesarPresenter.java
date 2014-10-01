package org.dimas.finance.cashandbank.penerimaankas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dimas.finance.model.Account;
import org.dimas.finance.model.Bkbbalance;
import org.dimas.finance.model.BkbbalancePK;
import org.dimas.finance.model.Bkbdetail;
import org.dimas.finance.model.BkbdetailPK;
import org.dimas.finance.model.Bkbheader;
import org.dimas.finance.model.BkbheaderPK;
import org.dimas.finance.model.Bkbtranstype;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Modul;
import org.dimas.finance.model.ModulTempDetail;
import org.dimas.finance.model.ModulTempDetailPK;
import org.dimas.finance.model.ModulTempHeader;
import org.dimas.finance.model.User;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.vaadin.dialogs.ConfirmDialog;

import com.google.gwt.user.client.ui.Widget;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table.HeaderClickEvent;
import com.vaadin.ui.Table.HeaderClickListener;

public class PenerimaanKasBesarPresenter implements ClickListener, ValueChangeListener{
	private PenerimaanKasBesarModel model;
	private PenerimaanKasBesarView view;
	
	public PenerimaanKasBesarPresenter(PenerimaanKasBesarModel model, PenerimaanKasBesarView view){
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
		view.getBtnEdit().addClickListener(this);
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
						if (model.isSelectAllItem()==true) {
							
							view.getTableDetail().setColumnHeader("selected", "<input type='checkbox'  checked />");		
							model.setSelectAllItem(false);
							
							Collection itemIds = model.getBeanItemContainerBkbDetail().getItemIds();
							for (Object itemId: itemIds){
								model.getBeanItemContainerBkbDetail().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getBeanItemContainerBkbDetail().getItem(itemId).getBean().getSelected().setValue(true);
							}
							
						} else {
							
							view.getTableDetail().setColumnHeader("selected", "<input type='checkbox' />");		
							model.setSelectAllItem(true);
							
							Collection itemIds = model.getBeanItemContainerBkbDetail().getItemIds();
							for (Object itemId: itemIds){
								model.getBeanItemContainerBkbDetail().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getBeanItemContainerBkbDetail().getItem(itemId).getBean().getSelected().setValue(false);
							}
							
						}	
						//KASIH SELEKSI >> buat SELECTED READONLY(TRUE) LAGI				
						view.setDisplayTableFooter();

					}
				} catch(Exception ex){}
			}				
		};
		
		view.getTableDetail().addHeaderClickListener(listenerHeaderTableDetail);		
		view.getTableDetail().addValueChangeListener(this);
		
		ValueChangeListener listenerComboDivision = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				
				fixItemOnTableDetail();
				//NICER TABLE VIEW
				view.setTableDetailProperties();
				view.setFormButtonAndTextState();
				
			}
		};
		view.getComboDivision().setImmediate(true);
		view.getComboDivision().addValueChangeListener(listenerComboDivision);
				
	}
	public void fixItemOnTableDetail(){
		//1. MASUKKAN KE DALAM HEADER NEW OR EDIT				
		Division divisionBean = new Division();
		try{
			divisionBean = model.getBeanItemContainerDivision().getItem(view.getComboDivision().getValue()).getBean();
			if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
				model.getNewBkbheader().setDivisionBean(divisionBean);
			} else {
				model.bkbheader.setDivisionBean(divisionBean);
			}					
			//Rubah tanggal transakasi otomatis
			view.getDateFieldTransDate().setValue(model.getTransaksiHelper().getCurrentTanggalTransaksiBerjalan(divisionBean));
		} catch (Exception ex){
		}
		
		//2. RUBAH SEMUA PK DI DETAIL
		List<Bkbdetail> newListDetail = new ArrayList<Bkbdetail>();
		Collection itemIds = model.getBeanItemContainerBkbDetail().getItemIds();
		for (Object itemId: itemIds){
			Bkbdetail item = new Bkbdetail();
			item = model.getBeanItemContainerBkbDetail().getItem(itemId).getBean();
			BkbdetailPK id = new BkbdetailPK();
			id = item.getId();
			id.setDivision(divisionBean.getId());
			
			item.setId(id);
			item.setDivisionBean(divisionBean);
			
			newListDetail.add(item);
			
		}
		model.getBeanItemContainerBkbDetail().removeAllItems();
		model.getBeanItemContainerBkbDetail().addAll(newListDetail);
		view.getTableDetail().refreshRowCache();
		
	}

	public void initListenerWindowHeaderSelect(){
		ClickListener listenerSelectAndClose = new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				windowHeaderSelectViewSelectAndClose();
			}
		};
		view.getHeaderSelectView().getBtnSelect().addClickListener(listenerSelectAndClose);		
	}
	public void initListenerWindowItemAdd(){
		ClickListener listenerAddToDetail = new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				windowItemAddViewAddToDetail();
			}
		};
		view.getItemAddView().getBtnAdd().addClickListener(listenerAddToDetail);

		ClickListener listenerReset = new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				windowItemAddViewReset();
			}
		};
		view.getItemAddView().getBtnReset().addClickListener(listenerReset);
		
		ClickListener listenerClose = new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				windowItemAddViewClose();
			}
		};
		view.getItemAddView().getBtnClose().addClickListener(listenerClose);
		
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		
		Object itemId = event.getProperty().getValue();
		Item item = view.getTableDetail().getItem(itemId);
		boolean entitySelected = item != null;
		// modify visibility of form and delete button if an item is selected
		
		model.bkbdetail = new Bkbdetail();
		if (entitySelected) {			
			model.bkbdetail = model.getBeanItemContainerBkbDetail().getItem(itemId).getBean();
		} 
		
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getButton()==view.getBtnAddForm()){
			addHeader();
		} else if (event.getButton()==view.getBtnDeleteForm()){
			deleteForm();
		} else if (event.getButton()==view.getBtnSearchForm()){
			searchHeader();
		} else if (event.getButton()==view.getBtnAdd()){
			addDetail();
		} else if (event.getButton()==view.getBtnEdit()){
			editDetail();
		} else if (event.getButton()==view.getBtnRem()){
			remDetail();
		} else if (event.getButton()==view.getBtnSaveForm()){
			saveForm();
		} else if (event.getButton()==view.getBtnCancelForm()){
			cancelForm();
		} 
		
	}
	
	public void addHeader(){
		try {
			
			//1. Deklarasikan object awal
			model.newBkbheader = new Bkbheader();
			//Karena tidak pisa pake null representation
			BkbheaderPK id = new BkbheaderPK();
			id.setRefno("New");
			id.setDivision("");			
			model.newBkbheader.setId(id);
			
			Division div = new Division();
			String strDiv = null;
			try{
				div = model.getBeanItemContainerDivision().getItem(view.getComboDivision().getValue()).getBean();
				strDiv = div.getId();
			} catch(Exception ex){
			}			
			model.newBkbheader.setDivisionBean(div);
			if (strDiv ==null){
				Notification.show("Pilih Divisi untuk mengaktifkan tombol Save dan Cancel");
			}
			
			model.newBkbheader.setNotes("");		
			model.newBkbheader.setClosing(false);
			model.newBkbheader.setEntrydate(new Date());
			model.newBkbheader.setTransdate(model.getTransaksiHelper().getCurrentTanggalTransaksiBerjalan(div));
			
			//TRANSAKTION DATE MENGAMBIL DARI DIVISI TANGGAL TRANSAKSI
//			model.bkbheader.setTransdate(new Date());
			
			//2. SET NEW DATA ITEM TO BINDER
			model.getBinderBkbheader().setItemDataSource(model.newBkbheader);
			
			//3. REFRESH VIEW AND SHOW FORM LAYOUT
			view.bindAndBuildFieldGroupComponent();			
			view.getFormLayout().setVisible(true);

			
			//KOSONGKAN DETAIL
			//KOSONGKAN TABLE DETAIL
			model.getBeanItemContainerBkbDetail().removeAllItems();
			view.getTableDetail().refreshRowCache();
			
			//3.SET FORM STATE AND BUTTON STATE
			model.setOperationStatus(EnumFormOperationStatus.ADDING.getStrCode());
			view.setFormButtonAndTextState();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	public void deleteForm(){
		
		if (model.getBkbheader() != null ){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		if (model.getBkbheader().isClosing()==false){
	                			//**HAPUS HEADER DAN DETAIL OLD (OTOMATIS DENGAN DETAIL)	
	                			double debetValue = 0.0;
	                			double kreditValue = 0.0;                    		
	                			List<Bkbdetail> listDetailToRem = new ArrayList<Bkbdetail>(model.getBkbheader().getBkbdetails());
	                			for (Bkbdetail itemDetail:listDetailToRem){
	                				if (itemDetail.getDebetkredit().equals("D")){
	                					debetValue -= itemDetail.getAmount();
	                				} else if (itemDetail.getDebetkredit().equals("K")){
	                					kreditValue -= itemDetail.getAmount();
	                				}
	                				model.getBkbDetailService().removeObject(itemDetail);	
	                			}
	                			model.getBkbHeaderService().removeObject(model.getBkbheader());		
	                    		
	                    		//**UPDATE Balance
	                    		updateBalance(model.getBkbheader().getDivisionBean(), debetValue, kreditValue);

	                    		//**KOSONGKAN BINDER
	                    		model.bkbheader = new Bkbheader();
	                    		model.getBinderBkbheader().setItemDataSource(model.getBkbheader());
	                    		
	                    		//**HAPUS TABLE
	                    		model.getBeanItemContainerBkbDetail().removeAllItems();
	                    		view.getTableDetail().refreshRowCache();
	                    		
	                    		
	                			Notification.show("Delete Sukses");		     
                    		} else {
	                			Notification.show("Sudah Closing.. Tidak boleh di update!!");		                         			
                    		}
                    	} catch(Exception ex) {
                			Notification.show("Error Delete!!");		
                			ex.printStackTrace();
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
		
	public void saveForm(){
		String strHeader = null;
		try{
			if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
				strHeader = model.getNewBkbheader().getId().getRefno();				
			} else {
				strHeader = model.getBkbheader().getId().getRefno();				
			}
		} catch(Exception ex){}
		
		if (strHeader != null ){
			if (model.getBeanItemContainerBkbDetail().size()>0){
				 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Konfirmasi Simpan", "Simpan Data?", "Save", "No",
			                new ConfirmDialog.Listener() {
			                    public void onClose(ConfirmDialog dialog) {
			                        if (dialog.isConfirmed()) {
			                            // Confirmed to continue
			                    		if (model.getBkbheader().isClosing()==false){
			                    			boolean isValid = true;
			                    			//CEK VALIDASI SYSTEM
			                    			//Tanggal Transaksi dan tanggal entry tidak boleh kosong
			                    			Date entrydate = view.getDateFieldEntryDate().getValue();
			                    			Date transDate = view.getDateFieldTransDate().getValue();
			                    			if (entrydate==null || transDate==null){
			                    				isValid = false;
			                    				Notification.show("Tanggal Transaksi atau tanggal Entry salah!!");
			                    			}
			                    			
			                    			//Tanggal Transaksi tidak boleh kurang dari tanggal transaksi berjalan setiap Divisinya
			                    			Division divisionBean = new Division();
			                    			try{
			                    				divisionBean = model.getBeanItemContainerDivision().getItem(view.getComboDivision().getValue()).getBean();
			                    			} catch(Exception ex){}
			                    			if (transDate.getTime()<
			                    					model.getTransaksiHelper().getCurrentTanggalTransaksiBerjalan(divisionBean).getTime()){
			                    				isValid = false;
			                    				Notification.show("Tanggal Transaksi tidak boleh lebih kecil dari tanggal Transaksi Berjalan Sistem");			                    				
			                    			}
			                    			//PROSES SAVE ADDNEW OR UPDATE
			                    			if (isValid){
				                    			if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
				                    				saveFormAddNew();
				                    			} else {
				                    				saveFormUpdate();
				                    			}
			                    			}
			                    			
			                    		} else {
				                			Notification.show("Sudah Closing.. Tidak boleh di update!!");		     
			                    			
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
	public void saveFormAddNew(){
		try{
			//**HEADER
			//1. COMMIT BINDER			
			model.getBinderBkbheader().commit();
			//2. PERBAIKI KODE (Untuk header dan detail)
			BkbheaderPK headerId = new BkbheaderPK();
			headerId = model.getNewBkbheader().getId();
			String newRefno = model.getTransaksiHelper().getNewNomorUrutBukuKasBesar(model.getNewBkbheader().getDivisionBean());
			headerId.setRefno(newRefno);
			model.getNewBkbheader().setId(headerId);
			//3. Simpan Header ke DATABASE
			model.getBkbHeaderService().createObject(model.getNewBkbheader());
			
			fixItemOnTableDetail();			
			//2. Container masih berisi nilai(Tidak ikut dihapus) >> Simpan lagi ke DATABASE
			double debetValue = 0.0;
			double kreditValue = 0.0;
			Collection itemIds = model.getBeanItemContainerBkbDetail().getItemIds();
			List<Bkbdetail> newList = new ArrayList<Bkbdetail>();
			for (Object itemId: itemIds){
				Bkbdetail itemDetail = new Bkbdetail();	                    				
				itemDetail = model.getBeanItemContainerBkbDetail().getItem(itemId).getBean();
				//KARENA NEW MAKA kode refno (New) di Container harus diganti dengan kode yang baru
				BkbdetailPK newId = new BkbdetailPK();
				newId = itemDetail.getId();
				newId.setRefno(headerId.getRefno());				
				itemDetail.setId(newId);

				if (itemDetail.getDebetkredit().equals("D")){
					debetValue += itemDetail.getAmount();
				} else if (itemDetail.getDebetkredit().equals("K")){
					kreditValue += itemDetail.getAmount();
				}
				
				//MAsukkan ke List yang baru
				newList.add(itemDetail);
				//UPDATE DETAIL KE DATABASE
				model.getBkbDetailService().createObject(itemDetail);
			}

			//3. Perbaiki kondisi form
			model.getBeanItemContainerBkbDetail().removeAllItems();
			model.getBeanItemContainerBkbDetail().addAll(newList);
			view.getTableDetail().refreshRowCache();
			
			//4. UPDATE SALDO KAS BESAR
			updateBalance(model.getNewBkbheader().getDivisionBean(), debetValue, kreditValue);
			
    		model.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
    		view.setDisplay();
			
			Notification.show("Simpan Sukses");		
			
			
		} catch(Exception ex){
			Notification.show("Terjadi Kesalahan Simpan");
			ex.printStackTrace();
			
		}
	}
	public void saveFormUpdate(){
		System.out.println("Start Update");
		try{
			//**HAPUS HEADER DAN DETAIL OLD (OTOMATIS DENGAN DETAIL)	
			List<Bkbdetail> listDetailOld = new ArrayList<Bkbdetail>(model.getBkbheader().getBkbdetails());
			double debetValueRem = 0.0;
			double kreditValueRem = 0.0;
			for (Bkbdetail itemDetail:listDetailOld){
				if (itemDetail.getDebetkredit().equals("D")){
					debetValueRem -= itemDetail.getAmount();
				} else if (itemDetail.getDebetkredit().equals("K")){
					kreditValueRem -= itemDetail.getAmount();
				}
				
				model.getBkbDetailService().removeObject(itemDetail);	
			}
			//UPDATE SALDO KAS BESAR
			updateBalance(model.getBkbheader().getDivisionBean(), debetValueRem, kreditValueRem);			
			
			//**GANTI DENGAN YANG BARU
			//**HEADER
			//1. COMMIT BINDER
			model.getBinderBkbheader().commit();
			//2. Simpan Header ke DATABASE :: SAVE Header dan Detail Secara Otomatis
			//Clean Up detail:: Supaya tidak bentrok
			model.getBkbheader().setBkbdetails(null);
			model.getBkbHeaderService().updateObject(model.getBkbheader());
			
			//**DETAIL
//			fixItemOnTableDetail();
			//1. Save New Update to Table detail dan Database
			double debetValue = 0.0;
			double kreditValue = 0.0;
			Collection itemIds = model.getBeanItemContainerBkbDetail().getItemIds();
			Set<Bkbdetail> newSet = new HashSet<Bkbdetail>();
			for (Object itemId: itemIds){
				Bkbdetail itemDetail = new Bkbdetail();	                    				
				itemDetail = model.getBeanItemContainerBkbDetail().getItem(itemId).getBean();
				
				if (itemDetail.getDebetkredit().equals("D")){
					debetValue += itemDetail.getAmount();
				} else if (itemDetail.getDebetkredit().equals("K")){
					kreditValue += itemDetail.getAmount();
				}
				newSet.add(itemDetail);
				///Simpan List Detail Ke database :: SUDAH TERSIMPAN KARNA PAKE LAZY
				model.getBkbDetailService().createObject(itemDetail);
			}
			model.getBkbheader().setBkbdetails(newSet);
			
			//2. Perbaiki kondisi form
			model.getBeanItemContainerBkbDetail().removeAllItems();
			model.getBeanItemContainerBkbDetail().addAll(newSet);
			view.getTableDetail().refreshRowCache();
			
			//3. UPDATE SALDO KAS BESAR
			updateBalance(model.getBkbheader().getDivisionBean(), debetValue, kreditValue);
			
			//SET ULANG KONDISI FORM			
			model.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
    		view.setDisplay();
			
			Notification.show("Simpan Sukses");		
			
		} catch(Exception ex) {
			Notification.show("Terjadi Kesalahan Simpan");
			ex.printStackTrace();
		}
	}
	
	//SALDO KAS
	public void updateBalance(Division divisionBean, double debetValue, double kreditValue){
		
		Bkbbalance domain = new Bkbbalance();
		BkbbalancePK id = new BkbbalancePK();
		id.setDivision(divisionBean.getId());
		id.setFdate(model.getTransaksiHelper().getCurrentTanggalTransaksiBerjalan(divisionBean));
		domain.setId(id);
		
		domain.setDivisionBean(divisionBean);
		
		domain.setAmountDebet(domain.getAmountDebet() + debetValue);
		domain.setAmountKredit(domain.getAmountKredit() + kreditValue);
		
		model.getBkbbalanceService().updateObject(domain);
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
	                    				model.getBinderBkbheader().setItemDataSource(model.getBkbheader());
	                    			} else {
	                    				model.getBinderBkbheader().discard();
	                    			}
	                    			//DETAIL DI ISI DAN DI LOAD ULANG
	                    			model.fillContainerDetailWithNumber(model.getBkbheader().getBkbdetails());
	                    			view.getTableDetail().refreshRowCache();
	                    			
	                    			model.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
	                    			
	                    			Notification.show("Perubahan dibatalkan");
	                    		} catch(Exception ex) {
	                    			ex.printStackTrace();
	                    			Notification.show("Terjadi Kesalahan Discard");
	                    		}
	                        	
	                        } else {
	                        //TIDAK ADA YANG PERLU DIFOCUS
	                         
	                        }
	               		 //SET ULANG KONDISI FORM
	            			view.setDisplay();

	                    }
	                });	        
		 discardDialog.setStyleName("dialog");
		 discardDialog.getOkButton().setStyleName("small");
		 discardDialog.getCancelButton().setStyleName("small");
		 //JANGAN LUPA
		 discardDialog.focus();
		
	}
	public void searchHeader(){
		view.buildWindowHeaderSelect();
		initListenerWindowHeaderSelect();
		view.setFormButtonAndTextState();		
	}
	public void windowHeaderSelectViewSelectAndClose(){
		view.destroyWindowHeaderSelect();
		model.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
		//SET ITEM HEADER BINDER
		if (view.getHeaderSelectModel().getBkbHeader() != null){
			//HEADER
			model.bkbheader = new Bkbheader();
			model.bkbheader = view.getHeaderSelectModel().getBkbHeader();
			model.getBinderBkbheader().setItemDataSource(model.bkbheader);
			view.bindAndBuildFieldGroupComponent();			
			//TAMPILIN DETAIL AFTER SELECT
			//FILL TABLE DETAIL
			//MENGOLAH
			model.fillContainerDetailWithNumber(model.getBkbheader().getBkbdetails());
			view.getTableDetail().refreshRowCache();
			
		}
		//SET ULANG KONDISI FORM
		view.setDisplay();
	}
	public void addDetail(){
		view.buildWindowItemAdd(EnumFormOperationStatus.ADDING.getStrCode());
		initListenerWindowItemAdd();
		view.setFormButtonAndTextState();		
	}
	public void editDetail(){
		String refno = null;
		try{
			refno = model.getBkbdetail().getId().getRefno();
		} catch(Exception ex){}
		
		if (refno != null) {
			view.buildWindowItemAdd(EnumFormOperationStatus.EDITING.getStrCode());
			initListenerWindowItemAdd();
			view.setFormButtonAndTextState();
		} else {
			Notification.show("Belum ada Item yang dipilih");
		}
	}
	
	public void remDetail(){
		List<Bkbdetail> listNotSelected = new ArrayList<Bkbdetail>();
		
		Collection items = model.getBeanItemContainerBkbDetail().getItemIds();
		for (Object itemId: items){
			Bkbdetail itemDetail = new Bkbdetail();
			itemDetail = model.getBeanItemContainerBkbDetail().getItem(itemId).getBean();
			if (itemDetail.getSelected().getValue()==false){				
				listNotSelected.add(itemDetail);
			}
		}
		//HAPUS DULU BARU TAMBAH LAGI >> NDAK EFEKTIF
		model.getBeanItemContainerBkbDetail().removeAllItems();
		model.getBeanItemContainerBkbDetail().addAll(listNotSelected);
		
		view.setDisplayTableFooter();
		
	}
	
	public void windowItemAddViewAddToDetail(){
		try{
			boolean isValid = true;
			
			//BIND DIVISI LAGI AHH
			Division divisionBean = new Division();
			String strDiv = null;
			try{
				divisionBean = model.getBeanItemContainerDivision().getItem(view.getComboDivision().getValue()).getBean();
				strDiv = divisionBean.getId();
			} catch(Exception ex){
			}			
			if (strDiv.equals(null)){
				isValid = false;
			}
			double nilaiAmount = (Double) view.getItemAddView().getFieldAmount().getConvertedValue();
			if (nilaiAmount <= 0){
				isValid = false;
				Notification.show("Amount atau nilai tidak boleh kosong");
			}
			
			if (isValid){
				//1. JIKA TAMBAH => id kosong
				BkbdetailPK id = new BkbdetailPK();
				if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
					id.setRefno(model.getNewBkbheader().getId().getRefno());
					id.setDivision(strDiv);	
				} else {
					id.setRefno(model.getBkbheader().getId().getRefno());
					id.setDivision(model.getBkbheader().getId().getDivision());	
				}
				//NOMOR URUT
				if (view.getItemAddModel().OperationStatus.equals(EnumFormOperationStatus.ADDING.getStrCode())){
					id.setNourut(model.getBeanItemContainerBkbDetail().size() + 1);
					//NOMOR URUT UNTUK TABLE
					view.getItemAddModel().getBkbdetail().setUrut(id.getNourut());
				}else {
					id.setNourut(model.getBkbdetail().getId().getNourut());				
				}
				view.getItemAddModel().getBkbdetail().setId(id);
				
				//COMMIT ALL
					view.getItemAddModel().getBinderBkbdetail().commit();		
					//LAIN-LAIN
					view.getItemAddModel().getBkbdetail().setDivisionBean(divisionBean);
					
					model.getBeanItemContainerBkbDetail().addBean(view.getItemAddModel().getBkbdetail());
					view.getTableDetail().refreshRowCache();
	
					view.getItemAddPresenter().addItem();
					view.getItemAddView().focustIdOrDesc();
				
				//JIKA EDITING LANGSUNG CLOSE
				if (view.getItemAddModel().OperationStatus.equals(EnumFormOperationStatus.EDITING.getStrCode())){
					windowItemAddViewClose();
				}	
			}
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	public void windowItemAddViewReset(){		
	}
	public void windowItemAddViewClose(){
		view.destroyWindowItemAdd();
		view.setDisplayTableFooter();
		
	}
	
	//FOR TESTING
	public static void main(String args []){
		PenerimaanKasBesarModel model = new PenerimaanKasBesarModel();
		PenerimaanKasBesarView view = new PenerimaanKasBesarView(model);
		PenerimaanKasBesarPresenter f = new PenerimaanKasBesarPresenter(model, view);
		
		Bkbheader header = new Bkbheader();
		BkbheaderPK id = new BkbheaderPK();
		id.setRefno("001");
		id.setDivision("DIV2");		
		header.setId(id);
		
		Division division = new Division();
		division.setId("DIV2");
		header.setDivisionBean(division);
		
		BkbdetailPK idDetail = new BkbdetailPK();
		idDetail.setRefno(id.getRefno());
		idDetail.setDivision(id.getDivision());
		
		f.model.getBkbDetailService().removeAllByHeaderIdWithoutNoUrut(idDetail);
		
		f.model.getBkbHeaderService().removeObject(header);
		System.out.println("Oke Sukses");
		
	}
}
