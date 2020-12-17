package de.niklas.galaxy.dto;

import java.util.HashMap;

public class Node {
	private final String id;
	private final String label;
	private final HashMap<String, ?> data;
			
	

	public Node(String id, String label, HashMap<String, ?> data) {
		super();
		this.id = id;
		this.label = label;
		this.data = data;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Node() {
		super();
		this.id = "";
		this.label = "";
		this.data = new HashMap();
	}
	
	public HashMap<String, ?> getData() {
		return data;
	}
	
	public String getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}

	
	/*
	 *    id: world.name,
          label: world.name,
	 */
}
