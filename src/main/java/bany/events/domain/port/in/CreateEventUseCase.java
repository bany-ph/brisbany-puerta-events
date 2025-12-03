package bany.events.domain.port.in;

import bany.events.domain.model.Event;

public interface CreateEventUseCase {
    Event save (Event event);
}
