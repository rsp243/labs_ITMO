package backend.DTO;

import backend.model.Color;
import backend.model.Coordinates;
import backend.model.Country;
import backend.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonCreatedDTO {
    private String name;
    private Long coordinates_id;
    private java.time.LocalDate creationDate;
    private Color eyeColor;
    private Color hairColor;
    private Long location_id;
    private int height;
    private Country nationality;
}
