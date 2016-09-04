package bootwildfly;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProblemNotFoundException extends Exception {
	
	public ProblemNotFoundException(String problemid) {
		super("could not find problem '" + problemid + "'.");
	}

}
