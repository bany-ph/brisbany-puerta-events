package bany.events.repositories.interfaces;

import bany.events.dtos.request.VenueRequest;
import bany.events.entities.VenueEntity;

import java.util.List;
import java.util.Optional;

public interface VenueRepository {
    VenueEntity save (VenueRequest event);
    List<VenueEntity> findAll();
    Optional<VenueEntity> findById(Long id);
    VenueEntity update(Long id, VenueRequest event);
    void delete (Long id);
}
