package bany.events.mappers;

import bany.events.dtos.request.EventRequest;
import bany.events.dtos.response.EventResponse;

import bany.events.models.Event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = VenueMapper.class)
public interface EventMapper {

    EventMapper EVENT_INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "idVenue", target = "idVenue")
    EventResponse toResponseDto(Event event);

    @Mapping(target = "id", ignore = true)
    Event toEntity(EventRequest eventRequest);

}
