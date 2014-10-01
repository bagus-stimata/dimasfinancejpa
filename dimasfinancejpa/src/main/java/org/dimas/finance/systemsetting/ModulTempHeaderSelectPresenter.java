package org.dimas.finance.systemsetting;

import org.dimas.finance.model.Modul;
import org.dimas.finance.model.ModulTempHeader;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ModulTempHeaderSelectPresenter implements ClickListener, ValueChangeListener{
	private ModulTempHeaderSelectModel model;
	private ModulTempHeaderSelectView view;
	
	public ModulTempHeaderSelectPresenter(ModulTempHeaderSelectModel model, ModulTempHeaderSelectView view){
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
			
			model.modulTempHeader = new ModulTempHeader();
			model.modulTempHeader = model.getBeanItemContainerModulTempHeader().getItem(itemId).getBean();
			model.getBinderModulTempHeader().setItemDataSource(model.modulTempHeader);
			
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
	
	public int searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.getBeanItemContainerModulTempHeader().removeAllContainerFilters();
		
		//2. Baru Kasih Filter Lagi
		String theId = view.getFieldSearchById().getValue().toString().trim();
		String theTitle = view.getFieldSearchByTemplateName().getValue().toString().trim();
		Filter filter1 = new SimpleStringFilter("id", theId, true, false);
		Filter filter2 = new Or(new SimpleStringFilter("templateName", theTitle, true, false));
		model.getBeanItemContainerModulTempHeader().addContainerFilter(filter1);
		model.getBeanItemContainerModulTempHeader().addContainerFilter(filter2);
		
		//3. Refresh TABLE
		view.getTable().refreshRowCache();
		//4. Focus KE TABLE
		view.getTable().focus();
		
		return 0;
		
	}
	
	public void selectForm(){
		
	}
	

}
