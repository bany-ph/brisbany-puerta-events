package bany.events.dtos.response;

import lombok.Data;

@Data
public class EventResponse {
    Long id;
    String name;
    String date;
    Long idVenue;
    String venueName;
    String venueLocation;
}
