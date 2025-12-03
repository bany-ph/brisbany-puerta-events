package bany.events.domain.port.in;

import bany.events.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindEventUseCase {
    Event findById(Long id);
    Page<Event> findAll(Pageable pageable, String location, String city);

}
