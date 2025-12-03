package bany.events.infrastructure.adapter.in.web.mappers;

import bany.events.domain.model.Venue;
import bany.events.infrastructure.adapter.in.web.dtos.request.VenueRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VenueWebMapper {


    @Mapping(target = "id", ignore = true)
    Venue toDomain(VenueRequest venueRequest);
}
