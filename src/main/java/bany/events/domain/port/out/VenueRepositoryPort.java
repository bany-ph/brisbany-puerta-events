package bany.events.domain.port.out;


import bany.events.domain.model.Venue;


import java.util.List;
import java.util.Optional;

public interface VenueRepositoryPort {
    Venue save (Venue venue);
    Optional<Venue> findById(Long id);
    List<Venue> findAll();
    void deleteById(Long id);
}
