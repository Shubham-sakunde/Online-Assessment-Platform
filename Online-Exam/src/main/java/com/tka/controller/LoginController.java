package com.tka.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.entities.Answer;
import com.tka.entities.User;
import com.tka.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RestController
public class LoginController {
	
	@Autowired
	LoginService service;
	
	static HttpSession httpSession;
	
	
	@RequestMapping("validate")
	public boolean validate(@RequestBody User user,HttpServletRequest request) {
		
		httpSession = request.getSession();
		
		boolean answer = service.validate(user);
		
		if(answer) {
			
			httpSession.setAttribute("score", 0);
			
			HashMap<Integer,Answer> map = new HashMap<>();
			
			httpSession.setAttribute("submittedDetails", map);
			
			httpSession.setAttribute("questionIndex", 0);
			
			return true;
			
		}else {
			
			return false;
			
		}
		
		
		
	}

}
