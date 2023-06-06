package ad.uda.tprats.workit.workitapi.controllers;

import ad.uda.tprats.workit.workitapi.helpers.CustomErrorException;
import ad.uda.tprats.workitdata.services.EventService;
import ad.uda.tprats.workitdata.entities.Event;
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

    @PostMapping()
    public Event createEvent(@RequestBody Event event) {
        try {
            return eventService.createEvent(event);
        } catch (CustomErrorException e) {
            throw new CustomErrorException(e.getMessage());
        }
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody Event event) {
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
