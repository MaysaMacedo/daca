package bootwildfly;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolutionService {
	
	@Autowired
	SolutionRepository solutionRepo;

	@Autowired
	ProblemService problemServ;
	
	public void addSolution(String problemid, Solution solution) {
		Problem pro = problemServ.getProblem(problemid);
		solution.setProblem(pro);
		solutionRepo.save(solution);
	}

	public List<Solution> getSolutions(String problemid) {
		Problem pro = problemServ.getProblem(problemid);
		return solutionRepo.findByProblem(pro);
	}

	public Solution getSolution(String solutionId) {
		return solutionRepo.findOne(solutionId);
	}

}
