package bootwildfly;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


public class User implements Serializable {
	
	enum USER_TYPE{anonymous, normal, administrator}
	
	private String name;
	
	public User() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
