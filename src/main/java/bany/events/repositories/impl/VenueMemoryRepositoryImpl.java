package bany.events.repositories.impl;

import bany.events.dtos.request.VenueRequest;
import bany.events.exceptions.ResourceNotFoundException;
import bany.events.mappers.VenueMapper;
import bany.events.models.Venue;
import bany.events.repositories.interfaces.VenueRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class VenueMemoryRepositoryImpl implements VenueRepository {

    private final List<Venue> venues = new java.util.ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Venue save(VenueRequest venueRequest) {
        Venue venue = VenueMapper.VENUE_INSTANCE.toEntity(venueRequest);
        venue.setId(idGenerator.getAndIncrement());
        venues.add(venue);
        return venue;
    }

    @Override
    public List<Venue> findAll() {
        return venues;
    }

    @Override
    public Optional<Venue> findById(Long id) {
        return venues.stream()
                .filter(venue -> venue.getId().equals(id))
                .findFirst();
    }

    @Override
    public Venue update(Long id, VenueRequest venueRequest) {
        if (findById(id).isEmpty()){
            throw new ResourceNotFoundException("Venue", id);
        }
        Venue  updatedVenue  = VenueMapper.VENUE_INSTANCE.toEntity(venueRequest);
        updatedVenue.setId(id);

        venues.removeIf(e-> e.getId().equals(id));
        venues.add(updatedVenue);
        return updatedVenue;
    }

    @Override
    public void delete(Long id) {
        if( findById(id).isEmpty()){
            throw new ResourceNotFoundException("Venue", id);
        }
        venues.removeIf(e-> e.getId().equals(id));
    }
}
