package com.stackroute.hackathon.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.stackroute.hackathon.controller.*;
import com.stackroute.hackathon.domain.*;
import com.stackroute.hackathon.repositories.*;
import com.stackroute.hackathon.service.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
@RestController

@RequestMapping("/hackathon/")
public class WebController {
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(value="{userId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable String userId) {
		
		User users = userDAO.findByuserId(userId);
        return users;
    }
	@RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUser() {
		
		List<User> users = userDAO.findAll();
        return users;
    }
	
	
	@RequestMapping(value="{userId}",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addArticles(@PathVariable String userId,@RequestBody User user) {
		User user1 = userDAO.findByuserId(userId);
		if(user1==null) {
		boolean flag = userDAO.save(user) != null;
        if (flag == false) {
	    return new ResponseEntity<String>("userid alreday not created",HttpStatus.CONFLICT);
        }
        
        return new ResponseEntity<String>( "userid created",HttpStatus.CREATED);}
		else {
			return new ResponseEntity<String>("userid alreday exsists",HttpStatus.CONFLICT);
		}
    }
	
	@RequestMapping(value="{userId}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateArticles(@PathVariable String userId,@RequestBody User user) {
		
		boolean flag = userDAO.save(user) != null;
        if (flag == false) {
	    return new ResponseEntity<String>("userid not updated",HttpStatus.CONFLICT);
        }
        
        return new ResponseEntity<String>( "userid updated",HttpStatus.CREATED);
    }
	
	@RequestMapping(value="{userId}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteArticle( @PathVariable String userId) {
		User user= userDAO.findByuserId(userId);
		userDAO.delete(user);
		return new ResponseEntity<String>("userid deleted",HttpStatus.NO_CONTENT);
    }

}
