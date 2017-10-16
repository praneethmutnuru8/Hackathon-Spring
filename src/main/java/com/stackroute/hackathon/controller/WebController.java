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
	private UserService userService;
	
	@RequestMapping(value="{userId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable String userId) {
		
		User users = userService.getUserService(userId);
		return new ResponseEntity<String>("User Found",HttpStatus.OK);
    }
	@RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUser() {
		
		List<User> users = userService.getAllUserService();
        return users;
    }
	
	
	@RequestMapping(value="{userId}",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@PathVariable String userId,@RequestBody User user) {
		return userService.addUserService(userId,user);
    }
	
	@RequestMapping(value="{userId}",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable String userId,@RequestBody User user) {
		return userService.updateUserService(userId,user);
		
    }
	
	@RequestMapping(value="{userId}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser( @PathVariable String userId) {
        return  userService.deleteUserService(userId);
    }

}
