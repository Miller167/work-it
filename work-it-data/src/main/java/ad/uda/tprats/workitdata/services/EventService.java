package ad.uda.tprats.workitdata.services;

import ad.uda.tprats.workitdata.dtos.EventDTO;
import ad.uda.tprats.workitdata.entities.Event;
import ad.uda.tprats.workitdata.entities.Project;
import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.repositories.EventRepository;
import ad.uda.tprats.workitdata.repositories.ProjectRepository;
import ad.uda.tprats.workitdata.repositories.UserRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // CREATE
    public Event createEvent(EventDTO eventDTO) {
        Event event = new Event();
        if(!eventDTO.isAllDay() && eventDTO.getProject() != null){
            long diffInMillies = Math.abs(eventDTO.getStartDatetime().getTime() - eventDTO.getEndDatetime().getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            double totalHours = eventDTO.getProject().getTotalHours() + diff;
            eventDTO.getProject().setTotalHours(totalHours);
            projectRepository.save(eventDTO.getProject());
        }

        User user = userRepository.getById(eventDTO.getUser());
        event.setTitle(eventDTO.getTitle());

        if(eventDTO.getProject() != null){
            event.setProject(eventDTO.getProject());
        }
        event.setDescription(eventDTO.getDescription());
        event.setStartDatetime(eventDTO.getStartDatetime());
        event.setEndDatetime(eventDTO.getEndDatetime());
        event.setUser(user);
        event.setAllDay(eventDTO.isAllDay());
        return eventRepository.save(event);
    }

    // READ
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    // READ
    public Event getEventById(Long eventId) {
        return eventRepository.getById(eventId);
    }

    public List<Event> getEventsByProject(Project project) {
        return eventRepository.getEventsByProject(project);
    }

    public List<Event> getEventsByUser(User user) {
        return eventRepository.getEventsByUser(user);
    }
    // DELETE
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    // UPDATE
    public Event updateEvent(Long eventId, EventDTO details) {
        User user = userRepository.getById(details.getUser());
        Event event = eventRepository.findById(eventId).get();
        event.setStartDatetime(details.getStartDatetime());
        event.setEndDatetime(details.getEndDatetime());
        event.setDescription(details.getDescription());
        event.setAllDay(details.isAllDay());
        event.setTitle(details.getTitle());
        event.setProject(details.getProject());
        //event.setUser(user);
        return eventRepository.save(event);
    }
}
