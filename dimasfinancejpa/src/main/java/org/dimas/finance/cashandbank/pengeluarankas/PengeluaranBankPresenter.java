package org.dimas.finance.cashandbank.pengeluarankas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dimas.finance.model.Account;
import org.dimas.finance.model.Bbankbalance;
import org.dimas.finance.model.BbankbalancePK;
import org.dimas.finance.model.Bbankdetail;
import org.dimas.finance.model.BbankdetailPK;
import org.dimas.finance.model.Bkbbalance;
import org.dimas.finance.model.BkbbalancePK;
import org.dimas.finance.model.Bbankdetail;
import org.dimas.finance.model.BbankdetailPK;
import org.dimas.finance.model.Bbankheader;
import org.dimas.finance.model.BbankheaderPK;
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

public class PengeluaranBankPresenter implements ClickListener, ValueChangeListener{
	private PengeluaranBankModel model;
	private PengeluaranBankView view;
	
	public PengeluaranBankPresenter(PengeluaranBankModel model, PengeluaranBankView view){
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
							
							Collection itemIds = model.getBeanItemContainerDetail().getItemIds();
							for (Object itemId: itemIds){
								model.getBeanItemContainerDetail().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getBeanItemContainerDetail().getItem(itemId).getBean().getSelected().setValue(true);
							}
							
						} else {
							
							view.getTableDetail().setColumnHeader("selected", "<input type='checkbox' />");		
							model.setSelectAllItem(true);
							
							Collection itemIds = model.getBeanItemContainerDetail().getItemIds();
							for (Object itemId: itemIds){
								model.getBeanItemContainerDetail().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getBeanItemContainerDetail().getItem(itemId).getBean().getSelected().setValue(false);
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
				model.getNewItemHeader().setDivisionBean(divisionBean);
			} else {
				model.itemHeader.setDivisionBean(divisionBean);
			}					
			//Rubah tanggal transakasi otomatis
			view.getDateFieldTransDate().setValue(model.getTransaksiHelper().getCurrentTanggalTransaksiBerjalan(divisionBean));
		} catch (Exception ex){
		}
		
		//2. RUBAH SEMUA PK DI DETAIL
		List<Bbankdetail> newListDetail = new ArrayList<Bbankdetail>();
		Collection itemIds = model.getBeanItemContainerDetail().getItemIds();
		for (Object itemId: itemIds){
			Bbankdetail item = new Bbankdetail();
			item = model.getBeanItemContainerDetail().getItem(itemId).getBean();
			BbankdetailPK id = new BbankdetailPK();
			id = item.getId();
			id.setDivision(divisionBean.getId());
			
			item.setId(id);
			item.setDivisionBean(divisionBean);
			
			newListDetail.add(item);
			
		}
		model.getBeanItemContainerDetail().removeAllItems();
		model.getBeanItemContainerDetail().addAll(newListDetail);
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
		
		model.itemDetail = new Bbankdetail();
		if (entitySelected) {			
			model.itemDetail = model.getBeanItemContainerDetail().getItem(itemId).getBean();
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
			model.newItemHeader = new Bbankheader();
			//Karena tidak pisa pake null representation
			BbankheaderPK id = new BbankheaderPK();
			id.setRefno("New");
			id.setDivision("");			
			model.newItemHeader.setId(id);
			
			Division div = new Division();
			String strDiv = null;
			try{
				div = model.getBeanItemContainerDivision().getItem(view.getComboDivision().getValue()).getBean();
				strDiv = div.getId();
			} catch(Exception ex){
			}			
			model.newItemHeader.setDivisionBean(div);
			if (strDiv ==null){
				Notification.show("Pilih Divisi untuk mengaktifkan tombol Save dan Cancel");
			}
			
			model.newItemHeader.setNotes("");		
			model.newItemHeader.setClosing(false);
			model.newItemHeader.setEntrydate(new Date());
			model.newItemHeader.setTransdate(model.getTransaksiHelper().getCurrentTanggalTransaksiBerjalan(div));
			
			//TRANSAKTION DATE MENGAMBIL DARI DIVISI TANGGAL TRANSAKSI
//			model.bkbheader.setTransdate(new Date());
			
			//2. SET NEW DATA ITEM TO BINDER
			model.getBinderHeader().setItemDataSource(model.newItemHeader);
			
			//3. REFRESH VIEW AND SHOW FORM LAYOUT
			view.bindAndBuildFieldGroupComponent();			
			view.getFormLayout().setVisible(true);

			
			//KOSONGKAN DETAIL
			//KOSONGKAN TABLE DETAIL
			model.getBeanItemContainerDetail().removeAllItems();
			view.getTableDetail().refreshRowCache();
			
			//3.SET FORM STATE AND BUTTON STATE
			model.setOperationStatus(EnumFormOperationStatus.ADDING.getStrCode());
			view.setFormButtonAndTextState();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	public void deleteForm(){
		
		if (model.getItemHeader() != null ){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		if (model.getItemHeader().isClosing()==false){
	                			//**HAPUS HEADER DAN DETAIL OLD (OTOMATIS DENGAN DETAIL)	
	                			double debetValue = 0.0;
	                			double kreditValue = 0.0;                    		
	                			List<Bbankdetail> listDetailToRem = new ArrayList<Bbankdetail>(model.getItemHeader().getBbankdetails());
	                			for (Bbankdetail itemDetail:listDetailToRem){
	                				if (itemDetail.getDebetkredit().equals("D")){
	                					debetValue -= itemDetail.getAmount();
	                				} else if (itemDetail.getDebetkredit().equals("K")){
	                					kreditValue -= itemDetail.getAmount();
	                				}
	                				model.getBbankDetailService().removeObject(itemDetail);	
	                			}
	                			model.getBbankHeaderService().removeObject(model.getItemHeader());		
	                    		
	                    		//**UPDATE Balance
	                    		updateBalance(model.getItemHeader().getDivisionBean(), debetValue, kreditValue);

	                    		//**KOSONGKAN BINDER
	                    		model.itemHeader = new Bbankheader();
	                    		model.getBinderHeader().setItemDataSource(model.getItemHeader());
	                    		
	                    		//**HAPUS TABLE
	                    		model.getBeanItemContainerDetail().removeAllItems();
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
				strHeader = model.getNewItemHeader().getId().getRefno();				
			} else {
				strHeader = model.getItemHeader().getId().getRefno();				
			}
		} catch(Exception ex){}
		
		if (strHeader != null ){
			if (model.getBeanItemContainerDetail().size()>0){
				 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Konfirmasi Simpan", "Simpan Data?", "Save", "No",
			                new ConfirmDialog.Listener() {
			                    public void onClose(ConfirmDialog dialog) {
			                        if (dialog.isConfirmed()) {
			                            // Confirmed to continue
			                    		if (model.getItemHeader().isClosing()==false){
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
			model.getBinderHeader().commit();
			//2. PERBAIKI KODE (Untuk header dan detail)
			BbankheaderPK headerId = new BbankheaderPK();
			headerId = model.getNewItemHeader().getId();
			String newRefno = model.getTransaksiHelper().getNewNomorUrutBukuKasBesar(model.getNewItemHeader().getDivisionBean());
			headerId.setRefno(newRefno);
			model.getNewItemHeader().setId(headerId);
			//3. Simpan Header ke DATABASE
			model.getBbankHeaderService().createObject(model.getNewItemHeader());
			
			fixItemOnTableDetail();			
			//2. Container masih berisi nilai(Tidak ikut dihapus) >> Simpan lagi ke DATABASE
			double debetValue = 0.0;
			double kreditValue = 0.0;
			Collection itemIds = model.getBeanItemContainerDetail().getItemIds();
			List<Bbankdetail> newList = new ArrayList<Bbankdetail>();
			for (Object itemId: itemIds){
				Bbankdetail itemDetail = new Bbankdetail();	                    				
				itemDetail = model.getBeanItemContainerDetail().getItem(itemId).getBean();
				//KARENA NEW MAKA kode refno (New) di Container harus diganti dengan kode yang baru
				BbankdetailPK newId = new BbankdetailPK();
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
				model.getBbankDetailService().createObject(itemDetail);
			}

			//3. Perbaiki kondisi form
			model.getBeanItemContainerDetail().removeAllItems();
			model.getBeanItemContainerDetail().addAll(newList);
			view.getTableDetail().refreshRowCache();
			
			//4. UPDATE SALDO KAS BESAR
			updateBalance(model.getNewItemHeader().getDivisionBean(), debetValue, kreditValue);
			
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
			List<Bbankdetail> listDetailOld = new ArrayList<Bbankdetail>(model.getItemHeader().getBbankdetails());
			double debetValueRem = 0.0;
			double kreditValueRem = 0.0;
			for (Bbankdetail itemDetail:listDetailOld){
				if (itemDetail.getDebetkredit().equals("D")){
					debetValueRem -= itemDetail.getAmount();
				} else if (itemDetail.getDebetkredit().equals("K")){
					kreditValueRem -= itemDetail.getAmount();
				}
				
				model.getBbankDetailService().removeObject(itemDetail);	
			}
			//UPDATE SALDO KAS BESAR
			updateBalance(model.getItemHeader().getDivisionBean(), debetValueRem, kreditValueRem);			
			
			//**GANTI DENGAN YANG BARU
			//**HEADER
			//1. COMMIT BINDER
			model.getBinderHeader().commit();
			//2. Simpan Header ke DATABASE :: SAVE Header dan Detail Secara Otomatis
			//Clean Up detail:: Supaya tidak bentrok
			model.getItemHeader().setBbankdetails(null);
			model.getBbankHeaderService().updateObject(model.getItemHeader());
			
			//**DETAIL
//			fixItemOnTableDetail();
			//1. Save New Update to Table detail dan Database
			double debetValue = 0.0;
			double kreditValue = 0.0;
			Collection itemIds = model.getBeanItemContainerDetail().getItemIds();
			Set<Bbankdetail> newSet = new HashSet<Bbankdetail>();
			for (Object itemId: itemIds){
				Bbankdetail itemDetail = new Bbankdetail();	                    				
				itemDetail = model.getBeanItemContainerDetail().getItem(itemId).getBean();
				
				if (itemDetail.getDebetkredit().equals("D")){
					debetValue += itemDetail.getAmount();
				} else if (itemDetail.getDebetkredit().equals("K")){
					kreditValue += itemDetail.getAmount();
				}
				newSet.add(itemDetail);
				///Simpan List Detail Ke database :: SUDAH TERSIMPAN KARNA PAKE LAZY
				model.getBbankDetailService().createObject(itemDetail);
			}
			model.getItemHeader().setBbankdetails(newSet);
			
			//2. Perbaiki kondisi form
			model.getBeanItemContainerDetail().removeAllItems();
			model.getBeanItemContainerDetail().addAll(newSet);
			view.getTableDetail().refreshRowCache();
			
			//3. UPDATE SALDO KAS BESAR
			updateBalance(model.getItemHeader().getDivisionBean(), debetValue, kreditValue);
			
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
		
		Bbankbalance domain = new Bbankbalance();
		BbankbalancePK id = new BbankbalancePK();
		id.setDivision(divisionBean.getId());
		id.setFdate(model.getTransaksiHelper().getCurrentTanggalTransaksiBerjalan(divisionBean));
		domain.setId(id);
		
		domain.setDivisionBean(divisionBean);
		
		domain.setAmountDebet(domain.getAmountDebet() + debetValue);
		domain.setAmountKredit(domain.getAmountKredit() + kreditValue);
		
		model.getBbankbalanceService().updateObject(domain);
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
	                    				model.getBinderHeader().setItemDataSource(model.getItemHeader());
	                    			} else {
	                    				model.getBinderHeader().discard();
	                    			}
	                    			//DETAIL DI ISI DAN DI LOAD ULANG
	                    			model.fillContainerDetailWithNumber(model.getItemHeader().getBbankdetails());
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
		if (view.getHeaderSelectModel().getItemHeader() != null){
			//HEADER
			model.itemHeader = new Bbankheader();
			model.itemHeader = view.getHeaderSelectModel().getItemHeader();
			model.getBinderHeader().setItemDataSource(model.itemHeader);
			view.bindAndBuildFieldGroupComponent();			
			//TAMPILIN DETAIL AFTER SELECT
			//FILL TABLE DETAIL
			//MENGOLAH
			model.fillContainerDetailWithNumber(model.getItemHeader().getBbankdetails());
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
			refno = model.getItemDetail().getId().getRefno();
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
		List<Bbankdetail> listNotSelected = new ArrayList<Bbankdetail>();
		
		Collection items = model.getBeanItemContainerDetail().getItemIds();
		for (Object itemId: items){
			Bbankdetail itemDetail = new Bbankdetail();
			itemDetail = model.getBeanItemContainerDetail().getItem(itemId).getBean();
			if (itemDetail.getSelected().getValue()==false){				
				listNotSelected.add(itemDetail);
			}
		}
		//HAPUS DULU BARU TAMBAH LAGI >> NDAK EFEKTIF
		model.getBeanItemContainerDetail().removeAllItems();
		model.getBeanItemContainerDetail().addAll(listNotSelected);
		
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
				BbankdetailPK id = new BbankdetailPK();
				if (model.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
					id.setRefno(model.getNewItemHeader().getId().getRefno());
					id.setDivision(strDiv);	
				} else {
					id.setRefno(model.getItemHeader().getId().getRefno());
					id.setDivision(model.getItemHeader().getId().getDivision());	
				}
				//NOMOR URUT
				if (view.getItemAddModel().OperationStatus.equals(EnumFormOperationStatus.ADDING.getStrCode())){
					id.setNourut(model.getBeanItemContainerDetail().size() + 1);
					//NOMOR URUT UNTUK TABLE
					view.getItemAddModel().getItemDetail().setUrut(id.getNourut());
				}else {
					id.setNourut(model.getItemDetail().getId().getNourut());				
				}
				view.getItemAddModel().getItemDetail().setId(id);
				
				//COMMIT ALL
					view.getItemAddModel().getBinderItemDetail().commit();		
					//LAIN-LAIN
					view.getItemAddModel().getItemDetail().setDivisionBean(divisionBean);
					
					model.getBeanItemContainerDetail().addBean(view.getItemAddModel().getItemDetail());
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
		PengeluaranBankModel model = new PengeluaranBankModel();
		PengeluaranBankView view = new PengeluaranBankView(model);
		PengeluaranBankPresenter f = new PengeluaranBankPresenter(model, view);
		
		Bbankheader header = new Bbankheader();
		BbankheaderPK id = new BbankheaderPK();
		id.setRefno("001");
		id.setDivision("DIV2");		
		header.setId(id);
		
		Division division = new Division();
		division.setId("DIV2");
		header.setDivisionBean(division);
		
		BbankdetailPK idDetail = new BbankdetailPK();
		idDetail.setRefno(id.getRefno());
		idDetail.setDivision(id.getDivision());
		
		f.model.getBbankDetailService().removeAllByHeaderIdWithoutNoUrut(idDetail);
		
		f.model.getBbankHeaderService().removeObject(header);
		System.out.println("Oke Sukses");
		
	}
}
