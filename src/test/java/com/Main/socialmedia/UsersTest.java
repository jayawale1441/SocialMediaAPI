package com.Main.socialmedia;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;

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
		Users u1 = new Users("A123", "john@gmail.com", "john123", 25);
		Users u2 = new Users("B123", "mary@gmail.com", "mary123", 25);
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
		Users u1 = new Users("A123", "john@gmail.com", "john123", 25);
		Users u2 = new Users("B123", "mary@gmail.com", "mary123", 25);
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