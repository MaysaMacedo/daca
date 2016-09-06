package bootwildfly;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	public Long countSolved() {
		Long totalSolved = 0L;
		Page<Problem> allProblems = problemServ.getAllProblems(100, 0);
		for (Iterator iterator = allProblems.iterator(); iterator.hasNext();) {
			Problem problema = (Problem) iterator.next();
			String problemId = problema.getId();
			List <Solution> allSolutions = this.getSolutions(problemId);
			for (Iterator iterator2 = allSolutions.iterator(); iterator2.hasNext();) {
				Solution solution = (Solution) iterator2.next();
				if (solution.isCorrect())
				{
					totalSolved++;
					break;//next problem
				}
			}
		}
		return totalSolved;
	}
}
