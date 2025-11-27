package bany.events.mappers;

import bany.events.dtos.request.EventRequest;
import bany.events.dtos.response.EventResponse;

import bany.events.entities.EventEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = VenueMapper.class)
public interface EventMapper {

    EventMapper EVENT_INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(source = "venue.id", target = "idVenue")
    @Mapping(source = "venue.name", target = "venueName")
    @Mapping(source = "venue.location", target = "venueLocation")
    EventResponse toResponseDto(EventEntity eventEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "idVenue", target = "venue.id")
    EventEntity toEntity(EventRequest eventRequest);

}
