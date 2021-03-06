package org.dimas.finance.ar.canvas;

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
import org.dimas.finance.model.Salesman;
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
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table.HeaderClickEvent;
import com.vaadin.ui.Table.HeaderClickListener;

public class PelunasanCanvasPresenter implements ClickListener, ValueChangeListener,  Handler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PelunasanCanvasModel model;
	private PelunasanCanvasView view;
	
	Arinvoice item = new Arinvoice();
	
	public PelunasanCanvasPresenter(PelunasanCanvasModel model, PelunasanCanvasView view){
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
		
		view.getBtnLunaskan().addClickListener(this);
		view.getBtnBatalLunaskan().addClickListener(this);
		
		view.getTable().addValueChangeListener(this);
		
		// register action handler (enter and ctrl-n)
//		view.getPanelUtama().addActionHandler(this);
//		view.getPanelTop().addActionHandler(this);
//		view.getPanelTabel().addActionHandler(this);
//		view.getPanelForm().addActionHandler(this);
		
		ValueChangeListener listenerComboLunas = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				view.getBtnBatalLunaskan().setEnabled(false);
				view.getBtnLunaskan().setEnabled(false);
				if (view.getFieldSearchComboLunas().getValue().equals("B")){
					view.getBtnLunaskan().setEnabled(true);					
				} else if (view.getFieldSearchComboLunas().getValue().equals("L")){
					view.getBtnBatalLunaskan().setEnabled(true);					
				}
			}
		};
		view.getFieldSearchComboLunas().setImmediate(true);
		view.getFieldSearchComboLunas().addValueChangeListener(listenerComboLunas);
		
		
		
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
		
	}
	
	public void initDisplay(){
		//1. Table
		model.setFreshDataBeanItemContainer();
		view.setDisplaySearchComponent();
		view.setDisplay();
		
		//EVENT AWAL
		view.getBtnSearch().click();
		
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
		} else if (event.getButton() == view.getBtnHelp()){
			helpForm();
		} else if (event.getButton() == view.getBtnLunaskan()){			
			if (itemTableSelected != null){			
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
			
		} else if (event.getButton() == view.getBtnBatalLunaskan()){
			if (itemTableSelected != null){			
				 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Batal Pelunasan", "BATALKAN PELUNASAN NOTA?: ", 
						 "Batalkan", "Cancel", konfirmDialogBatalLunaskanListener);
				 
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
			
		
		}
		
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
		
		}

																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									public void setFormButtonAndText(){
		
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
		

		String recapNo = view.getFieldSearchByRekap().getValue().toString().trim();
		if (! recapNo.trim().equals("")){
			Filter filter2 = new And(new SimpleStringFilter("recapno", recapNo, true, false));
			model.getTableBeanItemContainer().addContainerFilter(filter2);
		}
		
		//JIKA TIDAK DIPILIH YA KOSONG
		String strDivision="";
		Division div = new Division();
		try{
			div = model.getBeanItemContainerDivision().getItem(view.getFieldSearchComboDivisi().getValue()).getBean();
			strDivision = div.getId();		
		} catch(Exception ex){}
		try{
			if (! strDivision.trim().equals("")){
//				Filter filter3 = new And(new SimpleStringFilter("id.division", strDivision, true, false));
				Filter filter3 = new And(new Compare.Equal("divisionBean", div));
				model.getTableBeanItemContainer().addContainerFilter(filter3);
			} 
		}catch(Exception ex){}

		String strSalesman ="";
		Salesman salesman = new Salesman();
		try{
			salesman = model.getBeanItemContainerSalesman().getItem(view.getFieldSearchComboSalesman().getValue()).getBean();
			strSalesman = salesman.getId().getSpcode();	
			
		} catch(Exception ex){}
		
		
		try{
			if (! strSalesman.trim().equals("")){
				Filter filter4 = new And(new SimpleStringFilter("salesman", strSalesman, true, false));
				model.getTableBeanItemContainer().addContainerFilter(filter4);
			} 
		}catch(Exception ex){}
		
		
		
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
		//CURRENTLY UN CHECK
//		model.setCurrentContainerUncheck();
		
		view.setDisplay();
		//Focus
		view.getTable().focus();
		
		return 0;
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
        			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Pelunasan CANVAS", 
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
	

    ConfirmDialog.Listener konfirmDialogBatalLunaskanListener = new ConfirmDialog.Listener() {					
		//@Override
		public void onClose(ConfirmDialog dialog) {
			
            if (dialog.isConfirmed()) {
                // Confirmed to continue
            	batalLunaskan();
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
			
			view.getBtnSearch().click();
			
			Notification.show("Sejumlah " + listItemProses.size() + " Nota berhasil dilunaskan!");
			
			view.setDisplayFooter();
			
		} catch(Exception ex){
			Notification.show("Error Pelunasan!!");		                        		
			ex.printStackTrace();
		}
}

public void batalLunaskan(){
	
	//SYARAT PEMBATALAN PELUNASAN TUNAI
	//1. Belum diproses akhir hari/Dalam batas toleransi (HEADERNYA)
	//2. Sudah dilunaskan seluruhnya
	//INVOICE DIBAWAH PASTI TIDAK ADA YANG DICICIL artinya 	
	List<Object> listItemProses = new ArrayList<Object>();		
	List<Object> listItemProsesUnNormal = new ArrayList<Object>();		
	
	Collection itemIds =  model.getTableBeanItemContainer().getItemIds();
	for (Object itemId: itemIds){
		Arinvoice itemArinvoice = new Arinvoice();
		itemArinvoice = model.getTableBeanItemContainer().getItem(itemId).getBean();

		if (itemArinvoice.getSelected().getValue()==true && itemArinvoice.isLunas() == true){
			//1-nota =  1-arPaymentDetail
			//1. HAPUS NOTA dari ArPaymentDetail
			List<Arpaymentdetail> listPaymentDetailFromInv = new ArrayList<Arpaymentdetail>(itemArinvoice.getArpaymentDetailInvoices());
			if (listPaymentDetailFromInv.size()>0){ //Ada Isinya walau cuma satu
				Arpaymentdetail itemArPaymentDetail = new Arpaymentdetail();
				itemArPaymentDetail = listPaymentDetailFromInv.get(0);

				Arpaymentheader itemArPaymentHeader = new Arpaymentheader();
//				itemArPaymentHeader = itemArPaymentDetail.getArpaymentheaderBean();					
				itemArPaymentHeader = model.getArpaymentHeaderService().findAllById(itemArPaymentDetail.getId().getRefno(), 
						itemArPaymentDetail.getId().getDivision()).get(0);
				
				if (itemArPaymentHeader.isClosing() !=true){ //Kalau pake false mungkin null
					//1. HAPUS arPaymentDetail dari 1-Nota tsb
					model.getArpaymentDetailService().removeObject(itemArPaymentDetail);
					//2. Kurangkan Nilai Pembayaran Invoice(AmountPay) dengan Nilai Cash dari ArPaymentDetail(CashAmountPay)
					//Notes: Ingat lebih bayar dan kurang bayar
//					itemArinvoice.setAmountpay(itemArinvoice.getAmountpay() - itemArPaymentDetail.getCashamountpay());
					itemArinvoice.setAmountpay(0);//Karena Tuani>> 1-Nota = 1 Detail
					itemArinvoice.setLunas(false);
					model.getArInvoiceService().updateObject(itemArinvoice);
					//REFRESH SATU PERSATU >> lebih cepat
					listItemProses.add(itemId);
				}
			}else {
				//LANGSUNG DI ANGGAP LUNAS>> JIKA TIDAK ADA DETAIL
				itemArinvoice.setAmountpay(0);//Karena Tuani>> 1-Nota = 1 Detail
				itemArinvoice.setLunas(false);
				model.getArInvoiceService().updateObject(itemArinvoice);
				
				listItemProsesUnNormal.add(itemId);
				
			}

		}
		
	}
	
	//UPDATE CONTAINER AND TABLE
	view.getBtnSearch().click();
	
	Notification.show("Sejumlah: " + listItemProses.size() + " Nota berhasil DIBATALKAN!"  +
			"dan Sejumlah: " + listItemProsesUnNormal.size() + " Nota "
					+ "tidak mempunyai dokumen pembayaran (Pembatalan Tidak Normal)");
	
	view.setDisplayFooter();
}

	
	public int reloadForm(){		
		model.setFreshDataBeanItemContainer();
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
			//JIKA LUNAS TIDAK BOLEH DI CHECK >> Karena ada pembatalan lunas maka boleh saja faktur di klik
//			if (model.getItem().isLunas()==true){
//				Notification.show("FAKTUR " + item.getId() + " SUDAH LUNAS!!");
//				model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setValue(false);				
//			}
			
			view.setDisplayFooter();
			
			boolean entitySelected = item != null;
		} catch (Exception ex){}
		
		// modify visibility of form and delete button if an item is selected
		
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
