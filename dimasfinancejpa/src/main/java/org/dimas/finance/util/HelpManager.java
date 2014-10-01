/**
 * DISCLAIMER
 * 
 * The quality of the code is such that you should not copy any of it as best
 * practice how to build Vaadin applications.
 * 
 * @author jouni@vaadin.com
 * 
 */

package org.dimas.finance.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dimas.finance.DashboardView;
import org.dimas.finance.transaction.SalesView;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class HelpManager implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private UI ui;
    private List<HelpOverlay> overlays = new ArrayList<HelpOverlay>();

    public HelpManager(UI ui) {
        this.ui = ui;
    }

    public void closeAll() {
        for (HelpOverlay overlay : overlays) {
            overlay.close();
        }
        overlays.clear();
    }

    public void showHelpFor(int tipeHelp){
    	//Shortcut key help
    	if (tipeHelp==1){
            addOverlay(
                    "Help for Shortcut Key: ",
                    "<p>Native Object Vaadin Application (NOVA) General Ledger yang didistribusikan untuk CV. Dimas Indonesia</p>"
                    + "<p>Informasi lebih lanjut pada Bagus Winarno: bagus_download@yahoo.co.id atau +62821-43574-692 "
                    + "atau pada site kami <a href=\"http://nova-erp.com\">Nova ERP Software</a>.</p>"
                    + "<p>Mintalah username or password pada administrator program pada IT CV. Dimas Indonesia untuk login</p>",
                    "login");
    		
    	}
    }
    
    public void showHelpFor(View view) {
        // showHelpFor(view.getClass());
    }

    public void showHelpFor(Class<? extends View> view) {
         if (view == DashboardView.class) {
        
         } else if (view == SalesView.class) {
         addOverlay(
         "Add new data sets",
         "You can add new data sets to the graph by choosing a title from the combo box and clicking \"Add\".",
         "timeline-add");
         addOverlay("Clear graph", "Clear all data sets from the graph",
         "timeline-clear");
         addOverlay(
         "Browse",
         "The Timeline component allows you to browse through and zoom the data sets infinitely.",
         "timeline-browse").center();
//         } else if (view == TransactionsView.class) {
//         addOverlay(
//         "Unlimited Data",
//         "You can scroll through any number of rows in the table with blazing speed",
//         "table-lazy").center();
//         addOverlay(
//         "Filter",
//         "Live filter the table contents (in this demo you can only filter the country field",
//         "table-filter");
//         addOverlay(
//         "Create a report",
//         "You can select some rows from the table, and then either drag them over to the \"Reports\" tab in the sidebar or click this button or open the context menu for the items and select \"Create Report\"",
//         "table-rows");
//         } else if (view == ScheduleView.class) {
//         addOverlay("Drag'n'drop",
//         "You can drag'n'drop the events to change the schedule. You can also click on the events to get more information of the event.",
//         "event").center();
         } 
    }
    
    
    public HelpOverlay addOverlay(String caption, String text, String style) {
        HelpOverlay o = new HelpOverlay();
        o.setCaption(caption);
        o.addComponent(new Label(text, ContentMode.HTML));
        o.setStyleName(style);
        // ui.addWindow(o);
        overlays.add(o);
        return o;
    }
    
    public HelpOverlay addOverlay(int enumHelpOverlayTipe, String caption, String text, String style) {
    	if (enumHelpOverlayTipe==1) {
    		if (caption==null)
    			caption = ":::Help Shortcut Key:::";
    		if (text==null)
	    		text = 	  "<p>PENAMBAHAN DAN EDIT DATA</p>"
	    				+ "<ul>"
	    				+ "<li>Penambahan Baru --> INSERT</li>"
	    				+ "<li>Simpan Data --> ALT+S</li>"
	    				+ "<li>Batal Simpan(discard) --> ESC atau ALT+C</li>"
	    				+ "<li>Menuju ke Isian ID --> F2</li>"
	    				+ "<li>Menuju ke Tabel --> ESC</li>"
	    				+ "<li>Hapus Data --> SHIFT+DELETE</li>"	
	    				+ "</ul>"
	                    + "<p>PENCARIAN</>"
	    				+ "<ul>"
	    				+ "<li>Isian Pencarian --> F3</li>"
	    				+ "<li>Mulai Cari --> F4</li>"
	    				+ "<li>Menuju ke Tabel --> ESC</li>"
	    				+ "</ul>"
	    				
	                    + "<p>Informasi lebih lanjut pada Bagus Winarno: bagus_download@yahoo.co.id atau +62821-43574-692</p>";
    		if (style==null)
    			style = "login";
    	}
    	
        HelpOverlay o = new HelpOverlay();
        o.setCaption(caption);
        o.addComponent(new Label(text, ContentMode.HTML));
        o.setStyleName(style);
        // ui.addWindow(o);
        overlays.add(o);
        return o;
    }
   
}
