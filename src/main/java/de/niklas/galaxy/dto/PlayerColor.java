package de.niklas.galaxy.dto;

public class PlayerColor {
	private final String playername;
	private final String color;
	private final String fontColor;
	
	public PlayerColor() {
		this.playername = "";
		this.color = "";
		this.fontColor = "";
	}

	public PlayerColor(String playername, String color, String fontColor) {
		super();
		this.playername = playername;
		this.color = color;
		this.fontColor = fontColor;
	}
	
	public String getPlayername() {
		return playername;
	}
	public String getColor() {
		return color;
	}
	public String getFontColor() {
		return fontColor;
	}
}
