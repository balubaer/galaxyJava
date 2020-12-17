package de.niklas.galaxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import de.niklas.galaxy.dto.PlayerColor;
import de.niklas.galaxy.dto.User;

@RestController
public class ColorController {
	private static final String COLOR_JSON_FILENAME = "color.json";
	private static final String FONTCOLOR_JSON_FILENAME = "fontColor.json";

	private static HashMap<String, String> getColorMapByFileName(String fileName) {
		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String, String> map = new HashMap<String, String>();
		InputStream input;
		try {
			String fileNameString = UsersController.getResourcePathForFile(fileName);
			File f = new File(fileNameString);
			if (f.exists() && !f.isDirectory()) {
				TypeFactory typeFactory = objectMapper.getTypeFactory();
				MapType mapType = typeFactory.constructMapType(HashMap.class, String.class, String.class);

				input = new FileInputStream(fileNameString);
				map = objectMapper.readValue(input, mapType);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	private static HashMap<String, String> getColorMap() {
		return ColorController.getColorMapByFileName(COLOR_JSON_FILENAME);
	}

	private static ArrayList<String> getColorFromPlayer() {
		ArrayList<User> userArray = UsersController.getUserArrayList();
		ArrayList<String> colorArray = new ArrayList<String>();

		for (User user : userArray) {
			colorArray.add(user.getColor());
		}
		return colorArray;
	}

	private static Boolean isColorInUsers(ArrayList<String> colorUsers, String color) {
		Boolean result = false;
		for (String string : colorUsers) {
			if (string.equals(color)) {
				result = true;
				break;
			}
		}
		return result;
	}

	private static ArrayList<String> getColorArray() {
		HashMap<String, String> colorMap = ColorController.getColorMap();
		ArrayList<String> colorFromPlayer = ColorController.getColorFromPlayer();
		ArrayList<String> colorArray = new ArrayList<String>();

		Set<String> colorSet = colorMap.keySet();

		for (String string : colorSet) {
			if (ColorController.isColorInUsers(colorFromPlayer, string) == false) {
				colorArray.add(string);
			}
		}
		return colorArray;
	}

	@GetMapping("/api/GetColors")
	public ArrayList<String> getColors() {
		return ColorController.getColorArray();
	}

	private static String findUserColorWithUserName(String userName, ArrayList<User> users) {
		String result = "";
		for (User user : users) {
			if (user.getUsername().equals(userName)) {
				result = user.getColor();
				break;
			}
		}

		return result;
	}

	private static HashMap<String, String> getColorPlayerMap() {
		HashMap<String, String> colorMap = ColorController.getColorMap();
		HashMap<String, String> colorPlayerMap = new HashMap<String, String>();
		ArrayList<String> playerNameArray = UsersController.getPlayerNamenArrayList();
		ArrayList<User> users = UsersController.getUserArrayList();

		for (String playerName : playerNameArray) {
			String foundColorString = ColorController.findUserColorWithUserName(playerName, users);
			colorPlayerMap.put(playerName, colorMap.get(foundColorString));
		}
		return colorPlayerMap;

	}

	private static HashMap<String, String> getFontColorMap() {
		return ColorController.getColorMapByFileName(FONTCOLOR_JSON_FILENAME);
	}

	private static HashMap<String, String> getFontColorPlayerMap() {
		HashMap<String, String> fontColorMap = ColorController.getFontColorMap();
		HashMap<String, String> fontColorPlayerMap = new HashMap<String, String>();
		ArrayList<User> users = UsersController.getUserArrayList();
		ArrayList<String> playerNameArray = UsersController.getPlayerNamenArrayList();

		for (String playerName : playerNameArray) {
			String foundColorString = ColorController.findUserColorWithUserName(playerName, users);
			fontColorPlayerMap.put(playerName, fontColorMap.get(foundColorString));
		}
		return fontColorPlayerMap;
	}

	@GetMapping("/api/GetPlayerColor")
	public ArrayList<PlayerColor> getPlayerColor() {
		ArrayList<PlayerColor> result = new ArrayList<PlayerColor>();
		HashMap<String, String> colorPlayerMap = ColorController.getColorPlayerMap();
		HashMap<String, String> fontColorMap = ColorController.getFontColorPlayerMap();
		Set<String> keys = colorPlayerMap.keySet();

		for (String playerName : keys) {
			result.add(new PlayerColor(playerName, colorPlayerMap.get(playerName), fontColorMap.get(playerName)));
		}

		return result;
	}

	public static String getColorWithPlayerName(String playerName) {
		String result = "lightgray";
		HashMap<String, String> colorPlayerMap = ColorController.getColorPlayerMap();
		if (playerName != null) {
			result = colorPlayerMap.get(playerName);
		}
		return result;
	}

}
