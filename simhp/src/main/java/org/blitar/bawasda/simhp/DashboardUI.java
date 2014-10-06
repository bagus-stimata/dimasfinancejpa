/**
 * DISCLAIMER
 * 
 * The quality of the code is such that you should not copy any of it as best
 * practice how to build Vaadin applications.
 * 
 * @author jouni@vaadin.com
 * 
 */

package org.blitar.bawasda.simhp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.blitar.bawasda.simhp.hp.HasilPemeriksaanMainView;
import org.blitar.bawasda.simhp.mastertabel.MasterTabelMainView;
import org.blitar.bawasda.simhp.model.User;
import org.blitar.bawasda.simhp.model.modelenum.EnumUserOtorize;
import org.blitar.bawasda.simhp.otorisasi.UserOtorizeMainView;
import org.blitar.bawasda.simhp.otorisasi.UserPasswordChangeModel;
import org.blitar.bawasda.simhp.otorisasi.UserPasswordChangePresenter;
import org.blitar.bawasda.simhp.otorisasi.UserPasswordChangeView;
import org.blitar.bawasda.simhp.service.UserJpaService;
import org.blitar.bawasda.simhp.service.UserJpaServiceImpl;
import org.blitar.bawasda.simhp.system.SystemMainView;
import org.blitar.bawasda.simhp.util.HelpManager;
import org.blitar.bawasda.simhp.util.HelpOverlay;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
//import com.vaadin.demo.dashboard.data.DataProvider;
//import com.vaadin.demo.dashboard.data.Generator;
//import com.vaadin.demo.dashboard.data.MyConverterFactory;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.AbstractSelect.AcceptItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.DragStartMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Theme("dashboard")
@Title("SimHp Online Kota Blitar")
public class DashboardUI extends UI {

//    DataProvider dataProvider = new DataProvider();

    private static final long serialVersionUID = 1L;

    CssLayout root = new CssLayout();

    VerticalLayout loginLayout;

    CssLayout menu = new CssLayout();
    CssLayout content = new CssLayout();
    
    private User userActive = new User();

    HashMap<String, Class<? extends View>> routes = new HashMap<String, Class<? extends View>>() {
//   HashMap<String, Class<? extends CustomComponentUI>> routes = new HashMap<String, Class<? extends CustomComponentUI>>() {
        {
            put("/dashboard", DashboardView.class);
            put("/surat-tugas", HasilPemeriksaanMainView.class);
            put("/hasil-pemeriksaan", HasilPemeriksaanMainView.class);            
            put("/laporan", HasilPemeriksaanMainView.class);
            put("/master-tabel", MasterTabelMainView.class);
            
	    	put("/setup-system", SystemMainView.class);
	    	put("/setup-user", UserOtorizeMainView.class);            
	    	put("/utilitas", HasilPemeriksaanMainView.class);
            
        }
    };

    HashMap<String, Button> viewNameToMenuButton = new HashMap<String, Button>();

    private Navigator nav;

    private HelpManager helpManager;

    @Override
    protected void init(VaadinRequest request) {
//        getSession().setConverterFactory(new MyConverterFactory());

        helpManager = new HelpManager(this);

        setLocale(Locale.US);

        setContent(root);
        root.addStyleName("root");
        root.setSizeFull();

        // Unfortunate to use an actual widget here, but since CSS generated
        // elements can't be transitioned yet, we must
        Label bg = new Label();
        bg.setSizeUndefined();
        bg.addStyleName("login-bg");
        root.addComponent(bg);

        buildLoginView(true);
        
        initListener();
        

    }
    public void initListener(){
    }
    public void initListenerSubWindow(){
    	ClickListener listenerCancel = new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				destroyWindowChangePassword();
			}
		};
    	userPasswordChangeView.getBtnCancelForm().addClickListener(listenerCancel);
    	
    }

    private void buildLoginView(boolean exit) {
        if (exit) {
            root.removeAllComponents();
        }
        helpManager.closeAll();
        HelpOverlay w = helpManager
                .addOverlay(
                        "Welcome to the SimHp Online Bawasda Kota Blitar",
                        "<p>SimHp Online didistribusikan untuk Bawasda Pemerintah Kota Blitar</p>"
                        + "<p>Informasi lebih lanjut pada Bagus Winarno: bagus.stimata@gmail.com atau +62821-43574-692 "
                        + "<p>Mintalah username or password pada administrator program untuk login</p>",
                        "login");
        w.center();
        addWindow(w);

        addStyleName("login");

        loginLayout = new VerticalLayout();
        loginLayout.setSizeFull();
        loginLayout.addStyleName("login-layout");
        root.addComponent(loginLayout);

        final CssLayout loginPanel = new CssLayout();
        loginPanel.addStyleName("login-panel");

        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.addStyleName("labels");
        loginPanel.addComponent(labels);

        Label welcome = new Label("Welcome");
        welcome.setSizeUndefined();
        welcome.addStyleName("h4");
        labels.addComponent(welcome);
        labels.setComponentAlignment(welcome, Alignment.MIDDLE_LEFT);

        Label title = new Label("<span>SimHp Online</span> <br/> Kota Blitar", ContentMode.HTML);
        title.setSizeUndefined();
        title.addStyleName("h2");
        title.addStyleName("branding");
        labels.addComponent(title);
        labels.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);

        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");

        final TextField username = new TextField("Username");
        username.focus();
        fields.addComponent(username);

        final PasswordField password = new PasswordField("Password");
        fields.addComponent(password);

        final Button signin = new Button("Sign In");
        signin.addStyleName("default");
        fields.addComponent(signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        final ShortcutListener enter = new ShortcutListener("Sign In",
                KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                signin.click();
            }
        };
        
//EVENT SAAT TOMBOL DITEKAN
        signin.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            	UserJpaService userService = new UserJpaServiceImpl();
            	User user=new User();
            	user = userService.findById(username.getValue());
            	
            	String strUser = "dummyxxx####";
            	String strPass = "dummyxxx####";
            	boolean userEnabled = false;
            	try{
            		strUser = user.getUserId();
            		strPass= user.getUserPassword();
            		strUser = strUser.trim();
            		strPass = strPass.trim();
            		userEnabled = user.isActive();
            	} catch(Exception ex){}
            	
                if (username.getValue() != null
                        && username.getValue().equalsIgnoreCase(strUser)
                        && password.getValue() != null
                        && password.getValue().equals(strPass)
                        && userEnabled==true) {
                    
                	signin.removeShortcutListener(enter);
                    
                	userActive = new User();
                    userActive = user;
                    buildMainView();
                    
                } else {
                    if (loginPanel.getComponentCount() > 2) {
                        // Remove the previous error message
                        loginPanel.removeComponent(loginPanel.getComponent(2));
                    }
                    // Add new error message
                    Label error = new Label(
                            "username atau password Salah!!. <span>Coba lagi</span>",
                            ContentMode.HTML);
                    error.addStyleName("error");
                    error.setSizeUndefined();
                    error.addStyleName("light");
                    // Add animation
                    error.addStyleName("v-animate-reveal");
                    loginPanel.addComponent(error);
                    username.focus();
                }
            }
        });

        signin.addShortcutListener(enter);

        loginPanel.addComponent(fields);

        loginLayout.addComponent(loginPanel);
        loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }

    private void buildMainView() {

        nav = new Navigator(this, content);
        //PINDAH KE BAWAH
//      for (String route : routes.keySet()) {
//      nav.addView(route, routes.get(route));
//      }
  

        helpManager.closeAll();
        removeStyleName("login");
        root.removeComponent(loginLayout);

        root.addComponent(new HorizontalLayout() {
            {
                setSizeFull();
                addStyleName("main-view");
                addComponent(new VerticalLayout() {
                    // Sidebar
                    {
                        addStyleName("sidebar");
                        setWidth(null);
                        setHeight("100%");

                        // Branding element
                        addComponent(new CssLayout() {
                            {
                                addStyleName("branding");
                                Label logo = new Label(
                                        "<span>SimHp Online</span> Kota Blitar",
                                        ContentMode.HTML);
                                
                                logo.setSizeUndefined();
                                addComponent(logo);
                                
                                 addComponent(new Image(null, new
                                 ThemeResource(
                                 "img/branding.png")));
                                
                            }
                        });

                        // Main menu
                        addComponent(menu);
                        setExpandRatio(menu, 1);

                        // User menu
                        addComponent(new VerticalLayout() {
                            {
                                setSizeUndefined();
                                addStyleName("user");
                                Image profilePic = new Image(
                                        null,
                                        new ThemeResource("img/profile-pic.png"));
                                profilePic.setWidth("34px");
                                addComponent(profilePic);
//                                Label userName = new Label(Generator
//                                        .randomFirstName()
//                                        + " "
//                                        + Generator.randomLastName());
//                                userName.setSizeUndefined();
//                                addComponent(userName);

                                Command cmd = new Command() {
                                    @Override
                                    public void menuSelected(
                                            MenuItem selectedItem) {
                                    	
//                                        Notification
//                                                .show("Not implemented in this demo");
                                    
                                    	buildWindowChangePassword();
                                    	initListenerSubWindow();
                                    }
                                };
                                
                                Command cmdSystemSetting = new Command() {									
									@Override
									public void menuSelected(MenuItem selectedItem) {
										// TODO Auto-generated method stub
//										Notification.show("System Setting oke");
									}
								};
                                Command cmdUserAccount = new Command() {									
									@Override
									public void menuSelected(MenuItem selectedItem) {
										// TODO Auto-generated method stub
										Notification.show("User Account View");
										buildWindowChangePassword();
									}
								};
                                MenuBar settings = new MenuBar();
                                MenuItem settingsMenu = settings.addItem("",
                                        null);
                                settingsMenu.setStyleName("icon-cog");
                                
                                //ADMINISTRATOR OTORIZE FOR USER ACCCOUNT
//                                settingsMenu.addItem("System Setting", cmdSystemSetting);
//                                settingsMenu.addItem("User Account", cmdUserAccount);
                                //*******
                                
                                settingsMenu.addSeparator();
                                settingsMenu.addItem("My Account", cmd);
                                addComponent(settings);

                                Button exit = new NativeButton("Exit");
                                exit.addStyleName("icon-cancel");
                                exit.setDescription("Sign Out");
                                addComponent(exit);
                                exit.addClickListener(new ClickListener() {
                                    @Override
                                    public void buttonClick(ClickEvent event) {
                                        buildLoginView(true);
                                        //SING OUT HIRE
                                        userActive = new User();
                                    }
                                });
                            }
                        });
                    }
                });
                // Content
                addComponent(content);
                content.setSizeFull();
                content.addStyleName("view-content");
                setExpandRatio(content, 1);
            }

        });

        menu.removeAllComponents();
    	
    	nav.addView("/dashboard", routes.get("/dashboard"));
    	nav.addView("/surat-tugas", routes.get("/surat-tugas"));
    	nav.addView("/hasil-pemeriksaan", routes.get("/hasil-pemeriksaan"));
    	nav.addView("/laporan", routes.get("/laporan"));
    	nav.addView("/master-tabel", routes.get("/master-tabel"));
    	
        if (userActive.getUserOtorizeType().equals(EnumUserOtorize.ADMINISTRATOR.getStrCode())){          
	    	nav.addView("/setup-system", routes.get("/setup-system"));
	    	nav.addView("/setup-user", routes.get("/setup-user"));
	    	nav.addView("/utilitas", routes.get("/utilitas"));
        }
        
        List<String> listMenu = new ArrayList<String>();
        listMenu.add("dashboard");
        listMenu.add("surat-tugas");
        listMenu.add("hasil-pemeriksaan");
        listMenu.add("laporan");
        listMenu.add("master-tabel");

        if (userActive.getUserOtorizeType().equals(EnumUserOtorize.ADMINISTRATOR.getStrCode())){          
	        listMenu.add("setup-system");
	        listMenu.add("setup-user");
	        listMenu.add("utilitas");
        }  
        
//        for (final String view : new String[] { "dashboard", "surat-tugas","hasil-pemeriksaan", "laporan", "master-tabel", 
//                "setup-system", "setup-user", "utilitas"}) {

        for (final String view: listMenu){
        	
            Button b = new NativeButton(view.substring(0, 1).toUpperCase()
                    + view.substring(1).replace('-', ' '));
            b.addStyleName("icon-" + view);
            b.addClickListener(new ClickListener() {
                @Override
                public void buttonClick(ClickEvent event) {
                    clearMenuSelection();
                    event.getButton().addStyleName("selected");
                    if (!nav.getState().equals("/" + view))
                        nav.navigateTo("/" + view);
                }
                
            });

            if (view.equals("reports")) {
                // Add drop target to reports button
                DragAndDropWrapper reports = new DragAndDropWrapper(b);
                reports.setDragStartMode(DragStartMode.NONE);
                reports.setDropHandler(new DropHandler() {

                    @Override
                    public void drop(DragAndDropEvent event) {
                        clearMenuSelection();
                        viewNameToMenuButton.get("/reports").addStyleName(
                                "selected");
                        autoCreateReport = true;
                        items = event.getTransferable();
                        nav.navigateTo("/reports");
                    }

                    @Override
                    public AcceptCriterion getAcceptCriterion() {
                        return AcceptItem.ALL;
                    }

                });
                menu.addComponent(reports);
                menu.addStyleName("no-vertical-drag-hints");
                menu.addStyleName("no-horizontal-drag-hints");
            } else {            	
                menu.addComponent(b);
                
            }

            viewNameToMenuButton.put("/" + view, b);
        }
        menu.addStyleName("menu");
        menu.setHeight("100%");

        viewNameToMenuButton.get("/dashboard").setHtmlContentAllowed(true);
        viewNameToMenuButton.get("/dashboard").setCaption(
                "Dashboard<span class=\"badge\">2</span>");

        String f = Page.getCurrent().getUriFragment();
        if (f != null && f.startsWith("!")) {
            f = f.substring(1);
        }
        if (f == null || f.equals("") || f.equals("/")) {
            nav.navigateTo("/dashboard");
            menu.getComponent(0).addStyleName("selected");
            helpManager.showHelpFor(DashboardView.class);
        } else {
            nav.navigateTo(f);
            
            helpManager.showHelpFor(routes.get(f));
            
            viewNameToMenuButton.get(f).addStyleName("selected");
        }

        nav.addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                helpManager.closeAll();
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {
                View newView = event.getNewView();
                helpManager.showHelpFor(newView);
//                if (autoCreateReport && newView instanceof ReportsView) {
//                    ((ReportsView) newView).autoCreate(2, items, transactions);
//                }
                autoCreateReport = false;
            }
        });

    }

    private Transferable items;

    private void clearMenuSelection() {
        for (Iterator<Component> it = menu.getComponentIterator(); it.hasNext();) {
            Component next = it.next();
            if (next instanceof NativeButton) {
                next.removeStyleName("selected");
            } else if (next instanceof DragAndDropWrapper) {
                // Wow, this is ugly (even uglier than the rest of the code)
                ((DragAndDropWrapper) next).iterator().next()
                        .removeStyleName("selected");
            }
        }
    }

    public void updateReportsButtonBadge(String badgeCount) {
        viewNameToMenuButton.get("/reports").setHtmlContentAllowed(true);
        viewNameToMenuButton.get("/reports").setCaption(
                "Reports<span class=\"badge\">" + badgeCount + "</span>");
    }

    void clearDashboardButtonBadge() {
        viewNameToMenuButton.get("/dashboard").setCaption("Dashboard");
    }

    boolean autoCreateReport = false;
    Table transactions;

    public void openReports(Table t) {
        transactions = t;
        autoCreateReport = true;
        nav.navigateTo("/reports");
        clearMenuSelection();
        viewNameToMenuButton.get("/reports").addStyleName("selected");
    }

    public HelpManager getHelpManager() {
        return helpManager;
    }
    
	private Window windowUserPasswordChange = new Window();	
	
	private UserPasswordChangeModel userPasswordChangeModel; 
	private UserPasswordChangeView userPasswordChangeView;
	
	//WINDOW HEADER SELECT
	public void buildWindowChangePassword(){
		
		//Create window
		windowUserPasswordChange = new Window();
		windowUserPasswordChange.setModal(true);
		windowUserPasswordChange.center();
		windowUserPasswordChange.setStyleName("login-layout");
		windowUserPasswordChange.setWidth("600px");
		windowUserPasswordChange.setHeight("450px");
		
		//INITIAL DATA TO PASS
		
		userPasswordChangeModel = new UserPasswordChangeModel();
		userPasswordChangeView = new UserPasswordChangeView(userPasswordChangeModel);
		
		UserPasswordChangePresenter userPasswordChangePresenter = new UserPasswordChangePresenter(
				userPasswordChangeModel, userPasswordChangeView);		
		userPasswordChangeView.setSizeFull();		
		
		windowUserPasswordChange.setContent(userPasswordChangeView);
		
		//SORRY I PUT IN HERE
		initListenerSubWindow();
		
		getUI().addWindow(windowUserPasswordChange);
		
		
	}
	
	public void destroyWindowChangePassword(){		
		windowUserPasswordChange.close();
	}
	public User getUserActive() {
		return userActive;
	}
	public void setUserActive(User userActive) {
		this.userActive = userActive;
	}
    

}
