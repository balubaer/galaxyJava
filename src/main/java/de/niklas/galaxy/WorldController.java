package de.niklas.galaxy;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.niklas.galaxy.dto.Edge;
import de.niklas.galaxy.dto.Node;

@RestController
public class WorldController {
	
	@GetMapping("/api/WorldStringList")
	public ArrayList<String> getWorldStringList() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("W1(2,3) [MARVIN] (D-Schiffe=1)");
		return array;
	}

	
	@GetMapping("/api/WorldsString")
	public ArrayList<String> getWorldsString() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("W1(2,3) [MARVIN] (D-Schiffe=1)");
		return array;
	}


	@GetMapping("/api/GetWorldsEdge")
	public ArrayList<Edge> getWorldsEdge() {
		ArrayList<Edge> array = new ArrayList<Edge>();
		array.add(new Edge("W1W2", "W1", "W2", "W1->W2"));
		array.add(new Edge("W1W3", "W1", "W3", "W1->W3"));
		return array;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/api/GetWorldsNode")
	public ArrayList<Node> getWorldsNode() {
		ArrayList<Node> array = new ArrayList<Node>();
		HashMap data = new HashMap();
		data.put("dships", new Long(1));
		data.put("backgroundColor", ColorController.getColorWithPlayerName("ZAPHOD"));
		
		array.add(new Node("W1", "W1", data));
		
		data = new HashMap();
		data.put("dships", new Long(5));
		data.put("backgroundColor", ColorController.getColorWithPlayerName("MARVIN"));
		
		array.add(new Node("W2", "W2", data));
		
		data = new HashMap();
		data.put("dships", new Long(0));
		data.put("backgroundColor", ColorController.getColorWithPlayerName(null));
		
		array.add(new Node("W3", "W3", data));
		return array;
	}

}
