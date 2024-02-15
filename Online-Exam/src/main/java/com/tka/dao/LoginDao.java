package com.tka.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tka.entities.User;

@Repository
public class LoginDao {
	
	
	@Autowired
	SessionFactory factory;
	
	
	public String getPasswordFromDatabase(String username){
		
		Session session = factory.openSession();
		User userFromDb = session.get(User.class, username);
		
		return userFromDb.getPassword();
		
	}
}
