package bany.events.repositories.impl;

import bany.events.dtos.request.VenueRequest;
import bany.events.entities.VenueEntity;
import bany.events.exceptions.ResourceNotFoundException;
import bany.events.mappers.VenueMapper;
import bany.events.repositories.interfaces.VenueRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Profile("memory")
public class VenueMemoryRepositoryImpl implements VenueRepository {

    private final List<VenueEntity> venueEntities = new java.util.ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public VenueEntity save(VenueRequest venueRequest) {
        VenueEntity venueEntity = VenueMapper.VENUE_INSTANCE.toEntity(venueRequest);
        venueEntity.setId(idGenerator.getAndIncrement());
        venueEntities.add(venueEntity);
        return venueEntity;
    }

    @Override
    public List<VenueEntity> findAll() {
        return venueEntities;
    }

    @Override
    public Optional<VenueEntity> findById(Long id) {
        return venueEntities.stream()
                .filter(venue -> venue.getId().equals(id))
                .findFirst();
    }

    @Override
    public VenueEntity update(Long id, VenueRequest venueRequest) {
        if (findById(id).isEmpty()){
            throw new ResourceNotFoundException("VenueEntity", id);
        }
        VenueEntity updatedVenueEntity = VenueMapper.VENUE_INSTANCE.toEntity(venueRequest);
        updatedVenueEntity.setId(id);

        venueEntities.removeIf(e-> e.getId().equals(id));
        venueEntities.add(updatedVenueEntity);
        return updatedVenueEntity;
    }

    @Override
    public void delete(Long id) {
        if( findById(id).isEmpty()){
            throw new ResourceNotFoundException("VenueEntity", id);
        }
        venueEntities.removeIf(e-> e.getId().equals(id));
    }
}
