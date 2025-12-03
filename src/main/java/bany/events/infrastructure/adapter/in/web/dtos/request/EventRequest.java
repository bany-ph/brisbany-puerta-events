package bany.events.infrastructure.adapter.in.web.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest{
    @NotNull(message = "id venue cannot be null")
    private Long idVenue;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "date is mandatory")
    private String date;
}
