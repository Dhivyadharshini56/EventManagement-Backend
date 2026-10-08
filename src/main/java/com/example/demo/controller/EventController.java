package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<Event> getAllEvents(
            @RequestParam(required = false) String organizerId,
            @RequestParam(required = false) String category) {
        if (organizerId != null && !organizerId.trim().isEmpty()) {
            return eventRepository.findByOrganizerId(organizerId);
        }
        if (category != null && !category.trim().isEmpty()) {
            return eventRepository.findByCategoryIgnoreCase(category.trim());
        }
        return eventRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return eventRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        if (event.getPrice() != null && (event.getPrice() < 0 || event.getPrice() > 10000.0)) {
            return ResponseEntity.badRequest().body("Ticket price cannot exceed ₹10,000.");
        }
        if (event.getId() == null || event.getId().trim().isEmpty()) {
            event.setId(UUID.randomUUID().toString());
        }
        Event saved = eventRepository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable String id, @RequestBody Event event) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        if (event.getPrice() != null && (event.getPrice() < 0 || event.getPrice() > 10000.0)) {
            return ResponseEntity.badRequest().body("Ticket price cannot exceed ₹10,000.");
        }
        event.setId(id);
        Event updated = eventRepository.save(event);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        if (!eventRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
