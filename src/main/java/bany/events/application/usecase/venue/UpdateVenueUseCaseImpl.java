package bany.events.application.usecase.venue;

import bany.events.domain.exceptions.ResourceNotFoundException;
import bany.events.domain.model.Venue;
import bany.events.domain.port.in.UpdateVenueUserCase;
import bany.events.domain.port.out.VenueRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateVenueUseCaseImpl implements UpdateVenueUserCase {

    private final VenueRepositoryPort venueRepositoryPort;

    @Override
    public Venue update(Long id, Venue venue) {
        if (venueRepositoryPort.findById(id).isEmpty()){
            throw new ResourceNotFoundException("Venue", id);
        }

        return venueRepositoryPort.save(venue);
    }
}
