package bany.events.services.impl;

import bany.events.dtos.request.EventFilterRequest;
import bany.events.dtos.request.EventRequest;
import bany.events.dtos.response.EventResponse;
import bany.events.dtos.response.PageResponse;
import bany.events.exceptions.ResourceNotFoundException;
import bany.events.repositories.interfaces.EventRepository;
import bany.events.services.interfaces.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventResponse save(EventRequest eventRequest) {
        return eventRepository.save(eventRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> findAll() {
        return eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public EventResponse findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("event", id));
    }

    @Override
    public EventResponse update(Long id, EventRequest eventRequest) {
        return eventRepository.update(id,eventRequest);
    }

    @Override
    public void delete(Long id) {
        eventRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<EventResponse> findAllPaginated(EventFilterRequest filter) {
        return eventRepository.findAllPaginated(filter);
    }
}
