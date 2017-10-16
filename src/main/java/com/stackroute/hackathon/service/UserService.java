package com.stackroute.hackathon.service;
import com.stackroute.hackathon.domain.*;
import com.stackroute.hackathon.repositories.UserDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	public User getUserService(String userId) {
		User user = userDAO.findByuserId(userId);
		return user;
	}
	public List<User> getAllUserService() {
		List<User> users = userDAO.findAll();
		return users;
	}
	public ResponseEntity<String> addArticlesService( String userId, User user) {
		User user1 = userDAO.findByuserId(userId);
		if(user1==null) {
		boolean flag = userDAO.save(user) != null;
        if (flag == false) {
	    return new ResponseEntity<String>("user not created",HttpStatus.CONFLICT);
        }
        
        return new ResponseEntity<String>( "user created",HttpStatus.CREATED);}
		else {
			return new ResponseEntity<String>("user alreday exsists",HttpStatus.CONFLICT);
		}
	}
	 public ResponseEntity<String> updateArticlesService(String userId, User user){
		 boolean flag = userDAO.save(user) != null;
	        if (flag == false) {
		    return new ResponseEntity<String>("user not updated",HttpStatus.CONFLICT);
	        }
	        
	        return new ResponseEntity<String>( "user updated",HttpStatus.CREATED);
	 }
	 public ResponseEntity<String> deleteArticleService(String userId){
			User user= userDAO.findByuserId(userId);
			userDAO.delete(user);
			return new ResponseEntity<String>("user deleted",HttpStatus.NO_CONTENT);
}}
