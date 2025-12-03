package bany.events.application.usecase.event;

import bany.events.domain.model.Event;
import bany.events.domain.model.Venue;
import bany.events.domain.port.in.CreateEventUseCase;
import bany.events.domain.port.out.EventRepositoryPort;
import bany.events.domain.port.out.VenueRepositoryPort;
import bany.events.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class CreateEventUseCaseImpl implements CreateEventUseCase {
    private final EventRepositoryPort eventRepositoryPort;
    private final VenueRepositoryPort venueRepositoryPort;

    public CreateEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, VenueRepositoryPort venueRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Event save(Event event) {


        Venue venue = venueRepositoryPort.findById(event.getVenue().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Venue", event.getVenue().getId()));
        event.setVenue(venue);

        return eventRepositoryPort.save(event);
    }
}
