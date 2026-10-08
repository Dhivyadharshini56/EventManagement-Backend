package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.model.Registration;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.RegistrationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    public RegistrationController(RegistrationRepository registrationRepository, EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<Registration> getAllRegistrations(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String eventId) {
        if (userId != null && !userId.trim().isEmpty()) {
            return registrationRepository.findByUserId(userId);
        }
        if (eventId != null && !eventId.trim().isEmpty()) {
            return registrationRepository.findByEventId(eventId);
        }
        return registrationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistrationById(@PathVariable String id) {
        return registrationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createRegistration(@RequestBody Registration registration) {
        if (registration.getEventId() == null || registration.getEventId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Event ID is required.");
        }

        Event event = eventRepository.findById(registration.getEventId()).orElse(null);
        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        int requestedTickets = registration.getTicketsCount() != null ? registration.getTicketsCount() : 1;
        if (requestedTickets < 1) {
            return ResponseEntity.badRequest().body("Ticket count must be at least 1.");
        }

        // Prevent overbooking
        List<Registration> existingRegs = registrationRepository.findByEventId(registration.getEventId());
        int alreadyBooked = 0;
        for (Registration r : existingRegs) {
            if (!"Cancelled".equalsIgnoreCase(r.getStatus())) {
                alreadyBooked += (r.getTicketsCount() != null ? r.getTicketsCount() : 1);
            }
        }

        int available = event.getCapacity() - alreadyBooked;
        if (requestedTickets > available) {
            return ResponseEntity.badRequest().body("Only " + Math.max(0, available) + " tickets are available.");
        }

        if (registration.getId() == null || registration.getId().trim().isEmpty()) {
            registration.setId(UUID.randomUUID().toString());
        }
        if (registration.getBookingDate() == null || registration.getBookingDate().trim().isEmpty()) {
            registration.setBookingDate(Instant.now().toString());
        }
        if (registration.getStatus() == null || registration.getStatus().trim().isEmpty()) {
            registration.setStatus("Confirmed");
        }
        registration.setTicketsCount(requestedTickets);
        if (registration.getAmountPaid() == null && event.getPrice() != null) {
            registration.setAmountPaid(event.getPrice() * requestedTickets);
        }

        Registration saved = registrationRepository.save(registration);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable String id, @RequestBody Registration registration) {
        if (!registrationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        registration.setId(id);
        Registration updated = registrationRepository.save(registration);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable String id) {
        if (!registrationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        registrationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
