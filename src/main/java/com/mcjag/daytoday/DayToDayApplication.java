package com.mcjag.daytoday;

import com.mcjag.daytoday.providers.EventProvider;
import com.mcjag.daytoday.providers.UserProvider;
import com.mcjag.daytoday.providers.WelcomeProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@EnableScheduling
public class DayToDayApplication {

	@Bean
	public EventProvider eventProvider() {
		EventProvider eventProvider = new EventProvider();
		return eventProvider;
	}

	@Bean
	public UserProvider userProvider() {
		UserProvider userProvider = new UserProvider();
		return userProvider;
	}

	@Bean
	public WelcomeProvider welcomeProvider() {
		WelcomeProvider welcomeProvider = new WelcomeProvider();
		return welcomeProvider;
	}

	public static void main(String[] args) {
		SpringApplication.run(DayToDayApplication.class, args);
	}
}
