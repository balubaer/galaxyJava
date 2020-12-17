package de.niklas.galaxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import de.niklas.galaxy.dto.GamePref;

@RestController
public class CreateWorldController {
	
	private static final String GAMEPREF_JSON_FILENAME = "gamePref.json";

	public static GamePref getGamePrefFromFile() {
		ObjectMapper objectMapper = new ObjectMapper();
		GamePref gamePref = new GamePref();
		InputStream input;
		try {
			String fileName = UsersController.getResourcePathForFile(GAMEPREF_JSON_FILENAME);
			File f = new File(fileName);
			if (f.exists() && !f.isDirectory()) {

				input = new FileInputStream(fileName);
				gamePref = objectMapper.readValue(input, GamePref.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gamePref;
	}
	
	@GetMapping("/api/create-world/GetGamePref")
	public GamePref getGamePref() {
		GamePref gamePref = CreateWorldController.getGamePrefFromFile();

		return gamePref;
	}

	@GetMapping("/api/create-world/GetPlayerList")
	public ArrayList<String> getPlayerList() {
		GamePref gamePref = CreateWorldController.getGamePrefFromFile();

		return gamePref.getPlayer();
	}

	private void writeGamePref(GamePref gamePref) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
		try {
			writer.writeValue(new File(UsersController.getResourcePathForFile(GAMEPREF_JSON_FILENAME)), gamePref);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/api/create-world/SetGamePref")
	public void register(@RequestBody GamePref gamePref) {
		this.writeGamePref(gamePref);

	}

}
