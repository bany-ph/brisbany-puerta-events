package bany.events.domain.port.in;

import bany.events.domain.model.Venue;

public interface UpdateVenueUserCase {
    Venue update(Long id, Venue venue);
}
