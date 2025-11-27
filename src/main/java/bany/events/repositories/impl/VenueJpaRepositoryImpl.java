package bany.events.repositories.impl;

import bany.events.dtos.request.VenueRequest;
import bany.events.entities.VenueEntity;
import bany.events.exceptions.ResourceNotFoundException;
import bany.events.mappers.VenueMapper;
import bany.events.repositories.interfaces.VenueRepository;
import bany.events.repositories.jpa.VenueJpa;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@Profile("!memory")
public class VenueJpaRepositoryImpl implements VenueRepository {

    private final VenueJpa venueJpa;

    public VenueJpaRepositoryImpl(VenueJpa venueJpa) {
        this.venueJpa = venueJpa;
    }

    @Override
    public VenueEntity save(VenueRequest event) {
        return venueJpa.save(VenueMapper.VENUE_INSTANCE.toEntity(event));
    }

    @Override
    public List<VenueEntity> findAll() {
        return venueJpa.findAll();
    }

    @Override
    public Optional<VenueEntity> findById(Long id) {
        return venueJpa.findById(id);
    }

    @Override
    public VenueEntity update(Long id, VenueRequest event) {
        Optional<VenueEntity> foundVenue = venueJpa.findById(id);
        if(foundVenue.isEmpty()){
           throw new ResourceNotFoundException("VenueEntity", id);
        }
        VenueEntity updatedVenueEntity = VenueMapper.VENUE_INSTANCE.toEntity(event);
        updatedVenueEntity.setId(id);
        return venueJpa.save(updatedVenueEntity);
    }

    @Override
    public void delete(Long id) {
        venueJpa.deleteById(id);
    }
}
