package de.niklas.galaxy.dto;

public class RequestTurnDataOnlyPlayer {
	 private final String playerName;
	 private final long distanceLevelHomes;

	public RequestTurnDataOnlyPlayer(String playerName, long distanceLevelHomes) {
		super();
		this.playerName = playerName;
		this.distanceLevelHomes = distanceLevelHomes;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public long getDistanceLevelHomes() {
		return distanceLevelHomes;
	}

}
