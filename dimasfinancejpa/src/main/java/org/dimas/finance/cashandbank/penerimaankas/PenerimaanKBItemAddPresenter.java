package org.dimas.finance.cashandbank.penerimaankas;

import java.util.Collection;

import org.dimas.finance.model.Bkbdetail;
import org.dimas.finance.model.BkbdetailPK;
import org.dimas.finance.model.Bkbtranstype;
import org.dimas.finance.model.Modul;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table.HeaderClickEvent;
import com.vaadin.ui.Table.HeaderClickListener;

public class PenerimaanKBItemAddPresenter implements ClickListener{
	private PenerimaanKBItemAddModel model;
	private PenerimaanKBItemAddView view;
	
	public PenerimaanKBItemAddPresenter(PenerimaanKBItemAddModel model, PenerimaanKBItemAddView view){
		this.model = model;
		this.view = view;
		
		initListener();
	}
	
	public void initListener(){
		view.getBtnAdd().addClickListener(this);
		view.getBtnReset().addClickListener(this);
		view.getBtnClose().addClickListener(this);
		
		ValueChangeListener listenerComboTranstype = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				Bkbtranstype transtype = new Bkbtranstype();
				try{
					transtype  = model.getBeanItemContainerTranstype()
							.getItem(view.getComboTranstype().getValue()).getBean();
				} catch(Exception ex){}
				if (transtype.getId() !=null){					
					//COMBO ACCOUNT
					view.getComboAccount().select(transtype.getAccountBean());
					//COMBO DEBET KREDIT
					if (transtype.getAccountBean().getTipedebetkredit().equals("D")){						
						view.getComboDebetkredit().select("D");
					} else {
						view.getComboDebetkredit().select("K");
						
					}
					//CEK GL OR NOt
					if (transtype.getAccountBean().getId() !=null){
						view.getCheckGl().setReadOnly(false);
						view.getCheckGl().setValue(true);
						view.getCheckGl().setReadOnly(true);
					}
				}
				
			}
		};
		view.getComboTranstype().setImmediate(true);
		view.getComboTranstype().addValueChangeListener(listenerComboTranstype);
		
		
	}
	
	public void initDisplay(){
		//SUDAH DIHANDLE OLEH VIEW
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
		if (event.getButton() == view.getBtnAdd()) {
			//1. MASUKKAN KE TABLE
			//2. NEW ITEM ADD LAGI
		} else if (event.getButton() == view.getBtnReset()) {
			//SAMA DENGAN addItem
			addItem();
			view.focustIdOrDesc();
		} else if (event.getButton() == view.getBtnClose()){
			
		}
		
		
	}
	public void addItem(){
		model.bkbdetail = new Bkbdetail();
		BkbdetailPK id = new BkbdetailPK();
		
		model.bkbdetail.setId(id);
		model.bkbdetail.setDescription("");
		model.getBinderBkbdetail().setItemDataSource(model.bkbdetail);
		view.bindAndBuildFieldGroupComponent();		
	}
	
	public void selectForm(){
		
	}
	

}
