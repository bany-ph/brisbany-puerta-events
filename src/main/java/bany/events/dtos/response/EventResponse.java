package bany.events.dtos.response;

import bany.events.models.Venue;
import lombok.Data;

@Data
public class EventResponse {
    Long id;
    String name;
    String date;
    Venue venue;
}
