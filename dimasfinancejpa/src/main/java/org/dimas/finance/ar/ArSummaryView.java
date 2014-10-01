package org.dimas.finance.ar;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.Reindeer;

public class ArSummaryView extends CustomComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArSummaryModel model;
	
	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private ComboBox fieldSearchComboArea = new ComboBox("AREA:");	
	private ComboBox fieldSearchComboDivisi = new ComboBox("DIVISI:");	
	private ComboBox fieldSearchComboCustomer = new ComboBox("CUSTOMER:");	
	private ComboBox fieldSearchComboSalesman = new ComboBox("SALESMAN:");	
	
	private DateField fieldSearchByDateInvoiceFrom;
	private DateField fieldSearchByDateInvoiceTo;
	
	private Button btnSearch;
	
	//Panel
	private Panel panelUtama;
	private Panel panelTop;
	private Panel panelTabel;
	private Panel panelForm;
	
	public ArSummaryView(ArSummaryModel model){
		this.model = model;
		initFactory();
		buildView();
		
	}
	public void initFactory(){
		table = new Table("Table: ") {
		    @Override
		    protected String formatPropertyValue(Object rowId,
		            Object colId, Property property) {
		        // Format by property type
		        if (property.getType() == Date.class && property.getValue() != null) {
		            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		            return df.format((Date)property.getValue());
		        }
		        
		        if (property.getType()==Boolean.class){
		        	if ((Boolean) property.getValue()==true) {
		        		return "OK";
		        	} else {
		        		return "-";
		        	}
		        }
		        return super.formatPropertyValue(rowId, colId, property);
		    }
		};			
	}
	public void buildView(){
		content.setSizeFull();
		
		//Inisialisasi Panel 
		setSizeFull();
		panelUtama = new Panel(getCaption());
		panelUtama.setSizeFull();

		panelTop = new Panel();
		panelTabel = new Panel();
		panelTabel.setSizeFull();
		panelForm = new Panel();

		//Init Komponen atas
		
		fieldSearchComboArea.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboArea.setWidth("150px");

		fieldSearchComboDivisi.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboDivisi.setWidth("150px");

		fieldSearchComboSalesman.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboSalesman.setWidth("150px");

		fieldSearchComboCustomer.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboCustomer.setWidth("150px");
		
		fieldSearchByDateInvoiceFrom = new DateField("Tgl Invoice Mulai");
		fieldSearchByDateInvoiceFrom.setDateFormat("dd-MM-yyyy");
//		fieldSearchByDateInvoiceFrom.setWidth("100px");
		fieldSearchByDateInvoiceFrom.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByDateInvoiceTo = new DateField("Sampai dengan");
//		fieldSearchByDateInvoiceTo.setWidth("100px");
		fieldSearchByDateInvoiceTo.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByDateInvoiceTo.setDateFormat("dd-MM-yyyy");

		
	    
		//Init komponen tengah
		table.setSizeFull();
		table.setSelectable(true);
		table.setImmediate(true);
		table.setBuffered(false);
		table.setFooterVisible(true);
		
		//Deklarasi Button dan Listener	
		btnSearch = new Button("Search");
		btnSearch.setStyleName(Reindeer.BUTTON_SMALL);
		
		//Init komponen bawah
		//DEKLARASI LAYOUT
		//KOMPONEN ATAS
		HorizontalLayout layoutTop = new HorizontalLayout();		
	
		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
		verticalSplitPanel.setSizeFull();		
		verticalSplitPanel.setSplitPosition(100);

		layoutTop.addComponent(fieldSearchComboArea);
		layoutTop.addComponent(fieldSearchComboDivisi);
		layoutTop.addComponent(fieldSearchComboSalesman);
		layoutTop.addComponent(fieldSearchComboCustomer);
		
		layoutTop.addComponent(fieldSearchByDateInvoiceFrom);
		layoutTop.addComponent(fieldSearchByDateInvoiceTo);
		
		HorizontalLayout horBut = new HorizontalLayout();
		
		layoutTop.addComponent(btnSearch);
		layoutTop.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
		
		panelTop.setContent(layoutTop);
		
		panelTabel.setContent(table);
		
		HorizontalLayout layoutFooter = new HorizontalLayout();
		layoutFooter.setSpacing(true);

		panelForm.setContent(layoutFooter);
		
		verticalSplitPanel.setFirstComponent(panelTabel);		
		verticalSplitPanel.setSecondComponent(panelForm);

		content.addComponent(new Label("***"));
		content.addComponent(panelTop);
		content.addComponent(verticalSplitPanel);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		
		content.setExpandRatio(verticalSplitPanel, 1);
		
	}
	
}
