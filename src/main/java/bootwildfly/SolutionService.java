package bootwildfly;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolutionService {
	
	@Autowired
	SolutionRepository solutionRepo;

	@Autowired
	ProblemService problemServ;
	
	@Transactional
	public Solution addSolution(String problemid, Solution solution) {
		solution.setProblem(problemid);
		return solutionRepo.save(solution);
	}

	public List<Solution> getSolutions(String problemid) {
		return solutionRepo.findByProblemid(problemid);
	}

	public Solution getSolution(String solutionId) {		
		return solutionRepo.findOne(solutionId);
	}

}
