package backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinatesCreatedDTO {
    private Long id;
    private Long x;
    private Double y;
}
