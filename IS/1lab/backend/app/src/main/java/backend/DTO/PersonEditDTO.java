package backend.DTO;

import backend.model.Color;
import backend.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonEditDTO implements TokenizedDTO {
    private int id;
    private String name;
    private Long coordinates_id;
    private Color eye_color;
    private Color hair_color;
    private Long location_id;
    private int height;
    private Country nationality;
    private TokenDTO token;
}
