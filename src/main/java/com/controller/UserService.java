package com.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.dao.UsersDAO;
import com.exception.NoUserFoundException;
import com.model.FriendRequest;
import com.model.Users;

@Component
public class UserService {
	
	@Autowired
	UsersDAO udao;
	
	public Map<String, String> getAllUsers(){
		List<String> list1 = Arrays.asList(new String[]{"Johnson", "Jack"});
		List<String> list2 = Arrays.asList(new String[]{"Mike", "Meghan"});
		Users u1 = new Users("A123", "john@gmail.com", "john123", 25,list1);
		Users u2 = new Users("B123", "mary@gmail.com", "mary123", 25,list2);
		udao.save(u1);
		udao.save(u2);
		List<Users> userList = udao.findAll();
		Map<String, String> usernames = new HashMap<>();
		for (Users x : userList) {
			usernames.put("User Id- "+x.getUserId(),"Email Id- "+ x.getEmailId());
		}
		return usernames;
	}
	
	public String getUserByUserId(String userId) throws NoUserFoundException  {
			Users user = udao.findByUserId(userId);
			if(user!=null)
				return "UserId: " + user.getUserId() + "\nEmailId: " + user.getEmailId();
			else
				throw new NoUserFoundException("No user found");
	}
	
	public List<String> getFriendList(String userId){
		Users u=udao.findByUserId(userId);
		List<String> friendlist=u.getFriendList();
		return friendlist;
		
		
		
	}

	public List<String> deletefriend(String userId, String friendId) {
		// TODO Auto-generated method stub
		Users u=udao.findByUserId(userId);
		List<String> friendlist=u.getFriendList();
		friendlist.remove(friendlist.indexOf(friendId));
		u.setFriendList(friendlist);
		udao.delete(u);
		udao.save(u);
		return friendlist;
		
		
	}

}
