import http from '../http-commons';

class EventService {
    getAll() {
        return http.get('/events');
    }

    get(eventID) {
        return http.get(`/events/${eventID}`);
    }

    create(data) {
        return http.post("/events", data);
    }

    update(eventID, data) {
        return http.put(`/events/${eventID}`, data);
    }

    delete(eventID) {
        return http.delete(`/events/${eventID}`);
    }
}

export default new EventService();