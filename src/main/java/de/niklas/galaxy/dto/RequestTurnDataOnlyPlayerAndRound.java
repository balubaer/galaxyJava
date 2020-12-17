package de.niklas.galaxy.dto;

public class RequestTurnDataOnlyPlayerAndRound {
	private final String playerName;
	private final long round;

	private final String worldName;
	private final long distanceLevelHomes;

	public RequestTurnDataOnlyPlayerAndRound(String playerName, long round, String worldName, long distanceLevelHomes) {
		super();
		this.playerName = playerName;
		this.round = round;
		this.worldName = worldName;
		this.distanceLevelHomes = distanceLevelHomes;
	}

	public String getPlayerName() {
		return playerName;
	}

	public long getRound() {
		return round;
	}

	public String getWorldName() {
		return worldName;
	}

	public long getDistanceLevelHomes() {
		return distanceLevelHomes;
	}

}
