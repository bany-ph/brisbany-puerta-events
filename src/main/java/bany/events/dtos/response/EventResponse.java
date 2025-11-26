package bany.events.dtos.response;

import lombok.Data;

@Data
public class EventResponse {
    private Long id;
    private String name;
    private String date;
    private Long idVenue;
    private String venueName;
    private String venueLocation;
}
