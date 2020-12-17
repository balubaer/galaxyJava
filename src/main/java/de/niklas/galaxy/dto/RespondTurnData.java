package de.niklas.galaxy.dto;

import java.util.ArrayList;

public class RespondTurnData {
	
	private final String turnDataTxt;
	private final String turnCommanTxt;
	private final String homeWorldName;
	private final long points;
	private final ArrayList<Edge> links;
	private final ArrayList<Node> nodes;
	
	public RespondTurnData(String turnDataTxt, String turnCommanTxt, String homeWorldName, long points,
			ArrayList<Edge> links, ArrayList<Node> nodes) {
		super();
		this.turnDataTxt = turnDataTxt;
		this.turnCommanTxt = turnCommanTxt;
		this.homeWorldName = homeWorldName;
		this.points = points;
		this.links = links;
		this.nodes = nodes;
	}

	public String getTurnDataTxt() {
		return turnDataTxt;
	}

	public String getTurnCommanTxt() {
		return turnCommanTxt;
	}

	public String getHomeWorldName() {
		return homeWorldName;
	}

	public long getPoints() {
		return points;
	}

	public ArrayList<Edge> getLinks() {
		return links;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

}
