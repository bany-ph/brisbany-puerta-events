package bany.events.application.usecase.event;

import bany.events.domain.model.Event;
import bany.events.domain.model.Venue;
import bany.events.domain.port.in.UpdateEventUseCase;
import bany.events.domain.port.out.EventRepositoryPort;
import bany.events.domain.port.out.VenueRepositoryPort;
import bany.events.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateEventUseCaseImpl implements UpdateEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;
    private final VenueRepositoryPort venueRepositoryPort;

    public UpdateEventUseCaseImpl(EventRepositoryPort eventRepositoryPort, VenueRepositoryPort venueRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Event update(Long id, Event event) {
        if(eventRepositoryPort.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Event", id);
        }

        Long venueId = event.getVenue().getId();
        if(venueId == null){
            throw new IllegalArgumentException("Venue ID cannot be null");
        }
        Venue venue = venueRepositoryPort.findById(venueId)
                .orElseThrow(()-> new ResourceNotFoundException("Venue", venueId));


        event.setId(id);
        event.setVenue(venue);
        return eventRepositoryPort.save(event);
    }
}
