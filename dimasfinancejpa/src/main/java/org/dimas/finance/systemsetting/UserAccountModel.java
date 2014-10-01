package org.dimas.finance.systemsetting;

import org.dimas.finance.jpa.dao.BankJpaService;
import org.dimas.finance.jpa.dao.BankJpaServiceImpl;
import org.dimas.finance.jpa.dao.BukuTransferJpaService;
import org.dimas.finance.jpa.dao.BukuTransferJpaServiceImpl;
import org.dimas.finance.jpa.dao.ModulTempHeaderJpaService;
import org.dimas.finance.jpa.dao.ModulTempHeaderJpaServiceImpl;
import org.dimas.finance.jpa.dao.SysvarJpaService;
import org.dimas.finance.jpa.dao.SysvarJpaServiceImpl;
import org.dimas.finance.jpa.dao.UserJpaService;
import org.dimas.finance.jpa.dao.UserJpaServiceImpl;
import org.dimas.finance.model.Bank;
import org.dimas.finance.model.Bukutransfer;
import org.dimas.finance.model.ModulTempHeader;
import org.dimas.finance.model.User;
import org.dimas.finance.util.TransaksiHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class UserAccountModel {
	
	//1. DAO SERVICE
		private UserJpaService userService;
//		private MenuAccessTempJpaService menuAccessTempService;
		private SysvarJpaService sysvarService;
		
		private ModulTempHeaderJpaService modulTempHeaderService;
		
	//2. ENTITY
		protected User user;
		protected User newUser;
//		protected MenuAccessTemp menuAccessTemp;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<User> beanItemContainerUser;
		private BeanItemContainer<User> beanItemContainerUserSearch;
		
		private BeanItemContainer<ModulTempHeader> beanItemContainerModulTemplate;
		
//		private BeanItemContainer<Bank> beanItemContainerBank;
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<User> binderUser;
				
	//OTHERS
	protected String OperationStatus = "OPEN";

	public UserAccountModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		userService = new UserJpaServiceImpl();
//		bankService = new BankJpaServiceImpl();
		sysvarService = new SysvarJpaServiceImpl();
		
		modulTempHeaderService = new ModulTempHeaderJpaServiceImpl();
		
		user = new User();
//		bank = new Bank();
		
		beanItemContainerUser = new BeanItemContainer<User>(User.class);
		beanItemContainerUserSearch = new BeanItemContainer<User>(User.class);
		beanItemContainerModulTemplate = new BeanItemContainer<ModulTempHeader>(ModulTempHeader.class);
		
		binderUser = new BeanFieldGroup<User>(User.class);
		
	}
	public void initVariableData(){
		reload();
		
		
	}
	public void reload(){
		beanItemContainerUser.removeAllContainerFilters();
		beanItemContainerModulTemplate.removeAllContainerFilters();
		beanItemContainerUser.removeAllItems();
		beanItemContainerModulTemplate.removeAllItems();
		
		beanItemContainerUser.addAll(userService.findAll());
		beanItemContainerModulTemplate.addAll(modulTempHeaderService.findAll());
	}

	public UserJpaService getUserService() {
		return userService;
	}

	public void setUserService(UserJpaService userService) {
		this.userService = userService;
	}

	public SysvarJpaService getSysvarService() {
		return sysvarService;
	}

	public void setSysvarService(SysvarJpaService sysvarService) {
		this.sysvarService = sysvarService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public BeanItemContainer<User> getBeanItemContainerUser() {
		return beanItemContainerUser;
	}

	public void setBeanItemContainerUser(
			BeanItemContainer<User> beanItemContainerUser) {
		this.beanItemContainerUser = beanItemContainerUser;
	}

	public BeanItemContainer<User> getBeanItemContainerUserSearch() {
		return beanItemContainerUserSearch;
	}

	public void setBeanItemContainerUserSearch(
			BeanItemContainer<User> beanItemContainerUserSearch) {
		this.beanItemContainerUserSearch = beanItemContainerUserSearch;
	}

	public BeanFieldGroup<User> getBinderUser() {
		return binderUser;
	}

	public void setBinderUser(BeanFieldGroup<User> binderUser) {
		this.binderUser = binderUser;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	public ModulTempHeaderJpaService getModulTempHeaderService() {
		return modulTempHeaderService;
	}

	public void setModulTempHeaderService(
			ModulTempHeaderJpaService modulTempHeaderService) {
		this.modulTempHeaderService = modulTempHeaderService;
	}

	public BeanItemContainer<ModulTempHeader> getBeanItemContainerModulTemplate() {
		return beanItemContainerModulTemplate;
	}

	public void setBeanItemContainerModulTemplate(
			BeanItemContainer<ModulTempHeader> beanItemContainerModulTemplate) {
		this.beanItemContainerModulTemplate = beanItemContainerModulTemplate;
	}
	
	
	
	
}
