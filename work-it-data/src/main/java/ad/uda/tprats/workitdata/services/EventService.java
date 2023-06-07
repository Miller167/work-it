package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.entities.Event;
import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // CREATE
    public Event createEvent(Event area) {
        return eventRepository.save(area);
    }

    // READ
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    // READ
    public Event getEventById(Long eventId) {
        return eventRepository.getById(eventId);
    }

    public List<Event> getEventsByUser(User user) {
        return eventRepository.getEventsByUser(user);
    }
    // DELETE
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    // UPDATE
    public Event updateEvent(Long eventId, Event details) {
        Event event = eventRepository.findById(eventId).get();
        event.setStartDatetime(details.getStartDatetime());
        event.setEndDatetime(details.getEndDatetime());
        event.setDescription(details.getDescription());
        event.setAllDay(details.isAllDay());
        event.setTitle(details.getTitle());
        event.setProject(details.getProject());
        return eventRepository.save(event);
    }
}
