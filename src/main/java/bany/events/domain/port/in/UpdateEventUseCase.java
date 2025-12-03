package bany.events.domain.port.in;

import bany.events.domain.model.Event;

public interface UpdateEventUseCase {
    Event update(Long id, Event event);
}
