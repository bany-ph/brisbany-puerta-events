package bany.events.infrastructure.adapter.out.persistence.jpa.mappers;

import bany.events.domain.model.Event;
import bany.events.infrastructure.adapter.out.persistence.jpa.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring", uses = VenuePersistenceMapper.class)
public interface EventPersistenceMapper {

    Event toDomain(EventEntity eventEntity);
    EventEntity toEntity(Event event);

}
