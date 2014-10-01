package org.dimas.finance.cashandbank.penerimaankas;


import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("servial")
@Theme("vaadin_theme")
public class BeanFieldGroupUI extends UI{

	@VaadinServletConfiguration(productionMode=false, ui=BeanFieldGroupUI.class)
	public static class Servlet extends VaadinServlet{
		
	}

	@Override
	protected void init(VaadinRequest request) {
		// TODO Auto-generated method stub
		VerticalLayout content = new VerticalLayout();

		BeanFieldGroupValidation fieldGroupView1 = new BeanFieldGroupValidation();
		
		content.addComponent(fieldGroupView1);
		setContent(content);
		
	}

}
