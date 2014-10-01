package org.dimas.finance.systemsetting.harikerja;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.digester.rss.Item;
import org.dimas.finance.model.Bukugiro;
import org.dimas.finance.model.Division;
import org.dimas.finance.model.SysCalender;
import org.dimas.finance.model.SysCalenderPK;
import org.dimas.finance.model.Sysvar;
import org.dimas.finance.model.modelenum.EnumFormOperationStatus;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class HariKerjaPresenter implements ClickListener, ValueChangeListener{
	private HariKerjaModel model;
	private HariKerjaView view;
	
	public HariKerjaPresenter(HariKerjaModel model, HariKerjaView view){
		this.model = model;
		this.view = view;

		initListener();
		
	}
	
	public void initListener(){
		view.getBtnSimpan().addClickListener(this);
		view.getBtnSwitchKerjaLibur().addClickListener(this);
		
		view.getTable().addValueChangeListener(this);
		
		ValueChangeListener listenerComboDivision = new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				Division divisionBean = new Division();
				String strDivision = null;
				try{
					divisionBean = model.getBeanItemContainerDivision().getItem(view.getComboDivision().getConvertedValue()).getBean();
					strDivision = divisionBean.getId();
				} catch(Exception ex){}
				
				if (strDivision != null){
					fillTableDetail(divisionBean);
				}
			}
		};
		view.getComboDivision().setImmediate(true);
		view.getComboDivision().addValueChangeListener(listenerComboDivision);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		if (event.getButton()==view.getBtnSimpan()){
			 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Perubahan kelender kerja akan mempengaruhi seluruh sistem", "Yakin Simpan?", "Save", "Cancel",
		                new ConfirmDialog.Listener() {
		                    public void onClose(ConfirmDialog dialog) {
		                        if (dialog.isConfirmed()) {
		                            // Confirmed to continue
		                        	saveForm();
		                        } else {
		                        	view.focustIdOrDesc();
		                        }
		                    }
		                });	        
			 commitDialog.setStyleName("dialog");
			 commitDialog.getOkButton().setStyleName("small");
			 commitDialog.getCancelButton().setStyleName("small");
			 //Jangan lupa
			 commitDialog.focus();
			
		}else if (event.getButton() == view.getBtnSwitchKerjaLibur()){
			try{
				if (model.getItem().getId().getDivision() != null){

					model.getItem().setWorkdate(! model.getItem().isWorkdate());				
					model.getTableBeanItemContainer().addItem(model.getItem());
					view.getTable().refreshRowCache();
				}
				
				
			} catch(Exception ex){}
			
			
		}
	
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		Object itemId = event.getProperty().getValue();
				
		model.item = new SysCalender();
		if (itemId != null) {			
			model.item = model.getTableBeanItemContainer().getItem(itemId).getBean();			
		} 
		
	}
	
	
	public void saveForm(){
		Collection itemIds = model.getTableBeanItemContainer().getItemIds();
		for (Object itemId: itemIds){
			SysCalender item = new SysCalender();
			item = model.getTableBeanItemContainer().getItem(itemId).getBean();
			model.getSysCalenderService().updateObject(item);
		}
		Notification.show("Update Selesai");
	}
	
	public void fillTableDetail(Division division){
		model.getTableBeanItemContainer().removeAllContainerFilters();
		model.getTableBeanItemContainer().removeAllItems();
		model.getTableBeanItemContainer().addAll(model.getSysCalenderService().findAllByDivision(division));
		
		view.getTable().setContainerDataSource(model.getTableBeanItemContainer());
		view.getTable().refreshRowCache();
		
		view.setDisplay();
		
	}

	

}
