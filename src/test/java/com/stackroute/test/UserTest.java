package com.stackroute.test;

import junit.framework.TestCase;
import com.stackroute.hackathon.*;
import com.stackroute.hackathon.domain.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest  extends TestCase {
   
	@LocalServerPort
	private int port;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	User user;    
    
	@Before
    public void setUp() throws Exception {
		 user = new User();
		user.setUserId("user1");
		user.setEmailId("goku@gmail.com");
		user.setFirstname("Goku");
		user.setPhone("7869509458");
		user.setLastname("San");
    }
	
    private String createURLWithPort(String uri) {
    	
        return "http://localhost:" +port +"/hackathon" + uri;
        
    }
    
    @After
    public void tearDown() throws Exception {
    	user=null;
    }
    
    @Test
    public void testSaveUser() throws Exception { 

    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<User>(user, headers); 
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/user"),
                HttpMethod.POST, entity, String.class); 
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("user created",actual);
    }
    
    @Test
    public void testListOfUsers() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/"),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response);
    }
    @Test
    public void testGetUserById() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<User>(user, headers); 
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/user/"),
                HttpMethod.GET, entity, String.class); 
        assertNotNull(response);
        String actual = response.getBody();
 
        assertEquals("User Found",actual);
    }
      
    @Test
    public void testUpdateUser() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<User>(user, headers); 
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/"+user.getUserId()),
                HttpMethod.PUT, entity, String.class); 
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals("user updated",actual);
    }
    
    @Test
    public void testDeleteUser() throws Exception {
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<User>(user, headers); 
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/user1"),
                HttpMethod.DELETE, entity, String.class); 
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertEquals(null,actual);
    }
    
}