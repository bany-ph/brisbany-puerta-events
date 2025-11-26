package bany.events.repositories.interfaces;

import bany.events.dtos.request.EventFilterRequest;
import bany.events.dtos.request.EventRequest;
import bany.events.dtos.response.EventResponse;
import bany.events.dtos.response.PageResponse;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    EventResponse save (EventRequest event);
    List<EventResponse> findAll ();
    Optional<EventResponse> findById(Long id);
    EventResponse update(Long id,EventRequest event);
    void delete (Long id);

    PageResponse<EventResponse> findAllPaginated(EventFilterRequest filter);
}
