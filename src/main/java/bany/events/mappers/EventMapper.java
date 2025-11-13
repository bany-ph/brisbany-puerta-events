package bany.events.mappers;

import bany.events.dtos.request.EventRequest;
import bany.events.dtos.response.EventResponse;
import bany.events.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {

    EventMapper EVENTMAPPER = Mappers.getMapper(EventMapper.class);

    EventResponse toResponseDto(Event event);

    @Mapping(target = "id", ignore = true)
    Event toEntity(EventRequest eventRequest);

}
