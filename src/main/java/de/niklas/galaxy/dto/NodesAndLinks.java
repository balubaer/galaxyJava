package de.niklas.galaxy.dto;

import java.util.ArrayList;

public class NodesAndLinks {
	private final ArrayList<Edge> links;
	private final ArrayList<Node> nodes;
	
	public NodesAndLinks(ArrayList<Edge> links, ArrayList<Node> nodes) {
		super();
		this.links = links;
		this.nodes = nodes;
	}
	
	public NodesAndLinks() {
		this.links = new ArrayList<Edge>();
		this.nodes = new ArrayList<Node>();
	}

	public ArrayList<Edge> getLinks() {
		return links;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}
}
