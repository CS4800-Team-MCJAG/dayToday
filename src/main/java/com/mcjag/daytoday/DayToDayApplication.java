package com.mcjag.daytoday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DayToDayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DayToDayApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/calendar")
	public String calendar() {
		return "Welcome to your Calendar!";
	}

	@GetMapping("/onThisDay")
	public String onThisDay() {
		return "On this day...";
	}
	
	@GetMapping("/bye")
	public String bye(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Bye %s!", name);
	}
}
