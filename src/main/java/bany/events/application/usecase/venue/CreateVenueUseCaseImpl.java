package bany.events.application.usecase.venue;

import bany.events.domain.model.Venue;
import bany.events.domain.port.in.CreateVenueUseCase;
import bany.events.domain.port.out.VenueRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateVenueUseCaseImpl implements CreateVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public CreateVenueUseCaseImpl(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Venue save(Venue venue) {
        return venueRepositoryPort.save(venue);
    }
}
