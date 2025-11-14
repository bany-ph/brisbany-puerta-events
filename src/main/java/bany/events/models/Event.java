package bany.events.models;

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
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200,nullable = false)
    private String name;

    @JsonFormat(pattern="dd-MM-yyyy")
    private String date;

    @JoinColumn(name = "id_venue")
    @OneToOne
    private Venue venue;

    @Transient
    private Long idVenue;
}
