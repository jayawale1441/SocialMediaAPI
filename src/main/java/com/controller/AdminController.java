package com.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dao.*;
import com.model.*;

import com.exception.NoAdminFoundException;
import com.exception.NoCommentFoundException;
import com.exception.NoPostFoundException;
import com.exception.NoUserFoundException;

@RestController
public class AdminController {
	
	@Autowired
	AdminDAO admindao;
	
	@Autowired
	ForumDAO forumdao;
	
	@Autowired
    GroupsDAO groupdao;
	
	@Autowired
	AdminService adminservice;
	
	@PostMapping("/addadmin")
	public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
		
		adminservice.addAdminService(admin);
		
		return new ResponseEntity<String>("Admin added successfully",HttpStatus.OK);
	}

	
	@PatchMapping("/updateadmin")
	public ResponseEntity<String> updateAdmin(@RequestBody Admin admin) {
		adminservice.updateAdminService(admin);
		return new ResponseEntity<String>("Admin details updated",HttpStatus.OK);
	}

	@DeleteMapping("/deleteadmin")
	public ResponseEntity<String> deleteAdmin(@RequestBody Admin admin) {
		adminservice.deleteAdminService(admin);
		return new ResponseEntity<String>("Admin deleted",HttpStatus.OK);
	}
	
	@GetMapping("/findbyadminid/{id}")
	public Admin getAdmin(@PathVariable String id) throws NoAdminFoundException {
			Admin admin;
			admin = adminservice.getAdminService(id);
			return admin;
	}
	
	@GetMapping("/admingetallUsers")
	public List<Users> adminGetAllUsers(){
		return adminservice.adminGetAllUsersService();
		
	}
	
	@PostMapping("/adminaddusers")
	public ResponseEntity<String> adminAddUsers(@RequestBody Users users){
		adminservice.adminAddUsersService(users);
		
		return new ResponseEntity<String>("User Registered Successfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/admindeleteusers")
	public ResponseEntity<String> adminDeleteUsers(@RequestBody Users users){
		adminservice.adminDeleteUsersService(users);
		
		return new ResponseEntity<String>("User Deleted Successfully",HttpStatus.OK);
	}
	
    @PostMapping("/addGroup")
	public ResponseEntity<String>addgroups(@RequestBody Groups groups)
    {
		
		adminservice.addGroupService(groups);
		return new ResponseEntity<String>("Group added successfully",HttpStatus.OK);
	}
    @PostMapping("/addForum")
	public ResponseEntity<String> addForum(@RequestBody Forum forum){
		
    	adminservice.addForumService(forum);
		return new ResponseEntity<String>("Added Forum", HttpStatus.OK);
	}
    @DeleteMapping("/deleteGroup")
	public ResponseEntity<String> deletegroups(@RequestBody Groups groups) {
    	adminservice.deleteGroupService(groups);
		return new ResponseEntity<String>("Group deleted",HttpStatus.OK);
		
	}
    @DeleteMapping("/deleteForum")
	public ResponseEntity<String> deleteForum(@RequestBody Forum forum) {
    	adminservice.deleteForumService(forum);
		return new ResponseEntity<String>("Forum deleted",HttpStatus.OK);
		
	}
	@PatchMapping("/updateGroup")
	public ResponseEntity<String> updateGroup(@RequestBody Groups groups)
	{
		groupdao.save(groups);
		return new ResponseEntity<String>("Update Successfully",HttpStatus.OK);
	}
	@PatchMapping("/updateForum")
	public ResponseEntity<String> updateForum(@RequestBody Forum forum)
	{
		//Optional<Forum> forumdata = dao.findById(forum.getForumId());
	//	forumdata.get().setCreatedBy(forum.getCreatedBy());   // data fetched then updated
		
		forumdao.save(forum);
		
		return new ResponseEntity<String>("Update Successfully",HttpStatus.OK);
	}
	@PatchMapping("/ApproveForum/{id}")
	public ResponseEntity<String> ApproveForum(@PathVariable String id)
	{
		

			Optional<Forum> vals = forumdao.findById(id);
			System.out.println(vals.get());
			vals.get().setStatus(Status.ACTIVE);
			forumdao.save(vals.get());
		
		return new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
	}
	@PatchMapping("/ApproveGroup/{id}")
	public ResponseEntity<String> ApproveGroup(@PathVariable String id)
	{
		

			Optional<Groups> vals = groupdao.findById(id);
			vals.get().setStatus(Status.ACTIVE);
			groupdao.save(vals.get());
		
		return new ResponseEntity<String>("Updated Successfully",HttpStatus.OK);
	}
	    @PostMapping("/blockcomment")
	    public ResponseEntity blockComment(@RequestBody Comment c) {
	        
	        try {
	            return adminservice.blockComment(c);
	        } catch (NoCommentFoundException e) {
	            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
	        }
	    }    
	    
	    @PostMapping("/blockpost")
	    public ResponseEntity blockPost(@RequestBody Post p) {
	        
	        try {
	            return adminservice.blockPost(p);
	        } catch (NoPostFoundException e) {
	            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
	        }
	    }    
	    
	    @PostMapping("/blockuser")
	    public ResponseEntity blockuser(@RequestBody Users u) {
	        
	        try {
	            return adminservice.blockUser(u);
	        }
	        catch (NoUserFoundException e) {
	            return new ResponseEntity(e.getMessage(),HttpStatus.OK);
	        }
	    }
	    
	    
	}