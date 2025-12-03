package bany.events.domain.port.out;

import bany.events.domain.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EventRepositoryPort {
    Event save (Event event);
    Optional<Event> findById(Long id);
    Page<Event> findAll(Pageable pageable, String location, String city);
    void deleteById(Long id);
}
