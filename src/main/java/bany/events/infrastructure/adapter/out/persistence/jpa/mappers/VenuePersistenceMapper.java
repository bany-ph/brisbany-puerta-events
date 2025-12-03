package bany.events.infrastructure.adapter.out.persistence.jpa.mappers;

import bany.events.domain.model.Venue;
import bany.events.infrastructure.adapter.out.persistence.jpa.entity.VenueEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenuePersistenceMapper {

    Venue toDomain(VenueEntity venueEntity);

    VenueEntity toEntity(Venue venue);
}
