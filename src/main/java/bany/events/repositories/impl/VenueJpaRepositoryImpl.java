package bany.events.repositories.impl;

import bany.events.dtos.request.VenueRequest;
import bany.events.exceptions.ResourceNotFoundException;
import bany.events.mappers.VenueMapper;
import bany.events.models.Venue;
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
    public Venue save(VenueRequest event) {
        return venueJpa.save(VenueMapper.VENUE_INSTANCE.toEntity(event));
    }

    @Override
    public List<Venue> findAll() {
        return venueJpa.findAll();
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return venueJpa.findById(id);
    }

    @Override
    public Venue update(Long id, VenueRequest event) {
        Optional<Venue> foundVenue = venueJpa.findById(id);
        if(foundVenue.isEmpty()){
           throw new ResourceNotFoundException("Venue", id);
        }
        Venue updatedVenue = VenueMapper.VENUE_INSTANCE.toEntity(event);
        updatedVenue.setId(id);
        return venueJpa.save(updatedVenue);
    }

    @Override
    public void delete(Long id) {
        venueJpa.deleteById(id);
    }
}
