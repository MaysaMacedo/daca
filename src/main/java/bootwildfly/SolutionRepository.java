package bootwildfly;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SolutionRepository extends CrudRepository<Solution, String> {

	List<Solution> findByProblem(Problem pro);
	

}
