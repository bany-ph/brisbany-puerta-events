package bany.events.infrastructure.adapter.out.persistence.adapters;

import bany.events.domain.model.Event;
import bany.events.domain.port.out.EventRepositoryPort;
import bany.events.infrastructure.adapter.out.persistence.jpa.entity.EventEntity;
import bany.events.infrastructure.adapter.out.persistence.jpa.mappers.EventPersistenceMapper;
import bany.events.infrastructure.adapter.out.persistence.jpa.repository.EventJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventJpaAdapter implements EventRepositoryPort {

    private final EventJpaRepository eventJpaRepository;
    private final EventPersistenceMapper mapper;

    @Override
    public Event save(Event event) {
        EventEntity eventEntity = mapper.toEntity(event);
        return mapper.toDomain(eventJpaRepository.save(eventEntity));
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Event> findAll(Pageable pageable, String location, String city) {
        Specification<EventEntity> specification = (root,query,cb) -> cb.conjunction();

        if(city != null && !city.isEmpty()) {
            specification = specification.and((root, query, cb) ->
                    cb.equal(root.get("venue").get("location"), city));
        }

        if(location != null && !location.isEmpty()) {
            specification = specification.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + location.toLowerCase() + "%"));

        }
        Page<EventEntity> entityPage = eventJpaRepository.findAll(specification,pageable);


        return entityPage.map(mapper::toDomain);
    }


    @Override
    public void deleteById(Long id) {
        eventJpaRepository.deleteById(id);
    }
}
