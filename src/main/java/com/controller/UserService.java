package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.dao.UsersDAO;
import com.exception.NoUserFoundException;
import com.model.Users;

@Component
public class UserService {
	
	@Autowired
	UsersDAO udao;
	
	public Map<String, String> getAllUsers(){
		Users u1 = new Users("A123", "john@gmail.com", "john123", 25);
		Users u2 = new Users("B123", "mary@gmail.com", "mary123", 25);
		udao.save(u1);
		udao.save(u2);
		List<Users> userList = udao.findAll();
		Map<String, String> usernames = new HashMap<>();
		for (Users x : userList) {
			usernames.put("User Id- "+x.getUserId(),"Email Id- "+ x.getEmailId());
		}
		return usernames;
	}
	
	public String getUserByEmailId(String userId) throws NoUserFoundException {
		try {
			Users user = udao.findByUserId(userId);
			if(user!=null)
				return "UserId: " + user.getUserId() + "\nEmailId: " + user.getEmailId();
			else
				throw new NoUserFoundException("No user found");
			
		}catch(NoUserFoundException e) {
			throw new NoUserFoundException("No user found");
		}
		
		
	}

}
