package de.niklas.galaxy.dto;

import java.util.ArrayList;

public class GamePref {
	private final long distanceLevelHomes;
	private final long fleetCount;
	private final long fleetsOnHomeWorld;
	private final long worldCount;
	private final long startShipCount;
	private final long round;
	private final String playName;
	private final ArrayList<String> player;
	
	public GamePref() {
		this.distanceLevelHomes = 0;
		this.fleetCount = 0;
		this.fleetsOnHomeWorld = 0;
		this.worldCount = 0;
		this.startShipCount = 0;
		this.round = 0;
		this.playName = "";
		this.player = new ArrayList<String>();
	}

	

	public GamePref(long distanceLevelHomes, long fleetCount, long fleetsOnHomeWorld, long worldCount,
			long startShipCount, long round, String playName, ArrayList<String> player) {
		super();
		this.distanceLevelHomes = distanceLevelHomes;
		this.fleetCount = fleetCount;
		this.fleetsOnHomeWorld = fleetsOnHomeWorld;
		this.worldCount = worldCount;
		this.startShipCount = startShipCount;
		this.round = round;
		this.playName = playName;
		this.player = player;
	}

	public long getDistanceLevelHomes() {
		return distanceLevelHomes;
	}
	public long getFleetCount() {
		return fleetCount;
	}
	public long getFleetsOnHomeWorld() {
		return fleetsOnHomeWorld;
	}
	public long getWorldCount() {
		return worldCount;
	}
	public long getStartShipCount() {
		return startShipCount;
	}
	public long getRound() {
		return round;
	}
	public String getPlayName() {
		return playName;
	}
	public ArrayList<String> getPlayer() {
		return player;
	}

}
