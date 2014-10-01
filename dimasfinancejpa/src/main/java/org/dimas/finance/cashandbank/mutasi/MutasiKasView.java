package org.dimas.finance.cashandbank.mutasi;

import org.eclipse.jdt.internal.core.SetContainerOperation;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MutasiKasView extends CustomComponent{
	private MutasiKasModel model;
	public MutasiKasView(MutasiKasModel model){
		this.model = model;
	}
	
	public void buildView(){
		VerticalLayout content = new VerticalLayout();
		content.addComponent(new Label("Coba deh didalem"));
		
		setCompositionRoot(content);
	}
	
}
