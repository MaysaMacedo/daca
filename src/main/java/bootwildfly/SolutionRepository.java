package bootwildfly;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface SolutionRepository extends CrudRepository<Solution, String> {

	List<Solution> findByProblemid(String pro);
	

}
