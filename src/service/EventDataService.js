import http from "../http-common";

class EventDataService
{
   calendar() 
    {
        return http.get("/calendar");
    }

   onThisDay()
    {
        return http.get("/onThisDay");
    }

    addEvent(event)
    {
        return http.get("/event");
    }

    showAllEvents() 
    {
        return http.get("/events");
    }

    deleteEvent(eventID) 
    {
        return http.delete("/event/{eventID}");
    }

    updateEvent(event) {
        return http.put("/event/{eventID}");
    }
}
export default new EventDataService();