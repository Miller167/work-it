package ad.uda.tprats.workitapi.controllers;

import ad.uda.tprats.workitapi.helpers.CustomErrorException;
import ad.uda.tprats.workitdata.dtos.EventDTO;
import ad.uda.tprats.workitdata.entities.Project;
import ad.uda.tprats.workitdata.entities.Todo;
import ad.uda.tprats.workitdata.entities.User;
import ad.uda.tprats.workitdata.services.EventService;
import ad.uda.tprats.workitdata.entities.Event;
import ad.uda.tprats.workitdata.services.ProjectService;
import ad.uda.tprats.workitdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<Event> getAllEvents() {
        try {
            return eventService.getEvents();
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/{eventId}")
    public Event getEventById(@PathVariable Long eventId) {
        try {
            return eventService.getEventById(eventId);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @GetMapping("/userId/{userId}")
    public List<Event> getEventsByUserId(@PathVariable Long userId) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                throw new CustomErrorException("user does not exist");
            }
            return eventService.getEventsByUser(user);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PostMapping()
    public Event createEvent(@RequestBody EventDTO event) {
        try {
            return eventService.createEvent(event);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody EventDTO event) {
        try {
            return ResponseEntity.ok(eventService.updateEvent(eventId, event));
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
        try {
            Event event = eventService.getEventById(eventId);
            if (event == null) {
                throw new CustomErrorException("Event does not exist");
            }
            eventService.deleteEvent(eventId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

}
