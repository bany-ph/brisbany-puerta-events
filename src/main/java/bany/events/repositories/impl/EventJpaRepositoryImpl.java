package bany.events.repositories.impl;

import bany.events.dtos.request.EventFilterRequest;
import bany.events.dtos.request.EventRequest;
import bany.events.dtos.response.EventResponse;
import bany.events.dtos.response.PageResponse;
import bany.events.entities.EventEntity;
import bany.events.exceptions.ResourceNotFoundException;
import bany.events.mappers.EventMapper;
import bany.events.repositories.jpa.EventJpa;
import bany.events.repositories.interfaces.EventRepository;
import org.springframework.context.annotation.Primary;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@Profile("!memory")
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
        Optional<EventEntity> foundEvent = jpa.findById(id);
        if(foundEvent.isEmpty()){
            throw new ResourceNotFoundException("Event", id);
        }
        return Optional.ofNullable(EventMapper.EVENT_INSTANCE
                .toResponseDto(foundEvent.get()));
    }

    @Override
    public EventResponse update(Long id, EventRequest event) {
        Optional<EventEntity> foundEvent = jpa.findById(id);
        if(foundEvent.isEmpty()){
            throw new ResourceNotFoundException("Event", id);
        }
        EventEntity updatedEventEntity = EventMapper.EVENT_INSTANCE.toEntity(event);
        updatedEventEntity.setId(id);
        return EventMapper.EVENT_INSTANCE.toResponseDto(jpa.save(updatedEventEntity));
    }

    @Override
    public void delete(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public PageResponse<EventResponse> findAllPaginated(EventFilterRequest filter) {
        Sort sort = filter.getDirection().equalsIgnoreCase("DESC") ?
                Sort.by(filter.getSortBy()).descending() :
                Sort.by(filter.getSortBy()).ascending();

        Pageable pageable = PageRequest.of(
                filter.getPage(),
                filter.getSize(),
                sort
        );

        Page<EventEntity> eventPage = jpa.findByFilters(
                filter.getName(),
                filter.getLocation(),
                filter.getDate(),
                pageable
        );

        List<EventResponse> eventResponses = eventPage.getContent().stream()
                .map(EventMapper.EVENT_INSTANCE::toResponseDto).toList();


        return PageResponse.<EventResponse>builder()
                .content(eventResponses)
                .pageNumber(eventPage.getNumber())
                .pageSize(eventPage.getSize())
                .totalElements(eventPage.getTotalElements())
                .totalPages(eventPage.getTotalPages())
                .last(eventPage.isLast())
                .first(eventPage.isFirst())
                .build();
    }

}
