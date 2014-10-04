package org.dimas.finance.warehouse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.Arpaymentdetail;
import org.dimas.finance.model.ArpaymentdetailPK;
import org.dimas.finance.model.Arpaymentheader;
import org.dimas.finance.model.Bbankheader;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Salesman;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.dimas.finance.model.modelenum.EnumHelpOverlayTipe;
import org.dimas.finance.util.HelpManager;
import org.dimas.finance.util.HelpOverlay;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.Or;
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

public class PenandaanKirimPresenter implements ClickListener, ValueChangeListener,  ItemClickListener, Handler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PenandaanKirimModel model;
	private PenandaanKirimView view;
	
	Arinvoice item = new Arinvoice();
	
	public PenandaanKirimPresenter(PenandaanKirimModel model, PenandaanKirimView view){
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
		
		view.getBtnSetKirim().addClickListener(this);
		view.getBtnBatalKirim().addClickListener(this);
		
		view.getTable().addValueChangeListener(this);
		view.getTable().addItemClickListener(this);
		
		view.getBtnSelectRekapNo().addClickListener(this);
		// register action handler (enter and ctrl-n)
//		view.getPanelUtama().addActionHandler(this);
//		view.getPanelTop().addActionHandler(this);
//		view.getPanelTabel().addActionHandler(this);
//		view.getPanelForm().addActionHandler(this);
		
		ValueChangeListener listenerComboTerkirim = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				view.getBtnBatalKirim().setEnabled(false);
				view.getBtnSetKirim().setEnabled(false);
				if (view.getFieldSearchComboTerkirim().getValue().equals("B")){
					view.getBtnSetKirim().setEnabled(true);					
				} else if (view.getFieldSearchComboTerkirim().getValue().equals("K")){
					view.getBtnBatalKirim().setEnabled(true);					
				}
			}
		};
		view.getFieldSearchComboTerkirim().setImmediate(true);
		view.getFieldSearchComboTerkirim().addValueChangeListener(listenerComboTerkirim);
		
		HeaderClickListener listenerHeaderTableUtama = new HeaderClickListener() {			
			@Override
			public void headerClick(HeaderClickEvent event) {
				// TODO Auto-generated method stub
				
				try{
					if (event.getPropertyId().equals("selected")){
						if (model.isSelectAllInvoice()==true) {
							
							view.getTable().setColumnHeader("selected", "<input type='checkbox'  checked />");		
							model.setSelectAllInvoice(false);
							
							Collection itemIds = model.getTableJpaContainer().getItemIds();
							for (Object itemId: itemIds){
								model.getTableJpaContainer().getItem(itemId).getEntity().getSelected().setReadOnly(false);
								model.getTableJpaContainer().getItem(itemId).getEntity().getSelected().setValue(true);
							}
							
						} else {
							
							view.getTable().setColumnHeader("selected", "<input type='checkbox' />");		
							model.setSelectAllInvoice(true);
							
							Collection itemIds = model.getTableJpaContainer().getItemIds();
							for (Object itemId: itemIds){
								model.getTableJpaContainer().getItem(itemId).getEntity().getSelected().setReadOnly(false);
								model.getTableJpaContainer().getItem(itemId).getEntity().getSelected().setValue(false);
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
	
	public void initListenerWindowRecapSelect(){
		ClickListener closeListener = new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				windowRecapSelectClose();
			}
		};
		view.getRecapSelectView().getBtnSelect().addClickListener(closeListener);
	}
	
	public void initDisplay(){
		//1. Table
		model.setFreshDataTable();
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
		} else if (event.getButton() == view.getBtnSetKirim()){			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Pengiriman", "CHECK ULANG SEBELUM MENANDAI TERKIRIM, "
			 		+ " YAKIN TANDAI TERKIRIM? ", 
					 "OK Terkirim", "Cancel", konfirmDialogKirimListener);
			 
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
			
		} else if (event.getButton() == view.getBtnBatalKirim()){
			if (itemTableSelected != null){			
				 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Batal Pengiriman", "BATALKAN PENANDAAN TERKIRIM?: ", 
						 "Batalkan", "Cancel", konfirmDialogBatalKirimListener);
				 
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
			
		
		} else if (event.getButton() == view.getBtnSelectRekapNo()){
			windowRecapSelectShow();
		}
		
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
		
		}

																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									public void setFormButtonAndText(){
		
	}
	
	public int searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
//		//SELALU TUNAI
		model.getTableJpaContainer().refresh();
		model.getTableJpaContainer().removeAllContainerFilters();
		model.setFilterDefaultJpaContainer();
		
		//2. Baru Kasih Filter Lagi
		String invoiceNo = view.getFieldSearchByInvoice().getValue().toString().trim();
	
		Filter filter1 = new And(new SimpleStringFilter("id.invoiceno",invoiceNo, true, false));
		model.getTableJpaContainer().addContainerFilter(filter1);

		//PARSING RECAPNO
//		Filter[] filterRecaps = new Filter[2];			
//		filterRecaps[0] = new And(new Like("recapno", "24288"));
//		filterRecaps[1] = new And(new Like("recapno", "24287"));
		String recapNo = view.getFieldSearchByRekap().getValue().toString().trim();
		if (! recapNo.trim().equals("")){
			String [] data = recapNo.split("\\,");			
			Filter[] filterRecaps = new Filter[data.length];						
			for (int i=0; i<data.length; i++){			
				filterRecaps[i] = new And(new Like("recapno", data[i].trim()));
			}
			
			Filter filter2 = new Or(filterRecaps);
			model.getTableJpaContainer().addContainerFilter(filter2);
			
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
				Filter filter3 = new And(new SimpleStringFilter("id.division", strDivision, true, false));
				model.getTableJpaContainer().addContainerFilter(filter3);
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
				model.getTableJpaContainer().addContainerFilter(filter4);
			} 
		}catch(Exception ex){}
		
		
		
		//LUNAS BELUM LUNAS
		Filter filter7=null;
		try{
			if (view.getFieldSearchComboTerkirim().getValue().equals("B")){
				filter7 = new And(new Compare.Equal("terkirim", false)); 					
			} else if (view.getFieldSearchComboTerkirim().getValue().equals("K")){
				filter7 = new And(new Compare.Equal("terkirim", true)); 						
			}
			
			
			if (! view.getFieldSearchComboTerkirim().getValue().equals("S")){
				model.getTableJpaContainer().addContainerFilter(filter7);
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
				model.getTableJpaContainer().addContainerFilter(filter8);
			}	
			if (tglInvoiceToLong !=0){
				model.getTableJpaContainer().addContainerFilter(filter9);
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

    ConfirmDialog.Listener konfirmDialogKirimListener = new ConfirmDialog.Listener() {					
		//@Override
		public void onClose(ConfirmDialog dialog) {
			
            if (dialog.isConfirmed()) {
                // Confirmed to continue
            	tandaiKirim();
            } else {
            // User did not confirm
            }
		}
	};

    ConfirmDialog.Listener konfirmDialogBatalKirimListener = new ConfirmDialog.Listener() {					
		//@Override
		public void onClose(ConfirmDialog dialog) {
			
            if (dialog.isConfirmed()) {
                // Confirmed to continue
            	batalKirim();
            } else {
            // User did not confirm
            }
		}
	};
	
	public void tandaiKirim(){
	
		try{
			int nomorUrut=0;
			Collection itemIds =  model.getTableJpaContainer().getItemIds();
			List<Object> listObject = new ArrayList<Object>();

			for (Object itemId: itemIds){
				
				Arinvoice item = new Arinvoice();
				item = model.getTableJpaContainer().getItem(itemId).getEntity();

				if (item.getSelected().getValue()==true && item.isLunas() == false ){
					
					//UPDATE INVOICE
					item.setTerkirim(true);
					model.getArInvoiceService().updateObject(item);
					nomorUrut +=1;
					//REFRESH SATU PERSATU
					listObject.add(itemId);
					
				}
			
			}
			//REFRESH MANUAL
			for (Object itemId: listObject){
				model.getTableJpaContainer().refreshItem(itemId);
			}
			
			view.getTable().refreshRowCache();
			if (nomorUrut>0){
				Notification.show("Sejumlah " + nomorUrut + " Nota berhasil diberi tanda TERKIRIM!");
			} else {
				Notification.show("TIDAK ADA FAKTUR YANG DITANDAI TERKIRIM");				
			}
			view.setDisplayFooter();
			
		} catch(Exception ex){
			Notification.show("Error Pengiriman!!");		                        		
			
		}
	}

	public void batalKirim(){
		
		try{
			int nomorUrut=0;
			Collection itemIds =  model.getTableJpaContainer().getItemIds();
			List<Object> listObject = new ArrayList<Object>();

			for (Object itemId: itemIds){
				
				Arinvoice item = new Arinvoice();
				item = model.getTableJpaContainer().getItem(itemId).getEntity();

				if (item.getSelected().getValue()==true && item.isLunas() == false ){
					
					//UPDATE INVOICE
					item.setTerkirim(false);
					model.getArInvoiceService().updateObject(item);
					nomorUrut +=1;
					//REFRESH SATU PERSATU
					listObject.add(itemId);
					
				}
			
			}
			//REFRESH MANUAL
			for (Object itemId: listObject){
				model.getTableJpaContainer().refreshItem(itemId);
			}
			
			view.getTable().refreshRowCache();
			if (nomorUrut>0){
				Notification.show("Sejumlah " + nomorUrut + " Nota berhasil DIBATALKAN KIRIM!");
			}else {
				Notification.show("TIDAK ADA FATUR YANG DIBATALKAN");				
			}
			view.setDisplayFooter();
			
		} catch(Exception ex){
			Notification.show("Error Pengiriman!!");		                        		
			
		}
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
			model.item = model.getTableJpaContainer().getItem(itemId).getEntity();			
			itemTableSelected = view.getTable().getItem(itemId);
			//biar checked
			model.getTableJpaContainer().getItem(itemId).getEntity().getSelected().setReadOnly(false);
			if (model.getItem().getSelected().getValue()==true){
				model.getTableJpaContainer().getItem(itemId).getEntity().getSelected().setValue(false);
			} else {
				model.getTableJpaContainer().getItem(itemId).getEntity().getSelected().setValue(true);				
			}
			//JIKA LUNAS TIDAK BOLEH DI CHECK
			if (model.getItem().isLunas()==true){
				Notification.show("FAKTUR " + item.getId() + " SUDAH LUNAS!!");
				model.getTableJpaContainer().getItem(itemId).getEntity().getSelected().setValue(false);				
			}
			
			view.setDisplayFooter();
			
			boolean entitySelected = item != null;
		} catch (Exception ex){}
		
		// modify visibility of form and delete button if an item is selected
		
	}
	
	@Override
	public void itemClick(ItemClickEvent event) {
		// TODO Auto-generated method stub

//		try{
//			Object itemId = event.getItemId();
//			model.item = model.getTableJpaContainer().getItem(itemId).getEntity();			
//			itemTableSelected = view.getTable().getItem(itemId);
//			
//			model.getTableJpaContainer().getItem(itemId).getEntity().getSelected().setReadOnly(false);
//			if (model.getItem().getSelected().getValue()==true){
//				model.getTableJpaContainer().getItem(itemId).getEntity().getSelected().setValue(false);
//			} else {
//				model.getTableJpaContainer().getItem(itemId).getEntity().getSelected().setValue(true);				
//			}
//			view.setDisplayFooter();
//			
//			boolean entitySelected = item != null;
//		} catch (Exception ex){}
//		
//		// modify visibility of form and delete button if an item is selected
		
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

	public void windowRecapSelectShow(){
		view.buildWindowRecapSelect();
		initListenerWindowRecapSelect();
//		view.setFormButtonAndTextState();		
	}
	
	public void windowRecapSelectClose(){
		view.destroyWindowRecapSelect();
		//Ambil data Masukin ke Kolom recap no
		String recapCollection = "";
		Collection itemIds = view.getRecapSelectModel().getBeanItemContainerItemHeader().getItemIds();
		for (Object itemId: itemIds){
			Arinvoice item = new Arinvoice();
			item = view.getRecapSelectModel().getBeanItemContainerItemHeader().getItem(itemId).getBean();
			if (item.getSelected().getValue()==true){
				recapCollection += item.getRecapno() + ", " ;
			}
		}
		
		view.getFieldSearchByRekap().setValue(recapCollection);
	}
	
	
}
