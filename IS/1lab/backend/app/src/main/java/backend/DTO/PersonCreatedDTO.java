package backend.DTO;

import backend.model.Color;
import backend.model.Country;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonCreatedDTO {
    private int id;
    private String name;
    private int coordinates_id;
    private java.time.LocalDate creationDate;
    private Color eyeColor;
    private Color hairColor;
    private int location_id;
    private int height;
    private Country nationality;
}
