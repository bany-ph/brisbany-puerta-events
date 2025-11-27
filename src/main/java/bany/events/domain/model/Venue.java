package bany.events.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    private Long id;
    private String name;
    private String location;
}
