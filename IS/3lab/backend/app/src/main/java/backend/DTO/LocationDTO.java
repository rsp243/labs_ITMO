package backend.DTO;

import lombok.Data;

@Data
public class LocationDTO implements TokenizedDTO {
    private Integer x;
    private Integer y;
    private long z;
    private boolean isEditableByAdmin;
    private TokenDTO token;
}
