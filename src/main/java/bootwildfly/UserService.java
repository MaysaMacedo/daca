package bootwildfly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public User save(User user){
		return userRepo.save(user);
	}

	public User findByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}
	
	

}
