package bany.events.application.usecase.venue;

import bany.events.domain.model.Venue;
import bany.events.domain.port.in.FindVenueUseCase;
import bany.events.domain.port.out.VenueRepositoryPort;
import bany.events.domain.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FindVenueUseCaseImpl implements FindVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public FindVenueUseCaseImpl(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public Venue findById(Long id) {
        return venueRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venue", id));
    }

    @Override
    public List<Venue> findAll() {
        return venueRepositoryPort.findAll();
    }
}
