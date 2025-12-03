package bany.events.domain.port.in;

import bany.events.domain.model.Venue;

import java.util.List;

public interface FindVenueUseCase {
    Venue findById(Long id);
    List<Venue> findAll();
}
