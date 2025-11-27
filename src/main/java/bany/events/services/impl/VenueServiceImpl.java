package bany.events.services.impl;

import bany.events.dtos.request.VenueRequest;
import bany.events.entities.VenueEntity;
import bany.events.exceptions.ResourceNotFoundException;
import bany.events.repositories.interfaces.VenueRepository;
import bany.events.services.interfaces.VenueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public VenueEntity save(VenueRequest venueRequest) {
        return venueRepository.save(venueRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public List<VenueEntity> findAll() {
        return venueRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public VenueEntity findById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("VenueEntity", id));
    }

    @Override
    public VenueEntity update(Long id, VenueRequest venueRequest) {
        return venueRepository.update(id, venueRequest);
    }

    @Override
    public void deleteById(Long id) {
        venueRepository.delete(id);
    }
}
