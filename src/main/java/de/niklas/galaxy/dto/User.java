package de.niklas.galaxy.dto;

public class User {
	private final long id;
	private final String username;
	private final String password;
	private final String firstName;
	private final String lastName;
	private final String token;
	private final String color;
	
	public User() {
		this.id = 0;
		this.username = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.token = "";
		this.color = "";
	}
	public User(long id, String username, String password, String lastName, String firstName, String token, String color) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.token = token;
		this.color = color;
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getToken() {
		return token;
	}

	public String getColor() {
		return color;
	}

}
