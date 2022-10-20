package com.controller;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UsersDAO;
import com.exception.NoUserFoundException;
import com.model.Users;

@RestController
public class UserController  {

	@Autowired
	UserService userv;

	
	@GetMapping("/getallregisteredusers")
	public Map<String,String> getAll() {
		Map<String,String> umap=userv.getAllUsers();
		return umap;
	}
	
	

	
	@GetMapping("/searchbyuserid/{userId}")
	public String getUser(@PathVariable String userId) throws NoUserFoundException {
		try {
			String u=userv.getUserByUserId(userId);
			return u;
			
		}catch(NoUserFoundException e) {
			throw new NoUserFoundException("No user found");
		}
		
		
	}
	
	
	
	@GetMapping("/findallfriends/{userId}")
	public List<String> getfriendlist(@PathVariable String userId ){
		List<String> friendList=userv.getFriendList(userId);
		return friendList;
	}
	
	
	@DeleteMapping("/deleteafriend/{userId}/{friendId}")
	public List<String> deletefriend(@PathVariable String userId, @PathVariable String friendId){
		List<String> friendList=userv.deletefriend(userId,friendId);
		return friendList;
	}
	

}
