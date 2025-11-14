package bany.events.services.interfaces;

import bany.events.dtos.request.EventRequest;
import bany.events.dtos.response.EventResponse;

import java.util.List;

public interface EventService {
    EventResponse save (EventRequest eventRequest);
    List<EventResponse> findAll();
    EventResponse findById(Long id);
    EventResponse update(Long id, EventRequest eventRequest);
    void delete(Long id);
}
