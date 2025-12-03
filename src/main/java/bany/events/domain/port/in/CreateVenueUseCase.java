package bany.events.domain.port.in;

import bany.events.domain.model.Venue;

public interface CreateVenueUseCase {
    Venue save(Venue venue);
}
