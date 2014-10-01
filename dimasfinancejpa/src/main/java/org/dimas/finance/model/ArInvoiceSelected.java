package org.dimas.finance.model;

import com.vaadin.ui.CheckBox;

public class ArInvoiceSelected extends Arinvoice{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CheckBox selected = new CheckBox();

	public CheckBox getSelected() {
		return selected;
	}

	public void setSelected(CheckBox selected) {
		this.selected = selected;
	}
	
}
