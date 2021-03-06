package org.dimas.finance.ar;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class FormForWindowOne extends CustomComponent {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private AbsoluteLayout absoluteLayout_2;
	@AutoGenerated
	private Button btnClose;
	@AutoGenerated
	private TextField fieldAlamat;
	@AutoGenerated
	private TextField fieldNama;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public FormForWindowOne() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		ClickListener listenerClose = new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				System.out.println("Saya klik dari FormForWindowOne");
				
			}
		};
		btnClose.addClickListener(listenerClose);
		
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// absoluteLayout_2
		absoluteLayout_2 = buildAbsoluteLayout_2();
		mainLayout.addComponent(absoluteLayout_2,
				"top:0.0px;right:3.0px;bottom:-3.0px;left:0.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private AbsoluteLayout buildAbsoluteLayout_2() {
		// common part: create layout
		absoluteLayout_2 = new AbsoluteLayout();
		absoluteLayout_2.setImmediate(false);
		absoluteLayout_2.setWidth("100.0%");
		absoluteLayout_2.setHeight("100.0%");
		
		// fieldNama
		fieldNama = new TextField();
		fieldNama.setCaption("Nama");
		fieldNama.setImmediate(false);
		fieldNama.setWidth("-1px");
		fieldNama.setHeight("-1px");
		absoluteLayout_2.addComponent(fieldNama, "top:20.0px;left:3.0px;");
		
		// fieldAlamat
		fieldAlamat = new TextField();
		fieldAlamat.setCaption("Alamat");
		fieldAlamat.setImmediate(false);
		fieldAlamat.setWidth("-1px");
		fieldAlamat.setHeight("-1px");
		absoluteLayout_2.addComponent(fieldAlamat, "top:44.0px;left:3.0px;");
		
		// btnClose
		btnClose = new Button();
		btnClose.setCaption("Close Window");
		btnClose.setImmediate(true);
		btnClose.setWidth("-1px");
		btnClose.setHeight("-1px");
		absoluteLayout_2.addComponent(btnClose, "top:100.0px;left:10.0px;");
		
		return absoluteLayout_2;
	}

	public AbsoluteLayout getMainLayout() {
		return mainLayout;
	}

	public void setMainLayout(AbsoluteLayout mainLayout) {
		this.mainLayout = mainLayout;
	}

	public AbsoluteLayout getAbsoluteLayout_2() {
		return absoluteLayout_2;
	}

	public void setAbsoluteLayout_2(AbsoluteLayout absoluteLayout_2) {
		this.absoluteLayout_2 = absoluteLayout_2;
	}

	public Button getBtnClose() {
		return btnClose;
	}

	public void setBtnClose(Button btnClose) {
		this.btnClose = btnClose;
	}

	public TextField getFieldAlamat() {
		return fieldAlamat;
	}

	public void setFieldAlamat(TextField fieldAlamat) {
		this.fieldAlamat = fieldAlamat;
	}

	public TextField getFieldNama() {
		return fieldNama;
	}

	public void setFieldNama(TextField fieldNama) {
		this.fieldNama = fieldNama;
	}

	
	
}
