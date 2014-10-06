package org.blitar.bawasda.simhp.dummy;

import com.vaadin.ui.CustomComponent;

public class TestView extends CustomComponent {
	private TestModel model;
	
	public TestView(TestModel model){
		this.model = model;
	}
}
