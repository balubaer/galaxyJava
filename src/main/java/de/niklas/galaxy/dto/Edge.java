package de.niklas.galaxy.dto;

public class Edge {
	
	private final String id;
	private final String source;
	private final String target;
	private final String label;
	
	public Edge() {
		this.id = "";
		this.source = "";
		this.target = "";
		this.label = "";
	}
	
	public Edge(String id, String source, String target, String label) {
		super();
		this.id = id;
		this.source = source;
		this.target = target;
		this.label = label;
	}

	public String getId() {
		return id;
	}
	public String getSource() {
		return source;
	}
	public String getTarget() {
		return target;
	}
	public String getLabel() {
		return label;
	}

}
