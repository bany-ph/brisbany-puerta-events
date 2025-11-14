package bany.events.mappers;

import bany.events.dtos.request.VenueRequest;
import bany.events.models.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VenueMapper {

    VenueMapper VENUE_INSTANCE = Mappers.getMapper(VenueMapper.class);

    @Mapping(target = "id", ignore = true)
    Venue toEntity (VenueRequest venueRequest);
}
