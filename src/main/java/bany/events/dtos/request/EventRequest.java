package bany.events.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventRequest{
    @NotNull(message = "id venue cannot be null")
    Long idVenue;

    @NotBlank(message = "name is mandatory")
    String name;

    @NotBlank(message = "date is mandatory")
    String date;
}
