package backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinatesCreatedDTO {
    private int id;
    private Long x;
    private Double y;
    private boolean updatable;
}
