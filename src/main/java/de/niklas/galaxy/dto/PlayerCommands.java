package de.niklas.galaxy.dto;

public class PlayerCommands {
	private final String player;
	private final String commands;
	
	
	public PlayerCommands(String player, String commands) {
		super();
		this.player = player;
		this.commands = commands;
	}
	
	public String getPlayer() {
		return player;
	}
	public String getCommands() {
		return commands;
	}
	
}
