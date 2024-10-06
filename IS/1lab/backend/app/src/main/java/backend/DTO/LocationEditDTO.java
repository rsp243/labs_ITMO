package backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationEditDTO implements TokenizedDTO {
    private int id;
    private Integer x;
    private Integer y;
    private long z;
    private TokenDTO token;
}
