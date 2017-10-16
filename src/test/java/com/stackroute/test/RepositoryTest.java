package com.stackroute.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.hackathon.repositories.UserDAO;
import com.stackroute.hackathon.DemoApplication;
import com.stackroute.hackathon.domain.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryTest {

  @Autowired
  private UserDAO userRepository;

  @Test
  public void saveTest() {
      User user = new User();
      user.setUserId("user1");
		user.setEmailId("goku@gmail.com");
		user.setFirstname("Goku");
		user.setPhone("7869509458");
		user.setLastname("San");
      
    userRepository.save(user);
    Assert.assertNotNull(userRepository.findOne("user1"));
  }
}