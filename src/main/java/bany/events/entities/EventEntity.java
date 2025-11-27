package bany.events.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200,nullable = false, unique = true)
    private String name;

    @JsonFormat(pattern="dd-MM-yyyy")
    private String date;

    @ManyToOne
    @JoinColumn(name = "id_venue")
    private VenueEntity venueEntity;

}
