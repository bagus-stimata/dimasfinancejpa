package org.dimas.finance.cashandbank.pengeluarankas;

import org.eclipse.jdt.internal.core.SetContainerOperation;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class PengeluaranKasBesarView extends CustomComponent{
	private PengeluaranKasBesarModel model;
	public PengeluaranKasBesarView(PengeluaranKasBesarModel model){
		this.model = model;
	}
	
	public void buildView(){
		VerticalLayout content = new VerticalLayout();
		content.addComponent(new Label("Coba deh didalem"));
		
		setCompositionRoot(content);
	}
	
}
