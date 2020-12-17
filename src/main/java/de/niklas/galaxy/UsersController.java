package de.niklas.galaxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import de.niklas.galaxy.dto.Login;
import de.niklas.galaxy.dto.Message;
import de.niklas.galaxy.dto.User;

@RestController
public class UsersController {

	private static final String USER_JSON_FILENAME = "user.json";
	private static final String ADMINUSER_JSON_FILENAME = "adminLogin.json";

	public static String getResourcePathForFile(String fname) {
		Properties systemProperties = System.getProperties();
		String path = systemProperties.getProperty("user.dir");
		path = path + "/src/main/resources/" + fname;
		return path;
	}

	public static ArrayList<User> getUserArrayList() {
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<User> userArray = new ArrayList<User>();
		InputStream input;
		try {
			String fileName = getResourcePathForFile(USER_JSON_FILENAME);
			File f = new File(fileName);
			if (f.exists() && !f.isDirectory()) {

				input = new FileInputStream(fileName);
				userArray = objectMapper.readValue(input, new TypeReference<ArrayList<User>>() {
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userArray;
	}

	public static ArrayList<String> getPlayerNamenArrayList() {
		ArrayList<String> playerNameArray = new ArrayList<String>();
		ArrayList<User> users = UsersController.getUserArrayList();

		for (User user : users) {
			playerNameArray.add(user.getUsername());
		}

		return playerNameArray;
	}

	private void writeUserArrayList(ArrayList<User> userArray) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
		try {
			writer.writeValue(new File(getResourcePathForFile(USER_JSON_FILENAME)), userArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/api/users/users")
	public ArrayList<User> users() {
		ArrayList<User> userArray = UsersController.getUserArrayList();

		return userArray;
	}

	@PostMapping("/api/users/register")
	public Message register(@RequestBody User user) {
		Message result = new Message("OK");
		ArrayList<User> userArray = UsersController.getUserArrayList();

		userArray.add(user);
		this.writeUserArrayList(userArray);

		return result;
	}

	@PostMapping("/api/users/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody Login login) {
		ArrayList<User> userArray = UsersController.getUserArrayList();
		for (User user : userArray) {
			if (user.getUsername().equals(login.getUsername())) {
				return ResponseEntity.ok(user);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new User());
	}

	private void writeUserAdminUser(Login login) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
		try {
			writer.writeValue(new File(getResourcePathForFile(ADMINUSER_JSON_FILENAME)), login);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/api/users/setAdminUser")
	public Message setAdminUser(@RequestBody Login login) {
		Message result = new Message("OK");

		this.writeUserAdminUser(login);

		return result;
	}

	private Login getAdminUser() {
		ObjectMapper objectMapper = new ObjectMapper();
		Login login = new Login("", "");
		InputStream input;
		try {
			String fileName = getResourcePathForFile(ADMINUSER_JSON_FILENAME);
			File f = new File(fileName);
			if (f.exists() && !f.isDirectory()) {

				input = new FileInputStream(fileName);
				login = objectMapper.readValue(input, Login.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return login;
	}

	@PostMapping("/api/users/authenticateAdmin")
	public ResponseEntity<?> authenticateAdmin(@RequestBody Login login) {
		Login loginAdmin = this.getAdminUser();
		if (loginAdmin.getUsername().equals(login.getUsername())) {
			return ResponseEntity.ok(loginAdmin);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Login("", ""));
	}

}
