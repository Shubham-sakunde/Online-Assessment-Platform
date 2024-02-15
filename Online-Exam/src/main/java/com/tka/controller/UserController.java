package com.tka.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.entities.User;

@RestController
public class UserController {

	@Autowired
	SessionFactory factory;



	@PostMapping("saveUser")
	public void saveUser(@RequestBody User user) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		System.out.println("User Saved");
	}

	

}
