package bany.events.controllers;

import bany.events.dtos.request.EventFilterRequest;
import bany.events.dtos.request.EventRequest;
import bany.events.dtos.response.EventResponse;
import bany.events.dtos.response.PageResponse;
import bany.events.services.interfaces.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponse> create(@Valid @RequestBody EventRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventResponse>> getAll() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody EventRequest request) {
        return ResponseEntity.ok(eventService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PageResponse<EventResponse>> getAllEventPaginated(
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String location,
            @RequestParam(required = false)String date,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "ASC")String direction
    ){

        EventFilterRequest filter = EventFilterRequest.builder()
                .name(name)
                .location(location)
                .size(size)
                .sortBy(sortBy)
                .date(date)
                .page(page)
                .direction(direction)
                .build();

        PageResponse<EventResponse> response = eventService.findAllPaginated(filter);
        return ResponseEntity.ok(response);
    }
}
