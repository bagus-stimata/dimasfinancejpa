package org.dimas.finance.cashandbank.penerimaankas;

import org.eclipse.jdt.internal.core.SetContainerOperation;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PenerimaanSetoranView extends CustomComponent{
	private PenerimaanSetoranModel model;
	public PenerimaanSetoranView(PenerimaanSetoranModel model){
		this.model = model;
	}
	
	public void buildView(){
		VerticalLayout content = new VerticalLayout();
		content.addComponent(new Label("Coba deh didalem"));
		
		setCompositionRoot(content);
	}
	
}
