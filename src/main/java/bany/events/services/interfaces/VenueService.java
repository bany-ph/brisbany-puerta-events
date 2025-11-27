package bany.events.services.interfaces;

import bany.events.dtos.request.VenueRequest;
import bany.events.entities.VenueEntity;

import java.util.List;

public interface VenueService {
    VenueEntity save (VenueRequest venueRequest);
    List<VenueEntity> findAll ();
    VenueEntity findById(Long id);
    VenueEntity update (Long id, VenueRequest venueRequest);
    void deleteById(Long id);
}
