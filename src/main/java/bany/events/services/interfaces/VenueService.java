package bany.events.services.interfaces;

import bany.events.dtos.request.VenueRequest;
import bany.events.models.Venue;

import java.util.List;

public interface VenueService {
    Venue save (VenueRequest venueRequest);
    List<Venue> findAll ();
    Venue findById(Long id);
    Venue update (Long id, VenueRequest venueRequest);
    void deleteById(Long id);
}
