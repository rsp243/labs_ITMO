package backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationCreatedDTO {
    private int id;
    private Integer x;
    private Integer y;
    private long z;
}
