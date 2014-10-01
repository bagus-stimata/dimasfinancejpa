package org.dimas.finance.cashandbank.penerimaankas;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

public class BeanFieldGroupFormSample extends FormLayout {
	@PropertyId("id")
	private TextField theId = new TextField("ID");
	private TextField firstName = new TextField("FIRST NAME");
	private TextField lastName = new TextField("LAST NAME");
	private TextField email = new TextField("EMAIL");

	private Button btnCommit = new Button("commit");
	
	
	public BeanFieldGroupFormSample(){
//		FormLayout inputLayout = new FormLayout();
		HorizontalLayout inputLayout = new HorizontalLayout();
		inputLayout.addComponent(theId);
		inputLayout.addComponent(firstName);
		inputLayout.addComponent(lastName);
		inputLayout.addComponent(email);
		inputLayout.addComponent(btnCommit);
		addComponent(inputLayout);
		
		
	}


	public Button getBtnCommit() {
		return btnCommit;
	}


	public void setBtnCommit(Button btnCommit) {
		this.btnCommit = btnCommit;
	}
	
}
