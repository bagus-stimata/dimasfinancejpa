package org.blitar.bawasda.simhp.otorisasi;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@Theme("runo")
public class UserOtorizeMainView extends CssLayout implements View{
	
	@Override
	public void enter(ViewChangeEvent event) {
		setSizeFull();
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		addComponent(layout);
		
		TabSheet tabsheet = new TabSheet();
		layout.addComponent(tabsheet);
		tabsheet.setSizeFull();
		
		VerticalLayout tab1 = new VerticalLayout();
		UserAccountModel userAccountModel = new UserAccountModel();
		UserAccountView userAccountView = new UserAccountView(userAccountModel);
		userAccountView.setSizeFull();
		new UserAccountPresenter(userAccountModel, userAccountView);
		tab1.addComponent(userAccountView);
		tab1.setSizeFull();

		
//		VerticalLayout tabTest = new VerticalLayout();
//		UserPasswordChangeModel userPasswordChangeModel = new UserPasswordChangeModel();
//		UserPasswordChangeView userPasswordChangeView = new UserPasswordChangeView(userPasswordChangeModel);
//		userPasswordChangeView.setSizeFull();
//		new UserPasswordChangePresenter(userPasswordChangeModel, userPasswordChangeView);
//		tabTest.addComponent(userPasswordChangeView);
//		tabTest.setSizeFull();

		tabsheet.addTab(tab1, "Pengguna Sistem",  null);
//		tabsheet.addTab(tabTest, "Password Change Test",  null);

		
	}
	

}
