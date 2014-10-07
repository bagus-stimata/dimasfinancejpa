package org.dimas.finance.ar.kredittunai;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.ArinvoicePK;
import org.dimas.finance.model.Arpaymentdetail;
import org.dimas.finance.model.ArpaymentdetailPK;
import org.dimas.finance.model.Arpaymentheader;
import org.dimas.finance.model.ArpaymentheaderPK;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Bukutransfer;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.model.modelenum.EnumHelpOverlayTipe;
import org.dimas.finance.util.HelpManager;
import org.dimas.finance.util.HelpOverlay;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.AbstractSelect.ItemDescriptionGenerator;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class ArPaymentCustomerPresenter implements ClickListener, ValueChangeListener, Handler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArPaymentCustomerModel model;
	private ArPaymentCustomerView view;
	
	public ArPaymentCustomerPresenter(ArPaymentCustomerModel model, ArPaymentCustomerView view){
		this.model = model;
		this.view = view;
		
		initVariable();
		initVariableData();		
		initListener();		
		initListenerSubWindow();
		
		initDisplay();
		
	}
	public void initVariable(){
//		//DETAIL DISPLAY KOSONG AJA		
//		model.beanItemContainerDetail = new BeanItemContainer<Arpaymentdetail>(Arpaymentdetail.class);				
	}
	public void initVariableData(){
//		//Mungkin harus ada isinya
//		model.beanItemInvoice = new BeanItem<Arinvoice>(model.getItemInvoice());				
	}
	
	public void initListener(){
		view.getBtnAddDetail().addClickListener(this);
		view.getBtnEditDetail().addClickListener(this);
		view.getBtnDeleteDetail().addClickListener(this);
		view.getBtnReloadDetail().addClickListener(this);
		
		view.getTableHeader().addValueChangeListener(listenerTableHeader);
		view.getTableDetail().addValueChangeListener(listenerTableDetail);
		
		// register action handler (enter and ctrl-n)
//		view.getPanelUtama().addActionHandler(this);
//		view.getPanelTop().addActionHandler(this);
//		view.getPanelTabel().addActionHandler(this);
		
//		view.getPanelForm().addActionHandler(this);
		
	}
	public void initListenerSubWindow(){
		
		ClickListener listenerSaveCancelSubWindow = new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				//BISA CLOSE KALAU STATUSNYA BOLEH CLOSE
				if (view.getFormModel().isAllowCloseWindow()==true){
					
					//HITUNG ULANG AMOUNT PAY dan update
					//KE ENTITYNYA LANGSUNG AJA
					model.getItemInvoice().setAmountpay(model.getHitungAmountDetailAllFromDatabase());
					//ADD PAYMENTHEADER CUMA UNTUK OPERASI PENAMBAHAN
					if(view.getFormModel().getFormOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
						model.getBeanItemContainerHeader().addBean(view.getFormModel().getArPaymentHeader());
					}
					
					//1. Always reload detail
					view.setDisplayHeader();
					view.setDisplayDetail();
					
					//2. Close dan Window pembayaran
					view.destroyFormPembayaran();				
					
				}
			}
		};
		
		//FOR CLOSING ONLY
		view.getFormView().getBtnCancelForm().addClickListener(listenerSaveCancelSubWindow);
		view.getFormView().getBtnSaveForm().addClickListener(listenerSaveCancelSubWindow);
		
	}
	
	//MAIN EVENT >> TABLE EVENT
	private  ValueChangeListener listenerTableHeader = new ValueChangeListener() {		
		@Override
		public void valueChange(ValueChangeEvent event) {
			//1. Rubah Item Header dengan yang baru
			Object itemId = event.getProperty().getValue();
			model.itemHeader = new Arpaymentheader();
			model.itemHeader = model.getBeanItemContainerHeader().getItem(itemId).getBean();		
			//2. Rubah Isi Containter Detail sesuai dengan header yang dipilih
			String refno = model.getItemHeader().getId().getRefno();
			String inv = model.getItemInvoice().getId().getInvoiceno();
			String div = model.getItemInvoice().getId().getDivision();
			
			model.beanItemContainerDetail = new BeanItemContainer<Arpaymentdetail>(Arpaymentdetail.class);
			
			//HITUNG SUBTOAL DETAIL DULU
			List<Arpaymentdetail> listDetail = new ArrayList<Arpaymentdetail>();
			listDetail = model.getArPaymentDetailService().findAllByRefnoAndInvAndDiv(refno, inv, div);
			//Hitung subtotalpay dan update listDetail
			int index =0;
			for (Arpaymentdetail item: listDetail){
				double subtotal = item.getCashamountpay() + item.getGiroamountpay() +
						item.getReturamountpay() + item.getTransferamountpay() + item.getPotonganamount();
				item.setSubtotalpay(subtotal);
				listDetail.set(index, item);
				index++;
			}
			
			model.beanItemContainerDetail.addAll(listDetail);
			
			//2. Ganti tampilan pada table detail
			view.setDisplayDetail();
			
		}
	};
	private ValueChangeListener listenerTableDetail = new ValueChangeListener() {		
		@Override
		public void valueChange(ValueChangeEvent event) {
			Object itemId = event.getProperty().getValue();
			model.itemDetail = new Arpaymentdetail();
			try{
				model.itemDetail = model.getBeanItemContainerDetail().getItem(itemId).getBean();
			} catch(Exception ex){}
		}
	};
	
	public void initDisplay(){		
		view.setDisplayHeader();
		view.setDisplayDetail();		
	}
	//MAIN EVENT >> BUTTON EVENT
	@Override
	public void buttonClick(ClickEvent event) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(0);
		nf.setMinimumFractionDigits(0);
		
//		//Antisipasi
//		if (view.getOperationStatus()==null) view.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
//		if (view.getOperationStatus().equals("")) view.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
//	
		if (event.getButton() == view.getBtnAddDetail()){
			//Jika amount lebih kecil dari amout pay maka masih boleh tambah
			if (model.getItemInvoice().getAmount() > model.getItemInvoice().getAmountpay() ){				
				//ARPAYMENTDETAIL DAN ARPAYMENT HEADER --> BUAT BARU
				model.itemHeader = new Arpaymentheader();
				ArpaymentheaderPK id = new ArpaymentheaderPK();
				id.setRefno("xxx");
			
//				model.getItemHeader().setRefno("xxx");
				model.getItemHeader().setId(id);
				model.getItemHeader().setEntrydate(new Date());
				model.getItemHeader().setTransdate(new Date());
				model.getItemHeader().setUserid("admin");
				model.getItemHeader().setClosing(false);				
				model.beanItemHeader = new BeanItem<Arpaymentheader>(model.getItemHeader());
				
				model.itemDetail = new Arpaymentdetail();
				ArpaymentdetailPK itemDetailPK = new ArpaymentdetailPK();
				itemDetailPK.setRefno("xxx");
				itemDetailPK.setInvoiceno(model.getItemInvoice().getId().getInvoiceno());
				itemDetailPK.setDivision(model.getItemInvoice().getId().getDivision());
				//KARENA TAMBAHNYA DISINI SELALU DI INVOICE BARU >> MAKA 1
				itemDetailPK.setNumber(1);	
				//KARENA BEAN INI DIMASUKKAN KE COMPOSITE KEY
				//DIVISION
				Division divisionBean = new Division();
				divisionBean = model.getItemInvoice().getDivisionBean();
				model.getItemDetail().setDivisionBean(model.getItemInvoice().getDivisionBean());
				//INVOICENO
				Arinvoice invoiceBean = new Arinvoice();
				invoiceBean = model.getItemInvoice();
				model.getItemDetail().setInvoiceBean(invoiceBean);
				
				model.getItemDetail().setId(itemDetailPK);				
				model.beanItemDetail = new BeanItem<Arpaymentdetail>(model.getItemDetail());			
				
				view.buildFormPembayaran();
				//Listener for close sub window
				initListenerSubWindow();
			} else {
				Notification.show("INVOICE SUDAH LUNAS");
			}
			
		} else if (event.getButton() == view.getBtnEditDetail()){
			
			//Tampil cuma jika di seleksi
			//SOME TIMES yang null cuma ID aja
			try{
				if (! model.getItemDetail().getId().equals(null)){
					if (model.getItemHeader().isClosing()==false){
						
						//ARPAYMENT HEADER DAN ARPAYMENT DETAIL IKUT SELEKSI TABLE
						view.buildFormPembayaran();
						//Listener for close sub window
						initListenerSubWindow();	
						
					} else {
						Notification.show("NOTA SUDAH CLOSING!.. TIDAK BISA DIHAPUS ATAU DI EDIT");
					}					
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				Notification.show("Belum ada Detail yang dipilih!!..");
			}
			
		} else if (event.getButton()==view.getBtnDeleteDetail()){
			try{
				if (! model.getItemDetail().getId().equals(null)){
					
					if (model.getItemHeader().isClosing()==false){
						deleteItem(model.getItemDetail());					
					} else {
						Notification.show("NOTA SUDAH CLOSING!.. TIDAK BISA DIHAPUS ATAU DI EDIT");					
					}
				}
				
			} catch(Exception ex){}
			
		} else if (event.getButton()==view.getBtnReloadDetail()){
			
			view.setDisplayDetail();
			
		} 
		
	}

	public void deleteItem(final Arpaymentdetail arpaymentdetail) {
		if (arpaymentdetail != null){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
					
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		
                    		double amountThisDetail = model.getItemDetail().getCashamountpay() + 
                    				model.getItemDetail().getGiroamountpay() +
                    				model.getItemDetail().getReturamountpay() + 
                    				model.getItemDetail().getTransferamountpay() + 
                    				model.getItemDetail().getPotonganamount();
                    		
                    		//HAPUS ITEM DETAIL DARI DATABASE
                    		model.getArPaymentDetailService().removeObject(model.getItemDetail());                    		
                    		//DELETE QUOTA GIRO, RETUR TRANSFER >> (GIRO OVERDUE BISA DI DELETE)
                    		if (model.getItemDetail().getBukugiroBean() != null) {
                    			
                    			System.out.println(model.getItemDetail().getBukugiroBean());
                    			
                    			Bukugiro newBukuGiro = new Bukugiro();
                    			newBukuGiro = model.getItemDetail().getBukugiroBean();
                    			//KURANGI GIRO DAN UPDATE KE DATABASE
                    			newBukuGiro.setAmountused(newBukuGiro.getAmountused() - model.getItemDetail().getGiroamountpay());
                    			model.getBukugiroService().updateObject(newBukuGiro);
                    			
                    		}
                    		if (model.getItemDetail().getBukutransferBean() != null) {
                    			System.out.println(model.getItemDetail().getBukutransferBean());
                    			Bukutransfer newBukuTransfer = new Bukutransfer();
                    			newBukuTransfer = model.getItemDetail().getBukutransferBean();
                    			//KURANGI TRANSFER DAN UPDATE KE DATABASE
                    			newBukuTransfer.setAmountused(newBukuTransfer.getAmountused() - model.getItemDetail().getTransferamountpay());
                    			model.getBukutransferService().updateObject(newBukuTransfer);
                    		}
                    		
                    		
                    		model.getBeanItemContainerDetail().removeItem(model.getItemDetail());
                    		if (model.getBeanItemContainerDetail().size() == 0){
                    			model.getArPaymentHeaderService().removeObject(model.getItemHeader());
                    			model.getBeanItemContainerHeader().removeItem(model.getItemHeader());
                    		}
                    		
                    		//PERBAIKI INVOICE AMOUNTPAY >> Sorry Harus manual
                    		model.getItemInvoice().setAmountpay(model.getItemInvoice().getAmountpay()- amountThisDetail);                    		
                    		model.getArInvoiceService().updateObject(model.getItemInvoice());
                    		
                    		
                    		//PERBAIKI TAMPILAN DETAIL & HEADER
                    		view.setDisplayHeader();
                    		view.setDisplayDetail();
                    		
                			Notification.show("Delete Sukses");		
                			
                    	} catch(Exception ex) {
                    		ex.printStackTrace();
                			Notification.show("Error Delete!!");		                        		
                    	}
                    	
                    } else {
                    // User did not confirm
                    }
//					view.getTable().focus();
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
	
	protected void addItem() {
	}
	
	public int updateAndValidateForm(){
		return 0;
	}
	public int insertAndValidateForm(){
		
		return 0;
	}

	
	public int discardForm(String argOperationStatus){
//		 ConfirmDialog discardDialog = ConfirmDialog.show(view.getUI(),"Konfirmasi Discard", "Yakin keluar mode editing(discard)?", "Discard..", "No",
//	                new ConfirmDialog.Listener() {
//	                    public void onClose(ConfirmDialog dialog) {
//	                        if (dialog.isConfirmed()) {
//	                            // Confirmed to continue
//	                    		try {			
//	                    			//1. form discard
//	                    			view.getForm().discard();
//	                    			view.setOperationStatus(EnumFormOperationStatus.OPEN.getStrCode());
//	                    			setFormButtonAndText();
//	                    			//2. refresh container
//	                        		model.setFreshDataTable();
//	                        		view.setDisplay();
//	                    			view.getTable().focus();
//	                    			
//	                    		} catch(Exception ex) {
//	                    			Notification.show("Terjadi Kesalahan Discard");
//	                    		}
//	                        	
//	                        } else {
//	                        	view.getForm().getField("refno").focus();
//	                            // User did not confirm
//	                        }
//	                    }
//	                });	        
//		 discardDialog.setStyleName("dialog");
//		 discardDialog.getOkButton().setStyleName("small");
//		 discardDialog.getCancelButton().setStyleName("small");
//		 //Jangan lupa
//		 discardDialog.focus();
		
		return 0;
	}

	public int deleteForm(){
//		deleteItem(view.getTable().getValue());
//		model.setFreshDataTable();
//		view.setDisplay();
		return 0;
	}
	public int searchForm(){
//		//1. Remove filter dan Refresh container dalulu dahulu
//		model.getTableJpaContainer().removeAllContainerFilters();
//		
//		//2. Baru Kasih Filter Lagi
//		String theId = view.getFieldSearchById().getValue().toString().trim();
//		String theName = view.getFieldSearchByDesc().getValue().toString().trim();
//		Filter filter1 = new SimpleStringFilter("id", theId, true, false);
//		Filter filter2 = new Or(new SimpleStringFilter("region", theName, true, false));
//		model.getTableJpaContainer().addContainerFilter(filter1);
//		model.getTableJpaContainer().addContainerFilter(filter2);
//		//3. Refresh container dengan kondisi filter
////		model.setFreshDataTable();
//		view.setDisplay();
//		//Focus
//		view.getTable().focus();
		
		return 0;
	}
	public int reloadForm(){		
		model.removeContainerFiltersTableUtama();
		view.setDisplayHeader();
		
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

//		Object itemId = event.getProperty().getValue();
//		Item item = view.getTable().getItem(itemId);
//		boolean entitySelected = item != null;
//		// modify visibility of form and delete button if an item is selected
//		view.getForm().setVisible(entitySelected);
//		view.getDeleteButton().setEnabled(entitySelected);
//		if (entitySelected) {
//			// set entity item to form and focus it
//			view.getForm().setItemDataSource(item,
//					view.getFormPropertyIds() != null ? Arrays.asList(view.getFormPropertyIds())
//							: item.getItemPropertyIds());
//		}
//		//Nicer apperance
//		view.setFormProperties();
//		
//		view.setOperationStatus(EnumFormOperationStatus.EDITING.getStrCode());
//		setFormButtonAndText();
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
//		if (sender == view.getPanelTop()) {
//			return ACTIONS_SEARCH;
//		} else if (sender== view.getPanelTabel()){
//			return ACTIONS_TABLE;
//		} else if (sender== view.getPanelForm()){
//			return ACTIONS_FORM;
//		}
		return ACTIONS;
	}
	@Override
	public void handleAction(Action action, Object sender, Object target) {		
//		if (action==INSERT){
//			view.getAddButton().click();
//		}else if (action==DELETE){
//			deleteForm();
//		}else if (action==ENTER_SEARCH){
//		}else if (action==ENTER_TABLE){
//			view.getForm().focus();
//		}else if (action==ENTER_FORM){
//			//MENGGANTI TAB: HARUSNYA
////			commit.click();
//		}else if (action==ESCAPE_SEARCH){
//			view.getTable().focus();
//		}else if (action==ESCAPE_TABLE){
//		}else if (action==ESCAPE_FORM){
//			view.getDiscard().click();
//		}else if (action==ALTS){
//			view.getCommit().click();
//		}else if (action==ALTC){
//			if (view.getForm().isVisible()){
//				discardForm(view.getOperationStatus());
//			}
//		}else if (action==ENTER){
//			
//		}else if (action==EDITMODE){
//			view.getForm().focus();			
//		}else if (action==FINDMODE){
//			view.getFieldSearchById().focus();
//		}else if (action==FIND){			
//		}else if (action==REFRESH){
//		}else if (action==HELP){
//		}else if (action==ADD){		
//		}else if (action==DEL){		
//			view.getDeleteButton().click();
//		}
	}
	
	
}
