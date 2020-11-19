package com.mcjag.daytoday.tables;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.util.Date;

@Entity
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private long eventID;

    @Column(name="eventName")
    private String eventName; 

    @Column(name="startDayAndTime")
    private Date startDayAndTime;

    @Column(name="endDayAndTime")
    private Date endDayAndTime;

    @Column(name="alert")
    private Date alert;

    @Column(name="email")
    private String email;

    @Column(name="zoomLink")
    private String zoomLink;

    public Event() {
    }

    public Event(String eventName, Date start, Date end, Date alert, String email, String zoomLink) {
        this.eventName = eventName;
        this.startDayAndTime = start;
        this.endDayAndTime = end;
        this.alert = alert;
        this.email = email;
        this.zoomLink = zoomLink;
    }

    public long getEventID() {
        return eventID;
    }

    public void setEventID(long eventID) {
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

    @Override
    public String toString(){
        return "Event [id=" + eventID + ", name= " + eventName + "]";
    }
}
