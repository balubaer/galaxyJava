package de.niklas.galaxy.dto;

public class Login {
	private final String username;
	private final String password;
	
	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Login() {
		this.username = "";
		this.password = "";
	} 
	
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}


}
