package com.stackroute.hackathon.service;
import com.stackroute.hackathon.domain.*;
import com.stackroute.hackathon.repositories.UserDAO;
import java.util.List;
import com.stackroute.hackathon.controller.*;
import com.stackroute.hackathon.domain.*;
import com.stackroute.hackathon.repositories.*;
import com.stackroute.hackathon.service.*;
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
    
    
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public User getUserService(String userId) {
        User user = userDAO.findByuserId(userId);
        return user;
    }
    public List<User> getAllUserService() {
        List<User> users = userDAO.findAll();
        return users;
    }
    public ResponseEntity<String> addUserService( String userId, User user) {
        User user1 = userDAO.findByuserId(userId);
        if(user1==null&& user.getEmailId() != null) {
        boolean flag = userDAO.save(user) != null;
        if (flag == false) {
        return new ResponseEntity<String>("user not created",HttpStatus.CONFLICT);
        }
        
        
        return new ResponseEntity<String>( "user created",HttpStatus.CREATED);}
        else {
            return new ResponseEntity<String>("user alreday exsists",HttpStatus.CONFLICT);
        }
    }
     public ResponseEntity<String> updateUserService(String userId, User user){
         
         boolean flag = false;
         
         if (userDAO.findByuserId(userId) != null){
             
         flag = userDAO.save(user) != null;
         
         
            
            if (flag == false) {
            return new ResponseEntity<String>("user not updated",HttpStatus.CONFLICT);
            }
            
            return new ResponseEntity<String>( "user updated",HttpStatus.CREATED);
     }
         else {
             return new ResponseEntity<String>("user not in database",HttpStatus.CONFLICT);
         }
         
     }
     public ResponseEntity<String> deleteUserService(String userId){
            User user= userDAO.findByuserId(userId);
            
            if(user != null) {
            userDAO.delete(user);
            return new ResponseEntity<String>("user deleted",HttpStatus.NO_CONTENT);
}
            else {
                return new ResponseEntity<String>("user not in database",HttpStatus.CONFLICT);
            }       
     
     
     }}