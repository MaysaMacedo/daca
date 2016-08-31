package bootwildfly;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;


public interface ProblemRepository extends CrudRepository<Problem, String> {

	Page<Problem> findAll(Pageable pageable);
	
}
