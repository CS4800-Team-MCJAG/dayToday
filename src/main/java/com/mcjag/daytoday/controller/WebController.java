package com.mcjag.daytoday.controller;

import com.mcjag.daytoday.providers.EventProvider;
import com.mcjag.daytoday.providers.UserProvider;
import com.mcjag.daytoday.providers.WelcomeProvider;
import com.mcjag.daytoday.tables.Event;
import com.mcjag.daytoday.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.List;

@RestController
public class WebController {
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private EventProvider eventProvider;
    @Autowired
    private WelcomeProvider welcomeProvider;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return welcomeProvider.hello(name);
    }

    @GetMapping("/calendar")
    public String calendar() {
        return welcomeProvider.calendar();
    }

    @GetMapping("/onThisDay")
    public String onThisDay() throws IOException {
        return welcomeProvider.onThisDay();
    }

    @PostMapping("/event")
    public String addEvent(Event e) {
        return eventProvider.addEvent(e);
    }

    @GetMapping("/events")
    public List<Event> showAllEvents() {
        return eventProvider.displayAllEvents();
    }

    @DeleteMapping("/event/{eventID}")
    public String deleteEvent(@PathVariable("eventID") int eventID) {
        return eventProvider.deleteEvent(eventID);
    }

    @PutMapping
    public String updateEvent(Event e) {
        return eventProvider.updateEvent(e);
    }

    @GetMapping(value = "/graph/{email}", produces = "image/png")
    public ResponseEntity<StreamingResponseBody> displayGraph(@PathVariable("email") String email) throws Exception {
        return eventProvider.getGraph(email);
    }

    @GetMapping("/email/{eventID}")
    public String sendEmail(@PathVariable("eventID") int eventID) {
        return eventProvider.email(eventID);
    }

    @PostMapping("/user")
    public String createUser(User u) {
        return userProvider.addUser(u);
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userProvider.displayAllUsers();
    }

    @DeleteMapping("/user/{email}")
    public String deleteUser(@PathVariable("email") String email) {
        return userProvider.deleteUser(email);
    }

    @PutMapping("/user")
    public String updateUser(User u) {
        return userProvider.updateUser(u);
    }
}
