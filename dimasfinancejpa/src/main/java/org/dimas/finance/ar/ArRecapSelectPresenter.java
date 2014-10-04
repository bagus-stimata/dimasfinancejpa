package org.dimas.finance.ar;

import java.util.Collection;
import java.util.Date;

import org.dimas.finance.model.Arinvoice;
import org.dimas.finance.model.Bbankheader;
import org.dimas.finance.model.Bkbheader;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.Modul;
import org.dimas.finance.model.ModulTempHeader;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table.HeaderClickEvent;
import com.vaadin.ui.Table.HeaderClickListener;

public class ArRecapSelectPresenter implements ClickListener, ValueChangeListener{
	private ArRecapSelectModel model;
	private ArRecapSelectView view;
	
	public ArRecapSelectPresenter(ArRecapSelectModel model, ArRecapSelectView view){
		this.model = model;
		this.view = view;
		
		initListener();
	}
	
	public void initListener(){
		view.getBtnSearch().addClickListener(this);
		view.getBtnSelect().addClickListener(this);

		view.getTable().addValueChangeListener(this);
	
		HeaderClickListener listenerHeaderTableUtama = new HeaderClickListener() {			
			@Override
			public void headerClick(HeaderClickEvent event) {
				// TODO Auto-generated method stub
				
				try{
					if (event.getPropertyId().equals("selected")){
						if (model.isSelectAllInvoice()==true) {
							
							view.getTable().setColumnHeader("selected", "<input type='checkbox'  checked />");		
							model.setSelectAllInvoice(false);
							
							Collection itemIds = model.getBeanItemContainerItemHeader().getItemIds();
							for (Object itemId: itemIds){
								model.getBeanItemContainerItemHeader().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getBeanItemContainerItemHeader().getItem(itemId).getBean().getSelected().setValue(true);
							}
							
						} else {
							
							view.getTable().setColumnHeader("selected", "<input type='checkbox' />");		
							model.setSelectAllInvoice(true);
							
							Collection itemIds = model.getBeanItemContainerItemHeader().getItemIds();
							for (Object itemId: itemIds){
								model.getBeanItemContainerItemHeader().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getBeanItemContainerItemHeader().getItem(itemId).getBean().getSelected().setValue(false);
							}
							
						}	
						//KASIH SELEKSI >> buat SELECTED READONLY(TRUE) LAGI				
						view.setDisplayTableFooter();

					}
				} catch(Exception ex){}
			}
				
		};
		view.getTable().addHeaderClickListener(listenerHeaderTableUtama);
		
		
		
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
//		view.getFormLayout().setVisible(entitySelected); //BUTTON SELECTT ALWAYS SHOW
		
		if (entitySelected) {
			
			model.itemHeader = new Arinvoice();
			model.itemHeader = model.getBeanItemContainerItemHeader().getItem(itemId).getBean();
			model.getBinderItemHeader().setItemDataSource(model.getItemHeader());
			
		}
		
		view.bindAndBuildFieldGroupComponent();		
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
		if (event.getButton() == view.getBtnSearch()) {
			searchForm();
		} else if (event.getButton() == view.getBtnSelect()){			
			selectForm();
		}
		
	}
	public void searchForm(){
		model.reload();
		model.getBeanItemContainerItemHeader().removeAllContainerFilters();
		model.getBeanItemContainerItemHeader().removeAllItems();
		//2. Baru Kasih Filter Lagi
		String theId = view.getFieldSearchById().getValue().toString().trim();

		Division div = new Division();
		String strDivision = "";
		try{
			div = model.getBeanItemContainerDivision().getItem(view.getComboSearchByDivision().getValue()).getBean();
			strDivision = div.getId();		
		} catch(Exception ex){}
		//TANGGAL INVOICE
		long tglTransFromLong = 0;
		long tglTransToLong = 0;
		try{
			tglTransFromLong = view.getDateFieldSearchByTransdateFrom().getValue().getTime();
		} catch (Exception ex){}
		try{
			tglTransToLong = view.getDateFieldSearchByTransdateTo().getValue().getTime();
		} catch(Exception ex){}
		
		if (tglTransFromLong ==0 & tglTransToLong ==0){ 
			model.getBeanItemContainerItemHeader().addAll(model.getArInvoiceService().findAllForRecapSelectArTOTunai("%" + theId + "%", "%" + strDivision + "%" ));
		} else if (tglTransFromLong !=0 & tglTransToLong==0){
			model.getBeanItemContainerItemHeader().addAll(model.getArInvoiceService().findAllForRecapSelectArTOTunai("%" + theId + "%", "%" + strDivision + "%" ,
					new Date(tglTransFromLong)));			
		}else{
			model.getBeanItemContainerItemHeader().addAll(model.getArInvoiceService().findAllForRecapSelectArTOTunai("%" + theId + "%", "%" + strDivision + "%" ,
					new Date(tglTransFromLong), new Date(tglTransToLong)));						
		}
		view.getTable().refreshRowCache();
		
	}
	
	public void selectForm(){
		
	}
	

}
