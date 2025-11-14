package bany.events.repositories.impl;

import bany.events.dtos.request.EventRequest;
import bany.events.dtos.response.EventResponse;
import bany.events.exceptions.ResourceNotFoundException;
import bany.events.mappers.EventMapper;
import bany.events.models.Event;
import bany.events.repositories.interfaces.EventRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class EventJpaRepositoryImpl  implements EventRepository {

    private final EventJpa jpa;

    public EventJpaRepositoryImpl(EventJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public EventResponse save(EventRequest event) {
        return EventMapper.EVENT_INSTANCE.toResponseDto(
                jpa.save(EventMapper.EVENT_INSTANCE.toEntity(event))
        );
    }

    @Override
    public List<EventResponse> findAll() {
        return jpa.findAll().stream()
                .map(EventMapper.EVENT_INSTANCE::toResponseDto)
                .toList();
    }

    @Override
    public Optional<EventResponse> findById(Long id) {
        Optional<Event> foundEvent = jpa.findById(id);
        if(foundEvent.isEmpty()){
            throw new ResourceNotFoundException("Event", id);
        }
        return Optional.ofNullable(EventMapper.EVENT_INSTANCE
                .toResponseDto(foundEvent.get()));
    }

    @Override
    public EventResponse update(Long id, EventRequest event) {
        Optional<Event> foundEvent = jpa.findById(id);
        if(foundEvent.isEmpty()){
            throw new ResourceNotFoundException("Event", id);
        }
        Event updatedEvent = EventMapper.EVENT_INSTANCE.toEntity(event);
        updatedEvent.setId(id);
        return EventMapper.EVENT_INSTANCE.toResponseDto(jpa.save(updatedEvent));
    }

    @Override
    public void delete(Long id) {
        jpa.deleteById(id);
    }

    public interface EventJpa extends JpaRepository<Event,Long>{

    }
}
