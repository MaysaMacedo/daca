package bootwildfly;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
	User findByUserName(String userName);
}
