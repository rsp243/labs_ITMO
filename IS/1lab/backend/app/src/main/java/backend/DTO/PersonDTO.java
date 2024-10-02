package backend.DTO;

import backend.model.Color;
import backend.model.Coordinates;
import backend.model.Country;
import backend.model.Location;
import lombok.Data;

@Data
public class PersonDTO implements TokenizedDTO {
    private String name;
    private Long coordinates_id;
    private Color eye_color;
    private Color hair_color;
    private Long location_id;
    private int height;
    private Country nationality;
    private TokenDTO token;
}
