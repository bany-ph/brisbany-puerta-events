package bany.events.infrastructure.adapter.in.web.mappers;

import bany.events.domain.model.Event;
import bany.events.infrastructure.adapter.in.web.dtos.request.EventRequest;
import bany.events.infrastructure.adapter.in.web.dtos.response.EventResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = VenueWebMapper.class)
public interface EventWebMapper {


    EventResponse toResponseDto(Event event);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "idVenue", target = "venue.id")
    Event toDomain(EventRequest eventRequest);

}
