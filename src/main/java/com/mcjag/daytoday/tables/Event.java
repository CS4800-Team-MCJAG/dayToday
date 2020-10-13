package com.mcjag.daytoday.tables;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.util.Date;

@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int eventID;
    private String eventName;
    private Date startDayAndTime;
    private Date endDayAndTime;
    private Date alert;
    private String email;
    private String zoomLink;

    public Event() {
    }

    public Event(String eventName, Date start, Date end, Date alert, String email, String zoomLink) throws MalformedURLException {
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

    public Date getStartDayAndTime() {
        return startDayAndTime;
    }

    public void setStartDayAndTime(Date startDayAndTime) {
        this.startDayAndTime = startDayAndTime;
    }

    public Date getEndDayAndTime() {
        return endDayAndTime;
    }

    public void setEndDayAndTime(Date endDayAndTime) {
        this.endDayAndTime = endDayAndTime;
    }

    public Date getAlert() {
        return alert;
    }

    public void setAlert(Date alert) {
        this.alert = alert;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZoomLink() {
        return zoomLink;
    }

    public void setZoomLink(String zoomLink) {
        this.zoomLink = zoomLink;
    }
}
