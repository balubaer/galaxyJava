package de.niklas.galaxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.niklas.galaxy.dto.Message;

@SpringBootApplication
@RestController
public class GalaxyApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(GalaxyApplication.class, args);
	}

	@GetMapping("/api/hello")
	public Message hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		Message result = new Message(String.format("Welcome %s to Galaxy api!!", name));
		return result;
	}	
}
