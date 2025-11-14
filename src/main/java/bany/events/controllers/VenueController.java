package bany.events.controllers;

import bany.events.dtos.request.VenueRequest;
import bany.events.models.Venue;
import bany.events.services.interfaces.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }


    @PostMapping
    public ResponseEntity<Venue> save(@Valid @RequestBody VenueRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(venueService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<Venue>> findAll(){
        return ResponseEntity.ok().body(venueService.findAll());
    }


    @Operation(
            summary = "Get venue by ID",
            description = "Return an specific venue by id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Venue> findById(@PathVariable Long id){
        return ResponseEntity.ok(venueService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venue> update(@PathVariable Long id, @Valid VenueRequest venueRequest ){
        return ResponseEntity.status(HttpStatus.OK).body(venueService.update(id, venueRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        venueService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
