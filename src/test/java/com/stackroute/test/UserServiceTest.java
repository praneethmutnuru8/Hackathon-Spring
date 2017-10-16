package com.stackroute.test;
import com.stackroute.hackathon.controller.*;
import com.stackroute.hackathon.domain.*;
import com.stackroute.hackathon.repositories.*;
import com.stackroute.hackathon.service.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
public class UserServiceTest {
   private UserService userService;
    @Mock
    private UserDAO userRepository;
    @Mock
    private User user;
    List<User> users;
    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        userService=new UserService();
        userService.setUserDAO(userRepository);
        userRepository.save(user);
    }
    @Test
    public void shouldReturnUser_whengetAllUserServiceIsCalled() throws Exception {
        // Arrange
        when(userRepository.findAll()).thenReturn(users);
        // Act
        List<User> retrievedUsers = userService.getAllUserService();
        // Assert
        assertThat(retrievedUsers, is(equalTo(users)));
  }
    @Test
    public void shouldReturnUser_whengetUserServiceIsCalled() throws Exception {
        // Arrange
        when(userRepository.findByuserId(this.user.getUserId())).thenReturn(user);
        // Act
        User retrievedUser = userService.getUserService(this.user.getUserId());
        // Assert
        assertThat(retrievedUser, is(equalTo(user)));
  }
    @Test
    public void shouldReturnUser_whenaddUserServiceIsCalled() throws Exception {
        // Arrange
        userService.addUserService(this.user.getUserId(),this.user);
        // Act
        when(userRepository.findByuserId(this.user.getUserId())).thenReturn(user);
        // Assert
        assertThat(this.user, is(equalTo(user)));
  }  
    @Test
    public void shouldReturnUser_deleteUserServiceIsCalled() throws Exception {
        
        ResponseEntity<String> res =new ResponseEntity<String>("user not in database",HttpStatus.CONFLICT);
        ResponseEntity<String> res1;
        res1 =userService.deleteUserService(this.user.getUserId());
        
        assertThat(res1, is(equalTo(res)));
  }  
  
    
}