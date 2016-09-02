package bootwildfly;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;


@Service
public class ProblemService {

	@Autowired
	private ProblemRepository problemRepo;
	
	public ProblemService() {
		
	}
	

	

	public Page<Problem> getAllProblems(Integer page, Integer size) {
		return problemRepo.findAll(new PageRequest(page,size));
	}

	@Transactional
	public Problem addProblem(Problem problem) {
		return problemRepo.save(problem);
	}
	
	public void updateProblem(Problem problem) {
		Problem oldProblem = getProblem(problem.getId());
		oldProblem.setCode(problem.getCode());
		oldProblem.setDescription(problem.getDescription());
		oldProblem.setName(problem.getName());
		oldProblem.setTip(problem.getTip());
		//oldProblem.setTests(problem.getTests());
		problemRepo.save(oldProblem);
	}

	public Problem getProblem(String problemid) {
		return problemRepo.findOne(problemid);
	}


}
