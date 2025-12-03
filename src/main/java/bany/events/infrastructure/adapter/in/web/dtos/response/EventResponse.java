package bany.events.infrastructure.adapter.in.web.dtos.response;

import bany.events.domain.model.Venue;
import bany.events.infrastructure.adapter.out.persistence.jpa.entity.VenueEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventResponse {
    private Long id;
    private String name;
    private String date;
    private VenueEntity venue;

}
