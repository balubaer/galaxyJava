package de.niklas.galaxy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.niklas.galaxy.dto.GamePref;
import de.niklas.galaxy.dto.Message;
import de.niklas.galaxy.dto.NodesAndLinks;
import de.niklas.galaxy.dto.PlayerCommands;
import de.niklas.galaxy.dto.RequestTurnDataOnlyPlayer;
import de.niklas.galaxy.dto.RequestTurnDataOnlyPlayerAndRound;
import de.niklas.galaxy.dto.RespondTurnData;

@RestController
public class GamePlayController {

	private String getTurnFilePath(String playName, long round, String playerName) {
		StringBuffer sb = new StringBuffer();

		sb.append(playName);
		sb.append('/');
		sb.append("Turn");
		sb.append(round);

		sb.append('/');

		sb.append(playerName);
		return (sb.toString());
	}

	@PostMapping("/api/game-play/GetCommands")
	public PlayerCommands getCommands(@RequestBody RequestTurnDataOnlyPlayer request) {
		String commands = "";
		GamePref gamePref = CreateWorldController.getGamePrefFromFile();
		String turnFilePathString = this.getTurnFilePath(gamePref.getPlayName(), gamePref.getRound() + 1,
				request.getPlayerName());

		String fileName = UsersController.getResourcePathForFile(turnFilePathString + ".txt");
		try {
			File f = new File(fileName);
			if (f.exists() && !f.isDirectory()) {
				byte[] encoded = Files.readAllBytes(Paths.get(fileName));
				commands = new String(encoded, StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new PlayerCommands(request.getPlayerName(), commands);
	}

	@PostMapping("/api/game-play/SetCommands")
	public Message setCommands(@RequestBody PlayerCommands request) {
		Message message = new Message("OK"); 
		GamePref gamePref = CreateWorldController.getGamePrefFromFile();
		String turnFilePathString = this.getTurnFilePath(gamePref.getPlayName(), gamePref.getRound() + 1,
				request.getPlayer());
		String fileName = UsersController.getResourcePathForFile(turnFilePathString + ".txt");

		Path path = Paths.get(fileName);
		String directory = path.getParent().toString();
		
		File theDir = new File(directory);
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
		
		try (PrintWriter out = new PrintWriter(fileName)) {
		    out.println(request.getCommands());
		}catch (IOException e) {
			e.printStackTrace();
			message = new Message("Error");
		}
		
		return message;
	}

	private RespondTurnData getRespondTurnData(String playName, long round, String playerName) {
		String commands = "";
		String turnDataTxTString = "";
		String turnFilePathString = this.getTurnFilePath(playName, round + 1, playerName);

		String fileName = UsersController.getResourcePathForFile(turnFilePathString + ".txt");
		try {
			File f = new File(fileName);
			if (f.exists() && !f.isDirectory()) {
				byte[] encoded = Files.readAllBytes(Paths.get(fileName));
				commands = new String(encoded, StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		turnFilePathString = this.getTurnFilePath(playName, round, playerName);
		fileName = UsersController.getResourcePathForFile(turnFilePathString + ".out");
		try {
			File f = new File(fileName);
			if (f.exists() && !f.isDirectory()) {
				byte[] encoded = Files.readAllBytes(Paths.get(fileName));
				turnDataTxTString = new String(encoded, StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ObjectMapper objectMapper = new ObjectMapper();
		NodesAndLinks turnDataGraf = null;

		InputStream input;
		try {
			fileName = UsersController.getResourcePathForFile(turnFilePathString + "_graf.json");
			File f = new File(fileName);
			if (f.exists() && !f.isDirectory()) {
				input = new FileInputStream(fileName);
				turnDataGraf = objectMapper.readValue(input, NodesAndLinks.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new RespondTurnData(turnDataTxTString, commands, "W1", 0, turnDataGraf.getLinks(),
				turnDataGraf.getNodes());
	}

	@PostMapping("/api/game-play/GetTurnDataOnlyPlayer")
	public RespondTurnData getTurnDataOnlyPlayer(@RequestBody RequestTurnDataOnlyPlayer request) {
		GamePref gamePref = CreateWorldController.getGamePrefFromFile();
		return this.getRespondTurnData(gamePref.getPlayName(), gamePref.getRound(), request.getPlayerName());

	}

	@PostMapping("/api/game-play/GetTurnDataOnlyPlayerAndRound")
	public RespondTurnData getTurnDataOnlyPlayer(@RequestBody RequestTurnDataOnlyPlayerAndRound request) {
		GamePref gamePref = CreateWorldController.getGamePrefFromFile();
		return this.getRespondTurnData(gamePref.getPlayName(), request.getRound(), request.getPlayerName());
	}

}
