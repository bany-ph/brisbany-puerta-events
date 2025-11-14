package bany.events.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class VenueRequest {
    @NotBlank(message = "name is mandatory")
    String name;

    @NotBlank(message = "Location is mandatory")
    String location;
}
