package bany.events.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventFilterRequest {
    private String name;
    private String location;
    private String date;

    private Integer page = 0; // default page number
    private Integer size = 10;
    private String sortBy = "date";
    private String direction = "ASC"; // ASC or DESC

}
