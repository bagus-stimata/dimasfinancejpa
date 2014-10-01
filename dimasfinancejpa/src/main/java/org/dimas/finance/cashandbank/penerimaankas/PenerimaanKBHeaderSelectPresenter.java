package org.dimas.finance.cashandbank.penerimaankas;

import java.util.Date;

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

public class PenerimaanKBHeaderSelectPresenter implements ClickListener, ValueChangeListener{
	private PenerimaanKBHeaderSelectModel model;
	private PenerimaanKBHeaderSelectView view;
	
	public PenerimaanKBHeaderSelectPresenter(PenerimaanKBHeaderSelectModel model, PenerimaanKBHeaderSelectView view){
		this.model = model;
		this.view = view;
		
		initListener();
	}
	
	public void initListener(){
		view.getBtnSearch().addClickListener(this);
		view.getBtnSelect().addClickListener(this);

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
//		view.getFormLayout().setVisible(entitySelected); //BUTTON SELECTT ALWAYS SHOW
		
		if (entitySelected) {
			
			model.bkbHeader = new Bkbheader();
			model.bkbHeader = model.getBeanItemContainerBkbHeader().getItem(itemId).getBean();
			model.getBinderBkbHeader().setItemDataSource(model.getBkbHeader());
			
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
		model.getBeanItemContainerBkbHeader().removeAllContainerFilters();
		model.getBeanItemContainerBkbHeader().removeAllItems();
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
			model.getBeanItemContainerBkbHeader().addAll(model.getBkbHeaderService().findAll("%" + theId + "%", "%" + strDivision + "%" ));
		} else if (tglTransFromLong !=0 & tglTransToLong==0){
			model.getBeanItemContainerBkbHeader().addAll(model.getBkbHeaderService().findAll("%" + theId + "%", "%" + strDivision + "%" ,
					new Date(tglTransFromLong)));			
		}else{
			model.getBeanItemContainerBkbHeader().addAll(model.getBkbHeaderService().findAll("%" + theId + "%", "%" + strDivision + "%" ,
					new Date(tglTransFromLong), new Date(tglTransToLong)));						
		}
		view.getTable().refreshRowCache();
		
	}
	
	public void selectForm(){
		
	}
	

}
