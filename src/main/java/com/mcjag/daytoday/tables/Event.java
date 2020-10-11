package com.mcjag.daytoday.tables;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.net.URL;

@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventID;
    private String eventName;
    private DateTime startDayAndTime;
    private DateTime endDayAndTime;
    private DateTime alert;
    private String email;
    private URL zoomLink;

    public Event(int eventID, String eventName, DateTime start, DateTime end, DateTime alert, String email, URL zoomLink) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.startDayAndTime = start;
        this.endDayAndTime = end;
        this.alert = alert;
        this.email = email;
        this.zoomLink = zoomLink;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public DateTime getStartDayAndTime() {
        return startDayAndTime;
    }

    public void setStartDayAndTime(DateTime startDayAndTime) {
        this.startDayAndTime = startDayAndTime;
    }

    public DateTime getEndDayAndTime() {
        return endDayAndTime;
    }

    public void setEndDayAndTime(DateTime endDayAndTime) {
        this.endDayAndTime = endDayAndTime;
    }

    public DateTime getAlert() {
        return alert;
    }

    public void setAlert(DateTime alert) {
        this.alert = alert;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public URL getZoomLink() {
        return zoomLink;
    }

    public void setZoomLink(URL zoomLink) {
        this.zoomLink = zoomLink;
    }
}
