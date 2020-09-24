package com.mcjag.daytoday;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Logger;

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
	public String onThisDay() throws IOException {
		Document doc = Jsoup.connect("https://www.history.com/this-day-in-history").get();
		String keywords = doc.select("meta[itemprop=description]").first().attr("content");
		return String.format("On this day: %s", keywords);
	}
	
	@GetMapping("/tomorrow")
	public String tomorrow() {
		return "tomorrow";
	}
	
	@GetMapping("/bye")
	public String bye(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Bye %s!", name);
	}
}
