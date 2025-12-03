package bany.events.application.usecase.event;

import bany.events.domain.model.Event;
import bany.events.domain.port.in.FindEventUseCase;
import bany.events.domain.port.out.EventRepositoryPort;
import bany.events.domain.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class FindEventUseCaseImpl implements FindEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public FindEventUseCaseImpl(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public Event findById(Long id) {
        return eventRepositoryPort.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Event", id));
    }

    @Override
    public Page<Event> findAll(Pageable pageable, String location, String city) {
        return eventRepositoryPort.findAll(pageable, location, city);
    }
}
