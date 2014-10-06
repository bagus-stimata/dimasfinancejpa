package org.blitar.bawasda.simhp.util;

import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;

public class AlertDialog extends Window{
	public AlertDialog(){
        VerticalLayout l = new VerticalLayout();
        l.setWidth("400px");
        l.setMargin(true);
        l.setSpacing(true);
        final Window alert = new Window("Unsaved Changes", l);
        alert.setModal(true);
        alert.setResizable(false);
        alert.setDraggable(false);
        alert.addStyleName("dialog");
        alert.setClosable(false);

        Label message = new Label(
                "You have not saved this report. Do you want to save or discard any changes you've made to this report?");
        l.addComponent(message);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setWidth("100%");
        buttons.setSpacing(true);
        l.addComponent(buttons);

        Button discard = new Button("Don't Save");
        discard.addStyleName("small");
        discard.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("hehehe");
           	 alert.close();
                

            }
        });
        buttons.addComponent(discard);
        buttons.setExpandRatio(discard, 1);

        Button cancel = new Button("Cancel");
        cancel.addStyleName("small");
        cancel.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                alert.close();
            }
        });
        buttons.addComponent(cancel);

        Button ok = new Button("Save");
        ok.addStyleName("default");
        ok.addStyleName("small");
        ok.addStyleName("wide");
        ok.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                alert.close();
                Notification
                        .show("The report was saved as a draft",
                                "Actually, the report was just closed and deleted forever. As this is only a demo, it doesn't persist any data.",
                                Type.TRAY_NOTIFICATION);

            }
        });
        buttons.addComponent(ok);
        ok.focus();

        alert.addShortcutListener(new ShortcutListener("Cancel",
                KeyCode.ESCAPE, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                alert.close();
            }
        });
		
	}
}
