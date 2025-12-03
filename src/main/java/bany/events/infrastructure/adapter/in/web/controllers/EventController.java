package bany.events.infrastructure.adapter.in.web.controllers;

import bany.events.domain.model.Event;
import bany.events.domain.port.in.CreateEventUseCase;
import bany.events.domain.port.in.DeleteEventUseCase;
import bany.events.domain.port.in.FindEventUseCase;
import bany.events.domain.port.in.UpdateEventUseCase;
import bany.events.infrastructure.adapter.in.web.dtos.request.EventRequest;
import bany.events.infrastructure.adapter.in.web.dtos.response.EventResponse;
import bany.events.infrastructure.adapter.in.web.mappers.EventWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
@RequiredArgsConstructor
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final FindEventUseCase findEventUseCase;
    private final EventWebMapper mapper;

    @Operation(summary = "Create a new Event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully"),
            @ApiResponse(responseCode = "404", description = "Venue ID does not exist"),
            @ApiResponse(responseCode = "409", description = "Duplicate event name (Conflict)")
    })
    @PostMapping
    public ResponseEntity<EventResponse> create(@Valid @RequestBody EventRequest request) {
        Event event = mapper.toDomain(request);
        Event createdEvent = createEventUseCase.save(event);
        EventResponse response = mapper.toResponseDto(createdEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get Event by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getById(@PathVariable Long id) {
         EventResponse eventResponse =  mapper.toResponseDto(findEventUseCase.findById(id));
        return ResponseEntity.ok(eventResponse);
    }



    @Operation(summary = "Update an Event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated successfully"),
            @ApiResponse(responseCode = "404", description = "Event or Venue not found"),
            @ApiResponse(responseCode = "409", description = "Duplicate name (Conflict)")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody EventRequest request) {
        Event event = mapper.toDomain(request);
        Event updatedEvent = updateEventUseCase.update(id, event);
        EventResponse response = mapper.toResponseDto(updatedEvent);
        return ResponseEntity.ok(response);
    }



    @Operation(summary = "Delete an Event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteEventUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all Events with Pagination and Filters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paginated list retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<Page<EventResponse>> getAllEventPaginated(
            Pageable pageable,
            @RequestParam(required = false)String location,
            @RequestParam(required = false)String city
    ){

        Page<Event> eventsPage = findEventUseCase.findAll(pageable, location, city);
        return ResponseEntity.ok(eventsPage.map(mapper::toResponseDto));
    }
}
