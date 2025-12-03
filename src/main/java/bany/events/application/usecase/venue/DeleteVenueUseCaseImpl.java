package bany.events.application.usecase.venue;

import bany.events.domain.port.in.DeleteVenueUseCase;
import bany.events.domain.port.out.VenueRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteVenueUseCaseImpl implements DeleteVenueUseCase {

    private final VenueRepositoryPort venueRepositoryPort;

    public DeleteVenueUseCaseImpl(VenueRepositoryPort venueRepositoryPort) {
        this.venueRepositoryPort = venueRepositoryPort;
    }

    @Override
    public void deleteById(Long id) {
        venueRepositoryPort.deleteById(id);
    }
}
