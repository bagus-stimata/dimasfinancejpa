package org.dimas.finance.jpa.dao;

import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Service;

@Service("UserBean")
public class UserBeanImpl implements UserBean{

	public String getUser() {
		
		return "Bagus Winarno";
	}

}
