package com.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UsersDAO;
import com.model.Users;

@RestController
public class UserController {

	@Autowired
	UsersDAO udao;

	@GetMapping("/getallregisteredusers")
	public Map<String, String> getAllUsers() {
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

	@GetMapping("/searchbyuserid/{userId}")
	public String getUserByEmailId(@PathVariable String userId) {
		Users u1 = new Users("A123", "john@gmail.com", "john123", 25);
		Users u2 = new Users("B123", "mary@gmail.com", "mary123", 25);
		udao.save(u1);
		udao.save(u2);
		Users user = udao.findByUserId(userId);
		return "UserId: " + user.getUserId() + "\nEmailId: " + user.getEmailId();
	}

}
