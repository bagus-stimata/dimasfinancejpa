package org.blitar.bawasda.simhp.otorisasi;


import org.blitar.bawasda.simhp.model.User;
import org.blitar.bawasda.simhp.service.SysvarJpaService;
import org.blitar.bawasda.simhp.service.UserJpaService;
import org.blitar.bawasda.simhp.service.UserJpaServiceImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;

public class UserPasswordChangeModel {
	
	//1. DAO SERVICE
		private UserJpaService userService;
//		private MenuAccessTempJpaService menuAccessTempService;
		private SysvarJpaService sysvarService;
		
	//2. ENTITY
		protected User user;
		protected User newUser;
//		protected MenuAccessTemp menuAccessTemp;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<User> beanItemContainerUser;
		private BeanItemContainer<User> beanItemContainerUserSearch;
		
		
//		private BeanItemContainer<Bank> beanItemContainerBank;
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<User> binderUser;
				
	//OTHERS
	protected String OperationStatus = "OPEN";

	public UserPasswordChangeModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		userService = new UserJpaServiceImpl();		
		user = new User();
		
		beanItemContainerUser = new BeanItemContainer<User>(User.class);
		beanItemContainerUserSearch = new BeanItemContainer<User>(User.class);
		
		binderUser = new BeanFieldGroup<User>(User.class);
		
	}
	public void initVariableData(){
		reload();
		
		
	}
	public void reload(){
		beanItemContainerUser.removeAllContainerFilters();
		beanItemContainerUser.removeAllItems();
		
		//CUMA SATU OBJECT YANG BISA MASUK
//		beanItemContainerUser.addAll(userService.findAll());
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

	
	
	
	
}
