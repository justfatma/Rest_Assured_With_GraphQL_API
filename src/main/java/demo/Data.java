package demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Data {
	
    @JsonProperty("users")
    private List<User> allUserData;

	public List<User> getAllUserData() {
		return allUserData;
	}

	public void setAllUserData(List<User> allUserData) {
		this.allUserData = allUserData;
	}
    
    

}
