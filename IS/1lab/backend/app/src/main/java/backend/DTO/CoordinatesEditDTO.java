package backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoordinatesEditDTO implements TokenizedDTO {
    private int id;
    private Long x;
    private Double y;
    private TokenDTO token;
}
