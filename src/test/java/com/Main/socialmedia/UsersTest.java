package com.Main.socialmedia;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dao.UsersDAO;
import com.model.Users;

@SpringBootTest
class UsersTest {

	@Autowired
	UsersDAO udao;

	// DAO Test for getting all registered users
	@Test
	void testGetAllRegisteredUser() {
		List<String> list1 = Arrays.asList(new String[]{"Johnson", "Jack"});
		List<String> list2 = Arrays.asList(new String[]{"Mike", "Meghan"});
		Users u1 = new Users("A123", "john@gmail.com", "john123", 25,null);
		Users u2 = new Users("B123", "mary@gmail.com", "mary123", 25,null);
		udao.save(u1);
		udao.save(u2);
		long c = udao.count();
		Assertions.assertEquals(2, c);

	}

	// Controller Test for getting all registered users
	@Test
	void testControllerGetAllRegisteredUsers() throws URISyntaxException {
		RestTemplate temp = new RestTemplate();
		final String url = "http://localhost:8080/getallregisteredusers";
		URI uri = new URI(url);
		ResponseEntity<String> res = temp.getForEntity(uri, String.class);
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	// DAO Test for searching by User UserId
	@Test
	void testGetByUserId() {
		Users u1 = new Users("A123", "john@gmail.com", "john123", 25,null);
		Users u2 = new Users("B123", "mary@gmail.com", "mary123", 25,null);
		udao.save(u1);
		udao.save(u2);
		Users actual = udao.findByUserId(u1.getUserId());
		Assertions.assertEquals(actual.getEmailId(), "john@gmail.com");

	}

	// Controller Test for searching user by user id
	@Test
	void testControllerGetByUserId() throws URISyntaxException {
		RestTemplate temp = new RestTemplate();
		final String url = "http://localhost:8080/searchbyuserid/A123";
		URI uri = new URI(url);
		ResponseEntity<String> res = temp.getForEntity(uri, String.class);
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
	}
	
	
	
	//DAO for getfriendlist
	@Test
	void testGetAllFriends() {
		List<String> list1 = Arrays.asList(new String[]{"Johnson", "Jack"});
		Users u1 = new Users("A123", "john@gmail.com", "john123", 25,list1);
		udao.save(u1);
		String userId="A123";
		Users temp=udao.findByUserId(userId);
		int expected=temp.getFriendList().size();
		Assertions.assertEquals(2, expected);

	}
	
	
	// Controller Test for searching user by user id
		@Test
		void testControllerGetAllFriends() throws URISyntaxException {
			RestTemplate temp = new RestTemplate();
			final String url = "http://localhost:8080/findallfriends/A123";
			URI uri = new URI(url);
			ResponseEntity<String> res = temp.getForEntity(uri, String.class);
			Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
		}
		
		//DAO for deleteafriend
		@Test
		void testdeletefriend() {
			List<String> list1 = Arrays.asList(new String[]{"Johnson", "Jack"});
			Users u1 = new Users("A123", "john@gmail.com", "john123", 25,list1);
			udao.save(u1);
			
			Users u=udao.findByUserId(u1.getUserId());
			List<String> friendlist=u.getFriendList();
			
			String friendId="Jack";
			friendlist.remove(friendlist.indexOf(friendId));
			u.setFriendList(friendlist);
			
			udao.delete(u1);
			udao.save(u);
			int expected=u.getFriendList().size();
			Assertions.assertEquals(1, expected);

		}
		
		
	/*
	@Test
    public void testAdminDeleteUsers() {//throws URISyntaxException{
        //RestTemplate template=new RestTemplate();
        Users users=new Users();
        users.setAge(15);
        users.setEmailId("abc@gmail.com");
        users.setPassword("abc123");
        users.setUserId("abc12300");
        usersdao.save(users);
        
        Users users1=new Users();
        users1.setAge(15);
        users1.setEmailId("abc@gmail.com");
        users1.setPassword("abc123");
        users1.setUserId("aaa111");
        usersdao.save(users1);
        
         usersdao.deleteById(users.getUserId());
        /*
        System.out.println(users);
        System.out.println(usersdao.findAll());
        
        final String url="http://localhost:8080/admindeleteusers";
        URI uri=new URI(url);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Users> entity = new HttpEntity<Users>(users,headers);
          
        ResponseEntity<String> res = template.exchange(url, HttpMethod.DELETE, entity, String.class).getBody();
         */
        
       // Assertions.assertFalse(usersdao.findAll().toString().contains(users.toString()));    
  //  }

}

/*
 * if any part of your controller has exception handling make controller advice
 * 
 * if you're using try catch block
 *and define your own custom exception class
 *in com.controller make ControllerAdvisor class
 *
 *@ExceptionHnadler(Customerexceptionclassname.class)
 *public ResponseEntityt<Object> handleexception(Customerexception e, WebRequest r){
 *return new ResponseEntityt <>(e.toString,HttpStatus.NOT_FOUND);
 *}
 */