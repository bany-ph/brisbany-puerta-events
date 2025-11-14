package bany.events.repositories.interfaces;

import bany.events.dtos.request.VenueRequest;
import bany.events.models.Venue;

import java.util.List;
import java.util.Optional;

public interface VenueRepository {
    Venue save (VenueRequest event);
    List<Venue> findAll();
    Optional<Venue> findById(Long id);
    Venue update(Long id,VenueRequest event);
    void delete (Long id);
}
