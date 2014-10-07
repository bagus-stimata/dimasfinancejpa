package org.dimas.finance.ar.kredittunai;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.ArinvoicePK;
import org.dimas.finance.model.Arpaymentdetail;
import org.dimas.finance.model.ArpaymentdetailPK;
import org.dimas.finance.model.Arpaymentheader;
import org.dimas.finance.model.ArpaymentheaderPK;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.model.modelenum.EnumHelpOverlayTipe;
import org.dimas.finance.util.FormatAndConvertionUtil;
import org.dimas.finance.util.HelpManager;
import org.dimas.finance.util.HelpOverlay;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.Action;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table.HeaderClickEvent;
import com.vaadin.ui.Table.HeaderClickListener;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class CustomerCreditPresenter implements ClickListener, ValueChangeListener, Handler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CustomerCreditModel model;
	private CustomerCreditView view;
	
	Arinvoice item = new Arinvoice();
	
	
	public CustomerCreditPresenter(CustomerCreditModel model, CustomerCreditView view){
		this.model = model;
		this.view = view;
		initListener();		
		initDisplay();
		
	}
	
	public void initListener(){
		view.getBtnHelp().addClickListener(this);
		view.getBtnPrint().addClickListener(this);
		view.getBtnReload().addClickListener(this);
		view.getBtnSearch().addClickListener(this);
		
		view.getBtnPay().addClickListener(this);
		view.getBtnLunaskan().addClickListener(this);
		
		view.getTable().addValueChangeListener(this);
		
		// register action handler (enter and ctrl-n)
//		view.getPanelUtama().addActionHandler(this);
//		view.getPanelTop().addActionHandler(this);
//		view.getPanelTabel().addActionHandler(this);
//		view.getPanelForm().addActionHandler(this);
		
		HeaderClickListener listenerHeaderTableUtama = new HeaderClickListener() {			
			@Override
			public void headerClick(HeaderClickEvent event) {
				// TODO Auto-generated method stub
				
				try{
					if (event.getPropertyId().equals("selected")){
						if (model.isSelectAllInvoice()==true) {
							
							view.getTable().setColumnHeader("selected", "<input type='checkbox'  checked />");		
							model.setSelectAllInvoice(false);
							
							Collection itemIds = model.getTableBeanItemContainer().getItemIds();
							for (Object itemId: itemIds){
								model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setValue(true);
							}
							
						} else {
							
							view.getTable().setColumnHeader("selected", "<input type='checkbox' />");		
							model.setSelectAllInvoice(true);
							
							Collection itemIds = model.getTableBeanItemContainer().getItemIds();
							for (Object itemId: itemIds){
								model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setValue(false);
							}
							
						}	
						//KASIH SELEKSI >> buat SELECTED READONLY(TRUE) LAGI				
						view.setDisplayFooter();

					}
				} catch(Exception ex){}
			}
				
		};
		view.getTable().addHeaderClickListener(listenerHeaderTableUtama);
		
		
		
	}
	public void initListenerSubWindow(){
		
		CloseListener listenerCloseWindowPembayaran = new CloseListener() {
			
			@Override
			public void windowClose(CloseEvent e) {
				// TODO Auto-generated method stub
				try{
					//REMEMBER JPA CONTAINER LANGSUNG MENUJU KE DATABASE
					model.item.setAmountpay(view.getArPaymentCustomerModel().getItemInvoice().getAmountpay());
					//LUNAS ATAU TIDAK LUNAS amountPay >= amount maka 
					if (model.item.getAmountpay() >= model.item.getAmount()){
						model.item.setLunas(true);
					} else {
						model.item.setLunas(false);
					}
					//TANPA DI SELEKSI PASTI HARUS DISELEKSI
					model.getTableBeanItemContainer().addBean(model.getItem());
//					model.getTableBeanItemContainer().commit();
					view.getTable().commit();
					view.getTable().refreshRowCache();
					
					view.setDisplayFooter();
					
				} catch(Exception ex){}
				
			}
		};
		view.getWindowPembayaran().addCloseListener(listenerCloseWindowPembayaran);
		
		
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
		
			
		if (event.getButton() == view.getBtnSearch()) {
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
		} else if (event.getButton() == view.getBtnPay()){
			if (itemTableSelected != null){
				
				view.buildWindowPembayaran(model.getItem());
				//Listener for close sub window
				initListenerSubWindow();
				
			}		
		} else if (event.getButton() == view.getBtnLunaskan()){			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Pelunasan", "CHECK ULANG SEBELUM MELUNASKAN, YAKIN LUNASKAN? ", 
					 "Oke Lunaskan", "Cancel", konfirmDialogLunaskanListener);
			 
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
			
			
		}	 
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
		
	}

																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									public void setFormButtonAndText(){
		if (view.getOperationStatus().equals(EnumFormOperationStatus.OPEN.getStrCode())){
			view.getForm().setVisible(false);
			view.getTable().setSelectable(true);
			view.getForm().getField("id").setReadOnly(true);			
		} else if (view.getOperationStatus().equals(EnumFormOperationStatus.ADDING.getStrCode())){
			view.getForm().setVisible(true);
			view.getTable().setSelectable(false);
			view.getForm().getField("id").setReadOnly(false);
		}else if (view.getOperationStatus().equals(EnumFormOperationStatus.EDITING.getStrCode())){
			view.getForm().setVisible(true);
			view.getTable().setSelectable(true);
			view.getForm().getField("id").setReadOnly(true);
			
		}		
	}
	
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									
	public int searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.getTableBeanItemContainer().removeAllItems();
		model.getTableBeanItemContainer().removeAllContainerFilters();		
		
		//2. AMBAIL DARI DATABASE dengan Primary Key dan 
		String invoiceNo = view.getFieldSearchByInvoice().getValue().toString().trim();
		ArinvoicePK id = new ArinvoicePK();
		id.setDivision("%");
		id.setTipefaktur("%");
		id.setInvoiceno("%" + invoiceNo + "%");
		//FIND ALL PAKAI Ini
		model.getTableBeanItemContainer().addAll(model.getArInvoiceService().findAllByIdPk(id));
		//3. Filter Default
		model.setFilterDefaultBeanItemContainer();
			
		String idCust = view.getFieldSearchByIdCustomer().getValue().toString().trim();
		Filter filter2 = new And(new SimpleStringFilter("customer", idCust, true, false));
		model.getTableBeanItemContainer().addContainerFilter(filter2);
		
		String namaCust = view.getFieldSearchByNamaCustomer().getValue().toString().trim();
		Filter filter3 = new And(new SimpleStringFilter("custname", namaCust, true, false));
		model.getTableBeanItemContainer().addContainerFilter(filter3);
		
		
		String idSalesman = view.getFieldSearchByIdSalesman().getValue().toString().trim();
		Filter filter4 = new And(new SimpleStringFilter("salesman", idSalesman, true, false));
		model.getTableBeanItemContainer().addContainerFilter(filter4);

		String namaSalesman = view.getFieldSearchByNamaSalesman().getValue().toString().trim();
		Filter filter5 = new And(new SimpleStringFilter("spname", namaSalesman, true, false));
		model.getTableBeanItemContainer().addContainerFilter(filter5);
		

		//LUNAS BELUM LUNAS
		Filter filter7=null;
		try{
			if (view.getFieldSearchComboLunas().getValue().equals("B")){
				filter7 = new And(new Compare.Equal("lunas", false)); 					
			} else if (view.getFieldSearchComboLunas().getValue().equals("L")){
				filter7 = new And(new Compare.Equal("lunas", true)); 						
			}
			
			
			if (! view.getFieldSearchComboLunas().getValue().equals("S")){
				model.getTableBeanItemContainer().addContainerFilter(filter7);
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		//Tanggal
		try{
			//TANGGAL INVOICE
			long tglInvoiceFromLong = 0;
			try{
				tglInvoiceFromLong = view.getFieldSearchByDateInvoiceFrom().getValue().getTime();
			} catch (Exception ex){}
			long tglInvoiceToLong = 0;
			try{
				tglInvoiceToLong = view.getFieldSearchByDateInvoiceTo().getValue().getTime();
			} catch(Exception ex){}
			
			Filter filter8 = new And(new Compare.GreaterOrEqual("invoicedate", tglInvoiceFromLong));
			Filter filter9 = new And(new Compare.LessOrEqual("invoicedate", tglInvoiceToLong));
			//Jika semua maka tidak ada filter
			
			if (tglInvoiceFromLong !=0){
				model.getTableBeanItemContainer().addContainerFilter(filter8);
			}	
			if (tglInvoiceToLong !=0){
				model.getTableBeanItemContainer().addContainerFilter(filter9);
			}	
		} catch(Exception ex){}
			
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
    
	private Item itemTableSelected=null;
	
	@Override
	public void valueChange(ValueChangeEvent event) {
		
		try{
			Object itemId = event.getProperty().getValue();
			model.item = model.getTableBeanItemContainer().getItem(itemId).getBean();			
			itemTableSelected = view.getTable().getItem(itemId);
			
			//biar checked
			model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setReadOnly(false);
			if (model.getItem().getSelected().getValue()==true){
				model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setValue(false);
			} else {
				model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setValue(true);				
			}
			//JIKA LUNAS TIDAK BOLEH DI CHECK
			if (model.getItem().isLunas()==true){
				Notification.show("FAKTUR " + item.getId() + " SUDAH LUNAS!!");
				model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setValue(false);				
			}
			view.setDisplayFooter();
			
			boolean entitySelected = item != null;
		} catch (Exception ex){}
		
		// modify visibility of form and delete button if an item is selected
		
	}

    ConfirmDialog.Listener konfirmDialogLunaskanListener = new ConfirmDialog.Listener() {					
		//@Override
		public void onClose(ConfirmDialog dialog) {
			
            if (dialog.isConfirmed()) {
                // Confirmed to continue
            	
        		FormatAndConvertionUtil myFormatUtil = new FormatAndConvertionUtil();
        		Double totalYangHarusBayar = myFormatUtil.convertStringToDouble((String) view.getFieldAmountSum().getConvertedValue());
        		Double totalKurangLebihBayar = myFormatUtil.convertStringToDouble((String) view.getFieldAmountPaySum().getConvertedValue());

        		if (! totalYangHarusBayar.equals(totalKurangLebihBayar)){
        			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Pelunasan TO TUNAI", 
        					 "Kurang Lebih Bayar Rp. "  + (totalKurangLebihBayar - totalYangHarusBayar) + "! Yakin Lanjutkan!?", 
        					 "Yes", "No", konfirmDialogKurangLebihBayarListener);
        			 
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
        			lunaskan();
        		}
            } else {
            // User did not confirm
            }
		}
	};
	ConfirmDialog.Listener konfirmDialogKurangLebihBayarListener = new ConfirmDialog.Listener() {					
		@Override
		public void onClose(ConfirmDialog dialog) {
			
            if (dialog.isConfirmed()) {
            	// Confirmed to continue
            	lunaskan();
            } else {
            // User did not confirm
            }
		}
	};
	
	public void lunaskan(){
		
		FormatAndConvertionUtil myFormatUtil = new FormatAndConvertionUtil();
		Double totalKurangLebihBayar = myFormatUtil.convertStringToDouble((String) view.getFieldAmountPaySum().getConvertedValue());
		
		try{
			//KONSEP ONE-HEADER --> MANY DETAIL
			Division division = new Division();
			try{
				division = model.getBeanItemContainerDivision().getItem(view.getFieldSearchComboDivisi().getConvertedValue()).getBean();
			} catch(Exception ex){}
			
			String newRefno = model.getManagerTransaksi().getNewNomorUrutArPayment(division);
			//1. BUAT HEADER
			Arpaymentheader itemArpaymentHeader = new Arpaymentheader();
			ArpaymentheaderPK id = new ArpaymentheaderPK();
			id.setRefno(newRefno);
			id.setDivision(division.getId());
			itemArpaymentHeader.setId(id);				
			
			itemArpaymentHeader.setDivisionBean(division);
			itemArpaymentHeader.setEntrydate(new Date());
			itemArpaymentHeader.setTransdate(model.getManagerTransaksi().getCurrentTanggalTransaksiBerjalan(division));
			
			itemArpaymentHeader.setUserid("admin");
			itemArpaymentHeader.setClosing(false);			
			
			model.getArpaymentHeaderService().createObject(itemArpaymentHeader);
			
			//SYARAT INVOICE DILUNASKAN
			//1. Diseleksi dari Table
			//2. Belum Lunas
			List<Object> listItemProses = new ArrayList<Object>();				
			int nomorUrut=0;
			Collection itemIds =  model.getTableBeanItemContainer().getItemIds();				
			Arinvoice itemDummyArinvoice = new Arinvoice();
			for (Object itemId: itemIds){
				
				Arinvoice itemArinvoice = new Arinvoice();
				itemArinvoice = model.getTableBeanItemContainer().getItem(itemId).getBean();

				if (itemArinvoice.getSelected().getValue()==true && itemArinvoice.isLunas() == false ){
					Arpaymentdetail itemArpaymentDetail = new Arpaymentdetail();
					
					ArpaymentdetailPK itemDetailPK = new ArpaymentdetailPK();
					itemDetailPK.setRefno(newRefno);
					itemDetailPK.setInvoiceno(itemArinvoice.getId().getInvoiceno());
					itemDetailPK.setDivision(itemArinvoice.getId().getDivision());
					nomorUrut++;
					itemDetailPK.setNumber(nomorUrut);	
					itemArpaymentDetail.setId(itemDetailPK);
					
					itemArpaymentDetail.setDivisionBean(itemArinvoice.getDivisionBean());
					itemArpaymentDetail.setInvoiceBean(itemArinvoice);
					
					double jumlahBayar = itemArinvoice.getAmount() - itemArinvoice.getAmountpay();
					itemArpaymentDetail.setCashamountpay(jumlahBayar);
					//Hitung Kurang Lebih Bayar
					totalKurangLebihBayar -= jumlahBayar;
					//UPADATE DETAIL
					model.getArpaymentDetailService().updateObject(itemArpaymentDetail);
					
					//UPDATE INVOICE
					itemArinvoice.setAmountpay(itemArinvoice.getAmount());
					itemArinvoice.setLunas(true);
					model.getArInvoiceService().updateObject(itemArinvoice);

					//Untuk kurang lebih
					itemDummyArinvoice = new Arinvoice();
					itemDummyArinvoice = itemArinvoice;
					
					listItemProses.add(itemId);
					
				}				
			}
			//UPDATE Kurang lebih pembayaran
			
			itemDummyArinvoice.setAmountpay(itemDummyArinvoice.getAmountpay() + totalKurangLebihBayar);
			model.getArInvoiceService().updateObject(itemDummyArinvoice);
			//REFRESH FROM
			view.getBtnSearch().click();
			Notification.show("Sejumlah " + listItemProses.size() + " Nota berhasil dilunaskan!");
			
			view.setDisplayFooter();
			
		} catch(Exception ex){
			Notification.show("Error Pelunasan!!");		                        		
			ex.printStackTrace();
		}
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
		if (action==ENTER_SEARCH){
			view.getBtnSearch().click();
			view.getTable().focus();
		}else if (action==ENTER_TABLE){
			view.getForm().focus();
		}else if (action==ENTER_FORM){
		}else if (action==ESCAPE_SEARCH){
			view.getTable().focus();
		}else if (action==ESCAPE_TABLE){
		}else if (action==ALTC){
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
			
		}
	}
	
	
}
