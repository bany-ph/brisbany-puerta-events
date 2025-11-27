package bany.events.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long id;
    private String name;
    private String date;
    private Venue venue;
}
