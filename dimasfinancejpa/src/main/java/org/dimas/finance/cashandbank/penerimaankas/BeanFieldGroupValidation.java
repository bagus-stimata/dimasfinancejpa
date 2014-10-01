package org.dimas.finance.cashandbank.penerimaankas;



import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class BeanFieldGroupValidation extends CustomComponent{
	private TextField outId = new TextField("ID");
	private TextField outFirstName = new TextField("FIRST NAME");
	private TextField outLastName = new TextField("LAST NAME");
	private TextField outEmail = new TextField("EMAIL");
	
	private BeanFieldGroupFormSample myForm = new BeanFieldGroupFormSample();
	public BeanFieldGroupValidation(){
		buildView();
	}
	public void buildView(){
		VerticalLayout content = new VerticalLayout();
		
		final Person person = new Person();
		person.setId(2);
		person.setFirstName("Bagus Winarno");	
		person.setLastName("Encapsulation Form");
		person.setEmail("bagus.stimata@gmail.com");
		
		final BeanFieldGroup<Person> binder = new BeanFieldGroup<Person>(Person.class);
		binder.setItemDataSource(person);
		binder.setBuffered(true);
		
		binder.bindMemberFields(myForm);
		myForm.getBtnCommit().addClickListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				try {
					binder.commit();
					
					outId.setValue(String.valueOf(person.getId()));
					outFirstName.setValue(person.getFirstName());
					outLastName.setValue(person.getLastName());
					outEmail.setValue(person.getEmail());
					
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		
		content.addComponent(myForm);
		
		FormLayout outputLayout = new FormLayout();
		outputLayout.addComponent(outId);
		outputLayout.addComponent(outFirstName);
		outputLayout.addComponent(outLastName);
		outputLayout.addComponent(outEmail);
		
		content.addComponent(outputLayout);
		
		
		setCompositionRoot(content);
	}
}
