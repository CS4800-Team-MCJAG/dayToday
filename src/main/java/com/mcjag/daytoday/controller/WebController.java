package com.mcjag.daytoday.controller;

import com.mcjag.daytoday.providers.EventProvider;
import com.mcjag.daytoday.providers.WelcomeProvider;
import com.mcjag.daytoday.repository.EventRepository;
import com.mcjag.daytoday.tables.Event;
import com.mcjag.daytoday.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("")
public class WebController {

    @Autowired
    private EventRepository eventRepository;
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

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        try {
          List<Event> events = new ArrayList<Event>();

          eventRepository.findAll().forEach(events::add);

          if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
    
          return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        try {
          Event _event = eventRepository
              .save(new Event(event.getEventName(), event.getStartDayAndTime(), event.getEndDayAndTime(), event.getAlert(), event.getEmail(), event.getZoomLink()));
          return new ResponseEntity<>(_event, HttpStatus.CREATED);
        } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") long id) {
        Optional<Event> eventData = eventRepository.findById(id);
    
        if (eventData.isPresent()) {
          return new ResponseEntity<>(eventData.get(), HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable("id") long id) {
        try {
          eventRepository.deleteById(id);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") long id, @RequestBody Event event) {
        Optional<Event> eventData = eventRepository.findById(id);

        if (eventData.isPresent()) {
            Event _event = eventData.get();
            _event.setEventName(event.getEventName());
            _event.setStartDayAndTime(event.getStartDayAndTime());
            _event.setEndDayAndTime(event.getEndDayAndTime());
            _event.setAlert(event.getAlert());
            _event.setZoomLink(event.getZoomLink());
            return new ResponseEntity<>(eventRepository.save(_event), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/graph/{email}", produces = "image/png")
    public ResponseEntity<StreamingResponseBody> displayGraph(@PathVariable("email") String email) throws Exception {
        return eventProvider.getGraph(email);
    }

    @GetMapping("/email/{id}")
    public String sendEmail(@PathVariable("id") long id) {
        return eventProvider.email(id);
    }

}
