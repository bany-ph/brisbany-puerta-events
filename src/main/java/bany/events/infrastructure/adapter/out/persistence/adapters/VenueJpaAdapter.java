package bany.events.infrastructure.adapter.out.persistence.adapters;

import bany.events.domain.model.Venue;
import bany.events.domain.port.out.VenueRepositoryPort;
import bany.events.infrastructure.adapter.out.persistence.jpa.mappers.VenuePersistenceMapper;
import bany.events.infrastructure.adapter.out.persistence.jpa.repository.VenueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VenueJpaAdapter implements VenueRepositoryPort {

    private final VenueJpaRepository venueJpaRepository;
    private final VenuePersistenceMapper mapper;

    @Override
    public Venue save(Venue venue) {
        return mapper.toDomain(venueJpaRepository.save(mapper.toEntity(venue)));
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return venueJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Venue> findAll() {
        return venueJpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        venueJpaRepository.deleteById(id);
    }
}
