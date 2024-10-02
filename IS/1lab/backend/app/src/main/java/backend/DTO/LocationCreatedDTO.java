package backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationCreatedDTO {
    private Long id;
    private Integer x;
    private Integer y;
    private long z;
}
