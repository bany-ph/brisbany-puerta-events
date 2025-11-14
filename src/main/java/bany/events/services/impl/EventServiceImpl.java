package bany.events.services.impl;

import bany.events.dtos.request.EventRequest;
import bany.events.dtos.response.EventResponse;
import bany.events.exceptions.ResourceNotFoundException;
import bany.events.repositories.interfaces.EventRepository;
import bany.events.services.interfaces.EventService;
import org.springframework.stereotype.Service;

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
    public List<EventResponse> findAll() {
        return List.of();
    }

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
}
