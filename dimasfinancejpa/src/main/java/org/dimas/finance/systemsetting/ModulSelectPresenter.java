package org.dimas.finance.systemsetting;

import java.util.Collection;

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

public class ModulSelectPresenter implements ClickListener, ValueChangeListener{
	private ModulSelectModel model;
	private ModulSelectView view;
	
	public ModulSelectPresenter(ModulSelectModel model, ModulSelectView view){
		this.model = model;
		this.view = view;
		
		initListener();
	}
	
	public void initListener(){
		view.getBtnSearch().addClickListener(this);
		view.getBtnSelect().addClickListener(this);

		view.getTable().addValueChangeListener(this);
		
		HeaderClickListener listenerHeaderTableModul = new HeaderClickListener() {			
			@Override
			public void headerClick(HeaderClickEvent event) {
				// TODO Auto-generated method stub
				
				try{
					if (event.getPropertyId().equals("selected")){
						if (model.isSelectAllInvoice()==true) {
							
							view.getTable().setColumnHeader("selected", "<input type='checkbox'  checked />");		
							model.setSelectAllInvoice(false);
							
							Collection itemIds = model.getBeanItemContainerModul().getItemIds();
							for (Object itemId: itemIds){
								model.getBeanItemContainerModul().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getBeanItemContainerModul().getItem(itemId).getBean().getSelected().setValue(true);
							}
							
						} else {
							
							view.getTable().setColumnHeader("selected", "<input type='checkbox' />");		
							model.setSelectAllInvoice(true);
							
							Collection itemIds = model.getBeanItemContainerModul().getItemIds();
							for (Object itemId: itemIds){
								model.getBeanItemContainerModul().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getBeanItemContainerModul().getItem(itemId).getBean().getSelected().setValue(false);
							}
							
						}	
						//KASIH SELEKSI >> buat SELECTED READONLY(TRUE) LAGI				
						view.setDisplayTableFooter();

					}
				} catch(Exception ex){}
			}
				
		};
		view.getTable().addHeaderClickListener(listenerHeaderTableModul);
		
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
		view.getFormLayout().setVisible(entitySelected);
		
		if (entitySelected) {
			
			model.modul = new Modul();
			model.modul = model.getBeanItemContainerModul().getItem(itemId).getBean();
			model.getBinderModul().setItemDataSource(model.modul);
			
		}
		
		view.bindAndBuildFieldGroupComponent();		
		
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
		if (event.getButton() == view.getBtnSearch()) {
			searchForm();
		} 
//		else if (event.getButton() == view.getBtnSelect()){			
//			selectForm();
//		}
		
	}
	
	public int searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.getBeanItemContainerModul().removeAllContainerFilters();
		
		//2. Baru Kasih Filter Lagi
		String theId = view.getFieldSearchById().getValue().toString().trim();
		String theGroup = view.getFieldSearchByGroup().getValue().toString().trim();
		String theTitle = view.getFieldSearchByTitle().getValue().toString().trim();
		Filter filter1 = new SimpleStringFilter("id", theId, true, false);
		Filter filter2 = new Or(new SimpleStringFilter("modulGroup", theGroup, true, false));
		Filter filter3 = new Or(new SimpleStringFilter("title", theTitle, true, false));
		model.getBeanItemContainerModul().addContainerFilter(filter1);
		model.getBeanItemContainerModul().addContainerFilter(filter2);
		model.getBeanItemContainerModul().addContainerFilter(filter3);
		
		//3. Refresh TABLE
		view.getTable().refreshRowCache();
		//4. Focus KE TABLE
		view.getTable().focus();
		
		return 0;
		
	}
	
	public void selectForm(){
		
	}
	

}
