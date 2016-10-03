package bootwildfly;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {
	
	enum USER_TYPE{anonymous, normal, administrator}
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
//	@OneToMany(mappedBy="user",cascade=CascadeType.ALL, fetch= FetchType.EAGER)
//	private List<Problem> problems;
//	
//	@ManyToMany
//	private List<Problem> solvedProblems;
	

	public User() {
		
	}
	
	public String getName() {
		return firstName;
	}

	public void setName(String name) {
		this.firstName = name;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public List<Problem> getProblems() {
//		return problems;
//	}
//
//	public void setProblems(List<Problem> problems) {
//		this.problems = problems;
//	}
//
//	public List<Problem> getSolvedProblems() {
//		return solvedProblems;
//	}
//
//	public void setSolvedProblems(List<Problem> solvedProblems) {
//		this.solvedProblems = solvedProblems;
//	}

}
