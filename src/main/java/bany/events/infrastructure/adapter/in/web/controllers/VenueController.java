package bany.events.infrastructure.adapter.in.web.controllers;

import bany.events.domain.model.Venue;
import bany.events.domain.port.in.CreateVenueUseCase;
import bany.events.domain.port.in.DeleteVenueUseCase;
import bany.events.domain.port.in.FindVenueUseCase;
import bany.events.domain.port.in.UpdateVenueUserCase;
import bany.events.infrastructure.adapter.in.web.dtos.request.VenueRequest;
import bany.events.infrastructure.adapter.in.web.mappers.VenueWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueWebMapper mapper;
    private final CreateVenueUseCase createVenueUseCase;
    private final FindVenueUseCase findVenueUseCase;
    private final UpdateVenueUserCase updateVenueUseCase;
    private final DeleteVenueUseCase deleteVenueUseCase;



    @PostMapping
    public ResponseEntity<Venue> save(@Valid @RequestBody VenueRequest request) {
        Venue venue = createVenueUseCase.save(mapper.toDomain(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(venue);
    }

    @GetMapping
    public ResponseEntity<List<Venue>> findAll(){
        return ResponseEntity.ok().body(findVenueUseCase.findAll());
    }


    @Operation(
            summary = "Get venue by ID",
            description = "Return an specific venue by id"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Venue> findById(@PathVariable Long id){
        Venue venue = findVenueUseCase.findById(id);
        return ResponseEntity.ok(venue);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venue> update(@PathVariable Long id, @Valid VenueRequest venueRequest ){
        Venue venue = updateVenueUseCase.update(id, mapper.toDomain(venueRequest));
        return ResponseEntity.status(HttpStatus.OK).body(venue);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        deleteVenueUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
