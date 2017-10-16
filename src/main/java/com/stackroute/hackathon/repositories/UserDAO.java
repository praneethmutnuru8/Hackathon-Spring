package com.stackroute.hackathon.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import com.stackroute.hackathon.domain.*;
import java.util.List;
@Transactional
public interface UserDAO extends MongoRepository<User,String>{
	
	
	User findByuserId(String userId);
	void delete(User user);

}
