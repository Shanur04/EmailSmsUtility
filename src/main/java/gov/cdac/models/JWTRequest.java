package gov.cdac.models;

public class JWTRequest {
	
	private String username;
	
	private String password;

	public JWTRequest() {
		
	}

	public JWTRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
