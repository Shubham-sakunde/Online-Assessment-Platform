package com.tka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.dao.LoginDao;
import com.tka.entities.User;

@Service
public class LoginService {

	@Autowired
	public LoginDao dao;
	
	
	public boolean validate(User userFromBrowser) {
		
		String dbpassword = dao.getPasswordFromDatabase(userFromBrowser.getUsername());
		
		if(dbpassword.equals(userFromBrowser.getPassword())) {
			
			return true;
			
		}else {
			
			return  false;
			
		}
		
	}
	
}
